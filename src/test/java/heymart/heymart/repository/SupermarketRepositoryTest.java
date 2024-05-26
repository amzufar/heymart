package heymart.heymart.repository;

import heymart.heymart.model.Supermarket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupermarketRepositoryTest {

    @Mock
    private SupermarketRepository supermarketRepository;

    @Test
    void testFindById() {
        Long supermarketId = 10L;
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(supermarketId)
                .name("Supermarket A")
                .ownerId(100L)
                .supermarketDescription("Large retail store that offers a wide variety of goods and services")
                .build();

        when(supermarketRepository.findById(supermarketId)).thenReturn(Optional.of(supermarket));

        Optional<Supermarket> result = supermarketRepository.findById(supermarketId);
        assertEquals(supermarket, result.get());
        assertEquals(supermarket.getSupermarketId(), result.get().getSupermarketId());
    }

    @Test
    void testSave() {
        Long supermarketId = 10L;
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(supermarketId)
                .name("Supermarket A")
                .ownerId(100L)
                .supermarketDescription("Large retail store that offers a wide variety of goods and services")
                .build();

        when(supermarketRepository.save(supermarket)).thenReturn(supermarket);

        Supermarket result = supermarketRepository.save(supermarket);
        assertEquals(supermarket, result);
        assertEquals(supermarket.getSupermarketId(), result.getSupermarketId());
    }

}