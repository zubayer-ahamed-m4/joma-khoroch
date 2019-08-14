package com.coderslab.restcontroller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderslab.entity.IncomeSource;
import com.coderslab.model.IncomeSourceList;
import com.coderslab.model.ResponseHelper;
import com.coderslab.model.ResponseHelperBuilder;
import com.coderslab.model.enums.ResponseStatus;
import com.coderslab.service.IncomeSourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zubayer Ahamed
 *
 */
@Slf4j
@RestController
@RequestMapping("/rest/incomesource")
@Api(description = "Operations related with income source", tags = "Income source Operations")
public class IncomeSourceController {

	@Autowired private IncomeSourceService incomeSourceSrvice;
	@Autowired private Validator validator;

	@GetMapping(value = "/list", produces = "application/json")
	public IncomeSourceList getAllIncomeSourceList(){
		return new IncomeSourceList(incomeSourceSrvice.findAll());
	}

	@GetMapping(value = "/find/{incomeSourceName}", produces = "application/json")
	public IncomeSource getIncomeSource(@PathVariable String incomeSourceName) {
		return incomeSourceSrvice.findByIncomeSourceName(incomeSourceName);
	}

	@ApiOperation(value = "Save new income source", response = IncomeSource.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(value = "/save", produces = "application/json")
	public ResponseHelper saveIncomeSource(@RequestBody IncomeSource incomeSource, BindingResult bindingResult) {
		if(incomeSourceValidate(incomeSource, validator, bindingResult).hasErrors()) {
			StringBuilder errors = new StringBuilder();
			bindingResult.getAllErrors().stream().forEach(e -> errors.append(e.getDefaultMessage()));
			return new ResponseHelperBuilder()
						.addResponseStatus(ResponseStatus.ERROR)
						.addMessage(errors.toString())
						.build();
		}

		IncomeSource is = incomeSourceSrvice.save(incomeSource);
		if(is == null || is.getIncomeSourceId() == null) {
			return new ResponseHelperBuilder()
					.addResponseStatus(ResponseStatus.ERROR)
					.addMessage("Can't save income source")
					.build();
		}

		return new ResponseHelperBuilder()
				.addResponseStatus(ResponseStatus.SUCCESS)
				.addMessage("Income source saved successfully")
				.addContents(generateContentMap("incomeSource", is))
				.build();
	}

	private Map<String, Object> generateContentMap(String key, Object value){
		Map<String, Object> map = new HashMap<>();
		map.put(key, value);
		return map;
	}

	public BindingResult incomeSourceValidate(IncomeSource incomeSource, Validator validator, BindingResult bindingResult) {
		Set<ConstraintViolation<IncomeSource>> constraintViolations = validator.validate(incomeSource);
		for(ConstraintViolation<IncomeSource> item : constraintViolations) {
			bindingResult.rejectValue(item.getPropertyPath().toString(), null, item.getMessage());
		}

		IncomeSource is = incomeSourceSrvice.findByIncomeSourceName(incomeSource.getIncomeSourceName());
		if(is == null) return bindingResult;

		bindingResult.rejectValue("incomeSourceName", null, "Income source already exisist in the system");
		return bindingResult;
	}
}
