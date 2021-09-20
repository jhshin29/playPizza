package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.dto.MenuDTO;
import model.entity.Menu;
import model.util.DBUtil;

public class MenuDAO {

	private static MenuDAO instance = new MenuDAO();

	private MenuDAO() {
	}

	public static MenuDAO getInstance() {
		return instance;
	}

	public MenuDTO getOneMenu(String name) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		
		MenuDTO menu = null;

		try {
			Menu m = em.createNamedQuery("Menu.findByMenuName", Menu.class).setParameter("name", name)
					.getSingleResult();
			
			menu = new MenuDTO(m.getMenuId(), m.getName(), m.getPrice(), m.getImgname());
		
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			em = null;
		}
		
		return menu;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<MenuDTO> getAllMenu() throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		
		List<Menu> list = null;
		ArrayList<MenuDTO> arr = new ArrayList<MenuDTO>();
		
		try {
			list = em.createNativeQuery("SELECT * FROM Menu", Menu.class).getResultList();
			for (Menu m : list) {
				arr.add(new MenuDTO(m.getMenuId(), m.getName(), m.getPrice(), m.getImgname()));
			}
			
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			em = null;
		}
		
		return arr;
	}
}