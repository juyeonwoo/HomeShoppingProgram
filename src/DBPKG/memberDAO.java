package DBPKG;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class memberDAO {
	
	private static memberDAO instance = new memberDAO();
	public static memberDAO getInstance(){
		return instance;
	}
	private memberDAO(){}
	
	public Connection getConnection() throws SQLException {
		String jdbc_driver="oracle.jdbc.OracleDriver";
		String db_url="jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(db_url,"system","1234");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void join(mVO vo) throws SQLException{
		Connection conn=getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into member_tbl_02 values(?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?)";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(vo.getCustno()));
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getJoindate());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getCity());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			 if(pstmt!=null) pstmt.close();
			 if(conn!=null) conn.close();
		}
	}
	
	public String count() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String custno=null;
		try {
			conn = getConnection();
			sql = "select max(custno)+1 from member_tbl_02";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				custno=rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) rs.close();
			 if(pstmt!=null) pstmt.close();
			 if(conn!=null) conn.close();
		}
		return custno;
	}
	
	public ArrayList<mVO> memberList()  throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<mVO> mvo = new ArrayList<mVO>();
		try {
			conn = getConnection();
			sql = "SELECT * FROM member_tbl_02";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mVO vo = new mVO();
				vo.setCustno(rs.getString(1));
				vo.setCustname(rs.getString(2));
				vo.setPhone(rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setJoindate(rs.getString(5));
				vo.setGrade(rs.getString(6));
				vo.setCity(rs.getString(7));
				mvo.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) rs.close();
			 if(pstmt!=null) pstmt.close();
			 if(conn!=null) conn.close();
		}
		return mvo;
	}
	
	public ArrayList<mVO> saleList()  throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<mVO> mvo = new ArrayList<mVO>();
		try {
			conn = getConnection();
			sql = "select a.custno,a.custname,a.grade,to_char(sum(b.price)) from member_tbl_02 a,money_tbl_02 b "
					+ "where a.custno=b.custno group by a.custno,a.custname,a.grade;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mVO vo = new mVO();
				vo.setCustno(rs.getString(1));
				vo.setCustname(rs.getString(2));
				vo.setGrade(rs.getString(3));
				vo.setPrice(rs.getString(4));
				mvo.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) rs.close();
			 if(pstmt!=null) pstmt.close();
			 if(conn!=null) conn.close();
		}
		return mvo;
	}
}
