package com.coderslab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(notes = "Wallet unique id", hidden = true)
	private Long walletId;

	@Column(name = "walletName", unique = true)
	@ApiModelProperty(notes = "Wallet name (Should be unique)")
	private String walletName;

	@Column(name = "initialBalance")
	@Min(value = 0, message = "Initial balance must be positive")
	@ApiModelProperty(notes = "Initial balance when wallet is being created")
	private Double initialBalance;

	@Column(name = "currentBalance")
	@Min(value = 0, message = "Current balance can't be go negative")
	@ApiModelProperty(notes = "Current balance of wallet")
	private Double currentBalance;

	@Column(name = "notes")
	@ApiModelProperty(notes = "Notest about this wallet")
	private String notes;

	@Column(name = "icon")
	@ApiModelProperty(notes = "Wallet icon", hidden = true)
	private String icon;

	@Column(name = "userId")
	@ApiModelProperty(notes = "User id of wallet owner", hidden = true)
	private Long userId;

	public Wallet getWallet(Wallet wallet) {
		this.setWalletId(wallet.getWalletId());
		this.setWalletName(wallet.getWalletName());
		this.setInitialBalance(wallet.getInitialBalance());
		this.setCurrentBalance(wallet.getCurrentBalance());
		this.setNotes(wallet.getNotes());
		this.setIcon(wallet.getIcon());
		this.setUserId(wallet.getUserId());
		return this;
	}
}
