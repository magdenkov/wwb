package ru.wwb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Clients {

    private List<Client> clients;

    @XmlElement
    public List<Client> getClientList() {
        if (clients == null) {
            clients = new ArrayList<>();
        }
        return clients;
    }

}
