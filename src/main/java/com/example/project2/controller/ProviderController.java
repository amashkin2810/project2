package com.example.project2.controller;


import com.example.project2.entity.Item;
import com.example.project2.entity.Provider;
import com.example.project2.entity.ProviderItem;
import com.example.project2.repository.ItemService;
import com.example.project2.repository.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import java.util.*;

@RequestMapping("providers")
@Controller
public class ProviderController {
    private final ProviderService providerService;
    private final ItemService itemService;

    public ProviderController(ProviderService providerService, ItemService itemService) {
        this.providerService = providerService;
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Provider> showNewProvider() {
        return  (providerService.findAll());
     }

    @RequestMapping(value = "provider", method = RequestMethod.GET, produces = "application/json")

    public String showNewProvider(Model model)
    {
        model.addAttribute(new Provider());
        model.addAttribute(new ProviderItem());
        List <Item> itemList = itemService.findAll();
        model.addAttribute("itemlist",itemList);
        return "provider";
    }
    @RequestMapping(value = "provider",method=RequestMethod.POST)
    public Provider addProviderItem(Provider provider, ProviderItem providerItem, @RequestParam(required = false) Item itemChoose) {
        if (providerItem.getQty() > 0) {
            List<Item> itemList = new ArrayList<>();
            itemList.add(itemChoose);
            Provider providerExist = providerService.findByName(provider.getName());
            boolean flag = false;

            if (providerExist != null) {
                flag = true;
                provider.setProviderItems(providerExist.getProviderItems());
            }
            providerItem.setItems(itemList);
            provider.addProviderItem(providerItem);
            if (flag) {
                provider.setId(providerExist.getId());
            }
            providerService.save(provider);
        }
        return provider;
    }


}
