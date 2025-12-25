package org.rep.inmemory;

import org.model.Seller;
import org.rep.SellerRep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SellerRepo implements SellerRep {
    private final Map<Integer, Seller> data = new HashMap<>();

    @Override
    public Optional<Seller> findSingle(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Seller> findAll() {
        return data.values().stream()
                .filter(seller -> !seller.isDeleted())
                .toList();
    }

    @Override
    public void save(Seller object) {
        data.put(object.getSellerId(), object);
    }

    @Override
    public void delete(Integer id) {
        Seller seller = data.get(id);
        if (seller != null) {
            seller.setDeleted(true);
            data.put(id, seller);
        }
    }

    public Optional<Seller> findByUserId(Integer userId) {
        return data.values().stream()
                .filter(seller -> !seller.isDeleted()
                        && seller.getUserId().equals(userId))
                .findFirst();
    }
}