package kr.co.itcen.bookmall.vo;

public class OrderVo {
	private String no, user_no, price, addr;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "주문번호=" + no + ", 고객번호=" + user_no + ", 총 가격=" + price + ", 고객 주소=" + addr;
	}
	
}
