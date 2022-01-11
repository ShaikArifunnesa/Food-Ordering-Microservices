package com.online.menufoodservice.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.menufoodservice.dto.MenuDTO;
import com.online.menufoodservice.exception.ItemExistException;
import com.online.menufoodservice.model.Menu;
import com.online.menufoodservice.repo.MenuRepository;
import com.online.menufoodservice.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	MenuRepository repository;

	@Override
	public boolean saveMenu(@Valid @NotEmpty MenuDTO menudto) {
		Optional<Menu> optionalMenu= repository.findByItemName(menudto.getItemName());
		if(optionalMenu.isPresent())
		{
			throw new ItemExistException( menudto.getItemName()+ " already exist in menu ");
		}
		Menu menu = new Menu();
		BeanUtils.copyProperties(menudto, menu);
		Menu savedMenu=repository.save(menu);
		if(savedMenu != null)
			{
			return true;
			}
		else {
			return false;
		}
}

	@Override
	public List<Menu> getAllMenu() {
		List<Menu> menuList=new ArrayList<>();
		Iterator it=repository.findAll().iterator();
		while(it.hasNext())
		{
			Menu mlist=new Menu();
			BeanUtils.copyProperties(it.next(), mlist);
			menuList.add(mlist);
		}
		return menuList;
		
		
	}
	@Override
	public Menu getByItemName(String itemName) {
		
		Menu menu1=repository.findByItemNameContaining(itemName).get();
		return menu1;
	}
	
	@Override
	public String deleteByItemId(Integer itemId) {
		repository.deleteById(itemId);
		return "Item deleted from the menuList";
		
	}

	@Override
	public String updateMenu(Integer itemId, MenuDTO menuDto) {
		
		Menu menuItem=repository.findByItemIdEquals(itemId);
		
		BeanUtils.copyProperties(menuDto, menuItem);
		repository.save(menuItem);
		
		return "Item updated successfully";
	}

	
		
}
