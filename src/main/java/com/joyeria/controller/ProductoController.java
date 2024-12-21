package com.joyeria.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyeria.model.dto.ProductoInputDTO;
import com.joyeria.model.entity.Producto;
import com.joyeria.model.service.IProductoService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/producto")
@AllArgsConstructor
public class ProductoController {

    private IProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> listar() {
        return productoService.listar();
    }

    @GetMapping("/obtener/{id}")
    public Producto buscar(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Producto> agregar(@RequestBody ProductoInputDTO productoDTO) {
    	Producto nuevoProducto = productoService.registrar(productoDTO);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody ProductoInputDTO productoDTO) {
    	Producto productoEncontrado = productoService.buscarPorId(id);
        if (productoEncontrado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Producto productoActualizado = productoService.actualizar(id, productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Producto>eliminar(@PathVariable Long id) {
    	Producto productoEncontrado = productoService.buscarPorId(id);
        if (productoEncontrado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Producto productoEliminado = productoService.eliminar(id);
        return ResponseEntity.ok(productoEliminado);
    }
}
