package com.nst.facture.billing.service;


import com.nst.facture.billing.models.Client;
import com.nst.facture.billing.payload.Dto.ClientDto;
import com.nst.facture.billing.repository.ClientRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
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
    public Client addClientFromDTO(ClientDto clientDto){
        Client toAdd = new Client();
        BeanUtils.copyProperties(clientDto, toAdd);
        return clientRepository.save(toAdd);
    }
    @Override
    public Client updateClient(Long id, Client client) {
        Client clientDB =getClientById(id);
        clientDB.setUsername(client.getUsername());
        clientDB.setEmail(client.getEmail());
        clientDB.setPassword(client.getPassword());
        clientDB.setAdresse(client.getAdresse());
        clientDB.setTelephone(client.getTelephone());
        Client updatedClient = getClientById(id);

        return updatedClient;
    }

    @Override
    public void deleteClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        client.ifPresent(c -> {
            clientRepository.delete(c);

        });
    }

    @Override
    public Client getClientById(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }
}

