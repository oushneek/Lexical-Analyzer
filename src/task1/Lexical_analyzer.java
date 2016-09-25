/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Tazbeea Tazakka
 */
public class Lexical_analyzer {
    
    WriteFile write=new WriteFile();
    Character c;
    String[] keyword={"int","float","double","char","string","main","return","stdio.h","printf","scanf","while","for","do","include","void","long","short","if","else","break","continue","void","public","static","private","void"};
    List validkeyword=Arrays.asList(keyword);
    String get_word=new String();
    String get_line=new String();

    void split(ArrayList<String> whole_file) {
        for(int i=0;i<whole_file.size();i++){
           get_line=whole_file.get(i);
           StringTokenizer tokenizer=new StringTokenizer(get_line," \t\n(){}[];,!%#<>^&|+-*/=",true);
           while(tokenizer.hasMoreTokens()){
               //System.out.println(tokenizer.nextElement());
               get_word=(String) tokenizer.nextElement();
               //System.out.println(get_word);
               if(!checkDigit(get_word)){
                   //as the string is not digit check if it contains alphabet or not
                   if(checkAlphabet(get_word)){
                       // the string contains alphabets then check if it is in the keyword list
                       if(validkeyword.contains(get_word)){
                           // the string is a keyword
                           write.write_file(get_word+"\tKEYWORD");
                       }
                      
                       else{
                           //the string is not in keyword list .
                           // it is an identifier
                           if(checkLiteral(get_word)){
                                //if the string is inside a double cottation it is a literal
                               write.write_file(get_word+"\tLITERAL");
                           }
                           else{
                               //else it is an identifier
                               write.write_file(get_word+"\tIDENTIFIER");
                           }
                       }                       
                   }
                   
                   //string is not alphabet
                   else if(checkPunctuations(get_word)){
                       write.write_file(get_word+"\tPUNCTUATION");
                   }
                   else if(checkParanthesis(get_word)){
                        write.write_file(get_word+"\tPARANTHESIS");
                   }
                   else if(checkOperators(get_word)){
                       write.write_file(get_word+"\tOPERATOR");
                   }
                   else if(checkSymbols(get_word)){
                       write.write_file(get_word+"\tSYMBOL");
                   }
//                   else if(checkSpace(get_word)){
//                       write.write_file(get_word+"\tSPACE");
//                   }
                   
               }
               else if(checkDigit(get_word)){
                       write.write_file(get_word+"\tNUMBER");
               }
               
               
           }
       }
    }    
    private boolean checkDigit(String get_word) {
        boolean decimalFlag = false;
        for (int i = 0; i < get_word.length(); i++) {
            char ch = get_word.charAt(i);
            if (ch>= '0'  && ch <= '9') {
                return true;
            } 
            else if (ch == '.' && decimalFlag == false) {
                decimalFlag = true;
            } 
            else {
                return false;
            }
        }
        return true;
    }
    private boolean checkAlphabet(String get_word) {

        for (int i = 0; i < get_word.length(); i++) {
            char ch = get_word.charAt(i);
            if ((ch>= 'a'  && ch <= 'z') || (ch>='A' && ch <= 'Z') || (ch>='0'  && ch <= '9') || ch == '_' || ch=='\"') {
            } else {
                return false;
            }
        }
        return true;
    }
    private boolean checkLiteral(String get_word){
        if (get_word.charAt(0)=='\"' && get_word.charAt(get_word.length()-1)=='\"' ){
            //System.out.print(get_word.charAt(0));
            return true;
        }
        else 
            return false;
    }
    private boolean checkPunctuations(String get_word){
        char ch;
        ch=get_word.charAt(0);
        if(ch==',' || ch== ';'){
            return true;
        }
        else 
            return false;
    }
    private boolean checkParanthesis(String get_word){
        char ch;
        ch=get_word.charAt(0);
        if(ch=='(' || ch==')' || ch=='{' || ch=='}' || ch=='[' || ch==']')
            return true;
        else return false;
    }
    private boolean checkOperators(String get_word) {
        char ch;
        ch=get_word.charAt(0);
        switch(ch){
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
            case '%':
            case '<':
            case '>':
            case '=':
                return true;
            default:
                return false;
                //!%#<>^&|=
        }
        
    }
    private boolean checkSymbols(String get_word){
        char ch;
        ch=get_word.charAt(0);
        switch(ch){
             case '&':
             case '|':
             case '!':
             case '#':
             case '@':
             case '$':
             case '?':
                 return true;
             default :
                 return false;
        }
    }
//    private boolean checkSpace(String get_word){
//        char ch;
//        ch=get_word.charAt(0);
//        if(ch==' ' || ch=='\t' || ch=='\n'){
//            return true;
//        }
//        else return false;
//    }
}
