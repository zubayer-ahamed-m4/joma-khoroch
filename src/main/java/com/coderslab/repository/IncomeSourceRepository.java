/**
 * 
 */
package com.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderslab.entity.IncomeSource;
import com.coderslab.model.enums.RecordStatus;

/**
 * @author zubay
 *
 */
@Repository
public interface IncomeSourceRepository extends JpaRepository<IncomeSource, Long>{

	public IncomeSource findIncomeSourceByIncomeSourceNameAndStatus(String incomeSourceName, RecordStatus status);
}
