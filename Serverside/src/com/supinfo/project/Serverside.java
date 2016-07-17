package com.supinfo.project;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Serverside {

	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		
			Class.forName(com.mysql.jdbc.Driver.class.getName());		
			//PlanningManager is my database name
			String url="jdbc:mysql://localhost/supplannersystem";
			String user="root";
			//the password when we install the MySql
			String password="supinfo";
		
			//Database connection information
			Connection conn = DriverManager.getConnection(url, user, password);
			//receive client information
			ServerSocket server=new ServerSocket(33011);
			Socket socket=server.accept();
			DataInputStream input1 = new DataInputStream(socket.getInputStream());
			String clientInputStr1 = input1.readUTF();
			if(clientInputStr1.equals("2")){
				 DataInputStream input2 = new DataInputStream(socket.getInputStream());
				 String clientInputStr2 = input2.readUTF();
				 String[] array = clientInputStr2.split(",");
				   PreparedStatement preStatement = conn.prepareStatement("insert into user values(?,?,?,?,?)");
				   preStatement.setString(1, array[0]);
				   preStatement.setString(2, array[1]);
				   preStatement.setString(3, array[2]);
				   preStatement.setString(4, array[3]);
				   preStatement.setString(5, array[4]);
				   preStatement.executeUpdate();
				   preStatement.close();
				   System.out.println("Successfully insert client information!"); 
			}else if(clientInputStr1.equals("1")){
				DataOutputStream out = new DataOutputStream(socket.getOutputStream()); 
				String check = "false";
				do{	
					String name[] = new String[10];
					String passwd[] = new String[10];
					DataInputStream input3 = new DataInputStream(socket.getInputStream());
					String clientInputStr3 = input3.readUTF();
					String[] arr = clientInputStr3.split(",");
					int i=0;
					Statement stmt = conn.createStatement();
					ResultSet re = stmt.executeQuery("SELECT * FROM user");
					while(re.next()){
						name[i] = re.getString("name");
						passwd[i] = re.getString("password");
						i++;
					}
					
					for(int j=0;j<=i;j++){
						if(arr[0].equals(name[j])){
							if(arr[1].equals(passwd[j]))
							{
								check = "true";
								break;
							}
						}
					}
					out.writeUTF(check); 
			   }while(check.equals("false"));
						//dashboard
						DataInputStream input4 = new DataInputStream(socket.getInputStream());
						String clientInputStr4 = input4.readUTF();
						System.out.println(clientInputStr4+"! dashboard starts");
						Statement state = conn.createStatement();
						ResultSet result = state.executeQuery("SELECT * FROM project");
						int m=0;
						String iArray[] = new String[20]; 
						String nArray[] = new String[20]; 
						String cArray[] = new String[20]; 
						String eArray[] = new String[20]; 
						String bArray[] = new String[20]; 
						String dArray[] = new String[20]; 
						while(result.next()){
							iArray[m] = result.getString("id");
							nArray[m] = result.getString("name");
							cArray[m] = result.getString("completion");
							eArray[m] = result.getString("employees");
							bArray[m] = result.getString("beginDate");
							dArray[m] = result.getString("endDate");
							m++;
						}
						String idArray = iArray[0];
						String nameArray = nArray[0];
						String completionArray = cArray[0];
						String employeeArray = eArray[0];
						String beginArray = bArray[0];
						String endArray = dArray[0];
						for(int n=1;n<m;n++){
							idArray += "," +iArray[n];
							nameArray += "," +nArray[n];
							completionArray += "," +cArray[n];
							employeeArray += "," +eArray[n];
							beginArray += "," +bArray[n];
							endArray += "," +dArray[n];
						}
						
						out.writeUTF(idArray);
					    out.writeUTF(nameArray);
					    out.writeUTF(completionArray);
						out.writeUTF(employeeArray);
						out.writeUTF(beginArray);
						out.writeUTF(endArray); 
						//project function
						DataInputStream projectChoose = new DataInputStream(socket.getInputStream());
						String projectChoice = projectChoose.readUTF();
						System.out.println(projectChoice+"zhuzhu");
						if(projectChoice.equals("1")){
							 System.out.println("Now, you can insert a new project.Press ok.");
							 //out.writeUTF(projectChoice);
							 DataInputStream inputProject = new DataInputStream(socket.getInputStream());
							 String inputProjectStr = inputProject.readUTF();
							 System.out.println(inputProjectStr);
							 String[] arry = inputProjectStr.split(",");
							   PreparedStatement preStatement = conn.prepareStatement("insert into project values(?,?,?,?,?,?)");
							   preStatement.setString(1, arry[0]);
							   preStatement.setString(2, arry[1]);
							   preStatement.setString(3, arry[2]);
							   preStatement.setString(4, arry[3]);
							   preStatement.setString(5, arry[4]);
							   preStatement.setString(6, arry[5]);
							   preStatement.executeUpdate();
							   preStatement.close();
							   System.out.println("Successfully insert a new project!"); 
						}else if(projectChoice.equals("2")){
							
						}
			
			}
			socket.close();
			server.close();

	}

}
