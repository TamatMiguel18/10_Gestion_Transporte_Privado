package org.lucky_devs.Transport_Management.dominio.service;

import org.lucky_devs.Transport_Management.dominio.dto.ModUnidadDto;
import org.lucky_devs.Transport_Management.dominio.dto.UnidadRequestDto;
import org.lucky_devs.Transport_Management.dominio.dto.UnidadResponseDto;
import org.lucky_devs.Transport_Management.dominio.enums.Estado_Operativo;
import org.lucky_devs.Transport_Management.dominio.exception.PlacaNotFound;
import org.lucky_devs.Transport_Management.dominio.exception.PlacaYaEnUso;
import org.lucky_devs.Transport_Management.persistence.entity.UnidadEntity;
import org.lucky_devs.Transport_Management.persistence.mapper.UnidadMapper;
import org.lucky_devs.Transport_Management.repository.UnidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UnidadService {
    private final UnidadRepository unidadRepository;
    private final UnidadMapper unidadMapper;

    public UnidadService(UnidadRepository unidadRepository, UnidadMapper unidadMapper) {
        this.unidadRepository = unidadRepository;
        this.unidadMapper = unidadMapper;
    }

    public List<UnidadResponseDto> listaUnidades(){
        return this.unidadMapper.toDto(this.unidadRepository.findAll());
    }

    public UnidadResponseDto buscarPorPlaca(String placa){
        if (this.unidadMapper.toDto(this.unidadRepository.findByPlaca(placa)) == null){
            throw new PlacaNotFound(placa);
        }else {
            return this.unidadMapper.toDto(this.unidadRepository.findByPlaca(placa));
        }
    }

    public UnidadResponseDto agregarNuevaUnidad(UnidadRequestDto unidadRequestDto){
        if (this.unidadRepository.findByPlaca(unidadRequestDto.placa()) != null){
            throw new PlacaYaEnUso("Ya hay una unidad con dicha placa.");
        } else {
            UnidadEntity unidadEntity = this.unidadMapper.toEntity(unidadRequestDto);
            unidadEntity.setEstado_operativo(Estado_Operativo.DISPONIBLE);
            this.unidadRepository.save(unidadEntity);
            return this.unidadMapper.toDto(unidadEntity);
        }
    }

    public UnidadResponseDto modificarUnidadExistente(String placa, ModUnidadDto modUnidadDto){
        UnidadEntity unidadEntity = this.unidadRepository.findByPlaca(placa);

        if (unidadEntity == null){
            throw new PlacaNotFound("No hay ninguna unidad registrada con esta placa: "+modUnidadDto.placa()+" verifique que este ingresando la correcta.");
        }else{
            this.unidadMapper.modificarEntityFromDto(modUnidadDto, unidadEntity);
            return this.unidadMapper.toDto(this.unidadRepository.save(unidadEntity));
        }
    }

    public void eliminarUnidad(String placa){
        UnidadEntity unidadEntity = this.unidadRepository.findByPlaca(placa);
        if(unidadEntity == null){
            throw new PlacaNotFound("No hay ninguna unidad registrada con esta placa, verifique que este ingresando la correcta.");
        }else{
            this.unidadRepository.deleteByPlaca(placa);
        }
    }
}
