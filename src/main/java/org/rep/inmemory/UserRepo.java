package org.rep.inmemory;

import org.model.User;
import org.rep.UserRep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepo implements UserRep {
    private final Map<Integer, User> data = new HashMap<>();

    @Override
    public Optional<User> findSingle(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<User> findAll() {
        return data.values().stream()
                .filter(user -> !user.isDeleted())
                .toList();
    }

    @Override
    public void save(User object) {
        data.put(object.getUserId(), object);
    }

    @Override
    public void delete(Integer id) {
        User user = data.get(id);
        if (user != null) {
            user.setDeleted(true);
            data.put(id, user);
        }
    }

    public Optional<User> findByEmail(String email) {
        return data.values().stream()
                .filter(user -> !user.isDeleted()
                        && email.equals(user.getEmail()))
                .findFirst();
    }

    public boolean emailExists(String email) {
        return data.values().stream()
                .anyMatch(user -> !user.isDeleted()
                        && email.equals(user.getEmail()));
    }
}