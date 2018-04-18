package com.example.project2.controller;


import com.example.project2.entity.Item;
import com.example.project2.repository.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("items")
@Controller
public class ItemController {

    private  ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Item> listItems() {
        return (itemService.findAll());
    }

    @RequestMapping(value = "item", method = RequestMethod.GET)
    public String showNewItem(Model model) {
        model.addAttribute(new Item());
        return "item";
    }

    @RequestMapping(value = "item", method = RequestMethod.POST)
    public Item createItem(Item item) {
        itemService.save(item);
        return item;
    }
}
