package spittr.base;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import spittr.web.HomeController;

public class HomeControllerTest {
	
	@Test
	public void testHomePage() throws Exception {
        DataAccessException e;
		HomeController controller = new HomeController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform ( get ( "/homepage" ) ).andExpect ( view ().name ( "home" ) );
        JdbcOperations r;
        JdbcTemplate jt;
        NamedParameterJdbcTemplate named;
		//assertEquals("home", controller.home());
	}
	
}
