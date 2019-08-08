package com.coderslab.service;

import org.springframework.stereotype.Component;

import com.coderslab.entity.IncomeSource;

@Component
public interface IncomeSourceService extends GenericService<IncomeSource, Long> {

	/**
	 * FIND BY INCOME SOURCE NAME
	 * 
	 * @param incomeSourceName
	 * @return {@link IncomeSource}
	 */
	public IncomeSource findByIncomeSourceName(String incomeSourceName);
}
