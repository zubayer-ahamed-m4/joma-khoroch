package com.coderslab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Zubayer Ahamed
 *
 */
@Data
@Entity
@Table(name = "expensetype")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExpenseType extends AbstractModel {

	@Id
	@SequenceGenerator(name = "ExpenseTypeSeqGen", schema = "public", sequenceName = "incomesource_incomesourceid_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IncomeSourceSeqGen")
	@Column(name = "incomeSourceId", nullable = false, unique = true)
	private Long expenseTypeId;

	@Column(name = "expenseTypeName")
	private String expenseTypeName;

	@Column(name = "note")
	private String note;

	@Column(name = "icon")
	private String icon;

	@Column(name = "userId")
	private Long userId;
}
