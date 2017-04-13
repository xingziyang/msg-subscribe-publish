package org.msg.publish;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.msg.publish.params.MailParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author ziyang
 *
 * @date 2017年4月12日
 */
@Service("mqProducer")
public class MQProducer {

	@Autowired
	private JmsTemplate activeMqJmsTemplate;

	/**
	 * 发送消息.
	 * 
	 * @param mail
	 */
	public void sendMessage(final MailParam mail, final Destination destination) {
		if (destination != null)
			activeMqJmsTemplate.send(destination, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(JSONObject.toJSONString(mail));
				}
			});

	}

}
