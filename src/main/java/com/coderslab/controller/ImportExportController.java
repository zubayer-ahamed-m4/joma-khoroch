package com.coderslab.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coderslab.entity.Wallet;
import com.coderslab.service.WalletService;
import com.opencsv.CSVReader;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zubayer Ahamed
 *
 */
@Slf4j
@Controller
@RequestMapping("/exportimport")
public class ImportExportController {

	@Autowired AsyncFileProcessor csvProcessor;
	@Autowired WalletService walletService;
	private List<String[]> theCSV = new ArrayList<>();
	
	@GetMapping
	public String loadImportPage() {
		log.info("Loading export import page.....");
		return "exportimport";
	}

	@GetMapping("/size")
	public @ResponseBody String displaySize() {
		return String.valueOf(theCSV.size());
	}

	@PostMapping("/upload")
	public @ResponseBody String uploadFile(@RequestParam MultipartFile file, Model model) throws IOException {
		log.info(file.getOriginalFilename());
		String fileName = saveFile(file);
		theCSV = new ArrayList<>();
		csvProcessor.uploadIntoDb(fileName, theCSV);
		log.info("total rows : {}", theCSV.size());
		return String.valueOf(theCSV.size());
	}

	private String saveFile(MultipartFile csvFile) throws IOException {
		String fileType = ".csv";
		String location = "F:\\";
		String fileNameWithDirectory = location + "csvfile" + fileType;
		OutputStream out = new FileOutputStream(new File(fileNameWithDirectory));
		IOUtils.copy(csvFile.getInputStream(), out);
		return fileNameWithDirectory;
	}

	@Service
	@EnableAsync
	static class AsyncFileProcessor{
		@Autowired WalletService walletService;
		
		@Async
		public void readCsv(String fileName, List<String[]> theCSV) throws IOException {
			log.info("Auc called .....");
			CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"), ',', '"');
			theCSV.addAll(csvReader.readAll());
		}

		@Async
		public void uploadIntoDb(String fileName, List<String[]> theCSV) throws IOException {
			CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"), ',', '"');
			theCSV.addAll(csvReader.readAll());
			theCSV.forEach(f -> {
				Wallet wallet = new Wallet();
				wallet.setWalletName(f[0]);
				walletService.save(wallet);
			});
			
		}
	}
}
