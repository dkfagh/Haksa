import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Chart extends JPanel {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	private String [] data = {"","",""};
	private int [] arcAngle = new int [3];
	private Color [] color = {Color.RED, Color.BLUE, Color.ORANGE};
	private String [] dept = {"컴퓨터시스템", "멀티미디어", "컴퓨터공학"};
	private ChartPanel chartpanel = new ChartPanel();
	int sum=0;
	
	public Chart() {
		DBmanager db = new DBmanager();
		conn=db.getConnection();
		
		setLayout(new BorderLayout());
		drawChart();
		add(new ValuePanel(),BorderLayout.NORTH);
		add(chartpanel,BorderLayout.CENTER);
		this.setSize(600, 500);
		this.setVisible(true);
	}
	
	
	public void drawChart() {
		
		try {
			stmt = conn.createStatement();
			
			for(int i = 0 ; i < dept.length ; i++) {
			ResultSet rs = stmt.executeQuery("select count(*) as count" + 
												" from student2 s, books b, bookrent br" + 
												" where s.id = br.id" + 
												" and b.no = br.bookNo" + 
												" and s.dept = '"+dept[i]+"'");
					String val = "";
					while(rs.next()) {
						val = rs.getString("count");
					}
				data[i] = val;
				sum+=Integer.parseInt(data[i]);
			}
			//if(sum == 0) return;		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<data.length; i++) 
			arcAngle[i]=(int)Math.round(Double.parseDouble(data[i])/(double)sum*360);
		
		chartpanel.repaint();
	}
	
	public class ValuePanel extends JPanel {
		
		public ValuePanel() {
			this.setBackground(Color.GRAY);
			for(int i=0 ; i<dept.length ; i++ ) {
				add(new JLabel(dept[i]+" " + data[i] + "건  "));
			}
			add(new JLabel(" /   총 대여 건수 : "+Integer.toString(sum)+"건"));
		}
	}
	
	private class ChartPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int startAngle = 0;
			for(int i=0; i<data.length; i++) {
				g.setColor(color[i]);
				g.drawString(dept[i]+" "+Math.round(arcAngle[i]*100./360.)+"%", 40+i*190, 30);
			}
			for(int i=0; i<data.length; i++) {
				g.setColor(color[i]);
				g.fillArc(120, 50, 350, 350, startAngle, arcAngle[i]);
				startAngle = startAngle + arcAngle[i];
		}
	}
	}
}