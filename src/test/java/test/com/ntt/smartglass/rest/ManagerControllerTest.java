package test.com.ntt.smartglass.rest;

import com.ntt.smartglass.common.consts.RestConst;
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
public class ManagerControllerTest {

    private MockMvc mockMvc;
    private String urlAuth = "http://localhost:8080/smartglass/managerlogin";

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testManagerLoginSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(urlAuth)
                .header("version", "1.0")
                .content("{\"username\":\"test\",\"password\":\"123456\"}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.SUCCESS_CODE))
                .andDo(print());
    }

    @Test
    public void testManagerErrorSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(urlAuth)
                .header("version", "1.0")
                .content("{\"username\":\"test1\",\"password\":\"123456\"}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.ErrorCodeEnum.LOGIN_FAILED.getCode()))
                .andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.post(urlAuth)
                .header("version", "1.0")
                .content("{\"username\":\"test\",\"password\":\"123456789\"}")
                .header("Content-Type", "application/json"))
                .andExpect(jsonPath("$.statusCode")
                        .value(RestConst.ErrorCodeEnum.LOGIN_FAILED.getCode()))
                .andDo(print());
    }


}
