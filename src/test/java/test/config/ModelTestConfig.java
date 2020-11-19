package test.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
						{"classpath:app/config/spring/root-context.xml",
						 "classpath:app/config/spring/application-context_dev.xml",
						 "classpath:app/config/db/datasource-context_dev.xml"})
public class ModelTestConfig {
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	@Ignore
	@Test
	public void dummy() {
		
	}
	
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		// 실행할 스크립트를 추가할 수 있다.
		// 사용할 파일(초기화를 위해 사용할 sql 파일)의 경로를 기입한다.
		// (Resource 인터페이스를 참조)
		populator.addScripts(new ClassPathResource("/app/config/db/initData_dev.sql")); // 설정 파일을 추가한다.
		populator.setContinueOnError(false);	// 스크립트 실행중 에러 발생시 멈춤 ..
		
		// spring bean으로 등록해놓은 dataSource를 사용한다.
		// 스크립트를 실행한다. 
		DatabasePopulatorUtils.execute(populator, dataSource);
	}
}