package com.unir.ms_inventory_orders.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest  {
    @NotNull(message = "`books` cannot be null")
	@NotEmpty(message = "`books` cannot be empty")
	private List<String> books;
}
