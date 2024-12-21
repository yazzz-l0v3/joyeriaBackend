package com.joyeria.model.dto;

public record ProductoInputDTO(
		Long id,
		String nombreProd,
		String descripcionProd,
		double precioProd,
		String imagenUrl,
		Integer estado,
		Long idMarca
		) {

}
