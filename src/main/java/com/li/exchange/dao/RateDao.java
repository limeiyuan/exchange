package com.li.exchange.dao;

import com.li.exchange.entity.Rate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LiMeiyuan on 2017/3/4.
 * description:
 */
@Repository
public interface RateDao extends JpaRepository<Rate, Long> {
    @Query("select t FROM Rate t where t.rate is not null order by t.order desc,t.name")
    List<Rate> query(Pageable pageable);

    @Query("select r from Rate r where r.name=?1")
    Rate getByName(String name);

    @Query("select r.name from Rate r where r.rate is not null order by r.order asc")
    List<String> getNames();
}
