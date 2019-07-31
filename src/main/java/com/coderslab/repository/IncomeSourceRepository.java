/**
 * 
 */
package com.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderslab.entity.IncomeSource;

/**
 * @author zubay
 *
 */
@Repository
public interface IncomeSourceRepository extends JpaRepository<IncomeSource, Long>{

}
