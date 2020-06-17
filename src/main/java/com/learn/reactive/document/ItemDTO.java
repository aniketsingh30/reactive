package com.learn.reactive.document;


import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    @Id
    private String id;
    private String description;
    private Double price;
    private List<SubItemDTO> subitem;
    private Date manufactureDate;
    private Date expiryDate;
}
