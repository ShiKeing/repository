package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import po.Notice;
import po.Types;
import vo.MenusInfo;

public class NoticeDaoTest {
	private NoticeDao dao = new NoticeDao();
	@Test
	public void testFindAll() {
   ArrayList<Notice> list = dao.findAll();
		for (Notice notice : list) {
			System.out.println(notice);
		}
	}
	@Test
	public void testDel() {
		int result = dao.del(7);
		System.out.println(result);
	}
	@Test
	public void testAdd() {
		Notice notice= new Notice();
		notice.setName("今日份的好吃");
		notice.setContent("班车部分if和二号发货の収我发会儿腹黑符号i");
		notice.setTimes("2018-9-13");
		int result = dao.add(notice);
		System.out.println(result);
	}


}
