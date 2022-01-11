package com.online.menufoodservice.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.online.menufoodservice.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

	Optional<Menu> findByItemName(String itemName);

	Menu findByItemIdEquals(Integer itemId);

	Optional<Menu> findByItemNameContaining(String itemName);


	//Menu findByItemName(String itemName);

	
	

}
