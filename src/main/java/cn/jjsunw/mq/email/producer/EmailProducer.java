package cn.jjsunw.mq.email.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jjsunw.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailProducer {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	/**
	 * 发送邮件任务存入消息队列
	 *
	 * @param message
	 * @throws Exception
	 */
	public void sendEmailMsg(String message) throws Exception {
		log.info("Send email message to MQ");
		rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_EXCHANGE, RabbitMQConfig.EMAIL_ROUTEKEY, message);

	}

}
