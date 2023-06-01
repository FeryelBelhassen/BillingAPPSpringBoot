package com.nst.facture.billing.controllers.devis;


import com.nst.facture.billing.exception.ResourceNotFoundException;
import com.nst.facture.billing.models.Devis;
import com.nst.facture.billing.payload.Dto.DevisDto;
import com.nst.facture.billing.repository.DevisRepository;
import com.nst.facture.billing.service.DevisService;
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
@Api("Devis Controller API")
/**
 * This class describes a DevisController
 */
public class DevisController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DevisService devisService;
    @Autowired
    private DevisRepository devisRepository;


    /**
     * This function displays the list of devis
     * @return
     */
    @GetMapping("/devis")
    public List<Devis> allDevis(){
        return devisService.getAllDevis();

    }
    /**
     * This function for get a facture
     * @param id
     * @return
     */
    @GetMapping("/devis/{id}")
    public ResponseEntity<DevisDto> getDevisById(@PathVariable(name = "id") Long id) {
        Devis devis = devisService.getDevisById(id);

        // convert entity to DTO
        DevisDto postResponse = modelMapper.map(devis, DevisDto.class);

        return ResponseEntity.ok().body(postResponse);
    }

    /**
     * This function about create a facture
     * @param devisDto
     * @return
     */

    @PostMapping("/adddevis")
    public Devis createDevis(@RequestBody DevisDto devisDto) {

        return devisService.addDevisFromDTO(devisDto);
    }

    /**
     * This function about update a facture
     * @param id
     * @return
     */
    @PutMapping("updatedevis/{id}")
    public Devis updateDevis(@PathVariable("id") @NotNull @Min(1) Long id, @RequestBody Devis updatedDevis) {
        Devis devis = devisRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Devis not found"));
        devis.setNumerodevis(updatedDevis.getNumerodevis());
        devis.setDatedevis(updatedDevis.getDatedevis());
        devis.setProduct(updatedDevis.getProduct());
        devis.setPrice(updatedDevis.getPrice());
        devisRepository.save(devis);
        return devis;
    }

    /**
     * This function about delete a facture
     * @param id
     * @return
     */
    @DeleteMapping("/deletedevis/{id}")
    public void deleteDevis(@PathVariable("id") Long id) {

        devisService.deleteDevis(id);
    }

}