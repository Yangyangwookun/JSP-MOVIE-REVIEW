package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CommentDao;
import repository.CommentRepository;

/**
 * 댓글 서블릿
 * 댓글 읽기, 등록, 삭제
 */
@WebServlet("/CommentService")
public class CommentService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CommentRepository service;
    
    public CommentService() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		System.out.println(action);
		 
		 if("list".compareTo(action)==0) {
			 System.out.println("list test");
			 listComment(request, response);
			 
	     } else if("listpage".compareTo(action)==0) {
			 System.out.println("list page test");
	    	 listCommentPage(request, response);
	    	 
	     } else if ("insert".compareTo(action)==0) {
			 System.out.println("insert test");
	         insertComment(request, response);
	         
	     } else if ("delete".compareTo(action)==0) {
			 System.out.println("delete test");
	         deleteComment(request, response);
	         
	     } else {
			 System.out.println("none");
	    	 response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 action 입니다.");
	     } 
	}
	
	public void listComment(HttpServletRequest request, HttpServletResponse response) {
		service=CommentRepository.getInstance();
		String articleid=request.getParameter("articleid");
		
		if(articleid!=null) {
			List<CommentDao> commentlist=service.GetCommentList_Article(Long.parseLong(articleid));

			//request.setAttribute("comentlist", commentlist);
			
			
			response.setContentType("application/json;charset=UTF-8");
			try {
	    	 
				PrintWriter out = response.getWriter();
				Gson gson = new Gson();
				String json = gson.toJson(commentlist);
				System.out.println(json);
				out.write(json);
				out.flush();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("게시글 아이디 파라미터 값이 비어있음");
		}
	}
	public void listCommentPage(HttpServletRequest request, HttpServletResponse response) {
		service=CommentRepository.getInstance();
		String articleid=request.getParameter("articleid");
		String pagenum=request.getParameter("pagenum");
		String amount=request.getParameter("amount");
		
		if(articleid!=null&&pagenum!=null&&amount!=null) {
			List<CommentDao> commentlist=service.GetCommentList_ArticlePage(Long.parseLong(articleid), 
						Integer.parseInt(amount), Integer.parseInt(pagenum));
		
			
			response.setContentType("application/json;charset=UTF-8");
			try {
				PrintWriter out = response.getWriter();
				Gson gson = new Gson();
				String json = gson.toJson(commentlist);
				out.write(json);
				out.flush();
				
			} catch (IOException e) {
					e.printStackTrace();
			}
		}else {
			System.out.println("몇몇 파라미터 값이 비어있음");
		}
	}
	
	public void deleteComment(HttpServletRequest request, HttpServletResponse response) {
		service=CommentRepository.getInstance();
		String commentid=request.getParameter("commentid");
		
		if(service.DeleteComment(Long.parseLong(commentid))) {
			System.out.println(commentid+" 번 댓글이 정상적으로 삭제됨");
			listComment(request,response);
		}else {
			System.out.println("알 수 없는 오류로 실패");
		}
		
	}
	public void insertComment(HttpServletRequest request, HttpServletResponse response) {
		service=CommentRepository.getInstance();
		CommentDao newcomment=new CommentDao();
		
		String content=request.getParameter("content");
		String userid=request.getParameter("userid");
		String articleid=request.getParameter("articleid");
		
		newcomment.setContent(content);
		newcomment.setTbl_user_id(Long.parseLong(userid));
		newcomment.setTbl_article_id(Long.parseLong(articleid));
		
		if(service.InsertComment(newcomment)) {
			System.out.println(userid+"번 회원이 "+articleid+" 에 작성한 "+content+" 댓글이 입력됨");
			listComment(request,response);
		}else {
			System.out.println("알 수 없는 오류로 실패");
		}
	}
	
}
