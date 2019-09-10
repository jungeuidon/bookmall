package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.BookDao;
import kr.co.itcen.bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
//			insertBook();
			selectBook();

		}

		private static void insertBook() {
			System.out.println("insert");
			BookDao dao = new BookDao();
			
			BookVo vo1 = new BookVo();
//			vo1.setNo("2");
			vo1.setName("우리가 과학을 사랑하는 법");
			vo1.setPrice("14000");
			vo1.setCnt("250");
			vo1.setCategory_no("2");
			dao.insert(vo1);
			System.out.println(vo1);
		}
		
		private static void selectBook() {
			BookDao dao = new BookDao();
			List<BookVo> list = dao.getList();
			System.out.println("도서목록");
			for(BookVo book : list) {
				System.out.println(book);
			}
		}
}