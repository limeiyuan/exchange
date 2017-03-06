package com.li.exchange.dto;

/**
 * Created by LiMeiyuan on 2017/3/5.
 * description:
 */
public class RateDTO {
    private String name;
    private String title;
    private Double in;
    private Double out;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getIn() {
        return in;
    }

    public void setIn(Double in) {
        this.in = in;
    }

    public Double getOut() {
        return out;
    }

    public void setOut(Double out) {
        this.out = out;
    }
}
