package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Client;
import com.nst.facture.billing.payload.Dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    Client addClientFromDTO(ClientDto clientDto);

    Client updateClient(Long id, Client client);

    void deleteClient(Long id);

    Client getClientById(long id);

}
