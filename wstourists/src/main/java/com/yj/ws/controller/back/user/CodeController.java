package com.yj.ws.controller.back.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yj.ws.util.common.CommonConstant;



@Controller
@RequestMapping("/admin/code")
public class CodeController {
	
	public static final Integer NUM = 4;
	@RequestMapping("/getCode")
	public void getCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.addHeader("pragma", "no-cache");
		response.addHeader("cache-control", "no-cache");
		response.addHeader("expires", "0");

  
		//System.out.println("image");
		char[] rands = getRandom();
		String temp  = new String(rands);
		request.getSession().setAttribute(CommonConstant.VERIFY_CODE, temp);

		//产生图片缓冲�?
		BufferedImage bufferedImage = new BufferedImage(80,20,BufferedImage.TYPE_INT_RGB);
		//得到缓图对象
		Graphics g  = bufferedImage.getGraphics();
		//设置背景�?
		g.setColor(Color.lightGray);
		//填充
		g.fillRect(0, 0, 80, 20);
 
		//写字
		g.setColor(Color.blue);
		g.setFont(new Font("宋体",Font.BOLD,22));
		g.drawString(rands[0]+"", 0, 16);
		g.drawString(rands[1]+"", 20, 15);
		g.drawString(rands[2]+"", 40, 14);
		g.drawString(rands[3]+"", 60, 16);
 
		//四根�?
		Random r = new Random();
		g.setColor(Color.pink);
		g.drawLine(r.nextInt(80), r.nextInt(20),r.nextInt(80), r.nextInt(20));
		g.drawLine(r.nextInt(80), r.nextInt(20),r.nextInt(80), r.nextInt(20));
		g.drawLine(r.nextInt(80), r.nextInt(20),r.nextInt(80), r.nextInt(20));
		g.drawLine(r.nextInt(80), r.nextInt(20),r.nextInt(80), r.nextInt(20));
		//生成jpeg
		ImageIO.write(bufferedImage, "jpeg", response.getOutputStream());
		
	}
	public char[] getRandom()
	{
		//源始的内�?
		String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		//存放随机�?
		char[] rands = new char[NUM];
		Random  random   = new Random();
		for (int i=0;i<NUM;i++)
		{
			int index = random.nextInt(62);
			rands[i] = str.charAt(index);
		}
		return rands;
	}
	@RequestMapping("/judCode")
	public void judCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String code=request.getParameter("codes");
		String  temp =  (String) request.getSession().getAttribute(CommonConstant.VERIFY_CODE);
		String bz="0";
		if(code!=null && code.trim().length()>0){
			if(code.equalsIgnoreCase(temp)){
				bz="1";
			}else{
				bz="2";//验证码错�?
			}
		}else{
			bz="2";//验证码错�?
		}
		//this.writeResult(true, bz, this.getResponse());
		JSONObject json=new JSONObject();
		json.put("bz", bz);
	}
	
}








