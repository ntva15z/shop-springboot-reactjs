package com.myweb.reactbookstore.repository;

import com.myweb.reactbookstore.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    List<Category> findByNameLikeOrderByName(String name);

    @Query("select c.id,c.name,c.status from Category c")
    List<Object> list();

}
