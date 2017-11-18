package com.yzcx.api.vo.highchat.pie;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Series_pie_data{
         private String name;
         private double y;
         private double ldgnumber;

    public double getLdgnumber() {
        return ldgnumber;
    }

    public void setLdgnumber(double ldgnumber) {
        this.ldgnumber = ldgnumber;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }