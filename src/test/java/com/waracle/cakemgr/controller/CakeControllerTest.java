package com.waracle.cakemgr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.config.TestSecurityConfig;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.service.CakeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@Import(TestSecurityConfig.class)
@WebMvcTest(CakeController.class)
class CakeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CakeService cakeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetAllCakes() throws Exception {
        List<Cake> cakes = List.of(new Cake(1L, "Vanilla", "Vanilla", 8.0));
        Mockito.when(cakeService.getAll()).thenReturn(cakes);

        mockMvc.perform(get("/cakes")
                        .with(httpBasic("admin", "admin123")))
                .andExpect(status().isOk());
    }

    @Test
    void testAddCake() throws Exception {
        Cake cake = new Cake(null, "Red Velvet", "Red", 12.0);
        Cake savedCake = new Cake(1L, "Red Velvet", "Red", 12.0);

        Mockito.when(cakeService.add(Mockito.any())).thenReturn(savedCake);

        mockMvc.perform(post("/cakes")
                        .with(httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cake)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateCake() throws Exception {
        Cake updatedCake = new Cake(1L, "Chocolate Cake", "Chocolate", 15.0);

        Mockito.when(cakeService.update(Mockito.eq(1L), Mockito.any())).thenReturn(updatedCake);

        mockMvc.perform(put("/cakes/1")
                        .with(httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCake)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCake() throws Exception {
        Mockito.doNothing().when(cakeService).delete(1L);

        mockMvc.perform(delete("/cakes/1")
                .with(httpBasic("admin", "admin123")))
                .andExpect(status().isOk());
    }

}