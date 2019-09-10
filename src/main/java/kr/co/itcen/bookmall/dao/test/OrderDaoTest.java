package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.BookDao;
import kr.co.itcen.bookmall.dao.MemberDao;
import kr.co.itcen.bookmall.dao.OrderDao;
import kr.co.itcen.bookmall.vo.CartVo;
import kr.co.itcen.bookmall.vo.MemberVo;
import kr.co.itcen.bookmall.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		insertOrders();
		selectOrders();

	}

	private static void insertOrders() {
		OrderDao dao = new OrderDao();
		
		OrderVo vo1 = new OrderVo();

		vo1.setNo("1");
		vo1.setUser_no("2");
		vo1.setAddr("경기도 성남시 수정구 수진1동 0000번지");
//		vo1.setPrice(vo1.getPrice());
		dao.insert(vo1);
		System.out.println("vo1 입니다" + vo1);
		orderBook();
	}
	
	
	private static void orderBook() {
		
		
	}

	private static void selectOrders() {
		OrderDao dao = new OrderDao();
		List<OrderVo> list = dao.getList();
		System.out.println(list);
		for(OrderVo order : list) {
			System.out.println(order);
		}
	}
	
	
}
