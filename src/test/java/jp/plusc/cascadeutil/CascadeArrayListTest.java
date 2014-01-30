package jp.plusc.cascadeutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.plusc.cascadeutil.CascadeArrayList.BoolBlock;
import jp.plusc.cascadeutil.CascadeArrayList.ValBlock;
import jp.plusc.cascadeutil.CascadeArrayList.VoidBlock;
import junit.framework.TestCase;

public class CascadeArrayListTest extends TestCase {

  public void testEach() {
    CascadeArrayList<String> list = new CascadeArrayList<String>(Arrays.asList(
        "foo", "bar", "baz"));
    final List<String> ret = new ArrayList<String>();
    list.each(new VoidBlock<String>() {
      public void call(String t) {
        ret.add(t + "-each");
      }
    });

    assertEquals("foo-each", ret.get(0));
    assertEquals("bar-each", ret.get(1));
    assertEquals("baz-each", ret.get(2));
  }

  public void testMap() {
    CascadeArrayList<String> list = new CascadeArrayList<String>(Arrays.asList(
        "foo", "bar", "baz"));
    List<String> ret = list.map(new ValBlock<String>() {
      public String call(String t) {
        return t + "-each";
      }
    });

    assertEquals("foo-each", ret.get(0));
    assertEquals("bar-each", ret.get(1));
    assertEquals("baz-each", ret.get(2));
  }

  public void testSomeTrue1() {
    CascadeArrayList<String> list = new CascadeArrayList<String>(Arrays.asList(
        "foo", "bar", "baz"));
    boolean ret = list.some(new BoolBlock<String>() {
      public boolean call(String t) {
        return "foo".equals(t);
      }
    });
    
    assertTrue(ret);
  }

  public void testSomeTrue2() {
    CascadeArrayList<String> list = new CascadeArrayList<String>(Arrays.asList(
        "foo", "foo", "foo"));
    boolean ret = list.some(new BoolBlock<String>() {
      public boolean call(String t) {
        return "foo".equals(t);
      }
    });
    
    assertTrue(ret);
  }

  public void testSomeFalse1() {
    CascadeArrayList<String> list = new CascadeArrayList<String>(Arrays.asList(
        "bar", "baz", "boo"));
    boolean ret = list.some(new BoolBlock<String>() {
      public boolean call(String t) {
        return "foo".equals(t);
      }
    });
    
    assertFalse(ret);
  }

  public void testSomeFalse2() {
    CascadeArrayList<String> list = new CascadeArrayList<String>();
    boolean ret = list.some(new BoolBlock<String>() {
      public boolean call(String t) {
        return "foo".equals(t);
      }
    });
    
    assertFalse(ret);
  }
  
  public void testEveryTrue1() {
    CascadeArrayList<Integer> list = new CascadeArrayList<Integer>(Arrays.asList(3, 5, 2));
    boolean ret = list.every(new BoolBlock<Integer>() {
      public boolean call(Integer t) {
        return t < 6;
      }
    });
    
    assertTrue(ret);
  }
  
  public void testEveryTrue2() {
    CascadeArrayList<Integer> list = new CascadeArrayList<Integer>();
    boolean ret = list.every(new BoolBlock<Integer>() {
      public boolean call(Integer t) {
        return t < 6;
      }
    });
    
    assertTrue(ret);
  }    
  
  public void testEveryFalse1() {
    CascadeArrayList<Integer> list = new CascadeArrayList<Integer>(Arrays.asList(3, 5, 8));
    boolean ret = list.every(new BoolBlock<Integer>() {
      public boolean call(Integer t) {
        return t < 6;
      }
    });
    
    assertFalse(ret);
  }  
}
