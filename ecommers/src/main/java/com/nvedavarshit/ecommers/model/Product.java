package com.nvedavarshit.ecommers.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@Getter
@Setter
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
