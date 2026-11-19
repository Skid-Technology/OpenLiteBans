package litebans.lib.compress.archivers.zip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import litebans.lib.compress.archivers.ArchiveEntry;

public class ZipArchiveEntry extends ZipEntry implements ArchiveEntry, EntryStreamOffsets {
   public static final int intValue = 3;
   public static final int intValue2 = 0;
   public static final int intValue3 = -1;
   private static final int intValue4 = 65535;
   private static final int intValue5 = 16;
   private static final byte[] byteArray = new byte[0];
   private int intValue6 = -1;
   private long longValue2 = -1L;
   private int intValue7 = 0;
   private int intValue8 = 0;
   private long longValue3 = 0L;
   private int intValue9 = 0;
   private ZipExtraField[] zipExtraFieldArray;
   private UnparseableExtraFieldData unparseableExtraFieldData = null;
   private String string = null;
   private byte[] byteArray2 = null;
   private GeneralPurposeBit generalPurposeBit = new GeneralPurposeBit();
   private static final ZipExtraField[] zipExtraFieldArray2 = new ZipExtraField[0];
   private long longValue4 = -1L;
   private long longValue5 = -1L;
   private boolean flag2 = false;
   private ZipNameSource zipNameSource = ZipNameSource.NAME;
   private ZipCommentSource zipCommentSource = ZipCommentSource.COMMENT;

   public ZipArchiveEntry(String var1) {
      super(var1);
      this.handleString(var1);
   }

   public ZipArchiveEntry() {
      this("");
   }

   @Override
   public Object clone() {
      ZipArchiveEntry var1 = (ZipArchiveEntry)super.clone();
      var1.setIntValue7(this.getIntValue7());
      var1.setLongValue3(this.getLongValue3());
      var1.handleZipExtraFieldArray(this.getZipExtraFieldArray());
      return var1;
   }

   @Override
   public int getMethod() {
      return this.intValue6;
   }

   @Override
   public void setMethod(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("ZIP compression method can not be negative: " + var1);
      } else {
         this.intValue6 = var1;
      }
   }

   public int getIntValue7() {
      return this.intValue7;
   }

   public void setIntValue7(int var1) {
      this.intValue7 = var1;
   }

   public long getLongValue3() {
      return this.longValue3;
   }

   public void setLongValue3(long var1) {
      this.longValue3 = var1;
   }

   public int getIntValue8() {
      return this.intValue8;
   }

   public void handleZipExtraFieldArray(ZipExtraField[] var1) {
      this.unparseableExtraFieldData = null;
      ArrayList var2 = new ArrayList();
      if (var1 != null) {
         for (ZipExtraField var6 : var1) {
            if (var6 instanceof UnparseableExtraFieldData) {
               this.unparseableExtraFieldData = (UnparseableExtraFieldData)var6;
            } else {
               var2.add(var6);
            }
         }
      }

      this.zipExtraFieldArray = /* cast-fix */(ZipExtraField[])(var2.toArray(zipExtraFieldArray2));
      this.run();
   }

   private final ZipExtraField[] getZipExtraFieldArray() {
      if (this.zipExtraFieldArray == null) {
         return this.getZipExtraFieldArray3();
      } else {
         return this.unparseableExtraFieldData != null ? this.getZipExtraFieldArray2() : this.zipExtraFieldArray;
      }
   }

   private final ZipExtraField[] getZipExtraFieldArray2() {
      ZipExtraField[] var1 = this.getZipExtraFieldArray(this.zipExtraFieldArray, this.zipExtraFieldArray.length + 1);
      var1[this.zipExtraFieldArray.length] = this.unparseableExtraFieldData;
      return var1;
   }

   private final ZipExtraField[] getZipExtraFieldArray3() {
      return this.unparseableExtraFieldData == null ? zipExtraFieldArray2 : new ZipExtraField[]{this.unparseableExtraFieldData};
   }

   public void handleZipExtraField(ZipExtraField var1) {
      if (var1 instanceof UnparseableExtraFieldData) {
         this.unparseableExtraFieldData = (UnparseableExtraFieldData)var1;
      } else if (this.zipExtraFieldArray == null) {
         this.zipExtraFieldArray = new ZipExtraField[]{var1};
      } else {
         if (this.getZipExtraField(var1.getZipShort()) != null) {
            this.handleZipShort(var1.getZipShort());
         }

         ZipExtraField[] var2 = this.getZipExtraFieldArray(this.zipExtraFieldArray, this.zipExtraFieldArray.length + 1);
         var2[var2.length - 1] = var1;
         this.zipExtraFieldArray = var2;
      }

      this.run();
   }

   public void handleZipShort(ZipShort var1) {
      if (this.zipExtraFieldArray == null) {
         throw new NoSuchElementException();
      } else {
         ArrayList var2 = new ArrayList();

         for (ZipExtraField var6 : this.zipExtraFieldArray) {
            if (!var1.equals(var6.getZipShort())) {
               var2.add(var6);
            }
         }

         if (this.zipExtraFieldArray.length == var2.size()) {
            throw new NoSuchElementException();
         } else {
            this.zipExtraFieldArray = /* cast-fix */(ZipExtraField[])(var2.toArray(zipExtraFieldArray2));
            this.run();
         }
      }
   }

   public ZipExtraField getZipExtraField(ZipShort var1) {
      if (this.zipExtraFieldArray != null) {
         for (ZipExtraField var5 : this.zipExtraFieldArray) {
            if (var1.equals(var5.getZipShort())) {
               return var5;
            }
         }
      }

      return null;
   }

   @Override
   public void setExtra(byte[] var1) {
      try {
         ZipExtraField[] var2 = ExtraFieldUtils.getZipExtraFieldArray(var1, true, ExtraFieldParsingMode.BEST_EFFORT);
         this.handleZipExtraFieldArray(var2, true);
      } catch (Exception var3) {
         if (!(var3 instanceof ZipException)) { throw litebans.util.Unreachable.rethrow(var3); }
         throw new RuntimeException("Error parsing extra fields for entry: " + this.getName() + " - " + var3.getMessage(), var3);
      }
   }

   public void run() {
      super.setExtra(ExtraFieldUtils.getByteArray(this.getZipExtraFieldArray()));
   }

   public byte[] getByteArray() {
      byte[] var1 = this.getExtra();
      return var1 != null ? var1 : byteArray;
   }

   public byte[] getByteArray2() {
      return ExtraFieldUtils.getByteArray2(this.getZipExtraFieldArray());
   }

   @Override
   public String getName() {
      return this.string == null ? super.getName() : this.string;
   }

   @Override
   public boolean isDirectory() {
      String var1 = this.getName();
      return var1 != null && var1.endsWith("/");
   }

   public void handleString(String var1) {
      if (var1 != null && this.getIntValue8() == 0 && !var1.contains("/")) {
         var1 = var1.replace('\\', '/');
      }

      this.string = var1;
   }

   @Override
   public long getSize() {
      return this.longValue2;
   }

   @Override
   public void setSize(long var1) {
      if (var1 < 0L) {
         throw new IllegalArgumentException("Invalid entry size");
      } else {
         this.longValue2 = var1;
      }
   }

   @Override
   public int hashCode() {
      String var1 = this.getName();
      return (var1 == null ? "" : var1).hashCode();
   }

   public GeneralPurposeBit getGeneralPurposeBit() {
      return this.generalPurposeBit;
   }

   private final void handleZipExtraFieldArray(ZipExtraField[] var1, boolean var2) {
      if (this.zipExtraFieldArray == null) {
         this.handleZipExtraFieldArray(var1);
      } else {
         for (ZipExtraField var6 : var1) {
            Object var7;
            if (var6 instanceof UnparseableExtraFieldData) {
               var7 = this.unparseableExtraFieldData;
            } else {
               var7 = this.getZipExtraField(var6.getZipShort());
            }

            if (var7 == null) {
               this.handleZipExtraField(var6);
            } else {
               byte[] var8 = var2 ? var6.getByteArray() : var6.getByteArray2();

               try {
                  if (var2) {
                     ((ZipExtraField)var7).handleByteArray(var8, 0, var8.length);
                  } else {
                     ((ZipExtraField)var7).handleByteArray2(var8, 0, var8.length);
                  }
               } catch (Exception var11) {
                  if (!(var11 instanceof ZipException)) { throw litebans.util.Unreachable.rethrow(var11); }
                  UnrecognizedExtraField var10 = new UnrecognizedExtraField();
                  var10.setZipShort(((ZipExtraField)var7).getZipShort());
                  if (var2) {
                     var10.handleByteArray(var8);
                     var10.handleByteArray2(((ZipExtraField)var7).getByteArray2());
                  } else {
                     var10.handleByteArray(((ZipExtraField)var7).getByteArray());
                     var10.handleByteArray2(var8);
                  }

                  this.handleZipShort(((ZipExtraField)var7).getZipShort());
                  this.handleZipExtraField(var10);
               }
            }
         }

         this.run();
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         ZipArchiveEntry var2 = (ZipArchiveEntry)var1;
         String var3 = this.getName();
         String var4 = var2.getName();
         if (var3 == null) {
            if (var4 != null) {
               return false;
            }
         } else if (!var3.equals(var4)) {
            return false;
         }

         String var5 = this.getComment();
         String var6 = var2.getComment();
         if (var5 == null) {
            var5 = "";
         }

         if (var6 == null) {
            var6 = "";
         }

         return this.getTime() == var2.getTime()
            && var5.equals(var6)
            && this.getIntValue7() == var2.getIntValue7()
            && this.getIntValue8() == var2.getIntValue8()
            && this.getLongValue3() == var2.getLongValue3()
            && this.getMethod() == var2.getMethod()
            && this.getSize() == var2.getSize()
            && this.getCrc() == var2.getCrc()
            && this.getCompressedSize() == var2.getCompressedSize()
            && Arrays.equals(this.getByteArray2(), var2.getByteArray2())
            && Arrays.equals(this.getByteArray(), var2.getByteArray())
            && this.longValue4 == var2.longValue4
            && this.longValue5 == var2.longValue5
            && this.generalPurposeBit.equals(var2.generalPurposeBit);
      } else {
         return false;
      }
   }

   private final ZipExtraField[] getZipExtraFieldArray(ZipExtraField[] var1, int var2) {
      ZipExtraField[] var3 = new ZipExtraField[var2];
      System.arraycopy(var1, 0, var3, 0, Math.min(var1.length, var2));
      return var3;
   }
}
