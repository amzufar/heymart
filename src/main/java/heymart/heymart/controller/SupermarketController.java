package heymart.heymart.controller;

import heymart.heymart.model.Supermarket;
import heymart.heymart.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/supermarket")
public class SupermarketController {

    @Autowired
    private SupermarketService supermarketService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Supermarket>>> getAllSupermarkets() {
        return supermarketService.findAll().thenApply(ResponseEntity::ok);
    }

    @PostMapping("/create")
    public ResponseEntity<Supermarket> createSupermarket(@RequestBody Supermarket supermarket) {
        Supermarket createdSupermarket = supermarketService.save(supermarket);
        return ResponseEntity.ok(createdSupermarket);
    }

    @GetMapping("/{supermarketId}")
    public CompletableFuture<ResponseEntity<Supermarket>> getSupermarketById(@PathVariable Long supermarketId) {
        return supermarketService.findById(supermarketId)
                .thenApply(supermarket -> {
                    if (supermarket != null) {
                        return ResponseEntity.ok(supermarket);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                });
    }

    @PutMapping("edit/{supermarketId}")
    public ResponseEntity<String> editSupermarket(@PathVariable Long supermarketId, @RequestBody Supermarket editedSupermarket) {
        Supermarket supermarket = supermarketService.findById(supermarketId).join();
        if (supermarket != null) {
            editedSupermarket.setSupermarketId(supermarketId);
            supermarketService.save(editedSupermarket);
            return ResponseEntity.ok("Supermarket " + supermarketId + " has been updated");
        } else {
            return ResponseEntity.badRequest().body("Supermarket " + supermarketId + " not found");
        }
    }

    @DeleteMapping("/delete/{supermarketId}")
    public ResponseEntity<String> deleteSupermarket(@PathVariable Long supermarketId) {
        Supermarket supermarket = supermarketService.findById(supermarketId).join();
        if (supermarket != null) {
            supermarketService.deleteById(supermarketId);
            return ResponseEntity.ok("Supermarket " + supermarketId + " has been deleted");
        } else {
            return ResponseEntity.badRequest().body("Supermarket " + supermarketId + " not found");
        }
    }
}
