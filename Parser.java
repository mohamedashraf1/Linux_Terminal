/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.Scanner;
import java.io.*;
import java.util.Vector;

/**
 *
 * @author Elliot
 */
public class Parser {
    String[] args_P; // Will be filled by arguments extracted by parse method   
    String  cmd = "nothing yet"; // Will be filled by the command extracted by parse method
    static Scanner sc = new Scanner(System.in);
    
    public boolean parse(String input){
        
        
        String []tmp = input.split(" ", 0);
        cmd = tmp[0];
        //handling Long Path
        /*String lastWord = tmp[tmp.length - 1];
        int indxOfLastChar = lastWord.charAt(lastWord.length());//get the index of the last Char in the last word of tmp
        
        //tmp = {"cd" , "\"F:\problem" , "solving\""}
        if ("\"".equals(tmp[tmp.length - 1].charAt(indxOfLastChar))){//check if the last word ends with "
            String load = "";//to concatenate the two args in it
            load = tmp[tmp.length - 2] + tmp[tmp.length - 1];//load = " "F:\problem Solving" "
        }
        */
        int len=tmp.length-1;
        args_P = new String[len];
        System.arraycopy(tmp,1,args_P ,0,len);
        //System.out.println("&" + args_P[0] + "&");
        
        if("cd".equals(cmd)){
           if(args_P.length == 1 || args_P.length == 0) 
               return true;
            else {
               System.out.println("cd requires 1 arguments or no arguments at all");
               return false;}
        }
        
        
        else if("ls".equals(cmd)){
           if(args_P.length == 0) 
               return true;
           else if(args_P.length > 0 && (">".equals(args_P[0])) || ">>".equals(args_P[0]) ) {
               return true;
           }
            else {
               System.out.println("ls requires 0 arguments");
               return false;}
        }
        else if("cp".equals(cmd)){
           if(args_P.length == 2) 
               return true;
            else {
               System.out.println("cp requires 2 arguments");
               return false;}
        }
        else if("cat".equals(cmd)){
           if(args_P.length >= 1) 
               return true;
            else {
               System.out.println("cat requires 1 arguments or more");
               return false;}
        }
        else if("more".equals(cmd)){
           if(args_P.length == 1) 
               return true;
            else {
               System.out.println("more requires 1 arguments");
               return false;}
        }
        else if("mkdir".equals(cmd)){
           if(args_P.length == 1) 
               return true;
            else {
               System.out.println("mkdir requires 1 arguments");
               return false;}
        }
        else if("rmdir".equals(cmd)){
           if(args_P.length == 1) 
               return true;
            else {
               System.out.println("rmdir requires 1 arguments");
               return false;}
        }
        else if("mv".equals(cmd)){
           if(args_P.length == 2)
               return true;
            else {
               System.out.println("mv requires 2 arguments");
               return false;}
        }
        else if("rm".equals(cmd)){
           if(args_P.length == 1)
               return true;
            else {
               System.out.println("rm requires 1 arguments");
               return false;}
        }
        else if("date".equals(cmd)){
           if(args_P.length == 0) 
               return true;
           else if(args_P.length > 0 && (">".equals(args_P[0])) || ">>".equals(args_P[0]) ) {
               return true;
           }
            else {
               System.out.println("date requires 0 arguments");
               return false;}
        }
        else if("pwd".equals(cmd)){
           if(args_P.length == 0) 
               return true;
           else if(args_P.length > 0 && (">".equals(args_P[0])) || ">>".equals(args_P[0]) ) {
               return true;}
           else{
               System.out.println("pwd requires 0 arguments");
               return false;}
        }
        else if("clear".equals(cmd)){
           if(args_P.length == 0) 
               return true;
            else {
               System.out.println("clear requires 0 arguments");
               return false;}
        }
        else if("help".equals(cmd)){
           if(args_P.length == 0) 
               return true;
            else {
               System.out.println("help requires 0 arguments");
               return false;}
        }
        
        
        else{
            System.out.println("wrong command line");
            return false;
        }

    
    }
    public String getCmd(){
        return cmd;
    } 
    public String[] getArguments(){
        
        return args_P;
    } 
    
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Terminal t = new Terminal();
        boolean x =true;
        System.out.println("if you want to stop write \"exit\" ");
        while(x){
            String input = sc.nextLine();
            Parser p = new Parser();
            String cmd;
            if("exit".equals(input)){
                x = false;
            }
            else if(p.parse(input)){
                cmd = p.getCmd();
                if("cp".equals(cmd)){
                    t.cp(p.getArguments()[0], p.getArguments()[1]);
                }
                else if(p.getArguments().length > 0 && ("pwd".equals(cmd) || "ls".equals(cmd) || "cat".equals(cmd) || "date".equals(cmd)) ){
                    if(">".equals(p.getArguments()[0]) && "pwd".equals(cmd)){
                        t.greaterThan(t.pwd(),p.getArguments()[1]);
                    }
                    else if(">>".equals(p.getArguments()[0]) && "pwd".equals(cmd) ){
                        t.multipleGreaterThan(t.pwd(), p.getArguments()[1]);
                    }
                    if(">".equals(p.getArguments()[0]) && "date".equals(cmd)){
                        t.greaterThan(t.date(),p.getArguments()[1]);
                    }
                    else if(">>".equals(p.getArguments()[0]) && "date".equals(cmd) ){
                        t.multipleGreaterThan(t.date(), p.getArguments()[1]);
                    }
                    else if(">".equals(p.getArguments()[0]) && "ls".equals(cmd)){
                        t.greaterThan(t.ls(), p.getArguments()[1]);
                    }
                    else if(">>".equals(p.getArguments()[0]) && "ls".equals(cmd) ){
                        t.multipleGreaterThan(t.ls(), p.getArguments()[1]);
                    }
                    else if(">".equals(p.getArguments()[1]) && "cat".equals(cmd)){
                        String []temp = new String[1];
                        System.arraycopy(p.getArguments(),0,temp ,0,1);//take from index 0 in args -- start from ind 0 in tme -- take 1 element
                        t.greaterThan(t.cat(temp), p.getArguments()[2]);
                    }
                    else if(">>".equals(p.getArguments()[1]) && "cat".equals(cmd)){
                        String []temp = new String[1];
                        System.arraycopy(p.getArguments(),0,temp ,0,1);//take from index 0 in args -- start from ind 0 in tme -- take 1 element
                        t.multipleGreaterThan(t.cat(temp), p.getArguments()[2]);
                    }
                    else if("cat".equals(cmd)){
                        System.out.println(t.cat(p.getArguments()));
                    }
                    
                }
                else if("pwd".equals(cmd)){
                    System.out.println(t.pwd());
                }
                else if("ls".equals(cmd)){
                    System.out.print(t.ls());
                }
                else if("date".equals(cmd)){
                    System.out.println(t.date());
                }
                else if("cd".equals(cmd)){
                    if(p.getArguments().length > 0)
                        t.cd(p.getArguments()[0]);
                    else
                        t.cd();
                }
                else if("more".equals(cmd)){
                    t.more(p.getArguments()[0]);
                }
                else if("mkdir".equals(cmd)){
                    t.mkdir(p.getArguments()[0]);
                }
                else if("rmdir".equals(cmd)){
                    t.rmdir(new File (p.getArguments()[0]));
                }
                else if("mv".equals(cmd)){
                    t.mv(p.getArguments()[0], p.getArguments()[1]);
                }
                else if("rm".equals(cmd)){
                    t.rm(p.getArguments()[0]);
                }
                else if("clear".equals(cmd)){
                    t.clear();
                }
                else if("help".equals(cmd)){
                    t.help();
                }
                
                
            }
           }
        
        }     
    }
