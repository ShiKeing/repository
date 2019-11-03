package po;

public class CarItem {
	private int menusid;
	private String menusname;
	private String price;
	private int count;//订购数量
	public int getMenusid() {
		return menusid;
	}
	public void setMenusid(int menusid) {
		this.menusid = menusid;
	}
	public String getMenusname() {
		return menusname;
	}
	public void setMenusname(String menusname) {
		this.menusname = menusname;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CarItem [menusid=" + menusid + ", menusname=" + menusname + ", price=" + price + ", count=" + count
				+ "]";
	}
}
