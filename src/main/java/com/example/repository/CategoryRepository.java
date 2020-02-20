package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;
import com.example.form.ItemForm;

@Repository
public class CategoryRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Category>CATEGORY_ROW_MAPPER = (rs,i) ->{
		Category category = new Category();
		category.setId(rs.getInt("id"));
		category.setName(rs.getString("name"));
		category.setNameAll(rs.getString("name_all"));
		category.setParentId(rs.getInt("parent_id"));
		return category;
	};
	
	
	
	/**
	 * 大カテゴリーを検索するリポジトリ
	 * @return
	 */
	public List<Category> searchByCategory(){
		String sql = "select DISTINCT id ,name,name_all,parent_id from category where parent_id is null ";
		List<Category> categoryList = template.query(sql, CATEGORY_ROW_MAPPER);
		System.err.println(categoryList);
		return categoryList;
		
	}
	
	/**
	 * 中カテゴリーを検索するリポジトリ
	 * @return
	 */
	public List<Category> searchMiddleCategory(Integer id){
		String sql = "select DISTINCT id,name,name_all,parent_id from category where parent_id =:id";
		SqlParameterSource param = new  MapSqlParameterSource().addValue("id", id);
		List<Category> categoryList = template.query(sql,param ,CATEGORY_ROW_MAPPER);
		return categoryList;
	}
	 
	/**
	 * 小カテゴリーを検索するリポジトリ
	 * @return
	 */
	public List<Category> searchMiniCategory(Integer id){
		String sql = "select DISTINCT id,name,name_all,parent_id from category where  parent_id =:id"; 
		SqlParameterSource param = new  MapSqlParameterSource().addValue("id", id);
		List<Category> categoryList = template.query(sql,param,CATEGORY_ROW_MAPPER );
	    return categoryList;
    }
	
	
	

}
