import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.*;

public class Haksa2 extends JFrame {
	JPanel panel = null;   //container 역할
	static Connection conn = null;
	static Statement stmt = null;
	
	public Haksa2() {
		
//		//DB연동
//		try {
////			Oracle 연결
////			Class.forName("oracle.jdbc.driver.OracleDriver");
////			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle", "ora_user", "hong");
//			
//			
////			MySQL 연결
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?useSSL=false","hkd","1234");
//			stmt = conn.createStatement();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		
		DBmanager db = new DBmanager();
		conn=db.getConnection();
		
		this.setTitle("학사관리");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Container c = getContentPane();
//		c.setLayout(new FlowLayout());
		
		JMenuBar mb = new JMenuBar();
		JMenu Menu1 = new JMenu("학생관리");
		JMenuItem item1 = new JMenuItem("학생정보");
		item1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("학생정보");
				
				panel.removeAll();    // 모든 컴포넌트 삭제
				panel.revalidate();   // 다시 활성화
				panel.repaint();      // 다시 그리기
				panel.add(new Student());   // 화면 생성
				panel.setLayout(null);   // 레이아웃 적용 안함
			}
		});
		JMenu Menu2 = new JMenu("도서관리");
		JMenuItem item2 = new JMenuItem("대출목록");
		item2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("대출목록");
				
				panel.removeAll();    // 모든 컴포넌트 삭제
				panel.revalidate();   // 다시 활성화
				panel.repaint();      // 다시 그리기
				panel.add(new BookRent());   // 화면 생성
				panel.setLayout(null);   // 레이아웃 적용 안함
			}
		});
		JMenuItem item3 = new JMenuItem("대출현황");
		item3.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("대출현황");
				
				panel.removeAll();    // 모든 컴포넌트 삭제
				panel.revalidate();   // 다시 활성화
				panel.repaint();      // 다시 그리기
				panel.add(new Chart());   // 화면 생성
				panel.setLayout(null);   // 레이아웃 적용 안함
			}
		});
		
		ImageIcon main = new ImageIcon("img/img01.jpg");
		JLabel mainImage = new JLabel(main);
		
		Menu1.add(item1);
		mb.add(Menu1);
		
		Menu2.add(item2);
		Menu2.add(item3);
		mb.add(Menu2);
		
		this.setJMenuBar(mb);
		this.add(mainImage);
		
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
		
		//this.setSize(800,600);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Haksa2();
	}
}