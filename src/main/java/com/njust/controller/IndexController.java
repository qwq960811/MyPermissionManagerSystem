package com.njust.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/29$ 10:22$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/29$ 10:22$
 * @ Version       :  1.0
 */
@Controller
@RequestMapping("index")
@Api(tags="视图", description="负责返回视图")
public class IndexController {

    @GetMapping("/404")
    @ApiOperation("跳转404错误页面")
    public String error404(){
        return "/error/404";
    }

    @GetMapping("/login")
    @ApiOperation(value = "跳转登录页面")
    public String login() {
        return "login";
    }

    @GetMapping("home")
    @ApiOperation(value = "跳转首页页面")
    public String home() {
        return "home";
    }

    @GetMapping("main")
    @ApiOperation(value = "跳转主页页面")
    public String main() {
        return "main";
    }

    @GetMapping("/menus")
    @ApiOperation(value = "跳转菜单权限页面")
    public String menusList(){
        return "menus/menu";
    }
}
