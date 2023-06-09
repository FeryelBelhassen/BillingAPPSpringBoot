package com.nst.facture.billing.controllers.facture;


import com.nst.facture.billing.exception.ResourceNotFoundException;
import com.nst.facture.billing.models.Facture;
import com.nst.facture.billing.payload.Dto.FactureDto;
import com.nst.facture.billing.repository.ClientRepository;
import com.nst.facture.billing.repository.FactureRepository;
import com.nst.facture.billing.repository.ProductRepository;
import com.nst.facture.billing.repository.RoleRepository;
import com.nst.facture.billing.service.FactureService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@Api("Facture Controller API")
public class FactureController {

    @Autowired
    FactureRepository factureRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FactureService factureService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;

    /**A function that returns factures per user
     *
     * */
    @GetMapping("/factures-per-user")
    public List<Facture> allFacturesPerUser(@RequestParam("idUser") Long idUser){
        return factureService.getAllFacturesPerUser(idUser);

    }
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

    @GetMapping("/factures/{id}")
    public Facture getFacture(@PathVariable Long id) {
        return factureService.getFactureById(id);
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
     * @return
     */
    @PutMapping("updatefacture/{id}")
    public Facture updateFacture(@PathVariable("id") @NotNull @Min(1) Long id, @RequestBody Facture updatedFacture) {
        Facture facture = factureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Facture not found"));
        facture.setNumerofacture(updatedFacture.getNumerofacture());
        facture.setClient(updatedFacture.getClient());
        facture.setDatefacture(updatedFacture.getDatefacture());
        facture.setProduct(updatedFacture.getProduct());
        facture.setTotal(updatedFacture.getTotal());
        factureRepository.save(facture);
        return facture;
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
