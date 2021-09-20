package model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entity.Branches;
import model.entity.Customers;
import model.entity.Menu;
import model.entity.Orders;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrdersDTO {
	private int orderId;
	private Customers customerId;
	private Menu menuId;
	private Branches branchId;
	
	public Orders toEntity() {
		return Orders.builder().orderId(orderId).customerId(customerId).menuId(menuId).branchId(branchId).build();
	}

}
