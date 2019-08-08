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

import com.coderslab.entity.ExpenseType;

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
public class ExpenseTypeServiceTest {

	@Autowired
	UsersService usersService;
	@Autowired
	ExpenseTypeService expenseTypeService;

	@Test
	public void test1Save() {
		ExpenseType e = new ExpenseType();
		e.setExpenseTypeName("Fast food");
		e.setUserId(usersService.findByUsername("admin").getUserId());
		e = expenseTypeService.save(e);
		assertNotNull(e);
		assertEquals("Fast food", e.getExpenseTypeName());
	}
}
