package cn.jjsunw;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	private static final String PROJECT_PATH = System.getProperty("user.dir");// disk path
	private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/xxx.xxx";// file path

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testFormData() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(new URI("/person/list"))
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("page", "1")
				.param("size", "10")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
	}

	@Test
	public void testEntityOrJsonData() throws Exception {
		JSONObject request = new JSONObject();
		request.put("name", "babiQ");
		request.put("sex", "girl");
		request.put("age", 18);
		request.put("birth", new Date());
		String result = mockMvc.perform(post(new URI("/person/add"))
				.content(request.toJSONString())
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		JSONObject jsonRes = JSONObject.parseObject(result);
		System.out.println(jsonRes);
	}

	@Test
	public void testFileUpload() throws Exception {
		File csvFile = new File(TEMPLATE_FILE_PATH);
		MockMultipartFile mmpf = new MockMultipartFile("file", "persons.csv",
				MediaType.APPLICATION_FORM_URLENCODED_VALUE, new FileInputStream(csvFile));
		String result = mockMvc
				.perform(MockMvcRequestBuilders.fileUpload("/csvImport").file(mmpf)
						.param("xxx", "xxx"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
}
