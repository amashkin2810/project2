package com.example.project2.controller;


import com.example.project2.entity.*;
import com.example.project2.repository.CustomerService;
import com.example.project2.repository.ItemService;
import com.example.project2.repository.WarehouseService;
import com.example.project2.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("customers")
@Controller
public class CustomerController {
    private final CustomerService customerService;
    private final ItemService itemService;
    private final
    WarehouseService warehouseService;
    private final TransferService transferService;

    private List <TransferSummary> transferSummaryList = new ArrayList<>();

    public CustomerController(CustomerService customerService, ItemService itemService, WarehouseService warehouseService, TransferService transferService) {
        this.customerService = customerService;
        this.itemService = itemService;
        this.warehouseService = warehouseService;
        this.transferService = transferService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Customer> listCustomers() {
        return  (customerService.findAll());
     }

    @RequestMapping(value = "customer", method = RequestMethod.GET, produces = "application/json")
    public String showNewCustomer(Model model)
    {
        model.addAttribute(new Customer());
        model.addAttribute(new CustomerItem());
        List <Warehouse> warehouseList = warehouseService.findAll();
        transferSummaryList.clear();
        for(Warehouse w: warehouseList){
            if(w.getWarehouseItems().size()!=0){
                for (WarehouseItem wi : w.getWarehouseItems()) {
                    TransferSummary transferSummary = new TransferSummary();
                    transferSummary.setIdEntity(w.getId());
                    transferSummary.setNameEntity(w.getName());
                    transferSummary.setIdEntityItem(wi.getId());
                    transferSummary.setQty(wi.getQty());
                    wi.getItems().forEach(item -> {
                        transferSummary.setIdItem(item.getId());
                        transferSummary.setNameItem(item.getName());
                    });
                    transferSummaryList.add(transferSummary);
                }
            }
        }

        model.addAttribute("customerSummarylist", transferSummaryList);
        return "customer";
    }
    @RequestMapping(value = "customer",method=RequestMethod.POST)
    public Customer addCustomerItem(Customer customer, CustomerItem customerItem,
                                  @RequestParam(required = false) String itemChoose) {
        if (customerItem.getQty() > 0) {
            for (TransferSummary transferSummary: transferSummaryList) {
                String idItem = transferSummary.getIdItem().toString();
                if ((idItem.equals(itemChoose)) && (transferSummary.getQty() >= customerItem.getQty())) {
                    List<Item> itemList = new ArrayList<>();
                    Item item = itemService.findByid(transferSummary.getIdItem());
                    itemList.add(item);
                    Customer customerExist = customerService.findByPassport(customer.getPassport());
                    boolean flag = false;
                    if (customerExist != null) {
                        flag = true;
                        customer.setCustomerItems(customerExist.getCustomerItems());
                    }
                    customerItem.setItems(itemList);
                    customer.addCustomerItem(customerItem);
                    if (flag) {
                        customer.setId(customerExist.getId());
                    }

                    transferService.updateWarehouseItem(transferSummary.getIdEntity(),
                            transferSummary.getIdEntityItem(),transferSummary.getQty(),customerItem.getQty());
                    transferService.transfer(customer);
                    transferSummaryList.clear();
                    break;
                }
            }
        }
        return customer;
    }

}
