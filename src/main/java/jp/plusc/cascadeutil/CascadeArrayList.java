package jp.plusc.cascadeutil;

import java.util.ArrayList;
import java.util.Collection;

public class CascadeArrayList<T> extends ArrayList<T> {
  public CascadeArrayList(Collection<? extends T> collection) {
    super(collection);
  }
  
  public CascadeArrayList(int capacity) {
    super(capacity);
  }

  public CascadeArrayList() {
    super();
  }


  public CascadeArrayList<T> each(VoidBlock<T> b) {
    for (T t : this) {
      b.call(t);
    }
    
    return this;
  }
  
  public CascadeArrayList<T> map(ValBlock<T> b) {
    CascadeArrayList<T> ret = new CascadeArrayList<T>();
    for (T t : this) {
      ret.add(b.call(t));
    }
    
    return ret;
  }
  
  public boolean some(BoolBlock<T> b) {
    for (T t : this) {
     if (b.call(t)) {
       return true;
     }
    }
    return false;
  }
  
  public boolean every(BoolBlock<T> b) {
    for (T t : this) {
      if (!b.call(t)) {
        return false;
      }
     }
     return true;    
  }

  public static interface VoidBlock<T> {
    void call(T t);
  }
  
  public static interface BoolBlock<T> {
    boolean call(T t);
  }
  
  public static interface ValBlock<T> {
    T call(T t);
  }
}
