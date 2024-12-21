package com.joyeria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyeria.model.dto.LoginInputDTO;
import com.joyeria.model.dto.LoginOutputDTO;
import com.joyeria.model.dto.RegistroInputDTO;
import com.joyeria.model.dto.RegistroOutputDTO;
import com.joyeria.model.service.IUsuarioService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioController {
	private IUsuarioService usuarioService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginOutputDTO> login(@RequestBody LoginInputDTO inputDTO){
		return ResponseEntity.ok(usuarioService.login(inputDTO));
	}
	
	 @PostMapping("/registro")
	    public ResponseEntity<String> registrarUsuario(@RequestBody RegistroInputDTO inputDTO) {
	        try {
	            usuarioService.registrarUsuario(inputDTO);
	            return ResponseEntity.ok("Usuario registrado exitosamente");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Error al registrar usuario: " + e.getMessage());
	        }
	    }
	
}
