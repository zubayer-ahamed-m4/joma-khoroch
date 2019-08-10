package com.coderslab.service;

import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coderslab.entity.Transaction;
import com.coderslab.entity.Wallet;
import com.coderslab.model.enums.TransactionType;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zubayer Ahamed
 *
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ComponentScan(basePackages = { "com.coderslab.*" })
@Rollback(false)
public class TransactionServiceTest {

	private static final SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");

	@Autowired TransactionService transactionService;
	@Autowired UsersService usersService;
	@Autowired WalletService walletService;
	@Autowired IncomeSourceService incomeSourceService;
	@Autowired ExpenseTypeService expenseTypeService;

	@Test
	public void test1AddIncome() {
		Transaction tr = new Transaction();
		tr.setTransactionType(TransactionType.INCOME);
		tr.setTransactionAmount(100.0);
		tr.setTransactionCharge(0.00);
		tr.setToWallet(walletService.findByWalletName("Bank").getWalletId());
		tr.setIncomeSource(incomeSourceService.findByIncomeSourceName("Company").getIncomeSourceId());
		tr.setTransactionDate(new Date());
		tr.setTransactionTime(new Date());
		tr.setUserId(usersService.findByUsername("admin").getUserId());
		tr.setMonth(monthFormat.format(new Date()).toUpperCase());
		tr = transactionService.save(tr);
		assertNotNull(tr);

		Wallet wallet = walletService.findById(tr.getToWallet());
		wallet.setCurrentBalance((wallet.getCurrentBalance() + tr.getTransactionAmount()) - tr.getTransactionCharge());
		wallet = walletService.update(wallet);
		assertNotNull(wallet);
	}

	@Test
	public void test2AddExpense() {
		Transaction tr = new Transaction();
		tr.setTransactionType(TransactionType.EXPENSE);
		tr.setTransactionAmount(100.0);
		tr.setTransactionCharge(5.00);
		tr.setFromWallet(walletService.findByWalletName("Bank").getWalletId());
		tr.setIncomeSource(incomeSourceService.findByIncomeSourceName("Company").getIncomeSourceId());
		tr.setTransactionDate(new Date());
		tr.setTransactionTime(new Date());
		tr.setUserId(usersService.findByUsername("admin").getUserId());
		tr.setMonth(monthFormat.format(new Date()).toUpperCase());
		tr = transactionService.save(tr);
		assertNotNull(tr);

		Wallet wallet = walletService.findById(tr.getFromWallet());
		wallet.setCurrentBalance(wallet.getCurrentBalance() - tr.getTransactionAmount() - tr.getTransactionCharge());
		wallet = walletService.update(wallet);
		assertNotNull(wallet);
	}

	@Test
	public void test3AddTransfer() {
		Transaction tr = new Transaction();
		tr.setTransactionType(TransactionType.TRANSFER);
		tr.setTransactionAmount(205.0);
		tr.setTransactionCharge(0.00);
		tr.setFromWallet(walletService.findByWalletName("Bkash").getWalletId());
		tr.setToWallet(walletService.findByWalletName("Bank").getWalletId());
		tr.setTransactionDate(new Date());
		tr.setTransactionTime(new Date());
		tr.setUserId(usersService.findByUsername("admin").getUserId());
		tr.setMonth(monthFormat.format(new Date()).toUpperCase());
		tr = transactionService.save(tr);
		assertNotNull(tr);

		Wallet fromWallet = walletService.findById(tr.getFromWallet());
		fromWallet.setCurrentBalance(fromWallet.getCurrentBalance() - tr.getTransactionAmount() - tr.getTransactionCharge());
		fromWallet = walletService.update(fromWallet);
		assertNotNull(fromWallet);

		Wallet toWallet = walletService.findById(tr.getToWallet());
		toWallet.setCurrentBalance(toWallet.getCurrentBalance() + tr.getTransactionAmount());
		toWallet = walletService.update(toWallet);
		assertNotNull(toWallet);
	}

	@Test
	public void testMonthNamePrint() {
		log.info(monthFormat.format(new Date()).toUpperCase());
	}
}
