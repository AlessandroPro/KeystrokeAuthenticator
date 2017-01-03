/**
 * Name: Tolaz Hewa, Alessandro Profenna, Shiqi Dong, Kira Singh
 * Date: November 1, 2016
 * Course: CPS633 (Computer Security)
 * Lab: #2
 * Description: A class that represents a user's keystroke data
 */

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class User {

    File file;
    public String userName;
    public int[][] enrollment;
    public int[][][] verification;

    public User(String userSample) throws FileNotFoundException {
        file = new File(userSample);
        enrollment = readBlock(1, 500);
        verification = new int[5][][];
        verification[0] = readBlock(501, 1000);
        verification[1] = readBlock(1001, 1500);
        verification[2] = readBlock(1501, 2000);
        verification[3] = readBlock(2001, 2500);
        verification[4] = readBlock(2501, 3000);
        userName = userSample.substring(0,5);
    }

    /**
     * A method to read a segment of recorded keystroke data from a file
     * @param start the line to start reading the file
     * @param end the line to stop read the file
     * @return an array with the recorded keystroke data for the specified rows of the file
     * @throws FileNotFoundException
     */
    public int[][] readBlock(int start, int end) throws FileNotFoundException {

        int [][] block = new int[500][5];
        Scanner input = new Scanner(file);
        int i = 0;
        int lineNum = 0;
        while(input.hasNext() && i < 500) {
            String answer = input.nextLine();
            if(lineNum >= start) {
                StringTokenizer tk = new StringTokenizer(answer, "\t");
                int k = 0;
                while (tk.hasMoreTokens() && k < 5) {
                    String token = tk.nextToken();
                    block[i][k] = Integer.parseInt(token);
                    k++;
                }
                i++;
            }
            else if(lineNum > end) break;
            lineNum++;
        }
        return block;
    }


}
