package com.joyeria.model.service.impl;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.joyeria.model.dto.LoginInputDTO;
import com.joyeria.model.dto.LoginOutputDTO;
import com.joyeria.model.dto.RegistroInputDTO;
import com.joyeria.model.entity.Rol;
import com.joyeria.model.entity.Usuario;
import com.joyeria.model.repository.IUsuarioRepository;
import com.joyeria.model.service.IUsuarioService;
import com.joyeria.security.JwtUtil;
import com.joyeria.util.BCryptUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	private IUsuarioRepository usuarioRepository;
	private BCryptUtil bcryptUtil;
	
	@Override
	public LoginOutputDTO login(LoginInputDTO inputDTO) {

		Usuario usuario = usuarioRepository.findOneByNombreUsuario(inputDTO.usuario()).orElse(null);
		if(usuario!=null) {
			if(bcryptUtil.validarPassword(inputDTO.clave(), usuario.getClaveUsuario())){
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(inputDTO.usuario(), inputDTO.clave())
						);
				ArrayList<Rol> listaRoles = new ArrayList<Rol>(usuario.getRoles());
				if(authentication.isAuthenticated()) {
					String token = jwtUtil.generateToken(usuario.getNombreUsuario(), listaRoles.get(0).getNombre());
					return new LoginOutputDTO(Boolean.TRUE, "Operación realizada correctamente", token);
				}else {
					return new LoginOutputDTO(Boolean.FALSE, "Usuario y/o contraseña invalidas", null);
				}
			}else {
				return new LoginOutputDTO(Boolean.FALSE, "Usuario y/o contraseña invalidas", null);
			}
		}else {
			return new LoginOutputDTO(Boolean.FALSE, "Usuario y/o contraseña invalidas", null);
		}
	}
	
	 @Override
	    public void registrarUsuario(RegistroInputDTO inputDTO) {
	        if (usuarioRepository.existsByCorreo(inputDTO.correo())) {
	            throw new IllegalArgumentException("El usuario con ese correo ya existe");
	        }

	        Usuario nuevoUsuario = new Usuario();
	        nuevoUsuario.setNombreUsuario(inputDTO.usuario());
	        nuevoUsuario.setCorreo(inputDTO.correo());
	        nuevoUsuario.setClaveUsuario(bcryptUtil.encriptar(inputDTO.clave()));
	        nuevoUsuario.setNombres(inputDTO.nombres());
	        nuevoUsuario.setApellidoPaterno(inputDTO.apellidoPaterno());
	        nuevoUsuario.setApellidoMaterno(inputDTO.apellidoMaterno());
	        nuevoUsuario.setDni(inputDTO.dni());

	        // Asigna un rol por defecto
	        Rol rolUsuario = new Rol();
	        rolUsuario.setNombre("USUARIO");
	        nuevoUsuario.setRoles(Collections.singleton(rolUsuario));

	        usuarioRepository.save(nuevoUsuario);
	    }


	
}
