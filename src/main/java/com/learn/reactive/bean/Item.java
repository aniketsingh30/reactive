package com.learn.reactive.bean;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Item {

    private String id;
    private String description;
    private Double price;
    private List<SubItem> subitem;
    private Date manufactureDate;
    private Date expiryDate;
}
