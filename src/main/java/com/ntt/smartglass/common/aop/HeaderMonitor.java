package com.ntt.smartglass.common.aop;

import com.ntt.smartglass.common.consts.RestException;
import com.ntt.smartglass.common.model.Property;
import com.ntt.smartglass.rest.domain.ServerCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Aspect
@Order(1)
@Component
public class HeaderMonitor {

    private Logger logger = LoggerFactory.getLogger(HeaderMonitor.class);

    @Autowired
    private Property property;


    /**
     *
     * @return パラメータエラーメッセージ  Exception メッセージ
     * @throws Throwable
     */
    @Before("execution(* com.ntt.smartglass.rest..*Controller.*(..))")
    public Object logServiceAccess() throws Throwable {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getHeader("version"));
//        logger.info("IP : " + request.getRemoteAddr());
//        tokenCheck(request);
//        versionCheck(request);
//
//
//
        return null;
    }


    private void versionCheck(HttpServletRequest request) throws RestException.ParaLostException, RestException.ParaErrorException {
        String version = request.getHeader("version");
        String paraNameVersion = "バージョン情報";
        if (version == null) {
            throw new RestException.ParaLostException(paraNameVersion);
        }
        if (!property.getVersion().equals(version)) {
            throw new RestException.ParaErrorException(paraNameVersion);
        }
    }

    private void tokenCheck(HttpServletRequest request) throws AuthenticationException {
//        String url = request.getRequestURI();
//        if (url.contains("auth") || url.contains("managerlogin")) {
//            return;
//        }
//        String auth = request.getHeader("token");
//        request.getHeaderNames();
//        request.getMethod();
//
//        if ((auth != null) && ServerCache.getInstance().isGlassTokenExist(auth) && (auth.length() > 0)) {
//            return;
//        } else {
//            throw new AuthenticationException();
//        }
    }
}
