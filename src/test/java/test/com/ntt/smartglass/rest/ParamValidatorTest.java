package test.com.ntt.smartglass.rest;

import com.ntt.smartglass.common.consts.RestConst;
import com.ntt.smartglass.common.validator.ParamValidator;
import com.ntt.smartglass.rest.main.RestApplication;
import org.hibernate.validator.constraints.NotBlank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by liuqi on 2017/06/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestApplication.class)
@TestPropertySource(locations="classpath:test.properties")
@SpringBootTest
public class ParamValidatorTest {

    @Test
    public void testValidator() throws Exception {
        Map<String, ArrayList<String>> result1 = ParamValidator.validator(getTestData1());
        assertEquals(null, result1);

        Map<String, ArrayList<String>> result2 = ParamValidator.validator(getTestData2());
        assertEquals(2, result2.size());

        Map<String, ArrayList<String>> result3 = ParamValidator.validator(getTestData3());
        assertEquals(1, result3.size());

        HashSet<String> set = new HashSet<String>();
        set.add("a");

        Map<String, ArrayList<String>> result4 = ParamValidator.validator(getTestData1(), set);
        assertEquals(null, result4);

        assertEquals(RestConst.ErrorCode.INPUT_ERROR, "1002");
        assertEquals(RestConst.ErrorCode.PARAMETER_LOST, "1001");
    }

    private TestData getTestData1() {
        TestData data = new TestData();
        data.setId(1);
        data.setName("");
        data.setSex("liu");
        return data;
    }

    private TestData getTestData3() {
        TestData data = new TestData();
        data.setId(1);

        data.setSex("liu");
        return data;
    }

    private TestData getTestData2() {
        TestData data = new TestData();
        data.setId(2);
        data.setSex("");
        return data;
    }

    public class TestData {


        private Integer id;

        @NotNull(message = "name null")
        private String name;


        @NotBlank(message = "sex can't be empty")
        private String sex;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
