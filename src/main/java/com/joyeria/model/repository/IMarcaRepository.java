package com.joyeria.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joyeria.model.entity.Marca;

public interface IMarcaRepository extends JpaRepository<Marca, Long> {

}
