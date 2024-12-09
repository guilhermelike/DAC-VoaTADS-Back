package com.voatads.flights.controller;

import com.voatads.flights.dto.VooDTO;
import com.voatads.flights.model.Airport;
import com.voatads.flights.service.VooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/flights")
public class VooController {

    @Autowired
    private VooService vooService;

    @GetMapping
    public ResponseEntity<List<VooDTO>> getAllVoos() {
        List<VooDTO> voos = vooService.getAllVoos();
        return ResponseEntity.ok(voos);
    }

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> getAirports() {
        List<Airport> airports = vooService.getAllAirports();
        System.out.println("Aeroportos:");
        System.out.println(airports);
        return ResponseEntity.ok(airports);
    }

    @PostMapping("/travels")
    public ResponseEntity<List<VooDTO>> getTravels(@RequestBody VooDTO vooDTO) {
        System.out.println("FlightDTO: " + vooDTO);
        List<VooDTO> voos = vooService.getFilterVoos(vooDTO.getAeroportoOrigem(), vooDTO.getAeroportoDestino());
        return ResponseEntity.ok(voos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VooDTO> getVooById(@PathVariable UUID id) {
        VooDTO voo = vooService.getVooById(id);
        return ResponseEntity.ok(voo);
    }

    @PostMapping
    public ResponseEntity<VooDTO> createVoo(@RequestBody VooDTO vooDTO) {
        VooDTO createdVoo = vooService.createVoo(vooDTO);
        return ResponseEntity.ok(createdVoo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VooDTO> updateVoo(@PathVariable UUID id, @RequestBody VooDTO vooDTO) {
        VooDTO updatedVoo = vooService.updateVoo(id, vooDTO);
        return ResponseEntity.ok(updatedVoo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VooDTO> partialUpdateVoo(@PathVariable UUID id, @RequestBody VooDTO vooDTO) {
        VooDTO updatedVoo = vooService.partialUpdateVoo(id, vooDTO);
        return ResponseEntity.ok(updatedVoo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoo(@PathVariable UUID id) {
        vooService.deleteVoo(id);
        return ResponseEntity.noContent().build();
    }


    
}
