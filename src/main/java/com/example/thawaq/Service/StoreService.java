package com.example.thawaq.Service;

import com.example.thawaq.Api.ApiException;
import com.example.thawaq.Model.*;
import com.example.thawaq.Repository.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreAdminRepository storeAdminRepository;
    private final RatingService ratingService;
    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;
    private final AddressRepository addressRepository;


    public List<Store> getStores()
    {
        return storeRepository.findAll();
    }

    public void addStore(Integer sID,Store store) // v2
    {
        StoreAdmin sa = storeAdminRepository.findStoreAdminById(sID);
        if(sa == null)
        {
            throw new ApiException("Store not found");
        }
        storeRepository.save(store);
        sa.setStore(store);
        storeAdminRepository.save(sa);
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

    //Find Store by Type of Acivity //Jana v2
    public List<Store> findStoreByTypeOfActivity(String typeOfActivity){
        List<Store> stores = storeRepository.findStoreByTypeOfActivity(typeOfActivity);
        if(stores.isEmpty()){
            return null;}
        return stores;
    }

    public List<Store> getBestQualityForCafesByName(String name){
        List<Store> stores = storeRepository.findStoreByName(name);
        List<Store> chosenStores = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("CAFE")){
                chosenStores.add(store);
            }
        }
        Collections.sort(chosenStores, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {

                return Double.compare(ratingService.CalculateAverageQualityStore(s2.getId()), ratingService.CalculateAverageQualityStore(s1.getId()));
            }
        });
        return chosenStores;
    }
    public List<Store> getBestQualityForRestaurantsByName(String name){
        List<Store> stores = storeRepository.findStoreByName(name);
        List<Store> chosenStores = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("RESTAURANT")){
                chosenStores.add(store);
            }
        }
        Collections.sort(chosenStores, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {

                return Double.compare(ratingService.CalculateAverageQualityStore(s2.getId()), ratingService.CalculateAverageQualityStore(s1.getId()));
            }
        });
        return chosenStores;
    }
    public List<Store> getBestQualityForBothByName(String name){
        List<Store> stores = storeRepository.findStoreByName(name);
        List<Store> chosenStores = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("BOTH")){
                chosenStores.add(store);
            }
        }
        Collections.sort(chosenStores, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {

                return Double.compare(ratingService.CalculateAverageQualityStore(s2.getId()), ratingService.CalculateAverageQualityStore(s1.getId()));
            }
        });
        return chosenStores;
    }

    public List<Store> getBestQualityForCafesByDishType(String categoryName){
        Category category = categoryRepository.findCategoryByName(categoryName);
        List<Menu> menus = menuRepository.findMenuByCategory(category);
        List<Store> stores = new ArrayList<>();
        for (Menu menu : menus){
            stores.add(menu.getBranch().getStore());
        }
        List<Store> chosenStores = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("CAFE")){
                chosenStores.add(store);
            }
        }
        Collections.sort(chosenStores, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {

                return Double.compare(ratingService.CalculateAverageQualityStore(s2.getId()), ratingService.CalculateAverageQualityStore(s1.getId()));
            }
        });
        return chosenStores;
    }
    public List<Store> getBestQualityForRestaurantByDishType(String categoryName){
        Category category = categoryRepository.findCategoryByName(categoryName);
        List<Menu> menus = menuRepository.findMenuByCategory(category);
        List<Store> stores = new ArrayList<>();
        for (Menu menu : menus){
            stores.add(menu.getBranch().getStore());
        }
        List<Store> chosenStores = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("RESTAURANT")){
                chosenStores.add(store);
            }
        }
        Collections.sort(chosenStores, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {

                return Double.compare(ratingService.CalculateAverageQualityStore(s2.getId()), ratingService.CalculateAverageQualityStore(s1.getId()));
            }
        });
        return chosenStores;
    }
    public List<Store> getBestQualityForBothByDishType(String categoryName){
        Category category = categoryRepository.findCategoryByName(categoryName);
        List<Menu> menus = menuRepository.findMenuByCategory(category);
        List<Store> stores = new ArrayList<>();
        for (Menu menu : menus){
            stores.add(menu.getBranch().getStore());
        }
        List<Store> chosenStores = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("BOTH")){
                chosenStores.add(store);
            }
        }
        Collections.sort(chosenStores, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {

                return Double.compare(ratingService.CalculateAverageQualityStore(s2.getId()), ratingService.CalculateAverageQualityStore(s1.getId()));
            }
        });
        return chosenStores;
    }

    public List<Store> getBestQualityForCafesByCity(String City){
        List<Address> addresses = addressRepository.findAddressByCity(City);
        List<Store> stores = new ArrayList<>();
        for (Address address : addresses) {
            stores.add(address.getBranch().getStore());
        }
        List<Store> chosenStores = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("CAFE")){
                chosenStores.add(store);
            }
        }
        Collections.sort(chosenStores, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {

                return Double.compare(ratingService.CalculateAverageQualityStore(s2.getId()), ratingService.CalculateAverageQualityStore(s1.getId()));
            }
        });
        return chosenStores;
    }
    public List<Store> getBestQualityForRestaurantByCity(String City){
        List<Address> addresses = addressRepository.findAddressByCity(City);
        List<Store> stores = new ArrayList<>();
        for (Address address : addresses) {
            stores.add(address.getBranch().getStore());
        }
        List<Store> chosenStores = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("RESTAURANT")){
                chosenStores.add(store);
            }
        }
        Collections.sort(chosenStores, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {

                return Double.compare(ratingService.CalculateAverageQualityStore(s2.getId()), ratingService.CalculateAverageQualityStore(s1.getId()));
            }
        });
        return chosenStores;
    }
    public List<Store> getBestQualityForBothByCity(String City){
        List<Address> addresses = addressRepository.findAddressByCity(City);
        List<Store> stores = new ArrayList<>();
        for (Address address : addresses) {
            stores.add(address.getBranch().getStore());
        }
        List<Store> chosenStores = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("BOTH")){
                chosenStores.add(store);
            }
        }
        Collections.sort(chosenStores, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {

                return Double.compare(ratingService.CalculateAverageQualityStore(s2.getId()), ratingService.CalculateAverageQualityStore(s1.getId()));
            }
        });
        return chosenStores;
    }
    ////v3
    public List<Store> getLowestCostForCafesByName(String name) {
        List<Store> stores = storeRepository.findStoreByName(name);
        List<Store> Stores1 = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("CAFE")) {
                Stores1.add(store);
            }

        }
        Collections.sort(Stores1, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {

                return Double.compare(ratingService.CalculateAverageCostStore(s2.getId()), ratingService.CalculateAverageCostStore(s1.getId()));

            }

        });
        return Stores1;
    }
    ////v3
    public List<Store> getLowestCostRestaurantsByName(String name) {
        List<Store> stores = storeRepository.findStoreByName(name);
        List<Store> Stores1 = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("RESTAURANT")) {
                Stores1.add(store);
            }
        }
        Collections.sort(Stores1, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {

                return Double.compare(ratingService.CalculateAverageCostStore(s2.getId()), ratingService.CalculateAverageCostStore(s1.getId()));
            }
        });
        return Stores1;
    }
    ////v3
    public List<Store> getLowestCostForBothByName(String name) {
        List<Store> stores = storeRepository.findStoreByName(name);
        List<Store> Stores1 = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("BOTH")) {
                Stores1.add(store);
            }
        }
        Collections.sort(Stores1, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {
                return Double.compare(ratingService.CalculateAverageCostStore(s2.getId()), ratingService.CalculateAverageCostStore(s1.getId()));

            }
        });
        return Stores1;

    }
    ////v3
    public List<Store> getLowestCostForCafesByDishType(String categoryName) {
        Category category = categoryRepository.findCategoryByName(categoryName);
        List<Menu> menus = menuRepository.findMenuByCategory(category);
        List<Store> stores = new ArrayList<>();
        for (Menu menu : menus) {
            stores.add(menu.getBranch().getStore());
        }
        List<Store> Stores1 = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("CAFE")) {
                Stores1.add(store);
            }
        }
        Collections.sort(Stores1, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {
                return Double.compare(ratingService.CalculateAverageCostStore(s2.getId()), ratingService.CalculateAverageCostStore(s1.getId()));
            }
        });
        return Stores1;

    }
    ////v3
    public List<Store> getLowestCostForRestaurantByDishType(String categoryName){
        Category category = categoryRepository.findCategoryByName(categoryName);
        List<Menu> menus = menuRepository.findMenuByCategory(category);
        List<Store> stores = new ArrayList<>();
        for (Menu menu : menus){
            stores.add(menu.getBranch().getStore());
        }
        List<Store> Stores1 = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("RESTAURANT")){
                Stores1.add(store);
            }
        }
        Collections.sort(Stores1, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {
                return Double.compare(ratingService.CalculateAverageCostStore(s2.getId()), ratingService.CalculateAverageCostStore(s1.getId()));
            }});
        return Stores1;


            }
    ////v3
    public List<Store> getLowestCostForBothByDishType(String categoryName){
        Category category = categoryRepository.findCategoryByName(categoryName);
        List<Menu> menus = menuRepository.findMenuByCategory(category);
        List<Store> stores = new ArrayList<>();
        for (Menu menu : menus){
            stores.add(menu.getBranch().getStore());
        }
        List<Store> Stores1 = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("BOTH")){
                Stores1.add(store);
            }
        }
        Collections.sort(Stores1, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {
                return Double.compare(ratingService.CalculateAverageCostStore(s2.getId()), ratingService.CalculateAverageCostStore(s1.getId()));

            }});
        return Stores1;
}            ////v3
    public List<Store>  getLowestCostForCafesByCity(String City) {
        List<Address> addresses = addressRepository.findAddressByCity(City);
        List<Store> stores = new ArrayList<>();
        for (Address address : addresses) {
            stores.add(address.getBranch().getStore());
        }
        List<Store> Stores1 = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("CAFE")) {
                Stores1.add(store);
            }
        }
        Collections.sort(Stores1, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {
                return Double.compare(ratingService.CalculateAverageCostStore(s2.getId()), ratingService.CalculateAverageCostStore(s1.getId()));
            }
        });
        return Stores1;
    } ////v3
    public List<Store>  getLowestCostForRestaurantByCity(String City) {
        List<Address> addresses = addressRepository.findAddressByCity(City);
        List<Store> stores = new ArrayList<>();
        for (Address address : addresses) {
            stores.add(address.getBranch().getStore());
        }
        List<Store> Stores1 = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("RESTAURANT")) {
                Stores1.add(store);
            }
        }
        Collections.sort(Stores1, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {
                return Double.compare(ratingService.CalculateAverageCostStore(s2.getId()), ratingService.CalculateAverageCostStore(s1.getId()));

            }
        });
        return Stores1;
    }       ////v3
    public List<Store> getLowestCostForBothByCity(String City){
        List<Address> addresses = addressRepository.findAddressByCity(City);
        List<Store> stores = new ArrayList<>();
        for (Address address : addresses) {
            stores.add(address.getBranch().getStore());
        }
        List<Store> Stores1 = new ArrayList<>();
        for (Store store : stores) {
            if (store.getTypeOfActivity().equals("BOTH")){
                Stores1.add(store);
            }
        }
        Collections.sort(Stores1, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {
                return Double.compare(ratingService.CalculateAverageCostStore(s2.getId()), ratingService.CalculateAverageCostStore(s1.getId()));

            }});
        return Stores1;



}}

