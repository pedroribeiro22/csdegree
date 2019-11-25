package ex2;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private Map<String, Item> stock;

    public Warehouse() {
       this.stock = new HashMap<>();
    }

    public void supply(String item, int quantity) {
        if(this.stock.containsKey(item)) {
            this.lock(item);
            this.stock.get(item).supply(quantity);
            this.unlock(item);
        }
    }

    // podiamos ter locks aninhados
    public void consume(String[] items){
        for(int i = 0; i < items.length; i++) {
           if(this.stock.containsKey(items[i])) {
               this.lock(items[i]);
               try {
                   this.stock.get(items[i]).consume();
               } catch (Exception e) {
               } finally {
                   this.unlock(items[i]);
               }
           }
        }
   }


    public void lock(String item)  {
        boolean isThere = stock.containsKey(item);
        if(isThere) {
           this.stock.get(item).lock();
        }
    }

    public void unlock(String item) {
        boolean isThere = stock.containsKey(item);
        if(isThere) {
            this.stock.get(item).unlock();
        }
    }
}
