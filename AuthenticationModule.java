/**
 * Name: Tolaz Hewa, Alessandro Profenna, Shiqi Dong, Kira Singh
 * Date: November 1, 2016
 * Course: CPS633 (Computer Security)
 * Lab: #2
 * Description: An authentication system using keystroke data
 */

import java.io.*;
import java.util.Scanner;

public class AuthenticationModule {

    /**
     * Method to calculate the deviation for each comparison of the monitored data to the reference data
     * @param n is the sample size
     * @param ref is the reference data
     * @param mon is the monitored data
     * @return the deviation value
     */
    public static double calculateDeviation(int n, int[][] ref, int[][] mon) {
        double digraphSum = 0;
        double monographSum = 0;
        double deviation = 0;
        int size1 = n;
        int size2 = n-1;

        int i;
        for (i = 0; i < size1; i++){
            double digraph = 0;
            if(mon[i][2] != 0)
                digraph = Math.abs(ref[i][2] - mon[i][2]) / (double) mon[i][2];
            else
                size2--;
            digraphSum += digraph;
        }

        for (i = 0; i < (size2); i++){
            double monograph = 0;
            if(mon[i][3] != 0)
                monograph = Math.abs(ref[i][3] - mon[i][3]) / (double) mon[i][3];
            else
                size1--;
            monographSum += monograph;
        }

        deviation = (digraphSum/(double)(size2) + monographSum/(double)size1) * 50;
        return deviation;
    }

    /**
     * Method to calculate the False Reject Rate for the user
     * @param users is the list of all users
     * @param threshold is the False Reject threshold
     * @return the False Reject Rate for the user
     */
    public static double calculateFRR(User[] users, double threshold){

        int FRsum = 0;
        double devTrials[] = new double[5];

        System.out.println("--------------------------------------------------------");
        System.out.println("------- Deviation of False Reject Rate for " + users[0].userName + " -------\n");
        System.out.print(users[0].userName + ":\t\t|");
        int i;
        for(i = 0; i < 5; i++){
            devTrials[i] = calculateDeviation(500, users[0].enrollment, users[0].verification[i]);
            System.out.print("| " + devTrials[i] + " |");
            if(devTrials[i] >= threshold) {
                FRsum++;
            }
        }
        System.out.println("|\n\n");
        return FRsum / (double) devTrials.length;
    }
    /**
     * Method to calculate the False Accept Rate for the user
     * @param users is the list of all users
     * @param threshold is the False Accept threshold
     * @return the False Accept Rate for the user
     */
    public static double calculateFAR(User[] users, double threshold){
        int FAsum = 0;
        double devTrials[] = new double[20];

        System.out.println("--------------------------------------------------------");
        System.out.println("------- Deviation of False Accept Rate for " + users[0].userName + " -------\n");

        int i;
        int j = 1;
        for(i = 0; i < 20; i++){
            System.out.print(users[0].userName + " compared to " + users[j].userName + ":\t\t|");
            int k;
            for(k = 0; k < 5; k++) {
                devTrials[i] = calculateDeviation(500, users[0].enrollment, users[j].verification[k]);
                System.out.print("| " + devTrials[i] + " |");
                if(devTrials[i] <= threshold) {
                    FAsum++;
                }
                if(k < 4)
                    i++;
            }
            j++;
            System.out.println("|");
        }
        System.out.println("\n\n");
        return FAsum / (double) devTrials.length;
    }


    public static void main(String[] args) throws FileNotFoundException {

        User[] users = new User[5];
        double thresholdFA = 0;
        double thresholdFR = 0;

        double FRR;
        double FAR;

        System.out.println("Welcome. Please enter your username:");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        
        if(userName.equals("User1")){
            users[0] = new User("User1.txt");
            users[1] = new User("User2.txt");
            users[2] = new User("User3.txt");
            users[3] = new User("User4.txt");
            users[4] = new User("User5.txt");
            thresholdFR = 70;
            thresholdFA = 83;
        }
        else if(userName.equals("User2")){
            users[0] = new User("User2.txt");
            users[1] = new User("User1.txt");
            users[2] = new User("User3.txt");
            users[3] = new User("User4.txt");
            users[4] = new User("User5.txt");
            thresholdFR = 68;
            thresholdFA = 84.2;

        }
        else if(userName.equals("User3")){
            users[0] = new User("User3.txt");
            users[1] = new User("User1.txt");
            users[2] = new User("User2.txt");
            users[3] = new User("User4.txt");
            users[4] = new User("User5.txt");
            thresholdFR = 76;
            thresholdFA = 67;
        }
        else if(userName.equals("User4")){
            users[0] = new User("User4.txt");
            users[1] = new User("User1.txt");
            users[2] = new User("User2.txt");
            users[3] = new User("User3.txt");
            users[4] = new User("User5.txt");
            thresholdFR = 78;
            thresholdFA = 72;
        }
        else if(userName.equals("User5")){
            users[0] = new User("User5.txt");
            users[1] = new User("User1.txt");
            users[2] = new User("User2.txt");
            users[3] = new User("User3.txt");
            users[4] = new User("User4.txt");
            thresholdFR = 82;
            thresholdFA = 80;
        }
        else{
            System.out.println("Invalid username. Exiting program.");
            System.exit(0);
        }

        FRR = calculateFRR(users, thresholdFR);
        FAR = calculateFAR(users, thresholdFA);
        System.out.println("\nFRR is: " + FRR + "\t\tthreshold: " + thresholdFR);
        System.out.println("FAR is: " + FAR + "\t\tthreshold: " + thresholdFA + "\n");

        if(FRR == FAR) {
            System.out.println("Access Granted!");
        }
        else{
            System.out.println("Access Denied!");
        }

    }
}
