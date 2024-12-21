package com.joyeria.model.service;

import java.util.List;

import com.joyeria.model.dto.ProductoInputDTO;
import com.joyeria.model.entity.Producto;


public interface IProductoService {
	List<Producto> listar();
	Producto registrar(ProductoInputDTO productoDTO);
	Producto buscarPorId(Long id);
	Producto actualizar(Long idProducto, ProductoInputDTO productoDTO);
	Producto eliminar(Long idProducto);
}
