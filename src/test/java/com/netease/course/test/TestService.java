package com.netease.course.test;

import java.util.List;

import org.junit.Test;

import com.netease.course.entity.Product;
import com.netease.course.meta.Content;
import com.netease.course.meta.Person;
import com.netease.course.meta.Trx;

public class TestService extends BaseTest{

	@Test
	public void Test01(){
		List<Person> list = personService.selectList();
		System.out.println(list);
	}
	
	@Test
	public void Test02() throws Exception{
		Content content = new Content();
		content.setImage("C:\\Users\\Administrator\\Desktop\\book_photo\\ejb.jpg");
		content.setPrice(12d);
		content.setSummary("aaa");
		content.setDetail("aaa");
		content.setTitle("aaa");
		contentService.insert(content);
		System.out.println(content);
	}
	
	@Test
	public void Test03() throws Exception{
		Product list = contentService.getProductById(17);
		List<Product> productList = contentService.getProductList(null);
		System.out.println(productList);
		System.out.println(list.getIsBuy());
		System.out.println(list);
	}
	
	@Test
	public void Test04() throws Exception{
		Person user = new Person();
		user.setId(1);
		List<Trx> buyList = trxService.getBuyList(user);
		System.out.println(buyList);
	}
}
