package com.carecart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateDto {
	private long userId;
	private String firstName;
	private String lastName;
	private String country;
	private String city;
	private String postalCode;
}
