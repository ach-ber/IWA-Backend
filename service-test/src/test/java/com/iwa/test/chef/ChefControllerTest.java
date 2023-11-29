package com.iwa.test.chef;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/*
@WebMvcTest(ChefController.class)
public class ChefControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ChefService chefService;

    private Chef chef;

    @BeforeEach
    void setUp() {
        chef = new Chef(1L, "John Doe");
    }

    @Test
    public void testCreateChef() throws Exception {
        when(chefService.createChef(any())).thenReturn(chef);

        mockMvc.perform(post("/api/chefs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(chef)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(chefService, times(1)).createChef(any());
    }

    @Test
    public void testGetAllChefs() throws Exception {
        when(chefService.getChefs()).thenReturn(Arrays.asList(chef));

        mockMvc.perform(get("/api/chefs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("John Doe"));

        verify(chefService, times(1)).getChefs();
    }

    @Test
    public void testGetChefById() throws Exception {
        when(chefService.getChefById(1L)).thenReturn(Optional.of(chef));

        mockMvc.perform(get("/api/chefs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(chefService, times(1)).getChefById(1L);
    }

    @Test
    public void testGetChefByIdNotFound() throws Exception {
        when(chefService.getChefById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/chefs/2"))
                .andExpect(status().isNotFound());

        verify(chefService, times(1)).getChefById(2L);
    }

    @Test
    public void testUpdateChefById() throws Exception {
        when(chefService.updateChefById(eq(1L), any())).thenReturn(Optional.of(chef));

        mockMvc.perform(put("/api/chefs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(chef)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(chefService, times(1)).updateChefById(eq(1L), any());
    }

    @Test
    public void testUpdateChefByIdNotFound() throws Exception {
        when(chefService.updateChefById(eq(2L), any())).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/chefs/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(chef)))
                .andExpect(status().isNotFound());

        verify(chefService, times(1)).updateChefById(eq(2L), any());
    }

    @Test
    public void testDeleteChefById() throws Exception {
        when(chefService.deleteChefById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/chefs/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Chef deleted successfully"));

        verify(chefService, times(1)).deleteChefById(1L);
    }

    @Test
    public void testDeleteChefByIdNotFound() throws Exception {
        when(chefService.deleteChefById(2L)).thenReturn(false);

        mockMvc.perform(delete("/api/chefs/2"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Chef not found"));

        verify(chefService, times(1)).deleteChefById(2L);
    }
}


 */