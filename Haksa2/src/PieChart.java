import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PieChart extends JPanel {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	private int [] data = {0,0,0};
	private int [] arcAngle = new int [3];
	private Color [] color = {Color.RED, Color.BLUE, Color.ORANGE};
	private String [] dept = {"컴퓨터시스템", "멀티미디어", "컴퓨터공학"};
	private JTextField[] tf =new JTextField[3];
	private ChartPanel chartpanel = new ChartPanel();
	
	public PieChart() {
		add(new ValuePanel(),BorderLayout.NORTH);
		add(chartpanel,BorderLayout.CENTER);
		this.setSize(600, 500);
		this.setVisible(true);
		drawChart();
	}
	private void drawChart() {
		DBmanager db = new DBmanager();
		conn=db.getConnection();
		
		int sum=0;
		for(int i=0; i<data.length; i++) {
			data[i] = Integer.parseInt(tf[i].getText());
			sum+=data[i];			
		}

		if(sum == 0) return;

		for(int i=0; i<data.length; i++) 
			arcAngle[i]=(int)Math.round((double)data[i]/(double)sum*360);
		
		chartpanel.repaint();
	}
	
	private class ValuePanel extends JPanel {
		
		public ValuePanel() {
			this.setBackground(Color.GRAY);
			for(int i=0 ; i<dept.length ; i++ ) {
				tf[i] = new JTextField("0", 8);
				tf[i].addActionListener(new MyActionListener());
				add(new JLabel(dept[i]));
				add(tf[i]);
			}
		}
		private class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource();
				int n;
				try {
					n = Integer.parseInt(t.getText());
				}catch(Exception ex) {
					t.setText("0");
					return;
				}
				drawChart();
			}
		}
	}
	
	private class ChartPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int startAngle = 0;
			for(int i=0; i<data.length; i++) {
				g.setColor(color[i]);
				g.drawString(dept[i]+" "+Math.round(arcAngle[i]*100./360.)+"%", 40+i*150, 30);
			}
			for(int i=0; i<data.length; i++) {
				g.setColor(color[i]);
				g.fillArc(120, 50, 350, 350, startAngle, arcAngle[i]);
				startAngle = startAngle + arcAngle[i];
		}
	}
	}
}