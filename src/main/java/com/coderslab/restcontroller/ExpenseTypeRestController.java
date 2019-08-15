package com.coderslab.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderslab.entity.ExpenseType;
import com.coderslab.entity.Wallet;
import com.coderslab.model.ResponseHelper;
import com.coderslab.model.ResponseHelperBuilder;
import com.coderslab.model.enums.ResponseStatus;
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

	@PostMapping(value = "/save", produces = "application/json")
	public ResponseHelper saveExpenseType(@RequestBody ExpenseType expenseType, BindingResult bindingResult) {
		if(expenseTypeValidate(expenseType, validator, bindingResult).hasErrors()) {
			StringBuilder errors = new StringBuilder();
			bindingResult.getAllErrors().stream().forEach(e -> errors.append(e.getDefaultMessage()));
			return new ResponseHelperBuilder()
						.addResponseStatus(ResponseStatus.ERROR)
						.addMessage(errors.toString())
						.build();
		}

		ExpenseType et = expenseTypeService.save(expenseType);
		if(et == null || et.getExpenseTypeId() == null) {
			return new ResponseHelperBuilder()
					.addResponseStatus(ResponseStatus.ERROR)
					.addMessage("Can't save expense type")
					.build();
		}

		return new ResponseHelperBuilder()
				.addResponseStatus(ResponseStatus.SUCCESS)
				.addMessage("Wallet saved successfully")
				.addContents(generateContentMap("expenseType", et))
				.build();
	}

	private Map<String, Object> generateContentMap(String key, Object value){
		Map<String, Object> map = new HashMap<>();
		map.put(key, value);
		return map;
	}

	public BindingResult expenseTypeValidate(ExpenseType expenseType, Validator validator, BindingResult bindingResult) {
		Set<ConstraintViolation<ExpenseType>> constraintViolations = validator.validate(expenseType);
		for(ConstraintViolation<ExpenseType> item : constraintViolations) {
			bindingResult.rejectValue(item.getPropertyPath().toString(), null, item.getMessage());
		}

		ExpenseType et = expenseTypeService.findByExpenseTypeName(expenseType.getExpenseTypeName());
		if(et == null) return bindingResult;

		bindingResult.rejectValue("expenseTypeName", null, "Expense type already exisist in the system");
		return bindingResult;
	}
}
