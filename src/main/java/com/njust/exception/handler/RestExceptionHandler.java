package com.njust.exception.handler;

import com.njust.exception.BusinessException;
import com.njust.exception.code.BaseResponseCode;
import com.njust.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @ Description   :  公共异常处理类
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 14:09$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 14:09$
 * @ Version       :  1.0
 */
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public DataResult handleException(Exception e){
        log.error("Exception, exception: {}", e);
        return DataResult.getResult(BaseResponseCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    DataResult  handBusinessException(BusinessException e){
        log.error("BusinessException, exception: {}", e);
        return DataResult.getResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    DataResult handMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("methodArgumentNotValidExceptionHandler bindingResult.allErrors():{},exception:{}",
                e.getBindingResult().getAllErrors(), e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();  //获得所有异常
        return createValidExceptionResp(allErrors);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public DataResult handleUnauthorizedException(UnauthorizedException e){
        log.error("UnauthorizedException,{}",e);
        return DataResult.getResult(BaseResponseCode.NO_PERMISSION);
    }

    private DataResult createValidExceptionResp(List<ObjectError> errors){
        String[] msgs = new String[errors.size()];
        int i = 0;
        for (ObjectError error : errors) {
            msgs[i] = error.getDefaultMessage();
            log.info("msg={}", msgs[i]);
            i++;
        }
        return DataResult.getResult(BaseResponseCode.VALIDATE_ERROR.getCode(), msgs[0]);
    }

}
