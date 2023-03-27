package com.nst.facture.billing.controllers.facture;


import com.nst.facture.billing.models.Facture;
import com.nst.facture.billing.payload.Dto.FactureDto;
import com.nst.facture.billing.payload.response.ApiResponse;
import com.nst.facture.billing.repository.FactureRepository;
import com.nst.facture.billing.service.FactureService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/facture")
@CrossOrigin(origins = "http://localhost:8081")
@Api("Facture Controller API")
public class FactureController {

    @Autowired
    FactureRepository factureRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FactureService factureService;


    /**
     * This function displays the list of factures
     * @return
     */
    @GetMapping
    public List<FactureDto> getAllFactures() {

        return factureService.getAllFactures().stream().map(facture -> modelMapper.map(facture, FactureDto.class))
                .collect(Collectors.toList());
    }

    /**
     * This function for get a facture
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<FactureDto> getFactureById(@PathVariable(name = "id") Long id) {
        Facture facture = factureService.getFactureById(id);

        // convert entity to DTO
        FactureDto postResponse = modelMapper.map(facture, FactureDto.class);

        return ResponseEntity.ok().body(postResponse);
    }

    /**
     * This function about create a facture
     * @param factureDto
     * @return
     */
   @PostMapping
    public ResponseEntity<FactureDto> createFacture(@RequestBody FactureDto factureDto) {

        // convert DTO to entity
        Facture factureRequest = modelMapper.map(factureDto, Facture.class);

        Facture facture = factureService.createFacture(factureRequest);

        // convert entity to DTO
        FactureDto factureResponse = modelMapper.map(facture, FactureDto.class);

        return new ResponseEntity<FactureDto>(factureResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO

    /**
     * This function about update a facture
     * @param id
     * @param factureDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<FactureDto> updateFacture(@PathVariable long id, @RequestBody FactureDto factureDto) {

        // convert DTO to Entity
        Facture factureRequest = modelMapper.map(factureDto, Facture.class);

        Facture facture = factureService.updateFacture(id, factureRequest);

        // entity to DTO
        FactureDto factureResponse = modelMapper.map(facture, FactureDto.class);

        return ResponseEntity.ok().body(factureResponse);
    }

    /**
     * This function about delete a facture
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFacture(@PathVariable(name = "id") Long id) {
        factureService.deleteFacture(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Facture deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }


}
