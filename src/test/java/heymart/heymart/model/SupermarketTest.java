package heymart.heymart.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SupermarketTest {
    Supermarket supermarket;
    private Long managerId;

    @BeforeEach
    void setUp() {
        managerId = 100L;
        Long supermarketId = 10L;
        this.supermarket = Supermarket.builder()
                .supermarketId(supermarketId)
                .name("Supermarket A")
                .ownerId(managerId)
                .supermarketDescription("Large retail store that offers a wide variety of goods and services")
                .build();
    }

    @Test
    void testGetId() {
        assertEquals(10L, supermarket.getSupermarketId());
    }

    @Test
    void testGetName() {
        assertEquals("Supermarket A", supermarket.getName());
    }

    @Test
    void testGetOwnerId() {
        assertEquals(100L, supermarket.getOwnerId());
    }

    @Test
    void testSetName() {
        supermarket.setName("Supermarket X");
        assertEquals("Supermarket X", supermarket.getName());
    }

    @Test
    void testGetSupermarketDescription() {
        assertEquals("Large retail store that offers a wide variety of goods and services", supermarket.getSupermarketDescription());
    }

    @Test
    void testSetDescription() {
        supermarket.setSupermarketDescription("Small retail store that offers a wide variety of goods and services");
        assertEquals("Small retail store that offers a wide variety of goods and services", supermarket.getSupermarketDescription());
    }
}
