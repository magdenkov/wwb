
package ru.wwb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.wwb.model.Account;
import ru.wwb.model.Accounts;
import ru.wwb.model.Client;
import ru.wwb.service.BankServiceFacade;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes(types = Account.class)
public class AccountController {

    private final BankServiceFacade bankServiceFacade;


    @Autowired
    public AccountController(BankServiceFacade bankServiceFacade) {
        this.bankServiceFacade = bankServiceFacade;
    }

    // clients/1/accounts/new.html
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

    @RequestMapping(value={"/accounts.xml","/accounts.html"})
    public String showAccountList(Map<String, Object> model) {

        Accounts accounts = new Accounts();
        accounts.getAccountList().addAll(this.bankServiceFacade.findAllAccounts());

        model.put("accounts", accounts);
        return "accounts/accountList";
    }

    @RequestMapping("/accounts.json")
    public @ResponseBody Accounts showResourcesVetList() {
        Accounts accounts = new Accounts();
        accounts.getAccountList().addAll(this.bankServiceFacade.findAllAccounts());

        return accounts;
    }




}
