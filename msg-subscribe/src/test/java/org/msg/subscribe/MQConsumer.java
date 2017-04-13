package org.msg.subscribe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author ziyang
 *
 * @date 2017年4月12日
 */
public class MQConsumer {
	private static final Log log = LogFactory.getLog(MQConsumer.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
			System.out.println("消息监听启动成功！");
		} catch (Exception e) {
			log.error("==>MQ context start error:", e);
			System.exit(0);
		}
	}
}
