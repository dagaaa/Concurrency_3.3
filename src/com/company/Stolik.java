package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Stolik {

    private boolean czyWolnyStolik;
    private Lock lock = new ReentrantLock();

    private Condition[] czekaNaPare;
    private Condition czekaNaStolik = lock.newCondition();
    private Boolean[] czyCzekaNaPare;
    private int ilePrzyStoliku;

    public Stolik(int N) {
        czekaNaPare = new Condition[N];
        czyCzekaNaPare = new Boolean[N];
        for (int i = 0; i < N; i++) {
            czekaNaPare[i] = lock.newCondition();
            czyCzekaNaPare[i] = false;
        }
        czyWolnyStolik = true;
        ilePrzyStoliku = 0;
    }


    public void obsluzPare(int j) {
        lock.lock();
        if (czyCzekaNaPare[j]) {
            while (!czyWolnyStolik) {
                try {
                    czekaNaStolik.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ilePrzyStoliku++;
            czekaNaPare[j].signal();
            czyCzekaNaPare[j] = false;
            ilePrzyStoliku++;
            czyWolnyStolik = false;

            lock.unlock();
        } else {
            czyCzekaNaPare[j] = true;
            try {
                czekaNaPare[j].await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }

    public void zwalniamStolik() {
        lock.lock();

        if (ilePrzyStoliku > 1) {
            ilePrzyStoliku--;

        } else {
            ilePrzyStoliku--;
            czyWolnyStolik = true;
            czekaNaStolik.signal();

        }

        lock.unlock();


    }
}
