package com.li.exchange.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by LiMeiyuan on 2017/3/4.
 * description:
 */
@Entity
@Table(name = "rate")
public class Rate {
    @Id
    @Column
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(strategy = "uuid", name="system-uuid")
    private String id;
    @Column
    private String name;
    @Column
    private String code;
    @Column
    private Double rate;
    @Column
    private Double bought_in;
    @Column
    private Double bought_out;
    @Column(name="order_r")
    private Integer order;
    @Column
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getBought_in() {
        return bought_in;
    }

    public void setBought_in(Double bought_in) {
        this.bought_in = bought_in;
    }

    public Double getBought_out() {
        return bought_out;
    }

    public void setBought_out(Double bought_out) {
        this.bought_out = bought_out;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
