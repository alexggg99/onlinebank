package com.onlinebank.model;

public enum Currency {

    EUR("eur", "Euro"), USD("usd", "US Dollar"), RUB("rub", "Russian rubles");
    private String code;
    private String text;

    Currency(String code, String text) {
        this.code = code;
        this.text = text;
    }

    // Static method return Gender by code.
    public static Currency getGenderByCode(String code) {
        for (Currency currency : Currency.values()) {
            if (currency.code.equals(code)) {
                return currency;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
