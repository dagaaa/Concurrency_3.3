package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int N=10;
        Stolik stolik= new Stolik(N);
        List<Thread> kobiety = new LinkedList<>();
        List<Thread> mezczyzni = new LinkedList<>();
        for (int i=0 ;i<N; i++ ){
            kobiety.add(new Thread(new Klient(i,i,stolik)));
            mezczyzni.add(new Thread(new Klient(i+N,i,stolik)));
        }

        kobiety.forEach(Thread::start);
        mezczyzni.forEach(Thread::start);

    }
}
