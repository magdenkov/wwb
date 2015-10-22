package ru.wwb.model;

import java.util.ArrayList;
import java.util.List;

public class Clients {

    private List<Client> clients;

    public List<Client> getClientList() {
        if (clients == null) {
            clients = new ArrayList<>();
        }
        return clients;
    }

}
