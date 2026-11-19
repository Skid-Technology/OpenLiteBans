package litebans.util;

import java.util.Comparator;
import litebans.lib.compress.archivers.tar.TarArchiveInputStream;
import litebans.lib.compress.archivers.tar.TarArchiveStructSparse;

public class SortComparator1 implements Comparator {
   public final TarArchiveInputStream tarArchiveInputStream;

   public SortComparator1(TarArchiveInputStream var1) {
      this.tarArchiveInputStream = var1;
   }

   public int getInt(TarArchiveStructSparse var1, TarArchiveStructSparse var2) {
      Long var3 = var1.getOffset();
      Long var4 = var2.getOffset();
      return var3.compareTo(var4);
   }

   @Override
   public int compare(Object var1, Object var2) {
      return this.getInt((TarArchiveStructSparse)var1, (TarArchiveStructSparse)var2);
   }
}
