package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.MessageException;
import exception.NotExistException;
import model.dto.BranchesDTO;
import model.dto.CustomersDTO;
import model.dto.MenuDTO;
import model.dto.OrdersDTO;

public class Service {

	private static Service instance = new Service();

	private Service() {
	}

	public static Service getInstance() {
		return instance;
	}

	private static CustomersDAO customerDAO = CustomersDAO.getInstance();
	private static MenuDAO menuDAO = MenuDAO.getInstance();
	private static BranchesDAO branchesDAO = BranchesDAO.getInstance();
	private static OrdersDAO ordersDAO = OrdersDAO.getInstance();

	// 고객 ID로 검색
	public CustomersDTO getCustomer(String sId) {
		return customerDAO.getCustomer(sId);
	}

	// 회원가입
	public static void notExistCustomer(String customerId) throws NotExistException, SQLException {
		CustomersDTO customer = customerDAO.getCustomer(customerId);

		if (customer == null) {
			throw new NotExistException("검색하는 회원이 미존재합니다.");
		}
	}

	public boolean addCustomer(CustomersDTO customer) throws NotExistException, MessageException {
		return customerDAO.addCustomer(customer);
	}

	public boolean updateCustomer(String sId, String password, String address, String phone)
			throws NotExistException, SQLException {
		notExistCustomer(sId);
		
		return customerDAO.updateCustomer(sId, password, address, phone);
	}

	public boolean deleteCustomer(String sId) throws NotExistException, SQLException {
		notExistCustomer(sId);
		boolean result = customerDAO.deleteCustomer(sId);

		if (!result) {
			throw new NotExistException("고객 정보 삭제 실패");
		}

		return result;
	}

	// 특정 지점 검색
	public static BranchesDTO getBranch(String name) throws SQLException, NotExistException {
		BranchesDTO branch = branchesDAO.getBranch(name);

		if (branch == null) {
			throw new NotExistException("검색하신 지점이 미존재합니다.");
		}
		
		return branch;
	}

	// 모든 지점 검색
	public ArrayList<BranchesDTO> getAllBranches() throws SQLException, NotExistException {
		ArrayList<BranchesDTO> branchesAll = branchesDAO.getAllBranches();

		if (branchesAll == null) {
			throw new NotExistException("검색하신 지점이 미존재합니다.");
		}

		return branchesAll;
	}

	// Menu
	public static void notExistMenu(String name) throws NotExistException, SQLException {
		MenuDTO menu = menuDAO.getOneMenu(name);

		if (menu == null) {
			throw new NotExistException("찾으신 메뉴가 없습니다.");
		}
	}

	// Menu 이름으로 검색
	public MenuDTO getOneMenu(String name) throws SQLException, NotExistException {
		MenuDTO menu = menuDAO.getOneMenu(name);

		if (menu == null) {
			throw new NotExistException("찾으시는 메뉴가 없습니다.");
		}

		return menu;
	}

	// 모든 Menu 검색
	public ArrayList<MenuDTO> getAllMenu() throws SQLException, NotExistException {
		ArrayList<MenuDTO> menuList = menuDAO.getAllMenu();

		if (menuList == null || menuList.size() == 0) {
			throw new NotExistException("메뉴가 없습니다.");
		}

		return menuList;
	}

	// 주문 정보 추가 메소드 반환
	public static boolean addOrders(String sId, String mName, String bName) throws SQLException {
		return ordersDAO.addOrders(sId, mName, bName);
	}

	// 고객 번호로 주문 내역 검색
	public List<OrdersDTO> getAllOrder(int customerId) throws SQLException, NotExistException {
		List<OrdersDTO> orders = ordersDAO.getAllOrder(customerId);

		if (orders == null || orders.size() == 0) {
			throw new NotExistException("주문내역이 없습니다.");
		}

		return orders;
	}

	public boolean deleteOrder(int orderId) throws NotExistException, SQLException {
		boolean result = ordersDAO.deleteOrder(orderId);
		
		if (!result) {
			throw new NotExistException("주문 삭제 실패");
		}
		
		return result;
	}

	public OrdersDTO findLastOrder() throws NotExistException {
		OrdersDTO order = ordersDAO.findLastOrder();
		
		if (order == null) {
			throw new NotExistException("주문이 없습니다.");
		}
		
		return order;
	}
}