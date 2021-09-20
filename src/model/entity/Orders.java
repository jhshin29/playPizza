package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@SequenceGenerator(name = "ORDERS_SEQ_GENERATOR", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1)

@NamedQuery(query = "select o from Orders o where o.orderId=:orderId order by o.orderId", name = "Order.findByOrderId")
@NamedQuery(query = "select o from Orders o where o.customerId.sId=:customerId order by o.customerId", name = "Order.findByCustomerId")
@NamedQuery(query = "select o from Orders o where o.customerId.customerId=:customerId order by o.orderId", name = "Order.findByCustomerId2")
@NamedQuery(query = "select o from Orders o where o.menuId=:menuId order by o.menuId", name = "Order.findByMenuId")
@NamedQuery(query = "select o from Orders o where o.branchId=:branchId order by o.branchId", name = "Order.findByBranchId")
public class Orders {
	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERS_SEQ_GENERATOR")
	private int orderId;

	@ManyToOne(targetEntity = Customers.class)
	@JoinColumn(name = "customer_Id")
	private Customers customerId;

	@ManyToOne(targetEntity = Menu.class)
	@JoinColumn(name = "menu_Id")
	private Menu menuId;

	@ManyToOne(targetEntity = Branches.class)
	@JoinColumn(name = "branch_Id")
	private Branches branchId;

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", customerId=" + customerId + ", menuId=" + menuId + ", branchId="
				+ branchId + "]";
	}

}