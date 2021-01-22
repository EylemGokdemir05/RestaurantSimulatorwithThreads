package org.kodluyoruz;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Order {

    private HashMap<String,Boolean> wantOrder;
    private HashMap<String,Boolean> getOrder;
    private HashMap<String,Boolean> putOrder;
    private HashMap<String,Boolean> finishOrder;

    public Order() {
        wantOrder=new HashMap<>();
        getOrder=new HashMap<>();
        putOrder=new HashMap<>();
        finishOrder=new HashMap<>();
    }

    public HashMap<String, Boolean> getWantOrder() {
        return wantOrder;
    }

    public HashMap<String, Boolean> getGetOrder() {
        return getOrder;
    }

    public HashMap<String, Boolean> getPutOrder() {
        return putOrder;
    }

    public HashMap<String, Boolean> getFinishOrder() {
        return finishOrder;
    }

    //masanın sipariş vermek istemesi
    public void alertOrder(String order){
        synchronized (this){
            wantOrder.put(order,false);
            System.out.println(order+" ordered.");
        }
    }

    //sipariş alma
    public void takeOrder(String waiter){
        synchronized (this){
            try {
                if (wantOrder!=null){
                    for (Map.Entry<String,Boolean> me: wantOrder.entrySet()){
                        if (me.getValue()== false){
                            getOrder.put(me.getKey(), false);
                            wantOrder.put(me.getKey(), true);
                            System.out.println(waiter+" got order "+me.getKey());
                            break;
                        }
                    }
                }
            }catch (ConcurrentModificationException exception){
                System.out.println("No waiting customers.");
            }
        }
    }

    //siparişin hazırlanması
    public void prepareOrder(String chef){
        synchronized (this){
            try {
                if (getOrder!=null){
                    for (Map.Entry<String,Boolean> me: getOrder.entrySet()){
                        if (me.getValue()==false){
                            putOrder.put(me.getKey(), false);
                            getOrder.put(me.getKey(), true);
                            System.out.println(chef+" prepare order "+me.getKey());
                            break;
                        }
                    }
                }
            }catch (ConcurrentModificationException exception){
                System.out.println("No get order.");
            }
        }
    }

    //siparişin sonlanması
    public void endOrder(String waiter){
        synchronized (this){
            try {
                if (putOrder!=null){
                    for (Map.Entry<String,Boolean> me: putOrder.entrySet()){
                        if (me.getValue()==false){
                            finishOrder.put(me.getKey(), false);
                            putOrder.put(me.getKey(), true);
                            System.out.println(waiter+" enden order "+me.getKey());
                            break;
                        }
                    }
                }
            }catch (ConcurrentModificationException exception){
                System.out.println("End order.");
            }
        }
    }

    //siparişin silinmesi
    public void deleteOrder(String order){
        wantOrder.remove(order);
        getOrder.remove(order);
        putOrder.remove(order);
        finishOrder.remove(order);
        System.out.println(order+ " put order");
    }
}
