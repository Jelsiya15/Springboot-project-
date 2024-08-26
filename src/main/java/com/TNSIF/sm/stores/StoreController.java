package com.TNSIF.sm.stores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
public class StoreController {
	@Autowired
	private final StoreService storeService;

    
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<Stores>> getAllStores() {
        List<Stores> stores = storeService.getAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stores> getStoreById(@PathVariable int id) {
        Optional<Stores> store = storeService.getStoreById(id);
        return store.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    

    @PostMapping
    public ResponseEntity<Stores> createStore(@RequestBody Stores store) {
        Stores createdStore = storeService.createStore(store);
        return new ResponseEntity<Stores>(createdStore, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
   
    public ResponseEntity<Stores> updateStore(@PathVariable int id, @RequestBody Stores storeDetails) {
        Stores updatedStore = storeService.updateStore(id, storeDetails);
        return ResponseEntity.ok(updatedStore);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable int id) {
        storeService.deleteStore(id);
        
    }
}
