package heymart.heymart.service;


import heymart.heymart.model.Supermarket;
import heymart.heymart.repository.SupermarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SupermarketServiceImpl implements SupermarketService {
    private final SupermarketRepository supermarketRepository;

    @Autowired
    public SupermarketServiceImpl(SupermarketRepository supermarketRepository) {
        this.supermarketRepository = supermarketRepository;
    }

    @Async
    @Override
    public CompletableFuture<Supermarket> findById(Long supermarketId) {
        return CompletableFuture.completedFuture(supermarketRepository.findById(supermarketId).orElse(null));
    }

    @Async
    @Override
    public CompletableFuture<List<Supermarket>> findAll() {
        return CompletableFuture.completedFuture(supermarketRepository.findAll());
    }

    @Async
    @Override
    public Supermarket save(Supermarket supermarket) {
        return supermarketRepository.save(supermarket);
    }

    @Async
    @Override
    public void deleteById(Long supermarketId) {
        supermarketRepository.deleteById(supermarketId);
    }
}
