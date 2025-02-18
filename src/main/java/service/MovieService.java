package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

import dao.MovieDao;
import dao.MovieTypeDao;
import repository.MovieRepository;
import repository.UserRepository;

/**
 * Servlet implementation class MovicService
 */
@WebServlet("/MovieService")
public class MovieService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MovieRepository service;
	
    public MovieService() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		String view="";
		System.out.println(action);
		
		if(action==null) {
			getServletContext().getRequestDispatcher("/MovieService?action=list").forward(request, response);
		}else {
			switch (action) {
			case "list":
				view=movielist(request, response);
				break;
			case "insert":
				view=movieinsert(request, response);
				break;
			case "delete":
				view=moviedelete(request, response);
				break;
			default:
				System.out.println("알 수 없는 파라미터 값입니다!");
				break;
			}
			getServletContext().getRequestDispatcher("/jsp/"+view).forward(request, response);
		}
	}

	public String movielist(HttpServletRequest request, HttpServletResponse response) {
		service=MovieRepository.getInstance();
		UserRepository userrepository=new UserRepository();
		long userid=Long.parseLong(request.getParameter("tblUserId"));
		Map<String,Object> map=new HashMap<>();
		Map<Long,Object> typemap=new HashMap<>();
		
		List<MovieDao> movelist=service.getMovielist();
		for(MovieDao mov: movelist) {
			
			long id=mov.getId();
			List<String> typelist=new ArrayList<>();
			for(MovieTypeDao typeone:service.getMovieType(id)) {
				String typeelement=typeone.getType();
				typelist.add(typeelement);
			}
			typemap.put(id, typelist);
			
		}
		map.put("movlist", movelist);
		map.put("typelist", typemap);
		
		request.setAttribute("user", userrepository.selectById(userid));
		request.setAttribute("moveinfo",map);
		
		return "MovieList.jsp";
	}
	

	//영화 등록 폼을 보여주고 동시에, 등록할수 있는 영화 장르들을 보여준다.
	public String movieinsert(HttpServletRequest request, HttpServletResponse response) {
		
		return "MovieInsert.jsp";
	}
	
	
	//영화 삭제를 진행하고 다시 영화 리스트 화면을 보여준다.
	public String moviedelete(HttpServletRequest request, HttpServletResponse response) {
		service=MovieRepository.getInstance();
		long movieid=Long.parseLong(request.getParameter("movieid"));
		if(service.DeleteMovie(movieid)){
			request.setAttribute("deleteresult",true);
		}else {
			request.setAttribute("deleteresult",false);
		}
		return "MovieList.jsp";
	}
	
	//구현 여부 미정
	/*
	public String movieupdate(HttpServletRequest request, HttpServletResponse response) {
		
		return "MovieUpdate.jsp";
	}*/
}
