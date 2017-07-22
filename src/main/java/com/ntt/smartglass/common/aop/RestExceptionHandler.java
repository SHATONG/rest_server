package com.ntt.smartglass.common.aop;


import com.ntt.smartglass.common.consts.RestException.ParaLostException;
import com.ntt.smartglass.common.model.DataResult;
import com.ntt.smartglass.common.util.ExceptionInfoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;


/**
 * Exception情報処理クラス
 */
@RestController
@ControllerAdvice
public class RestExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Exception情報処理
     *
     * @param e Exception
     * @return Exception情報
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public DataResult<String> defaultErrorHandler(Exception e) {
        logger.error("Exception", e);
        return ExceptionInfoUtil.getTargetDataResult(e);
    }


}
