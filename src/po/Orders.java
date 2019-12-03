package po;

public class Orders {
	private int id;
	private String userid;
	private String classid;
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
	public String getClassidid() {
		return classidid;
	}
	public void setClassidid(String classidid) {
		this.classidid = classidid;
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
		return "Orders [id=" + id + ", userid=" + userid + ", classidid=" + classidid + ", times="
				+ times + ", delivery=" + delivery + "]";
	}
}
