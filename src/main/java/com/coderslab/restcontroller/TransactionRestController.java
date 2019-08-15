package com.coderslab.restcontroller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderslab.entity.Transaction;
import com.coderslab.model.ResponseHelper;
import com.coderslab.model.ResponseHelperBuilder;
import com.coderslab.model.enums.ResponseStatus;
import com.coderslab.service.ExpenseTypeService;
import com.coderslab.service.IncomeSourceService;
import com.coderslab.service.TransactionService;
import com.coderslab.service.WalletService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zubayer Ahamed
 *
 */
@Slf4j
@RestController
@RequestMapping("/rest/transaction")
@Api(description = "Operations related with transaction", tags = "Transaction Operations")
public class TransactionRestController {

	@Autowired private TransactionService transactionService; 
	@Autowired private WalletService walletService;
	@Autowired private IncomeSourceService incomeSourceService;
	@Autowired private ExpenseTypeService expenseTypeService;
	@Autowired private Validator validator;

	@PostMapping(value = "save", produces = "application/json")
	public ResponseHelper saveTransaction(@RequestBody Transaction transaction, BindingResult bindingResult) {
		if(transactionValidate(transaction, validator, bindingResult).hasErrors()) {
			StringBuilder errors = new StringBuilder();
			bindingResult.getAllErrors().stream().forEach(e -> errors.append(e.getDefaultMessage()).append(" \n"));
			return new ResponseHelperBuilder()
						.addResponseStatus(ResponseStatus.ERROR)
						.addMessage(errors.toString())
						.build();
		}

		Transaction tr = transactionService.save(transaction);
		if(tr == null || tr.getTransactionId() == null) {
			return new ResponseHelperBuilder()
					.addResponseStatus(ResponseStatus.ERROR)
					.addMessage("Can't save wallet")
					.build();
		}

		return new ResponseHelperBuilder()
				.addResponseStatus(ResponseStatus.SUCCESS)
				.addMessage("Wallet saved successfully")
				.addContents(generateContentMap("transaction", tr))
				.build();
	}

	private Map<String, Object> generateContentMap(String key, Object value){
		Map<String, Object> map = new HashMap<>();
		map.put(key, value);
		return map;
	}


	public BindingResult transactionValidate(Transaction transaction, Validator validator, BindingResult bindingResult) {
		Set<ConstraintViolation<Transaction>> constraintViolations = validator.validate(transaction);
		for(ConstraintViolation<Transaction> item : constraintViolations) {
			bindingResult.rejectValue(item.getPropertyPath().toString(), null, item.getMessage());
		}

		if(transaction.getTransactionType() != null) {
			switch (transaction.getTransactionType().name().toUpperCase()) {
			case "INCOME":
				if(transaction.getToWallet() == null || walletService.findById(transaction.getToWallet()) == null) bindingResult.rejectValue("toWallet", null, "To wallet is required for transaction");
				if(transaction.getIncomeSource() == null || incomeSourceService.findById(transaction.getIncomeSource()) == null) bindingResult.rejectValue("incomeSource", null, "Income source is required for transaction");
				break;
			case "EXPENSE":
				if(transaction.getFromWallet() == null || walletService.findById(transaction.getFromWallet()) == null) bindingResult.rejectValue("fromWallet", null, "From wallet is required for transaction");
				if(transaction.getExpenseType() == null || expenseTypeService.findById(transaction.getExpenseType()) == null) bindingResult.rejectValue("expenseType", null, "Expense type is required for transaction");
				break;
			default:
				if(transaction.getFromWallet() == null || walletService.findById(transaction.getFromWallet()) == null) bindingResult.rejectValue("fromWallet", null, "From wallet is required for transaction");
				if(transaction.getToWallet() == null || walletService.findById(transaction.getToWallet()) == null) bindingResult.rejectValue("toWallet", null, "To wallet is required for transaction");
				break;
			}
		}

		return bindingResult;
	}
}
