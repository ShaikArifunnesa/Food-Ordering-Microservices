package com.online.menufoodservice.service;

import java.util.List;

import com.online.menufoodservice.dto.MenuDTO;
import com.online.menufoodservice.model.Menu;


public interface MenuService {

	boolean saveMenu(MenuDTO menudto);

	List<Menu> getAllMenu();

	Menu getByItemName(String itemName);

	String deleteByItemId(Integer itemId);

	String updateMenu(Integer itemId, MenuDTO menuDto);



	

	

	
}
