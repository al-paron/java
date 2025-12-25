package org.service;

import org.model.Role;

import java.util.List;

public interface RoleInterface extends BaseInterface<Role, Integer> {
    List<Role> findByUserId(Integer userId);
    boolean isUserSeller(Integer userId);
}