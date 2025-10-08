package org.lucky_devs.Transport_Management.dominio.service;

import org.lucky_devs.Transport_Management.dominio.dto.TarifasDto;
import org.lucky_devs.Transport_Management.dominio.enums.Tipo_Unidad;
import org.lucky_devs.Transport_Management.persistence.entity.TarifasEntity;
import org.lucky_devs.Transport_Management.persistence.mapper.TarifasMapper;
import org.lucky_devs.Transport_Management.repository.TarifasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifasService {

    private final TarifasRepository tarifasRepository;
    private final TarifasMapper tarifasMapper;

    public TarifasService(TarifasRepository tarifasRepository, TarifasMapper tarifasMapper) {
        this.tarifasRepository = tarifasRepository;
        this.tarifasMapper = tarifasMapper;
    }

    public List<TarifasDto> listarTarifas() {
        return tarifasMapper.toDtoList(tarifasRepository.findAll());
    }

    public TarifasDto buscarPorTipoUnidad(Tipo_Unidad tipoUnidad) {
        TarifasEntity entity = tarifasRepository.findByTipoUnidad(tipoUnidad);
        if (entity == null) {
            throw new RuntimeException("No hay tarifas registradas para el tipo de unidad: " + tipoUnidad);
        }
        return tarifasMapper.toDto(entity);
    }

    public TarifasDto agregarTarifa(TarifasDto dto) {
        if (tarifasRepository.existsByTipoUnidad(dto.tipoUnidad())) {
            throw new RuntimeException("Ya existe una tarifa para el tipo de unidad: " + dto.tipoUnidad());
        }
        TarifasEntity entity = tarifasMapper.toEntity(dto);
        tarifasRepository.save(entity);
        return tarifasMapper.toDto(entity);
    }

    public TarifasDto modificarTarifa(Long id, TarifasDto dto) {
        TarifasEntity entity = tarifasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarifa no encontrada con id: " + id));

        tarifasMapper.updateEntityFromDto(dto, entity);
        tarifasRepository.save(entity);
        return tarifasMapper.toDto(entity);
    }

    public void eliminarTarifa(Long id) {
        if (!tarifasRepository.existsById(id)) {
            throw new RuntimeException("No existe tarifa con id: " + id);
        }
        tarifasRepository.deleteById(id);
    }
}