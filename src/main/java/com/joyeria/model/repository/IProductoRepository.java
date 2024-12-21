package com.joyeria.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joyeria.model.entity.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long>{
	
	List<Producto> findAllByEstado(Integer estado);

}
