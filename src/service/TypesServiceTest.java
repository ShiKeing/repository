package service;

import static org.junit.Assert.*;


import org.junit.Test;


import po.Types;

public class TypesServiceTest {
   private TypesService typesService= new TypesService();
	@Test
	public void testchg() {
		Types types= new Types();
		System.out.println(typesService.chg(13,"川菜"));
	}
}

	
