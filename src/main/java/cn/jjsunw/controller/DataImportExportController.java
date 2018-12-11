package cn.jjsunw.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags="Data handler via file API.")
@RestController
public class DataImportExportController {/*
	
	@Autowired 
	PersonServiceImpl personService;

	@ApiOperation(value="Import persons from excel")
	@PostMapping("/excelImport")
	public void importViaExcel(MultipartFile excel) {
		List<Person> persons = ExcelUtils.importExcel(excel, 1, 1, Person.class);
		personService.save(persons);
	}
	
	@ApiOperation(value="Export persons to excel")
	@PostMapping("/excelExport")
	public void export2Excel(HttpServletResponse response) {
		List<Person> all = personService.findAll();
		ExcelUtils.exportExcel(all, "All persons", "test", Person.class, "person file", true, response);
	}
	
	@ApiOperation(value="Import person from csv")
	@ApiImplicitParam(name="file", value="csv", dataType="file", paramType="form")
	@PostMapping("/csvImport")
	public Result importViaCsv(@RequestParam("file") MultipartFile csv) {
		CsvReader reader = null;
		try {
			reader = new CsvReader(csv.getInputStream(), Charset.defaultCharset());
			reader.readHeaders();// if header exist, must run this method
			while(reader.readRecord()) {// row data exist, read row or column?
				String row = reader.getRawRecord();// read one line
				String column = reader.get("name");// column name as key
				System.out.println("line data : " + row + "\\n" + "column value for name is : " + column);
			}
		} catch (IOException e) {
			return ResultGenerator.genFailResult("Csv file import error.");
		} finally {
			reader.close();
		}
		return ResultGenerator.genSuccessResult().setMessage("Import successfully.");
	}
	
	@ApiOperation(value="Import person from excel")
	@PostMapping("/csvExport")
	public Result export2Csv(HttpServletResponse response) {
		response.setContentType("text/csv");
		List<Person> list = personService.findAll();
		if(list.size() == 0) {
			return ResultGenerator.genFailResult("No data");
		}
		NumberFormat nbf = NumberFormat.getNumberInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		CsvWriter csvWriter = null;
		try {
			csvWriter = new CsvWriter(response.getOutputStream(), ',', Charset.forName("GBK"));
			String[] headers = {"姓名","性别","年龄","生日"};
			csvWriter.writeRecord(headers);
			for(Person person : list) {
				String[] thisRecord = new String[] {person.getName(), person.getSex(), nbf.format(person.getAge()), sdf.format(person.getBirth())};
				csvWriter.writeRecord(thisRecord);
			}
		} catch (IOException e) {
			return ResultGenerator.genFailResult("");
		} finally {
			try {
				csvWriter.flush();
			} catch (IOException e) {
				return ResultGenerator.genFailResult("CsvWriter flush error.");
			}
			csvWriter.close();
		}
		return ResultGenerator.genSuccessResult().setMessage("");
	}
*/}
