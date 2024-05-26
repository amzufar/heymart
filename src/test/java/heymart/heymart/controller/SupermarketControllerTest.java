package heymart.heymart.controller;

import heymart.heymart.model.Supermarket;
import heymart.heymart.service.SupermarketServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupermarketControllerTest {

    @Mock
    private SupermarketServiceImpl supermarketService;

    @InjectMocks
    private SupermarketController supermarketController;

    @Test
    void testCreateSupermarket() {
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(10L)
                .name("Supermarket A")
                .ownerId(100L)
                .supermarketDescription("Large retail store that offers a wide variety of goods and services")
                .build();

        when(supermarketService.save(any(Supermarket.class))).thenReturn(supermarket);

        ResponseEntity<Supermarket> result = supermarketController.createSupermarket(supermarket);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(supermarket, result.getBody());
        verify(supermarketService, times(1)).save(supermarket);
    }

    @Test
    void testGetSupermarketById() {
        Long supermarketId = 10L;
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(supermarketId)
                .name("Supermarket A")
                .ownerId(100L)
                .supermarketDescription("Large retail store that offers a wide variety of goods and services")
                .build();

        CompletableFuture<Supermarket> future = CompletableFuture.completedFuture(supermarket);
        when(supermarketService.findById(supermarketId)).thenReturn(future);

        ResponseEntity<?> result = supermarketController.getSupermarketById(supermarketId).join();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(supermarketService, times(1)).findById(supermarketId);
    }

    @Test
    void testGetSupermarketByIdNotFound() {
        Long id = 11L;

        when(supermarketService.findById(id)).thenReturn(CompletableFuture.completedFuture(null));

        ResponseEntity<?> result = supermarketController.getSupermarketById(id).join();

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(supermarketService, times(1)).findById(id);
    }

    @Test
    void testGetAllSupermarkets() {
        List<Supermarket> supermarkets = new ArrayList<>();
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(10L)
                .name("Supermarket A")
                .ownerId(100L)
                .supermarketDescription("Large retail store that offers a wide variety of goods and services")
                .build();
        supermarkets.add(supermarket);
        Supermarket supermarket2 = Supermarket.builder()
                .supermarketId(11L)
                .name("Supermarket B")
                .ownerId(101L)
                .supermarketDescription("Small retail store that offers a wide variety of goods and services")
                .build();
        supermarkets.add(supermarket2);

        CompletableFuture<List<Supermarket>> future = CompletableFuture.completedFuture(supermarkets);
        when(supermarketService.findAll()).thenReturn(future);

        ResponseEntity<?> result = supermarketController.getAllSupermarkets().join();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(supermarkets, result.getBody());
        verify(supermarketService, times(1)).findAll();
    }

    @Test
    void testEditSupermarket() {
        Long supermarketId = 10L;
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(supermarketId)
                .name("Supermarket A")
                .ownerId(100L)
                .supermarketDescription("Large retail store that offers a wide variety of goods and services")
                .build();
        Supermarket editedSupermarket = Supermarket.builder()
                .supermarketId(supermarketId)
                .name("Supermarket B")
                .ownerId(120L)
                .supermarketDescription("Small retail store that offers a wide variety of goods and services")
                .build();

        CompletableFuture<Supermarket> future = CompletableFuture.completedFuture(supermarket);
        when(supermarketService.findById(supermarketId)).thenReturn(future);
        when(supermarketService.save(editedSupermarket)).thenReturn(supermarket);

        ResponseEntity<String> result = supermarketController.editSupermarket(supermarketId, editedSupermarket);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(supermarketService, times(1)).findById(supermarketId);
        verify(supermarketService, times(1)).save(editedSupermarket);
    }

    @Test
    void testEditSupermarketNotFound() {
        Long supermarketId = 10L;
        Supermarket editedSupermarket = Supermarket.builder()
                .supermarketId(supermarketId)
                .name("Supermarket B")
                .ownerId(120L)
                .supermarketDescription("Large retail store that offers a wide variety of goods and services")
                .build();
        when(supermarketService.findById(supermarketId)).thenReturn(CompletableFuture.completedFuture(null));

        ResponseEntity<String> result = supermarketController.editSupermarket(supermarketId, editedSupermarket);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Supermarket " + supermarketId + " not found", result.getBody());
        verify(supermarketService, times(1)).findById(supermarketId);
        verify(supermarketService, times(0)).save(editedSupermarket);
    }


    @Test
    void testDeleteSupermarket() {
        Long supermarketId = 10L;
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(supermarketId)
                .name("Supermarket A")
                .ownerId(100L)
                .supermarketDescription("Large retail store that offers a wide variety of goods and services")
                .build();

        CompletableFuture<Supermarket> future = CompletableFuture.completedFuture(supermarket);
        when(supermarketService.findById(supermarketId)).thenReturn(future);
        when(supermarketService.findById(supermarketId)).thenReturn(CompletableFuture.completedFuture(supermarket));


        ResponseEntity<String> result = supermarketController.deleteSupermarket(supermarketId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(supermarketService, times(1)).findById(supermarketId);
        verify(supermarketService, times(1)).deleteById(supermarketId);
    }

    @Test
    void testDeleteSupermarketNotFound() {
        Long supermarketId = 10L;
        when(supermarketService.findById(supermarketId)).thenReturn(CompletableFuture.completedFuture(null));

        ResponseEntity<String> result = supermarketController.deleteSupermarket(supermarketId);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Supermarket " + supermarketId + " not found", result.getBody());
        verify(supermarketService, times(1)).findById(supermarketId);
        verify(supermarketService, times(0)).deleteById(supermarketId);
    }
}