package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.bookmall.vo.OrderVo;

public class OrderDao {
	
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
	
	//주문 추가
	public boolean insert(OrderVo vo1) {
		boolean b = false;
		String Strprice = null;  //총가격
		String user_no = null;
		try {
			conn = getConn();

			String sql2 = "select all_price, user_no from cart where user_no=?";
			pstmt = conn.prepareStatement(sql2);
			
			pstmt.setString(1, vo1.getUser_no());
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
			Strprice = rs.getString(1);    //all_price
			user_no = rs.getString(2);		// user_no
			}
			
			//price 받아오고 형변환
			int price = Integer.parseInt(Strprice);
			System.out.println(user_no);
			String sql = "insert into orders values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo1.getNo());  //주문고유번호
			pstmt.setString(2, vo1.getUser_no());		//고객번호
			pstmt.setString(3, Strprice);	 			//총가격
			pstmt.setString(4, vo1.getAddr());
			
			System.out.println("dddd1");
			int cnt = pstmt.executeUpdate();
			System.out.println("dddd2");
			
			if (cnt !=0 ) {
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

	public List<OrderVo> getList() {
		List<OrderVo> list = new ArrayList<OrderVo>();

		try {
			conn = getConn();
			String sql = "select no, user_no, price, addr from orders order by no";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String no = rs.getString(1);
				String user_no = rs.getString(2);
				String price = rs.getString(3);
				String addr = rs.getString(4);
				
				
				OrderVo vo = new OrderVo();
				vo.setNo(no);
				vo.setUser_no(user_no);
				vo.setPrice(price);
				vo.setAddr(addr);

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
