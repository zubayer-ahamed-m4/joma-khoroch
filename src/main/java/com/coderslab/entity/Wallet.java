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
@Table(name = "wallet")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Wallet extends AbstractModel {

	@Id
	@SequenceGenerator(name = "WalletSeqGen", schema = "public", sequenceName = "wallet_walletid_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WalletSeqGen")
	@Column(name = "walletId", nullable = false, unique = true)
	private Long walletId;

	@Column(name = "walletName")
	private String walletName;

	@Column(name = "initialBalance")
	private Double initialBalance;

	@Column(name = "currentBalance")
	private Double currentBalance;

	@Column(name = "notes")
	private String notes;

	@Column(name = "icon")
	private String icon;

	@Column(name = "userId")
	private String userId;
}
