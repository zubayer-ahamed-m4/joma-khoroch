package com.coderslab.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderslab.entity.Transaction;
import com.coderslab.model.MonthlyStatus;
import com.coderslab.model.enums.RecordStatus;
import com.coderslab.model.enums.TransactionType;
import com.coderslab.service.MonthlyStatusService;
import com.coderslab.service.WalletService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zubayer Ahamed
 *
 */
@Slf4j
@Service
public class MonthlyStatusServiceImpl implements MonthlyStatusService {

	private static final StringBuilder sql = new StringBuilder("SELECT t FROM Transaction t WHERE t.userId=:userId AND t.status=:status AND t.transactionDate BETWEEN :fromDate AND :toDate");

	@Autowired private WalletService walletService;
	@Autowired @PersistenceContext private EntityManager em;

	@Override
	public List<MonthlyStatus> getListOfMonthlyStatusofCurrentYear(Long userId, Date fromDate, Date toDate, RecordStatus recordStatus) {
		log.info("Getting list of monthly status....");
		List<Transaction> transactions = em.createQuery(sql.toString(), Transaction.class)
											.setParameter("userId", new Long(5))
											.setParameter("fromDate", fromDate)
											.setParameter("toDate", toDate)
											.setParameter("status", recordStatus)
											.getResultList();

		if(transactions == null || transactions.isEmpty()) return Collections.emptyList();

		Map<String, List<Transaction>> monthlyMap = new HashMap<>();
		transactions.stream().forEach(tr -> {
			if(monthlyMap.get(tr.getMonth()) != null) {
				monthlyMap.get(tr.getMonth()).add(tr);
			} else {
				List<Transaction> list = new ArrayList<>();
				list.add(tr);
				monthlyMap.put(tr.getMonth(), list);
			}
		});

		Double totalCurrentBalance = walletService.getTotalBalance();
		List<MonthlyStatus> list = new ArrayList<>();
		monthlyMap.entrySet().stream().forEach(m -> {
			MonthlyStatus ms = new MonthlyStatus();

			Double monthlyIncome = m.getValue()
									.stream()
									.filter(t -> TransactionType.INCOME.equals(t.getTransactionType()))
									.mapToDouble(Transaction::getTransactionAmount)
									.sum();

			Double monthlyExpense = m.getValue()
									.stream()
									.filter(t -> TransactionType.EXPENSE.equals(t.getTransactionType()))
									.mapToDouble(Transaction::getTransactionAmount)
									.sum();

			Double monthlySavings = monthlyIncome - monthlyExpense;

			ms.setMonthlyIncome(monthlyIncome);
			ms.setMonthlyExpense(monthlyExpense);
			ms.setMonthlySaving(monthlySavings);
			ms.setCurrentBalance(totalCurrentBalance);
			ms.setMonth(m.getKey());

			list.add(ms);
		});

		return list;
	}

	@SuppressWarnings("unused")
	@Override
	public MonthlyStatus getMonthlyStatus(Long userId, Date fromDate, Date toDate, RecordStatus recordStatus) {
		log.info("Getting monthly status with details ....");

		List<Transaction> transactions = em.createQuery(sql.toString(), Transaction.class)
											.setParameter("userId", new Long(5))
											.setParameter("fromDate", fromDate)
											.setParameter("toDate", toDate)
											.setParameter("status", recordStatus)
											.getResultList();

		Double monthlyIncome = transactions.stream()
											.filter(t -> TransactionType.INCOME.equals(t.getTransactionType()))
											.mapToDouble(Transaction::getTransactionAmount)
											.sum();

		Double monthlyExpense = transactions.stream()
											.filter(t -> TransactionType.EXPENSE.equals(t.getTransactionType()))
											.mapToDouble(Transaction::getTransactionAmount)
											.sum();

		Map<Date, List<Transaction>> transactionDetails = new HashMap<>();
		transactions.stream().forEach(t -> {
			if(transactionDetails.get(t.getTransactionDate()) != null) {
				transactionDetails.get(t.getTransactionDate()).add(t);
			} else {
				List<Transaction> list = new ArrayList<>();
				list.add(t);
				transactionDetails.put(t.getTransactionDate(), list);
			}
		});

		MonthlyStatus ms = new MonthlyStatus();
		ms.setMonthlyIncome(monthlyIncome);
		ms.setMonthlyExpense(monthlyExpense);
		ms.setMonthlyTransactions(transactionDetails);
		return ms;
	}

}
