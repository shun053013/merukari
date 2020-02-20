package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.form.ItemForm;
import com.example.service.CategoryService;
import com.example.service.ShowItemListService;

@Controller
@RequestMapping("")
public class ItemShowController {

	@Autowired
	private ShowItemListService showItemListService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * 商品一覧を表示する. ページング機能
	 * 
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String findAll(Integer startPage, Integer id, Model model) {
		if (startPage == null) {
			startPage = 0;
		}
		List<Item> itemList = showItemListService.ShowItem(startPage);
		model.addAttribute("itemList", itemList);
		model.addAttribute("startPage", startPage);

		Integer maxCount = showItemListService.Paging();
		Integer maxPage = 0;
		if (maxCount % 30 == 0) {

			maxPage = maxCount / 30;

		} else if (!(maxCount % 30 == 0)) {
			maxPage = maxCount / 30 + 1;
		}
		model.addAttribute("maxPage", maxPage);

		List<Category> categoryList = categoryService.showCategory();
		model.addAttribute("categoryList", categoryList);

		List<Category> categoryList1 = categoryService.searchMiddleCategory(id);
		model.addAttribute("categoryList1", categoryList1);

		List<Category> categoryList2 = categoryService.searchMiniCategory(id);
		model.addAttribute("categoryList2", categoryList2);

		return "list.html";

	}

	/**
	 * ページングした商品を表示する
	 * 
	 * @param startPage
	 * @param number
	 * @param model
	 * @return
	 */
	@RequestMapping("/sort")
	public String Paging(Integer startPage, Integer number, Integer id, Model model) {

		if (startPage == null) {
			startPage = 0;
		}

		Integer maxCount = showItemListService.Paging();
		Integer maxPage = 0;
		if (maxCount % 30 == 0) {

			maxPage = maxCount / 30;

		} else if (!(maxCount % 30 == 0)) {
			maxPage = maxCount / 30 + 1;
		}
		model.addAttribute("maxPage", maxPage);

		startPage = number * 30;
		List<Item> itemList = showItemListService.ShowItem(startPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("itemList", itemList);

		List<Category> categoryList = categoryService.showCategory();
		model.addAttribute("categoryList", categoryList);

		List<Category> categoryList1 = categoryService.searchMiddleCategory(id);
		model.addAttribute("categoryList1", categoryList1);

		List<Category> categoryList2 = categoryService.searchMiniCategory(id);
		model.addAttribute("categoryList2", categoryList2);

		return "list.html";

	}

	/**
	 * 商品を名前.カテゴリー名で検索するコントローラー.
	 * 
	 * @param itemForm
	 * @param startPage
	 * @param model
	 * @return
	 */
	@RequestMapping("/search")
	public String SearchByName(ItemForm itemForm, Integer startPage, Integer id, Model model) {
		System.err.println("小カテゴリーのid=" + itemForm.getMini());
		if (startPage == null) {
			startPage = 0;
		}
		model.addAttribute("startPage", startPage);

		Integer maxCount = showItemListService.Paging();
		Integer maxPage = 0;
		if (maxCount % 30 == 0) {

			maxPage = maxCount / 30;

		} else if (!(maxCount % 30 == 0)) {
			maxPage = maxCount / 30 + 1;
		}
		model.addAttribute("maxPage", maxPage);

		List<Item> itemList = showItemListService.SearchByName(itemForm, startPage);
		model.addAttribute("itemList", itemList);

		List<Category> categoryList = categoryService.showCategory();
		model.addAttribute("categoryList", categoryList);

		List<Category> categoryList1 = categoryService.searchMiddleCategory(id);
		model.addAttribute("categoryList1", categoryList1);

		List<Category> categoryList2 = categoryService.searchMiniCategory(id);
		model.addAttribute("categoryList2", categoryList2);

		return "list.html";

	}

	/**
	 * 商品詳細画面を表示する
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/showDetail")
	public String showDetail(Integer id, Model model) {

		Item item = showItemListService.SearchById(id);
		model.addAttribute("item", item);
		return "detail";

	}
	
	

}
