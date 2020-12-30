package crc;

import java.util.Random;

public class Transmitter {
    private final int[] T;
    private final int[] P;
    private final int n;
    private final int k;
    
    public Transmitter(int x,int y,int[] denominator){
        n = x;
        k = y;
        T = new int[n];
        P = denominator;
    }
    
    public int[] CRC(){
        Random randomGenerator = new Random();
        int i;
        T[0] = 1;
        for (i=0;i<n;i++)
        {
            if (i<k)
                T[i] = randomGenerator.nextInt(2);
            else
                T[i] = 0;
        }
        int[] M = T.clone();
        calculateFCS();
        for (i = k; i < n; i++)
            M[i] = M[i] + T[i];
        return M;
    }
    
    private void calculateFCS(){
        int j=0,i;
        int l = T.length - P.length;
        while (j<=l)
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
    }
}