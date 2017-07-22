package test.com.ntt.smartglass.rest;

import com.ntt.smartglass.common.aop.ServiceMonitor;
import com.ntt.smartglass.common.consts.RestConst;
import com.ntt.smartglass.common.model.DataResult;

import com.ntt.smartglass.rest.main.RestApplication;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.hibernate.validator.constraints.NotBlank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestApplication.class)
@SpringBootTest
@DirtiesContext
public class ServiceMonitorTest {

    private ServiceMonitor monitor=new ServiceMonitor();

    @Test
    public void testServiceAccess() throws Throwable {
        ProceedingJoinPoint pjp = Mockito.mock(ProceedingJoinPoint.class);
        when(pjp.getTarget()).thenReturn("com.ntt.smartglass.rest.controller.GlassController");
        when(pjp.getTarget().getClass().getName()).thenReturn("com.ntt.smartglass.rest.controller.GlassController");
        when(pjp.getSignature()).thenReturn(Mockito.mock(Signature.class));
        when(pjp.getSignature().getName()).thenReturn("com.ntt.smartglass.rest.controller.GlassController.auth");
        Object[] objects = {"version", getTestData1(), getTestData2()};
        when(pjp.getArgs()).thenReturn(objects);
        assertEquals(RestConst.ErrorCodeEnum.UNKNOWN.getCode(),((DataResult<String>) monitor.logServiceAccess(pjp)).getStatusCode());


        Object[] objects1 = {"version", getTestData3()};
        when(pjp.getArgs()).thenReturn(objects1);
        assertEquals(RestConst.ErrorCodeEnum.UNKNOWN.getCode(),((DataResult<String>) monitor.logServiceAccess(pjp)).getStatusCode());


        when(pjp.proceed()).thenReturn(new DataResult<String>("1001", "1213"));
        when(pjp.getArgs()).thenReturn(null);
        assertEquals(RestConst.ErrorCodeEnum.PARAMETER_LOST.getCode(),((DataResult<String>) monitor.logServiceAccess(pjp)).getStatusCode());


        when(pjp.proceed()).thenThrow(new SQLException());
        assertEquals(RestConst.ErrorCodeEnum.UNKNOWN.getCode(),((DataResult<String>) monitor.logServiceAccess(pjp)).getStatusCode());

    }

    @Test
    public void testThowable() throws Throwable {
        ProceedingJoinPoint pjp = Mockito.mock(ProceedingJoinPoint.class);
        when(pjp.getTarget()).thenReturn("com.ntt.smartglass.rest.controller.GlassController");
        when(pjp.getTarget().getClass().getName()).thenReturn("com.ntt.smartglass.rest.controller.GlassController");
        when(pjp.getSignature()).thenReturn(Mockito.mock(Signature.class));
        when(pjp.getSignature().getName()).thenReturn("com.ntt.smartglass.rest.controller.GlassController.auth");
        Object[] objects = {};
        when(pjp.getArgs()).thenReturn(null);
      when( pjp.proceed()).thenThrow(new Throwable());

        assertEquals(RestConst.ErrorCodeEnum.UNKNOWN.getCode(),((DataResult<String>) monitor.logServiceAccess(pjp)).getStatusCode());
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

        @NotNull(message = "name , null")
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
