package com.kdm.KodadProperties.dto;

import com.kdm.KodadProperties.enums.PropertyCategory;
import com.kdm.KodadProperties.enums.PropertyStatus;
import com.kdm.KodadProperties.enums.PropertyType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Builder
public class MinPropertyDetailsDto implements Serializable {

    private String propertyId;

    private float price;

    private String locality;//gopireddy nagar

    private String town;// locality and town are for filtering searches

    private String size;

    private String defImgPath;

}
