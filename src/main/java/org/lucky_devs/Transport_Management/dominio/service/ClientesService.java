package org.lucky_devs.Transport_Management.dominio.service;

import org.lucky_devs.Transport_Management.dominio.dto.ClientesDto;
import org.lucky_devs.Transport_Management.persistence.entity.ClienteEntity;
import org.lucky_devs.Transport_Management.persistence.mapper.ClientesMapper;
import org.lucky_devs.Transport_Management.repository.ClientesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ClientesService {

    private final ClientesRepository clientesRepository;
    private final ClientesMapper clientesMapper;

    public ClientesService(ClientesRepository clientesRepository, ClientesMapper clientesMapper) {
        this.clientesRepository = clientesRepository;
        this.clientesMapper = clientesMapper;
    }

    @Transactional(readOnly = true)
    public ClientesDto buscarPorCorreo(String correo) {
        ClienteEntity entity = clientesRepository.findByCorreo(correo);
        if (entity == null) {
            throw new RuntimeException("No se encontró un cliente con el correo: " + correo);
        }
        return clientesMapper.toDto(entity);
    }

    @Transactional
    public ClientesDto agregarNuevoCliente(ClientesDto dto) {
        if (clientesRepository.existsByCorreo(dto.correo())) {
            throw new RuntimeException("Ya existe un cliente con el correo: " + dto.correo());
        }
        ClienteEntity entity = clientesMapper.toEntity(dto);
        clientesRepository.save(entity);
        return clientesMapper.toDto(entity);
    }

    @Transactional
    public ClientesDto modificarCliente(String correo, ClientesDto clienteModificado) {
        ClienteEntity entity = clientesRepository.findByCorreo(correo);
        if (entity == null) {
            throw new RuntimeException("No existe un cliente con el correo: " + correo);
        }

        entity.setNombre(clienteModificado.nombre());
        entity.setTelefono(clienteModificado.telefono());
        entity.setCorreo(clienteModificado.correo());

        clientesRepository.save(entity);
        return clientesMapper.toDto(entity);
    }

    @Transactional
    public void eliminarCliente(String correo) {
        if (clientesRepository.findByCorreo(correo) == null) {
            throw new RuntimeException("No existe un cliente con el correo: " + correo);
        }
        clientesRepository.deleteByCorreo(correo);
    }

    @Transactional(readOnly = true)
    public List<ClientesDto> listarClientes() {
        return clientesMapper.toDto(clientesRepository.findAll());
    }

}
