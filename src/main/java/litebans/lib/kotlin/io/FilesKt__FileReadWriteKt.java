package litebans.lib.kotlin.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

public class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
   public static final byte[] getByteArray(@NotNull File var0) {
      try {
      Intrinsics.handleObject3(var0, "<this>");
      Closeable var1 = new FileInputStream(var0);
      Throwable var2 = null;

      byte[] var18;
      try {
         FileInputStream var3 = (FileInputStream)var1;
         int var5 = 0;
         long var6 = var0.length();
         if (var6 > 2147483647L) {
            throw new OutOfMemoryError("File " + var0 + " is too big (" + var6 + " bytes) to fit in memory.");
         }

         int var11 = (int)var6;
         byte[] var19 = new byte[var11];

         while (var11 > 0) {
            int var12 = var3.read(var19, var5, var11);
            if (var12 < 0) {
               break;
            }

            var11 -= var12;
            var5 += var12;
         }

         byte[] var10000;
         if (var11 > 0) {
            var10000 = Arrays.copyOf(var19, var5);
         } else {
            int var20 = var3.read();
            if (var20 == -1) {
               var10000 = var19;
            } else {
               ExposingBufferByteArrayOutputStream var13 = new ExposingBufferByteArrayOutputStream(8193);
               var13.write(var20);
               ByteStreamsKt.getLongDefault(var3, var13, 0, 2, null);
               int var10 = var19.length + var13.size();
               if (var10 < 0) {
                  throw new OutOfMemoryError("File " + var0 + " is too big to fit in memory.");
               }

               var10000 = ArraysKt.getByteArray(var13.getBuf(), Arrays.copyOf(var19, var10), var19.length, 0, var13.size());
            }
         }

         var18 = var10000;
      } catch (Throwable var16) {
         var2 = var16;
         throw var16;
      } finally {
         CloseableKt.handleCloseable(var1, var2);
      }

      return var18;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static final void handleFile(@NotNull File file, @NotNull byte[] array) {
      try {
      Intrinsics.handleObject3(file, "<this>");
      Intrinsics.handleObject3(array, "array");
      Closeable var2 = new FileOutputStream(file);
      Throwable var3 = null;

      try {
         FileOutputStream var4 = (FileOutputStream)var2;
         var4.write(array);
      } catch (Throwable var8) {
         var3 = var8;
         throw var8;
      } finally {
         CloseableKt.handleCloseable(var2, var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static final void handleFile(@NotNull File file, @NotNull String text, @NotNull Charset charset) {
      try {
      Intrinsics.handleObject3(file, "<this>");
      Intrinsics.handleObject3(text, "text");
      Intrinsics.handleObject3(charset, "charset");
      Closeable var3 = new FileOutputStream(file);
      Throwable var4 = null;

      try {
         FileOutputStream var5 = (FileOutputStream)var3;
         FilesKt.handleOutputStream(var5, text, charset);
      } catch (Throwable var9) {
         var4 = var9;
         throw var9;
      } finally {
         CloseableKt.handleCloseable(var3, var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static void handleFile(File var0, String var1, Charset var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = Charsets.charset;
      }

      FilesKt.handleFile(var0, var1, var2);
   }

   public static final void handleOutputStream(@NotNull OutputStream outputStream, @NotNull String text, @NotNull Charset charset) {
      try {
      Intrinsics.handleObject3(outputStream, "<this>");
      Intrinsics.handleObject3(text, "text");
      Intrinsics.handleObject3(charset, "charset");
      short var3 = 8192;
      if (text.length() < 2 * var3) {
         outputStream.write(text.getBytes(charset));
      } else {
         CharsetEncoder var4 = FilesKt.getCharsetEncoder(charset);
         CharBuffer var5 = CharBuffer.allocate(var3);
         Intrinsics.handleObject(var4);
         ByteBuffer var6 = FilesKt.getByteBuffer(var3, var4);
         int var7 = 0;
         byte var8 = 0;

         while (var7 < text.length()) {
            int var9 = Math.min(var3 - var8, text.length() - var7);
            int var10 = var7 + var9;
            char[] var12 = var5.array();
            text.getChars(var7, var10, var12, var8);
            ((Buffer)var5).limit(var9 + var8);
            CoderResult var11 = var4.encode(var5, var6, var10 == text.length());
            if (!var11.isUnderflow()) {
               throw new IllegalStateException("Check failed.");
            }

            outputStream.write(var6.array(), 0, var6.position());
            if (var5.position() != var5.limit()) {
               var5.put(0, var5.get());
               var8 = 1;
            } else {
               var8 = 0;
            }

            ((Buffer)var5).clear();
            ((Buffer)var6).clear();
            var7 = var10;
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static final CharsetEncoder getCharsetEncoder(@NotNull Charset var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
   }

   public static final ByteBuffer getByteBuffer(int intValue, @NotNull CharsetEncoder encoder) {
      Intrinsics.handleObject3(encoder, "encoder");
      int var2 = (int)((float)Math.ceil((double)encoder.maxBytesPerChar()));
      return ByteBuffer.allocate(intValue * var2);
   }
}
