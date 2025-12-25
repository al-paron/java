package org.service.inmemory;

import org.model.Seller;
import org.rep.inmemory.SellerRepo;
import org.service.SellerInterface;

import java.util.List;
import java.util.Optional;

public class SellerService implements SellerInterface {
    private final SellerRepo sellerRepo;

    public SellerService(SellerRepo sellerRepo) {
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

    // Дополнительный метод для поиска продавца по пользователю
    public Optional<Seller> findByUserId(Integer userId) {
        return sellerRepo.findByUserId(userId);
    }
}