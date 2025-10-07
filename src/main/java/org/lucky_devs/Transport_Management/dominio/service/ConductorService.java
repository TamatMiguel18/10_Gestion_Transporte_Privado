package org.lucky_devs.Transport_Management.dominio.service;

import org.lucky_devs.Transport_Management.dominio.dto.ConductorDto;
import org.lucky_devs.Transport_Management.dominio.dto.ModConductor;
import org.lucky_devs.Transport_Management.repository.ConductorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConductorService {

    private final ConductorRepository conductorRepository;

    public ConductorService (ConductorRepository conductorRepository){
        this.conductorRepository = conductorRepository;
    }

    public List<ConductorDto> obtenerTodo(){
        return this.conductorRepository.obtenerTodo();
    }

    public ConductorDto buscarPorId(Long id){
        return this.conductorRepository.buscoarPorId(id);
    }

    public ConductorDto GuardarConductor(ConductorDto conductorDto){
        return this.conductorRepository.guardarConductor(conductorDto);
    }

    public ConductorDto modificarConductor(Long Id, ModConductor modConductor){
        return this.conductorRepository.modificarConductor(Id, modConductor);
    }

    public void eliminarConductor(Long Id){
        this.conductorRepository.eliminarConductor(Id);
    }
}
