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

    public VooDTO createVoo(VooDTO vooDTO) {
        Voo voo = modelMapper.map(vooDTO, Voo.class);
        Voo savedVoo = vooRepository.save(voo);
        return modelMapper.map(savedVoo, VooDTO.class);
    }

    public VooDTO updateVoo(UUID id, VooDTO vooDTO) {
        Voo existingVoo = vooRepository.findById(id).orElseThrow(() -> new RuntimeException("Voo não encontrado"));
        modelMapper.map(vooDTO, existingVoo); // Atualiza campos com valores de vooDTO
        Voo updatedVoo = vooRepository.save(existingVoo);
        return modelMapper.map(updatedVoo, VooDTO.class);
    }

    public VooDTO partialUpdateVoo(UUID id, VooDTO vooDTO) {
        Voo existingVoo = vooRepository.findById(id).orElseThrow(() -> new RuntimeException("Voo não encontrado"));

        // Atualização parcial: verifica se cada campo está presente em vooDTO antes de atualizar
        if (vooDTO.getCodigoVoo() != null) existingVoo.setCodigoVoo(vooDTO.getCodigoVoo());
        if (vooDTO.getDataVoo() != null) existingVoo.setDataVoo(vooDTO.getDataVoo());
        if (vooDTO.getAeroportoOrigem() != null) existingVoo.setAeroportoOrigem(vooDTO.getAeroportoOrigem());
        if (vooDTO.getAeroportoDestino() != null) existingVoo.setAeroportoDestino(vooDTO.getAeroportoDestino());
        if (vooDTO.getValorPassagem() != null) existingVoo.setValorPassagem(vooDTO.getValorPassagem());
        if (vooDTO.getValorMilhas() != null) existingVoo.setValorMilhas(vooDTO.getValorMilhas());
        if (vooDTO.getTotalPoltronas() != null) existingVoo.setTotalPoltronas(vooDTO.getTotalPoltronas());
        if (vooDTO.getQtdPoltronasOcupadas() != null) existingVoo.setQtdPoltronasOcupadas(vooDTO.getQtdPoltronasOcupadas());
        if (vooDTO.getStatusVoo() != null) existingVoo.setStatusVoo(vooDTO.getStatusVoo());

        Voo updatedVoo = vooRepository.save(existingVoo);
        return modelMapper.map(updatedVoo, VooDTO.class);
    }

    public void deleteVoo(UUID id) {
        Voo existingVoo = vooRepository.findById(id).orElseThrow(() -> new RuntimeException("Voo não encontrado"));
        vooRepository.delete(existingVoo);
    }
}
