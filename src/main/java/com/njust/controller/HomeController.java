package com.njust.controller;

import com.njust.constants.Constant;
import com.njust.service.HomeService;
import com.njust.utils.DataResult;
import com.njust.utils.JwtTokenUtil;
import com.njust.vo.resp.HomeRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/29$ 15:53$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/29$ 15:53$
 * @ Version       :  1.0
 */
@RestController
@RequestMapping("api")
@Api(tags="首页数据")
public class HomeController {
    @Autowired
    private HomeService homeService;
    
    @GetMapping("home")
    @ApiOperation(value ="获取首页数据接口")
    public DataResult<HomeRespVO> getHomeInfo(HttpServletRequest request){
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getUserId(accessToken);

        DataResult result = DataResult.success();
        result.setData(homeService.getHomeInfo(userId));
        return result;
    }
    



}
