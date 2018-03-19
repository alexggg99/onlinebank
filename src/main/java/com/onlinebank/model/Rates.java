package com.onlinebank.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class Rates {
    private String success;
    private String timestamp;
    private String base;
    private Date date;
    private Map<String, Double> rates;
}
