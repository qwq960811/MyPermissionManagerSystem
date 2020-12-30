package com.njust.utils;

import com.njust.exception.code.BaseResponseCode;
import com.njust.exception.code.ResponseCodeInterface;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/23$ 17:45$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/23$ 17:45$
 * @ Version       :  1.0
 */
public class DataResult<T> {

    @ApiModelProperty(value="响应状态码")
    private int code;

    @ApiModelProperty(value="响应状态信息")
    private String msg;

    @ApiModelProperty(value="响应数据")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DataResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public DataResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public DataResult(int code, T data) {
        this.code = code;
        this.data = data;
        this.msg = null;
    }


    public DataResult(){
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = null;
    }

    public DataResult(T data){
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = data;
    }

    public DataResult(ResponseCodeInterface responseCodeInterface){
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
        this.data = null;
    }

    public DataResult(ResponseCodeInterface responseCodeInterface, T data){
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
        this.data = data;
    }


    public static <T> DataResult getResult(int code, String msg, T data){
        return new DataResult(code, msg, data);
    }

    public static DataResult getResult(int code, String msg){
        return new DataResult(code, msg);
    }

    public static <T> DataResult getResult(int code, T data){
        return new DataResult(code, data);
    }


    public static DataResult success(){
        return new DataResult();
    }

    public static <T>DataResult success(T data){
        return new DataResult(data);
    }

    public static DataResult getResult(BaseResponseCode responseCode){
        return new DataResult(responseCode);
    }

    public static <T>DataResult getResult(BaseResponseCode responseCode, T data){
        return new DataResult(responseCode,data);
    }

}
