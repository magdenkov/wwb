
package ru.wwb.web;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import ru.wwb.model.Client;
import ru.wwb.model.Clients;
import ru.wwb.service.BankServiceFacade;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@SessionAttributes(types = Client.class)
public class ClientController {

    private final BankServiceFacade bankServiceFacade;


    @Autowired
    public ClientController(BankServiceFacade bankServiceFacade) {
        this.bankServiceFacade = bankServiceFacade;
    }


    @RequestMapping(value = "/clients/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Client client = new Client();
        model.put("client", client);
        return "clients/createOrUpdateClientForm";
    }

    @RequestMapping(value = "/clients/new", method = RequestMethod.POST)
    public String saveClient(@ModelAttribute("client") Client client, BindingResult result) {
        if (result.hasErrors()) {
            return "clients/createOrUpdateClientForm";
        }
        bankServiceFacade.saveClient(client);
        return "redirect:/clients.html?success=true";
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.GET)
    public ModelAndView showClient(@PathVariable("clientId") int clientId) {
        ModelAndView mav = new ModelAndView("clients/clientDetails");
        Client client = this.bankServiceFacade.findClientById(clientId);
        mav.addObject(client);
        return mav;
    }

    @RequestMapping(value={"/clients.xml","/clients.html"})
    public String showClientList(Map<String, Object> model) {
        model.put("clients", getClients());
        return "clients/clientList";
    }

    @RequestMapping("/clients.json")
    public @ResponseBody Clients showResourcesClientList() {
        return getClients();
    }


    private Clients getClients() {
        Clients clients = new Clients();
        clients.getClientList().addAll(this.bankServiceFacade.findClients());
        return clients;
    }

}
