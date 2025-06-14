package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.service.CakeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cakes")
public class CakeController {

    private final CakeService service;

    public CakeController(CakeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cake> list() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Cake> add(@RequestBody Cake cake) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(cake));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cake> update(@PathVariable Long id, @RequestBody Cake cake) {
        return ResponseEntity.ok(service.update(id, cake));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("deleted successfully");
    }
}
