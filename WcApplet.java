import java.awt.*;
import java.awt.event.*;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class WcApplet extends JApplet {
	public void init () {
		try {
			SwingUtilities.invokeAndWait (new
			Runnable () {
				public void run () {			

				       WordCounterView wcv = new WordCounterView ();
					wcv.show ();
				}
			});
		} catch (Exception e) {
			System.err.println ("CreateGui didint work");
		}
	}	
}

class WordCounterView implements ActionListener {

	public void show () {
		createInterface ();
	}
	private void createInterface () {	
		aWindow = new Frame ("HelloWorld");
		aWindow.setSize (windowWidth, windowHeight);
		BorderLayout layout = new BorderLayout ();
		wordBox = new TextArea ();
		Label header = new Label ();
		header.setText (directions);
		wordBox.setRows (wordBoxHeight);
		wordBox.setColumns (wordBoxWidth);
		Button submitBut = new Button ();
		submitBut.setLabel (submitButLabel);
		submitBut.setActionCommand (submitButCmd);
		submitBut.addActionListener (this);
		aWindow.setLayout (layout);
		aWindow.add (header, BorderLayout.NORTH);
		aWindow.add (wordBox, BorderLayout.CENTER);
		aWindow.add (submitBut, BorderLayout.SOUTH);
		aWindow.show ();
	}
	private static Frame aWindow;
	private static int windowHeight = 450, windowWidth = 600;
	private static String submitButLabel = "Submit";
	private static String submitButCmd = submitButLabel;
	private static int wordBoxHeight = 200, wordBoxWidth = 500;
	private static String directions = "Counts number of words.";
	private static TextArea wordBox;
	private static WordCounter wc;
	@Override
	public void actionPerformed (ActionEvent e) {
		String cmd = e.getActionCommand ();
		if (cmd.equals (submitButCmd)) {
			int wordCount = countWords ();
			String msg = "Word Count: " + wordCount;
			showCounterBox (msg);
		} 
	}
	private int countWords () {
		String text = wordBox.getText ();
		return wc.countWords (text);
	}
	private void showCounterBox (String msg) {
		javax.swing.JOptionPane.showMessageDialog(aWindow, msg);
	}
}

class WordCounter {
	public WordCounter () {

	}
	public static int countWords (String text) {
		byte [] chars = text.getBytes ();
		int wordCount = 1;
		for (int i = 0; i < chars.length; ++i) {
			if (chars [i] == 0x20) {
				++wordCount;
				while (chars [i] == 0x20) {
					++i;
				}
			}
		}
		return wordCount;
	}
}
