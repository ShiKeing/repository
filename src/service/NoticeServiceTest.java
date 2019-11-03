package service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dao.NoticeDao;
import po.Notice;
import po.Types;
import vo.MenusInfo;

public class NoticeServiceTest {
	 private NoticeService noticeService= new NoticeService();
	@Test
	public void testAdd() {
		
	}

	@Test
	public void testFindAll() {
   ArrayList<Notice> list = noticeService.findAll();
		for(Notice notice:list)
		{
			System.out.println(notice);
		}
	}

	@Test
	public void testDel() {
		fail("Not yet implemented");
	}

}
