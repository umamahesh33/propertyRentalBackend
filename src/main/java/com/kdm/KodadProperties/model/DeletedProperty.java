package com.kdm.KodadProperties.model;

import com.kdm.KodadProperties.enums.PropertyStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeletedProperty {

    @Id
    @GeneratedValue
    private long id;

    private float price;

    private String propertyId;

    private String locality;

    private String town;

    @Enumerated(value = EnumType.STRING)
    private PropertyStatus propertyStatus;

    @CreationTimestamp
    private Date deletedTime;

}
