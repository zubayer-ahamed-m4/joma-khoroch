package com.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderslab.entity.Wallet;
import com.coderslab.model.enums.RecordStatus;

/**
 * @author Zubayer Ahamed
 *
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{

	public Wallet findWalletByWalletNameAndStatus(String walletName, RecordStatus status);
}
