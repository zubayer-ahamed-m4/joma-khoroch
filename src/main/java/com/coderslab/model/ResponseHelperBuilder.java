package com.coderslab.model;

import java.util.Map;

import com.coderslab.model.enums.ResponseStatus;

/**
 * @author Zubayer Ahamed
 *
 */
public class ResponseHelperBuilder extends ResponseHelper {

	private static final long serialVersionUID = 4237828090897727806L;

	public ResponseHelperBuilder addMessage(String message) {
		this.setMessage(message);
		return this;
	}

	public ResponseHelperBuilder addResponseStatus(ResponseStatus status) {
		this.setResponseStatus(status);
		return this;
	}

	public ResponseHelperBuilder addContents(Map<String, Object> contents) {
		this.setContents(contents);
		return this;
	}

	public ResponseHelper build() {
		ResponseHelper helper = new ResponseHelper();
		helper.setMessage(this.getMessage());
		helper.setResponseStatus(this.getResponseStatus());
		helper.setContents(this.getContents());
		return helper;
	}
}
