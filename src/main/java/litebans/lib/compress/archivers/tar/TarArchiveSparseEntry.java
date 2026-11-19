package litebans.lib.compress.archivers.tar;

import java.util.ArrayList;
import java.util.List;

public class TarArchiveSparseEntry implements TarConstants {
   private final boolean flag;
   private List list;

   public TarArchiveSparseEntry(byte[] var1) {
      int var2 = 0;
      this.list = new ArrayList();

      for (int var3 = 0; var3 < 21; var3++) {
         TarArchiveStructSparse var4 = TarUtils.getTarArchiveStructSparse(var1, var2 + var3 * 24);
         if (var4.getOffset() > 0L || var4.getNumbytes() > 0L) {
            this.list.add(var4);
         }
      }

      var2 += 504;
      this.flag = TarUtils.checkByteArray(var1, var2);
   }

   public boolean isFlag() {
      return this.flag;
   }

   public List getList() {
      return this.list;
   }
}
