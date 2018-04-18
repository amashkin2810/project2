package com.example.project2.repository;

import com.example.project2.entity.Warehouse;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service("warehouseService")
@Repository
@Transactional
public class WarehouseServiceImpl implements WarehouseService{

    private WarehouseRepository warehouseRepository;
    private List <Warehouse> warehouseList = new ArrayList<>();

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
        }

    @Override
    @Transactional(readOnly=true)
    public List<Warehouse> findAll() {

        warehouseList = (List<Warehouse>) warehouseRepository.findAll();

        return warehouseList;
    }

    @Override
    @Transactional(readOnly=true)
    public Warehouse findByid(Long id) {
        return warehouseRepository.findOne(id);
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

}
