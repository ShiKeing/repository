package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import po.Types;

public class TypesDaoTest {
	private TypesDao typesDao = new TypesDao();
	
	@Test
	public void testAdd() {
		Types type = new Types();
		type.setName("川菜");
		int result = typesDao.add(type);
		System.out.println(result);
	}

	@Test
	public void testDel() {
		int result = typesDao.del(100);
		System.out.println(result);
	}

	@Test
	public void testChg() {
		int result = typesDao.chg(1,"川菜");
		System.out.println(result);
	}

	@Test
	public void testFindAll() {
		ArrayList<Types> list = typesDao.findAll();
		for (Types types : list) {
			System.out.println(types);
		}
	}

	@Test
	public void testFindByName() {
		Types type = typesDao.findByName("川菜");
		System.out.println(type);
	}
}