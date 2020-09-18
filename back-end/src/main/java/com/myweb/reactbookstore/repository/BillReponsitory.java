package com.myweb.reactbookstore.repository;

import com.myweb.reactbookstore.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillReponsitory extends JpaRepository<Bill,Long> {

    @Query("select b.activedate,sum(b.total) from Bill b where b.status=2 group by b.activedate")
    List<Object[]> totalByDate();


}
