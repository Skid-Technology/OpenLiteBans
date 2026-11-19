package litebans.util;

import java.util.Comparator;
import litebans.lib.compress.archivers.dump.DumpArchiveEntry;
import litebans.lib.compress.archivers.dump.DumpArchiveInputStream;

public class SortComparator9 implements Comparator {
   public final DumpArchiveInputStream dumpArchiveInputStream;

   public SortComparator9(DumpArchiveInputStream var1) {
      this.dumpArchiveInputStream = var1;
   }

   public int getInt(DumpArchiveEntry var1, DumpArchiveEntry var2) {
      return var1.getString2() != null && var2.getString2() != null ? var1.getString2().compareTo(var2.getString2()) : Integer.MAX_VALUE;
   }

   @Override
   public int compare(Object var1, Object var2) {
      return this.getInt((DumpArchiveEntry)var1, (DumpArchiveEntry)var2);
   }
}
