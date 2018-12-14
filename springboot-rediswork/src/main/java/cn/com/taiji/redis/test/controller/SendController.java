package cn.com.taiji.redis.test.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

	@Autowired
	private StringRedisTemplate srt;
	String flag="1";
	int i=0;

	@RequestMapping("/sendMsg")
	public String sendMsg() {
		if(i<3) {
			StringBuffer sb=new StringBuffer();
			String str=null;
			for (int i = 0; i < 5; i++) {
					long result=0;
			        result=Math.round(Math.random()*25+65);
			        sb.append(String.valueOf((char)result));
			        str=sb.toString();
			}
			srt.opsForValue().set(flag, str, 1, TimeUnit.SECONDS); 
			i++;
			return srt.opsForValue().get(flag);
		}
		if (!srt.hasKey(flag)) {
		     	  return "请稍后尝试 "; 
		}
		else {
            return srt.opsForValue().get(flag);
		}
	}

}
