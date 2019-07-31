package com.coderslab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderslab.entity.Wallet;
import com.coderslab.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	@Autowired private WalletService walletService;

	@GetMapping("/all")
	public List<Wallet> getAllWallet(){
		return walletService.findAll();
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
