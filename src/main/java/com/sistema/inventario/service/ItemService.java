package com.sistema.inventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistema.inventario.model.Item;
import com.sistema.inventario.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(Item item){
        return itemRepository.save(item);
    }

    public Item getItemById(Long id){
        return itemRepository.findById(id).get();
    }

    public Item updateItem(Item item, long id){
        if (itemRepository.existsById(id)){
            Item itemBd = itemRepository.findById(id).get();
            itemBd.setName(item.getName());
            item.setCategory(item.getCategory());
            item.setDescription(item.getDescription());
            item.setPrice(item.getPrice());
            item.setProvider(item.getProvider());
            item.setStock(item.getStock());
            return itemRepository.save(itemBd);
        }
        return null;
    }

    public Boolean deleteItemById(Long id){
        if (itemRepository.existsById(id)){
            itemRepository.deleteById(id);;
            return true;
        }
        return false;
    }
    public List<Item> findAllItem(){
        return (List<Item>) itemRepository.findAll();
    }
}





