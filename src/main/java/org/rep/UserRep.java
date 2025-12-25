package org.rep;

import org.model.User;

import java.util.Optional;

public interface UserRep extends BaseRep<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean emailExists(String email);
}