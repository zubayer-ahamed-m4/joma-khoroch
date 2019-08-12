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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderslab.entity.Wallet;
import com.coderslab.model.ResponseHelper;
import com.coderslab.model.ResponseHelperBuilder;
import com.coderslab.model.WalletList;
import com.coderslab.model.enums.ResponseStatus;
import com.coderslab.service.WalletService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/wallet")
@Api(description = "Operations related with wallet", tags = "Wallet Operations")
public class WalletController {

	@Autowired private WalletService walletService;
	@Autowired private Validator validator;

	@ApiOperation(value = "View a list of available wallets", response = WalletList.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/list", produces = "application/json")
	public WalletList getAllWallet(){
		log.info("Getting all wallet list ........");
		return new WalletList(walletService.findAll());
	}

	@ApiOperation(value = "Find wallet by wallet id", response = Wallet.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/findbyid/{walletId}", produces = "application/json")
	public Wallet findWalletById(@PathVariable Long walletId) {
		return walletService.findById(walletId);
	}

	@ApiOperation(value = "Find wallet by wallet name", response = Wallet.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/findbywalletname/{walletName}", produces = "application/json")
	public Wallet findWalletByWalletName(@PathVariable String walletName) {
		return walletService.findByWalletName(walletName);
	}

	@ApiOperation(value = "Save new wallet", response = Wallet.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(value = "/save", headers="Accept=application/json")
	public ResponseHelper createWallet(@RequestBody Wallet wallet, BindingResult bindingResult) {

		if(walletValidate(wallet, validator, bindingResult).hasErrors()) {
			StringBuilder errors = new StringBuilder();
			bindingResult.getAllErrors().stream().forEach(e -> errors.append(e.getDefaultMessage()));
			return new ResponseHelperBuilder()
						.addResponseStatus(ResponseStatus.ERROR)
						.addMessage(errors.toString())
						.build();
		}

		Wallet w = walletService.save(wallet);
		if(w == null || w.getWalletId() == null) {
			return new ResponseHelperBuilder()
					.addResponseStatus(ResponseStatus.ERROR)
					.addMessage("Can't save wallet")
					.build();
		}

		return new ResponseHelperBuilder()
				.addResponseStatus(ResponseStatus.SUCCESS)
				.addMessage("Wallet saved successfully")
				.addContents(generateContentMap("wallet", w))
				.build();
	}

	private Map<String, Object> generateContentMap(String key, Object value){
		Map<String, Object> map = new HashMap<>();
		map.put(key, value);
		return map;
	}

	@ApiOperation(value = "Update existing wallet", response = WalletList.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		
	})
	@PutMapping("/update")
	public Wallet updateWallet(@RequestBody Wallet wallet) {
		Wallet w = walletService.findById(wallet.getWalletId());
		w = w.getWallet(wallet);
		return walletService.update(w);
	}

	public BindingResult walletValidate(Wallet wallet, Validator validator, BindingResult bindingResult) {
		Set<ConstraintViolation<Wallet>> constraintViolations = validator.validate(wallet);
		for(ConstraintViolation<Wallet> item : constraintViolations) {
			bindingResult.rejectValue(item.getPropertyPath().toString(), null, item.getMessage());
		}

		Wallet w = walletService.findByWalletName(wallet.getWalletName());
		if(w == null) return bindingResult;

		bindingResult.rejectValue("walletName", null, "Wallet already exisist in the system");
		return bindingResult;
	}
}
