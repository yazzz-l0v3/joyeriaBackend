package com.joyeria.model.dto;

public record RegistroInputDTO(
	    String usuario,
	    String clave,
	    String nombres,
	    String apellidoPaterno,
	    String apellidoMaterno,
	    String dni,
	    String correo
    
) {}
