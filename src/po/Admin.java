package po;

public class Admin {
	private int id;
	   private String name;
	   private String pwd;
	   private String authrity;
   public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAuthrity() {
		return authrity;
	}
	public void setAuthrity(String authrity) {
		this.authrity = authrity;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", pwd=" + pwd + ", authrity=" + authrity + "]";
	}
   
}
