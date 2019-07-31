package com.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderslab.entity.Wallet;

/**
 * @author Zubayer Ahamed
 *
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{

}
