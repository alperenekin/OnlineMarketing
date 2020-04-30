package OnlineMarketing;

import java.util.HashMap;

public abstract class Inventory<T> implements IInventory<T>{
    protected HashMap<T,Integer> inventory;

}
