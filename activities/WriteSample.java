package activities;

import java.util.*;
import java.io.*;

public class WriteSample {
    public static void main(String[] args) throws Exception /*shortcut for try catch*/ {
    
        File oFile=new File("sample.txt"); //assigns the text file File var=new File("text file name")
        FileWriter FW=new FileWriter(oFile); //FileWriter var=new FileWriter(filevar);
        int ages[] = {15, 16, 17, 18, 19, 20, 21};

        for(int i = 0; i < ages.length; i++){
            FW.write(ages[i] + "\n");
        }

        FW.close();
    }
}
