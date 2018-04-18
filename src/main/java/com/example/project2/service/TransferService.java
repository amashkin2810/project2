package com.example.project2.service;

import com.example.project2.entity.*;
import com.example.project2.repository.CustomerService;
import com.example.project2.repository.ProviderService;
import com.example.project2.repository.WarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Service
public class TransferService {

    private
    WarehouseService warehouseService;
    private
    ProviderService providerService;
    private
    CustomerService customerService;
    private Provider provider;
    private Warehouse warehouse;

    public TransferService(WarehouseService warehouseService, ProviderService providerService, CustomerService customerService) {
        this.warehouseService = warehouseService;
        this.providerService = providerService;
        this.customerService = customerService;
    }

    @Transactional(propagation= Propagation.REQUIRES_NEW)
   public void transfer( Warehouse warehouse){
     providerService.save(provider);
     warehouseService.save(warehouse);
   }
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void transfer(Customer customer){
        customerService.save(customer);
        warehouseService.save(warehouse);
    }

    public void updateProviderItem(Long IdEntity, Long IdEntityItem, int qty, int warehouseItemQty){
        int qtyCurrent;
         provider = providerService.findByid(IdEntity);
        for(ProviderItem pI : provider.getProviderItems()){
            if(pI.getId().equals(IdEntityItem)){
                qtyCurrent = qty - warehouseItemQty;
                pI.setQty(qtyCurrent);
            }
        }
    }

    public void updateWarehouseItem(Long IdEntity, Long IdEntityItem, int qty, int customerItemQty){
        int qtyCurrent;
         warehouse = warehouseService.findByid(IdEntity);
        for(WarehouseItem wI : warehouse.getWarehouseItems()){
            if(wI.getId().equals(IdEntityItem)){
                 qtyCurrent = qty - customerItemQty;
                wI.setQty(qtyCurrent);
            }
        }
    }

}
