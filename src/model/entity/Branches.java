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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name = "BRANCHES_SEQ_GENERATOR", sequenceName = "BRANCHES_SEQ", initialValue = 1, allocationSize = 1)
@NamedQuery(name = "Branch.findByName", query = "select b from Branches b where b.name LIKE :name")
public class Branches {
	@Id
	@Column(name = "branch_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BRANCHES_SEQ_GENERATOR")
	private int branchId;
	private String name;
	private String address;
	private String phone;

	@OneToMany(mappedBy = "branchId")
	private List<Orders> pizzaOrders;

	@Override
	public String toString() {
		return "지점id : " + branchId + ", 지점명 : " + name + ", 주소 : " + address + ", 전화번호 : " + phone;
	}

}
