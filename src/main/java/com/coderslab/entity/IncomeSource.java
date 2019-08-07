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
@Table(name = "incomesource")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IncomeSource extends AbstractModel {

	@Id
	@SequenceGenerator(name = "IncomeSourceSeqGen", schema = "public", sequenceName = "incomesource_incomesourceid_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IncomeSourceSeqGen")
	@Column(name = "incomeSourceId", nullable = false, unique = true)
	private Long IncomeSourceId;

	@Column(name = "incomeSourceName")
	private String incomeSourceName;

	@Column(name = "notes")
	private String notes;

	@Column(name = "icon")
	private String icon;

	@Column(name = "userId")
	private String userId;
}
