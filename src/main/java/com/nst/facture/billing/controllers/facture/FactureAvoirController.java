package com.nst.facture.billing.controllers.facture;


import com.nst.facture.billing.exception.ResourceNotFoundException;
import com.nst.facture.billing.models.FactureAvoir;
import com.nst.facture.billing.payload.Dto.FactureAvoirDto;
import com.nst.facture.billing.repository.FactureAvoirRepository;
import com.nst.facture.billing.service.FactureAvoirService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
     * This function displays the list of facturesavoir
     * @return
     */
    @GetMapping("/facturesavoir")
    public List<FactureAvoir> allFacturesAvoir(){

        return factureAvoirService.getAllFacturesAvoir();
    }
    /**
     * This function for get a factureavoir
     * @param id
     * @return
     */
    @GetMapping("/factureavoir/{id}")
    public ResponseEntity<FactureAvoirDto> getFactureById(@PathVariable(name = "id") Long id) {
        FactureAvoir factureAvoir = factureAvoirService.getFactureAvoirById(id);

        // convert entity to DTO
        FactureAvoirDto postResponse = modelMapper.map(factureAvoir, FactureAvoirDto.class);

        return ResponseEntity.ok().body(postResponse);
    }

    /**
     * This function about create a facture
     * @param factureAvoirDto
     * @return
     */

    @PostMapping("/addfactureavoir")
    public FactureAvoir createFactureAvoir(@RequestBody FactureAvoirDto factureAvoirDto) {

        return factureAvoirService.addFactureAvoirFromDTO(factureAvoirDto);
    }

    /**
     * This function about update a factureavoir
     * @param id
     * @return
     */
    @PutMapping("updatefactureavoir/{id}")
    public FactureAvoir updateFactureAvoir(@PathVariable("id") @NotNull @Min(1) Long id, @RequestBody FactureAvoir updatedFactureavoir) {
        FactureAvoir factureAvoir = factureAvoirRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FactureAvoir not found"));
        factureAvoir.setNumfactureavoir(updatedFactureavoir.getNumfactureavoir());
        factureAvoir.setClient(updatedFactureavoir.getClient());
        factureAvoir.setDatefacture(updatedFactureavoir.getDatefacture());
        factureAvoir.setProduct(updatedFactureavoir.getProduct());
        factureAvoir.setPrice(updatedFactureavoir.getPrice());
        factureAvoirRepository.save(factureAvoir);
        return factureAvoir;
    }


    /**
     * This function about delete a factureavoir
     * @param id
     * @return
     */
    @DeleteMapping("/deletefactureavoir/{id}")
    public void deleteFactureAvoir(@PathVariable("id") Long id) {

        factureAvoirService.deleteFactureAvoir(id);
    }
}
