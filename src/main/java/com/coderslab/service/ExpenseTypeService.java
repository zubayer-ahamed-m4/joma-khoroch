package com.coderslab.service;

import org.springframework.stereotype.Component;

import com.coderslab.entity.ExpenseType;

@Component
public interface ExpenseTypeService extends GenericService<ExpenseType, Long> {

	/**
	 * Find by expense type name
	 * 
	 * @param expenseTypeName
	 * @return {@link ExpenseType}
	 */
	public ExpenseType findByExpenseTypeName(String expenseTypeName);
}
