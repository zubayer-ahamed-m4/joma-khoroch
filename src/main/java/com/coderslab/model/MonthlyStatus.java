package com.coderslab.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coderslab.entity.Transaction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Zubayer Ahamed
 *
 */
@Data
public class MonthlyStatus {

	@ApiModelProperty(notes = "Current month total income", readOnly = true)
	private Double monthlyIncome;
	@ApiModelProperty(notes = "Current month total expense", readOnly = true)
	private Double monthlyExpense;
	@ApiModelProperty(notes = "Current month total savings", readOnly = true)
	private Double monthlySaving;
	@ApiModelProperty(notes = "Current available balance", readOnly = true)
	private Double currentBalance;
	@ApiModelProperty(notes = "Month and Year", readOnly = true)
	private String month;
	@ApiModelProperty(notes = "Monthly all transactions grouping by date", readOnly = true)
	private Map<Date, List<Transaction>> monthlyTransactions = new HashMap<>();
}
