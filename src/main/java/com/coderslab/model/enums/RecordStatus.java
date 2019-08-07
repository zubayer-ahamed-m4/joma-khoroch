package com.coderslab.model.enums;

/**
 * @author Zubayer Ahamed
 *
 */
public enum RecordStatus {

	L("Live"), D("Deleted");

	private String description;

	private RecordStatus(String description) {
		this.description = description;
	}

	public String title() {
		return description;
	}

	/**
	 * Parse string value into {@link RecordStatus}.
	 * 
	 * @param value D or L
	 * @return {@link RecordStatus}
	 */
	public static RecordStatus parse(String value) {
		if (value == null) return null;
		value = value.trim().replaceAll(" ", "");
		return value.isEmpty() ? null : valueOf(value);
	}
}
