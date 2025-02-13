package com.api.productos.controller;

import com.api.productos.dto.CategoriasDTO;
import com.api.productos.model.Categorias;
import com.api.productos.service.CategoriasService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
    @Autowired
    private CategoriasService service;

    @GetMapping
    private ResponseEntity<List<CategoriasDTO>> findAll() {
        List<Categorias> listCategorias = service.findAll();
        if (listCategorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<CategoriasDTO> listDTO = listCategorias.stream().map(categoria -> CategoriasDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .build()).toList();
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CategoriasDTO> findById(@PathVariable Long id) {
        Categorias categoria = service.findById(id).get();
        if(Objects.isNull(categoria)){
            return ResponseEntity.notFound().build();
        }
        CategoriasDTO categoriaDTO = CategoriasDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .build();
        return ResponseEntity.ok(categoriaDTO);
    }

    @PostMapping
    private ResponseEntity<CategoriasDTO> create(@RequestBody Categorias categoria) {
        service.save(categoria);
        URI location=URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getNombre()).toUriString());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<CategoriasDTO> update(@RequestBody Categorias categoria) {
        Categorias categoriaEnt = service.findById(categoria.getId()).get();
        if (Objects.isNull(categoriaEnt)) {
            return ResponseEntity.notFound().build();
        }
        CategoriasDTO categoriaDTO= CategoriasDTO.builder()
                .id(categoriaEnt.getId())
                .nombre(categoriaEnt.getNombre()).build();
        service.save(categoria);
        return ResponseEntity.ok(categoriaDTO);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        Categorias categoria = service.findById(id).get();
        if (Objects.isNull(categoria)) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(categoria.getId());
        return ResponseEntity.noContent().build();
    }
}
