import java.awt.Dimension;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookRent extends JPanel{
	Connection conn = null;
	Statement stmt = null;
	DefaultTableModel model = null;
	JTable table = null;
	String query;
	
	public BookRent() {
		
		//conn = Haksa2.conn;
		DBmanager db = new DBmanager();
		conn = db.getConnection();
				
		query = "select s.id , s.name , b.title , br.rDate" +
				" from student2 s, books b, bookrent br" +
				" where s.id = br.id" +
				" and b.no = br.bookNo";
		
	    setLayout(null);   //���̾ƿ�����. ���̾ƿ� ��� ����.
	   
	    JLabel l_dept=new JLabel("�а�");
	    l_dept.setBounds(10, 10, 30, 20);
	    add(l_dept);
	    
	    
	    String colName[]={"�й�","�̸�","������","������"};
	    model=new DefaultTableModel(colName,0);
	    table = new JTable(model);
	    table.setPreferredScrollableViewportSize(new Dimension(470,200));
	    add(table);
	    JScrollPane sp=new JScrollPane(table);
	    sp.setBounds(10, 40, 460, 250);
	    add(sp);
	   
	   
	    //String[] dept={"��ü","��ǻ�Ͱ���","��Ƽ�̵��","�Ͼ��Ϲ���"};
	    String[] dept={"��ü","��ǻ�ͽý���","��Ƽ�̵��","��ǻ�Ͱ���"};

	    JComboBox cb_dept=new JComboBox(dept);
	    
	    cb_dept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				System.out.println(cb.getSelectedIndex());
				int si = cb.getSelectedIndex();
				
				query = "select s.id , s.name , b.title , br.rDate" + 
						" from student2 s, books b, bookrent br" + 
						" where s.id = br.id" + 
						" and b.no = br.bookNo";
				
//				if(si==0) {   //��ü
//					query += " order by br.no";
//				} else if(si==1) {   //��ǻ�Ͱ���
//					query += " and s.dept = '��ǻ�Ͱ���' order by br.no";
//				} else if(si==2) {   //��Ƽ�̵��
//					query += " and s.dept = '��Ƽ�̵��' order by br.no";
//				} else if(si==3) {   //�Ͼ��Ϲ���
//					query += " and s.dept = '�Ͼ��Ϲ���' order by br.no";
//				}
				
				if(si==0) {   //��ü
					query += " order by br.no";
				} else if(si==1) {   //��ǻ�ͽý���
					query += " and s.dept = '��ǻ�ͽý���' order by br.no";
				} else if(si==2) {   //��Ƽ�̵��
					query += " and s.dept = '��Ƽ�̵��' order by br.no";
				} else if(si==3) {   //��ǻ�Ͱ���
					query += " and s.dept = '��ǻ�Ͱ���' order by br.no";
				}
				
				//�˻����
				list();
				
			}});
	    cb_dept.setBounds(45, 10, 100, 20);
	    add(cb_dept);
	    
	    setSize(490, 400);
	    setVisible(true);
	    
	    list();   //��ü���
	}
	
	public void list(){
	    try{
	     System.out.println("����Ǿ����ϴ�.....");
	     System.out.println(query);       
	     stmt = conn.createStatement();
	     // Select�� ����     
	     ResultSet rs=stmt.executeQuery(query);
	    
	     //JTable �ʱ�ȭ
	     model.setNumRows(0);
	    
	     while(rs.next()){
	      String[] row=new String[4];//�÷��� ������ 4
	      row[0]=rs.getString("id");
	      row[1]=rs.getString("name");
	      row[2]=rs.getString("title");
	      row[3]=rs.getString("rdate");
	      model.addRow(row);
	     }
	     rs.close();
	    
	    }
	    catch(Exception e1){
	     //e.getStackTrace();
	     System.out.println(e1.getMessage());
	    }
	 }
	
}