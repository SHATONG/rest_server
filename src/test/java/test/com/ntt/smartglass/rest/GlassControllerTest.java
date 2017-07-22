package test.com.ntt.smartglass.rest;

import com.ntt.smartglass.common.consts.RestConst;
import com.ntt.smartglass.rest.domain.ServerCache;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 *
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestApplicationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations="classpath:test.properties")
@SpringBootTest
public class GlassControllerTest {

    private MockMvc mockMvc;
    private String urlAuth = "http://localhost:8080/smartglass/auth";
    private String urlOnline = "http://localhost:8080/smartglass/online";








    @Autowired
    private WebApplicationContext wac;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }
    @Test
    public void headerErrorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(urlAuth)
                .header("version", "9999")
                .content("{\"certificationKey\":\"123\"\n" +
                        "}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.ErrorCodeEnum.INPUT_ERROR.getCode()))
                .andDo(print());

    }

    @Test
    public void headerLostTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(urlAuth)

                .content("{\"certificationKey\":\"123\"\n" +
                        "}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.ErrorCodeEnum.PARAMETER_LOST.getCode()))
                .andDo(print());

    }
    @Test
    public void testTokenUnfind() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(urlOnline)
                .header("version", "1.0")
                .content("{\"peerID\":\"123456\"\n" +
                        "}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.ErrorCodeEnum.UNAUTHORIZED.getCode()))

                .andDo(print());

    }
    @Test
    public void testAuthSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(urlAuth)
                .header("version", "1.0")
                .content("{\"certificationKey\":\"123\"\n" +
                        "}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.SUCCESS_CODE))
                .andDo(print());

    }

    @Test
    public void testAuthNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(urlAuth)
                .header("version", "1.0")
                .content("{\"certificationKey\":null\n" +
                        "}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.ErrorCodeEnum.PARAMETER_LOST.getCode()))
                .andDo(print());

    }

    @Test
    public void testAuthError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(urlAuth)
                .header("version", "1.0")
                .content("{\"certificationKey\":\"\"\n" +
                        "}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.ErrorCodeEnum.INPUT_ERROR.getCode()))
                .andDo(print());

    }

    @Test
    public void tesAuthtUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(urlAuth)
                .header("version", "1.0")
                .content("{\"certificationKey\":\"888\"\n" +
                        "}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.ErrorCodeEnum.UNAUTHORIZED.getCode()))
                .andDo(print());

    }


    @Test
    public void testOnlineSuccess() throws Exception {

        String token1 = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJjZXJ0aWZpY2F0aW9uS2V5IjoiMTIzIiwiaWF0IjoxNDk3M" +
                "zUxODczLCJleHAiOjE0OTc0MzgyNzMsIm5iZiI6MTQ5NzM1MTg3M30.EM48cd3Qsxcd4PSmtFy9xjyYl2NxyRfGgbU5MvJSTw8";

        ServerCache cache = ServerCache.getInstance();
        cache.addGlassToken(token1, "123");
        mockMvc.perform(MockMvcRequestBuilders.post(urlOnline)
                .header("version", "1.0")
                .header("token", token1)
                .content("{\"peerID\":\"123456\"\n" +
                        "}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.SUCCESS_CODE))
                .andExpect(jsonPath("$.message").value(""))
                .andDo(print());
        assertEquals (true,cache.isGlassOnline(token1));

    }

    @Test
    public void testOnlineParameterNull() throws Exception {
        String token4 = "eDD0EEEEIjoiSldUIiwiYWxnIjoiSFMyNTHHHH.eyJjZXJ0aWZpY2F0aW9uS2V5IjoiMTIzIiwiaWF0IjoxNDk3M" +
                "zUxODczLCJleHAiOjE0OTc0MzgyNzMsIm5iZiI6MTQ5NzM1MTg3M30.EM48cd3Qsxcd4PSmtFy9xjyYl2NxyRfGgbU5MvJSTw8";
        ServerCache cache = ServerCache.getInstance();
        cache.addGlassToken(token4, "123");
        mockMvc.perform(MockMvcRequestBuilders.post(urlOnline)
                .header("version", "1.0")
                .header("token", token4)
                .content("{\"peerId\":null}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.ErrorCodeEnum.PARAMETER_LOST.getCode()))

                .andDo(print());
        assertEquals (false,cache.isGlassOnline(token4));

    }

    @Test
    public void testOnlineParameterError() throws Exception {
        String token3 = "eDD0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTHHHH.eyJjZXJ0aWZpY2F0aW9uS2V5IjoiMTIzIiwiaWF0IjoxNDk3M" +
                "zUxODczLCJleHAiOjE0OTc0MzgyNzMsIm5iZiI6MTQ5NzM1MTg3M30.EM48cd3Qsxcd4PSmtFy9xjyYl2NxyRfGgbU5MvJSTw8";
        ServerCache cache = ServerCache.getInstance();
        cache.addGlassToken(token3, "123");
        mockMvc.perform(MockMvcRequestBuilders.post(urlOnline)
                .header("version", "1.0")
                .header("token", token3)
                .content("{\"peerID\":\"\"}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.ErrorCodeEnum.INPUT_ERROR.getCode()))

                .andDo(print());
        assertEquals (false,cache.isGlassOnline(token3));

    }

    @Test
    public void testOfflineSuccess() throws Exception {
         String urlOffline = "http://localhost:8080/smartglass/offline";
        String token2 = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTHHHH.eyJjZXJ0aWZpY2F0aW9uS2V5IjoiMTIzIiwiaWF0IjoxNDk3M" +
                "zUxODczLCJleHAiOjE0OTc0MzgyNzMsIm5iZiI6MTQ5NzM1MTg3M30.EM48cd3Qsxcd4PSmtFy9xjyYl2NxyRfGgbU5MvJSTw8";
        ServerCache cache = ServerCache.getInstance();
        cache.addGlassToken(token2, "123");
        mockMvc.perform(MockMvcRequestBuilders.post(urlOffline)
                .header("version", "1.0")
                .header("token", token2)
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.SUCCESS_CODE))
                .andExpect(jsonPath("$.message").value(""))
                .andDo(print());
        assertEquals (false,cache.isGlassOnline(token2));

    }

    @Test
    public void testglassListSuccess() throws Exception {
        String urlglassList = "http://localhost:8080/smartglass/glasslist?div_id=1";

        String token1 = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJjZXJ0aWZpY2F0aW9uS2V5IjoiMTIzIiwiaWF0IjoxNDk3M" +
                "zUxODczLCJleHAiOjE0OTc0MzgyNzMsIm5iZiI6MTQ5NzM1MTg3M30.EM48cd3Qsxcd4PSmtFy9xjyYl2NxyRfGgbU5MvJSTw8";

        ServerCache cache = ServerCache.getInstance();
        cache.addGlassToken(token1, "123");
        mockMvc.perform(MockMvcRequestBuilders.get(urlglassList)
                .header("version", "1.0")
                .header("token", token1))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.SUCCESS_CODE))
                .andExpect(jsonPath("$.message").value(""))
                .andDo(print());

    }

    @Test
    public void testglassListParameterError() throws Exception {
        String urlglassList = "http://localhost:8080/smartglass/glasslist?div_id=";

        String token1 = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJjZXJ0aWZpY2F0aW9uS2V5IjoiMTIzIiwiaWF0IjoxNDk3M" +
                "zUxODczLCJleHAiOjE0OTc0MzgyNzMsIm5iZiI6MTQ5NzM1MTg3M30.EM48cd3Qsxcd4PSmtFy9xjyYl2NxyRfGgbU5MvJSTw8";

        ServerCache cache = ServerCache.getInstance();
        cache.addGlassToken(token1, "123");
        mockMvc.perform(MockMvcRequestBuilders.get(urlglassList)
                .header("version", "1.0")
                .header("token", token1))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.ErrorCode.PARAMETER_LOST))
                .andExpect(jsonPath("$.message").value("div_idは必須項目です。"))
                .andDo(print());

    }

    @Test
    public void testglassListTypeError() throws Exception {
        String urlglassList = "http://localhost:8080/smartglass/glasslist?div_id=a";

        String token1 = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJjZXJ0aWZpY2F0aW9uS2V5IjoiMTIzIiwiaWF0IjoxNDk3M" +
                "zUxODczLCJleHAiOjE0OTc0MzgyNzMsIm5iZiI6MTQ5NzM1MTg3M30.EM48cd3Qsxcd4PSmtFy9xjyYl2NxyRfGgbU5MvJSTw8";

        ServerCache cache = ServerCache.getInstance();
        cache.addGlassToken(token1, "123");
        mockMvc.perform(MockMvcRequestBuilders.get(urlglassList)
                .header("version", "1.0")
                .header("token", token1))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.ErrorCode.INPUT_ERROR))
                .andExpect(jsonPath("$.message").value("入力値div_idが不正です。"))
                .andDo(print());

    }
}
