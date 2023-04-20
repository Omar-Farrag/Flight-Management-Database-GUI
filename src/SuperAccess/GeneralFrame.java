/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SuperAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GeneralFrame extends JFrame {

    public GeneralFrame() {
        // Create the JFrame
//        JFrame frame = new JFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());

        // Create the JLabel at the top
        JLabel label = new JLabel("My Label");
        label.setFont(new Font("Verdana", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
        label.setPreferredSize(new Dimension(getWidth(), 75));

        // Create the JPanel inside the JScrollPane
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create the gray JLabel and JTextField
        JLabel grayLabel = new JLabel("Gray Label");
        grayLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        grayLabel.setForeground(Color.BLACK);
        grayLabel.setBackground(Color.GRAY);
        grayLabel.setOpaque(true);
        grayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        grayLabel.setVerticalAlignment(SwingConstants.CENTER);
        int grayLabelWidth = (int) (getWidth() * 0.2); // Set the width of the gray label to 10% of the window width
        grayLabel.setPreferredSize(new Dimension(grayLabelWidth, 50));

        JTextField textField = new JTextField(20);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField.setBackground(Color.RED);
        textField.setPreferredSize(new Dimension(grayLabelWidth, 50));

        JTextField textField2 = new JTextField(20);
        textField2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField2.setBackground(Color.RED);
        textField2.setPreferredSize(new Dimension(grayLabelWidth, 50));

        // Add the gray JLabel and JTextField to the panel
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, (int) (getWidth() * 0.1), 10, 0);
        panel.add(grayLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.BELOW_BASELINE;
        c.insets = new Insets(10, 10, 10, (int) (getWidth() * 0.1));
        panel.add(textField, c);

        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.BELOW_BASELINE;
        c.insets = new Insets(10, 10, 10, (int) (getWidth() * 0.1));
        panel.add(textField2, c);

        // Create the JScrollPane and add the panel to it
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        // Set the JFrame to be visible
        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int grayLabelWidth = (int) (getWidth() * 0.2); // Set the width of the gray label to 10% of the window width
                grayLabel.setPreferredSize(new Dimension(grayLabelWidth, 50));
                grayLabel.setFont(new Font("Verdana", Font.BOLD, (int) (getWidth() * 0.02)));
//                System.out.println();
            }
        });
    }

    public static void main(String[] args) {
        new GeneralFrame();
    }
}
