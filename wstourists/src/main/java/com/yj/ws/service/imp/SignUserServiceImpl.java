package com.yj.ws.service.imp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.ws.dao.CapitalMapper;
import com.yj.ws.dao.SignUserMapper;
import com.yj.ws.dao.UserMapper;
import com.yj.ws.dao.WithDrawRecordMapper;
import com.yj.ws.pojo.Capital;
import com.yj.ws.pojo.SignUser;
import com.yj.ws.pojo.User;
import com.yj.ws.pojo.WithDrawRecord;
import com.yj.ws.service.SignUserService;
import com.yj.ws.util.common.DateUtil;

@Service("signUserService")
public class SignUserServiceImpl implements SignUserService{
	@Autowired
	private SignUserMapper signUserMapper;
	@Autowired
	private CapitalMapper capitalMapper;
	@Autowired
	private WithDrawRecordMapper recordMapper;
	@Autowired
	private UserMapper userMapper;
	/**
	 *用户签到
	 */
	public void inserSign(SignUser signUser,User user){
		//得到金额
		Capital capital=new Capital();
		capital.setCaclss(2);
		capital.setBestClass(4);
		List<Capital> capitalList=capitalMapper.selectCapital(capital);
		BigDecimal money=new BigDecimal("0.00");
		if(capitalList!=null){
			money=capitalList.get(0).getCmoney();
		}
		signUserMapper.inserSign(signUser);        //插入签到表
		
		//总获取,与推荐金额增加
		user.setTotalMoney(user.getTotalMoney().add(money));
		user.setEarnMoney(user.getEarnMoney().add(money));
		signUser.setSignTotal(1);
		signUser.setFback1("");
		int signCount=signUserMapper.selectSignCount(signUser);
		if(signCount==4){
			user.setFansNumber(user.getFansNumber()+50);
		}
		if(signCount>=5){
			signUserMapper.updateSign(user.getUserId());
		}
		WithDrawRecord record=new WithDrawRecord();
		record.setRecordClss(2);               //签到
		record.setRecordMomey(money);          //金额
		record.setRecordTime(signUser.getSignTime());
		record.setUserId(signUser.getUserId());
		record.setInorout(1);     					//1收入   2支出
		recordMapper.insertRecord(record);             //增加资金记录
		userMapper.updateUserMoney(user);  
	}
	/**
	 * 签到金额
	 */
	public BigDecimal getMoney(Capital capital){
		List<Capital> capitalList=capitalMapper.selectCapital(capital);
		BigDecimal money=new BigDecimal("0.00");
		if(capitalList!=null){
			money=capitalList.get(0).getCmoney();
		}
		return money;
	}
	/**
	 * 得到签到
	 */
	public List<SignUser> selectSign(Map map){
		return signUserMapper.selectSign(map);
	}
	/**
	 * 得到签到
	 */
	public Integer selectSignCount(SignUser signUser){
		return signUserMapper.selectSignCount(signUser);
	}
	public List<SignUser> selectSignTotal(SignUser signUser){
		return signUserMapper.selectSignTotal(signUser);
	}
}






