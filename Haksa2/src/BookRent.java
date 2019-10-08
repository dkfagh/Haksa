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
		
	    setLayout(null);   //레이아웃설정. 레이아웃 사용 안함.
	   
	    JLabel l_dept=new JLabel("학과");
	    l_dept.setBounds(10, 10, 30, 20);
	    add(l_dept);
	    
	    
	    String colName[]={"학번","이름","도서명","대출일"};
	    model=new DefaultTableModel(colName,0);
	    table = new JTable(model);
	    table.setPreferredScrollableViewportSize(new Dimension(470,200));
	    add(table);
	    JScrollPane sp=new JScrollPane(table);
	    sp.setBounds(10, 40, 460, 250);
	    add(sp);
	   
	   
	    //String[] dept={"전체","컴퓨터공학","멀티미디어","일어일문학"};
	    String[] dept={"전체","컴퓨터시스템","멀티미디어","컴퓨터공학"};

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
				
//				if(si==0) {   //전체
//					query += " order by br.no";
//				} else if(si==1) {   //컴퓨터공학
//					query += " and s.dept = '컴퓨터공학' order by br.no";
//				} else if(si==2) {   //멀티미디어
//					query += " and s.dept = '멀티미디어' order by br.no";
//				} else if(si==3) {   //일어일문학
//					query += " and s.dept = '일어일문학' order by br.no";
//				}
				
				if(si==0) {   //전체
					query += " order by br.no";
				} else if(si==1) {   //컴퓨터시스템
					query += " and s.dept = '컴퓨터시스템' order by br.no";
				} else if(si==2) {   //멀티미디어
					query += " and s.dept = '멀티미디어' order by br.no";
				} else if(si==3) {   //컴퓨터공학
					query += " and s.dept = '컴퓨터공학' order by br.no";
				}
				
				//검색목록
				list();
				
			}});
	    cb_dept.setBounds(45, 10, 100, 20);
	    add(cb_dept);
	    
	    setSize(490, 400);
	    setVisible(true);
	    
	    list();   //전체목록
	}
	
	public void list(){
	    try{
	     System.out.println("연결되었습니다.....");
	     System.out.println(query);       
	     stmt = conn.createStatement();
	     // Select문 실행     
	     ResultSet rs=stmt.executeQuery(query);
	    
	     //JTable 초기화
	     model.setNumRows(0);
	    
	     while(rs.next()){
	      String[] row=new String[4];//컬럼의 갯수가 4
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