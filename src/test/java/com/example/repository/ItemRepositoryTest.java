package com.example.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemRepositoryTest {
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private JdbcTemplate template;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Item item =  new Item();
		
		item.setName("東");
		item.setBrandName("rakus");
		item.setCategoryId(2);
		item.setPrice(50.0);
		item.setItemConditionId(2);
		item.setItemDescription("新しい商品です");
		itemRepository.insert(item);
		
		java.util.Map<String, Object> create = template.queryForMap("select name,category_id,brand_name,price,item_condition_id,item_description from items where name = '東' ");
		
		
		assertThat(create.get("name"), is("東"));
		assertThat(create.get("brand_name"), is("rakus"));
	    assertThat(create.get("category_id"), is(2));
	    assertThat(create.get("price"), is(50.0));
	    assertThat(create.get("item_condition_id"), is(2));
	    assertThat(create.get("item_description"),is("新しい商品です"));
	    
	}

}
