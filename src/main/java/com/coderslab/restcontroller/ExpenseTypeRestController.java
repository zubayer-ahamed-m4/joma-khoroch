package com.coderslab.restcontroller;

import java.util.List;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderslab.entity.ExpenseType;
import com.coderslab.service.ExpenseTypeService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zubayer Ahamed
 *
 */
@Slf4j
@RestController
@RequestMapping("/rest/expensetype")
@Api(description = "Operations related with expense type", tags = "Expense type Operations")
public class ExpenseTypeRestController {

	@Autowired private ExpenseTypeService expenseTypeService;
	@Autowired private Validator validator;

	@GetMapping(value = "/list", produces = "application/jsaon")
	public List<ExpenseType> getAllExpenseTypeList(){
		return expenseTypeService.findAll();
	}
}
