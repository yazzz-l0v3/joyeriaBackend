package com.joyeria.model.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joyeria.model.dto.ProductoInputDTO;
import com.joyeria.model.entity.Marca;
import com.joyeria.model.entity.Producto;
import com.joyeria.model.repository.IProductoRepository;
import com.joyeria.model.service.IProductoService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ProductoServiceImpl implements IProductoService{

	private IProductoRepository productoRepository;
	
	@Override
	public List<Producto> listar() {
		Integer estadoActivo = 1;
		return productoRepository.findAllByEstado(estadoActivo);
	}

	@Override
	public Producto registrar(ProductoInputDTO productoDTO) {
		
		Integer estadoActivo = 1;
		Producto producto = new Producto();
		Marca marca = new Marca();
		marca.setId(productoDTO.idMarca());
		producto.setMarca(marca);
		producto.setNombreProd(productoDTO.nombreProd());
		producto.setDescripcionProd(productoDTO.descripcionProd());
		producto.setPrecioProd(productoDTO.precioProd());
		producto.setImagenUrl(productoDTO.imagenUrl());
		producto.setEstado(estadoActivo);
		
		return productoRepository.save(producto);
	}

	@Override
	public Producto buscarPorId(Long id) {
		return productoRepository.findById(id).orElse(null);
	}

	@Override
	public Producto actualizar(Long idProducto, ProductoInputDTO productoDTO) {
		
		Producto productoEncontrado = buscarPorId(idProducto);
		Marca marca = new Marca();
		marca.setId(productoDTO.idMarca());
		productoEncontrado.setMarca(marca);
		productoEncontrado.setNombreProd(productoDTO.nombreProd());
		productoEncontrado.setDescripcionProd(productoDTO.descripcionProd());
		productoEncontrado.setPrecioProd(productoDTO.precioProd());
		productoEncontrado.setImagenUrl(productoDTO.imagenUrl());
		
		return productoRepository.save(productoEncontrado);
	}

	@Override
	public Producto eliminar(Long idProducto) {
		Integer estadoInactivo = 0;
		Producto productoEncontrado = buscarPorId(idProducto);
		productoEncontrado.setEstado(estadoInactivo);
		return productoRepository.save(productoEncontrado);
	}

}
