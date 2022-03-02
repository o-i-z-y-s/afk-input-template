import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.text.DecimalFormat;

public class idleinput {

	// used to format the elapsed time
	private static DecimalFormat decimal = new DecimalFormat("0.00"); 

	// the object which handles mouse and key events 
	private static Robot robot = null;
	private static void createRobot() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	
	protected static void autoClick() {

		int clickCount = 50; // the number of clicks that will be done
		int clickDelay = 20; // milliseconds  between clicks
		int milestone = 10; // after this number of clicks, progress will be reported

		createRobot();

		for (int i = 1; i <= clickCount; i++) {

			robot.delay(clickDelay);
			robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);

			if (i % milestone == 0) { // prints progress during loop at the increment specified 
				System.out.println("Progress: " + i + "/" + clickCount);
			}

			try {
				TimeUnit.MILLISECONDS.sleep(clickDelay);
			}
			catch(InterruptedException e) {
				System.out.println("got interrupted!");
			}

		}

	}


	// Types WASD the specified number of times. Each key is pressed for a randomly determined amount of time within the given range. Keys pressed can be changed as needed.
	protected static void autoKeys() {

		Random rand = new Random();
		int keyDelay = rand.nextInt(25) * 60000; // How often the keys will be entered. Uses a random number between 0 and what's entered in '.nextInt()'
		int keysCount = 10; // Number of times the keys will be entered
		int secondsRange = 10; // The upper bound of how long each key will be held for.
		int milestone = 1; // How many repetitions before reporting progress.

		createRobot();

		for (int i = 1; i <= keysCount; i++) {

			// prints progress during loop at the increment specified 
			if (i % milestone == 0) { 
				System.out.println("This delay will last " + (keyDelay/60000) + " mins. Progress: " + i + "/" + keysCount);
			}

			int w_interval = rand.nextInt(secondsRange); 
			robot.keyPress(KeyEvent.VK_W);
			robot.delay(100*w_interval);
			robot.keyRelease(KeyEvent.VK_W);
	
			int a_interval = rand.nextInt(secondsRange); 
			robot.keyPress(KeyEvent.VK_A);
			robot.delay(100*a_interval);
			robot.keyRelease(KeyEvent.VK_A);
	
			int s_interval = rand.nextInt(secondsRange); 
			robot.keyPress(KeyEvent.VK_S);
			robot.delay(100*s_interval);
			robot.keyRelease(KeyEvent.VK_S);
	
			int d_interval = rand.nextInt(secondsRange); 
			robot.keyPress(KeyEvent.VK_D);
			robot.delay(100*d_interval);
			robot.keyRelease(KeyEvent.VK_D);

			try {
				TimeUnit.MILLISECONDS.sleep(keyDelay);
			}
			catch(InterruptedException e) {
				System.out.println("got interrupted!");
			}
		}

	}


	// This gives you a moment to prepare, lest the clicker seize control before your cursor is in position.
	private static void countdown() {
		try {
			System.out.println("3");
			TimeUnit.SECONDS.sleep(1);

			System.out.println("2");
			TimeUnit.SECONDS.sleep(1);

			System.out.println("1");
			TimeUnit.SECONDS.sleep(1);

			System.out.println("go");
		}
		catch(InterruptedException e) {
			System.out.println("Interrupted!");
			return;
		}
	}
	 

	public static void main(String args[]){

		countdown();
		
		long start = System.nanoTime();

		// Uncomment the one you'd like to run.
		// autoClick();
		// autoKeys();

		double elapsedTime = ((double)System.nanoTime() - (double)start) / 1_000_000_000;

		int hour = (int)elapsedTime / 60;
		int min = hour % 60;
		String sec = decimal.format(elapsedTime % 60);

		// prints time based on number of seconds elapsed
		if (elapsedTime >= 3600) { System.out.println("Done! " + hour + "h " + min + "m " + sec +"s elapsed"); }
		else if (elapsedTime >= 60) { System.out.println("Done! " + min + "m " + sec +"s elapsed"); }
		else { System.out.println("Done! " + sec +"s elapsed"); }
	}	
	
}