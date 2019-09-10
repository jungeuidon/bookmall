package kr.co.itcen.bookmall.vo;

public class OrderBookVo {
	private String o_no, c_book_no, c_cnt;

	public String getO_no() {
		return o_no;
	}

	public void setO_no(String o_no) {
		this.o_no = o_no;
	}

	public String getC_book_no() {
		return c_book_no;
	}

	public void setC_book_no(String c_book_no) {
		this.c_book_no = c_book_no;
	}

	public String getC_cnt() {
		return c_cnt;
	}

	public void setC_cnt(String c_cnt) {
		this.c_cnt = c_cnt;
	}

	@Override
	public String toString() {
		return "주문번호:" + o_no + ", 책번호=" + c_book_no + ", 책별주문수량=" + c_cnt;
	} 
	
	
}
