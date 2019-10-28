package com.mmcro.cms.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.poi.hssf.record.CalcCountRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mmcro.utils.StringUtils;

/**
 * 
 * @author zhuzg
 *
 */
public class TestComment  extends BaseTest{
	
	/**
	 * 
	 */
	@Autowired
	ArticleService articleService;
	
	/**
	 * 
	 */
	@Test
	public void testComment() {
		
		int articleIds[]= {57,58,28,23,59,69,33,45,28,48};
		Random random = new Random();
		
		// 获取当前的时间
		Calendar calendar = Calendar.getInstance();
		// 获取今天
		Date time = calendar.getTime();
		
		
		
		//System.out.println("time2 is " + time2);
		
		
		
		
		

		
		
		
		
		for (int i = 0; i < 1000; i++) {

			//2019-1-1 00:00:00
			calendar.set(Calendar.YEAR, 2019);
			calendar.set(Calendar.MONTH, random.nextInt(11));
			calendar.set(Calendar.DAY_OF_MONTH, random.nextInt(32));
			calendar.set(Calendar.HOUR, random.nextInt(24));
			calendar.set(Calendar.MINUTE, random.nextInt(60));
			calendar.set(Calendar.SECOND, random.nextInt(60));
			Date time2 = calendar.getTime();
			articleService.comment(46, articleIds[random.nextInt(10)], 
					StringUtils.randomCharAndNumber(120),time2 );
		}
		/*for (int i = 51; i < 61; i++) {
			for (int j = 0; j < 100; j++) {
				//Comment comment = new Comment();
				articleService.comment(46, i, " 测试发布评论 ，文章id是 [" + i + "] 第 "+j+"次发布"   );
				
			}
		}*/
	}
	
	
}
