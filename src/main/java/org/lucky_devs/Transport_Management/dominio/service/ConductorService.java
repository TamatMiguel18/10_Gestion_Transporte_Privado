package org.lucky_devs.Transport_Management.dominio.service;

import org.lucky_devs.Transport_Management.dominio.dto.ConductorDto;
import org.lucky_devs.Transport_Management.dominio.dto.ModConductor;
import org.lucky_devs.Transport_Management.dominio.enums.Estado;
import org.lucky_devs.Transport_Management.persistence.entity.ConductorEntity;
import org.lucky_devs.Transport_Management.persistence.mapper.ConductorMapper;
import org.lucky_devs.Transport_Management.repository.ConductorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConductorService {

    private final ConductorRepository conductorRepository;
    private final ConductorMapper conductorMapper;

    public ConductorService(ConductorRepository conductorRepository, ConductorMapper conductorMapper) {
        this.conductorRepository = conductorRepository;
        this.conductorMapper = conductorMapper;
    }

    public List<ConductorDto> listarConductores() {
        return conductorMapper.toDto(conductorRepository.findAll());
    }

    // 🔍 Buscar conductor por licencia
    public ConductorDto buscarPorLicencia(String licencia) {
        ConductorEntity entity = conductorRepository.findByLicencia(licencia);
        if (entity == null) {
            throw new RuntimeException("No se encontró ningún conductor con la licencia: " + licencia);
        }
        return conductorMapper.toDto(entity);
    }

    // 🆕 Agregar un nuevo conductor
    public ConductorDto agregarConductor(ConductorDto dto) {
        if (conductorRepository.existsByLicencia(dto.licencia())) {
            throw new RuntimeException("Ya existe un conductor registrado con la licencia: " + dto.licencia());
        }

        ConductorEntity entity = conductorMapper.toEntity(dto);
        entity.setEstado(Estado.DESCANSANDO);
        conductorRepository.save(entity);
        return conductorMapper.toDto(entity);
    }

    public ConductorDto modificarConductor(String licencia, ModConductor modConductorDto) {
        ConductorEntity entity = conductorRepository.findByLicencia(licencia);
        if (entity == null) {
            throw new RuntimeException("No se encontró ningún conductor con la licencia: " + licencia);
        }

        conductorMapper.modificarEntityFromDto(modConductorDto, entity);
        conductorRepository.save(entity);
        return conductorMapper.toDto(entity);
    }
    @Transactional
    public void eliminarConductor(String licencia) {
        ConductorEntity entity = conductorRepository.findByLicencia(licencia);
        if (entity == null) {
            throw new RuntimeException("No existe un conductor con la licencia: " + licencia);
        }
        conductorRepository.deleteByLicencia(licencia);
    }

    public List<ConductorDto> listarPorEstado(Estado estado) {
        List<ConductorEntity> conductores = conductorRepository.findByEstado(estado);
        return conductorMapper.toDto(conductores);
    }
}
