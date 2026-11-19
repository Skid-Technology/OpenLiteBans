package litebans.lib.kotlin.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class EmptyMap implements Map, Serializable, KMappedMarker {
   public static final EmptyMap INSTANCE = new EmptyMap();
   private static final long longValue = 8246714829545688274L;

   private EmptyMap() {
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return var1 instanceof Map && ((Map)var1).isEmpty();
   }

   @Override
   public int hashCode() {
      return 0;
   }

   @Override
   public String toString() {
      return "{}";
   }

   public int getInt() {
      return 0;
   }

   @Override
   public boolean isEmpty() {
      return true;
   }

   @Override
   public boolean containsKey(@Nullable Object var1) {
      return false;
   }

   public boolean checkVoid(@NotNull Void value) {
      Intrinsics.handleObject3(value, "value");
      return false;
   }

   public Void getVoid(@Nullable Object var1) {
      return null;
   }

   public Set getSet() {
      return EmptySet.INSTANCE;
   }

   public Set getSet2() {
      return EmptySet.INSTANCE;
   }

   public Collection getCollection() {
      return EmptyList.INSTANCE;
   }

   private final Object getObject() {
      return INSTANCE;
   }

   public Void getVoid2(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public void putAll(Map var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public void clear() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public final int size() {
      return this.getInt();
   }

   @Override
   public final boolean containsValue(Object var1) {
      return !(var1 instanceof Void) ? false : this.checkVoid((Void)var1);
   }

   @Override
   public Object get(Object var1) {
      return this.getVoid(var1);
   }

   @Override
   public final Set entrySet() {
      return this.getSet();
   }

   @Override
   public final Set keySet() {
      return this.getSet2();
   }

   @Override
   public final Collection values() {
      return this.getCollection();
   }

   @Override
   public Object put(Object var1, Object var2) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public Object remove(Object var1) {
      return this.getVoid2(var1);
   }
}
