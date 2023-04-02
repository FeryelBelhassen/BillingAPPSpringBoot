package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    Client createClient(Client client);

    Client updateClient(long id, Client client);

    void deleteClient(long id);

    Client getClientById(long id);


}
