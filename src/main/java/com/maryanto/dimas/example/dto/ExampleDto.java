package com.maryanto.dimas.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class ExampleDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Product {

        private String id;
        private String name;
        private BigDecimal price;
        private Long qty;
        private String description;
    }
}
