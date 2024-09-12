package com.example.thawaq.Service;

import com.example.thawaq.Model.Store;
import com.example.thawaq.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private StoreRepository storeRepository;

    public List<Store> getStores()
    {
        return storeRepository.findAll();
    }

    public void addStore(Store store)
    {
        storeRepository.save(store);
    }

    public void updateStore(Integer id,Store store)
    {
        Store s = storeRepository.findStoreById(id);
        if(s == null)
        {
//            throw new ApiException("Store not found");
        }
        s.setName(store.getName());
        s.setTypeOfActivity(store.getTypeOfActivity());
        s.setPhoneNumber(store.getPhoneNumber());
        s.setCommercialRegister(store.getCommercialRegister());
        s.setRestaurantImage(store.getRestaurantImage());
        storeRepository.save(s);
    }
    public void deleteStore(Integer id)
    {
        if(storeRepository.findStoreById(id) == null)
        {
//            throw new ApiException("Store not found");
        }
        storeRepository.deleteById(id);
    }

}
