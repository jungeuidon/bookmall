package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.bookmall.vo.BookVo;

public class BookDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private Connection getConn() throws SQLException {
		Connection conn = null;
		try {

			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.40:3306/bookmall?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bit1234");
//			System.out.println("연결성공!");

		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		}
		return conn;
	}
	
	public boolean insert(BookVo vo1) {
		boolean b = false;
		try {
			conn = getConn();

			String sql = "insert into book values(null,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

//			pstmt.setString(1, vo1.getNo());
			pstmt.setString(1, vo1.getName());
			pstmt.setString(2, vo1.getPrice());
			pstmt.setString(3, vo1.getCnt());
			pstmt.setString(4, vo1.getCategory_no());

			int cnt = pstmt.executeUpdate();

			if (cnt == 1) {
				System.out.println("insert 성공!!");
				b = true;
			} else {
				System.out.println("insert 실패 !!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return b;
		
	}

	public List<BookVo> getList() {
		List<BookVo> list = new ArrayList<BookVo>();

		try {
			conn = getConn();
			String sql = "select no, name, price, cnt, category_no from book order by no";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String no = rs.getString(1);
				String name = rs.getString(2);
				String price = rs.getString(3);
				String cnt = rs.getString(4);
				String category_no = rs.getString(5);
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPrice(price);
				vo.setCnt(cnt);
				vo.setCategory_no(category_no);
				
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return list;
	}

	

}
