/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Proctor implements f1
{
    String ProctorName;
    String proctorID;

    String usn;
    String studName;
    int sem;
    String dept;
    int attendance;
    Map<String, Integer> marks;
    Proctor_frame frame;

    public Proctor(String pid, String p_name)
    {
        ProctorName = p_name;
        proctorID = pid;
        usn = null;
        studName=null;
        sem=0;
        attendance=0;
        dept=null;
        marks= new HashMap<>();
        
        frame = new Proctor_frame(this);
        frame.setVisible(true);
    }
    //Method to check whether USN is valid or not
    public void validate(String usn) throws InvalidUSNException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Java Project\\College Project\\src\\Main_Package\\StudentInfo.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null){
                String[] ele=line.split(",");
                boolean flag = ele[0].equalsIgnoreCase(String.valueOf(usn));
                if(flag){
                    throw new InvalidUSNException("USN already exists!");
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error in opening file");
        }
    }

    //function to add a student under a respective proctor
    public void addStud()
    {
        try{
            File f1=new File("F:\\Java Project\\College Project\\src\\Main_Package\\StudentInfo.txt");
            if(f1.exists())
                validate(this.usn);
        }
        catch(Exception m) {
            System.out.println("Error :" + m);
        }

        File file;
        try{
            file = new File("F:\\Java Project\\College Project\\src\\Main_Package\\StudentInfo.txt");
            FileWriter fw = new FileWriter(file, true);
            PrintWriter pw =new PrintWriter(fw);
            pw.write(this.usn+","+this.studName+","+this.dept+","+this.sem+","+this.attendance+","+this.proctorID +"\n");
            pw.close();
        }
        catch (IOException e)
        {
            System.out.println("Error in opening file");
        }
    }

    //function to delete a student under the respective proctor
    public void delStud() {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Java Project\\College Project\\src\\Main_Package\\StudentInfo.txt"), StandardCharsets.UTF_8))) {
            boolean flag = false;
            String line;
            while ((line = br.readLine()) != null) {
                String[] ele=line.split(" ");
                if(ele[0].equalsIgnoreCase(String.valueOf(this.usn)) && ele[5].equalsIgnoreCase(String.valueOf(this.proctorID))){
                    flag =true;
                    continue;
                }
                else if(ele[0].equalsIgnoreCase(String.valueOf(this.usn)) && !(ele[5].equalsIgnoreCase(String.valueOf(this.proctorID)))) {
                    flag =true;
                    System.out.println("Cannot Delete This Student\nSince this student does not belong to the proctor");
                }
                sb.append(line);
                sb.append("\n");
            }
            if(flag)
                System.out.println("Deleted the student with USN : " + this.usn);
            if(!flag)
                System.out.println("Cannot Delete since the student does not exist");
            String s = sb.toString();
            byte[] buf = s.getBytes(StandardCharsets.UTF_8);
            OutputStream f = new FileOutputStream("F:\\Java Project\\College Project\\src\\Main_Package\\StudentInfo.txt");
            f.write(buf);
            f.close();
        } catch (IOException e) {
            System.out.println("Error :" + e);
        }
    }

    //function to update a student under a respective proctor
    @Override
     public void update(String usn){

        StringBuilder sb=new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Java Project\\College Project\\src\\Main_Package\\StudentInfo.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] ele=line.split(",");
                if(ele[0].equalsIgnoreCase(String.valueOf(usn)) && ele[5].equalsIgnoreCase(String.valueOf(this.proctorID))) {
                    ele[1] = this.studName;
                    ele[2] = this.dept;
                    ele[3] = String.valueOf(this.sem);
                    ele[4] = String.valueOf(this.attendance);
                    line =String.join(",", ele);
                }
                else if(ele[0].equalsIgnoreCase(String.valueOf(usn)) && !(ele[5].equalsIgnoreCase(String.valueOf(proctorID)))) {
                    System.out.println("cannot Update - The student does not belong to the proctor");
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


    //function to display all the student under the proctor
    public String[] listStudents()
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Java Project\\College Project\\src\\Main_Package\\StudentInfo.txt"), StandardCharsets.UTF_8)))
        {
            String line;
            String[] stud_usn = new String[1000];
            System.out.println("USN\tName");
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] ele=line.split(",");
                if (ele[5].equalsIgnoreCase(String.valueOf(proctorID))) {
                    System.out.println(ele[0] + "\t" + ele[1]);
                    stud_usn[i] = ele[0];
                    System.out.println(stud_usn[i]);
                    i++;
                }
            }
            return stud_usn;
        }
        catch(IOException e)
        {
            System.out.println("Error :"+e);
        }
        return null;
    }

    public List<String> getDetails()
    {
        List<String> details=new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Java Project\\College Project\\src\\Main_Package\\StudentInfo.txt"), StandardCharsets.UTF_8))) {
            boolean flag = false;
            String line;
            while ((line = br.readLine()) != null) {
                String[] ele = line.split(",");
                if (ele[0].equalsIgnoreCase(String.valueOf(this.usn))) {
                    this.studName = ele[1];
                    this.dept = ele[2];
                    this.sem = Integer.parseInt(ele[3]);
                    this.attendance = Integer.parseInt(ele[4]);
                    Collections.addAll(details,ele);
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Error :"+e);
        }
        return details;
    }

    public void updateMarks(int[] stud_marks)
    {
        List<String> details=getDetails();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Java Project\\College Project\\src\\Main_Package\\Subjects.txt"), StandardCharsets.UTF_8))) {
            String line;
            boolean flag=false;
            while ((line = br.readLine()) != null) {
                if(line.startsWith(details.get(2)+","+details.get(3)) && (details.get(5)).equalsIgnoreCase(String.valueOf(this.proctorID))){
                    String[] subjects = Arrays.copyOfRange(line.split(","),2,(line.split(",")).length);
                    for (int i=0; i<subjects.length; i++)
                    {
                        int m=stud_marks[i];
                        this.marks.put(subjects[i],m);
                    }
                    flag=true;
                    break;
                }
            }
            if(!flag) {
                System.out.println("cannot Add Marks - The student does not belong to the proctor");
            }
        }
        catch(IOException e)
        {
            System.out.println("Error :"+e);
        }
        
        StringBuilder sb=new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Java Project\\College Project\\src\\Main_Package\\Marks.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] ele=line.split(",");
                if(ele[0].equalsIgnoreCase(String.valueOf(usn))) {
                    int i=1;
                    for(Map.Entry<String, Integer> m : marks.entrySet()) {
                        ele[i] = m.getKey() + "";
                        ele[i+1] = m.getValue() + "";
                        i+=2;
                    }
                    line =String.join(",", ele);
                }
                sb.append(line);
                sb.append("\n");
            }
            
            String s=sb.toString();
            byte[] buf =s.getBytes(StandardCharsets.UTF_8);
            OutputStream f=new FileOutputStream("F:\\Java Project\\College Project\\src\\Main_Package\\Marks.txt");
            f.write(buf);
            f.close();
        }
        catch(IOException e)
        {
            System.out.println("Error :"+e);
        }
    }

    @Override
    public void update(int i, String str) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
