package helpers;


public class Helpers {
	
	public void TimeSeconds(int seconds){
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
