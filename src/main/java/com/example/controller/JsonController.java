package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Category;
import com.example.service.CategoryService;

@RestController
@RequestMapping("")
public class JsonController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 中カテゴリーを返す
	 * 
	 * @return
	 */
	@RequestMapping("/middle")
	public List<Category> ReturnMiddleCategory(Integer id) {
		List<Category> categoryList = categoryService.searchMiddleCategory(id);
		return categoryList;
       
	}
	
	/**
	 * 小カテゴリーを返す
	 * @return
	 */
	@RequestMapping("/mini")
	public List<Category> ReturnMiniCategory(Integer id) {
		List<Category> categoryList = categoryService.searchMiniCategory(id);
		return categoryList;
	}
	
	

}
