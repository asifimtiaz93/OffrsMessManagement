import java.text.SimpleDateFormat;
import java.util.Date;
public class test {
	
	public static void main(String[] args) {
		Date thisDate = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");
		
		System.out.println(dateformat.format(thisDate));
	}
}
