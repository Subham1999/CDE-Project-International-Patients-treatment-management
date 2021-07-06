package com.cts.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// -------------------------------
// Lombok

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
// -------------------------------
// JPA

@Entity
@Table(name = "user_details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "user_name", unique = true, updatable = false)
	private String userName;

	@Column(length = 5)
	private String password;

	private String roles;
}
