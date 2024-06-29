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


	// Clicks a 'clickCount' number of times with 'clickDelay' milliseconds between them and reports progress every 'milestone' times.
	protected static void autoClick() {

		int clickCount = 500; // the number of clicks that will be done
		int clickDelay = 20; // milliseconds  between clicks
		int milestone = 50; // after this number of clicks, progress will be reported

		createRobot();

		for (int i = 1; i <= clickCount; i++) {

			robot.delay(clickDelay);

			// left click
			robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);

			// right click
			// robot.mousePress(MouseEvent.BUTTON3_DOWN_MASK);
			// robot.mouseRelease(MouseEvent.BUTTON3_DOWN_MASK);

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
		int keyDelay;
		int keysCount = 10; // Number of times the keys will be entered
		int secondsRange = 10; // The upper bound of how long each key will be held for.
		int milestone = 1; // How many repetitions before reporting progress.

		createRobot();

		for (int i = 0; i <= keysCount; i++) {
			keyDelay = rand.nextInt(25); // How often the keys will be entered in seconds. Uses a random number between 0 and what's entered in '.nextInt()'

			// prints progress during loop at the increment specified by milestone
			if (i % milestone == 0) { 
				System.out.println("This delay will last " + getTime(keyDelay) + ". Progress: " + (i + 1) + "/" + keysCount);
			}

			// The '_interval' variables here control how long each key is pressed.
			// 'KeyEvent.VK_W' corresponds to an action on the W key. Note that you need to send a release event after a press event.
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
				TimeUnit.MILLISECONDS.sleep(keyDelay * 1000);
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
	 
	public static String getTime(double elapsedTime) {
		int hour = (int)elapsedTime / 60;
		int min = hour % 60;
		String sec = decimal.format(elapsedTime % 60);

		if (elapsedTime >= 3600) { return hour + "h " + min + "m " + sec +"s"; }
		else if (elapsedTime >= 60) { return min + "m " + sec +"s"; }
		else { return sec +"s"; }
	}

	// The heart of the function that sets the instructions for running the script. Uncomment either 'autoclick()' or 'autoKeys()'.
	public static void main(String args[]){

		countdown();

		long start = System.nanoTime();

		// Uncomment the one you'd like to run.
		// autoClick();
		autoKeys();

		double elapsedTime = ((double)System.nanoTime() - (double)start) / 1_000_000_000;

		System.out.println("Done! " + getTime(elapsedTime) +" elapsed");
	}	
	
}