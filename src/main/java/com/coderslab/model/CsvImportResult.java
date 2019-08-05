package com.coderslab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zubayer Ahamed
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvImportResult {

	private String fileLocation;
	private String fileType;

}
