package com.joyeria.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyeria.model.entity.Marca;
import com.joyeria.model.repository.IMarcaRepository;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/marca")
@AllArgsConstructor
public class MarcaController {
	private IMarcaRepository marcaRepository;

	@GetMapping("/listar")
	public List<Marca> listarMarcas() {
		return marcaRepository.findAll();
	}
}
