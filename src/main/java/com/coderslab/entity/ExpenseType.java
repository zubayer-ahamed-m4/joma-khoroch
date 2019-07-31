package com.coderslab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Zubayer Ahamed
 *
 */
@Data
@Entity
@Table(name = "expensetype")
public class ExpenseType {

	@Id
	@SequenceGenerator(name = "ExpenseTypeSeqGen", schema = "public", sequenceName = "incomesource_incomesourceid_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IncomeSourceSeqGen")
	@Column(name = "incomeSourceId", nullable = false, unique = true)
	private Long expenseTypeId;

	@Column(name = "expenseTypeName")
	private String expenseTypeName;

	@Column(name = "note")
	private String note;
}
