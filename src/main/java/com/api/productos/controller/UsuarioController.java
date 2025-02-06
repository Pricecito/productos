package com.api.productos.controller;

import com.api.productos.model.Usuarios;
import com.api.productos.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuariosService service;

    @GetMapping
    public ResponseEntity<List<Usuarios>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> findById(@PathVariable("id") UUID id){
        var user = service.findById(id).get();
        if(Objects.isNull(user)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);

    }
    @PostMapping
    public ResponseEntity<Usuarios> save(@RequestBody Usuarios usuariio){
        return ResponseEntity.created(URI.create("usuarios/"+service.save(usuariio).getId())).build();
    }

    @PutMapping
    public ResponseEntity<Usuarios> update(@RequestBody Usuarios usuario){
        Usuarios u=service.findById(usuario.getId()).get();
        if(Objects.isNull(u)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.save(u));
    }

    @PatchMapping
    public ResponseEntity<Usuarios> updatePartial(@RequestBody Usuarios usuario){
        Usuarios u=service.findById(usuario.getId()).get();
        if(Objects.isNull(u)){
            return ResponseEntity.notFound().build();
        }
        if(Objects.nonNull(usuario.getNombre())){
            u.setNombre(usuario.getNombre());
        }
        if(Objects.nonNull(usuario.getCorreo())){
            u.setCorreo(usuario.getCorreo());
        }
        if(Objects.nonNull(usuario.getContraseña())){
            u.setContraseña(usuario.getContraseña());
        }
        return ResponseEntity.ok(service.save(u));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id){
        var user = service.findById(id).get();
        if(Objects.isNull(user)){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
