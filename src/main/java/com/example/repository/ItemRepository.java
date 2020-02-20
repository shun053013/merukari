package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.form.ItemForm;

/**
 * @author shun053012
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {

		Item item = new Item();
		item.setId(rs.getInt("i_id"));
		item.setName(rs.getString("i_name"));
		item.setItemConditionId(rs.getInt("i_item_condition_id"));
		item.setPrice(rs.getDouble("i_price"));
		item.setCategoryId(Integer.parseInt(rs.getString("i_category_id")));
		item.setBrandName(rs.getString("i_brand_name"));
		item.setShipping(rs.getInt("i_shipping"));
		item.setItemDescription(rs.getString("i_item_description"));

		Category category = new Category();
		category.setId(rs.getInt("c_id"));
		category.setName(rs.getString("c_name"));
		category.setParentId(rs.getInt("c_parent_id"));
		category.setNameAll(rs.getString("c_name_all"));
		item.setCategory(category);
		return item;
	};

	/**
	 * ページ毎の商品情報を検索するリポジトリ
	 * 
	 * @param startPage
	 * @return
	 */
	public List<Item> findAll(Integer startPage) {
		String sql = "select c.id c_id,c.name c_name,c.parent_id c_parent_id,c.name_all c_name_all,i.id i_id,i.name i_name,i.item_condition_id i_item_condition_id,i.category_id i_category_id,i.brand_name i_brand_name,i.price i_price,i.shipping i_shipping,i.item_description i_item_description from category c inner join items i on c.id = i.category_id LIMIT 30 OFFSET :startPage";
		SqlParameterSource param = new MapSqlParameterSource().addValue("startPage", startPage);
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;

	}

	/**
	 * 商品の合計数を求めるリポジトリ
	 * 
	 * @return
	 */
	public Integer Paging() {
		String sql = " select count(*) from items ";
		SqlParameterSource param = new MapSqlParameterSource();
		return template.queryForObject(sql, param, Integer.class);
	}

	/**
	 * 名前で曖昧検索するリポジトリ
	 * 
	 * @param itemForm
	 * @param startPage
	 * @return
	 */
	public List<Item> searchByname(ItemForm itemForm, Integer startPage) {
		String NameSql = "";
		String MiniSql = "";
		String BigAndMiddleSql = "";
		String BigSql = "";
		String BrandSql = "";
		String name = itemForm.getName();
		String brand = itemForm.getBrand();
		String searchBrand = itemForm.getBrand();
		Integer searchBig = itemForm.getBig();
		Integer searchMiddle = itemForm.getMiddle();
		Integer searchMini = itemForm.getMini();
		String searchName = itemForm.getName();

		// 条件式に名前を指定したsql
		if (!(searchName.equals(""))) {
			NameSql = " where i.name Ilike :searchName ";
		}

		// 条件式に大・中・小カテゴリーを指定したsql
		if (!(searchBig == 0) && !(searchMiddle == 0) && !(searchMini == 0) && ((name == "") && (brand == ""))) {
			MiniSql = " where i.category_id = :searchMini ";
		}
		if (!(searchBig == 0) && !(searchMiddle == 0) && !(searchMini == 0) && !((name == "") && (brand == ""))) {
			MiniSql = " and i.category_id = :searchMini";
		} else {

		}

		// 条件式に大・中カテゴリーを指定したsql
		if (!(searchBig == 0) && !(searchMiddle == 0) && (searchMini == 0) && ((name == "") && (brand == ""))) {
			BigAndMiddleSql = " where c.parent_id = :searchMiddle ";

		}
		if (!(searchBig == 0) && !(searchMiddle == 0) && (searchMini == 0) && !((name == "") && (brand == ""))) {
			BigAndMiddleSql = " and c.parent_id = :searchMiddle ";
		} else {

		}

		// 条件式に大カテゴリーを指定したsql
		if (!(searchBig == 0) && (searchMiddle == 0) && (searchMini == 0) && ((name == "") && (brand == ""))) {
			BigSql = " where c.parent_id IN (select c.id  from category  c where c.parent_id = :searchBig)";
		}
		if (!(searchBig == 0) && (searchMiddle == 0) && (searchMini == 0) && !((name == "") && (brand == ""))) {
			BigSql = " and c.parent_id id IN (select c.id  from category  c where c.parent_id = :searchBig)";
		} else {

		}

		// 条件式にブランドを指定したsql
		if (!(searchBrand.equals("")) && !(searchName.equals(""))) {
			BrandSql = " and  i.brand_name Ilike :searchBrand ";
		} else if (!(searchBrand.equals("")) && (searchName.equals(""))) {
			BrandSql = " where i.brand_name Ilike :searchBrand";
		} else {

		}

		String sql = " select  c.id c_id,c.name c_name,c.parent_id c_parent_id,c.name_all c_name_all ,i.id i_id,i.name i_name,i.item_condition_id i_item_condition_id,i.category_id i_category_id,i.brand_name i_brand_name,i.price i_price,i.shipping i_shipping,i.item_description i_item_description from category c inner join items i on c.id = i.category_id "
				+ NameSql + BrandSql + MiniSql + BigAndMiddleSql + BigSql + " LIMIT 30 OFFSET :startPage ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("searchName", '%' + searchName + '%')
				.addValue("searchBrand", '%' + searchBrand + '%').addValue("startPage", startPage)
				.addValue("searchMini", searchMini).addValue("searchMiddle", searchMiddle)
				.addValue("searchBig", searchBig);
		return template.query(sql, param, ITEM_ROW_MAPPER);

	}
	  
	  /**
	   * idから商品詳細情報を検索するリポジトリ
	 * @param id
	 * @return
	 */
	public Item load(Integer id) { 
		  String sql = "select  c.id c_id,c.name c_name,c.parent_id c_parent_id,c.name_all c_name_all ,i.id i_id,i.name i_name,i.item_condition_id i_item_condition_id,i.category_id i_category_id,i.brand_name i_brand_name,i.price i_price,i.shipping i_shipping,i.item_description i_item_description from category c inner join items i on c.id = i.category_id where i.id =:id";
		  SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
		  return template.queryForObject(sql, param, ITEM_ROW_MAPPER);
	  }
	
	
	/**
	 * 新しい商品情報を追加するリポジトリ.
	 * @param item
	 */
	public void insert(Item item) {
		 String sql = "insert into items (name,category_id,brand_name,price,item_condition_id,item_description) values(:name,:categoryId,:brandName,:price,:itemConditionId,:itemDescription) ";
		 SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		 template.update(sql, param);
	}

}
