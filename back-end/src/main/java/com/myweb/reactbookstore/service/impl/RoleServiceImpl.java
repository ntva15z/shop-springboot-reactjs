package com.myweb.reactbookstore.service.impl;

import com.myweb.reactbookstore.entity.Role;
import com.myweb.reactbookstore.repository.RoleReponsitory;
import com.myweb.reactbookstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleReponsitory roleReponsitory;

    @Override
    public Optional<Role> findById(Long id) {
        return roleReponsitory.findById(id);
    }
}
