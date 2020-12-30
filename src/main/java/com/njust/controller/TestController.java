package com.njust.controller;

import com.njust.entity.SysUser;
import com.njust.exception.BusinessException;
import com.njust.exception.code.BaseResponseCode;
import com.njust.utils.DataResult;
import com.njust.vo.req.TestReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("swagger")
@Api(tags="测试接口模块", description="为测试使用")
public class TestController {

    @GetMapping("test")
    @ApiOperation(value="测试接口")
    public String test(){
        return "测试成功";
    }

    @GetMapping("home")
    @ApiOperation(value="统一的响应格式测试接口")
    public DataResult<String> test1(){
        //DataResult<String> result = DataResult.success("统一的响应格式测试接口");

        List<SysUser> list = new ArrayList<>();
        SysUser user1 = new SysUser();
        user1.setUsername("zs");
        user1.setPhone("110");
        SysUser user2 = new SysUser();
        user2.setUsername("ls");
        user2.setPhone("119");
        list.add(user1);
        list.add(user2);
        DataResult<String> result = DataResult.success(list);
        return result;
    }

    @GetMapping("testException")
    @ApiOperation(value = "测试处理全局异常")
    public DataResult<String> testExceptionHander(){
        int i = 1/0;
        return DataResult.success("测试处理全局异常");
    }

    @GetMapping("testBusinessError")
    @ApiOperation(value="测试主动抛出业务异常处理")
    public DataResult<String> testBusinessError(@RequestParam String type){

        if(!( "1".equals(type) || "2".equals(type) || "3".equals(type))){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        DataResult result = DataResult.success(type);
        return result;
    }

    @PostMapping("/valid/error")
    @ApiOperation(value="测试Validator抛出业务异常接口")
    public DataResult testValid(@RequestBody @Valid TestReqVO vo){  //要使注解生效，必须在入参处(controller)加@Valid
        return DataResult.success(vo);
    }
}
