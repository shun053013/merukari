package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.form.ItemForm;
import com.example.repository.CategoryRepository;
import com.example.repository.ItemRepository;

@Service
@Transactional
public class ShowItemListService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * 商品情報を全件検索するするサービス
	 * 
	 * @param startPage
	 * @return
	 */
	public List<Item> ShowItem(Integer startPage) {

		List<Item> itemList = itemRepository.findAll(startPage);
		return itemList;

	}

	/**
	 * 商品数の合計数を求めるページングメソッド
	 * 
	 * @return
	 */
	public Integer Paging() {
		return itemRepository.Paging();

	}

	/**
	 * 曖昧検索するメソッド
	 * 
	 * @param itemForm
	 * @param startPage
	 * @return
	 */
	public List<Item> SearchByName(ItemForm itemForm, Integer startPage) {
		return itemRepository.searchByname(itemForm, startPage);
	}

	/**
	 * 商品情報を１行検索する
	 * @param id
	 * @return
	 */
	public Item SearchById(Integer id) {
		return itemRepository.load(id);
		}


}
