package org.service.inmemory;

import org.model.User;
import org.rep.inmemory.UserRepo;
import org.service.UserInterface;

import java.util.List;
import java.util.Optional;

public class UserService implements UserInterface {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
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
}