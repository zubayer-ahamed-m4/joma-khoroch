package com.coderslab.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.coderslab.model.enums.TransactionType;

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
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Transaction extends AbstractModel {

	@Id
	@SequenceGenerator(name = "TransactionSeqGen", schema = "public", sequenceName = "transaction_transactionid_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TransactionSeqGen")
	@Column(name = "transactionId", nullable = false, unique = true)
	private Long transactionId;

	@Column(name = "transactionType")
	@Enumerated(EnumType.STRING)
	@NotNull
	private TransactionType transactionType;

	@Column(name = "transactionAmount")
	@Min(value = 1, message = "Transaction amount must be greater then 0")
	private Double transactionAmount;

	@Column(name = "fromWallet")
	private Long fromWallet;

	@Column(name = "toWallet")
	private Long toWallet;

	@Column(name = "expenseType")
	private Long expenseType;

	@Column(name = "incomeSource")
	private Long incomeSource;

	@Column(name = "transactionDate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date transactionDate;

	@Column(name = "transactionTime")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "hh:mm:ss")
	@NotNull
	private Date transactionTime;

	@Column(name = "month")
	@NotNull
	private String month;

	@Column(name = "userId")
	@NotNull
	private Long userId;
}
