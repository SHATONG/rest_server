package test.com.ntt.smartglass.rest;

import com.ntt.smartglass.common.aop.RestExceptionHandler;
import com.ntt.smartglass.common.consts.RestConst;
import com.ntt.smartglass.common.consts.RestException;
import com.ntt.smartglass.common.util.ExceptionInfoUtil;
import com.ntt.smartglass.common.util.TokenUtils;
import com.ntt.smartglass.common.util.RSAUtils;
import com.ntt.smartglass.common.validator.ParamValidator;
import com.ntt.smartglass.rest.converter.ModelConverter;
import com.ntt.smartglass.rest.main.RestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import javax.naming.AuthenticationException;
import javax.naming.ServiceUnavailableException;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

/**
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestApplication.class)
@SpringBootTest
public class RestExceptionHandlerTest {

    @Test
    public void testException() throws Exception {
        RestExceptionHandler handler = new RestExceptionHandler();

        org.springframework.web.servlet.NoHandlerFoundException e1 = Mockito.mock(org.springframework.web.servlet.NoHandlerFoundException.class);
        assertEquals(RestConst.ErrorCodeEnum.NOT_FOUND.getCode(), handler.defaultErrorHandler(e1).getStatusCode());

        org.springframework.web.HttpRequestMethodNotSupportedException e2 = Mockito.mock(org.springframework.web.HttpRequestMethodNotSupportedException.class);
        assertEquals(RestConst.ErrorCodeEnum.METHOD_NOT_ALLOWED.getCode(), handler.defaultErrorHandler(e2).getStatusCode());

        assertEquals(RestConst.ErrorCodeEnum.SERVICE_UNAVAILABLE.getCode(), handler.defaultErrorHandler(new ServiceUnavailableException()).getStatusCode());

        assertEquals(RestConst.ErrorCodeEnum.UNAUTHORIZED.getCode(), handler.defaultErrorHandler(new AuthenticationException()).getStatusCode());

        DataAccessException e3 = Mockito.mock(DataAccessException.class);
        assertEquals(RestConst.ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode(), handler.defaultErrorHandler(e3).getStatusCode());

        org.springframework.http.converter.HttpMessageNotReadableException e4 = Mockito.mock(org.springframework.http.converter.HttpMessageNotReadableException.class);
        assertEquals(RestConst.ErrorCodeEnum.BAD_REQUEST.getCode(), handler.defaultErrorHandler(e4).getStatusCode());

        assertEquals(RestConst.ErrorCodeEnum.UNKNOWN.getCode(), handler.defaultErrorHandler(new UnknownHostException()).getStatusCode());

        HttpMediaTypeNotSupportedException e5 = Mockito.mock(HttpMediaTypeNotSupportedException.class);
        assertEquals(RestConst.ErrorCodeEnum.BAD_REQUEST.getCode(), handler.defaultErrorHandler(e5).getStatusCode());
    }

    @Test
    public void classTest() throws Exception {
        RestConst restConst = new RestConst();
        RestConst.ErrorCode code = new RestConst.ErrorCode();

        assertEquals("1001", RestConst.ErrorCode.PARAMETER_LOST);
        assertEquals("1002", RestConst.ErrorCode.INPUT_ERROR);

        ModelConverter converter = new ModelConverter();
        assertEquals(null, ModelConverter.toSmartGlassListResultData(null,"123"));

        ParamValidator validator = new ParamValidator();
        TokenUtils jwtHelper = new TokenUtils();
        ExceptionInfoUtil exceptionInfoUtil = new ExceptionInfoUtil();

        RestApplication application = new RestApplication();
        String[] args = {};
        RestApplication.main(args);

        RestException exception = new RestException();
        RSAUtils rsaUtils = new RSAUtils();
    }
}
