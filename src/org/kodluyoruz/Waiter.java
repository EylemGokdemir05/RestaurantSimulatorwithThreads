package org.kodluyoruz;

import java.util.concurrent.CyclicBarrier;

public class Waiter extends Thread{

    private Order order;
    private String waiter;

    public Waiter(Order order,String waiter) {
        this.order = order;
        this.waiter=waiter;
    }

    @Override
    public void run() {
        while (true){
            try {
                if (order.getWantOrder().containsValue(false)){
                    order.takeOrder(waiter);
                }else if (order.getPutOrder().containsValue(false)){
                    order.endOrder(waiter);
                }
                Thread.sleep(1000);
            }catch (InterruptedException exception){
                exception.printStackTrace();
            }
        }
    }
}
