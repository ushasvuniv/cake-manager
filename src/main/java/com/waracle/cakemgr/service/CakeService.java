package com.waracle.cakemgr.service;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
            cake.setTitle(updated.getTitle());
            cake.setDescription(updated.getDescription());
            cake.setImage(updated.getImage());
            return repo.save(cake);
        }).orElseThrow(() -> new NoSuchElementException("Cake with ID " + id + " not found"));
    }
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Cake with ID " + id + " not found");
        }
        repo.deleteById(id);
    }
}
