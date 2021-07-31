/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;


public interface f1 {
    void update(int i, String str);
    void update(String usn); //polymorphism
    
    default boolean isMarksExists(String usn){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Java Project\\College Project\\src\\Main_Package\\Marks.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String stud_usn = line.split(",")[0];
                if(stud_usn.equalsIgnoreCase(usn)){
                    System.out.println("Marks exists");
                    return true;
                }
                else
                    System.out.println("Marks does not exists");
            }
        }
        catch(IOException e)
        {
            System.out.println("Error :"+e);
        }
        return false;
    }
    
    default String[] displayMarks(String usn, String dept, int sem){
        if(isMarksExists(usn)){
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Java Project\\College Project\\src\\Main_Package\\Marks.txt"), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] ele=line.split(",");
                    if(ele[0].equalsIgnoreCase(String.valueOf(usn))){
                        String[] sub = Arrays.copyOfRange(line.split(","), 1, (line.split(",")).length);
                        for(int i=0; i<sub.length; i=i+2)
                            System.out.println(sub[i]+" : "+sub[i+1]);
                        return sub;
                    }
                }
            }
            catch(IOException e)
            {
                System.out.println("Error :"+e);
            }
        }
        else {
            System.out.println("Marks not yet updated");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Java Project\\College Project\\src\\Main_Package\\Subjects.txt"), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line.split(",")[0]);
                    System.out.println(line.split(",")[1]);
                    if(dept.equalsIgnoreCase(line.split(",")[0]) && sem == Integer.parseInt(line.split(",")[1])) {
//                        System.out.println("Hello bug here");
                        String[] subjects = Arrays.copyOfRange(line.split(","),2,(line.split(",")).length);
                        String[] sub = new String[10];
                        for(int i=0; i<5; i++) {
                            int j = i*2;
                            sub[j] = subjects[i];
                            System.out.println("sub[j] = " + sub[j]);
                            sub[j + 1] = "";
                            System.out.println("sub[j+1] = " + sub[j+1]);
                        }
                        return sub;
                    }
                }
            }
            catch(IOException e)
            {
                System.out.println("Error :"+e);
            }   
        }
        return null;
    }

    default String[] showStudDetails(String usn){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter usn of the stud:");
//        String usn = sc.nextLine();
        boolean flag = false;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Java Project\\College Project\\src\\Main_Package\\StudentInfo.txt"), StandardCharsets.UTF_8)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] ele = line.split(",");
                if (ele[0].equalsIgnoreCase(usn)) {
                    System.out.println("USN:"+ele[0]);
                    System.out.println("Name:"+ele[1]);
                    System.out.println("Department:"+ele[2]);
                    System.out.println("Semester:"+ele[3]);
                    System.out.println("Attendance:"+ele[4]);
                    flag=true;
                    return ele;
                }
            }
            if(!flag)
                System.out.println("Student does not exist");
        }
        catch(IOException e)
        {
            System.out.println("Error :"+e);
        }
        return null;
    }

}
