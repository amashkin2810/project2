package com.example.project2.controller;

import com.example.project2.entity.Item;
import com.example.project2.repository.ItemService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTest {


    @Test
    public void testCreateItem() {
        final Item newItem = new Item();
        newItem.setId(1L);
        newItem.setName("phone");
        newItem.setPrice(99f);
        newItem.setDescription("nokia");

        ItemService itemService = mock(ItemService.class);
        when(itemService.save(newItem)).thenAnswer(new Answer<Item>() {
            @Override
            public Item answer(InvocationOnMock invocation) {
                return newItem;
            }
        });
        ItemController itemController = new ItemController(itemService);
        ReflectionTestUtils.setField(itemController,"itemService",itemService);
        Item item = itemController.createItem(newItem);
        assertEquals(Long.valueOf(1),item.getId());
        assertEquals("phone",item.getName());
        assertEquals(Float.valueOf( 99f),item.getPrice());
        assertEquals("nokia",item.getDescription());
    }
}