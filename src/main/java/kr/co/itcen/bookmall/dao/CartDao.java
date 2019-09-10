package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.bookmall.vo.CartVo;

public class CartDao {

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
			System.out.println("연결성공!");

		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		}
		return conn;
	}

	public boolean insert(CartVo vo1) {
		boolean b = false;
		String Strprice = null;  //총가격
		try {
			conn = getConn();

			String sql2 = "select price from book where no=?";
			pstmt = conn.prepareStatement(sql2);
			
			pstmt.setString(1, vo1.getBook_no());
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
			Strprice = rs.getString(1);
			}
			
			//price 받아오고 형변환
			int price = Integer.parseInt(Strprice);
			
			String sql = "insert into cart values(null,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setString(1, vo1.getNo());
			pstmt.setString(1, vo1.getBook_no());
			pstmt.setString(2, vo1.getCnt());
			pstmt.setInt(3, price*Integer.parseInt(vo1.getCnt()));
			pstmt.setString(4, vo1.getUser_no());

			
			int cnt = pstmt.executeUpdate();

			if (cnt == 1) {
				System.out.println("insert 성공!!");
				b = true;
			} else {
				System.out.println("insert 실패 !!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public List<CartVo> getList() {
		List<CartVo> list = new ArrayList<CartVo>();

		try {
			conn = getConn();
			String sql = "select no, book_no, cnt, all_price, user_no from cart order by no";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String no = rs.getString(1);
				String book_no = rs.getString(2);
				String cnt = rs.getString(3);
				String all_price = rs.getString(4);
				String user_no = rs.getString(5);
				
				CartVo vo = new CartVo();
				vo.setNo(no);
				vo.setBook_no(book_no);
				vo.setCnt(cnt);
				vo.setall_price(all_price);
				vo.setUser_no(user_no);

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
