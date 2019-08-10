package com.coderslab.model;

import java.util.List;

import com.coderslab.entity.Wallet;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Zubayer Ahamed
 *
 */
@Data
@AllArgsConstructor
public class WalletList {

	@ApiModelProperty("List of wallets")
	private List<Wallet> wallets;

}
