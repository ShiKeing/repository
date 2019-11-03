package service;

import java.util.ArrayList;

import dao.NoticeDao;
import po.Notice;
import po.Types;

public class NoticeService {
	private NoticeDao noticeDao=new NoticeDao();
	public int add(Notice notice)
	{
		return noticeDao.add(notice);
	}
	public ArrayList<Notice> findAll()
	{
		return noticeDao.findAll();
	}
	public int del(int id)
	{
		return noticeDao.del(id);
	}
}
