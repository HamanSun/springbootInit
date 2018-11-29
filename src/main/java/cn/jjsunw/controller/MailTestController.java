package cn.jjsunw.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jjsunw.common.Result;
import cn.jjsunw.common.ResultGenerator;
import cn.jjsunw.utils.MailBody;
import cn.jjsunw.utils.MailUtils;

@RestController
@RequestMapping("/email")
public class MailTestController {
	@PostMapping("/send")
	public Result sendEmail() {
		MailBody mb = new MailBody();
		mb.setCause("TEST");
		mb.setMessageCode("200");
		mb.setMessageStatus("SUCCESS");
		try {
			MailUtils.sendMessageMail(mb, "Test mail", "13662005880@163.com");
		} catch (Exception e) {
			return ResultGenerator.genFailResult(e.getMessage());
		}
		return ResultGenerator.genSuccessResult().setMessage("Email send successfully.");
	}
}
