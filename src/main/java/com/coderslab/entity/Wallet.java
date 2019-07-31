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
@Table(name = "wallet")
public class Wallet {

	@Id
	@SequenceGenerator(name = "WalletSeqGen", schema = "public", sequenceName = "wallet_walletid_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WalletSeqGen")
	@Column(name = "walletId", nullable = false, unique = true)
	private Long walletId;

	@Column(name = "walletName")
	private String walletName;

	@Column(name = "primaryBalance")
	private Double primaryBalance;

	@Column(name = "currentBalance")
	private Double currentBalance;

	@Column(name = "note")
	private String note;
}
