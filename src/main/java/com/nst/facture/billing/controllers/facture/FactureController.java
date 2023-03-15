package com.nst.facture.billing.controllers.facture;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FactureController {

   /* @Autowired
    FactureRepository factureRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FactureService factureService;


    /**
     * This function displays the list of factures
     * @return
     */
    /*@GetMapping
    public List<FactureDto> getAllFactures() {

        return factureService.getAllFactures().stream().map(facture -> modelMapper.map(facture, FactureDto.class))
                .collect(Collectors.toList());
    }

    /**
     * This function for get a facture
     * @param id
     * @return
     */
    /*@GetMapping("/{id}")
    public ResponseEntity<FactureDto> getFactureById(@PathVariable(name = "id") Long id) {
        Facture facture = factureService.getFactureById(id);

        // convert entity to DTO
        FactureDto postResponse = modelMapper.map(facture, FactureDto.class);

        return ResponseEntity.ok().body(postResponse);
    }
*/
    /**
     * This function about create a facture
     * @param factureDto
     * @return
     */
  /*  @PostMapping
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
    /*@PutMapping("/{id}")
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
   /* @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFacture(@PathVariable(name = "id") Long id) {
        factureService.deleteFacture(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Facture deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

*/


}
