package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.Model.Fabric;
import com.example.capstone_3.Model.Merchant;
import com.example.capstone_3.Repository.FabricRepository;
import com.example.capstone_3.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FabricService {

    private final FabricRepository fabricRepository;
    private final MerchantRepository merchantRepository;

    public List<Fabric> getAllFabric() {
        return fabricRepository.findAll();
    }

    public void addFabric(Integer merchantId,Fabric fabric) {
        Merchant m = merchantRepository.findMerchantById(merchantId);
        if(m == null) {
            throw new ApiException("Merchant with id " + merchantId + " not found");
        }
        fabric.setMerchant(m);
        fabricRepository.save(fabric);
    }

    public void updateFabric(Integer id, Fabric fabric){
        Fabric f = fabricRepository.findFabricById(id);
        if(f == null) {
            throw new ApiException("Customer with id " + id + " not found");
        }
        f.setColor(fabric.getColor());
        f.setName(fabric.getName());
        f.setPrice(fabric.getPrice());
        f.setType(fabric.getType());
        f.setPattern(fabric.getPattern());
        f.setDescription(fabric.getDescription());
        fabricRepository.save(f);
    }

    public void deleteFabric(Integer id) {
        Fabric f = fabricRepository.findFabricById(id);
        if(f == null) {
            throw new ApiException("Customer with id " + id + " not found");
        }
        fabricRepository.delete(f);
    }

    public Fabric getFabricById(Integer id) {
        return fabricRepository.findById(id).orElseThrow(() -> new RuntimeException("Fabric not found"));
    }

    public List<Fabric> getFabricByName(String name) {
        return fabricRepository.findFabricByName(name);
    }

    public List<Fabric> getFabricByColor(String color) {
        return fabricRepository.findFabricByColor(color);
    }

    public List<Fabric> getFabricByPattern(String pattern) {
        return fabricRepository.findFabricByPattern(pattern);
    }

    public List<Fabric> getFabricByType(String type) {
        return fabricRepository.findFabricByType(type);
    }
    public List<Fabric> getFabricByPriceRange(double minPrice, double maxPrice) {
        return fabricRepository.findFabricByPriceRange(minPrice, maxPrice);
    }

    public List<Fabric> getFabricByMerchant(Integer merchantId) {
        return fabricRepository.findFabricByMerchantId(merchantId);
    }

    public void assignMerchant(Integer fabricId, Integer merchantId) {
        Fabric f = fabricRepository.findFabricById(fabricId);
        Merchant m = merchantRepository.findMerchantById(merchantId);
        if(f == null || m == null) {
            throw new RuntimeException("can not assign merchant");
        }
        f.setMerchant(m);
        m.getFabrics().add(f);
        fabricRepository.save(f);
        merchantRepository.save(m);
    }
}