package com.coderslab.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coderslab.model.MonthlyStatus;

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
	 * @param year
	 * @param active
	 * @return {@link List} of {@link MonthlyStatus}
	 */
	public List<MonthlyStatus> getListOfMonthlyStatus(Long userId, Date year, boolean active);
}
