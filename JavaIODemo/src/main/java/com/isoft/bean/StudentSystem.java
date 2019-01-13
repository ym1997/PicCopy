package com.isoft.bean;


import org.junit.jupiter.api.Test;

import java.util.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class StudentSystem {

    List<Map<String,String>>userList;

    FileWriter fw;
    BufferedWriter bw;

    FileReader fr;
    BufferedReader br;

    RandomAccessFile raf;
    Scanner sc;
    String fileName;

    public void login() throws IOException,InterruptedException{
        System.out.println("学生信息管理系统");
        System.out.print("请输入用户名");
        String uname=sc.next();
        System.out.println("请输入密码");
        String upwd=sc.next();
        Map map=new Hashtable();
        map.put(uname,upwd);
        if(userList.contains(map)){
            System.out.print("欢迎");
            System.err.print(uname);
            System.out.println("登陆成功");
            startSystem();
        }
        else
        {
            System.out.println("登陆失败");
        }
    }

    public StudentSystem(){

        userList=new ArrayList<Map<String, String>>();
        Map map1=new Hashtable();
        map1.put("zhangsan","111111");
        userList.add(map1);
        Map map2=new Hashtable();
        map1.put("lisi","111111");
        userList.add(map2);
        Map map3=new Hashtable();
        map1.put("wangwu","111111");
        userList.add(map3);
        fileName="studentInfo.txt";
        try{
            fw= new FileWriter(fileName,true);
            bw=new BufferedWriter(fw);
            sc=new Scanner(System.in);
            raf=new RandomAccessFile(fileName,"r"); }
        catch (IOException e){e.printStackTrace();
        }

    }

    @Test

    public void startSystem()throws IOException,InterruptedException {
        boolean temp = true;
        while (temp) {

            Menu.startMenu();
            System.out.println();
            String op = sc.next();
            switch (op) {
                case "1":
                    addStudentInfo();
                    break;

                case "2":
                    findStudentInfo();
                    break;

                case "3":
                    findAllStudentInfo();
                    break;

                case "4":
                    deleteStudentInfo();
                    break;

                case "5":
                    exitSystem();
                    break;
            }
            System.out.println("是否继续使用本系统?(1:是，0：否)");
            int i = sc.nextInt();

            //          int i= JOptionPane.showConfirmDialog(null,"是否继续输入","提示"JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if (i == 1) {
                temp = false;
            }
        }
        System.out.println("欢迎下次再来！");
        bw.close();
        fw.close();
        raf.close();
    }

    private void exitSystem()
    {
        System.out.println("是否继续使用本系统?(1:是，0：否)");
        int i=sc.nextInt();
        if(i==1)
            System.exit(0);
    }

    private void deleteStudentInfo() throws IOException {
        raf.seek(0);
        System.out.println("请输入要删除的学号;");
        String sid = sc.next();
        String str = null;

        ArrayList<String> stuList = new ArrayList<String>();
        while ((str = raf.readLine()) != null) {
            if (!str.split(",")[0].equalsIgnoreCase(sid)) {
                stuList.add(str);
            }
        }
        fw = new FileWriter(fileName);
        Iterator<String> iterator = stuList.iterator();
        while (iterator.hasNext()) {
            String row = iterator.next();
            bw.write(row);
            bw.write(row);
            bw.newLine();
            bw.flush();
        }
    }

    public void addStudentInfo() throws IOException {
        boolean temp=true;
        while(temp){
            System.out.println("请输入学号：");
            String sid = sc.next();
            System.out.printf("请输入姓名：");
            String name = sc.next();
            System.out.printf("请输入性别：");
            String sex = sc.next();
            System.out.printf("请输入成绩：");
            String score = sc.next();
            bw.write(sid+","+name+","+sex+","+score);
            bw.newLine();
            bw.flush();
            System.out.println("是否继续录入学生记录（1/0）");
            int i=sc.nextInt();
            if(i==0) {
                temp = false;
            }
        }
    }

    public void findStudentInfo() throws IOException,InterruptedException {
        raf.seek(0);
        //  String sid = JOptionPane.showInputDialog("请输入学号");
        System.out.println("请输入要查找的学号");
        String sid=sc.next();
        String str;
        System.out.println("查找学生信息");
        System.out.println("-----------------");
        int temp = 0;
        while ((str=raf.readLine()) != null) {
            String[] strarr = str.split(",");
            if (strarr[0].equalsIgnoreCase(sid)) {
                temp += 1;
                System.out.println(temp + "、学号：" + strarr[0] + "、姓名:" + strarr[1] + "、性别" + strarr[2] + "、年龄" + strarr[3] + "、成绩" + strarr[4]);
            }
            str = br.readLine();
        }
        if (temp == 0)
            System.out.printf("没有找到");
        System.out.printf("-------");

    }

    public void findAllStudentInfo() throws InterruptedException{
        try {
            raf.seek(0);
            String rowStr;
            System.out.println("查找全部学生信息");
            System.out.printf("--------------------------");
            int temp = 0;
            while ((rowStr = raf.readLine()) != null) {
                temp++;
                String[] strarr = rowStr.split(",");
                System.out.printf(temp + "、学号：" + strarr[0] + "、姓名:"
                        + CharSetConvertUtils.getUtf8(strarr[1]) + "、性别:" + CharSetConvertUtils.getUtf8(strarr[2]) + "、年龄" + strarr[3] + "、成绩" + strarr[4]);
                Thread.sleep(1000);
            }
            if (temp == 0) {
                System.out.printf("没有任何信息");
                System.out.printf("-----------------");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}


