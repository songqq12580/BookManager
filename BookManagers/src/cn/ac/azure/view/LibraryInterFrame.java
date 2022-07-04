package cn.ac.azure.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class LibraryInterFrame extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryInterFrame frame = new LibraryInterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LibraryInterFrame() {
		//改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		setClosable(true);
		setIconifiable(true);
		setBounds(450, 150, 503, 300);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LibraryInterFrame.class.getResource("/images/library.png")));
		
		JLabel label_1 = new JLabel(" \u6B22\u8FCE\u4F7F\u7528\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		label_1.setForeground(Color.GREEN);
		label_1.setBackground(Color.GREEN);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(140, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(175))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addGap(137))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addComponent(label)
					.addGap(28)
					.addComponent(label_1)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
}
