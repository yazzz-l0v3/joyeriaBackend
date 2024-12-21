package com.joyeria.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_producto")
@Data
public class Producto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="nombre")
    private String nombreProd;
	
	@Column(name="descripcion")
    private String descripcionProd;
	
	@Column(name="precio")
    private double precioProd;
	
	@Column(name="imagen")
    private String imagenUrl;
	
	@ManyToOne
	@JoinColumn(name = "id_marca")
	private Marca marca;
}
