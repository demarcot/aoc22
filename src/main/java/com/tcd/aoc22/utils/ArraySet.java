package com.tcd.aoc22.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// NOTE(Tom): hashset wasn't comparing properly
public class ArraySet<T> implements Set<T> {
  private List<T> arr = new ArrayList<>();
  
  public List<T> getArr () {
    return this.arr;
  }

  @Override
  public int size() {
    return arr.size();
  }

  @Override
  public boolean isEmpty() {
    return arr.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    for (var e : this.arr) {
      if (e.equals((T)o)) {
        return true;
      }
    }
    
    return false;
  }


  @Override
  public boolean add(Object e) {
    if (this.contains(e)) {
      return false;
    }
    return arr.add((T)e);
  }

  @Override
  public boolean addAll(Collection c) {
    for (var o : c) {
      this.add(o);
    }
    return true;
  }
  
  @Override
  public boolean remove(Object o) {
    return arr.remove((T)o);
  }

  @Override
  public void clear() {
    this.arr.clear();
  }
  
  public T getItem (T o) {
    for (var t : arr) {
      if (t.equals(o)) {
        return t;
      }
    }
    
    System.out.println("Couldn't find object in set");
    return null;
  }
  
  public T pop () {
    return this.arr.remove(this.arr.size() - 1);
  }
  
  // -- Didn't care to implement these
  @Override
  public Iterator iterator() {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public Object[] toArray() {
    // TODO Auto-generated method stub
    return this.arr.toArray();
  }
  
  @Override
  public Object[] toArray(Object[] a) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public boolean containsAll(Collection c) {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public boolean retainAll(Collection c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeAll(Collection c) {
    // TODO Auto-generated method stub
    return false;
  }

}
