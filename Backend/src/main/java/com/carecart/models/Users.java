package com.carecart.models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	private String firstName;
	private String lastName;
	private String country;
	private String city;
	private String postalCode;
	private String email;
	private String password;
	private String role;//either USER or ADMIN
	private LocalDate dateOfBirth;
	private LocalDate dateOfEntered=LocalDate.now();
	@OneToMany(mappedBy = "userId")
	private Set<Orders> orders;
	public Users(String firstName, String lastName, String country, String city, String postalCode, String email,
			String password, LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return userId == other.userId && Objects.equals(email, other.email);
	}
	@Override
	public int hashCode() {
		return Objects.hash(userId, email);
	}
	
	
}
