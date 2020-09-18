package com.myweb.reactbookstore.repository;

import com.myweb.reactbookstore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleReponsitory  extends JpaRepository<Role,Long> {
}
