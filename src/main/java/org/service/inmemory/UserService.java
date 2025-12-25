package org.service.inmemory;

import org.model.User;
import org.rep.UserRep;
import org.service.UserInterface;

import java.util.List;
import java.util.Optional;

public class UserService implements UserInterface {
    private final UserRep userRepo;

    public UserService(UserRep userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> findSingle(Integer id) {
        return userRepo.findSingle(id);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepo.delete(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}