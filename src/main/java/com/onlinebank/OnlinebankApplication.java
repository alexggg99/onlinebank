package com.onlinebank;

import com.onlinebank.model.accounts.*;
import com.onlinebank.repo.PrimaryTransactionRepo;
import com.onlinebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class OnlinebankApplication implements ApplicationRunner {

	@Autowired
	private AccountService accountService;

	@Autowired
	private PrimaryTransactionRepo primaryTransactionRepo;

	public static void main(String[] args) {
		SpringApplication.run(OnlinebankApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

		PrimaryAccount primaryAccount = accountService.createPrimaryAccount(Currency.RUR, "user");
		SavingAccount savingAccount = accountService.createSavingAccount("user");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		primaryTransactionRepo.save(new PrimaryTransaction(new Timestamp(simpleDateFormat.parse("24-09-2017 22:01:02").getTime()), "ok", 230, primaryAccount));
		primaryTransactionRepo.save(new PrimaryTransaction(new Timestamp(simpleDateFormat.parse("01-11-2017 14:24:11").getTime()), "ok", 230, primaryAccount));
		primaryTransactionRepo.save(new PrimaryTransaction(new Timestamp(simpleDateFormat.parse("24-11-2017 21:30:02").getTime()), "ok", 630, primaryAccount));
		primaryTransactionRepo.save(new PrimaryTransaction(new Timestamp(simpleDateFormat.parse("02-12-2017 02:01:02").getTime()), "ok", 900, primaryAccount));
		primaryTransactionRepo.save(new PrimaryTransaction(new Timestamp(simpleDateFormat.parse("11-12-2017 18:45:45").getTime()), "ok", 200, primaryAccount));
		primaryTransactionRepo.save(new PrimaryTransaction(new Timestamp(simpleDateFormat.parse("11-14-2017 19:33:45").getTime()), "ok", 80, primaryAccount));
		primaryTransactionRepo.save(new PrimaryTransaction(new Timestamp(simpleDateFormat.parse("11-18-2017 18:19:45").getTime()), "ok", 230, primaryAccount));
		primaryTransactionRepo.save(new PrimaryTransaction(new Timestamp(simpleDateFormat.parse("03-02-2018 20:03:44").getTime()), "ok", 230, primaryAccount));
		primaryTransactionRepo.save(new PrimaryTransaction(new Timestamp(simpleDateFormat.parse("09-02-2018 15:11:23").getTime()), "ok", 20, primaryAccount));
		primaryTransactionRepo.save(new PrimaryTransaction(new Timestamp(simpleDateFormat.parse("23-02-2018 23:01:16").getTime()), "ok", 72.3, primaryAccount));
		primaryTransactionRepo.save(new PrimaryTransaction(new Timestamp(simpleDateFormat.parse("04-02-2018 08:17:12").getTime()), "ok", 90, primaryAccount));
		primaryTransactionRepo.save(new PrimaryTransaction(new Timestamp(simpleDateFormat.parse("08-02-2018 10:45:14").getTime()), "ok", 1370, primaryAccount));

		primaryAccount.setAccountBalance(new BigDecimal("2360.8"));
		accountService.saveAccount(primaryAccount);

	}


}
