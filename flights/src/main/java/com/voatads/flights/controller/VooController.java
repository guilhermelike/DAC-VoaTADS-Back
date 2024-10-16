package com.voatads.flights.controller;

import com.voatads.flights.dto.VooDTO;
import com.voatads.flights.service.VooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/voos")
public class VooController {

    @Autowired
    private VooService vooService;

    @GetMapping
    public ResponseEntity<List<VooDTO>> getAllVoos() {
        List<VooDTO> voos = vooService.getAllVoos();
        return ResponseEntity.ok(voos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VooDTO> getVooById(@PathVariable UUID id) {
        VooDTO voo = vooService.getVooById(id);
        return ResponseEntity.ok(voo);
    }
}
