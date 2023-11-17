package com.sistema.inventario.service;

import com.sistema.inventario.exceptions.AlreadyExistsException;
import com.sistema.inventario.exceptions.NotFoundException;
import com.sistema.inventario.model.ItemModel;
import com.sistema.inventario.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public ItemModel createItem (ItemModel item){
        if (itemRepository.findByName(item.getName()).isPresent()) {
            throw new AlreadyExistsException("El item con el nombre " + item.getName() + " ya existe");
        }
        return itemRepository.save(item);
    }

    public ItemModel getItemById(Long id){
        Optional<ItemModel> item = itemRepository.findById(id);
        if(item.isEmpty()){
            throw new NotFoundException("Item no encontrado");
        }
        return item.get();
    }

    public ItemModel updateItem(ItemModel item, Long id){
        if(!itemRepository.existsById(id)){
            throw new NotFoundException("Item no encontrado");
        }
        Optional<ItemModel> existingItemOptional = itemRepository.findByName(item.getName());
        if (existingItemOptional.isPresent() && !existingItemOptional.get().getId().equals(id)) {
            throw new AlreadyExistsException("el item con el nombre " + item.getName() + " ya existe");
        }
            ItemModel itemDB = itemRepository.findById(id).get();
            itemDB.setName(item.getName());
            itemDB.setDescription(item.getDescription());
            itemDB.setPrice(item.getPrice());
            itemDB.setProvider(item.getProvider());
            return itemRepository.save(itemDB);
    }
    public Boolean deleteItemById(Long id){
        if(itemRepository.existsById(id)){
            itemRepository.deleteById(id);
            return true;
        }else{
            throw new NotFoundException("Item no encontrado");
        }

    }

    public List<ItemModel> findAllItems(){
        List<ItemModel> items = itemRepository.findAll();
        if(items.isEmpty()){
            throw new NotFoundException("No hay items encontrados");
        }
        return  items;
    }


}
