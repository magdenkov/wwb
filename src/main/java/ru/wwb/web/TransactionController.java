
package ru.wwb.web;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.wwb.model.Account;

import ru.wwb.model.Transaction;
import ru.wwb.model.Transactions;
import ru.wwb.service.BankServiceFacade;

import java.util.Collection;
import java.util.Map;

@Controller
@SessionAttributes(types = Transaction.class)
public class TransactionController {

    private final BankServiceFacade bankServiceFacade;


    @Autowired
    public TransactionController(BankServiceFacade bankServiceFacade) {
        this.bankServiceFacade = bankServiceFacade;
    }

    @ModelAttribute("accountFrom")
    public Collection<Account> populateAccountFrom() {
        return this.bankServiceFacade.findAllAccounts();
    }

    @ModelAttribute("accountTo")
    public Collection<Account> populateAccountTo() {
        return this.bankServiceFacade.findAllAccounts();
    }


    @RequestMapping(value={"/makeTransfer.html"}, method = RequestMethod.GET)
    public String initTransactionCreationForm(Map<String, Object> model) {

        Transaction transaction = new Transaction();
        transaction.settDate(new DateTime());
        model.put("transaction", transaction);

        return "transactions/makeTransfer";
    }

    @RequestMapping(value = "makeTransfer.html", method = RequestMethod.POST)
    public String saveTransaction(@ModelAttribute("transaction") Transaction transaction, BindingResult result) {
        if (result.hasErrors()) {
            return "transactions/makeTransfer";
        }
        bankServiceFacade.saveTransaction(transaction);
        return "redirect:/transactions.html?success=true";
    }



    @RequestMapping(value={"/transactions.xml","/transactions.html"})
    public String showTransactionList(Map<String, Object> model) {

        Transactions transactions = new Transactions();
        transactions.getTransactionList().addAll(this.bankServiceFacade.findAllTransactions());

        model.put("transactions", transactions);
        return "transactions/transactionList";
    }

    @RequestMapping("/transactions.json")
    public @ResponseBody Transactions showResourcesVetList() {
        Transactions transactions = new Transactions();
        transactions.getTransactionList().addAll(this.bankServiceFacade.findAllTransactions());

        return transactions;
    }

}
