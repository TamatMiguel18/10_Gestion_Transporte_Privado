package org.lucky_devs.Transport_Management.dominio.service;

import org.lucky_devs.Transport_Management.dominio.dto.UsuarioDto;
import org.lucky_devs.Transport_Management.persistence.entity.UsuariosEntity;
import org.lucky_devs.Transport_Management.repository.UsuarioRepository;
import org.lucky_devs.Transport_Management.persistence.mapper.UsuariosMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuariosMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuariosMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional(readOnly = true)
    public List<UsuarioDto> listarUsuarios() {
        return usuarioMapper.toDto(usuarioRepository.findAll());
    }

    @Transactional(readOnly = true)
    public UsuarioDto buscarPorCorreo(String correo) {
        UsuariosEntity entity = usuarioRepository.findByCorreo(correo);
        if (entity == null) {
            throw new RuntimeException("No se encontró ningún usuario con el correo: " + correo);
        }
        return usuarioMapper.toDto(entity);
    }

    @Transactional
    public UsuarioDto agregarUsuario(UsuarioDto dto) {
        if (usuarioRepository.existsByCorreo(dto.correo())) {
            throw new RuntimeException("Ya existe un usuario con el correo: " + dto.correo());
        }
        UsuariosEntity entity = usuarioMapper.toEntity(dto);
        usuarioRepository.save(entity);
        return usuarioMapper.toDto(entity);
    }

    @Transactional
    public UsuarioDto modificarUsuario(String correo, UsuarioDto dto) {
        UsuariosEntity entity = usuarioRepository.findByCorreo(correo);
        if (entity == null) {
            throw new RuntimeException("No existe un usuario con el correo: " + correo);
        }

        entity.setContrasena(dto.contrasena());

        usuarioRepository.save(entity);
        return usuarioMapper.toDto(entity);
    }

    @Transactional
    public void eliminarUsuario(String correo) {
        if (usuarioRepository.findByCorreo(correo) == null) {
            throw new RuntimeException("No existe un usuario con el correo: " + correo);
        }
        usuarioRepository.deleteByCorreo(correo);
    }
}
