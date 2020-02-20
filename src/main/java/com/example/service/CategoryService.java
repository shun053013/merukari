package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Category;
import com.example.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/** 大カテゴリーを検索するサービス.
	 * @param parentId
	 * @return
	 */
	public List<Category> showCategory(){
		List<Category> categoryList = categoryRepository.searchByCategory();
		return categoryList;
			
		}
	
	/**
	 *中カテゴリーを検索するサービス.
	 * @return
	 */
	public List<Category> searchMiddleCategory(Integer id){
		return  categoryRepository.searchMiddleCategory(id);
	}
	
	/**
	 * 小カテゴリーを検索するサービス.
	 * @return
	 */
	public List<Category> searchMiniCategory(Integer id){
		return categoryRepository.searchMiniCategory(id);
	}

}
