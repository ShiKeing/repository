package po;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;

public class ZhuCe {
private int id;
 private String name;
 private String pwd;
 private String realname;
 private String sex;
 private String age;
 private String card ;
 private String address;
 private String phone;
 private String email;
 private String code;
 private String type;
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
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public String getCard() {
	return card;
}
public void setCard(String card) {
	this.card = card;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
@Override
public String toString() {
	return "ZhuCe [id=" + id + ", name=" + name + ", pwd=" + pwd + ", realname=" + realname + ", sex=" + sex + ", age="
			+ age + ", card=" + card + ", address=" + address + ", phone=" + phone + ", email=" + email + ", code="
			+ code + ", type=" + type + "]";
}

}
