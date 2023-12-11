package uz.iftixortalim.crmspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uz.iftixortalim.crmspring.mapper.AttendanceMapper;
import uz.iftixortalim.crmspring.repository.AttendanceRepository;

@SpringBootTest
class CrmSpringApplicationTests {
	@Autowired
	private AttendanceRepository attendanceRepository;
	@Autowired
	private AttendanceMapper attendanceMapper;
	@Test
	void contextLoads() {

	}

}
