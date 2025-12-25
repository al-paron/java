package org.rep;

import org.model.Role;

import java.util.List;

public interface RoleRep extends BaseRep<Role, Integer> {
    List<Role> findByUserId(Integer userId);
    boolean isUserSeller(Integer userId);
}