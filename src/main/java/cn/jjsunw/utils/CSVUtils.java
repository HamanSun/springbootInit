package cn.jjsunw.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVUtils {
	/** CSV文件列分隔符 */
	private static final String CSV_COLUMN_SEPARATOR = ",";

	/** CSV文件列分隔符 */
	private static final String CSV_RN = "\r\n";

	/**
	 * 
	 * @param dataList
	 *            集合数据
	 * @param colNames
	 *            表头部数据
	 * @param mapKey
	 *            查找的对应数据
	 * @param response
	 *            返回结果
	 */
	public static boolean doExport(List<Map<String, Object>> dataList, String colNames, String mapKey,
			OutputStream os) {
		try {
			StringBuffer buf = new StringBuffer();
			String[] colNamesArr = null;
			String[] mapKeyArr = null;
			colNamesArr = colNames.split(",");
			mapKeyArr = mapKey.split(",");
			// 完成数据csv文件的封装
			// 输出列头
			for (int i = 0; i < colNamesArr.length; i++) {
				buf.append(colNamesArr[i]).append(CSV_COLUMN_SEPARATOR);
			}
			buf.append(CSV_RN);
			if (null != dataList) { // 输出数据
				for (int i = 0; i < dataList.size(); i++) {
					for (int j = 0; j < mapKeyArr.length; j++) {
						buf.append(dataList.get(i).get(mapKeyArr[j])).append(CSV_COLUMN_SEPARATOR);
					}
					buf.append(CSV_RN);
				}
			}
			// 写出响应
			os.write(buf.toString().getBytes("GBK"));
			os.flush();
			return true;
		} catch (Exception e) {
			log.error("doExport错误...", e);
		}
		return false;
	}

	/**
	 * generate CSV file
	 * 
	 * @throws FileNotFoundException
	 */
	public static String createCSVFile(List<Object[]> rows, HttpServletRequest req, String folderName)
			throws FileNotFoundException {
		// initialize root folder
		FileUploadUtils.init(req);
		// 文件输出流
		BufferedWriter fileOutputStream = null;

		try {
			String fileFolder = FileUploadUtils.PRO_RESOURCE_FOLDER_PATH;
			if(!StringUtils.isBlank(folderName)) {
				fileFolder = fileFolder + File.separator + folderName;
			}
			// 含文件名的全路径
			String filePath = fileFolder + File.separator + folderName + FileUploadUtils.DEFAULT_CONCAT + FileUploadUtils.getFormatDate() + ".csv";
			File file = new File(filePath);
			if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
				file.getParentFile().mkdirs();
			}
			
			if (file.exists()) { // 如果已存在,删除旧文件
				file.delete();
			}
			file = new File(filePath);
			file.createNewFile();

			// 格式化浮点数据
			NumberFormat formatter = NumberFormat.getNumberInstance();
			formatter.setMaximumFractionDigits(10); // 设置最大小数位为10

			// 格式化日期数据
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			// 实例化文件输出流
			fileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GB2312"), 1024);

			// 遍历输出每行
			Iterator<Object[]> ite = rows.iterator();
			while (ite.hasNext()) {
				Object[] rowData = (Object[]) ite.next();
				for (int i = 0; i < rowData.length; i++) {
					Object obj = rowData[i]; // 当前字段
					// 格式化数据
					String field = "";
					if (null != obj) {
						if (obj.getClass() == String.class) { // 如果是字符串
							field = (String) obj;
						} else if (obj.getClass() == Double.class || obj.getClass() == Float.class) { // 如果是浮点型
							field = formatter.format(obj); // 格式化浮点数,使浮点数不以科学计数法输出
						} else if (obj.getClass() == Integer.class || obj.getClass() == Long.class
								|| obj.getClass() == Short.class || obj.getClass() == Byte.class) { // 如果是整形
							field += obj;
						} else if (obj.getClass() == Date.class) { // 如果是日期类型
							field = sdf.format(obj);
						}
					} else {
						field = " "; // null时给一个空格占位
					}
					// 拼接所有字段为一行数据
					if (i < rowData.length - 1) { // 不是最后一个元素
						fileOutputStream.write("\"" + field + "\"" + ",");
					} else { // 是最后一个元素
						fileOutputStream.write("\"" + field + "\"");
					}
				}
				// 创建一个新行
				if (ite.hasNext()) {
					fileOutputStream.newLine();
				}
			}
			fileOutputStream.flush();
			return file.getPath();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @throws UnsupportedEncodingException
	 * 
	 *             setHeader
	 */
	public static void responseSetProperties(String fileName, HttpServletResponse response)
			throws UnsupportedEncodingException {
		// 设置文件后缀
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fn = fileName + sdf.format(new Date()).toString() + ".csv";
		// 读取字符编码
		String utf = "UTF-8";
		// 设置响应
		response.setContentType("application/ms-txt.numberformat:@");
		response.setCharacterEncoding(utf);
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, utf));
	}

}
