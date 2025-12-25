package org.service;

import org.model.User;

import java.util.Optional;

public interface UserInterface extends BaseInterface<User, Integer> {
    Optional<User> findByEmail(String email);
}