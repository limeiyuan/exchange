package com.li.exchange.controller;

import com.li.exchange.entity.Rate;
import com.li.exchange.service.RateService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by LiMeiyuan on 2017/3/6.
 * description:
 */
@Controller
public class ConsoleController {
    @Resource
    private RateService rateService;

    @RequestMapping("list")
    @ResponseBody
    public List<Rate> list() {
        Pageable page = new PageRequest(0, 100);
        return rateService.query(page);
    }

    @RequestMapping("console")
    public String consoleIndex() {
        return "list";
    }
}
