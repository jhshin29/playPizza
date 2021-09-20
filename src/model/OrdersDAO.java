package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.dto.OrdersDTO;
import model.entity.Branches;
import model.entity.Customers;
import model.entity.Menu;
import model.entity.Orders;
import model.util.DBUtil;

public class OrdersDAO {

	private static OrdersDAO instance = new OrdersDAO();

	private OrdersDAO() {
	}

	public static OrdersDAO getInstance() {
		return instance;
	}

	// 고객 번호로 주문 정보 검색
	public List<OrdersDTO> getAllOrder(int customerId) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();

		List<Orders> list = null;
		List<OrdersDTO> orders = new ArrayList<OrdersDTO>();

		try {
			list = em.createNamedQuery("Order.findByCustomerId2", Orders.class).setParameter("customerId", customerId).getResultList();
			for (Orders o : list) {
				orders.add(new OrdersDTO(o.getOrderId(), o.getCustomerId(), o.getMenuId(), o.getBranchId()));
			}

		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}

		return orders;
	}

	public boolean addOrders(String sId, String mName, String bName) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();

		boolean result = false;

		OrdersDTO newOrder = new OrdersDTO();
		Customers customer = null;
		Menu menu = null;
		Branches branch = null;

		try {
			customer = (Customers) em.createNamedQuery("Customer.findBySId").setParameter("sId", sId).getSingleResult();
			menu = (Menu) em.createNamedQuery("Menu.findByMenuName").setParameter("name", mName).getSingleResult();
			branch = (Branches) em.createNamedQuery("Branch.findByName").setParameter("name", bName).getSingleResult();

			newOrder.setCustomerId(customer);
			newOrder.setMenuId(menu);
			newOrder.setBranchId(branch);

			em.persist(newOrder.toEntity());
			em.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			em = null;
		}

		return result;
	}

	public boolean deleteOrder(int orderId) {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();

		boolean result = false;

		try {
			em.remove(em.createNamedQuery("Order.findByOrderId", Orders.class).setParameter("orderId", orderId).getSingleResult());

			em.getTransaction().commit();

			result = true;

		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			em = null;
		}

		return result;
	}

	public OrdersDTO findLastOrder() {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();

		OrdersDTO order = null;

		try {
			Orders o = (Orders) em.createNativeQuery("SELECT * FROM ORDERS WHERE ORDER_ID = (SELECT MAX(ORDER_ID) FROM ORDERS)",Orders.class).getSingleResult();

			order = new OrdersDTO(o.getOrderId(), o.getCustomerId(), o.getMenuId(), o.getBranchId());

		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			em = null;
		}

		return order;
	}

}