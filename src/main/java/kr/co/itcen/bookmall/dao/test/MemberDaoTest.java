package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.MemberDao;
import kr.co.itcen.bookmall.vo.MemberVo;

public class MemberDaoTest {

	
	public static void main(String[] args) {
//		insertMem();
		selectMem();

	}

	private static void insertMem() {
		MemberDao dao = new MemberDao();
		
		MemberVo vo1 = new MemberVo();
//		vo1.setNo(null);
		vo1.setName("하희라");
		vo1.setTel("010-1333-5678");
		vo1.setEmail("naver@naver.com");
		vo1.setPasswd("1234");
		dao.insert(vo1);
		System.out.println(vo1);
	}
	
	private static void selectMem() {
		MemberDao dao = new MemberDao();
		List<MemberVo> list = dao.getList();
		System.out.println(list);
		for(MemberVo member : list) {
			System.out.println(member);
		}
	}
	
	
}
