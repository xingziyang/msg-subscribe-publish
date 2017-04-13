package org.msg.publish;

import javax.jms.Destination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.msg.publish.params.MailParam;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author ziyang
 *
 * @date 2017年4月12日
 */
public class MQProducerMessage {
	private static final Log log = LogFactory.getLog(MQProducerMessage.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
			MQProducer mqProducer = (MQProducer) context.getBean("mqProducer");
			Destination topic1 = (Destination) context.getBean("topic1");
			Destination topic2 = (Destination) context.getBean("topic2");
			// 邮件内容
			MailParam mail = new MailParam();
			mail.setTo("ziyanghenan@126.com");
			mail.setSubject("ActiveMQ消息发送测试（topic1）");
			mail.setContent("通过ActiveMQ异步发送邮件（topic1）！");
			// 发送队列一消息
			sendMsgToQueneName1(mqProducer, mail, topic1);
			System.out.println("topic1消息发送成功！");

			mail.setSubject("ActiveMQ消息发送测试（topic2）");
			mail.setContent("通过ActiveMQ异步发送邮件（topic2）！");
			// 发送队列二消息
			sendMsgToQueneName1(mqProducer, mail, topic2);
			System.out.println("topic2消息发送成功！");
		} catch (Exception e) {
			log.error("==>MQ context start error:", e);
			System.exit(0);
		} finally {
			log.info("===>System.exit");
			System.exit(0);
		}
	}

	private static void sendMsgToQueneName1(MQProducer mqProducer, MailParam mail, Destination topic) {
		if (mqProducer != null) {
			mqProducer.sendMessage(mail, topic);
		}
	}
}
