package com.coderslab.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderslab.model.MonthlyStatus;
import com.coderslab.service.MonthlyStatusService;
import com.coderslab.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zubayer Ahamed
 *
 */
@Slf4j
@Service
public class MonthlyStatusServiceImpl implements MonthlyStatusService {

	@Autowired private TransactionService transactionService;

	@Override
	public List<MonthlyStatus> getListOfMonthlyStatus(Long userId, Date year, boolean active) {
		log.info("Getting list of monthly status....");
		
		return null;
	}

}
