package com.carecart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductUpdateDto {
	private long productId;
	private String productName;
	private String brand;
	private long categoryId;
	private double salePrice;
	private double costPrice;
}
