package College_Project;

import Main_Package.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class User {
    public static String username;
    public static String name;
    public static String password;
    public static boolean isStudent;
    
    public static void user_details(login lin, String uname, String name) {
        System.out.println("Username: " + uname + "\tName: " + name);
        if(isStudent) {
            System.out.println("Student " + name + " has logged in");
            Student s1 = new Student(uname);
            lin.dispose();
        }
        else {
            System.out.println("Teacher " + name + " has logged in");
            Proctor p1 = new Proctor(uname, name);
            lin.dispose();
        }
            
    }
    
    public static void enter_user_details(signup sigup) throws FileNotFoundException, IOException {
        OutputStream file;
        if(User.isStudent)
            file =new FileOutputStream("F:\\Java Project\\College Project\\src\\College_Project\\stud_user.txt", true);
        else
            file =new FileOutputStream("F:\\Java Project\\College Project\\src\\College_Project\\teach_user.txt", true);
        
        String details = "\n" + username + "\n" + password + "\n" + name;
        byte[] detailsBytes = details.getBytes();
        try{
            file.write(detailsBytes); 
            file.write('\n');
            System.out.println("Succesfully entered the data in " + file);
        }
        finally { 
            if(file!=null){
                file.close();
                if(isStudent) {
                    System.out.println("Student " + name + " has logged in");
                    Student s1 = new Student(username);
                    sigup.dispose();
                }
                else {
                    System.out.println("Teacher " + name + " has logged in");
                    Proctor p1 = new Proctor(username, name);
                    sigup.dispose();
                }
            }
                
        }
        
        
    }
    
    public static void main(String[] args) {
        
        login m = new login();
        m.setVisible(true);
        System.out.println(Thread.currentThread().getName());
        
    }
}
