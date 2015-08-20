import java.awt.*;
class TestWcApplet {
	public static void main (String[] args) {
		createInterface ();
	}
	public static void createInterface () {
		WcApplet wc = new WcApplet ();
		wc.init ();
		wc.start ();
	}
}
