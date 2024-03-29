package kr.co.itcen.bookmall.vo;

public class BookVo {
	private String no,name,price,cnt,category_no;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public String getCategory_no() {
		return category_no;
	}

	public void setCategory_no(String category_no) {
		this.category_no = category_no;
	}

	@Override
	public String toString() {
		return "책번호=" + no + ", 책이름=" + name + ", 책가격=" + price + ", 재고=" + cnt + ", 카테고리번호="+ category_no;
	}
	
}
