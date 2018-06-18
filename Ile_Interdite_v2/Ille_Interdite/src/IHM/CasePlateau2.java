package IHM;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;

public class CasePlateau2 extends JPanel {

	/**
	 * Create the panel.
	 */
	public CasePlateau2() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		add(lblNewLabel);

	}

}
