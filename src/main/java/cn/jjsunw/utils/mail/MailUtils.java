package cn.jjsunw.utils.mail;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Component
public class MailUtils {
	private static final String EMAIL_PATTERN = "^\\w+@(\\w+\\.)+\\w+$";
	private static final String MAIL_TEMPLATE_DEFAULT = "message.ftl";
	// 邮件的发送者
	private static String from;

	// 注入MailSender
	private static JavaMailSender mailSender;

	// 发送邮件的模板引擎
	private static FreeMarkerConfigurer configurer;

	public MailUtils(JavaMailSender sender, FreeMarkerConfigurer configurer) {
		MailUtils.mailSender = sender;
		MailUtils.configurer = configurer;
	}

	@Value("${spring.mail.from}")
	public void setFrom(String sendAddress) {
		MailUtils.from = sendAddress;
	}

	/**
	 * @param params
	 *            发送邮件的主题对象 object
	 * @param title
	 *            邮件标题
	 * @param templateName
	 *            模板名称
	 * @throws MessagingException
	 * @throws IOException
	 * @throws ParseException
	 * @throws MalformedTemplateNameException
	 * @throws TemplateNotFoundException
	 * @throws TemplateException
	 */
	public static void sendMessageMail(Object params, String title, String to) throws Exception {
		if(!validateEmailFormat(to)) {
			throw new Exception("Email format error.");
		}
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(from);
		helper.setTo(InternetAddress.parse(to));// 发送给谁
		helper.setSubject("【" + title + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "】");// 邮件标题

		Map<String, Object> model = new HashMap<>();
		model.put("params", params);
		Template template = configurer.getConfiguration().getTemplate(MAIL_TEMPLATE_DEFAULT);
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

		helper.setText(text, true);
		mailSender.send(mimeMessage);
	}
	
	private static boolean validateEmailFormat(String toAddress) {
		Pattern regex = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = regex.matcher(toAddress);
		return matcher.matches();
	}
}
