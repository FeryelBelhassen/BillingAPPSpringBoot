package com.nst.facture.billing.controllers.devis;


import com.nst.facture.billing.models.Devis;
import com.nst.facture.billing.payload.Dto.DevisDto;
import com.nst.facture.billing.payload.response.ApiResponse;
import com.nst.facture.billing.service.DevisService;
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
@Api("Devis Controller API")
/**
 * This class describes a DevisController
 */
public class DevisController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DevisService devisService;


    /**
     * This function displays the list of devis
     * @return
     */
    /*@GetMapping
    public List<DevisDto> getDevis() {

        return devisService.getAllDevis().stream().map(devis -> modelMapper.map(devis, DevisDto.class))
                .collect(Collectors.toList());
    }*/

    @GetMapping("/devis")
    public List<Devis> allDevis(){
        return devisService.getAllDevis();

    }


    /**
     * This function for get a devis
     * @param id
     * @return
     */
    @GetMapping("/devis/{id}")
    public ResponseEntity<DevisDto> getDevisById(@PathVariable(name = "id") Long id) {
        Devis devis = devisService.getDevisById(id);

        // convert entity to DTO
        DevisDto devisResponse = modelMapper.map(devis, DevisDto.class);

        return ResponseEntity.ok().body(devisResponse);
    }

    /**
     * This function about create a devis
     * @param devisDto
     * @return
     */
    @PostMapping("/createdevis")
    public ResponseEntity<DevisDto> createDevis(@RequestBody DevisDto devisDto) {

        // convert DTO to entity
        Devis devisRequest = modelMapper.map(devisDto, Devis.class);

        Devis devis = devisService.createDevis(devisRequest);

        // convert entity to DTO
        DevisDto devisResponse = modelMapper.map(devis, DevisDto.class);

        return new ResponseEntity<DevisDto>(devisResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO

    /**
     * This function about update a devis
     * @param id
     * @param devisDto
     * @return
     */
    @PutMapping("/devis/{id}")
    public ResponseEntity<DevisDto> updateDevis(@PathVariable long id, @RequestBody DevisDto devisDto) {

        // convert DTO to Entity
        Devis devisRequest = modelMapper.map(devisDto, Devis.class);

        Devis devis = devisService.updateDevis(id, devisRequest);

        // entity to DTO
        DevisDto devisResponse = modelMapper.map(devis, DevisDto.class);

        return ResponseEntity.ok().body(devisResponse);
    }

    /**
     * This function about delete a devis
     * @param id
     * @return
     */
    @DeleteMapping("/devis/{id}")
    public ResponseEntity<ApiResponse> deleteDevis(@PathVariable(name = "id") Long id) {
        devisService.deleteDevis(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Devis deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }



}