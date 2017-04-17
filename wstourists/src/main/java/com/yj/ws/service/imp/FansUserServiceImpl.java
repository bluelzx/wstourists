package com.yj.ws.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.ws.dao.FansUserMapper;
import com.yj.ws.dao.UserMapper;
import com.yj.ws.pojo.FansUser;
import com.yj.ws.pojo.User;
import com.yj.ws.service.FansUserService;

@Service("fansUserService")
public class FansUserServiceImpl implements FansUserService{
	@Autowired
	private FansUserMapper fansUserMapper;
	@Autowired
	private UserMapper userMapper;
	
	public void insertFuns(FansUser fansUser){
		fansUserMapper.insertFuns(fansUser);
	}
	public List<FansUser> getFans(Map map){
		return fansUserMapper.getFans(map);
	}
	public void updateFans(Map map){
		fansUserMapper.updateFans(map);
	}
	public List<FansUser> insertFansAndGet(FansUser fansUser,User user,int vip){
		Map<String,Object> map=new HashMap<String,Object>();
		fansUserMapper.insertFuns(fansUser);    //把粉丝加入到粉丝表
		//得到刚刚增加的粉丝
		map.put("fansUserId", user.getUserId());
		map.put("isdelete", 2);
		List<FansUser> fansList=fansUserMapper.getFans(map);
		fansUserMapper.updateFans(map);
		//user.setFansNumber(user.getFansNumber()-50);
		int fansUsers=user.getFansNumber()-fansList.size();
		if(fansUsers<0){
			fansUsers=0;
		}
		//如果是VIP 粉丝数目不减少
		if(vip==0){
			Long userid=user.getUserId();
			User users=new User();
			users.setFansNumber(fansUsers);
			users.setUserId(userid);
			userMapper.updateUserMoney(users);
		}
		return fansList;
	}
}


