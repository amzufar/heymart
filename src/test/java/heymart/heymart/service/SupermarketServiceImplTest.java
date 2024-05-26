package heymart.heymart.service;

import heymart.heymart.model.Supermarket;
import heymart.heymart.repository.SupermarketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupermarketServiceImplTest {

    @Mock
    private SupermarketRepository supermarketRepository;

    @InjectMocks
    private SupermarketServiceImpl supermarketService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() throws ExecutionException, InterruptedException {
        Long supermarketId = 10L;
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(supermarketId)
                .name("Supermarket A")
                .ownerId(100L)
                .supermarketDescription("Large retail store that offers a wide variety of goods and services")
                .build();

        when(supermarketRepository.findById(supermarketId)).thenReturn(Optional.of(supermarket));

        Supermarket result = supermarketService.findById(supermarketId).get();

        assertEquals(supermarket, result);
        verify(supermarketRepository, times(1)).findById(supermarketId);
    }

    @Test
    void testFindAll() {
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

        when(supermarketRepository.findAll()).thenReturn(supermarkets);

        CompletableFuture<List<Supermarket>> future = supermarketService.findAll();
        List<Supermarket> result = future.join();
        assertEquals(supermarkets, result);
        verify(supermarketRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(10L)
                .name("Supermarket A")
                .ownerId(100L)
                .supermarketDescription("Large retail store that offers a wide variety of goods and services")
                .build();

        when(supermarketRepository.save(supermarket)).thenReturn(supermarket);

        Supermarket result = supermarketService.save(supermarket);
        assertEquals(supermarket, result);
        verify(supermarketRepository, times(1)).save(supermarket);
    }

    @Test
    void testDeleteById() throws ExecutionException, InterruptedException {
        Long supermarketId = 10L;
        doNothing().when(supermarketRepository).deleteById(supermarketId);

        supermarketService.deleteById(supermarketId);
        verify(supermarketRepository, times(1)).deleteById(supermarketId);
    }
}