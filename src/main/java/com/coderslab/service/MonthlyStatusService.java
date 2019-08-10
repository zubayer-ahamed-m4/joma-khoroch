package com.coderslab.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coderslab.model.MonthlyStatus;
import com.coderslab.model.enums.RecordStatus;

/**
 * @author Zubayer Ahamed
 *
 */
@Component
public interface MonthlyStatusService {

	/**
	 * Get List of Monthly status list of current year
	 * 
	 * @param userId
	 * @param fromDate
	 * @param toDate
	 * @param recordStatus
	 * @return {@link List} of {@link MonthlyStatus}
	 */
	public List<MonthlyStatus> getListOfMonthlyStatusofCurrentYear(Long userId, Date fromDate, Date toDate, RecordStatus recordStatus);
}
