package com.example.project2.controller;


import com.example.project2.entity.*;
import com.example.project2.entity.TransferSummary;
import com.example.project2.repository.ItemService;
import com.example.project2.repository.ProviderService;
import com.example.project2.repository.WarehouseService;
import com.example.project2.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("warehouses")
@Controller
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final ProviderService providerService;
    private final ItemService itemService;
    private final TransferService transferService;

    private List <TransferSummary> transferSummaryList = new ArrayList<>();

    public WarehouseController(WarehouseService warehouseService, ProviderService providerService, ItemService itemService, TransferService transferService) {
        this.warehouseService = warehouseService;
        this.providerService = providerService;
        this.itemService = itemService;
        this.transferService = transferService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Warehouse> listWarehouses() {
        return  (warehouseService.findAll());
     }

    @RequestMapping(value = "warehouse", method = RequestMethod.GET, produces = "application/json")
    public String showNewWarehouseItem(Model model)
    {
        model.addAttribute(new WarehouseItem());
        List<Warehouse> warehouseList = warehouseService.findAll();
        List <Provider> providerList = providerService.findAll();
        transferSummaryList.clear();
        for(Provider p: providerList){
            if(p.getProviderItems().size()!=0){
                for(ProviderItem pD : p.getProviderItems()){
                   TransferSummary transferSummary = new TransferSummary();
                    transferSummary.setIdEntity(p.getId());
                    transferSummary.setNameEntity(p.getName());
                    transferSummary.setIdEntityItem(pD.getId());
                    transferSummary.setQty(pD.getQty());
                    for(Item item : pD.getItems()){
                        transferSummary.setIdItem(item.getId());
                        transferSummary.setNameItem(item.getName());
                    }
                    transferSummaryList.add(transferSummary);
                }
            }
        }

        model.addAttribute("providerSummarylist", transferSummaryList);
        model.addAttribute("warehouselist",warehouseList);
        return "warehouse";
    }
    @RequestMapping(value = "warehouse",method=RequestMethod.POST)

    public Warehouse transferItemToWarehouse(WarehouseItem warehouseItem,
                                             @RequestParam(required = false) Warehouse warehouseChoose,
                                             @RequestParam(required = false) String providerSummaryChoose)
                                                  {
        for (TransferSummary tS: transferSummaryList){
            if((warehouseItem.getQty() > 0)&&(tS.getNameItem().equals(providerSummaryChoose))
                    && (tS.getQty()>= warehouseItem.getQty())){
                List<Item> itemList = new ArrayList<>();
                Item item = itemService.findByid(tS.getIdItem());
                itemList.add(item);
                warehouseItem.setItems(itemList);
                warehouseChoose.addWarehouseItem(warehouseItem);
                transferService.updateProviderItem(tS.getIdEntity(),tS.getIdEntityItem(),tS.getQty(),warehouseItem.getQty());
                transferService.transfer(warehouseChoose);
                transferSummaryList.clear();
                break;
            }
        }

        return warehouseChoose;
    }

}
