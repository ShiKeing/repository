package po;
/*表示订单搜索条件的实体类*/
public class OrdersSearch {
	private String userid;
	private String name;
	private String date;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "OrdersSearch [userid=" + userid + ", name=" + name + ", date=" + date + "]";
	}
}
