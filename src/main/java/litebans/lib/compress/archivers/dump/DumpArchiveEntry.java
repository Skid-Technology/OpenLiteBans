package litebans.lib.compress.archivers.dump;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import litebans.lib.compress.archivers.ArchiveEntry;

public class DumpArchiveEntry implements ArchiveEntry {
   @Override
   public boolean isDirectory() {
      return false;
   }

   @Override
   public long getSize() {
      return this.longValue2;
   }

   private String string;
   private DumpType dumpType = DumpType.UNKNOWN;
   private int intValue;
   private Set set = Collections.emptySet();
   private long longValue2;
   private long longValue3;
   private long longValue4;
   private int intValue2;
   private int intValue3;
   private final DumpArchiveSummary dumpArchiveSummary = null;
   private final DumpTapeSegmentHeader dumpTapeSegmentHeader = new DumpTapeSegmentHeader();
   private String string2;
   private int intValue4;
   private int intValue5;
   private int intValue6;
   private long longValue5;
   private int intValue7;

   public DumpSegmentType getDumpSegmentType() {
      return this.dumpTapeSegmentHeader.getDumpSegmentType();
   }

   public int getInt() {
      return this.dumpTapeSegmentHeader.getIntValue3();
   }

   public boolean checkInt(int var1) {
      return (this.dumpTapeSegmentHeader.getInt(var1) & 1) == 0;
   }

   @Override
   public int hashCode() {
      return this.intValue5;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 != null && var1.getClass().equals(this.getClass())) {
         DumpArchiveEntry var2 = (DumpArchiveEntry)var1;
         if (var2.dumpTapeSegmentHeader == null) {
            return false;
         } else {
            return this.intValue5 != var2.intValue5
               ? false
               : (this.dumpArchiveSummary != null || var2.dumpArchiveSummary == null)
                  && (this.dumpArchiveSummary == null || this.dumpArchiveSummary.equals(var2.dumpArchiveSummary));
         }
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.getName();
   }

   public static final DumpArchiveEntry getDumpArchiveEntry(byte[] var0) {
      DumpArchiveEntry var1 = new DumpArchiveEntry();
      DumpTapeSegmentHeader var2 = var1.dumpTapeSegmentHeader;
      DumpTapeSegmentHeader.getDumpSegmentType(var2, DumpSegmentType.getDumpSegmentType(DumpArchiveUtil.getInt(var0, 0)));
      DumpTapeSegmentHeader.getInt(var2, DumpArchiveUtil.getInt(var0, 12));
      var1.intValue5 = DumpTapeSegmentHeader.getInt2(var2, DumpArchiveUtil.getInt(var0, 20));
      int var3 = DumpArchiveUtil.getInt2(var0, 32);
      var1.setDumpType(DumpType.getDumpType(var3 >> 12 & 15));
      var1.handleInt(var3);
      var1.intValue6 = DumpArchiveUtil.getInt2(var0, 34);
      var1.setLongValue2(DumpArchiveUtil.getLong(var0, 40));
      long var4 = 1000L * (long)DumpArchiveUtil.getInt(var0, 48) + (long)(DumpArchiveUtil.getInt(var0, 52) / 1000);
      var1.handleDate2(new Date(var4));
      var4 = 1000L * (long)DumpArchiveUtil.getInt(var0, 56) + (long)(DumpArchiveUtil.getInt(var0, 60) / 1000);
      var1.handleDate(new Date(var4));
      var4 = 1000L * (long)DumpArchiveUtil.getInt(var0, 64) + (long)(DumpArchiveUtil.getInt(var0, 68) / 1000);
      var1.longValue5 = var4;
      var1.intValue7 = DumpArchiveUtil.getInt(var0, 140);
      var1.setIntValue2(DumpArchiveUtil.getInt(var0, 144));
      var1.setIntValue3(DumpArchiveUtil.getInt(var0, 148));
      DumpTapeSegmentHeader.getInt3(var2, DumpArchiveUtil.getInt(var0, 160));
      DumpTapeSegmentHeader.getInt4(var2, 0);

      for (int var6 = 0; var6 < 512 && var6 < DumpTapeSegmentHeader.getInt(var2); var6++) {
         if (var0[164 + var6] == 0) {
            DumpTapeSegmentHeader.getInt2(var2);
         }
      }

      System.arraycopy(var0, 164, DumpTapeSegmentHeader.getByteArray(var2), 0, 512);
      var1.intValue4 = var2.getIntValue();
      return var1;
   }

   @Override
   public String getName() {
      return this.string;
   }

   public String getString2() {
      return this.string2;
   }

   public void setDumpType(DumpType var1) {
      this.dumpType = var1;
   }

   public void handleInt(int var1) {
      this.intValue = var1 & 4095;
      this.set = DumpPermission.getSet(var1);
   }

   public void setLongValue2(long var1) {
      this.longValue2 = var1;
   }

   public void handleDate(Date var1) {
      this.longValue4 = var1.getTime();
   }

   public void handleDate2(Date var1) {
      this.longValue3 = var1.getTime();
   }

   public void setIntValue2(int var1) {
      this.intValue2 = var1;
   }

   public void setIntValue3(int var1) {
      this.intValue3 = var1;
   }
}
