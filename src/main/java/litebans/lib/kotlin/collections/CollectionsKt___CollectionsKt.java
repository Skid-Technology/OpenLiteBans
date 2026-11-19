package litebans.lib.kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CollectionsKt___CollectionsKt extends CollectionsKt___CollectionsJvmKt {
   public static final boolean checkIterable(@NotNull Iterable var0, Object var1) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0 instanceof Collection ? ((Collection)var0).contains(var1) : CollectionsKt.getInt(var0, var1) >= 0;
   }

   public static final Object getObject(@NotNull Iterable var0, int var1) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0 instanceof List ? ((List)var0).get(var1) : getObject(var0, var1, (Function1) i -> getObject(var0, (Integer) i));
   }

   public static final Object getObject(@NotNull Iterable iterable, int intValue, @NotNull Function1 defaultValue) {
      Intrinsics.handleObject3(iterable, "<this>");
      Intrinsics.handleObject3(defaultValue, "defaultValue");
      if (iterable instanceof List) {
         List var6 = (List)iterable;
         return (0 <= intValue ? intValue < var6.size() : false) ? var6.get(intValue) : defaultValue.invoke(intValue);
      } else if (intValue < 0) {
         return defaultValue.invoke(intValue);
      } else {
         Iterator var3 = iterable.iterator();
         int var4 = 0;

         while (var3.hasNext()) {
            Object var5 = var3.next();
            if (intValue == var4++) {
               return var5;
            }
         }

         return defaultValue.invoke(intValue);
      }
   }

   public static final Object getObject(@NotNull Iterable var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0 instanceof List) {
         return CollectionsKt.getObject((List)var0);
      } else {
         Iterator var1 = var0.iterator();
         if (!var1.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
         } else {
            return var1.next();
         }
      }
   }

   public static final Object getObject(@NotNull List var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0.isEmpty()) {
         throw new NoSuchElementException("List is empty.");
      } else {
         return var0.get(0);
      }
   }

   public static final Object getObject2(@NotNull List var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0.isEmpty() ? null : var0.get(0);
   }

   public static final Object getObject(@NotNull List var0, int var1) {
      Intrinsics.handleObject3(var0, "<this>");
      return (0 <= var1 ? var1 < var0.size() : false) ? var0.get(var1) : null;
   }

   public static final int getInt(@NotNull Iterable var0, Object var1) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0 instanceof List) {
         return ((List)var0).indexOf(var1);
      } else {
         int var2 = 0;

         for (Object var4 : var0) {
            if (var2 < 0) {
               CollectionsKt.run();
            }

            if (Intrinsics.checkObject(var1, var4)) {
               return var2;
            }

            var2++;
         }

         return -1;
      }
   }

   public static final Object getObject3(@NotNull List var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0.isEmpty()) {
         throw new NoSuchElementException("List is empty.");
      } else {
         return var0.get(CollectionsKt.getInt(var0));
      }
   }

   public static final Object getObject2(@NotNull Iterable var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0 instanceof List) {
         return CollectionsKt.getObject4((List)var0);
      } else {
         Iterator var1 = var0.iterator();
         if (!var1.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
         } else {
            Object var2 = var1.next();
            if (var1.hasNext()) {
               throw new IllegalArgumentException("Collection has more than one element.");
            } else {
               return var2;
            }
         }
      }
   }

   public static final Object getObject4(@NotNull List var0) {
      Intrinsics.handleObject3(var0, "<this>");
      switch (var0.size()) {
         case 0:
            throw new NoSuchElementException("List is empty.");
         case 1:
            return var0.get(0);
         default:
            throw new IllegalArgumentException("List has more than one element.");
      }
   }

   public static final List getList(@NotNull Iterable var0, int var1) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var1 < 0) {
         String var6 = "Requested element count " + var1 + " is less than zero.";
         throw new IllegalArgumentException(var6.toString());
      } else if (var1 == 0) {
         return CollectionsKt.getList();
      } else {
         if (var0 instanceof Collection) {
            if (var1 >= ((Collection)var0).size()) {
               return CollectionsKt.getList3(var0);
            }

            if (var1 == 1) {
               return CollectionsKt.getList(CollectionsKt.getObject(var0));
            }
         }

         int var2 = 0;
         ArrayList var3 = new ArrayList(var1);

         for (Object var5 : var0) {
            var3.add(var5);
            if (++var2 == var1) {
               break;
            }
         }

         return CollectionsKt.getList(var3);
      }
   }

   public static final List getList(@NotNull Iterable var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0 instanceof Collection && ((Collection)var0).size() <= 1) {
         return CollectionsKt.getList3(var0);
      } else {
         List var1 = CollectionsKt.getList4(var0);
         CollectionsKt.handleList2(var1);
         return var1;
      }
   }

   public static final List getList2(@NotNull Iterable var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0 instanceof Collection) {
         if (((Collection)var0).size() <= 1) {
            return CollectionsKt.getList3(var0);
         } else {
            Collection var4 = (Collection)var0;
            Object[] var5 = var4.toArray(new Comparable[0]);
            Comparable[] var2 = (Comparable[])var5;
            ArraysKt.handleObjectArray(var2);
            return ArraysKt.getList(var5);
         }
      } else {
         List var1 = CollectionsKt.getList4(var0);
         CollectionsKt.handleList(var1);
         return var1;
      }
   }

   public static final List getList(@NotNull Iterable iterable, @NotNull Comparator comparator) {
      Intrinsics.handleObject3(iterable, "<this>");
      Intrinsics.handleObject3(comparator, "comparator");
      if (iterable instanceof Collection) {
         if (((Collection)iterable).size() <= 1) {
            return CollectionsKt.getList3(iterable);
         } else {
            Collection var5 = (Collection)iterable;
            Object[] var6 = var5.toArray(new Object[0]);
            ArraysKt.handleObjectArray(var6, comparator);
            return ArraysKt.getList(var6);
         }
      } else {
         List var2 = CollectionsKt.getList4(iterable);
         CollectionsKt.handleList(var2, comparator);
         return var2;
      }
   }

   public static final Collection getCollection(@NotNull Iterable iterable, @NotNull Collection destination) {
      Intrinsics.handleObject3(iterable, "<this>");
      Intrinsics.handleObject3(destination, "destination");

      for (Object var3 : iterable) {
         destination.add(var3);
      }

      return destination;
   }

   public static final HashSet getHashSet(@NotNull Iterable var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return (HashSet)CollectionsKt.getCollection(var0, new HashSet(MapsKt.getInt(CollectionsKt.getInt(var0, 12))));
   }

   public static final List getList3(@NotNull Iterable var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0 instanceof Collection) {
         List var10000;
         switch (((Collection)var0).size()) {
            case 0:
               var10000 = CollectionsKt.getList();
               break;
            case 1:
               var10000 = CollectionsKt.getList(var0 instanceof List ? ((List)var0).get(0) : ((Collection)var0).iterator().next());
               break;
            default:
               var10000 = CollectionsKt.getList((Collection)var0);
         }

         return var10000;
      } else {
         return CollectionsKt.getList(CollectionsKt.getList4(var0));
      }
   }

   public static final List getList4(@NotNull Iterable var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0 instanceof Collection ? CollectionsKt.getList((Collection)var0) : (List)CollectionsKt.getCollection(var0, new ArrayList());
   }

   public static final List getList(@NotNull Collection var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return new ArrayList(var0);
   }

   public static final Set getSet(@NotNull Iterable var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0 instanceof Collection) {
         Set var10000;
         switch (((Collection)var0).size()) {
            case 0:
               var10000 = SetsKt.getSet();
               break;
            case 1:
               var10000 = SetsKt.getSet(var0 instanceof List ? ((List)var0).get(0) : ((Collection)var0).iterator().next());
               break;
            default:
               var10000 = (Set)CollectionsKt.getCollection(var0, new LinkedHashSet(MapsKt.getInt(((Collection)var0).size())));
         }

         return var10000;
      } else {
         return SetsKt.getSet((Set)CollectionsKt.getCollection(var0, new LinkedHashSet()));
      }
   }

   public static final List getList(@NotNull Collection var0, Object var1) {
      Intrinsics.handleObject3(var0, "<this>");
      ArrayList var2 = new ArrayList(var0.size() + 1);
      var2.addAll(var0);
      var2.add(var1);
      return var2;
   }

   public static final List getList(@NotNull Collection collection, @NotNull Iterable elements) {
      Intrinsics.handleObject3(collection, "<this>");
      Intrinsics.handleObject3(elements, "elements");
      if (elements instanceof Collection) {
         ArrayList var3 = new ArrayList(collection.size() + ((Collection)elements).size());
         var3.addAll(collection);
         var3.addAll((Collection)elements);
         return var3;
      } else {
         ArrayList var2 = new ArrayList(collection);
         CollectionsKt.checkCollection(var2, elements);
         return var2;
      }
   }

   public static final Appendable getAppendable(
      @NotNull Iterable iterable,
      @NotNull Appendable buffer,
      @NotNull CharSequence separator,
      @NotNull CharSequence prefix,
      @NotNull CharSequence postfix,
      int intValue,
      @NotNull CharSequence truncated,
      @Nullable Function1 function1
   ) {
      try {
      Intrinsics.handleObject3(iterable, "<this>");
      Intrinsics.handleObject3(buffer, "buffer");
      Intrinsics.handleObject3(separator, "separator");
      Intrinsics.handleObject3(prefix, "prefix");
      Intrinsics.handleObject3(postfix, "postfix");
      Intrinsics.handleObject3(truncated, "truncated");
      buffer.append(prefix);
      int var8 = 0;

      for (Object var10 : iterable) {
         if (++var8 > 1) {
            buffer.append(separator);
         }

         if (intValue >= 0 && var8 > intValue) {
            break;
         }

         StringsKt.handleAppendable(buffer, var10, function1);
      }

      if (intValue >= 0 && var8 > intValue) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return buffer;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static final String getString(
      @NotNull Iterable iterable,
      @NotNull CharSequence separator,
      @NotNull CharSequence prefix,
      @NotNull CharSequence postfix,
      int intValue,
      @NotNull CharSequence truncated,
      @Nullable Function1 function1
   ) {
      Intrinsics.handleObject3(iterable, "<this>");
      Intrinsics.handleObject3(separator, "separator");
      Intrinsics.handleObject3(prefix, "prefix");
      Intrinsics.handleObject3(postfix, "postfix");
      Intrinsics.handleObject3(truncated, "truncated");
      return ((StringBuilder)CollectionsKt.getAppendable(iterable, new StringBuilder(), separator, prefix, postfix, intValue, truncated, function1)).toString();
   }

   public static String getString(
      Iterable var0, CharSequence var1, CharSequence var2, CharSequence var3, int var4, CharSequence var5, Function1 var6, int var7, Object var8
   ) {
      if ((var7 & 1) != 0) {
         var1 = ", ";
      }

      if ((var7 & 2) != 0) {
         var2 = "";
      }

      if ((var7 & 4) != 0) {
         var3 = "";
      }

      if ((var7 & 8) != 0) {
         var4 = -1;
      }

      if ((var7 & 16) != 0) {
         var5 = "...";
      }

      if ((var7 & 32) != 0) {
         var6 = null;
      }

      return CollectionsKt.getString(var0, var1, var2, var3, var4, var5, var6);
   }

   private static final Object getObject(int var0, int var1) {
      throw new IndexOutOfBoundsException("Collection doesn't contain element at index " + var0 + '.');
   }
}
