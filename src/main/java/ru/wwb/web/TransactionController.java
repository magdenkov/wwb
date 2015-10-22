
package ru.wwb.web;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.wwb.model.Account;
import ru.wwb.model.Transaction;
import ru.wwb.service.BankServiceFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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



    @RequestMapping(value={"/transactions.html"})
    public String showTransactionList(Map<String, Object> model) {

        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(this.bankServiceFacade.findAllTransactions());

        model.put("transactions", transactions);
        return "transactions/transactionList";
    }



}
