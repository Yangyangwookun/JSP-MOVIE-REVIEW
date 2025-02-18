package service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.User;
import repository.ArticleRepository;
import repository.ArticleTypeRepository;
import repository.UserRepository;
import repository.ViewArticleRepository;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action;
	private String email;
	private String password;
	private String name;
	private int age;
	private String phoneNumber;
	private User user;
	private UserRepository userRepository;
	private RequestDispatcher rd;
	private ArticleRepository articleRepository;
	private ViewArticleRepository viewArticleRepository;
	private ArticleTypeRepository articleTypeRepository;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        this.userRepository = new UserRepository();
        this.articleRepository = new ArticleRepository();
        this.viewArticleRepository = new ViewArticleRepository();
        this.articleTypeRepository = new ArticleTypeRepository();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
		if(action.equals("signUp")) {
			response.sendRedirect("/jspSqlProject/jsp/signUp.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
		if(action.equals("signUp")) {
			email = request.getParameter("email");
			password = request.getParameter("password");
			name = request.getParameter("name");
			age = Integer.parseInt(request.getParameter("age"));
			phoneNumber = request.getParameter("phoneNumber");
			
			user = new User(email, password, name, age, phoneNumber, 1);
			if(userRepository.insert(user)) {
				System.out.println("유저등록 성공");
				response.sendRedirect("/jspSqlProject/html/index.html");
			}else {
				System.out.println("유저등록 실패");
				response.sendRedirect("/jspSqlProject/jsp/signUp.jsp");
				return;
			}
		}else if(action.equals("login")) {
			email = request.getParameter("email");
			password = request.getParameter("password");
			
			user = userRepository.selectByEmail(email);
			if(user == null) {
				System.out.println("해당 유저가 없습니다.");
				response.sendRedirect("/jspSqlProject/html/index.html");
				return;
			}
			if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
				System.out.println("로그인 성공"); 
				request.setAttribute("articleTypeText", "전체");
				request.setAttribute("articleTypes", articleTypeRepository.selectAll());
				request.setAttribute("user", userRepository.selectById(user.getId()));
				request.setAttribute("viewArticles", viewArticleRepository.selectAll());
				rd = getServletContext().getRequestDispatcher("/jsp/main.jsp");
				rd.forward(request, response);
			}else {
				System.out.println("로그인 실패");
				response.sendRedirect("/jspSqlProject/html/index.html");
				return;
			}
		}
//		doGet(request, response);
	}

}
