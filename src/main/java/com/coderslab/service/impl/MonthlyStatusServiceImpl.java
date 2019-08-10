package com.coderslab.service.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired private WalletService walletService;
	@Autowired @PersistenceContext private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyStatus> getListOfMonthlyStatusofCurrentYear(Long userId, Date fromDate, Date toDate, RecordStatus recordStatus) {
		log.info("Getting list of monthly status....");
		StringBuilder sql = new StringBuilder()
			.append("SELECT ")
			.append("	transactionid, ")
			.append("	expensetype, ")
			.append("	fromwallet, ")
			.append("	incomesource, ")
			.append("	towallet, ")
			.append("	transactionamount, ")
			.append("	transactioncharge, ")
			.append("	transactiontype, ")
			.append("	userid, ")
			.append("	month ")
			.append("FROM ")
			.append("	transactions ")
			.append("WHERE ")
			.append("	status='"+ recordStatus +"' ")
			.append("AND transactiondate between '"+ sdf.format(fromDate) +"' AND '"+ sdf.format(toDate) +"' ");

		if(log.isDebugEnabled()) log.debug(sql.toString());

		Query q = em.createNativeQuery(sql.toString());

		List<Object[]> objects = q.getResultList(); 
		if(objects == null || objects.isEmpty()) return Collections.emptyList();

		List<Transaction> transactions = new ArrayList<>();
		objects.stream().forEach(o -> transactions.add(buildAndGetMonthlyStatusFromObject(o)));

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

	private Transaction buildAndGetMonthlyStatusFromObject(Object[] obj) {
		if(obj == null) return null;

		Transaction tr = new Transaction();
		tr.setTransactionId(((BigInteger) obj[0]).longValue());
		tr.setExpenseType(obj[1] != null ? ((BigInteger) obj[1]).longValue() : null);
		tr.setFromWallet(obj[2] != null ? ((BigInteger) obj[2]).longValue() : null);
		tr.setIncomeSource(obj[3] != null ? ((BigInteger) obj[3]).longValue() : null);
		tr.setToWallet(obj[4] != null ? ((BigInteger) obj[4]).longValue() : null);
		tr.setTransactionAmount(obj[5] != null ? (Double) obj[5] : null);
		tr.setTransactionCharge(obj[6] != null ? (Double) obj[6] : null);
		tr.setTransactionType(TransactionType.valueOf((String) obj[7]));
		tr.setUserId(obj[8] != null ? ((BigInteger) obj[8]).longValue() : null);
		tr.setMonth((String) obj[9]);
		return tr; 
	}

}
