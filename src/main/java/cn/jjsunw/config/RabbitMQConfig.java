package cn.jjsunw.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String EMAIL_QUENE_NAME = "email";// 队列名称
	public static final String EMAIL_EXCHANGE = "email_exchange";// 交换器名称
	public static final String EMAIL_ROUTEKEY = "123456@email";

	public static final String LOG_QUENE_NAME = "log";// 队列名称
	public static final String LOG_EXCHANGE = "log_exchange";// 交换器名称
	public static final String LOG_ROUTEKEY = "123456@log";

	@Bean
	Queue emailQueue() {
		// 是否持久化
		boolean durable = true;
		// 仅创建者可以使用的私有队列，断开后自动删除
		boolean exclusive = false;
		// 当所有消费客户端连接断开后，是否自动删除队列
		boolean autoDelete = false;

		return new Queue(EMAIL_QUENE_NAME, durable, exclusive, autoDelete);
	}

	@Bean
	TopicExchange emailExchange() {
		// 是否持久化
		boolean durable = true;
		// 当所有消费客户端连接断开后，是否自动删除队列
		boolean autoDelete = false;

		return new TopicExchange(EMAIL_EXCHANGE, durable, autoDelete);
	}

	@Bean
	Binding emailBinding() {// 绑定
		return BindingBuilder.bind(emailQueue()).to(emailExchange()).with(EMAIL_ROUTEKEY);
	}

	@Bean
	Queue logQueue() {
		// 是否持久化
		boolean durable = true;
		// 仅创建者可以使用的私有队列，断开后自动删除
		boolean exclusive = false;
		// 当所有消费客户端连接断开后，是否自动删除队列
		boolean autoDelete = false;

		return new Queue(LOG_QUENE_NAME, durable, exclusive, autoDelete);
	}

	@Bean
	TopicExchange logExchange() {
		// 是否持久化
		boolean durable = true;
		// 当所有消费客户端连接断开后，是否自动删除队列
		boolean autoDelete = false;

		return new TopicExchange(LOG_EXCHANGE, durable, autoDelete);
	}

	@Bean
	Binding logBinding() {// 绑定
		return BindingBuilder.bind(logQueue()).to(logExchange()).with(LOG_ROUTEKEY);
	}
}
