package service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.javafx.scene.control.skin.EmbeddedTextContextMenuContent;

import po.Menus;

public class MenusServiceTest {
   private MenusService menusService=new MenusService();
	@Test
	public void testAddInt() {
		Menus menus=new Menus();
		menus .setName("wqeqw");
		System.out.println(menusService.add(menus));
	
	}

	@Test
	public void testChg() {
		Menus menus=new Menus();
		menus.setName("lia");
		System.out.println(menusService.chg(12,menus));
		
	}

}
