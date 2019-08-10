package com.coderslab.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author Zubayer Ahamed
 *
 */
@Data
public class MonthlyStatusList {

	private List<MonthlyStatus> monthlyStatus = new ArrayList<>();
}
