package com.onlinebank.service.impl;

import com.onlinebank.model.Rates;
import com.onlinebank.model.accounts.Account;
import com.onlinebank.service.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RatesServiceImpl implements RatesService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Rates getRates(Date date) {
        System.out.println();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = format.format(date);
        Rates rates = restTemplate.getForObject("http://data.fixer.io/api/{stingDate}?access_key=30a8523738e5bd4ee20283eb19430dc2", Rates.class, stringDate);
        if (!rates.getSuccess().equals("false")) {
            rates.getRates().keySet().retainAll(Stream.of("USD", "RUB").collect(Collectors.toSet()));
        }
        return rates;
    }

    @Override
    public BigDecimal exchangeAmmount(Account accountFrom, Account accountTo, BigDecimal amount) {
        if (accountFrom.getCurrency().equals(accountTo.getCurrency())) {
            return amount;
        } else {
            Rates rates = getRates(new Date());
            if (!accountTo.getCurrency().name().equals("EUR")) {
                return new BigDecimal(rates.getRates().get(accountTo.getCurrency().name()) * amount.doubleValue());
            } else {
                return new BigDecimal(amount.doubleValue() / rates.getRates().get(accountFrom.getCurrency().name()));
            }
        }
    }

}
