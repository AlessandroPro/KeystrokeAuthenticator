# Keystroke Authenticator
Java program that authenticates a user based on inputted keystroke data. Lab 2 for CPS 633.

Background for this lab:

Implementing an effective online access control scheme in practice faces many challenges. Systems with highly sensitive data often require a multi-factor authentication. But, using tokens or biometric veri- fication techniques may require expensive and limiting devices and possible errors may occur both in the collection and transmission phases. In this lab, you will design an online keystroke-based biometric access model that can be easily setup and used in practice with a high degree of accuracy.

Suppose you are working for one of the leading Credit Reporting agencies that manages the users’ credit rating information with different financial institutions. The company wishes to implement an Online Keystroke-based Access Control Model (OKAM) that will be used by 5 users: 4 of its Sales and Technical staffs together with their manager, while on the road. 


Task for this lab:


#Authentication Module  
You are provided with sample keystrokes for the 5 users mentioned above. Following the way the input looks like, you will need to extend this file to about 3000 sample keystrokes for the said 5 users. All the sample files are in a .txt format. The keystroke features captured for each user are the time interval between when a key is pressed and released (referred to as Dwell time), the time interval between releasing a key and pressing the next key (referred to as Fly time), and the latencies incurred when one or two successive keys are pressed. The input in each row of the user data is the collection of values for the following five measurements:


The threshold level % would be given as an input during the calculation of D as also described in the following section. Treat each value provided as a base 10 number.


#Algorithm:  
Divide the user data into 6 equal sets. Use the first set for the enrolment phase of your system, and the rest for the verification phase. Use the following given formula to calculate the deviation D between the target time and the monitored time for the digraphs and monographs in the data, where || denotes the absolute value, tref is the targeted or reference time and tmon is the monitored time for dwell or fly time values, Di is the set of digraphs in the sample, Mo is the set of monographs in the sample, and n is the size of the sample.


To calculate the False Reject (FR) (also known as False Non-Match (FNM)) for a given user, compare each of the five verification session data of the same user against the enrolment set data by calculating the deviation for the monographs and digraphs; [in this case n = 500 for the monographs and n − 1 = 499 for the digraphs]. The value for the iterations for Di is 500 and that for Mo is 499. If D >= Thr,FR = 1 else FR = 0, where D is the deviation calculated and T hr is the value of the threshold%.


To calculate the False Accept (FA) (also known as False Match (FM) for the same user (as above), compare each of the verification session data of the 4 remaining users against the enrolment set data of the same user by calculating the deviation for the monographs and digraphs; [in this case n = 500 for the monographs and n−1 = 499 for the digraphs]. The value for the iterations for Di is 500 and that of Mo is 499. If D <= Thr,FA = 1 else FA = 0, where D is the deviation calculated and T hr is the value of the threshold %.
Using the values of FA and FR for this user, you can calculate the False Acceptance Rate (FAR) and the False Rejection Rate (FRR) as follows:


FAR = Sum of FA/ Total number of verification trials  
FRR = Sum of FR / Total number of verification trials 


For this set up, we are interested in achieving an Equal Error Rate (EER), i.e. when FAR = FRR.  
The user input to the model also includes the name of the file, and the type of access requested. If the user is authenticated using the authentication module in this section, its user id together with the name of the file and the access requested should be passed on to the authorization module described in the next section to process the requested access. Otherwise, the program should output the message Access Failed!.

