//package test.com.ntt.smartglass.rest;
//
//import com.ntt.smartglass.common.aop.GlassTokenFilter;
//import com.ntt.smartglass.common.aop.VersionFilter;
//import com.ntt.smartglass.rest.domain.ServerCache;
//import com.ntt.smartglass.rest.main.RestApplication;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.Writer;
//
//import static org.mockito.Mockito.when;
//
///**
// * Created by liuqi on 2017/06/14.
// */
//@Component
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = RestApplication.class)
//@WebAppConfiguration
//public class TokenFilterTest {
//    @Autowired
//    GlassTokenFilter filter;
//    String token = "eDD02ffIjoiSldUIiwiYWxnIjoiSFMyNTHHHH.eyJjZXJ0aWZpY2F0aW9uS2V5IjoiMTIzIiwiaWF0IjoxNDk3M" +
//            "zUxODczLCJleHAiOjE0OTc0MzgyNzMsIm5iZiI6MTQ5NzM1MTg3M30.EM48cd3Qsxcd4PSmtFy9xjyYl2NxyRfGgbU5MvJSTw8";
//
//    @Test
//    public void filterTest() throws IOException, ServletException {
//        ServerCache cache = ServerCache.getInstance();
//        cache.addGlassToken(token, "123");
//        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
//        FilterChain filterChain = Mockito.mock(FilterChain.class);
//        when(request.getHeader("token")).thenReturn(token);
//        filter.doFilter(request, response, filterChain);
//        FilterConfig config=Mockito.mock(FilterConfig.class);
//        filter.init(config);
//        filter.destroy();
//    }
//
//    @Test
//    public void filterErrorTest() throws IOException, ServletException {
//        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
//        FilterChain filterChain = Mockito.mock(FilterChain.class);
//        when(request.getHeader("token")).thenReturn("2.0");
//        when(response.getWriter()).thenReturn(new PrintWriter(new Writer() {
//            @Override
//            public void write(char[] cbuf, int off, int len) throws IOException {
//
//            }
//
//            @Override
//            public void flush() throws IOException {
//
//            }
//
//            @Override
//            public void close() throws IOException {
//
//            }
//        }));
//        filter.doFilter(request, response, filterChain);
//    }
//}
