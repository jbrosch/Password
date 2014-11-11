package mainPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class Password extends JPanel implements ActionListener {
	private static String OK = "ok";
	private static String HELP = "help";

	private JFrame controllingFrame; 
	private JPasswordField passwordField;

	public Password(JFrame f) {

		controllingFrame = f;

		passwordField = new JPasswordField(10);
		passwordField.setActionCommand(OK);
		passwordField.addActionListener(this);

		JLabel label = new JLabel("Enter the password: ");
		label.setLabelFor(passwordField);

		JComponent buttonPane = createButtonPanel();

		JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		textPane.add(label);
		textPane.add(passwordField);

		add(textPane);
		add(buttonPane);
	}

	JComponent createButtonPanel() {
		JPanel p = new JPanel(new GridLayout(0,1));
		JButton okButton = new JButton("OK");
		JButton helpButton = new JButton("Help");

		okButton.setActionCommand(OK);
		helpButton.setActionCommand(HELP);
		okButton.addActionListener(this);
		helpButton.addActionListener(this);

		p.add(okButton);
		p.add(helpButton);

		return p;
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (OK.equals(cmd)) {
			char[] input = passwordField.getPassword();
			if (isPasswordCorrect(input)) {
				JOptionPane.showMessageDialog(controllingFrame,
						"This password is correct.");
			} else {
				JOptionPane.showMessageDialog(controllingFrame,
						"Wrong Password",
						"Error Message",
						JOptionPane.ERROR_MESSAGE);
			}

			passwordField.selectAll();

		} else {
			JOptionPane.showMessageDialog(controllingFrame,
					"Look at the code");
		}
	}

	private static boolean isPasswordCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPassword = { 'b', 'u', 'g', 'a', 'b', 'o', 'o' };

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals (input, correctPassword);
		}
		return isCorrect;
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Password");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final Password newContentPane = new Password(frame);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}