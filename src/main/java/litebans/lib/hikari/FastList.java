package litebans.lib.hikari;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public final class FastList implements List, RandomAccess, Serializable {
   private static final long longValue = -4598088075242913858L;
   private final Class classValue;
   private Object[] objectArray;
   private int intValue;

   public FastList(Class var1, int var2) {
      this.objectArray = (Object[])Array.newInstance(var1, var2);
      this.classValue = var1;
   }

   @Override
   public boolean add(Object var1) {
      if (this.intValue < this.objectArray.length) {
         this.objectArray[this.intValue++] = var1;
      } else {
         int var2 = this.objectArray.length;
         int var3 = var2 << 1;
         Object[] var4 = (Object[])Array.newInstance(this.classValue, var3);
         System.arraycopy(this.objectArray, 0, var4, 0, var2);
         var4[this.intValue++] = var1;
         this.objectArray = var4;
      }

      return true;
   }

   @Override
   public Object get(int var1) {
      return this.objectArray[var1];
   }

   @Override
   public boolean remove(Object var1) {
      for (int var2 = this.intValue - 1; var2 >= 0; var2--) {
         if (var1 == this.objectArray[var2]) {
            int var3 = this.intValue - var2 - 1;
            if (var3 > 0) {
               System.arraycopy(this.objectArray, var2 + 1, this.objectArray, var2, var3);
            }

            this.objectArray[--this.intValue] = null;
            return true;
         }
      }

      return false;
   }

   @Override
   public void clear() {
      for (int var1 = 0; var1 < this.intValue; var1++) {
         this.objectArray[var1] = null;
      }

      this.intValue = 0;
   }

   @Override
   public int size() {
      return this.intValue;
   }

   @Override
   public boolean isEmpty() {
      return this.intValue == 0;
   }

   @Override
   public Object set(int var1, Object var2) {
      Object var3 = this.objectArray[var1];
      this.objectArray[var1] = var2;
      return var3;
   }

   @Override
   public Object remove(int var1) {
      if (this.intValue == 0) {
         return null;
      } else {
         Object var2 = this.objectArray[var1];
         int var3 = this.intValue - var1 - 1;
         if (var3 > 0) {
            System.arraycopy(this.objectArray, var1 + 1, this.objectArray, var1, var3);
         }

         this.objectArray[--this.intValue] = null;
         return var2;
      }
   }

   @Override
   public boolean contains(Object var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Iterator iterator() {
      return new FastListIterator(this);
   }

   @Override
   public Object[] toArray() {
      throw new UnsupportedOperationException();
   }

   @Override
   public Object[] toArray(Object[] var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean containsAll(Collection var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean addAll(Collection var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean addAll(int var1, Collection var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean removeAll(Collection var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean retainAll(Collection var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void add(int var1, Object var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public int indexOf(Object var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public int lastIndexOf(Object var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ListIterator listIterator() {
      throw new UnsupportedOperationException();
   }

   @Override
   public ListIterator listIterator(int var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public List subList(int var1, int var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Object clone() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void forEach(Consumer var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Spliterator spliterator() {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean removeIf(Predicate var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void replaceAll(UnaryOperator var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void sort(Comparator var1) {
      throw new UnsupportedOperationException();
   }

   public static int getInt(FastList var0) {
      return var0.intValue;
   }

   public static Object[] getObjectArray(FastList var0) {
      return var0.objectArray;
   }
}
