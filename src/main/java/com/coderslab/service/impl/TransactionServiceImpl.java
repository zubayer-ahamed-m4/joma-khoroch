package com.coderslab.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderslab.entity.Transaction;
import com.coderslab.model.enums.RecordStatus;
import com.coderslab.repository.TransactionRepository;
import com.coderslab.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zubayer Ahamed
 *
 */
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository repo;

	@Transactional
	@Override
	public Transaction save(Transaction obj) {
		return repo.save(obj);
	}

	@Override
	public Transaction update(Transaction obj) {
		return save(obj);
	}

	@Override
	public List<Transaction> findAll() {
		return repo.findAll();
	}

	@Override
	public Transaction findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Transaction archiveById(Long id) {
		Transaction t = findById(id);
		t.setStatus(RecordStatus.D);
		return update(t);
	}

	@Override
	public Boolean deleteById(Long id) {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

}
