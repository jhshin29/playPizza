package model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Entity
@Builder
@SequenceGenerator(name = "CUSTOMER_SEQ_GENERATOR", sequenceName = "CUSTOMER_SEQ", initialValue = 1, allocationSize = 1)
@NamedQuery(name = "Customer.findBySId", query = "select c from Customers c where c.sId = :sId")
public class Customers {
	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ_GENERATOR")
	private int customerId;
	private String sId;
	private String password;
	private String address;
	private String phone;

	@OneToMany(mappedBy = "customerId")
	private List<Orders> pizzaOrders;

	@Override
	public String toString() {
		return "Customers [customerId=" + customerId + ", sId=" + sId + ", password=" + password + ", address="
				+ address + ", phone=" + phone + "]";
	}

}
