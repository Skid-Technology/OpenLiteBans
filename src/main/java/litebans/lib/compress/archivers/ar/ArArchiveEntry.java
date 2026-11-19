package litebans.lib.compress.archivers.ar;

import litebans.lib.compress.archivers.ArchiveEntry;

public class ArArchiveEntry implements ArchiveEntry {
   @Override
   public boolean isDirectory() {
      return false;
   }

   @Override
   public String getName() {
      return this.string3;
   }

   @Override
   public long getSize() {
      return this.longValue2;
   }

   public ArArchiveEntry(String var1, long var2) {
      this.string3 = var1;
      this.longValue2 = var2;
   }

   public static final String string = "!<arch>\n";
   public static final String string2 = "`\n";
   private final String string3;
   private static final int intValue = 33188;
   private final long longValue2;

   public long getLongValue2() {
      return this.longValue2;
   }

   @Override
   public int hashCode() {
      byte var2 = 1;
      return 31 * var2 + (this.string3 == null ? 0 : this.string3.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         ArArchiveEntry var2 = (ArArchiveEntry)var1;
         return this.string3 == null ? var2.string3 == null : this.string3.equals(var2.string3);
      } else {
         return false;
      }
   }
}
