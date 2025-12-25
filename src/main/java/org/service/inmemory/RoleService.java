package org.service.inmemory;

import org.model.Role;
import org.rep.RoleRep;
import org.service.RoleInterface;

import java.util.List;
import java.util.Optional;

public class RoleService implements RoleInterface {
    private final RoleRep roleRepo;

    public RoleService(RoleRep roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Optional<Role> findSingle(Integer id) {
        return roleRepo.findSingle(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void delete(Integer id) {
        roleRepo.delete(id);
    }

    @Override
    public List<Role> findByUserId(Integer userId) {
        return roleRepo.findByUserId(userId);
    }

    @Override
    public boolean isUserSeller(Integer userId) {
        return roleRepo.isUserSeller(userId);
    }
}