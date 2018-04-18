package com.example.project2.controller;

import com.example.project2.entity.*;
import com.example.project2.repository.CustomerService;
import com.example.project2.repository.ItemService;
import com.example.project2.repository.ProviderService;
import com.example.project2.repository.WarehouseService;
import com.example.project2.service.TransferService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerControllerTest {
    private List <TransferSummary> transferSummaryList = new ArrayList<>();
    private List<Item> itemList = new ArrayList<>();
    private Customer customer;
    private TransferSummary transferSummary;
    private Item item;
    @Before
    public void setUp() throws Exception {
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

        customer = new Customer();
        customer.setId(1L);
        customer.setFirstname("ivan");
        customer.setLastname("ivanov");
        customer.setPassport("123456");

    }


    @Test
    public void testAddCustomerItem() {
        WarehouseService warehouseService = mock(WarehouseService.class);
        ItemService itemService = mock(ItemService.class);
        TransferService transferService = mock(TransferService.class);
        CustomerService customerService = mock(CustomerService.class);

        CustomerItem customerItem = new CustomerItem();
        customerItem.setId(1L);
        customerItem.setCustomer(customer);
        customerItem.setQty(1);

        when(itemService.findByid(1L)).thenAnswer(new Answer<Item>() {
            @Override
            public Item answer(InvocationOnMock invocation) {
                return item;
            }
        });

        CustomerController customerController = new CustomerController(customerService, itemService, warehouseService, transferService);
        ReflectionTestUtils.setField(customerController, "warehouseService", warehouseService);
        ReflectionTestUtils.setField(customerController, "itemService", itemService);
        ReflectionTestUtils.setField(customerController, "transferSummaryList", transferSummaryList);
        ReflectionTestUtils.setField(customerController, "transferService", transferService);

        Customer newCustomer = customerController.addCustomerItem(customer,customerItem,"1");

        assertEquals(Long.valueOf(1),newCustomer.getId());
        assertEquals(1,newCustomer.getCustomerItems().size());
        assertEquals("nameItem",newCustomer.getCustomerItems().get(0).getItems().get(0).getName());

        }
}