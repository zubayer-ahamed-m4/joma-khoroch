package com.coderslab.service;

import org.springframework.stereotype.Component;

import com.coderslab.entity.Wallet;

/**
 * @author Zubayer Ahamed
 *
 */
@Component
public interface WalletService extends GenericService<Wallet, Long> {

	/**
	 * FIND BY WALLET NAME
	 * 
	 * @param walletName
	 * @return {@link Wallet}
	 */
	public Wallet findByWalletName(String walletName);

	/**
	 * Get total balance
	 * 
	 * @return {@link Double}
	 */
	public Double getTotalBalance();

	/**
	 * Check wallet has available balance for transaction
	 * 
	 * @param transactionAmount
	 * @param walletName
	 * @return true or false
	 */
	public boolean isWalletHasAvailableBalance(Double transactionAmount, String walletName);
}
