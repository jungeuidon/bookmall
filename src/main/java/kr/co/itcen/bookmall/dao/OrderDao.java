package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.bookmall.vo.OrderBookVo;
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
//			System.out.println("연결성공!");

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
		String o_no =null; //주문번호
		String c_book_no =null;  //책 번호
		String c_cnt =null; //책별 수량
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
			System.out.println( "63행 고객번호" + user_no);
			String sql = "insert into orders values(null,?,?,?)";
			pstmt = conn.prepareStatement(sql);

//			pstmt.setString(1, vo1.getNo());  //주문고유번호
			pstmt.setString(1, vo1.getUser_no());		//고객번호
			pstmt.setString(2, Strprice);	 			//총가격
			pstmt.setString(3, vo1.getAddr());
			
			
			int ordercnt = pstmt.executeUpdate();
			
			if (ordercnt !=0 ) {
				System.out.println("oInsert 성공!!");
				b = true;
			} else {
				System.out.println("oInsert 실패 !!");
			}
			
			String orderbooksql = "select o.no, c.book_no, c.cnt from cart c, orders o where c.user_no = o.user_no";
			pstmt = conn.prepareStatement(orderbooksql);
			
			rs = pstmt.executeQuery();
			
			List<OrderBookVo> list = new ArrayList<OrderBookVo>(); 
			while (rs.next()) {
				OrderBookVo bVo = new OrderBookVo(); 
				o_no = rs.getString(1); // 주문번호
				c_book_no = rs.getString(2); // 책 번호
				c_cnt = rs.getString(3); // 책별 수량
				
				bVo.setC_book_no(c_book_no);
				bVo.setO_no(o_no);
				bVo.setC_cnt(c_cnt);
				
				list.add(bVo);
			}
			System.out.println(o_no + " " + c_book_no + " " + c_cnt);
			
			String orderBsql ="insert into order_book values(?,?,?)";  //주문도서
			pstmt = conn.prepareStatement(orderBsql);
			
			int orderBcnt =0;
			
			for(OrderBookVo bVo : list) {
				pstmt.setString(1, bVo.getC_book_no());   //book_no
				pstmt.setString(2, bVo.getC_cnt());   //cnt
				pstmt.setString(3, bVo.getO_no());  //order_no
				orderBcnt = pstmt.executeUpdate();
			}
			
			if (orderBcnt !=0 ) {
				System.out.println("oBinsert 성공!!");
				b = true;
			} else {
				System.out.println("oBinsert 실패 !!");
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

	public List<OrderBookVo> orderBookList() {
		List<OrderBookVo> list = new ArrayList<OrderBookVo>();

		try {
			conn = getConn();
			String sql = "select book_no, cnt, order_no from order_book";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String c_book_no = rs.getString(1);
				String c_cnt = rs.getString(2);
				String o_no = rs.getString(3);
				
				
				OrderBookVo vo = new OrderBookVo();
				vo.setO_no(o_no);
				vo.setC_book_no(c_book_no);
				vo.setC_cnt(c_cnt);

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
