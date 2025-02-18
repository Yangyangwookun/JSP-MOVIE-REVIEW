package service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Article;
import dao.ArticleType;
import dao.MovieDao;
import dao.User;
import repository.ArticleRepository;
import repository.ArticleTypeRepository;
import repository.CommentRepository;
import repository.MovieRepository;
import repository.UserRepository;
import repository.ViewArticleRepository;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private String action;
   private RequestDispatcher rd;
   private ArticleType articleType;
   private MovieDao movie;
   private HashMap<Long, ArticleType> articleTypes;
   private ArticleTypeRepository articleTypeRepository;
   private HashMap<Long, MovieDao> movies;
   private MovieRepository movieTypeRepository;
   private ViewArticleRepository viewArticleRepository;
   private String title;
   private long tblArticleTypeId;
   private long tblMovieId;
   private String content;
   private Article article;
   private String date;
   private long tblUserId;
   private ArticleRepository articleRepository;
   private long id;
   private CommentRepository commentRepository;
   private User user;
   private UserRepository userRepository;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.articleTypes = new HashMap<Long, ArticleType>();
        this.articleTypeRepository = new ArticleTypeRepository();
        this.viewArticleRepository=new ViewArticleRepository();
        this.movieTypeRepository = new MovieRepository();
        this.articleRepository = new ArticleRepository();
        this.commentRepository = new CommentRepository();
        this.userRepository = new UserRepository();
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      action = request.getParameter("action");
      if(action.equals("insert")) {
         
         //request.setCharacterEncoding("UTF-8");
         //response.setContentType("text/html;charset=UTF-8");
         tblUserId = Long.parseLong(request.getParameter("tblUserId"));
         articleTypes = articleTypeRepository.selectAll();
         movies = movieTypeRepository.selectAll();
         user = userRepository.selectById(tblUserId);
         if(user.getTblUserTypeId() == 1) {
            articleTypes.remove((long)4);
            articleTypes.remove((long)5);
         }else if(user.getTblUserTypeId() == 2) {
            articleTypes.remove((long)5);
         }
         request.setAttribute("articleTypes", articleTypes);
         request.setAttribute("movies", movies);
         request.setAttribute("tblUserId", tblUserId);
         rd = getServletContext().getRequestDispatcher("/jsp/articleInsert.jsp");
         rd.forward(request, response);
      }else if(action.equals("detail")) {
         
         id = Long.parseLong(request.getParameter("id"));
         request.setAttribute("article", articleRepository.selectById(id));
         request.setAttribute("comments", commentRepository.selectByTblArticleId(id));
         request.setAttribute("tblUserId", request.getParameter("tblUserId"));
         rd = getServletContext().getRequestDispatcher("/jsp/articleDetail.jsp");
         articleRepository.updateview(id);
         rd.forward(request, response);
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      response.setContentType("text/html;charset=UTF-8");
      request.setCharacterEncoding("UTF-8");
      action = request.getParameter("action");
      if(action.equals("insert")) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         LocalDate today  = LocalDate.now();
         date = today.format(formatter);
         tblUserId = Long.parseLong(request.getParameter("tblUserId"));
         title = request.getParameter("title");
         tblArticleTypeId = Long.parseLong(request.getParameter("tblArticleTypeId"));
         tblMovieId = Long.parseLong(request.getParameter("tblMovieId"));
         content = request.getParameter("content");
         System.out.println(title);
         article = new Article(date, content, title, 0, tblUserId, 0, 0, tblMovieId, tblArticleTypeId);
         if(articleRepository.insert(article)) {
            System.out.println("게시물 저장 성공");
            
         }else {
            System.out.println("게시물 저장 실패");
         }
         //request.setAttribute("tblUserId", tblUserId);
         //request.setAttribute("articles", articleRepository.selectAll());
         
         request.setAttribute("articleTypeText", "전체");
         request.setAttribute("articleTypes", articleTypeRepository.selectAll());
         request.setAttribute("user", userRepository.selectById(tblUserId));
         request.setAttribute("viewArticles", viewArticleRepository.selectAll());
         rd = getServletContext().getRequestDispatcher("/jsp/main.jsp");
         rd.forward(request, response);
         
      }
//      doGet(request, response);
   }

}
