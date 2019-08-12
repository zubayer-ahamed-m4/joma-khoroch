package com.coderslab.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.coderslab.model.enums.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Zubayer Ahamed
 *
 */
@Data
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ResponseHelper implements Serializable {

	private static final long serialVersionUID = -8523836358073658418L;

	private String message;
	private ResponseStatus responseStatus;
	private Map<String, Object> contents = new HashMap<>();

}


