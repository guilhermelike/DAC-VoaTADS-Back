package com.voatads.flights.service;

import com.voatads.flights.dto.VooDTO;
import com.voatads.flights.model.Voo;
import com.voatads.flights.repository.VooRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VooService {

    @Autowired
    private VooRepository vooRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<VooDTO> getAllVoos() {
        return vooRepository.findAll().stream()
                .map(voo -> modelMapper.map(voo, VooDTO.class))
                .collect(Collectors.toList());
    }

    public VooDTO getVooById(UUID id) {
        Voo voo = vooRepository.findById(id).orElseThrow(() -> new RuntimeException("Voo não encontrado"));
        return modelMapper.map(voo, VooDTO.class);
    }
}
