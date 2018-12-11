package cn.jjsunw.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
* Created by jjsunw on 2018/04/24.
*/
@Api(tags="人员管理")
@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {/*
	
    @Resource
    private PersonService personService;

    @ApiOperation("添加1人")
    @ApiImplicitParam(name="person",value="person info", dataType="Person", paramType="body")
    @PostMapping("/add")
    public Result add(@RequestBody Person person) {
        personService.save(person);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        personService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Person person) {
        personService.update(person);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/getDetail")
    public Result queryDetail() {
    	log.info("=============start to query person's detail.===============");
    	int id = 7;
        Person person = personService.findById(id);
        return ResultGenerator.genSuccessResult(person);
    }
    
    @GetMapping("/postDetail")
    public Result queryDetail1() {
    	log.info("=============start to query person's detail.===============");
    	int id = 7;
        Person person = personService.findById(id);
        return ResultGenerator.genSuccessResult(person);
    }

    @PostMapping("/list")
    public Result queryList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Person> list = personService.findAll();
        PageInfo<Person> pageInfo = new PageInfo<Person>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    @PostMapping("/exportData2CSV")
    public Result export(HttpServletRequest req) {
        List<Person> list = personService.findAll();
        List<Object[]> csvData = new ArrayList<Object[]>();
        String[] header = new String[] {"姓名","性别","年龄","生日"};
        csvData.add(header);
        for(Person p : list) {
        	Object[] item = new Object[] {p.getName(), p.getSex(), p.getAge(), p.getBirth()};
        	csvData.add(item);
        }
        String csvFilePath = "";
        try {
        	csvFilePath = CSVUtils.createCSVFile(csvData, req, "person");
		} catch (FileNotFoundException e) {
			return ResultGenerator.genFailResult("Person data export error.");
		}
        return ResultGenerator.genSuccessResult(csvFilePath).setMessage("Person Data export successful, the file path is: " + csvFilePath);
    }
    @PostMapping("/exception")
    public Result exception() {
    	throw new RuntimeException("自定義異常.");
    }
*/}
