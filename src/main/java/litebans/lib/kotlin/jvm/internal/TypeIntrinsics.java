package litebans.lib.kotlin.jvm.internal;

import java.util.Collection;
import java.util.List;

public class TypeIntrinsics {
   private static final Throwable getThrowable(Throwable var0) {
      return Intrinsics.getThrowable(var0, TypeIntrinsics.class.getName());
   }

   public static void handleObject(Object var0, String var1) {
      String var2 = var0 == null ? "null" : var0.getClass().getName();
      handleString(var2 + " cannot be cast to " + var1);
   }

   public static void handleString(String var0) {
      throw getClassCastException(new ClassCastException(var0));
   }

   public static ClassCastException getClassCastException(ClassCastException var0) {
      throw (ClassCastException)getThrowable(var0);
   }

   public static Iterable getIterable(Object var0) {
      if (var0 instanceof KMappedMarker && !(var0 instanceof KMutableIterable)) {
         handleObject(var0, "kotlin.collections.MutableIterable");
      }

      return getIterable2(var0);
   }

   public static Iterable getIterable2(Object var0) {
      try {
         return (Iterable)var0;
      } catch (ClassCastException var2) {
         throw getClassCastException(var2);
      }
   }

   public static Collection getCollection(Object var0) {
      if (var0 instanceof KMappedMarker && !(var0 instanceof KMutableCollection)) {
         handleObject(var0, "kotlin.collections.MutableCollection");
      }

      return getCollection2(var0);
   }

   public static Collection getCollection2(Object var0) {
      try {
         return (Collection)var0;
      } catch (ClassCastException var2) {
         throw getClassCastException(var2);
      }
   }

   public static List getList(Object var0) {
      if (var0 instanceof KMappedMarker && !(var0 instanceof KMutableList)) {
         handleObject(var0, "kotlin.collections.MutableList");
      }

      return getList2(var0);
   }

   public static List getList2(Object var0) {
      try {
         return (List)var0;
      } catch (ClassCastException var2) {
         throw getClassCastException(var2);
      }
   }
}
