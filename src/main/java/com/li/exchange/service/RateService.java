package com.li.exchange.service;

import com.li.exchange.dao.RateDao;
import com.li.exchange.entity.Rate;
import com.li.exchange.utils.BeanMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by LiMeiyuan on 2017/3/4.
 * description:
 */
@Service
public class RateService {
    @Resource
    private RateDao rateDao;

    public List<Rate> query() {
        Pageable pageable = new PageRequest(0, 8);
        return rateDao.query(pageable);
    }

    public void save(Rate rate) {
        Rate dest = rateDao.getByName(rate.getName());
        if (dest == null) {
            dest = new Rate();
        }
        new BeanMapper().copy(rate, dest);
        rateDao.save(dest);
    }

    public Rate getByName(String name) {
        return rateDao.getByName(name);
    }

    public List<String> getNames() {
        return rateDao.getNames();
    }
}
