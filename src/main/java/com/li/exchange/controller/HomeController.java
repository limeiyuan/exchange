package com.li.exchange.controller;

import com.li.exchange.dto.RateDTO;
import com.li.exchange.entity.Rate;
import com.li.exchange.service.RateService;
import com.li.exchange.utils.Formater;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiMeiyuan on 2017/3/4.
 * description:
 */
@Controller
public class HomeController {
    @Resource
    private RateService rateService;

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("getRate")
    @ResponseBody
    public List<RateDTO> getRate(String name) {
        if (StringUtils.isEmpty(name)) {
            name = "人民币";
        }
        Rate dest = rateService.getByName(name);
        if (dest == null) {
            dest = rateService.getByName("人民币");
        }
        List<Rate> rates = rateService.query();
        List<RateDTO> rateDTOS = new ArrayList<>();
        for (Rate rate : rates) {
            if (rate.getName().equalsIgnoreCase(name)) {
                continue;
            }
            if (rate.getBought_out() == null || rate.getBought_in() == null) {
                continue;
            }
            RateDTO dto = new RateDTO();
            dto.setName(name + "/" + rate.getName());
            dto.setTitle(name + "/" + rate.getName());
            dto.setIn(Formater.formatDouble(dest.getBought_in() / rate.getBought_in(), 2));
            dto.setOut(Formater.formatDouble(dest.getRate() / rate.getRate(), 2));
            dto.setRate(Formater.formatDouble(rate.getRate(), 2));
            rateDTOS.add(dto);
        }
        return rateDTOS;
    }

    @RequestMapping("getNames")
    @ResponseBody
    public List<String> getNames() {
        return rateService.getNames();
    }

    @RequestMapping("calculate")
    @ResponseBody
    public String calculate(String inName, String outName, Integer count) {
        Rate in = rateService.getByName(inName);
        Rate out = rateService.getByName(outName);
        return Formater.formatDouble(in.getRate() / out.getRate() * count, 2).toString();
    }
}
