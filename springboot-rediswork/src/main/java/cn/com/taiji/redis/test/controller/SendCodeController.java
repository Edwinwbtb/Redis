package cn.com.taiji.redis.test.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

@Controller
public class SendCodeController {

	private int num=0;
    //开始页面*****************************
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}
	//获取验证码方法
	@RequestMapping("/getCode")
	public String getCode() {
		num++;
		if(num<=3) {
			return "/showCode";
		}else {
			num=0;
			return "/toWait";
		}
	}
	//显示验证码方法
	@RequestMapping("/showCode")
	@ResponseBody
	public String showCode() {
		StringBuffer sb=new StringBuffer();
		String str=null;
		Random random =new Random();
		for (int i = 0; i < 5; i++) {
			int number=random.nextInt(3);
		      long result=0;
		        result=Math.round(Math.random()*25+65);
		        sb.append(String.valueOf((char)result));
		        str=sb.toString();
		}
		Jedis jedis=new Jedis();
		jedis.setex("key", 60, str);
		String code=jedis.get("key");
			return code;
	}
	//进入等待页面
	@RequestMapping("/toWait")
	public String go() {
		return "wait.html";
	}
	
}
