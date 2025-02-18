package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.DBCon;
import dao.MovieDao;
import dao.MovieTypeDao;

public class MovieRepository {

	private DBCon dbCon;
	public static MovieRepository repository=new MovieRepository();
	private HashMap<Long, MovieDao> movies;
	private MovieDao movie;
	private String sql;
	
	public MovieRepository() {
		this.dbCon=new DBCon();
		this.movies=new HashMap<>();
	}
	
	public static MovieRepository getInstance() {
		return repository;
	}

	
	public HashMap<Long, MovieDao> selectAll() {
		try {
			sql = "select * from tbl_movie";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));			
			dbCon.setRs(dbCon.getPs().executeQuery());
			
			while(dbCon.getRs().next()) {
				movie = new MovieDao(
						dbCon.getRs().getLong("id"), dbCon.getRs().getString("title"),
						dbCon.getRs().getDate("makeDate"), dbCon.getRs().getString("makeCountry"),
						dbCon.getRs().getString("director"), dbCon.getRs().getString("company")
						);
				
				movies.put(dbCon.getRs().getLong("id"), movie);
			}
			
			return movies;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("tbl_movie select fail");
			return null;
		}finally {
			dbCon.closeAll();			
		}
	}
	
	//모든 영화 목록 가져오기
	public List<MovieDao> getMovielist(){
		List<MovieDao> movielist=new ArrayList<>();
		String sql="SELECT * FROM `tbl_movie`";
		try {
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.setRs(dbCon.getPs().executeQuery());
			
			while(dbCon.getRs().next()) {
				MovieDao movie=new MovieDao();

				long id=dbCon.getRs().getLong(1);
				String title=dbCon.getRs().getString(2);
				Date makeDate=dbCon.getRs().getDate(3);
				String makeCountry=dbCon.getRs().getString(4);
				String director=dbCon.getRs().getString(5);
				String company=dbCon.getRs().getString(6);
				
				movie.setId(id);
				movie.setTitle(title);
				movie.setMakeDate(makeDate);
				movie.setMakeCountry(makeCountry);
				movie.setDirector(director);
				movie.setCompany(company);
				
				movielist.add(movie);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movielist;
	}
	//모든 영화 장르 목록을 가져온다
	public List<MovieTypeDao> getMovieTypelist(){
		List<MovieTypeDao> movietypelist=new ArrayList<>();
		
		String sql="SELECT * FROM tbl_movietype";
		
		try {
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.setRs(dbCon.getPs().executeQuery());
			while(dbCon.getRs().next()) {
				MovieTypeDao typeone=new MovieTypeDao();
				
				long id=dbCon.getRs().getLong(1);
				String type=dbCon.getRs().getString(2);
				
				typeone.setId(id);
				typeone.setType(type);
				
				movietypelist.add(typeone);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movietypelist;
	}
	//특정 영화에 해당하는 장르 목록 가져오기
	public List<MovieTypeDao> getMovieType(long id){
		List<MovieTypeDao> movietypelist=new ArrayList<>();
		
		String sql="SELECT a.id, b.type FROM `tbl_movietypedetail` a "
				+ "inner join `tbl_movietype` b on a.tbl_movieType_id=b.id "
				+ "WHERE (tbl_movie_id = ?)";
		
		try {
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setLong(1,id);
			dbCon.setRs(dbCon.getPs().executeQuery());
			
			while(dbCon.getRs().next()) {
				MovieTypeDao movietype=new MovieTypeDao();
				
				String type=dbCon.getRs().getString(2);
				
				movietype.setType(type);
				
				movietypelist.add(movietype);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movietypelist;
	}
	
	//영화 입력하기(이때 데이터베이스 상에 존재하는 장르도 같이 입력해야한다)
	public boolean InsertMovie(MovieDao insertmovie, String[] typearr) {
		boolean result=false;
		String sql="INSERT INTO `tbl_movie`(`title`,`makeDate`,`makeCountry`,`director`,`company`)"
				+ " VALUES(?,now(),?,?,?)";
		String subsql="INSERT INTO tbl_movietypedetail(tbl_movie_id, tbl_movieType_id) " +
	             "SELECT ?, id FROM tbl_movietype WHERE (type = ?)";
		long movieId=-1;
		
		try {

			dbCon.getConn().setAutoCommit(false);
			
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setString(1,insertmovie.getTitle());
			dbCon.getPs().setString(2, insertmovie.getMakeCountry());
			dbCon.getPs().setString(3, insertmovie.getDirector());
			dbCon.getPs().setString(4, insertmovie.getCompany());
			
			int insertcount=dbCon.getPs().executeUpdate();
			if(insertcount==0) {
				dbCon.getConn().rollback();
				dbCon.getConn().setAutoCommit(true);
				return result;
			}

			ResultSet rs= dbCon.getPs().getGeneratedKeys();
			
			if(rs.next()){
		        movieId = rs.getLong(1);
		    }
			
			
			for(int i=0;i<typearr.length;i++) {
				String typeone=typearr[i];
				dbCon.setPs(dbCon.getConn().prepareStatement(subsql));
				dbCon.getPs().setLong(1, movieId);
				dbCon.getPs().setString(2, typeone);
				
				int insertcount2=dbCon.getPs().executeUpdate();
				if(insertcount2==0) {
					dbCon.getConn().rollback();
					dbCon.getConn().setAutoCommit(true);
					return result;
				}
			}

			dbCon.getConn().commit();
			result=true;
			dbCon.getConn().setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//영화 삭제하기 (영화와 영화 장르 목록을 동시에 삭제)
	public boolean DeleteMovie(long movie_id) {
		boolean result=false;
		String sql="DELETE FROM `tbl_movie` WHERE ( id = ? )";
		String subsql="DELETE FROM `tbl_movietypedetail` WHERE (tbl_movie_id = ?)";
		
		try {
			dbCon.getConn().setAutoCommit(false);
			
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setLong(1,movie_id);
			
			int deletecount=dbCon.getPs().executeUpdate();
			if(deletecount==0) {
				dbCon.getConn().rollback();
				dbCon.getConn().setAutoCommit(true);
				return result;
			}

			dbCon.setPs(dbCon.getConn().prepareStatement(subsql));
			dbCon.getPs().setLong(1,movie_id);
			
			int deletecount2=dbCon.getPs().executeUpdate();
			if(deletecount2==0) {
				dbCon.getConn().rollback();
				dbCon.getConn().setAutoCommit(true);
				return result;
			}

			dbCon.getConn().commit();
	        result = true;
			dbCon.getConn().setAutoCommit(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//영화 정보 수정하기(둘다 같이 수정 되어야, 영화 장르는 삭제하기 다시 삽입)
	public boolean UpdateMovie(MovieDao updatemovie,String[] typearr) {
		boolean result=false;
		String sql="UPDATE `tbl_movie` SET title = ?, makeCountry = ?, "
				+ "director = ?, company = ? WHERE ( id = ?)";
		String deletesql="DELETE FROM tbl_movietypedetail WHERE (tbl_movie_id = ?)";
		String insertsql="INSERT INTO tbl_movietypedetail(tbl_movie_id, tbl_movieType_id) "
				+ "SELECT ?, id FROM tbl_movietype WHERE (type = ?)";
		
		try {
			dbCon.getConn().setAutoCommit(false);
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setString(1, updatemovie.getTitle());
			dbCon.getPs().setString(2, updatemovie.getMakeCountry());
			dbCon.getPs().setString(3, updatemovie.getDirector());
			dbCon.getPs().setString(4, updatemovie.getCompany());
			dbCon.getPs().setLong(5, updatemovie.getId());
			
			int updatecount=dbCon.getPs().executeUpdate();
			if(updatecount==0) {
				dbCon.getConn().rollback();
				dbCon.getConn().setAutoCommit(true);
				return result;
			}

			dbCon.setPs(dbCon.getConn().prepareStatement(deletesql));
			dbCon.getPs().setLong(1, updatemovie.getId());
			
			int deletecount=dbCon.getPs().executeUpdate();
			if(deletecount==0) {
				dbCon.getConn().rollback();
				dbCon.getConn().setAutoCommit(true);
				return result;
			}
			
			
			for(int i=0;i<typearr.length;i++) {
				String typeone=typearr[i];
				dbCon.setPs(dbCon.getConn().prepareStatement(insertsql));
				dbCon.getPs().setLong(1, updatemovie.getId());
				dbCon.getPs().setString(2, typeone);
				
				int insertcount=dbCon.getPs().executeUpdate();
				if(insertcount==0) {
					dbCon.getConn().rollback();
					dbCon.getConn().setAutoCommit(true);
					return result;
				}
			}
			
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
}
