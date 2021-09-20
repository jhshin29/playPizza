package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Service;
import model.dto.BranchesDTO;
import model.dto.CustomersDTO;
import model.dto.MenuDTO;
import model.dto.OrdersDTO;

@WebServlet("/pizza")
public class Controller extends HttpServlet {

	private static Service service = Service.getInstance();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");

		try {
			if (command.equals("branchesAll")) {// 모든 지점 검색
				branchesAll(request, response);
			} else if (command.equals("branch")) {// 특정 지점 검색
				branch(request, response);
			} else if (command.equals("menuAll")) {// 모든 Menu 검색
				menuAll(request, response);
			} else if (command.equals("menu")) {// Menu 이름으로 검색
				menu(request, response);
			} else if (command.equals("customerInsert")) {
				customerInsert(request, response);
			} else if (command.equals("customer")) {
				customer(request, response);
			} else if (command.equals("customerUpdateReq")) {
				customerUpdateReq(request, response);
			} else if (command.equals("customerUpdate")) {
				customerUpdate(request, response);
			} else if (command.equals("customerDelete")) {
				customerDelete(request, response);
			} else if (command.equals("login")) {
				loginOk(request, response);
			} else if (command.equals("logout")) {
				logout(request, response);
			} else if (command.equals("ordersAll")) {
				ordersAll(request, response);
			} else if (command.equals("orderDelete")) {
				orderDelete(request, response);
			} else if (command.equals("ordersInsert")) {// 주문 정보 추가
				ordersInsert(request, response);
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			s.printStackTrace();
		}

	}


	// 특정 지점 검색
	public void branch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			BranchesDTO b = service.getBranch(request.getParameter("name"));
			if (b != null) {
				request.setAttribute("branch", b);
				url = "branch/branchDetail.jsp";
			} else {
				request.setAttribute("errorMsg", "존재하지 않는 지점입니다.");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 모든 지점 검색 - 검색된 데이터 출력 화면[branchList.jsp]
	public void branchesAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("branchesAll", service.getAllBranches());
			url = "branch/branchList.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 모든 Menu 검색
	public void menuAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("menuAll", service.getAllMenu());
			url = "menu/menuList.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// Menu 이름으로 검색
	public void menu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			MenuDTO menu = service.getOneMenu(request.getParameter("name"));
			if (menu != null) {
				request.setAttribute("menu", menu);
				url = "menu/menuDetail.jsp";
			} else {
				request.setAttribute("errorMsg", "존재하지 않는 메뉴.");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 회원 가입
	private void customerInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;

		String sId = request.getParameter("sId");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		if (sId != null && sId.length() != 0 && password != null && address != null && phone != null) {

			CustomersDTO customer = new CustomersDTO(sId, password, address, phone);
			try {
				boolean result = service.addCustomer(customer);
				if (result) {
					request.setAttribute("customer", customer);
					request.setAttribute("successMsg", "가입 완료");
					url = "/index.jsp";
				} else {
					request.setAttribute("errorMsg", "다시 시도하세요");
				}
			} catch (Exception s) {
				request.setAttribute("errorMsg", s.getMessage());
			}
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	private void customer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;

		try {
			CustomersDTO c = service.getCustomer(request.getParameter("sId"));
			if (c != null) {
				request.setAttribute("customer", c);
				url = "customer/mypage.jsp";
			} else {
				request.setAttribute("errorMsg", "존재하지 않는 고객 정보입니다.");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	public void customerUpdateReq(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("customer", service.getCustomer(request.getParameter("sId")));
			url = "customer/customerUpdate.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	public void customerUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			boolean result = service.updateCustomer(request.getParameter("sId"), request.getParameter("password"),
					request.getParameter("address"), request.getParameter("phone"));
			if (result) {
				request.setAttribute("customer", service.getCustomer(request.getParameter("sId")));
				url = "customer/mypage.jsp";
			} else {
				request.setAttribute("errorMsg", "수정 실패");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

	public void customerDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			boolean result = service.deleteCustomer(request.getParameter("sId"));
			if (result) {
				url = "customer/logout.jsp";
			} else {
				request.setAttribute("errorMsg", "삭제 실패");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 로그인
	private void loginOk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		try {
			CustomersDTO c = service.getCustomer(request.getParameter("id"));

			if (c == null) {
				response.sendRedirect("customer/loginRetry.jsp");
			} else {
				if (pwd.equals(c.getPassword())) {
					session.setAttribute("id", id);
					response.sendRedirect("index.jsp");
				} else {
					response.sendRedirect("customer/loginRetry.jsp");
				}
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("id");
		response.sendRedirect(request.getContextPath());
	}
	
	public void orderDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {

			boolean result = service.deleteOrder(Integer.parseInt(request.getParameter("orderId")));

			if (result) {
				url = "/index.jsp";
			} else {
				request.setAttribute("errorMsg", "삭제 실패");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 고객 번호로 주문 정보 검색
	public void ordersAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		
		try {
			List<OrdersDTO> orders = service.getAllOrder(Integer.parseInt(request.getParameter("customerId")));
			
			request.setAttribute("orders", orders);
			url = "orders/ordersList.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 주문 정보 추가
	protected void ordersInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "showError.jsp";

		String sId = (String) request.getSession().getAttribute("id");
		String mName = request.getParameter("menu");
		String bName = request.getParameter("branch");

		// 해킹등으로 불합리하게 요청도 될수 있다는 가정하에 모든 데이터가 제대로 전송이 되었는지를 검증하는 로직
		if (sId != null && sId.length() != 0 && mName != null && bName != null) {
			try {
				request.getParameter("orderInsert");
				boolean result = service.addOrders(sId, mName, bName);

				OrdersDTO order = service.findLastOrder();
				if (result) {
					request.setAttribute("orderInsert", order);
					url = "orders/orderInfo.jsp";
				} else {
					request.setAttribute("errorMsg", "다시 시도하세요");
				}
			} catch (Exception s) {
				request.setAttribute("errorMsg", s.getMessage());
			}
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}
