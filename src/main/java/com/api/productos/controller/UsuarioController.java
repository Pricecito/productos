package com.api.productos.controller;

import com.api.productos.model.Usuarios;
import com.api.productos.service.UsuariosService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@Slf4j
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
        service.save(usuariio);
        URI location=URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(usuariio.getNombre()).toUriString());
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Usuarios> update(@RequestBody Usuarios usuario){
        if(Objects.isNull(service.findById(usuario.getId()).get())){
            return ResponseEntity.notFound().build();
        }
        service.save(usuario);
        log.info("Usuario actualizado: "+usuario.toString());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<Usuarios> updatePartial(@RequestBody Usuarios usuario){
        Optional<Usuarios> u = service.findById(usuario.getId());
        if(u.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Usuarios user = u.get();
        if(Objects.nonNull(usuario.getNombre())){
            user.setNombre(usuario.getNombre());
        }
        if(Objects.nonNull(usuario.getCorreo())){
            user.setCorreo(usuario.getCorreo());
        }
        if(Objects.nonNull(usuario.getContraseña())){
            user.setContraseña(usuario.getContraseña());
        }
        service.save(user);
        return ResponseEntity.noContent().build();
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
