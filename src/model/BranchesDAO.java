package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import model.dto.BranchesDTO;
import model.entity.Branches;
import model.util.DBUtil;


public class BranchesDAO {
	
	private static BranchesDAO instance = new BranchesDAO();
	
	private BranchesDAO() {}
	
	public static BranchesDAO getInstance() {
		return instance;
	}
	
	// 특정 지점 검색
	public BranchesDTO getBranch(String name) throws SQLException{
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		
		BranchesDTO branch = null;

		try {
			Branches b = em.createNamedQuery("Branch.findByName", Branches.class).setParameter("name", "%" + name + "%").getSingleResult();
			
			branch = new BranchesDTO(b.getBranchId(), b.getName(), b.getAddress(), b.getPhone());
		
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			em = null;
		}
		
		return branch;
	}
	
	//모든 지점 검색
	public ArrayList<BranchesDTO> getAllBranches() throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		
		List<Branches> list = null;
		ArrayList<BranchesDTO> branches = new ArrayList<>();
		
		try {
			list = em.createNativeQuery("SELECT * FROM BRANCHES").getResultList();
			Iterator it = list.iterator();
			while(it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				branches.add(new BranchesDTO(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3])));
			}
		
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			em = null;
		}
		
		return branches;
	}
}
