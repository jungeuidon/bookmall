package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.BookDao;
import kr.co.itcen.bookmall.dao.MemberDao;
import kr.co.itcen.bookmall.dao.OrderDao;
import kr.co.itcen.bookmall.vo.CartVo;
import kr.co.itcen.bookmall.vo.MemberVo;
import kr.co.itcen.bookmall.vo.OrderBookVo;
import kr.co.itcen.bookmall.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		insertOrders();
		selectOrders();
		selectOrderBooks();
	}

	private static void insertOrders() {
		OrderDao dao = new OrderDao();
		
		OrderVo vo1 = new OrderVo();

//		vo1.setNo("2");
		vo1.setUser_no("1");
		vo1.setAddr("경기도 성남시 수정구 수진1동 0000번지");
//		vo1.setPrice(vo1.getPrice());
		dao.insert(vo1);
		System.out.println("vo1 입니다" + vo1);
		
	}
	
	private static void selectOrders() {
		OrderDao dao = new OrderDao();
		List<OrderVo> list = dao.getList();
		System.out.println("주문목록");
		for(OrderVo order : list) {
			System.out.println(order);
		}
	}
	
	private static void selectOrderBooks() {
		OrderDao dao = new OrderDao();
		List<OrderBookVo> list = dao.orderBookList();
		System.out.println("주문도서목록");
		for(OrderBookVo orderB : list) {
			System.out.println(orderB);
		}
	}
	
}
