package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Comment;
import repository.CommentRepository;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action;
	private Comment comment;
	private CommentRepository commentRepository;
	private String content;
	private String date;
	private long tblUserId;
	private long tblArticleId;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        this.commentRepository = new CommentRepository();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
		if(action.equals("insert")) {
			content = request.getParameter("content");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate today  = LocalDate.now();
			date = today.format(formatter);
			tblUserId = Long.parseLong(request.getParameter("tblUserId"));
			tblArticleId = Long.parseLong(request.getParameter("tblArticleId"));
			
			comment = new Comment(content, date, tblUserId, tblArticleId, 0, 0);
			if(commentRepository.insert(comment)) {
				System.out.println("댓글 입력 성공");
			}else {
				System.out.println("댓글 입력 실패");
			}
			response.sendRedirect("/jspSqlProject/ArticleServlet?action=detail&id=" + tblArticleId + "&tblUserId=" + tblUserId);
		}
//		doGet(request, response);
	}

}
