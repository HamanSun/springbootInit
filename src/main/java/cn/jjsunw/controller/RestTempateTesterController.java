package cn.jjsunw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jjsunw.common.Result;
import cn.jjsunw.common.ResultGenerator;
import cn.jjsunw.utils.FileUploadUtils;
import cn.jjsunw.utils.RestTemplateUtils;

@RestController
@RequestMapping("/resttemplate")
public class RestTempateTesterController {

	@PostMapping("/valideCard")
	public Result valideCard(@RequestParam("file") MultipartFile file, @RequestParam("side") String side, HttpServletRequest req) {
		Map<String, String> headers = new HashMap<>();
		Map<String, String> body = new HashMap<>();
		headers.put("side", side);
		
		try {
			String filePath = FileUploadUtils.transferFile(System.currentTimeMillis(), "idcard", file, req);
			body.put("file", RestTemplateUtils.FILE_MARK_PREFIX + filePath);
			ResponseEntity<JSONObject> response = RestTemplateUtils.reqCardIdentifyApi(headers, body);
			return ResultGenerator.genSuccessResult(response);
		} catch (Exception e) {
			return ResultGenerator.genFailResult(e.getMessage());
		}
	}
}
