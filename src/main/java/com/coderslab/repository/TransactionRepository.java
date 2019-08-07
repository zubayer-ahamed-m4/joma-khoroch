package com.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderslab.entity.Transaction;

/**
 * @author Zubayer Ahamed
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
