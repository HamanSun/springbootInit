package cn.jjsunw.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

@Component
public class RestTemplateUtils {
	public static final String FILE_MARK_PREFIX = "F.I.L.E___";

	public static final String API_DOMAIN = "appName";

	public static final String API_AUTHENTICATION = "authentication";

	private static String appName;

	private static String appAuth;

	private static String idCardApi;

	private static RestTemplate template;

	@Value("${api.ocr.domain.name}")
	public void setAppName(String appName) {
		RestTemplateUtils.appName = appName;
	}

	@Value("${api.ocr.domain.auth}")
	public void setAppAuth(String appAuth) {
		RestTemplateUtils.appAuth = appAuth;
	}

	@Autowired
	public void setTemplate(RestTemplate template) {
		RestTemplateUtils.template = template;
	}

	@Value("${api.ocr.identity.idcard}")
	public void setIdCardApi(String idCardApi) {
		RestTemplateUtils.idCardApi = idCardApi;
	}

	public static synchronized ResponseEntity<JSONObject> reqCardIdentifyApi(Map<String, String> headerExtends,
			Map<String, String> body) throws Exception {

		HttpHeaders headers = getBasicHeaders();
		for (Map.Entry<String, String> entry : headerExtends.entrySet()) {
			headers.add(entry.getKey(), entry.getValue());
		}
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
		for (Map.Entry<String, String> entry : body.entrySet()) {
			form.add(entry.getKey(), getParameterValue(entry.getValue()));
		}

		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(form, headers);
		try {
			ResponseEntity<JSONObject> response = template.exchange(idCardApi, HttpMethod.POST, entity,
					JSONObject.class);
			return response;
		} catch (Exception e) {
			throw new Exception("Access three-party api failure.");
		}
	}

	private static HttpHeaders getBasicHeaders() {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("multipart/form-data");
		headers.setContentType(type);
		headers.add(API_DOMAIN, appName);
		headers.add(API_AUTHENTICATION, appAuth);
		return headers;
	}

	private static Object getParameterValue(String value) {
		if (value.indexOf(FILE_MARK_PREFIX) != -1) {
			return new FileSystemResource(value.substring(FILE_MARK_PREFIX.length()));
		}
		return value;
	}
}
