package org.service.inmemory;

import org.model.Seller;
import org.rep.SellerRep;
import org.service.SellerInterface;

import java.util.List;
import java.util.Optional;

public class SellerService implements SellerInterface {
    private final SellerRep sellerRepo;

    public SellerService(SellerRep sellerRepo) {
        this.sellerRepo = sellerRepo;
    }

    @Override
    public Optional<Seller> findSingle(Integer id) {
        return sellerRepo.findSingle(id);
    }

    @Override
    public List<Seller> findAll() {
        return sellerRepo.findAll();
    }

    @Override
    public void save(Seller seller) {
        sellerRepo.save(seller);
    }

    @Override
    public void delete(Integer id) {
        sellerRepo.delete(id);
    }

    @Override
    public Optional<Seller> findByUserId(Integer userId) {
        return sellerRepo.findByUserId(userId);
    }
}