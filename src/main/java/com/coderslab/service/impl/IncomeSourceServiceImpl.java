package com.coderslab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderslab.entity.IncomeSource;
import com.coderslab.repository.IncomeSourceRepository;
import com.coderslab.service.IncomeSourceService;

@Service
public class IncomeSourceServiceImpl implements IncomeSourceService {

	@Autowired
	private IncomeSourceRepository repo;

	@Override
	public IncomeSource save(IncomeSource obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncomeSource update(IncomeSource obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IncomeSource> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncomeSource findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncomeSource archiveById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
