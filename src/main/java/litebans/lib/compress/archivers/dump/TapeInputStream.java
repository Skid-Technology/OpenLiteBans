package litebans.lib.compress.archivers.dump;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import litebans.lib.compress.utils.IOUtils;

public class TapeInputStream extends FilterInputStream {
   private byte[] byteArray = new byte[1024];
   private int intValue = -1;
   private int intValue2 = 1024;
   private static final int intValue3 = 1024;
   private int intValue4 = 1024;
   private boolean flag = false;
   private long longValue = 0L;

   public TapeInputStream(InputStream var1) {
      super(var1);
   }

   public void handleInt(int var1, boolean var2) {
      try {
      this.flag = var2;
      if (var1 < 1) {
         throw new IOException("Block with " + var1 + " records found, must be at least 1");
      } else {
         this.intValue2 = 1024 * var1;
         byte[] var3 = this.byteArray;
         this.byteArray = new byte[this.intValue2];
         System.arraycopy(var3, 0, this.byteArray, 0, 1024);
         this.handleByteArray(this.byteArray, 1024, this.intValue2 - 1024);
         this.intValue = 0;
         this.intValue4 = 1024;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int available() {
      try {
      return this.intValue4 < this.intValue2 ? this.intValue2 - this.intValue4 : this.in.available();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int read() {
      throw new IllegalArgumentException("All reads must be multiple of record size (1024 bytes.");
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      if (var3 == 0) {
         return 0;
      } else if (var3 % 1024 != 0) {
         throw new IllegalArgumentException("All reads must be multiple of record size (1024 bytes.");
      } else {
         int var4 = 0;

         while (var4 < var3) {
            if (this.intValue4 == this.intValue2) {
               try {
                  this.handleBoolean(true);
               } catch (Exception var6) {
                  if (!(var6 instanceof ShortFileException)) { throw litebans.util.Unreachable.rethrow(var6); }
                  return -1;
               }
            }

            int var5;
            if (this.intValue4 + (var3 - var4) <= this.intValue2) {
               var5 = var3 - var4;
            } else {
               var5 = this.intValue2 - this.intValue4;
            }

            System.arraycopy(this.byteArray, this.intValue4, var1, var2, var5);
            this.intValue4 += var5;
            var4 += var5;
            var2 += var5;
         }

         return var4;
      }
   }

   @Override
   public long skip(long var1) {
      if (var1 % 1024L != 0L) {
         throw new IllegalArgumentException("All reads must be multiple of record size (1024 bytes.");
      } else {
         long var3 = 0L;

         while (var3 < var1) {
            if (this.intValue4 == this.intValue2) {
               try {
                  this.handleBoolean(var1 - var3 < (long)this.intValue2);
               } catch (Exception var7) {
                  if (!(var7 instanceof ShortFileException)) { throw litebans.util.Unreachable.rethrow(var7); }
                  return -1L;
               }
            }

            long var5;
            if ((long)this.intValue4 + (var1 - var3) <= (long)this.intValue2) {
               var5 = var1 - var3;
            } else {
               var5 = (long)this.intValue2 - (long)this.intValue4;
            }

            this.intValue4 = (int)((long)this.intValue4 + var5);
            var3 += var5;
         }

         return var3;
      }
   }

   @Override
   public void close() {
      try {
      if (this.in != null && this.in != System.in) {
         this.in.close();
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public byte[] getByteArray() {
      try {
      byte[] var1 = new byte[1024];
      if (-1 == this.read(var1, 0, var1.length)) {
         throw new ShortFileException();
      } else {
         return var1;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleBoolean(boolean var1) {
      try {
      if (this.in == null) {
         throw new IOException("Input buffer is closed");
      } else {
         if (this.flag && this.intValue != -1) {
            this.handleByteArray(this.byteArray, 0, 4);
            this.longValue += 4L;
            int var2 = DumpArchiveUtil.getInt(this.byteArray, 0);
            boolean var3 = (var2 & 1) == 1;
            if (!var3) {
               this.handleByteArray(this.byteArray, 0, this.intValue2);
               this.longValue = this.longValue + (long)this.intValue2;
            } else {
               int var4 = var2 >> 1 & 7;
               int var5 = var2 >> 4 & 268435455;
               byte[] var6 = new byte[var5];
               this.handleByteArray(var6, 0, var5);
               this.longValue += (long)var5;
               if (!var1) {
                  Arrays.fill(this.byteArray, (byte)0);
               } else {
                  switch (TapeInputStreamWhenMappings.switchMap[DumpCompressionType.getDumpCompressionType(var4 & 3).ordinal()]) {
                     case 1:
                        Inflater var7 = new Inflater();

                        try {
                           var7.setInput(var6, 0, var6.length);
                           var5 = var7.inflate(this.byteArray);
                           if (var5 != this.intValue2) {
                              throw new ShortFileException();
                           }
                           break;
                        } catch (DataFormatException var12) {
                           throw new DumpArchiveException("Bad data", var12);
                        } finally {
                           var7.end();
                        }
                     case 2:
                        throw new UnsupportedCompressionAlgorithmException("BZLIB2");
                     case 3:
                        throw new UnsupportedCompressionAlgorithmException("LZO");
                     default:
                        throw new UnsupportedCompressionAlgorithmException();
                  }
               }
            }
         } else {
            this.handleByteArray(this.byteArray, 0, this.intValue2);
            this.longValue = this.longValue + (long)this.intValue2;
         }

         this.intValue++;
         this.intValue4 = 0;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleByteArray(byte[] var1, int var2, int var3) {
      try {
      int var4 = IOUtils.getInt(this.in, var1, var2, var3);
      if (var4 < var3) {
         throw new ShortFileException();
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public long getLongValue() {
      return this.longValue;
   }
}
