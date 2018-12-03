package cn.jjsunw.utils.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class FileUploadUtil {

	public static String PRO_RESOURCE_FOLDER_PATH = "";
	public static final String RESOURCE_FOLDER_NAME = "springbootInitializeMedia";
	private static final SimpleDateFormat DATE_SDF = new SimpleDateFormat("yyyyMMdd");
	public static final String DEFAULT_CONCAT = "_";

	public static void init(HttpServletRequest req) throws FileNotFoundException {
		String root = req.getServletContext().getRealPath("");
		File rootFolder = new File(root);
		PRO_RESOURCE_FOLDER_PATH = rootFolder.getParent() + File.separator + RESOURCE_FOLDER_NAME;
		File resourceFolder = new File(PRO_RESOURCE_FOLDER_PATH);
		if (!resourceFolder.exists()) {
			resourceFolder.mkdir();
		}
	}

	public static String transferFile(long subfix, String fileType, MultipartFile file, HttpServletRequest req)
			throws IllegalStateException, IOException {
		init(req);
		File targetParentFolder = new File(PRO_RESOURCE_FOLDER_PATH, getFormatDate() + DEFAULT_CONCAT + subfix);
		log.info("Identification resource root folder is : " + targetParentFolder.getPath());
		if (!targetParentFolder.exists()) {
			targetParentFolder.mkdir();
		}
		File targetChildFolder = new File(targetParentFolder.getPath(), fileType);
		if (!targetChildFolder.exists()) {
			targetChildFolder.mkdir();
		}
		
		File targetFile = new File(targetChildFolder, fileType + DEFAULT_CONCAT + System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")));
		log.info("target file type is " + file.getContentType());
		System.out.println("target file type is " + file.getContentType());
		log.info("Identification resource target folder is : " + targetFile.getPath());
		file.transferTo(targetFile);
		return targetFile.getPath();

	}

	public static String getFormatDate() {
		return DATE_SDF.format(new Date());
	}
	
	public static String getMediaPath(HttpServletRequest req, String diskPath) {
		StringBuilder sb = new StringBuilder();
		String storagePath = File.separator + diskPath.substring(diskPath.indexOf(FileUploadUtil.RESOURCE_FOLDER_NAME));
		String host = req.getServerName();
		int port = req.getServerPort();
		sb.append(host);
		if(port != 0) {
			sb.append(":" + port);
		}
		sb.append(storagePath);
		return sb.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(FileUploadUtil.PRO_RESOURCE_FOLDER_PATH);
	}
}
