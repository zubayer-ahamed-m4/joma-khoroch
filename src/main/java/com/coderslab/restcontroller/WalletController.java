package com.coderslab.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderslab.entity.Wallet;
import com.coderslab.model.WalletList;
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

	@GetMapping("/find/{walletId}")
	public Wallet getWallet(@PathVariable Long walletId) {
		return walletService.findById(walletId);
	}

	@PostMapping(value = "/save", headers="Accept=application/json")
	public Wallet createWallet(@RequestBody Wallet wallet) {
		return walletService.save(wallet);
	}

	@PutMapping("/update")
	public Wallet updateWallet(@RequestBody Wallet wallet) {
		Wallet w = walletService.findById(wallet.getWalletId());
		//w.reGenerate(wallet);
		return walletService.save(w);
	}
}
