package org.kodluyoruz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Order order=new Order();

        ExecutorService executorService= Executors.newFixedThreadPool(5);

        Thread chefTh=new Chef(order,"Chef");
        Thread chefTh2=new Chef(order,"Chef2");

        Thread waiterTh=new Waiter(order,"Waiter");
        Thread waiterTh2=new Waiter(order,"Waiter2");
        Thread waiterTh3=new Waiter(order,"Waiter3");

        chefTh.start();
        chefTh2.start();

        waiterTh.start();
        waiterTh2.start();
        waiterTh3.start();

        for (int i=0; i<20; i++){
            executorService.submit(new Customer(order,"Customer "+i));
        }

        try {
            chefTh.join();
            chefTh2.join();
            waiterTh.join();
            waiterTh2.join();
            waiterTh3.join();

            executorService.awaitTermination(3, TimeUnit.SECONDS);
            executorService.shutdown();
        } catch (InterruptedException exception) {
            System.out.println("Thread is interrupted");
        }

    }
}
