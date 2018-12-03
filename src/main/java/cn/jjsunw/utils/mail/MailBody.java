package cn.jjsunw.utils.mail;

import lombok.Data;

@Data
public class MailBody {
	private String messageCode;

    private String messageStatus;

    private String cause;
}
