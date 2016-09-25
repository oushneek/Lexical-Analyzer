/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package task1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Tazbeea Tazakka
 */
public class WriteFile {
    
    public void write_file(String result){
         try {
                        File file = new File("Symbol_Table.txt");
 
			// if file doesnt exists, then create it	
			file.createNewFile();
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(result);
                        bw.newLine();
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
