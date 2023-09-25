package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author PHAT
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String s1 = "I0000001";
        String s2 = "I0000002";
        
        int n = s1.compareTo(s2);
        
        if (n > 0) 
            System.out.println(s1);
        
        if (n < 0)
            System.out.println(s2);
    }
}