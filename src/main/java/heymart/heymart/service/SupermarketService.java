package heymart.heymart.service;

import heymart.heymart.model.Supermarket;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SupermarketService {
    CompletableFuture<Supermarket> findById(Long supermarketId);
    CompletableFuture<List<Supermarket>> findAll();
    Supermarket save(Supermarket supermarket);
    void deleteById(Long supermarketId);
}