package crc;


public class Receiver {
    private final double BER;
    
    public Receiver(double ber){
        BER = ber;
    }
    
    public int check(int[] T,int[] P){
        int l = T.length,i,j=0;
        boolean msgChanged = false;
        double random;
        for (i=0;i<l;i++)
        {
            random = Math.random();
            if (random < BER)
            {
                T[i]=Math.abs(T[i]-1);
                msgChanged = true;
            }
        }
        int limit = T.length - P.length;
        while (j<=limit)
        {
            if (T[j] == 0)
                j++;
            else
            {
                for (i=0;i<P.length;i++)
                    T[j+i] = Math.abs(T[j+i] - P[i]);
                j++;
            }
        }
        boolean errFound = false;
        for (i=0;i<l;i++){
             if (T[i] != 0)
                 errFound = true;
        }
        if (msgChanged) //if the message has been changed by noise
        {
            if (errFound) //if the program detected the mistake
                return 1;
            else
                return 2;
        }
        return 0;
    }
}