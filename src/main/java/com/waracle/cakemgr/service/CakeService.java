package com.waracle.cakemgr.service;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CakeService {
    private final CakeRepository repo;

    public CakeService(CakeRepository repo) {
        this.repo = repo;
    }

    public List<Cake> getAll() { return repo.findAll(); }
    public Cake add(Cake cake) { return repo.save(cake); }
    public Cake update(Long id, Cake updated) {
        return repo.findById(id).map(cake -> {
            cake.setName(updated.getName());
            cake.setFlavor(updated.getFlavor());
            cake.setPrice(updated.getPrice());
            return repo.save(cake);
        }).orElseThrow(() -> new RuntimeException("Cake not found"));
    }
    public void delete(Long id) { repo.deleteById(id); }
}
