package com.example.CustomJavaBeckend;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class UserServiceTest {

	@Test
	void test() {
		UserService us=new UserService();
		LoginEntity le=new LoginEntity();
		String sessionId="fooId";
		le.setLogin("admin1");
		le.setPassword("padmin1");
		Resp resp = us.login(le, sessionId);
		assertTrue(resp.getStatus().equals("OK"));
		assertTrue(resp.getRole().equals("admin"));
		le.setLogin("admin1");
		le.setPassword("padmin1x");
		resp = us.login(le, sessionId);
		assertTrue(resp.getStatus().equals("badLogin"));
		le.setLogin("user1");
		le.setPassword("puser1");
		resp = us.login(le, sessionId);
		assertTrue(resp.getRole().equals("user"));
		le.setLogin("admin1");
		le.setPassword("padmin1");
		resp = us.login(le, sessionId);
		assertTrue(resp.getLimit()==0);
		
		LimitEntity lime=new LimitEntity();
		lime.setLimit(2);
		lime.setLogin(le.getLogin());
		lime.setPassword(le.getPassword());
		lime.setSessionId(sessionId);
		
		resp=us.setLimit(lime);
		
		assertTrue(resp.getLimit()==2);
		assertTrue(resp.getStatus().equals("OK"));
		
		lime.setLogin("admin1");
		lime.setPassword("padmin1x");
		
		resp=us.setLimit(lime);
		
		assertTrue(resp.getStatus().equals("badLogin"));

		lime.setLogin("user1");
		lime.setPassword("puser1");
		
		resp=us.setLimit(lime);
		
		assertTrue(resp.getStatus().equals("badLogin"));

		List<UploadEntity> list = UserService.map.get(sessionId);
		
		assertTrue(list!=null);
		
		LogoutEntity loge=new LogoutEntity();
		loge.setSessionId(sessionId);
		us.logout(loge);
		
		list = UserService.map.get(sessionId);
		assertTrue(list==null);


	}

}
