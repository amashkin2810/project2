package com.example.project2.repository;

import com.example.project2.entity.Provider;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service("providerService")
@Repository
@Transactional
public class ProviderServiceImpl implements ProviderService{
    private ProviderRepository providerRepository;
    private EntityManager em;

    public ProviderServiceImpl(ProviderRepository providerRepository, EntityManager em) {
        this.providerRepository = providerRepository;
        this.em = em;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Provider> findAll() {

        return (List<Provider>) providerRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Provider findByid(Long id) {
        return providerRepository.findOne(id);
    }

    @Override
    public Provider findByName(String name) {
        TypedQuery<Provider> query = em.createNamedQuery("Provider.findByName", Provider.class)
                .setParameter("name", name);

        if (query.getResultList().size() > 0) {
            return query.getSingleResult();
        } else {
            return null;
        }
    }

    @Override
    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }

}
