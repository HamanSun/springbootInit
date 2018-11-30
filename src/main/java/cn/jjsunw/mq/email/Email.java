package cn.jjsunw.mq.email;

import lombok.Data;

@Data
public class Email {

	private String text;
	private String to;
	private String subject;

}
