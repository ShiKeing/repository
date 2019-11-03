package service;

import java.util.ArrayList;

import dao.TypesDao;
import po.Types;

public class TypesService {
    //添加
	private TypesDao TypesDao= new TypesDao();
	public int add(Types types)
	{
     //判断是否已存在
		if(TypesDao.findByName(types.getName())!=null)
		{
			System.out.println(111);
			return (-1);
		}
		System.out.println(222);
		return TypesDao.add(types);
	}
	public int del(int id)
	{
		return TypesDao.del(id);
	}
	public int chg(int id,String name)
	{
		Types types=TypesDao.findByName(name);
		if(types !=null && types.getId() !=id)
		{
			return -1;
		}
		return TypesDao.chg(id, name);
	}
	public ArrayList <Types>findAll()
	{
		return TypesDao.findAll();
	}
	public Types findById(int id)
	{
		return TypesDao.findById(id);
	}
}
