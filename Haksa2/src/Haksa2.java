import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.*;

public class Haksa2 extends JFrame {
	JPanel panel = null;   //container ����
	static Connection conn = null;
	static Statement stmt = null;
	
	public Haksa2() {
		
//		//DB����
//		try {
////			Oracle ����
////			Class.forName("oracle.jdbc.driver.OracleDriver");
////			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle", "ora_user", "hong");
//			
//			
////			MySQL ����
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?useSSL=false","hkd","1234");
//			stmt = conn.createStatement();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		
		DBmanager db = new DBmanager();
		conn=db.getConnection();
		
		this.setTitle("�л����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mb = new JMenuBar();
		JMenu Menu1 = new JMenu("�л�����");
		JMenuItem item1 = new JMenuItem("�л�����");
		item1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("�л�����");
				
				panel.removeAll();    // ��� ������Ʈ ����
				panel.revalidate();   // �ٽ� Ȱ��ȭ
				panel.repaint();      // �ٽ� �׸���
				panel.add(new Student());   // ȭ�� ����
				panel.setLayout(null);   // ���̾ƿ� ���� ����
			}
		});
		JMenu Menu2 = new JMenu("��������");
		JMenuItem item2 = new JMenuItem("������");
		item2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("������");
				
				panel.removeAll();    // ��� ������Ʈ ����
				panel.revalidate();   // �ٽ� Ȱ��ȭ
				panel.repaint();      // �ٽ� �׸���
				panel.add(new BookRent());   // ȭ�� ����
				panel.setLayout(null);   // ���̾ƿ� ���� ����
			}
		});
		JMenuItem item3 = new JMenuItem("������Ȳ");
		item3.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("������Ȳ");
				
				panel.removeAll();    // ��� ������Ʈ ����
				panel.revalidate();   // �ٽ� Ȱ��ȭ
				panel.repaint();      // �ٽ� �׸���
				panel.add(new PieChart());   // ȭ�� ����
				panel.setLayout(null);   // ���̾ƿ� ���� ����
			}
		});
		
		Menu1.add(item1);
		mb.add(Menu1);
		
		Menu2.add(item2);
		Menu2.add(item3);
		mb.add(Menu2);
		
		this.setJMenuBar(mb);
		
		panel = new JPanel();
		this.add(panel);
		
		this.addWindowListener(new WindowListener() {
			@Override	public void windowOpened(WindowEvent e) {}
			@Override	public void windowClosing(WindowEvent e) {
				db.closeSet();}
			@Override	public void windowClosed(WindowEvent e) {}
			@Override	public void windowIconified(WindowEvent e) {}
			@Override	public void windowDeiconified(WindowEvent e) {}
			@Override	public void windowActivated(WindowEvent e) {}
			@Override	public void windowDeactivated(WindowEvent e) {}
		});
		
		this.setSize(800,600);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Haksa2();
	}
}