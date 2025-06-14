package com.waracle.cakemgr.service;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CakeServiceTest {

    @Mock
    private CakeRepository cakeRepository;

    @InjectMocks
    private CakeService cakeService;

    @Test
    void testAddCake() {
        Cake cake = new Cake(null, "Chocolate", "Chocolate", 10.0);
        Cake savedCake = new Cake(1L, "Chocolate", "Chocolate", 10.0);

        Mockito.when(cakeRepository.save(cake)).thenReturn(savedCake);

        Cake result = cakeService.add(cake);
        Assertions.assertEquals(savedCake.getId(), result.getId());
    }

    @Test
    void testGetAllCakes() {
        List<Cake> cakes = List.of(
                new Cake(1L, "Vanilla", "Vanilla", 8.5),
                new Cake(2L, "Strawberry", "Strawberry", 9.0)
        );

        Mockito.when(cakeRepository.findAll()).thenReturn(cakes);

        List<Cake> result = cakeService.getAll();
        Assertions.assertEquals(2, result.size());
    }
}