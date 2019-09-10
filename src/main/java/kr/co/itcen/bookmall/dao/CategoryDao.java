package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.bookmall.vo.CategoryVo;

public class CategoryDao {

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

	public boolean insert(CategoryVo vo1) {
		boolean b = false;
		try {
			conn = getConn();

			String sql = "insert into category values(?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo1.getNo());
			pstmt.setString(2, vo1.getKind());

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

	public List<CategoryVo> getList() {
		List<CategoryVo> list = new ArrayList<CategoryVo>();

		try {
			conn = getConn();
			String sql = "select no, kind from category order by no";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String no = rs.getString(1);
				String kind = rs.getString(2);

				CategoryVo vo = new CategoryVo();
				vo.setNo(no);
				vo.setKind(kind);

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
