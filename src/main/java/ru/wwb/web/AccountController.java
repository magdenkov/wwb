
package ru.wwb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.wwb.model.Account;
import ru.wwb.model.Client;
import ru.wwb.service.BankServiceFacade;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes(types = Account.class)
public class AccountController {

    private final BankServiceFacade bankServiceFacade;


    @Autowired
    public AccountController(BankServiceFacade bankServiceFacade) {
        this.bankServiceFacade = bankServiceFacade;
    }

    @RequestMapping(value = "/clients/{clientId}/accounts/new", method = RequestMethod.GET)
    public String initCreationAccountForm(@PathVariable("clientId") int clientId, Map<String, Object> model) {
        Client client = this.bankServiceFacade.findClientById(clientId);

        Account account = new Account();
        client.addAccount(account);
        model.put("account", account);
        return "accounts/createAccountForm";
    }



    @RequestMapping(value = "/clients/{clientId}/accounts/new", method = RequestMethod.POST)
    public String saveAccountForm(@Valid Account account, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "accounts/createAccountForm";
        } else {
            this.bankServiceFacade.saveAccount(account);
            status.setComplete();
            return "redirect:/clients/{clientId}";
        }
    }

    @RequestMapping(value={"/accounts.html"})
    public String showAccountList(Map<String, Object> model) {

        List<Account> accounts = new ArrayList<>();
        accounts.addAll(this.bankServiceFacade.findAllAccounts());

        model.put("accounts", accounts);
        return "accounts/accountList";
    }






}
