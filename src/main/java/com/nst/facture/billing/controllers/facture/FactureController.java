package com.nst.facture.billing.controllers.facture;


import com.nst.facture.billing.models.Facture;
import com.nst.facture.billing.payload.Dto.FactureDto;
import com.nst.facture.billing.repository.FactureRepository;
import com.nst.facture.billing.service.FactureService;
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

    @GetMapping("/factures")
    public List<Facture> allFactures(){
        return factureService.getAllFactures();

    }
    /**
     * This function for get a facture
     * @param id
     * @return
     */
    @GetMapping("/facture/{id}")
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

    @PostMapping("/addfacture")
    public Facture createFacture(@RequestBody FactureDto factureDto) {

        return factureService.addFactureFromDTO(factureDto);
    }

    /**
     * This function about update a facture
     * @param id
     * @param facture
     * @return
     */
    @PutMapping("updatefacture/{id}")
    public ResponseEntity<Facture> updateFacture(@PathVariable("id") Long id, @RequestBody Facture facture){

        Facture f= factureService.updateFacture(id, facture);
        return new ResponseEntity<Facture>(f, HttpStatus.OK);
    }

    /**
     * This function about delete a facture
     * @param id
     * @return
     */
    @DeleteMapping("/deletefacture/{id}")
    public void deleteFacture(@PathVariable("id") Long id) {

        factureService.deleteFacture(id);
    }


}
