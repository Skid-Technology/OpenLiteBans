package litebans.lib.kotlin.collections;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CollectionToArray {
   private static final Object[] objectArray = new Object[0];
   private static final int intValue = 2147483645;

   public static final Object[] getObjectArray(@NotNull Collection collection) {
      Intrinsics.handleObject3(collection, "collection");
      int var2 = collection.size();
      Object[] var10000;
      if (var2 == 0) {
         var10000 = objectArray;
      } else {
         Iterator var3 = collection.iterator();
         if (!var3.hasNext()) {
            var10000 = objectArray;
         } else {
            Object[] var4 = new Object[var2];
            int var5 = 0;

            while (true) {
               var4[var5++] = var3.next();
               if (var5 >= var4.length) {
                  if (!var3.hasNext()) {
                     var10000 = var4;
                     break;
                  }

                  int var6 = var5 * 3 + 1 >>> 1;
                  if (var6 <= var5) {
                     if (var5 >= 2147483645) {
                        throw new OutOfMemoryError();
                     }

                     var6 = 2147483645;
                  }

                  var4 = Arrays.copyOf(var4, var6);
               } else if (!var3.hasNext()) {
                  var10000 = Arrays.copyOf(var4, var5);
                  break;
               }
            }
         }
      }

      return var10000;
   }

   public static final Object[] getObjectArray(@NotNull Collection collection, @Nullable Object[] objectArray) {
      Intrinsics.handleObject3(collection, "collection");
      if (objectArray == null) {
         throw new NullPointerException();
      } else {
         int var3 = collection.size();
         Object[] var10000;
         if (var3 == 0) {
            if (objectArray.length > 0) {
               objectArray[0] = null;
            }

            var10000 = objectArray;
         } else {
            Iterator var5 = collection.iterator();
            if (!var5.hasNext()) {
               if (objectArray.length > 0) {
                  objectArray[0] = null;
               }

               var10000 = objectArray;
            } else {
               if (var3 <= objectArray.length) {
                  var10000 = objectArray;
               } else {
                  var10000 = (Object[])Array.newInstance(objectArray.getClass().getComponentType(), var3);
                  Intrinsics.handleObject(var10000, "");
                  var10000 = var10000;
               }

               Object[] var7 = var10000;
               int var4 = 0;

               while (true) {
                  var7[var4++] = var5.next();
                  if (var4 >= var7.length) {
                     if (!var5.hasNext()) {
                        var10000 = var7;
                        break;
                     }

                     int var6 = var4 * 3 + 1 >>> 1;
                     if (var6 <= var4) {
                        if (var4 >= 2147483645) {
                           throw new OutOfMemoryError();
                        }

                        var6 = 2147483645;
                     }

                     var7 = Arrays.copyOf(var7, var6);
                  } else if (!var5.hasNext()) {
                     if (var7 == objectArray) {
                        objectArray[var4] = null;
                        var10000 = objectArray;
                     } else {
                        var10000 = Arrays.copyOf(var7, var4);
                     }
                     break;
                  }
               }
            }
         }

         return var10000;
      }
   }
}
