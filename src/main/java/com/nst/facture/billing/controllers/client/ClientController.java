package com.nst.facture.billing.controllers.client;


import com.nst.facture.billing.models.Client;
import com.nst.facture.billing.payload.Dto.ClientDto;
import com.nst.facture.billing.repository.ClientRepository;
import com.nst.facture.billing.service.ClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@Api("Client Controller API")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;


    /**
     * This function displays the list of clients
     * @return
     */
    @GetMapping("/clients")
    public List<Client> getClients(){
        return clientService.getAllClients();

    }

    /**
     * This function for get a client
     * @param id
     * @return
     */
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(name = "id") Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return (ResponseEntity<Client>) ResponseEntity.ok(client.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * This function about create a client
     * @param clientDto
     * @return
     */
    @PostMapping("/addclient")
    public Client createClient(@RequestBody ClientDto clientDto) {

        return clientService.addClientFromDTO(clientDto);
    }

    /**
     * This function about update a client
     *
     * @param id
     * @param client
     * @return
     */
    @PutMapping("updateclient/{id}")
    public Client updateClient(@PathVariable("id") Long id, @RequestBody Client client){

        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient != null) {
            existingClient.setUsername(client.getUsername());
            existingClient.setEmail(client.getEmail());
            existingClient.setAdresse(client.getAdresse());
            existingClient.setTelephone(client.getTelephone());
            existingClient.setFactures(client.getFactures());
            return clientRepository.save(existingClient);
        }
        return null;
    }

    /**
     * This function about delete a client
     * @param id
     * @return
     */
    @DeleteMapping("/deleteclient/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void deleteClient(@PathVariable("id") Long id) {

        clientService.deleteClient(id);
    }


}
