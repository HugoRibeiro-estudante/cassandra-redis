package com.edu.iftm.cassandraredis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.edu.iftm.cassandraredis.model.Imovel;
import com.edu.iftm.cassandraredis.service.ImovelService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService service;

    @GetMapping
    public ResponseEntity<List<Imovel>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imovel> findById(@PathVariable UUID id) throws Exception{
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Imovel> save(@RequestBody Imovel imovel) throws Exception{
        return ResponseEntity.ok().body(service.save(imovel));
    }

    @PutMapping
    public ResponseEntity<Imovel> update(@RequestBody Imovel imovel) throws Exception{
        return ResponseEntity.ok(service.update(imovel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
