package com.coderslab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderslab.entity.ExpenseType;
import com.coderslab.repository.ExpenseTypeRepository;
import com.coderslab.service.ExpenseTypeService;

/**
 * @author Zubayer Ahamed
 *
 */
@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService {

	@Autowired
	private ExpenseTypeRepository repo;

	@Override
	public ExpenseType save(ExpenseType obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExpenseType update(ExpenseType obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExpenseType> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExpenseType findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExpenseType archiveById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
