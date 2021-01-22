package org.kodluyoruz;

public class Chef extends Thread{

    private Order order;
    private String chef;

    public Chef(Order order, String chef) {
        this.order=order;
        this.chef=chef;
    }

    @Override
    public void run() {
        while (true){
            try {
                if (order.getGetOrder().containsValue(false)){
                    order.prepareOrder(chef);
                }
                Thread.sleep(1000);
            }catch (InterruptedException exception){
                exception.printStackTrace();
            }
        }
    }
}
