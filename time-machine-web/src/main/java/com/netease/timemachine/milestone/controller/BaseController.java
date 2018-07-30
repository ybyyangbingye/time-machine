package com.netease.timemachine.milestone.controller;

import com.netease.timemachine.milestone.vo.ResponseResult;
import com.netease.timemachine.milestone.vo.ReturnCode;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午4:15
 */
public abstract class BaseController {


    protected ResponseResult getResponseResult(HttpServletRequest request, ResultDelegate delegate,
                                               BindingResult bindingResult) {
        ResponseResult result = new ResponseResult();

        // if (sessionUser == null) {
        //     MMLogger.info("the saleUser not login");
        //     throw new ClientException("请先登陆");
        // }
        // 参数检查失败
        if (bindingResult != null && bindingResult.hasErrors()) {
            result.setCode(ReturnCode.PARAM_ERROR.getCode());
            result.setMsg(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }

        try {
            Object resultObject = delegate.getResultObject();
            result.setCode(ReturnCode.SUCCESS.getCode());
            result.setMsg(ReturnCode.SUCCESS.getMsg());
            result.setResult(resultObject);
        } catch (Exception e) {
            result.setCode(ReturnCode.PARAM_ERROR.getCode());
            result.setMsg(e.getMessage());
            result.setResult(e.getMessage());
        }
        return result;
    }

    protected ResponseResult getResponseResult(HttpServletRequest request, ResultDelegate delegate) {
        return getResponseResult(request, delegate, null);
    }

    protected interface ResultDelegate {

        Object getResultObject() throws Exception;
    }

}
