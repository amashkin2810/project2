package com.example.project2.controller;


import com.example.project2.entity.Item;
import com.example.project2.entity.TransferSummary;
import com.example.project2.entity.Warehouse;
import com.example.project2.entity.WarehouseItem;
import com.example.project2.repository.ItemService;
import com.example.project2.repository.ProviderService;
import com.example.project2.repository.WarehouseService;
import com.example.project2.service.TransferService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WarehouseControllerTest {

    private final List<Warehouse> warehouseList = new ArrayList<>();
    private List <TransferSummary> transferSummaryList = new ArrayList<>();
    private List<Item> itemList = new ArrayList<>();
    private Warehouse warehouse;
    private TransferSummary transferSummary;
    private Item item;
    @Before
    public void setUp() throws Exception {
        warehouse = new Warehouse();
        warehouse.setId(1L);
        warehouse.setName("TestWarehouse");
        warehouse.setAddress("address");
        warehouseList.add(warehouse);

        transferSummary = new TransferSummary();
        transferSummary.setIdEntity(1L);
        transferSummary.setIdEntityItem(1L);
        transferSummary.setIdItem(1L);
        transferSummary.setNameEntity("nameEntity");
        transferSummary.setNameItem("nameItem");
        transferSummary.setQty(1);
        transferSummaryList.add(transferSummary);

        item = new Item();
        item.setId(1L);
        item.setName("nameItem");
        item.setPrice(1f);
        item.setDescription("12345");
         itemList.add(item);
    }


    @Test
    public void testListWarehouses() throws Exception {
        WarehouseService warehouseService = mock(WarehouseService.class);
        ProviderService providerService = mock(ProviderService.class);
        ItemService itemService = mock(ItemService.class);
        TransferService transferService = mock(TransferService.class);
        when(warehouseService.findAll()).thenReturn(warehouseList);
        WarehouseController warehouseController = new WarehouseController(warehouseService, providerService, itemService,transferService);

        ExtendedModelMap Model = new ExtendedModelMap();
        Model.addAttribute("listWarehouses", warehouseController.listWarehouses());

        assertEquals(1, Model.size());
    }


    @Test
    public void TestTransferItemToWarehouse() {
        WarehouseService warehouseService = mock(WarehouseService.class);
        ProviderService providerService = mock(ProviderService.class);
        ItemService itemService = mock(ItemService.class);
        TransferService transferService = mock(TransferService.class);

        WarehouseItem warehouseItem = new WarehouseItem();
        warehouseItem.setQty(1);
        warehouseItem.setWarehouse(warehouse);
        warehouseItem.setId(1L);

        when(itemService.findByid(1L)).thenAnswer(new Answer<Item>() {
            @Override
            public Item answer(InvocationOnMock invocation) {
                return item;
            }
        });
        WarehouseController warehouseController = new WarehouseController(warehouseService, providerService, itemService, transferService);
        ReflectionTestUtils.setField(warehouseController, "warehouseService", warehouseService);
        ReflectionTestUtils.setField(warehouseController, "providerService", providerService);
        ReflectionTestUtils.setField(warehouseController, "itemService", itemService);
        ReflectionTestUtils.setField(warehouseController, "transferSummaryList", transferSummaryList);
        ReflectionTestUtils.setField(warehouseController, "transferService", transferService);

        Warehouse newWarehouse = warehouseController.transferItemToWarehouse(warehouseItem,warehouse,"nameItem");

        assertEquals(Long.valueOf(1),newWarehouse.getId());
        assertEquals(1,newWarehouse.getWarehouseItems().size());
        assertEquals(1,newWarehouse.getWarehouseItems().get(0).getQty());
        assertEquals("nameItem",newWarehouse.getWarehouseItems().get(0).getItems().get(0).getName());


    }

}