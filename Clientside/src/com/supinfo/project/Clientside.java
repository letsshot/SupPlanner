package com.supinfo.project;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;
public class Clientside {

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to Our SupPlanner!");
		System.out.println("Client Side Starts.");
		System.out.println("Client starts");
		Socket socket=new Socket("127.0.0.1",33011);
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		String number= null;
	  do{
		System.out.println("Press 1 for login;2 for register;3 for quit.");
		number = new BufferedReader(new InputStreamReader(System.in)).readLine(); 
		output.writeUTF(number);
		String receive= null;
		if(number.equals("1")){
			do{
				System.out.println("Welcome to Login System.");
				DataInputStream input = new DataInputStream(socket.getInputStream()); 
				System.out.println("Enter user's name:");
				String name = new BufferedReader(new InputStreamReader(System.in)).readLine();
				System.out.println("Enter user's password");
				String passwd = new BufferedReader(new InputStreamReader(System.in)).readLine();
				String str = name + "," + passwd;
				output.writeUTF(str);
				receive = input.readUTF();
				System.out.println("data from server: " + receive);
			}while(receive.equals("false"));
				if(receive.equals("true")){
					System.out.println("Login success");
					System.out.println("Press ok for dashboard");
					String dash = new BufferedReader(new InputStreamReader(System.in)).readLine(); 
					output.writeUTF(dash);
					System.out.println("Dashboard(project information)");
					System.out.println("id"+"\t"+"name"+"\t"+"completion"+"\t"+"employees"+"\t"+"beginDate"+"\t"+"endDate");				
					//System.out.println("ok");
					DataInputStream input1 = new DataInputStream(socket.getInputStream());
					String idString = input1.readUTF();
					String nameString = input1.readUTF();
					String completionString = input1.readUTF();
					String employeeString = input1.readUTF();
					String beginString = input1.readUTF();
					String endString= input1.readUTF();
					
					String[] idArr = idString.split(",");
					String[] nameArr = nameString.split(",");
					String[] completionArr = completionString.split(",");
					String[] employeeArr = employeeString.split(",");
					String[] beginArr = beginString.split(",");
					String[] endArr = endString.split(",");
					
					for(int m=0;m<idArr.length;m++){
						System.out.println(idArr[m]+"\t"+nameArr[m]+"\t\t"+completionArr[m]+"\t"+employeeArr[m]
										+"\t"+beginArr[m]+"\t"+ endArr[m]);
					}
					
					System.out.println("Press 1 for create a  project");
					System.out.println("Press 2 for modify a  project");
	    			System.out.println("Press 3 for invite an employee");
	    			System.out.println("Press 4 for exit"); 
	    			String num = new BufferedReader(new InputStreamReader(System.in)).readLine(); 
	    			output.writeUTF(num);
	    			//create a project
	    			if(num.equals("1")){
	    				System.out.println("Now. lets create a project");
	    				System.out.println("Enter project's ID:");
	    				String id = new BufferedReader(new InputStreamReader(System.in)).readLine();
	    				System.out.println("Enter project's name:");
	    				String name = new BufferedReader(new InputStreamReader(System.in)).readLine();
	    				System.out.println("Enter project's completion:");
	    				String completion = new BufferedReader(new InputStreamReader(System.in)).readLine();
	    				System.out.println("Enter project's employees:");
	    				String employees = new BufferedReader(new InputStreamReader(System.in)).readLine();
	    				System.out.println("Enter project's begin Date:");
	    				String beginDate = new BufferedReader(new InputStreamReader(System.in)).readLine();
	    				System.out.println("Enter project's end Date:");
	    				String endDate = new BufferedReader(new InputStreamReader(System.in)).readLine();
	    			    String projectRow = id+","+name+","+completion+","+employees+","+beginDate+","+endDate;
	    				output.writeUTF(projectRow);
	    			}else if(num.equals("2")){
	    				System.out.println("Now. lets update a project");
	    				System.out.println("Enter project's ID:");
	    				String id = new BufferedReader(new InputStreamReader(System.in)).readLine();
	    				System.out.println("Update project's name:");
	    				String name = new BufferedReader(new InputStreamReader(System.in)).readLine();
	    				System.out.println("Update project's completion:");
	    				String completion = new BufferedReader(new InputStreamReader(System.in)).readLine();
	    				System.out.println("Update project's employees:");
	    				String employees = new BufferedReader(new InputStreamReader(System.in)).readLine();
	    				System.out.println("Update project's begin Date:");
	    				String beginDate = new BufferedReader(new InputStreamReader(System.in)).readLine();
	    				System.out.println("Update project's end Date:");
	    				String endDate = new BufferedReader(new InputStreamReader(System.in)).readLine();
	    			    String updateProjectRow = id+","+name+","+completion+","+employees+","+beginDate+","+endDate;
	    				output.writeUTF(updateProjectRow);
	    			}
	    			
	    			//DataInputStream input2 = new DataInputStream(socket.getInputStream());
				}
				output.close();
				socket.close();
		}else if(number.equals("2")){	
				//register
				System.out.println("Welcome to Register System.");
				System.out.println("Enter user's ID:");
				String id = new BufferedReader(new InputStreamReader(System.in)).readLine();
				System.out.println("Enter user's name:");
				String name = new BufferedReader(new InputStreamReader(System.in)).readLine();
				System.out.println("Enter user's password");
				String passwd1 = new BufferedReader(new InputStreamReader(System.in)).readLine();
				System.out.println("Confirm user's password again");
				String passwd2 = new BufferedReader(new InputStreamReader(System.in)).readLine();
			    while(passwd1.equals(passwd2)==false)
				    {
			    		System.out.println("Two passwords are not same!Enter again!");
			    		passwd1 = new BufferedReader(new InputStreamReader(System.in)).readLine();
						System.out.println("Confirm user's password again");
						passwd2 = new BufferedReader(new InputStreamReader(System.in)).readLine();
				    }
			    System.out.println("Enter domain name of user's email:(like 'XXXXX@supinfo.com')");
			    String email = new BufferedReader(new InputStreamReader(System.in)).readLine();
				System.out.println("Enter user's role:(Only manager or employee)");
				String role = new BufferedReader(new InputStreamReader(System.in)).readLine();
				//write
				//Writer writer=new OutputStreamWriter(socket.getOutputStream());
				String str1= id+ "," + name + "," + email +","+ role + "," + passwd1;
				output.writeUTF(str1); 
				output.close();
				socket.close();
			}
	 }while(number.equals("3")==false);
			
	}

}
