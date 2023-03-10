package com.nst.facture.billing.controllers;


import com.nst.facture.billing.models.Facture;
import com.nst.facture.billing.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FactureController {

    @Autowired
    FactureRepository factureRepository;
    /*public FactureController(FactureRepository factureRepository) {

        this.factureRepository = factureRepository;
    }*/

   @GetMapping("/factures")
    public ResponseEntity<List<Facture>> getAllFactures(@RequestParam(required = false) Integer numero) {
        try {
            List<Facture> factures = new ArrayList<Facture>();
            if (numero == null)
                factureRepository.findAll().forEach(factures::add);
            else
                factureRepository.findByNumero(numero).forEach(factures::add);

            if (factures.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(factures, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/factures/{id}")
    public ResponseEntity<Facture> getFactureById(@PathVariable("id") long id) {
        Optional<Facture> factureData = factureRepository.findById(id);

        if (factureData.isPresent()) {
            return new ResponseEntity<>(factureData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/factures")
    public ResponseEntity<Facture> createFacture(@RequestBody Facture facture) {
        try {
            Facture _facture = factureRepository
                    .save(new Facture(facture.getNumero(), facture.getNomclient(), facture.getDesignation(),
                            facture.getQuantite(), facture.getDate(), facture.getPrixunitaire(),
                            facture.getMontantht(), facture.getTauxtva(), facture.getTauxttc(),false));
            return new ResponseEntity<>(_facture, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/factures/{id}")
    public ResponseEntity<Facture> updateFacture(@PathVariable("id") long id, @RequestBody Facture facture) {
        Optional<Facture> factureData = factureRepository.findById(id);

        if (factureData.isPresent()) {
            Facture _facture = factureData.get();
            _facture.setNumero(facture.getNumero());
            _facture.setNomclient(facture.getNomclient());
            _facture.setDesignation(facture.getDesignation());
            _facture.setQuantite(facture.getQuantite());
            _facture.setDate(facture.getDate());
            _facture.setPrixunitaire(facture.getPrixunitaire());
            _facture.setTauxttc(facture.getTauxttc());
            _facture.setTauxtva(facture.getTauxtva());
            _facture.setMontantht(facture.getMontantht());
            return new ResponseEntity<>(factureRepository.save(_facture), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/factures/{id}")
    public ResponseEntity<HttpStatus> deleteFacture(@PathVariable("id") long id) {
        try {
            factureRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/factures")
    public ResponseEntity<HttpStatus> deleteAllFactures() {
        try {
            factureRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
