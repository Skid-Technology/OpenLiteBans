package litebans.lib.compress.archivers.dump;

import litebans.lib.compress.archivers.zip.ZipEncoding;

public class DumpArchiveSummary {
   private long longValue;
   private long longValue2;
   private int intValue;
   private String string;
   private int intValue2;
   private String string2;
   private String string3;
   private String string4;
   private int intValue3;
   private int intValue4;
   private int intValue5;

   public DumpArchiveSummary(byte[] var1, ZipEncoding var2) {
      this.longValue = 1000L * (long)DumpArchiveUtil.getInt(var1, 4);
      this.longValue2 = 1000L * (long)DumpArchiveUtil.getInt(var1, 8);
      this.intValue = DumpArchiveUtil.getInt(var1, 12);
      this.string = DumpArchiveUtil.getString(var2, var1, 676, 16).trim();
      this.intValue2 = DumpArchiveUtil.getInt(var1, 692);
      this.string2 = DumpArchiveUtil.getString(var2, var1, 696, 64).trim();
      this.string3 = DumpArchiveUtil.getString(var2, var1, 760, 64).trim();
      this.string4 = DumpArchiveUtil.getString(var2, var1, 824, 64).trim();
      this.intValue3 = DumpArchiveUtil.getInt(var1, 888);
      this.intValue4 = DumpArchiveUtil.getInt(var1, 892);
      this.intValue5 = DumpArchiveUtil.getInt(var1, 896);
   }

   public String getString3() {
      return this.string3;
   }

   public String getString4() {
      return this.string4;
   }

   public int getIntValue5() {
      return this.intValue5;
   }

   public boolean check() {
      return (this.intValue3 & 128) == 128;
   }

   @Override
   public int hashCode() {
      int var1 = 17;
      if (this.string != null) {
         var1 = this.string.hashCode();
      }

      var1 = (int)((long)var1 + 31L * this.longValue);
      if (this.string4 != null) {
         var1 = 31 * this.string4.hashCode() + 17;
      }

      if (this.string3 != null) {
         var1 = 31 * this.string3.hashCode() + 17;
      }

      return var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && var1.getClass().equals(this.getClass())) {
         DumpArchiveSummary var2 = (DumpArchiveSummary)var1;
         if (this.longValue != var2.longValue) {
            return false;
         } else {
            return this.getString4() == null || !this.getString4().equals(var2.getString4())
               ? false
               : this.getString3() != null && this.getString3().equals(var2.getString3());
         }
      } else {
         return false;
      }
   }
}
