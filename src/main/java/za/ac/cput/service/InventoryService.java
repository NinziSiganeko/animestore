package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Inventory;
import za.ac.cput.repository.InventoryRepository;

import java.util.List;

@Service
public class InventoryService implements IInventoryService{

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory create(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory read(Long aLong) {
        return inventoryRepository.findById(aLong).orElse(null);
    }

    @Override
    public Inventory update(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public boolean delete(Long aLong) {
        inventoryRepository.deleteById(aLong);
        return !inventoryRepository.existsById(aLong);
    }

    @Override
    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }
}
