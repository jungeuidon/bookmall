package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.bookmall.vo.MemberVo;

public class MemberDao {
	
	Connection conn=null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private Connection getConn() throws SQLException{
		Connection conn=null;
		try {
			
			//1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.1.40:3306/bookmall?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bit1234");
//			System.out.println("연결성공!");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		}
		return conn;
	}
	
	
	public boolean insert(MemberVo vo1) {
		boolean b = false;
		try {
			conn = getConn();
			
			String sql = "insert into user values(null,?,?,?,?)";
//			String sql = "insert into user values(?,?,?,?,?)"; 첫번째insert만 
			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setString(1, vo1.getNo());
			pstmt.setString(1, vo1.getName());
			pstmt.setString(2, vo1.getTel());
			pstmt.setString(3, vo1.getEmail());
			pstmt.setString(4, vo1.getPasswd());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				System.out.println("insert 성공!!");
				b=true;
			}else {
				System.out.println("insert 실패 !!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			} 
		}
		
		return b;
	}


	public List<MemberVo> getList() {
			List<MemberVo> list = new ArrayList<MemberVo>();
			
		try {
			conn = getConn();
			String sql  = "select no, name, tel ,email ,passwd from user order by no";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString(1);
				String name = rs.getString(2);
				String tel = rs.getString(3);
				String email = rs.getString(4);
				String pass = rs.getString(5);
				
				System.out.println(no);
				
				MemberVo vo = new MemberVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setTel(tel);
				vo.setEmail(email);
				vo.setPasswd(pass);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			} 
		}
		
		return list;
	}
	
}	


	

	
	
	
