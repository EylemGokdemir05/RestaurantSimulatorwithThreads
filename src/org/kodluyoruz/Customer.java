package org.kodluyoruz;

public class Customer extends Thread{

    private Order order;
    private String table;

    public Customer(Order order, String table) {
        this.order = order;
        this.table = table;
    }

    @Override
    public void run() {
        int count=0;
        order.alertOrder(table);
        while (order.getWantOrder().containsKey(table)){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException exception){
                exception.printStackTrace();
            }
            if (count==20){
                order.deleteOrder(table);
                break;
            }else if (order.getFinishOrder().containsKey(table)){
                boolean choice=false;
                if (choice==true){
                    order.deleteOrder(table);
                    order.alertOrder(table);
                }else {
                    order.deleteOrder(table);
                    break;
                }
            }
        }
    }
}
