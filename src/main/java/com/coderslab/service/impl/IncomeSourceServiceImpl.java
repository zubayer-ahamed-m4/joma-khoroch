package com.coderslab.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderslab.entity.IncomeSource;
import com.coderslab.model.enums.RecordStatus;
import com.coderslab.repository.IncomeSourceRepository;
import com.coderslab.service.IncomeSourceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IncomeSourceServiceImpl implements IncomeSourceService {

	@Autowired private IncomeSourceRepository repo;
	@Autowired @PersistenceContext private EntityManager jpa;

	@Transactional
	@Override
	public IncomeSource save(IncomeSource obj) {
		return repo.save(obj);
	}

	@Override
	public IncomeSource update(IncomeSource obj) {
		return save(obj);
	}

	@Override
	public List<IncomeSource> findAll() {
		return repo.findAll();
	}

	@Override
	public IncomeSource findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public IncomeSource archiveById(Long id) {
		IncomeSource i = findById(id);
		i.setStatus(RecordStatus.D);
		return update(i);
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

	@Override
	public IncomeSource findByIncomeSourceName(String incomeSourceName) {
		return jpa.createQuery("SELECT i from IncomeSource i WHERE UPPER(i.incomeSourceName)=:isnm AND i.status=:stat", IncomeSource.class)
					.setParameter("isnm", incomeSourceName.toUpperCase())
					.setParameter("stat", RecordStatus.L)
					.getResultList()
					.stream()
					.findFirst()
					.orElse(null);
	}

}
