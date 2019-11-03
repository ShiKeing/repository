package po;

public class User {
   private int id;
   private String name;
   private String pwd;
   private String realname;
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
public String getRealname() {
	return realname;
}
public void setRealname(String realname) {
	this.realname = realname;
}
@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", realname=" + realname + "]";
}
   
}
