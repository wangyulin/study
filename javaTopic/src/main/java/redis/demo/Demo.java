package redis.demo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Demo {
	
	private static List<String> hosts = new ArrayList<String>();
	
	static {
		hosts.add("wcc-thm-fe00");
		hosts.add("wcc-thm-fe01");
		hosts.add("wcc-thm-fe02");
		hosts.add("wcc-thm-fe03");
		hosts.add("wcc-thm-fe04");
		hosts.add("wcc-thm-fe05");
		hosts.add("wcc-thm-fe06");
		hosts.add("wcc-thm-fe07");
		hosts.add("wcc-thm-fe08");
		hosts.add("wcc-thm-fe09");
		hosts.add("wcc-thm-fe10");
		hosts.add("wcc-thm-fe11");
		hosts.add("wcc-thm-fe12");
		hosts.add("wcc-thm-fe13");
		hosts.add("wcc-thm-fe14");
		hosts.add("wcc-thm-fe15");
		hosts.add("wcc-thm-fe16");
		hosts.add("wcc-thm-fe17");
		hosts.add("wcc-thm-fe18");
		hosts.add("wcc-thm-fe19");
		hosts.add("wcc-thm-fe20");
		hosts.add("wcc-thm-fe21");
	}

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 1);
		
		System.out.println(CounterUtils.lrang("host_list"));
		for(int i = 0; i < 22; i++ ) {
			Thread t = new Thread(new Task(hosts.get(i),cal));
			t.start();
			t.yield ();
		}
		
	}

}
