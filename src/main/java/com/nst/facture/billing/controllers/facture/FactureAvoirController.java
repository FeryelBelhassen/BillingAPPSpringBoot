package com.nst.facture.billing.controllers.facture;


import com.nst.facture.billing.models.FactureAvoir;
import com.nst.facture.billing.payload.Dto.FactureAvoirDto;
import com.nst.facture.billing.payload.response.ApiResponse;
import com.nst.facture.billing.repository.FactureAvoirRepository;
import com.nst.facture.billing.service.FactureAvoirService;
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
@Api("Facture Controller API")
public class FactureAvoirController {

    @Autowired
    FactureAvoirRepository factureAvoirRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FactureAvoirService factureAvoirService;


    /**
     * This function displays the list of factures
     * @return
     */
    /*@GetMapping("/factures")
    public List<FactureDto> getAllFactures() {

        return factureService.getAllFactures().stream().map(facture -> modelMapper.map(facture, FactureDto.class))
                .collect(Collectors.toList());
    }*/

    @GetMapping("/factureavoir")
    public List<FactureAvoir> getAllFacturesAvoir(){
        return factureAvoirService.getAllFacturesAvoir();

    }
    /**
     * This function for get a factureavoir
     * @param idfactavoir
     * @return
     */
    @GetMapping("/factureavoir/{idfactavoir}")
    public ResponseEntity<FactureAvoirDto> getFactureById(@PathVariable(name = "id") Long idfactavoir) {
        FactureAvoir factureAvoir = factureAvoirService.getFactureAvoirById(idfactavoir);

        // convert entity to DTO
        FactureAvoirDto postResponse = modelMapper.map(factureAvoir, FactureAvoirDto.class);

        return ResponseEntity.ok().body(postResponse);
    }

    /**
     * This function about create a factureavoir
     * @param factureAvoirDto
     * @return
     */
   @PostMapping("/createfactureavoir")
    public ResponseEntity<FactureAvoirDto> createFacture(@RequestBody FactureAvoirDto factureAvoirDto) {

        // convert DTO to entity
        FactureAvoir factureavoirRequest = modelMapper.map(factureAvoirDto, FactureAvoir.class);

        FactureAvoir factureAvoir = factureAvoirService.createFactureAvoir(factureavoirRequest);

        // convert entity to DTO
        FactureAvoirDto factureavoirResponse = modelMapper.map(factureAvoir, FactureAvoirDto.class);

        return new ResponseEntity<FactureAvoirDto>(factureavoirResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO

    /**
     * This function about update a facture
     * @param idfactavoir
     * @param factureAvoirDto
     * @return
     */
    @PutMapping("/updatefactureavoir/{ididfactavoir}")
    public ResponseEntity<FactureAvoirDto> updateFactureAvoir(@PathVariable long idfactavoir, @RequestBody FactureAvoirDto factureAvoirDto) {

        // convert DTO to Entity
        FactureAvoir factureavoirRequest = modelMapper.map(factureAvoirDto, FactureAvoir.class);

        FactureAvoir factureAvoir = factureAvoirService.updateFactureAvoir(idfactavoir, factureavoirRequest);

        // entity to DTO
        FactureAvoirDto factureavoirResponse = modelMapper.map(factureAvoir, FactureAvoirDto.class);

        return ResponseEntity.ok().body(factureavoirResponse);
    }

    /**
     * This function about delete a facture
     * @param idfactavoir
     * @return
     */
    @DeleteMapping("/deletefactureavoir/{idfactavoir}")
    public ResponseEntity<ApiResponse> deleteFactureAvoir(@PathVariable(name = "idfactavoir") Long idfactavoir) {
        factureAvoirService.deleteFactureAvoir(idfactavoir);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "FactureAvoir deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }


}
