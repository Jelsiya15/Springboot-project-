package com.TNSIF.sm.stores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StoreService {
	@Autowired
	 private final StoreRepository storeRepository;
    
	    public StoreService(StoreRepository storeRepository) {
	        this.storeRepository = storeRepository;
	    }

	    public List<Stores> getAllStores() {
	        return storeRepository.findAll();
	    }

	    public Optional<Stores> getStoreById(int id) {
	        return storeRepository.findById(id);
	    }

	    public Stores createStore(Stores store) {
	        return storeRepository.save(store);
	    }

	   
	    public Stores updateStore(Integer id, Stores updatedStore) {
	        return storeRepository.findById(id)
	                .map(store -> {
	                    store.setStorename(updatedStore.getStorename());
	                    store.setContact_Info(updatedStore.getContact_Info());
	                    store.setOperating_hours(updatedStore.getOperating_hours());
	                    store.setLocation(updatedStore.getLocation());
	                    store.setCategory(updatedStore.getCategory());
	                    return storeRepository.save(store);
	                }).orElseThrow(() -> new ResourceNotFoundException("Store not found with id " + id));
	    }

	    public void deleteStore(int id) {
	        storeRepository.deleteById(id);
	    }
	}