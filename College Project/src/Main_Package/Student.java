/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class Student implements f1 {
    String studName;
    String proctorName;
    String usn;
    String branch;
    int sem;
    int attendance;
    Student_frame frame;
    
    public Student(String usn_i)
    {
//        studName=null;
        proctorName=null;
//        sem=0;
//        attendance=0;
        String[] ele = this.showStudDetails(usn_i);
        usn = usn_i;
        studName = ele[1];
        branch = ele[2];
        sem = Integer.parseInt(ele[3]);
        attendance = Integer.parseInt(ele[4]);
        
        frame = new Student_frame(this);
        frame.setVisible(true);
        
        
    }

    @Override
    public void update(int i, String str){
        int ch=i;

        StringBuilder sb=new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Java Project\\College Project\\src\\Main_Package\\StudentInfo.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] ele=line.split(",");
                if(ele[0].equalsIgnoreCase(String.valueOf(usn))) {
                    switch(ch) {
                        case 1 -> {
                            System.out.println("\ncurrent name ="+ele[1]);
                            ele[1] = str;
                            this.studName = ele[1];
                            System.out.println("changed name ="+ele[1]);
                        }
                            
                        case 2 -> {
                            System.out.println("\ncurrent sem ="+ele[3]);
                            ele[3] = str;
                            this.sem = Integer.parseInt(ele[3]);
                            System.out.println("\nchanged sem ="+ele[3]);
                        }
                    }
                    
                    line =String.join(",", ele);
                }
                sb.append(line);
                sb.append("\n");
            }
            String s=sb.toString();
            byte[] buf =s.getBytes(StandardCharsets.UTF_8);
            OutputStream f=new FileOutputStream("F:\\Java Project\\College Project\\src\\Main_Package\\StudentInfo.txt");
            f.write(buf);
            f.close();
        }
        catch(IOException e)
        {
            System.out.println("Error :"+e);
        }
    }

    @Override
    public void update(String usn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
