package com.njust.shiro;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.njust.constants.Constant;
import com.njust.exception.BusinessException;
import com.njust.exception.code.BaseResponseCode;
import com.njust.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.MediaType;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/27$ 22:40$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/27$ 22:40$
 * @ Version       :  1.0
 */
@Slf4j
public class CustomAccessControlFilter extends AccessControlFilter {

    /**
     *  是否允许访问
     * true：允许，交下一个Filter处理
     * false：回往下执行onAccessDenied
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }


    /**
     * 表示访问拒绝时是否自己处理，
     * 如果返回true表示自己不处理且继续拦截器链执行，
     * 返回false表示自己已经处理了（比如直接响应回前端）。
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("request 接口地址：{}", request.getRequestURL().toString());
        log.info("request 接口请求：{}", request.getMethod());
        //判断客户端是否携带accessToken
        try {
            String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
            log.info("onAccessDenied...accessToken={}", accessToken);
            if(StringUtils.isEmpty(accessToken)){
                throw new BusinessException(BaseResponseCode.TOKEN_NOT_NULL);
            }
            CustomUsernamePasswordToken jwtToken = new CustomUsernamePasswordToken(accessToken);
            getSubject(servletRequest,servletResponse).login(jwtToken);
        } catch (BusinessException e){
            customResponse(servletResponse,e.getCode(),e.getMsg());
            return false;
        } catch (AuthenticationException e) {
            if(e.getCause() instanceof BusinessException){
                BusinessException exception = (BusinessException)e.getCause();
                customResponse(servletResponse,exception.getCode(),exception.getMsg());
            }else {
                customResponse(servletResponse, BaseResponseCode.SHIRO_AUTHENTICATION_ERROR.getCode(),
                        BaseResponseCode.SHIRO_AUTHENTICATION_ERROR.getMsg());
            }
            return false;
        } catch (Exception e){
            customResponse(servletResponse,BaseResponseCode.SYSTEM_ERROR.getCode(),BaseResponseCode.SYSTEM_ERROR.getMsg());
            return false;
        }
        return true;
    }

    private void customResponse(ServletResponse response, int code, String msg){
        // 自定义异常的类，用户返回给客户端相应的JSON格式的信息
        try {
            DataResult result = DataResult.getResult(code, msg);
            //response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String str = JSON.toJSONString(result);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(str.getBytes("UTF-8"));
        } catch (IOException e) {
            log.error("response...error:{}",e);
            e.printStackTrace();
        }
    }
}
