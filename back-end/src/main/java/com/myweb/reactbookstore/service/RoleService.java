package com.myweb.reactbookstore.service;


import com.myweb.reactbookstore.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findById(Long id);
}
