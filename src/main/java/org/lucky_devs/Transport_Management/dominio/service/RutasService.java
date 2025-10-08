package org.lucky_devs.Transport_Management.dominio.service;

import org.lucky_devs.Transport_Management.dominio.dto.RutasRequestDto;
import org.lucky_devs.Transport_Management.dominio.dto.RutasResponseDto;
import org.lucky_devs.Transport_Management.dominio.exception.RutaNotFound;
import org.lucky_devs.Transport_Management.dominio.exception.RutaYaExiste;
import org.lucky_devs.Transport_Management.persistence.entity.RutaEntity;
import org.lucky_devs.Transport_Management.persistence.mapper.RutasMapper;
import org.lucky_devs.Transport_Management.repository.RutasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutasService {

    private final RutasRepository rutasRepository;
    private final RutasMapper rutasMapper;

    public RutasService(RutasRepository rutasRepository, RutasMapper rutasMapper) {
        this.rutasRepository = rutasRepository;
        this.rutasMapper = rutasMapper;
    }

    public List<RutasResponseDto> listarRutas(){
        return this.rutasMapper.toDto(this.rutasRepository.findAll());
    }

    public RutasResponseDto buscarRutaPorId(Long id_ruta){
            return this.rutasMapper.toDto(this.rutasRepository.findById(id_ruta).orElse(null));
    }

    public RutasResponseDto agregarNuevaRuta(RutasRequestDto rutasRequestDto){
        if (this.rutasRepository.findByDireccion_Destino(rutasRequestDto.direccion_destino()) != null){
            throw new RutaYaExiste(rutasRequestDto.direccion_destino());
        }else{
            RutaEntity rutaEntity = this.rutasMapper.toEntity(rutasRequestDto);
            this.rutasRepository.save(rutaEntity);
            return this.rutasMapper.toDto(rutaEntity);
        }
    }

    public RutasResponseDto modificarRutaExistente(Long id_ruta, RutasRequestDto rutasRequestDto){
        RutaEntity rutaEntity = this.rutasRepository.findById(id_ruta).orElse(null);

        if (rutaEntity == null){
            throw new RutaNotFound(id_ruta);
        }else{
            this.rutasMapper.modificarEntityFromDto(rutasRequestDto, rutaEntity);
            return this.rutasMapper.toDto(this.rutasRepository.save(rutaEntity));
        }
    }

    public void elminarRuta(Long id_ruta){
        RutaEntity rutaEntity = this.rutasRepository.findById(id_ruta).orElse(null);
        if (rutaEntity == null){
            throw new RutaNotFound(id_ruta);
        }else {
            this.rutasRepository.deleteById(id_ruta);
        }

    }
}
