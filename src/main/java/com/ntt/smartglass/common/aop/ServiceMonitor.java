package com.ntt.smartglass.common.aop;


import com.ntt.smartglass.common.consts.RestException;

import com.ntt.smartglass.common.consts.RestConst;
import com.ntt.smartglass.common.model.Property;
import com.ntt.smartglass.common.util.ExceptionInfoUtil;
import com.ntt.smartglass.common.validator.ParamValidator;
import com.ntt.smartglass.rest.domain.ServerCache;
import com.ntt.smartglass.rest.domain.ValidationData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ntt.smartglass.common.model.DataResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * サービスモニタ
 */
@Aspect
@Component
@Order(2)
public class ServiceMonitor {

    private Logger logger = LoggerFactory.getLogger(ServiceMonitor.class);



    /**
     * @param pjp ProceedingJoinPoint
     * @return パラメータエラーメッセージ  Exception メッセージ
     * @throws Throwable
     */
    @Around("execution(* com.ntt.smartglass.rest..*Controller.*(..))")
    public Object logServiceAccess(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();

        String className = pjp.getTarget().getClass().getName();
        String fullMethodName = className + "." + pjp.getSignature().getName();
        boolean needLog = false;

        if (!className.contains("com.sun.proxy.$Proxy") && !className.contains("$$EnhancerBySpringCGLIB$$")) {
            needLog = true;
            if (pjp.getArgs() != null && pjp.getArgs().length > 0) {
                logger.info(fullMethodName + " Is Called,Param:");
                for (Object arg : pjp.getArgs()) {
                    logger.info(arg.toString());
                    Map<String, ArrayList<String>> validateResult = ParamValidator.validator(arg);
                    if (validateResult != null) {
                        for (Iterator<Map.Entry<String, ArrayList<String>>> iterator = validateResult.entrySet().iterator(); iterator.hasNext(); ) {
                            Map.Entry<String, ArrayList<String>> entry = iterator.next();
                            logger.error(fullMethodName + " Param:" + arg);
                            logger.error(entry.getKey() + " Validate Fail,Cause:" + entry.getValue());
                            long end = System.currentTimeMillis();
                            long elapsedMilliseconds = end - start;
                            logger.info(fullMethodName + " Used Time:" + elapsedMilliseconds + " ms");
                            String jsonStringMessage = entry.getValue().toString();
                            ValidationData data = getValidationData(jsonStringMessage);
                            return new DataResult<String>(data.getCode(),
                                    data.getMessage());
                        }
                    }
                }
            } else {
                logger.info(fullMethodName + " Will Be Call");
            }
        }

        Object result;
        try {
            result = pjp.proceed();
        } catch (Exception e) {
            return ExceptionInfoUtil.getTargetDataResult(e);
        } catch (Throwable e) {
            return ExceptionInfoUtil.getTargetDataResult(null);
        }

        long end = System.currentTimeMillis();
        long elapsedMilliseconds = end - start;
        if (needLog) {
            logger.info(fullMethodName + " Used Time:" + elapsedMilliseconds + " ms");
        }

        return result;
    }

    private ValidationData getValidationData(String jsonString) {
        String handeldJson = jsonString.replace("[", "")
                .replace("]", "")
                .trim();
        int divider = handeldJson.indexOf(",");
        if (divider != -1) {
            String code = handeldJson.substring(0, divider);
            String message = handeldJson.substring(divider + 1);
            RestConst.ErrorCodeEnum codeEnum = RestConst.ErrorCodeEnum.toValueOf(code);
            return new ValidationData(codeEnum.getCode(), codeEnum.getMessgae().replace("%", message));
        } else {
            RestConst.ErrorCodeEnum codeEnum = RestConst.ErrorCodeEnum.toValueOf(jsonString);
            return new ValidationData(codeEnum.getCode(), codeEnum.getMessgae());
        }
    }


}