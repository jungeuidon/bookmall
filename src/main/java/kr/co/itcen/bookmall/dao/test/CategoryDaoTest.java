package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.CategoryDao;
import kr.co.itcen.bookmall.vo.BookVo;
import kr.co.itcen.bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
//		insertCategory();
		selectCategory();

	}

	private static void insertCategory() {
		CategoryDao dao = new CategoryDao();

		CategoryVo vo1 = new CategoryVo();
//		vo1.setNo(null);
		vo1.setNo("1");
		vo1.setKind("소설");
		dao.insert(vo1);
		System.out.println(vo1);
	}

	private static void selectCategory() {
		CategoryDao dao = new CategoryDao();
		List<CategoryVo> list = dao.getList();
		for(CategoryVo category : list) {
			System.out.println(category);
		}
	}
}