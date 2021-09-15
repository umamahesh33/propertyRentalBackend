package com.kdm.KodadProperties.dto;


import com.kdm.KodadProperties.enums.PropertyCategory;
import com.kdm.KodadProperties.enums.PropertyStatus;
import com.kdm.KodadProperties.enums.PropertyType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
public class FullPropertyDetailsDto {

    private String propertyId;

    private float price;

    private String locality;

    private String town;

    private String address;

    private String  description;

    private String size;

    private PropertyType propertyType;

    private List<String> allImgsPaths;

}
