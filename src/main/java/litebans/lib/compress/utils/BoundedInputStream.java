package litebans.lib.compress.utils;

import java.io.InputStream;

public class BoundedInputStream extends InputStream {
   private final InputStream inputStream;
   private long longValue;

   public BoundedInputStream(InputStream var1, long var2) {
      this.inputStream = var1;
      this.longValue = var2;
   }

   @Override
   public int read() {
      try {
      if (this.longValue > 0L) {
         this.longValue--;
         return this.inputStream.read();
      } else {
         return -1;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      try {
      if (var3 == 0) {
         return 0;
      } else if (this.longValue == 0L) {
         return -1;
      } else {
         int var4 = var3;
         if ((long)var3 > this.longValue) {
            var4 = (int)this.longValue;
         }

         int var5 = this.inputStream.read(var1, var2, var4);
         if (var5 >= 0) {
            this.longValue -= (long)var5;
         }

         return var5;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void close() {
   }

   @Override
   public long skip(long var1) {
      try {
      long var3 = Math.min(this.longValue, var1);
      long var5 = this.inputStream.skip(var3);
      this.longValue -= var5;
      return var5;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
