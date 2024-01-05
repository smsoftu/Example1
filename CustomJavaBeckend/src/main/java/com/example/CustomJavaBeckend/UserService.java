package com.example.CustomJavaBeckend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	
	public static List<UserEntity> list=new ArrayList<UserEntity>();
	public static int limit=0;
	public static Map<String, List<UploadEntity>> map=new HashMap<String, List<UploadEntity>>();
	
	public static void initialisation() {
		UserService.list=new ArrayList<UserEntity>();
		UserService.list.add(new UserEntity(1, "admin1","padmin1", "admin"));
		UserService.list.add(new UserEntity(2, "admin2","padmin2", "admin"));
		UserService.list.add(new UserEntity(3, "admin3","padmin3", "admin"));
		UserService.list.add(new UserEntity(4, "user1","puser1", "user"));
		UserService.list.add(new UserEntity(5, "user2","puser2", "user"));
		UserService.list.add(new UserEntity(5, "user3","puser3", "user"));
	}
	
	Resp login(LoginEntity loginEntity, String sessionId) {
		UserService.map.put(sessionId, new ArrayList<UploadEntity>());
		Resp retValue=new Resp("badLogin", "", 0, 0,"");
		for(int i=0;i<UserService.list.size();i++) {
			UserEntity user=UserService.list.get(i);
			if (user.getLogin().equals(loginEntity.getLogin())&&user.getPassword().equals(loginEntity.getPassword())) { 
				retValue = new Resp("OK",user.getRole(),UserService.limit,user.getId(),sessionId);
			}	
		}
		return retValue;
		
	}
	
	Resp setLimit(LimitEntity limitEntity) {
		Resp retValue=new Resp("badLogin", "", 0, 0,"");
		String correctRole="admin";
		for(int i=0;i<UserService.list.size();i++) {
			UserEntity user=list.get(i);
			if (user.getLogin().equals(limitEntity.getLogin())&&user.getPassword().equals(limitEntity.getPassword())&&user.getRole().equals(correctRole))
			{   UserService.limit=limitEntity.getLimit();
				UserService.map.remove(limitEntity.getSessionId());
				UserService.map.put(limitEntity.getSessionId(),new ArrayList<UploadEntity>());
				return new Resp("OK",user.getRole(),UserService.limit,user.getId(),"");
			}	
		}
		return retValue;

	}
	
	void logout(LogoutEntity logoutEntity) {
		
		UserService.map.remove(logoutEntity.getSessionId());
	}
	

	

}
