package service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.UserTypeRepository;
import dao.UserType;

/**
 * Servlet implementation class UserTypeServlet
 */
@WebServlet("/UserTypeServlet")
public class UserTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action;
	private UserTypeRepository userTypeRepository;
	private UserType userType;
	private HashMap<Long, UserType> userTypes;
	private String url;
	private RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.userTypes = new HashMap<Long, UserType>();
        this.userTypeRepository = new UserTypeRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
		System.out.println(action);
		if(action.equals("select")) {
			userTypes = userTypeRepository.selectAll();
			request.setAttribute("userTypes", userTypes);
			request.setAttribute("ex", "ex");
			url = "/jsp/selectUserType.jsp";
		}else {
			url = "/jsp/selectUserType.jsp";
		}
		rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
