package litebans.lib.kotlin.jvm.internal;

import java.util.Arrays;
import litebans.lib.kotlin.UninitializedPropertyAccessException;

public class Intrinsics {
   private Intrinsics() {
   }

   public static void handleObject(Object var0) {
      if (var0 == null) {
         run();
      }
   }

   public static void handleObject(Object var0, String var1) {
      if (var0 == null) {
         handleString(var1);
      }
   }

   public static void run() {
      throw (NullPointerException)getThrowable(new NullPointerException());
   }

   public static void handleString(String var0) {
      throw (NullPointerException)getThrowable(new NullPointerException(var0));
   }

   public static void handleString2(String var0) {
      throw (UninitializedPropertyAccessException)getThrowable(new UninitializedPropertyAccessException(var0));
   }

   public static void handleString3(String var0) {
      handleString2("lateinit property " + var0 + " has not been initialized");
   }

   public static void handleObject2(Object var0, String var1) {
      if (var0 == null) {
         throw (NullPointerException)getThrowable(new NullPointerException(var1 + " must not be null"));
      }
   }

   public static void handleObject3(Object var0, String var1) {
      if (var0 == null) {
         handleString4(var1);
      }
   }

   private static final void handleString4(String var0) {
      throw (NullPointerException)getThrowable(new NullPointerException(getString(var0)));
   }

   private static final String getString(String var0) {
      StackTraceElement[] var1 = Thread.currentThread().getStackTrace();
      String var2 = Intrinsics.class.getName();
      int var3 = 0;

      while (!var1[var3].getClassName().equals(var2)) {
         var3++;
      }

      while (var1[var3].getClassName().equals(var2)) {
         var3++;
      }

      StackTraceElement var4 = var1[var3];
      String var5 = var4.getClassName();
      String var6 = var4.getMethodName();
      return "Parameter specified as non-null is null: method " + var5 + "." + var6 + ", parameter " + var0;
   }

   public static int getInt(long var0, long var2) {
      return var0 < var2 ? -1 : (var0 == var2 ? 0 : 1);
   }

   public static int getInt(int var0, int var1) {
      return var0 < var1 ? -1 : (var0 == var1 ? 0 : 1);
   }

   public static boolean checkObject(Object var0, Object var1) {
      return var0 == null ? var1 == null : var0.equals(var1);
   }

   private static final Throwable getThrowable(Throwable var0) {
      return getThrowable(var0, Intrinsics.class.getName());
   }

   public static Throwable getThrowable(Throwable var0, String var1) {
      StackTraceElement[] var2 = var0.getStackTrace();
      int var3 = var2.length;
      int var4 = -1;

      for (int var5 = 0; var5 < var3; var5++) {
         if (var1.equals(var2[var5].getClassName())) {
            var4 = var5;
         }
      }

      StackTraceElement[] var6 = Arrays.copyOfRange(var2, var4 + 1, var3);
      var0.setStackTrace(var6);
      return var0;
   }
}
