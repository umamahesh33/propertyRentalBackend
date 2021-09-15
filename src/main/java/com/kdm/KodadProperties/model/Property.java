package com.kdm.KodadProperties.model;


import com.kdm.KodadProperties.enums.PropertyCategory;
import com.kdm.KodadProperties.enums.PropertyStatus;
import com.kdm.KodadProperties.enums.PropertyType;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String propertyId;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private String locality;//gopireddy nagar

    @Column(nullable = false)
    private String town;// locality and town are for filtering searches

    private String address;

    @Column(columnDefinition = "TEXT")
    private String  description;

    private String size;

    private String defImgPath;

    private String allImgsPath;

    @Enumerated(value = EnumType.STRING)
    private PropertyType propertyType;

    @Enumerated(value = EnumType.STRING)
    private PropertyStatus propertyStatus;

    @Enumerated(value = EnumType.STRING)
    private PropertyCategory propertyCategory;

    @OneToOne
    @JoinColumn
    private PropertyOwner propertyOwner;

}
