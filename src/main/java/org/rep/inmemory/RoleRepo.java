package org.rep.inmemory;

import org.model.Role;
import org.rep.RoleRep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RoleRepo implements RoleRep {
    private final Map<Integer, Role> data = new HashMap<>();

    @Override
    public Optional<Role> findSingle(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Role> findAll() {
        return data.values().stream()
                .filter(role -> !role.isDeleted())
                .toList();
    }

    @Override
    public void save(Role object) {
        data.put(object.getRoleId(), object);
    }

    @Override
    public void delete(Integer id) {
        Role role = data.get(id);
        if (role != null) {
            role.setDeleted(true);
            data.put(id, role);
        }
    }

    // метод для поиска ролей по пользователю
    public List<Role> findByUserId(Integer userId) {
        return data.values().stream()
                .filter(role -> !role.isDeleted()
                        && role.getUserId().equals(userId))
                .toList();
    }

    // метод для проверки, является ли пользователь продавцом
    public boolean isUserSeller(Integer userId) {
        return data.values().stream()
                .anyMatch(role -> !role.isDeleted()
                        && role.getUserId().equals(userId)
                        && "SELLER".equalsIgnoreCase(role.getRole()));
    }
}