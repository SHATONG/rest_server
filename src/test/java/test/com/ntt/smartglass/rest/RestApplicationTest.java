package test.com.ntt.smartglass.rest;

import com.ntt.smartglass.rest.main.RestApplication;
import com.wix.mysql.EmbeddedMysql;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.distribution.Version.v5_7_latest;

import javax.sql.DataSource;

/**
 * Created by yeming on 2017/06/14.
 */
public class RestApplicationTest extends RestApplication {

    @Override
    public void run(String... strings) throws Exception {

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("db/db.sql"));
    }

}
