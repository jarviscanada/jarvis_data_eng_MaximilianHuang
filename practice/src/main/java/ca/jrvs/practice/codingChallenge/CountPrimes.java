package ca.jrvs.practice.codingChallenge;

/*
 * Ticket: https://www.notion.so/jarvisdev/Count-Primes-7c0739657e6a4bf0b827abbc72a97adc
 */

public class CountPrimes {

  public int countPrimes(int n) {
    boolean[] notPrime = new boolean[n];
    int res = 0;
    
    for (int i=2; i<n; i++) {
      if (!notPrime[i]) {
        res++;
        for (int j=2; i*j<n; j++) {
          notPrime[i*j] = true;
        }
      }
    }
    
    return res;
  }

}
