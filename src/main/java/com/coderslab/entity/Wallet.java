package com.coderslab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

	@Column(name = "walletName")
	@ApiModelProperty(notes = "Wallet name")
	private String walletName;

	@Column(name = "initialBalance")
	@ApiModelProperty(notes = "Initial balance when wallet is being created")
	private Double initialBalance;

	@Column(name = "currentBalance")
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
}
