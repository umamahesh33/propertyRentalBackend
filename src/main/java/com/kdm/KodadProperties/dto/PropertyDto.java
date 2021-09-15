package com.kdm.KodadProperties.dto;

import com.kdm.KodadProperties.model.PropertyOwner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PropertyDto {

    private float price;

    private String locality;//gopireddy nagar

    private String town;// locality and town are for filtering searches

    private String address;

    private String  description;

    private String size;

    private String propertyType;

    private String propertyCategory;

    private PropertyOwner propertyOwner;

}
