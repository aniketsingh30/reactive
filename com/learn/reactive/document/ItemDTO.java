package com.example.juint.document;

import java.sql.Date;

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
    private SubItemDTO subitem;
    private Date manufactureDate;
    private Date expiryDate;
}
