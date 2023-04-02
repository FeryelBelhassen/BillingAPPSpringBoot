package com.nst.facture.billing.controllers.client;


import com.nst.facture.billing.models.Client;
import com.nst.facture.billing.payload.Dto.ClientDto;
import com.nst.facture.billing.payload.response.ApiResponse;
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
    @GetMapping("/client/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable(name = "id") Long id) {
        Client client = clientService.getClientById(id);

        // convert entity to DTO
        ClientDto postResponse = modelMapper.map(client, ClientDto.class);

        return ResponseEntity.ok().body(postResponse);
    }

    /**
     * This function about create a client
     * @param clientDto
     * @return
     */
   @PostMapping("/createclient")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {

        // convert DTO to entity
        Client clientRequest = modelMapper.map(clientDto, Client.class);

        Client client = clientService.createClient(clientRequest);

        // convert entity to DTO
        ClientDto clientResponse = modelMapper.map(client, ClientDto.class);

        return new ResponseEntity<ClientDto>(clientResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO

    /**
     * This function about update a client
     * @param id
     * @param clientDto
     * @return
     */
    @PutMapping("/client/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable long id, @RequestBody ClientDto clientDto) {

        // convert DTO to Entity
        Client clientRequest = modelMapper.map(clientDto, Client.class);

        Client client = clientService.updateClient(id, clientRequest);

        // entity to DTO
        ClientDto clientResponse = modelMapper.map(client, ClientDto.class);

        return ResponseEntity.ok().body(clientResponse);
    }

    /**
     * This function about delete a client
     * @param id
     * @return
     */
    @DeleteMapping("/client/{id}")
    public ResponseEntity<ApiResponse> deleteClient(@PathVariable(name = "id") Long id) {
        clientService.deleteClient(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Client deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }


}
