package com.nst.facture.billing.controllers.client;


import com.nst.facture.billing.models.Client;
import com.nst.facture.billing.payload.Dto.ClientDto;
import com.nst.facture.billing.repository.ClientRepository;
import com.nst.facture.billing.service.ClientService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
@Api("Client Controller API")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientService clientService;


    /**
     * This function displays the list of clients
     * @return
     */
    /*@GetMapping
    public List<ClientDto> getAllClients() {

        return clientService.getAllClients().stream().map(client -> modelMapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
    }*/

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
    public ResponseEntity<ClientDto> getClientById(@PathVariable(name = "id") Long id) {
        Client client = clientService.getClientById(id);

        // convert entity to DTO
        ClientDto clientResponse = modelMapper.map(client, ClientDto.class);

        return ResponseEntity.ok().body(clientResponse);
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
     * @param id
     * @param client
     * @return
     */
    @PutMapping("updateclient/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client client){

        Client c = clientService.updateClient(id, client);
        return new ResponseEntity<Client>(c, HttpStatus.OK);
    }

    /**
     * This function about delete a client
     * @param id
     * @return
     */
    @DeleteMapping("/deleteclient/{id}")
    public void deleteClient(@PathVariable("id") Long id) {

        clientService.deleteClient(id);
    }


}
