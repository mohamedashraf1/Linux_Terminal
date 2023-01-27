/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
/**
 *
 * @author Elliot
 */
public class Terminal{
    String defaultDir = "D:\\College\\3rd year\\first term\\Operating Systems-1\\assignments\\assignment 1\\Linux_Terminal";
    String wd = defaultDir;
    public void cd(){
        wd = defaultDir;
    }//F:\myfile.txt F:\myfile.txt
    public String CheckPath(String Path){
        char colon = ':';
        boolean found = false;
        //handling Short Path
        for (int i = 0 ; i < Path.length() ; i++){
            if(Path.charAt(i) == colon){
                found = true;
            }
        }
        if(!found)
            Path = wd+ "\\" + Path;
        return Path;
    }
    public void cd(String sourcePath){
       sourcePath = CheckPath(sourcePath);
       wd = sourcePath;
    }
    public String pwd(){
       
        return wd;
    }
    public String ls(){
        File dir = new File(wd);
        File[] files = dir.listFiles();
        String con = "";
      
        if (files.length == 0) {
            System.out.println("Either dir does not exist or is not a directory");
        } else {
            for (int i = 0; i< files.length; i++) {
                File filename = files[i];
                con += filename.toString() + "\n";
            }
        }
        return con;
    }
    
    public void cp(String sourcePath, String destinationPath ) throws FileNotFoundException, IOException{
         
        sourcePath = CheckPath(sourcePath);
        destinationPath = CheckPath(destinationPath);
        
        
        Scanner scanner = new Scanner(Paths.get(sourcePath), StandardCharsets.UTF_8.name());
        String content = scanner.useDelimiter("\\A").next();
        scanner.close();
        
        File newfile = new File(destinationPath);
        newfile.createNewFile();
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(destinationPath));
        writer.write(content);
        writer.close();
        
        
            
    }
    public void more(String sourcePath) throws IOException{
        sourcePath = CheckPath(sourcePath);
        BufferedReader br = new BufferedReader(new FileReader(sourcePath));
        String st="";
        Scanner sc = new Scanner(System.in);
        for(int i = 0 ; i < 10 ; i++){
                if((st = br.readLine()) != null)
                    System.out.println(st);
        }
        for (String line = st; line != null; line = br.readLine()) {
            System.out.println("Press 1 to continue 2 to stop :");
            int input = sc.nextInt();
            if(input == 1){
                for(int i = 0 ; i < 10 ; i++){
                    if((st = br.readLine()) != null)
                        System.out.println(st);
                }
            }
            else if(input == 2)
                break;
        }
    }
    
    
    
    public void mv(String sourcePath, String destinationPath) throws IOException{
        
        sourcePath = CheckPath(sourcePath);
        destinationPath = CheckPath(destinationPath);
        
        cp(sourcePath,destinationPath);
        
        File delfile = new File(sourcePath);
        if(!delfile.delete())
            System.out.println("File is not exist");
    }
    public void rm(String sourcePath){
        sourcePath = CheckPath(sourcePath);
        
        File delfile = new File(sourcePath);
        if(!delfile.delete())
            System.out.println("File is not exist");
    }
    public String date(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        //System.out.println(dtf.format(now));
        return time;
    }
    public void args(String cmd){
        //String []tmp = command.split(" ", 0);
        //String cmd = tmp[1];
        String SP = "SourcePath" , DP = " DestinationPath";
        if("cd".equals(cmd)){
            System.out.println("args1:" + SP);
        }
        else if("ls".equals(cmd)){
           System.out.println("ls has no args");
        }
        else if("cp".equals(cmd)){
          System.out.println("args1:" + SP + "," + "args2:" + DP);
        }
        else if("cat".equals(cmd)){
           System.out.println("cat take as many args as you want");
        }
        else if("more".equals(cmd)){
           System.out.println("args1:" + SP);
        }
        else if("mkdir".equals(cmd)){
           System.out.println("args1:" + SP);
        }
        else if("rmdir".equals(cmd)){
           System.out.println("args1:" + SP);
        }
        else if("mv".equals(cmd)){
           System.out.println("args1:" + SP + "," + "args2:" + DP);
        }
        else if("rm".equals(cmd)){
           System.out.println("args1:" + SP);
        }
        else if("date".equals(cmd)){
           System.out.println("date has no args");
        }
        else if("pwd".equals(cmd)){
           System.out.println("pwd has no args");
        }
        else if("clear".equals(cmd)){
           System.out.println("clear has no args");
        }
        else{
            System.out.println("wrong command line");
        }
        
    }
    public void clear() throws IOException, InterruptedException{
       for(int i = 0 ; i < 20 ; i++){
           System.out.println("");
       }
    }
    public void help(){
        System.out.println("clear : clear the terminal,Takes no Arguments");
        System.out.println("cd : Changes the current Directory to NEW_DIR ,1st Argument:The new Directory Path");
        System.out.println("ls : List each given file or directory name in the folder, Takes no Arguments");
        System.out.println("cp : create a copy for the selected file ,1st Argument: The Source path / 2nd Argument: The Destination Path");
        System.out.println("mv : take the selected file and change its path to another location , 1st Argument: The Source path / 2nd Argument: THe Destination Path");
        System.out.println("rm : removes each specified File or Directory (if it's empty),1st Argument: The File or Directory path to be removed");
        System.out.println("mkdir : Creates a directory with the given name(Folder or file) , 1st Argument: The New Directory Path even if parent doesn't exist");
        System.out.println("rmdir : Removes each given empty directory(Folder or file), 1st Argument: The Path to be removed");
        System.out.println("cat : Concatenate files and print on the standard output, Arguments: The Files to be concatenated(infinite number of arguments)");
        System.out.println("more : display and scroll down the output in one direction only, Takes no Arguments");
        System.out.println("pwd : Display current user directory(location), Takes no Arguments");
        System.out.println("args : Display The arguments of the following cmd ,1st Argument: cmd-The command instruction");         System.out.print("date : Display The Current Date and Time, Takes no Arguments");
        System.out.println("help : Display Information about built in commands, Takes no Arguments");
    }
    public void mkdir(String sourcePath){
        sourcePath = CheckPath(sourcePath);
        
        boolean done =(new File(sourcePath)).mkdir();
        if (done == false){
           System.out.println("can't create directory ");
        }else{
           System.out.println("directory created");
        }
    }
    public boolean rmdir(File dir){
        if (dir.isDirectory()) {
          System.out.println("The directory is deleted.");
      }
      return dir.delete();
    }
    public String cat(String[] paths) throws IOException{
        String con = "";
        for(int i = 0 ; i < paths.length ; i++){
                paths[i] = CheckPath(paths[i]);
            
                Scanner scanner = new Scanner(Paths.get(paths[i]), StandardCharsets.UTF_8.name());
                String content = scanner.useDelimiter("\\A").next();
                con += content;
                scanner.close();
                //System.out.println(content);
            
        }
        return con;
    }
    public void greaterThan(String content,String path) throws FileNotFoundException, IOException{
        path = CheckPath(path);
        
        File newfile = new File(path);
        newfile.createNewFile();
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(content);
        writer.close();
    }
    
    public void multipleGreaterThan(String content , String Path) throws IOException{
        Path = CheckPath(Path);
       
        BufferedWriter out = new BufferedWriter(new FileWriter(Path, true)); 
        out.write(content + "\n");
        out.close();
        
    }
    
    
    
}