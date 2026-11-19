package litebans.lib.kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractList extends AbstractCollection implements List, KMappedMarker {
   public static final AbstractListCompanion Companion = new AbstractListCompanion(null);
   private static final int intValue = 2147483639;

   @Override
   public abstract int getIntValue3();

   @Override
   public abstract Object get(int var1);

   @Override
   public Iterator iterator() {
      return new AbstractListIteratorImpl(this);
   }

   @Override
   public int indexOf(Object var1) {
      List var2 = this;
      int var4 = 0;
      Iterator var5 = var2.iterator();

      int var10000;
      while (true) {
         if (!var5.hasNext()) {
            var10000 = -1;
            break;
         }

         Object var6 = var5.next();
         if (Intrinsics.checkObject(var6, var1)) {
            var10000 = var4;
            break;
         }

         var4++;
      }

      return var10000;
   }

   @Override
   public int lastIndexOf(Object var1) {
      List var2 = this;
      ListIterator var4 = var2.listIterator(var2.size());

      int var10000;
      while (true) {
         if (var4.hasPrevious()) {
            Object var5 = var4.previous();
            if (!Intrinsics.checkObject(var5, var1)) {
               continue;
            }

            var10000 = var4.nextIndex();
            break;
         }

         var10000 = -1;
         break;
      }

      return var10000;
   }

   @Override
   public ListIterator listIterator() {
      return new AbstractListListIteratorImpl(this, 0);
   }

   @Override
   public ListIterator listIterator(int var1) {
      return new AbstractListListIteratorImpl(this, var1);
   }

   @Override
   public List subList(int var1, int var2) {
      return new AbstractListSubList(this, var1, var2);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      } else {
         return !(var1 instanceof List) ? false : Companion.checkCollection(this, (Collection)var1);
      }
   }

   @Override
   public int hashCode() {
      return Companion.getInt(this);
   }

   @Override
   public void add(int var1, Object var2) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public boolean addAll(int var1, Collection var2) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public Object set(int var1, Object var2) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public Object remove(int var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
