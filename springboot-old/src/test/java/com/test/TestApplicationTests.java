package com.test;

import com.test.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

	@Test
	public void contextLoads() {
	}

	/*@Autowired
	RedisDao redisDao;

	@Test
	public void testRedis(){
		redisDao.setKey("name","test");
		System.out.println(redisDao.getValue("name"));
	}*/

}
