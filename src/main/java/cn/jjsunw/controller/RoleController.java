package cn.jjsunw.controller;

import cn.jjsunw.common.Result;
import cn.jjsunw.common.ResultGenerator;
import cn.jjsunw.model.Role;
import cn.jjsunw.service.RoleService;
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
@Api(tags="RoleApiDoc")
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping
    @ApiOperation("New 1 Role")
    @ApiImplicitParam(name="role",value="role information", dataType="Role", paramType="body")
    public Result add(@RequestBody Role role) {
        roleService.save(role);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete 1 Role")
    @ApiImplicitParam(name="id",value="role primary key", dataType="Integer", paramType="path")
    public Result delete(@PathVariable Integer id) {
    	log.info("Will delete role with primary key : " + id);
        roleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
 	@ApiOperation("Update 1 Role")
    @ApiImplicitParam(name="role",value="role with primary key", dataType="Role", paramType="body")
    public Result update(@RequestBody Role role) {
        roleService.update(role);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get Role detail")
    @ApiImplicitParam(name="id",value="role primary key", dataType="Integer", paramType="path")
    public Result detail(@PathVariable Integer id) {
        Role role = roleService.findById(id);
        return ResultGenerator.genSuccessResult(role);
    }

    @GetMapping
    @ApiOperation("List all Roles")
    @ApiImplicitParams({@ApiImplicitParam(name="page",value="page number", dataType="Integer", paramType="form"),@ApiImplicitParam(name="size",value="page size", dataType="Integer", paramType="form")})
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Role> list = roleService.findAll();
        PageInfo<Role> pageInfo = new PageInfo<Role>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
