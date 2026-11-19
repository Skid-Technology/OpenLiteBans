package litebans.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import litebans.core.LiteBansCore;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.collections.CollectionToArray;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;

public final class ConfiguredList implements List, KMappedMarker {
   private final LiteBansPlatform liteBansPlatform;
   private final PunishmentStorage punishmentStorage;
   private final boolean flag;
   private final ArrayList arrayList;
   private Table table;
   private final LiteBansCore liteBansCore;

   public ConfiguredList(@NotNull LiteBansPlatform var1, @NotNull PunishmentStorage var2, boolean var3, @NotNull ArrayList var4) {
      this.liteBansPlatform = var1;
      this.punishmentStorage = var2;
      this.flag = var3;
      this.arrayList = var4;
      this.table = Table.BANS;
      this.liteBansCore = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
   }

   public ConfiguredList(LiteBansPlatform var1, PunishmentStorage var2, boolean var3, ArrayList var4, int var5, DefaultConstructorMarker var6) {
      this(var1, var2, var3, ((var5 & 8) != 0 ? new ArrayList() : var4));
   }

   public final boolean isFlag() {
      return this.flag;
   }

   public final ArrayList getArrayList() {
      return this.arrayList;
   }

   public final void setTable(@NotNull Table var1) {
      this.table = var1;
   }

   public final LiteBansCore getLiteBansCore() {
      return this.liteBansCore;
   }

   public final void handlePair(@NotNull Pair var1) {
      boolean var2 = Intrinsics.checkObject(this.liteBansCore.getConfig().getSqlDriver(), "mysql");
      if (this.flag || !this.punishmentStorage.columnExists(this.table, (String)var1.getObject(), var2)) {
         Object var3 = ((Function1)var1.getObject2()).invoke(this.table);
         if (var3 instanceof Object[]) {
            ConfiguredList var4 = this;
            Object[] var5 = (Object[])var3;
            Object[] var7 = var5;
            int var9 = 0;

            for (int var10 = var5.length; var9 < var10; var9++) {
               Object var11 = var7[var9];
               Collection var10000 = var4.getArrayList();
               Intrinsics.handleObject(var11, "");
               var10000.add((SqlString)var11);
            }
         } else {
            Collection var13 = this.arrayList;
            Intrinsics.handleObject(var3, "");
            var13.add((SqlString)var3);
         }
      }
   }

   @Override
   public boolean isEmpty() {
      return this.arrayList.isEmpty();
   }

   public boolean checkString(@NotNull String var1) {
      return this.arrayList.contains(SqlString.getSqlString(var1));
   }

   @Override
   public Iterator iterator() {
      return this.arrayList.iterator();
   }

   @Override
   public boolean containsAll(@NotNull Collection var1) {
      return this.arrayList.containsAll(var1);
   }

   public String getString(int var1) {
      return ((SqlString)this.arrayList.get(var1)).getString();
   }

   public int getInt(@NotNull String var1) {
      return this.arrayList.indexOf(SqlString.getSqlString(var1));
   }

   public int getInt2(@NotNull String var1) {
      return this.arrayList.lastIndexOf(SqlString.getSqlString(var1));
   }

   @Override
   public ListIterator listIterator() {
      return this.arrayList.listIterator();
   }

   @Override
   public ListIterator listIterator(int var1) {
      return this.arrayList.listIterator(var1);
   }

   @Override
   public List subList(int var1, int var2) {
      return this.arrayList.subList(var1, var2);
   }

   public int getInt() {
      return this.arrayList.size();
   }

   @Override
   public boolean remove(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public boolean addAll(Collection var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public boolean addAll(int var1, Collection var2) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public boolean removeAll(Collection var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public boolean retainAll(Collection var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public void clear() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public Object remove(int var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public void replaceAll(UnaryOperator var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public void sort(Comparator var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public final boolean contains(Object var1) {
      return !(var1 instanceof SqlString) ? false : this.checkString(((SqlString)var1).getString());
   }

   @Override
   public Object get(int var1) {
      return SqlString.getSqlString(this.getString(var1));
   }

   @Override
   public final int indexOf(Object var1) {
      return !(var1 instanceof SqlString) ? -1 : this.getInt(((SqlString)var1).getString());
   }

   @Override
   public final int lastIndexOf(Object var1) {
      return !(var1 instanceof SqlString) ? -1 : this.getInt2(((SqlString)var1).getString());
   }

   @Override
   public final int size() {
      return this.getInt();
   }

   @Override
   public boolean add(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public void add(int var1, Object var2) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public Object set(int var1, Object var2) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public Object[] toArray(Object[] var1) {
      return CollectionToArray.getObjectArray(this, var1);
   }

   @Override
   public Object[] toArray() {
      return CollectionToArray.getObjectArray(this);
   }
}
