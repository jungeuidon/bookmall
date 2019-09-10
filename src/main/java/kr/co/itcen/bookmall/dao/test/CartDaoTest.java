package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.CartDao;
import kr.co.itcen.bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
//		insertCart();
		selectCart();

	}

	private static void insertCart() {
		System.out.println("insert");
		CartDao dao = new CartDao();
		
		CartVo vo1 = new CartVo();
//		vo1.setNo("1"); //1
		vo1.setBook_no("3"); //2
		vo1.setCnt("4"); //3
		vo1.setUser_no("1"); //5
		dao.insert(vo1);
		System.out.println(vo1);
	}
	
	private static void selectCart() {
		System.out.println("select");
		CartDao dao = new CartDao();
		List<CartVo> list = dao.getList();
		System.out.println("카트목록");
		for(CartVo cart : list) {
			System.out.println(cart);
		}
	}

}
