package com.joyeria.model.service;

import com.joyeria.model.dto.LoginInputDTO;
import com.joyeria.model.dto.LoginOutputDTO;
import com.joyeria.model.dto.RegistroInputDTO;

public interface IUsuarioService {
	LoginOutputDTO login (LoginInputDTO inputDTO);
	void registrarUsuario(RegistroInputDTO registroInputDTO);
}
