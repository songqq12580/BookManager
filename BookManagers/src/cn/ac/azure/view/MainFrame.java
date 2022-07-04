package cn.ac.azure.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	//�������ݴ���
	private JPanel contentPane;
	//�������洰��
	private JDesktopPane table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		
		//�ı�ϵͳĬ������
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		
		
		setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u57FA\u672C\u6570\u636E\u7EF4\u62A4");
		menu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/base.png")));
		menuBar.add(menu);
		
		JMenu mnNewMenu = new JMenu("\u56FE\u4E66\u7C7B\u522B\u7BA1\u7406");
		mnNewMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookTypeManager.png")));
		menu.add(mnNewMenu);
		
		//ͼ��������
		JMenuItem menuItem = new JMenuItem("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeAddInterFrame bookTypeAddInterFrame=new BookTypeAddInterFrame();
				bookTypeAddInterFrame.setVisible(true);
				table.add(bookTypeAddInterFrame);
			}
		});
		menuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		mnNewMenu.add(menuItem);
		
		//ͼ�����ά��
		JMenuItem menuItem_1 = new JMenuItem("\u56FE\u4E66\u7C7B\u522B\u7EF4\u62A4");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeManageInterFrame bookTypeManageInterFrame=new BookTypeManageInterFrame();
				bookTypeManageInterFrame.setVisible(true);
				table.add(bookTypeManageInterFrame);
			}
		});
		menuItem_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		mnNewMenu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		menu_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookManager.png")));
		menu.add(menu_1);
		
		//ͼ�����
		JMenuItem menuItem_2 = new JMenuItem("\u56FE\u4E66\u6DFB\u52A0");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAddInterFrame bookAddInterFrame=new BookAddInterFrame();
				bookAddInterFrame.setVisible(true);
				table.add(bookAddInterFrame);
			}
		});
		menuItem_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		menu_1.add(menuItem_2);
		//ͼ��ά��
		JMenuItem menuItem_3 = new JMenuItem("\u56FE\u4E66\u7EF4\u62A4");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookManageInterFrame bookManageInterFrame=new BookManageInterFrame();
				bookManageInterFrame.setVisible(true);
				table.add(bookManageInterFrame);
			}
		});
		menuItem_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		menu_1.add(menuItem_3);
		
		//��ȫ�˳�
		JMenuItem menuItem_4 = new JMenuItem("\u5B89\u5168\u9000\u51FA");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�����˳�ȷ����ʾ��
				int res=JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�˳���");
				//ȷ���˳�
				if(res==JOptionPane.OK_OPTION){
					dispose();
				} 
				//����������ڸý���
			}
		});
		menuItem_4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/exit.png")));
		menu.add(menuItem_4);
		
		JMenu menu_2 = new JMenu("\u5173\u4E8E\u6211\u4EEC");
		menu_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/about.png")));
		menuBar.add(menu_2);
		
		//��������
		JMenuItem menuItem_5 = new JMenuItem("\u5173\u4E8E\u6211\u4EEC");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�½�һ��ͼ���ڲ�����
				LibraryInterFrame libraryInnerFrame=new LibraryInterFrame();
				//��ʾͼ���ڲ�����
				libraryInnerFrame.setVisible(true);
				//��ͼ���ڲ�������ʾ�����������洰����
				table.add(libraryInnerFrame);
			}
		});
		menuItem_5.setIcon(new ImageIcon(MainFrame.class.getResource("/images/about.png")));
		menu_2.add(menuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//�������������洰����棬����װ���ڲ�����
		table = new JDesktopPane();
		table.setBackground(Color.LIGHT_GRAY);
		contentPane.add(table, BorderLayout.CENTER);
		//���ô������
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
