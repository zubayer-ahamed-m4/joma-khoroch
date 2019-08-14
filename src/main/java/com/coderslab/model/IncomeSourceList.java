package com.coderslab.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coderslab.entity.IncomeSource;

import lombok.Data;

/**
 * @author Zubayer Ahamed
 *
 */
@Data
@Component
public class IncomeSourceList {

	private List<IncomeSource> incomeSources = new ArrayList<>();

	public IncomeSourceList(List<IncomeSource> incomeSources) {
		this.incomeSources = incomeSources;
	}
}
