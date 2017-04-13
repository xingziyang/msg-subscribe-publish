package org.msg.subscribe.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.msg.subscribe.biz.MailBiz;
import org.msg.subscribe.params.MailParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author ziyang
 *
 * @date 2017年4月12日
 */
public class ConsumerMessageListener3 implements MessageListener {

	private static final Log log = LogFactory.getLog(ConsumerMessageListener3.class);

	@Autowired
	private MailBiz mailBiz;

	public void setMailBiz(MailBiz mailBiz) {
		this.mailBiz = mailBiz;
	}

	@Override
	public void onMessage(Message message) {
		ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
		String ms;
		try {
			ms = msg.getText();
			log.info("==>receive message3:" + ms);
			MailParam mailParam = JSONObject.parseObject(ms, MailParam.class);// 转换成相应的对象
			if (mailParam == null) {
				return;
			}

			try {
				// 发送邮件测试
				// mailBiz.mailSend(mailParam);
			} catch (Exception e) {
				// 发送异常，重新放回队列
				// activeMqJmsTemplate.send(sessionAwareQueue, new MessageCreator() {
				// public Message createMessage(Session session) throws JMSException {
				// return session.createTextMessage(ms);
				// }
				// });
				log.error("==>MailException1:", e);
			}
		} catch (JMSException e1) {
			e1.printStackTrace();
		}

	}

}
