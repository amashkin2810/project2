package com.example.project2.repository;

import com.example.project2.entity.Item;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("itemService")
@Repository
@Transactional
public class ItemServiceImpl implements ItemService{
    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Item> findAll() {

        return (List<Item>) itemRepository.findAll();
    }


    @Override
    @Transactional(readOnly=true)
    public Item findByid(Long id) {
        return itemRepository.findOne(id);
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }
}
