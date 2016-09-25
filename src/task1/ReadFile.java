/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tazbeea Tazakka
 */
public class ReadFile {
    public void ReadFile(){
        String allfile = new String();
        String temp1,temp2;
        Lexical_analyzer la=new Lexical_analyzer();
        boolean commentStart=false,commentEnd=false;
        ArrayList<String> list=new ArrayList<>();
        try{
           BufferedReader br = new BufferedReader(new FileReader("Task1File.txt"));
           while((allfile=br.readLine())!=null){
               //System.out.println(allfile);
             for(int i=0;i<allfile.length();i++){
                 if(commentStart==true){
                     while(allfile.charAt(i)!='*'){
                        i++;
                        if(i==allfile.length()-1){
                            break;
                        }
                        
                     }
                     if(i<allfile.length()-1){
                         if(allfile.charAt(i)!='/'){
                            temp2=allfile.substring(i+1, allfile.length()-1);
                            commentStart=false;
                            allfile=temp2;
                          }
                     }
                     
                 }
                 else if(allfile.charAt(i)=='/'){
                     i+=1;
                     if(allfile.charAt(i)=='/'){
                         allfile=allfile.substring(0,allfile.lastIndexOf('/')-1);
                        // System.out.print(allfile);
                         break;
                     }
                     else if(allfile.charAt(i)=='*'){
                         temp1=allfile.substring(0, i-1);
                         
                         commentStart=true;
                         i+=1;
                         while(allfile.charAt(i)!='*'){
                             i++;
                             if(i==allfile.length()-1)
                                 break;
                         }
                         if(i<allfile.length()-1){
                             i++;
                             if(allfile.charAt(i)!='/'){
                                temp2=allfile.substring(i, allfile.length()-1);
                                commentStart=false;
                                allfile=temp1+temp2;
                             }
                         }
                         allfile=temp1;
                     }
                     
                 }
                 //else list.add(allfile);
             }
             list.add(allfile);
             
           }
           la.split(list);
        }
        catch(IOException Ex){
            System.out.println("Exception");
        }
    }
}
