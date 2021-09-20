package model;

import javax.persistence.EntityManager;

import model.dto.CustomersDTO;
import model.entity.Customers;
import model.util.DBUtil;

public class CustomersDAO {
	
	private static CustomersDAO instance = new CustomersDAO();
	
	private CustomersDAO() {}
	
	public static CustomersDAO getInstance() {
		return instance;
	}
	
	public CustomersDTO getCustomer(String sId) {
		EntityManager em = DBUtil.getEntityManager();
		
		CustomersDTO customer = null;
		
		try {
			Customers c = em.createNamedQuery("Customer.findBySId", Customers.class).setParameter("sId", sId).getSingleResult();
			
			customer = new CustomersDTO(c.getCustomerId(), c.getSId(), c.getPassword(), c.getAddress(), c.getPhone());
		
		} catch(Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			em = null;
		}
		
		return customer;
	}

	public boolean addCustomer(CustomersDTO customer) {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		
		boolean result = false;

		try {
			em.persist(customer.toEntity());
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

	public boolean updateCustomer(String sId, String password, String address, String phone) {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		
		boolean result = false;
		
		try {
			em.createNamedQuery("Customer.findBySId", Customers.class).setParameter("sId", sId).getSingleResult().setPassword(password);;
			em.createNamedQuery("Customer.findBySId", Customers.class).setParameter("sId", sId).getSingleResult().setAddress(address);
			em.createNamedQuery("Customer.findBySId", Customers.class).setParameter("sId", sId).getSingleResult().setPhone(phone);

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

	public boolean deleteCustomer(String sId) {
		EntityManager em = DBUtil.getEntityManager();	
		em.getTransaction().begin();
		
		boolean result = false;
		
		try {
			em.remove(em.createNamedQuery("Customer.findBySId", Customers.class).setParameter("sId", sId).getSingleResult());

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
}
