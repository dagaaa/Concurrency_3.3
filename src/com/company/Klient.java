package com.company;

import java.util.Random;

public class Klient implements Runnable {

    private int nrKlienta;
    private int para;
    private Stolik stolik;
    private Random random =new Random();
    public Klient(int nrKlienta,int para, Stolik stolik){
        this.nrKlienta=nrKlienta;
        this.para=para;
        this.stolik=stolik;
    }


    public void wlasne_sprawy(){
        try {
//            System.out.println("Wlasne sprawy. klient: "+nrKlienta);
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void jedzenie(){
        try {
            System.out.println("jestem sobie klient:  "+nrKlienta+" i jem z moja para numer:  "+para);
            Thread.sleep(random.nextInt(2000));
            System.out.println("koniec posilku klient: "+nrKlienta);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        wlasne_sprawy();
        stolik.obsluzPare(para);
        jedzenie();
        stolik.zwalniamStolik();
//        System.out.println("ide sobie");
    }
}
