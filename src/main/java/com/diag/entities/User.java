package com.diag.entities;

import jakarta.persistence.*;
import lombok.Data;

import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString
@Table(name="User")
@DynamicUpdate
@DynamicInsert
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Integer Id;
	@Column(name = "name")
	private String name;
	@Column(name = "email",unique = true)
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;
	@Column(name = "enabled")
	private boolean enabled;
	@Column(name = "imageUrl")
	private String imageUrl;


	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Form> forms=new ArrayList<>();



}
