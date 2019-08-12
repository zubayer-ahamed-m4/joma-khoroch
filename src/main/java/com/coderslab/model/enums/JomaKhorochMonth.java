package com.coderslab.model.enums;

/**
 * @author Zubayer Ahamed
 *
 */
public enum JomaKhorochMonth {

	JAN(0, "Jan"),
	FEB(1, "Feb"),
	MAR(2, "Mar"),
	APR(3, "Apr"),
	MAT(4, "May"),
	JUN(5, "Jun"),
	JUL(6, "Jul"),
	AUG(7, "Aug"),
	SEP(8, "Sep"),
	OCT(9, "Oct"),
	NOV(10, "Nov"),
	DEC(11, "Dec");

	private int code;
	private String description;

	private JomaKhorochMonth(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}
}
