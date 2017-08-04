package tr.org.linux.kamp.agar.model.gorunum;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.ldap.Rdn;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import tr.org.linux.kamp.agar.logic.GameLogic;
import tr.org.linux.kamp.agar.model.Dificulty;

public class FirstPanel extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblColor;
	private JComboBox comboBox;
	private JLabel lblNewLabel_1;
	private JRadioButton lblNewLabel_2;
	private JRadioButton lblNormal;
	private JRadioButton lblHard;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private ButtonGroup buttonGroup;

	/**
	 * Create the panel.
	 */
	public FirstPanel() {

		JLabel lblUserName = new JLabel("User Name:");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Password:");

		passwordField = new JPasswordField();

		lblColor = new JLabel("Color:");

		comboBox = new JComboBox();
		comboBox.addItem("RED");
		comboBox.addItem("BLUE");
		comboBox.addItem("GREEN");
		comboBox.addItem("YELLOW");

		lblNewLabel_1 = new JLabel("Diffucult:");

		lblNewLabel_2 = new JRadioButton("EASY");

		lblNormal = new JRadioButton("NORMAL");
		lblNormal.setSelected(true);

		lblHard = new JRadioButton("HARD");
		buttonGroup = new ButtonGroup(); // tek bir radio button secne isleni
		buttonGroup.add(lblNormal);
		buttonGroup.add(lblHard);
		buttonGroup.add(lblNewLabel_2);

		btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Color selectedColor = Color.BLACK;
				switch (comboBox.getSelectedItem().toString()) {
				case "RED":
					selectedColor = Color.RED;
					break;

				case "BLUE":
					selectedColor = Color.BLUE;
					break;

				case "GREEN":
					selectedColor = Color.GREEN;
					break;

				case "YELLOW":
					selectedColor = Color.YELLOW;
					break;

				default:
					break;
				}

				Dificulty dificulty = null;

				if (lblHard.isSelected()) {
					// HARDD
					dificulty = Dificulty.HARD;

				} else if (lblNormal.isSelected()) {
					// MEDİUM
					dificulty = Dificulty.NORMAL;
				} else if (lblNewLabel_2.isSelected()) {
					// EASY
					dificulty = Dificulty.EASY;
				}

				GameLogic gameLogic = new GameLogic(textField.getText(),
						selectedColor, dificulty);
				gameLogic.startApplication();

			}
		});

		btnNewButton_1 = new JButton("About");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(FirstPanel.this,
						"31Temmuz \n Linux Yaz Kampı \n Ali Tosun", "About",
						JOptionPane.DEFAULT_OPTION);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblNewLabel,
																GroupLayout.PREFERRED_SIZE,
																72,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblUserName,
																GroupLayout.PREFERRED_SIZE,
																72,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblColor,
																GroupLayout.PREFERRED_SIZE,
																61,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblNewLabel_1,
																GroupLayout.PREFERRED_SIZE,
																80,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblNormal)
																						.addComponent(
																								lblNewLabel_2,
																								GroupLayout.DEFAULT_SIZE,
																								351,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblHard)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												btnNewButton_1)
																										.addGap(5))
																						.addComponent(
																								btnNewButton)))
														.addComponent(
																comboBox,
																GroupLayout.PREFERRED_SIZE,
																322,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								textField,
																								Alignment.LEADING)
																						.addComponent(
																								passwordField,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								359,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addGap(288)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(6)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblUserName)
														.addComponent(
																textField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(8)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel)
														.addComponent(
																passwordField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(11)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblColor)
														.addComponent(
																comboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel_1)
														.addComponent(
																lblNewLabel_2))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblNormal)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblHard)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(btnNewButton).addGap(12)
										.addComponent(btnNewButton_1)
										.addContainerGap(126, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}
}
