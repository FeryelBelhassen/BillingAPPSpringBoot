package com.nst.facture.billing.service;


import com.nst.facture.billing.models.Client;
import com.nst.facture.billing.repository.ClientRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        super();
        this.clientRepository = clientRepository;
    }


    @Override
    public List <Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(com.nst.facture.billing.models.Client client) {
        return clientRepository.save(client);
    }

    @Override
    public com.nst.facture.billing.models.Client updateClient(long id, Client clientRequest) {
        com.nst.facture.billing.models.Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client"));

        client.setUsername(clientRequest.getUsername());
        client.setEmail(clientRequest.getEmail());
        client.setPassword(clientRequest.getPassword());
        client.setAdresse(clientRequest.getAdresse());
        client.setTelephone(clientRequest.getTelephone());
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client"));

        clientRepository.delete(client);

    }

    @Override
    public Client getClientById(long id) {
        Optional<Client> result = clientRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("Client ");
        }
    }
}

