package com.coderslab.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class WalletServiceTest {

	@Autowired
	UsersService usersService;
	@Autowired
	WalletService walletService;

	@Test
	public void test1Save() {
		Wallet wallet = new Wallet();
		wallet.setWalletName("Bkash");
		wallet.setInitialBalance(5000.0);
		wallet.setCurrentBalance(5000.0);
		wallet.setUserId(usersService.findByUsername("admin").getUserId());

		wallet = walletService.save(wallet);
		assertNotNull(wallet);
	}

	@Test
	public void test2FindByWalletName() {
		Wallet wallet = walletService.findByWalletName("Bank");
		log.info("Wallet : {}", wallet);
		assertNotNull(wallet);
		assertEquals("Bank", wallet.getWalletName());
	}
}
