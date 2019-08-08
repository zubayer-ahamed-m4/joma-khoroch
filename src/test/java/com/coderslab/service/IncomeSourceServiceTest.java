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

import com.coderslab.entity.IncomeSource;

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
public class IncomeSourceServiceTest {

	@Autowired
	IncomeSourceService incomeSourceService;
	@Autowired
	UsersService usersService;

	@Test
	public void test1Save() {
		IncomeSource is = new IncomeSource();
		is.setIncomeSourceName("Company");
		is.setUserId(usersService.findByUsername("admin").getUserId());
		is = incomeSourceService.save(is);
		assertNotNull(is);
		assertEquals("Company", is.getIncomeSourceName());
	}
}
