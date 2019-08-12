package com.coderslab.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coderslab.entity.Wallet;

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
public class MonthlyStatusServiceTest {

	@Autowired private TransactionService transactionService;
	@Autowired @PersistenceContext private EntityManager em;

	@Test
	public void test1ReadWallet() {
		Wallet wallet = em.find(Wallet.class, new Long(1));
		System.out.println(wallet.getWalletName());
	}

	@Test
	public void dateTest() {
		Calendar calDate = Calendar.getInstance();

		calDate.set(Calendar.DAY_OF_YEAR, 1);
		Date yearStartDate = calDate.getTime();

		calDate.set(Calendar.DAY_OF_YEAR, calDate.getActualMaximum(Calendar.DAY_OF_YEAR));
		Date yearEndDate = calDate.getTime();

		System.out.println(yearStartDate + " ---- " + yearEndDate);
	}

	@Test
	public void dateTest2() {
		LocalDate now = LocalDate.now();
		LocalDate firstDay = now.with(TemporalAdjusters.firstDayOfYear());
		LocalDate lastDay = now.with(TemporalAdjusters.lastDayOfYear());
	}

	@Test
	public void testCurrentMonthStartAndEndDate() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = 1;
		c.set(year, 4, day);
		int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("First Day of month: " + c.getTime());
		c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
		System.out.println("Last Day of month: " + c.getTime());
	}

	
}
