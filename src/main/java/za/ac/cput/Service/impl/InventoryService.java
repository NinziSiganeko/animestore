package za.ac.cput.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.Repository.InventoryRepository;
import za.ac.cput.domain.Inventory;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.ProductStatus;
import za.ac.cput.Service.IInventoryService;

import java.util.Optional;

@Service
public class InventoryService implements IInventoryService {

    private final InventoryRepository repository;

    @Autowired
    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Inventory create(Inventory inventory) {
        return repository.save(inventory);
    }

    @Override
    public Inventory read(String id) {
        try {
            Long inventoryId = Long.parseLong(id);
            Optional<Inventory> inventory = repository.findById(inventoryId);
            return inventory.orElse(null);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid inventory ID format: " + id);
        }
    }

    @Override
    public Inventory update(Inventory inventory) {
        if (inventory.getInventoryId() != null && repository.existsById(inventory.getInventoryId())) {
            return repository.save(inventory);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        try {
            Long inventoryId = Long.parseLong(id);
            if (repository.existsById(inventoryId)) {
                repository.deleteById(inventoryId);
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid inventory ID format: " + id);
        }
    }

    @Override
    public Inventory getInventoryByProductId(String productId) {
        try {
            Long prodId = Long.parseLong(productId);
            return repository.findAll().stream()
                    .filter(inv -> inv.getProduct() != null && prodId.equals(inv.getProduct().getProductId()))
                    .findFirst()
                    .orElse(null);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid product ID format: " + productId);
        }
    }

    @Override
    public Inventory updateInventoryStatus(String inventoryId, String status) {
        try {
            Long invId = Long.parseLong(inventoryId);
            Optional<Inventory> optionalInventory = repository.findById(invId);
            if (optionalInventory.isPresent()) {
                Inventory inventory = optionalInventory.get();
                try {
                    ProductStatus productStatus = ProductStatus.valueOf(status.toUpperCase());
                    inventory = new Inventory.Builder()
                            .copy(inventory)
                            .setStatus(productStatus)
                            .build();
                    return repository.save(inventory);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid product status: " + status);
                }
            }
            return null;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid inventory ID format: " + inventoryId);
        }
    }
}
