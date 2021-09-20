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
@Builder

@Entity
@SequenceGenerator(name = "MENU_SEQ_GENERATOR", sequenceName = "MENU_SEQ", initialValue = 1, allocationSize = 1)
@NamedQuery(query = "select m from Menu m order by m.menuId", name = "Menu.findAllMenu")
@NamedQuery(query = "select m from Menu m where m.name=:name order by m.name", name = "Menu.findByMenuName")
public class Menu {
	@Id
	@Column(name = "menu_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENU_SEQ_GENERATOR")
	private int menuId;
	private String name;
	private int price;
	private String imgname;

	@OneToMany(mappedBy = "menuId")
	private List<Orders> pizzaOrders;

	@Override
	public String toString() {
		return "메뉴 : " + name + ", 가격 : " + price;
	}

}
