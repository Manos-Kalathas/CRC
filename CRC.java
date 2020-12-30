package crc;

import java.util.Scanner;

public class CRC {
    
    public static void main(String[] args) {
        System.out.print("Give the denominator in binary form: ");
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int[] P = new int[s.length()]; //denominator
        int i;
        for (i = 0; i < s.length(); i++) {
            P[i] = Character.digit(s.charAt(i), 10);
        }
        System.out.print("Give the length of the message: ");
        int k = in.nextInt();
        int[] T;
        double BER = 0.001; //Bit error rate
        int n = k + P.length - 1; //length of message with FCS
        
        Receiver r = new Receiver(BER);
        Transmitter t = new Transmitter(n,k,P);
        
        int wrong_messages = 0,found_wrong_messages = 0,not_found_wrong_messages = 0,c;
        int n_of_tries = 1000000;
        
        for (i = 0; i < n_of_tries; i++){
            T = t.CRC(); //create a random message and calculate the FCS
            c = r.check(T,P); //send to the reciever the complete message and the denominator
            if (c == 1)
            {
                wrong_messages++;
                found_wrong_messages++;
            }
            else if (c == 2)
            {
                wrong_messages++;
                not_found_wrong_messages++;
            }
        }
        double p1 = wrong_messages*100.0/n_of_tries;
        double p2 = found_wrong_messages*100.0/wrong_messages;
        double p3 = not_found_wrong_messages*100.0/wrong_messages;
        System.out.println("The percentage of messages altered by noise is " + p1 + "%");
        System.out.println("The percentage of messages altered by noise and found by CRC is " + p2+ "%");
        System.out.println("The percentage of messages altered by noise and not found by CRC is " + p3 + "%");
    }
}