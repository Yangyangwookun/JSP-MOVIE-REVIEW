package service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.ArticleRepository;
import repository.ArticleTypeRepository;
import repository.UserRepository;
import repository.ViewArticleRepository;
import dao.ArticleType;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action;
	private long tblUserId;
	private RequestDispatcher rd;
	private ArticleRepository articleRepository;
	private ViewArticleRepository viewArticleRepository;
	private UserRepository userRepository;
	private ArticleTypeRepository articleTypeRepository;
	private String articleTypeText;
	private HashMap<Long, ArticleType> articleTypes;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        this.articleRepository = new ArticleRepository();
        this.viewArticleRepository = new ViewArticleRepository();
        this.userRepository = new UserRepository();
        this.articleTypeRepository = new ArticleTypeRepository();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
		if(action.equals("home")) {
			tblUserId = Long.parseLong(request.getParameter("tblUserId"));
			request.setAttribute("articleTypeText", "전체");
			request.setAttribute("articleTypes", articleTypeRepository.selectAll());
			request.setAttribute("user", userRepository.selectById(tblUserId));
			request.setAttribute("viewArticles", viewArticleRepository.selectAll());
			rd = getServletContext().getRequestDispatcher("/jsp/main.jsp");
			rd.forward(request, response);
		}else {
			tblUserId = Long.parseLong(request.getParameter("tblUserId"));
			articleTypes = articleTypeRepository.selectAll();
			articleTypeText = articleTypes.get(Long.parseLong(action)).getType(); 
			request.setAttribute("articleTypes", articleTypes);
			request.setAttribute("articleTypeText", articleTypeText);
			request.setAttribute("user", userRepository.selectById(tblUserId));
			request.setAttribute("viewArticles", viewArticleRepository.selectByArticleTypeId(Long.parseLong(action)));
			rd = getServletContext().getRequestDispatcher("/jsp/main.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
