package vo;
/*表示订单信息的视图类*/
public class OrdersInfo {
	private int id;
	private String userid;
	private String realname;
	private String phone;
	private String address;
	private String menuname;
	private String menusum;//订购数量
	private String price1;
	private String sum;//合计
	private String times;
	private String delivery;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMenusum() {
		return menusum;
	}
	public void setMenusum(String menusum) {
		this.menusum = menusum;
	}
	public String getPrice1() {
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	@Override
	public String toString() {
		return "OrdersInfo [id=" + id + ", userid=" + userid + ", realname=" + realname + ", phone=" + phone
				+ ", address=" + address + ", menuname=" + menuname + ", menusum=" + menusum + ", price1=" + price1
				+ ", sum=" + sum + ", times=" + times + ", delivery=" + delivery + "]";
	}
}
