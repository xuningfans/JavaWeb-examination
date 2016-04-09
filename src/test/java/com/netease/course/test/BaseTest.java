package com.netease.course.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.course.service.ContentService;
import com.netease.course.service.PersonService;
import com.netease.course.service.TrxService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class BaseTest {
	
	@Autowired
	protected ContentService contentService;
	
	@Autowired
	protected TrxService trxService;
	
	@Autowired
	protected PersonService personService;

}
