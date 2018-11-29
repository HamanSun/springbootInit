package cn.jjsunw.controller;

import cn.jjsunw.annotation.SysLogger;
import cn.jjsunw.common.Result;
import cn.jjsunw.common.ResultGenerator;
import cn.jjsunw.model.User;
import cn.jjsunw.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by jjsunw on 2018/11/28.
*/
@Api(tags="UserApiDoc")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping
    @ApiOperation("New 1 User")
    @ApiImplicitParam(name="user",value="user information", dataType="User", paramType="body")
    public Result add(@RequestBody User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete 1 User")
    @ApiImplicitParam(name="id",value="user primary key", dataType="Integer", paramType="path")
    public Result delete(@PathVariable Integer id) {
    	log.info("Will delete user with primary key : " + id);
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
 	@ApiOperation("Update 1 User")
    @ApiImplicitParam(name="user",value="user with primary key", dataType="User", paramType="body")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @SysLogger("user detail.")
    @ApiOperation("Get User detail")
    @ApiImplicitParam(name="id",value="user primary key", dataType="Integer", paramType="path")
    public Result detail(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @GetMapping
    @ApiOperation("List all Users")
    @ApiImplicitParams({@ApiImplicitParam(name="page",value="page number", dataType="Integer", paramType="form"),@ApiImplicitParam(name="size",value="page size", dataType="Integer", paramType="form")})
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
