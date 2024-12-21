package com.joyeria.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joyeria.model.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findOneByNombreUsuario(String nombreUsuario);
	boolean existsByCorreo(String correo);
}
