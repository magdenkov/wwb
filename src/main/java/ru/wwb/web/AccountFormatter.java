package ru.wwb.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import ru.wwb.model.Account;
import ru.wwb.service.BankServiceFacade;

import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountFormatter implements Formatter<Account> {

    private final BankServiceFacade bankServiceFacade;


    @Autowired
    public AccountFormatter(BankServiceFacade bankServiceFacade) {
        this.bankServiceFacade = bankServiceFacade;
    }

    @Override
    public String print(Account account, Locale locale) {
            return "ID" +account.getId() + " " + account.getClient().getFirstName() + " " + account.getClient().getLastName() + " " + account.getMoneyAmount() +
                    Currency.getInstance(Locale.getDefault());
    }

    @Override
    public Account parse(String text, Locale locale) throws ParseException {
        return this.bankServiceFacade.findAccountById(getId(text));
    }

    protected Integer getId(String s) {
        Matcher matcher = Pattern.compile("\\d+").matcher(s);
        matcher.find();
        return Integer.valueOf(matcher.group());
    }

}
