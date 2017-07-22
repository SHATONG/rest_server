package com.ntt.smartglass.common.util;


import com.ntt.smartglass.common.consts.RestConst;

import com.ntt.smartglass.common.consts.RestException;
import com.ntt.smartglass.common.model.DataResult;
import org.springframework.dao.DataAccessException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import javax.naming.AuthenticationException;
import javax.naming.ServiceUnavailableException;

/**
 * ExceptionInfoUtil
 */
public class ExceptionInfoUtil {


    /**
     * @param e
     * @return
     */
    public static DataResult<String> getTargetDataResult(Exception e) {
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return new DataResult<String>(RestConst.ErrorCodeEnum.NOT_FOUND.getCode(),
                    RestConst.ErrorCodeEnum.NOT_FOUND.getMessgae());
        } else if (e instanceof RestException.ParaLostException) {
            return new DataResult<String>(RestConst.ErrorCodeEnum.PARAMETER_LOST.getCode(),
                    RestConst.ErrorCodeEnum.PARAMETER_LOST.getMessgae().replace("%", e.getMessage()));
        } else if (e instanceof RestException.ParaErrorException) {
            return new DataResult<String>(RestConst.ErrorCodeEnum.INPUT_ERROR.getCode(),
                    RestConst.ErrorCodeEnum.INPUT_ERROR.getMessgae().replace("%", e.getMessage()));
        } else if (e instanceof org.springframework.web.HttpRequestMethodNotSupportedException) {
            return new DataResult<String>(RestConst.ErrorCodeEnum.METHOD_NOT_ALLOWED.getCode(),
                    RestConst.ErrorCodeEnum.METHOD_NOT_ALLOWED.getMessgae());
        } else if (e instanceof ServiceUnavailableException) {
            return new DataResult<String>(RestConst.ErrorCodeEnum.SERVICE_UNAVAILABLE.getCode(),
                    RestConst.ErrorCodeEnum.SERVICE_UNAVAILABLE.getMessgae());
        } else if (e instanceof AuthenticationException || e instanceof org.springframework.web.bind.ServletRequestBindingException) {
            return new DataResult<String>(RestConst.ErrorCodeEnum.UNAUTHORIZED.getCode(),
                    RestConst.ErrorCodeEnum.UNAUTHORIZED.getMessgae());
        } else if (e instanceof DataAccessException) {
            return new DataResult<String>(RestConst.ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode(),
                    RestConst.ErrorCodeEnum.INTERNAL_SERVER_ERROR.getMessgae());
        } else if (e instanceof org.springframework.http.converter.HttpMessageNotReadableException
                || e instanceof HttpMediaTypeNotSupportedException
                || e instanceof HttpMediaTypeNotAcceptableException) {
            return new DataResult<String>(RestConst.ErrorCodeEnum.BAD_REQUEST.getCode(),
                    RestConst.ErrorCodeEnum.BAD_REQUEST.getMessgae());
        } else {
            return new DataResult<String>(RestConst.ErrorCodeEnum.UNKNOWN.getCode(),
                    RestConst.ErrorCodeEnum.UNKNOWN.getMessgae());
        }
    }
}
