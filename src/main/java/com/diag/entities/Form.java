package com.diag.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Data
@Entity
@Table(name="Form")
@ToString
@DynamicUpdate
@DynamicInsert
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fId")
    private Integer fId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "imageUrl")
    private String imageUrl;

    @ManyToOne
    private User user;

}
