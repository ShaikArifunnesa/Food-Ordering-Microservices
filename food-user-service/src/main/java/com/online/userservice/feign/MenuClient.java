package com.online.userservice.feign;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.online.userservice.Dto.Menu;

@FeignClient("MENUSERVICE")
public interface MenuClient {
	
	@GetMapping("/menus/getAll")
	public ResponseEntity<List> getMenus();
	
	@GetMapping("/menus/port")
	public ResponseEntity<Integer> getPort();
	
	@GetMapping("/menus/{itemName}")
	public ResponseEntity<Menu> getItemByName(@PathVariable String itemName);
	
	
	
}
