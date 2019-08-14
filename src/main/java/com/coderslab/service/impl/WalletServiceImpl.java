package com.coderslab.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderslab.entity.Wallet;
import com.coderslab.model.enums.RecordStatus;
import com.coderslab.repository.WalletRepository;
import com.coderslab.service.WalletService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WalletServiceImpl implements WalletService {

	@Autowired private WalletRepository repo;
	@Autowired @PersistenceContext private EntityManager jpa;

	@Override
	@Transactional
	public Wallet save(Wallet obj) {
		log.info("Saving wallet : {}", obj);
		return repo.save(obj);
	}

	@Override
	public Wallet update(Wallet obj) {
		return save(obj);
	}

	@Override
	public List<Wallet> findAll() {
		return repo.findAll();
	}

	@Override
	public Wallet findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Wallet archiveById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Wallet findByWalletName(String walletName) {
		return jpa.createQuery("SELECT w FROM Wallet w WHERE UPPER(w.walletName)=:walnm AND w.status=:stat", Wallet.class)
					.setParameter("walnm", walletName.toUpperCase())
					.setParameter("stat", RecordStatus.L)
					.getResultList()
					.stream()
					.findFirst()
					.orElse(null);
	}

	@Override
	public Double getTotalBalance() {
		return findAll().stream().mapToDouble(Wallet::getCurrentBalance).sum();
	}

	@Override
	public boolean isWalletHasAvailableBalance(Double transactionAmount, String walletName) {
		Wallet wallet = findByWalletName(walletName);
		if(wallet == null) return false;
		if(wallet.getCurrentBalance() < transactionAmount) return false;
		return true;
	}

}
