package khj.dto;

public class OrderDTO {
	private String order_id;// 아이디: 외부키 → customer 테이블의 id
	private int order_number;// 주문 번호: 기본 키
	private int order_count;// 주문 수량
	private int order_product;// 상품 번호: 외부 키 → item_info 테이블의 item_num
	private int order_installment;// 할부 여부
	private String order_payment;// 결제 수단
	private String order_date;// 주문 날짜
	private int order_total;// 결제 총액
	private int order_coupon;// 쿠폰 여부
	private int order_point;// 포인트 여부

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public int getOrder_count() {
		return order_count;
	}

	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}

	public int getOrder_product() {
		return order_product;
	}

	public void setOrder_product(int order_product) {
		this.order_product = order_product;
	}

	public int getOrder_installment() {
		return order_installment;
	}

	public void setOrder_installment(int order_installment) {
		this.order_installment = order_installment;
	}

	public String getOrder_payment() {
		return order_payment;
	}

	public void setOrder_payment(String order_payment) {
		this.order_payment = order_payment;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public int getOrder_total() {
		return order_total;
	}

	public void setOrder_total(int order_total) {
		this.order_total = order_total;
	}

	public int getOrder_coupon() {
		return order_coupon;
	}

	public void setOrder_coupon(int order_coupon) {
		this.order_coupon = order_coupon;
	}

	public int getOrder_point() {
		return order_point;
	}

	public void setOrder_point(int order_point) {
		this.order_point = order_point;
	}
}