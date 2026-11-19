package litebans.lib.compress.archivers;

import java.security.PrivilegedAction;
import java.util.SortedMap;
import java.util.TreeMap;

public final class ArchiveProviderLoader implements PrivilegedAction {
   public SortedMap getSortedMap() {
      TreeMap var1 = new TreeMap();
      ArchiveStreamFactory.handleSet(ArchiveStreamFactory.getInstance().getSet(), ArchiveStreamFactory.getInstance(), var1);

      for (Object var3o : ArchiveStreamFactory.getArrayList2()) {
         ArchiveStreamProvider var3 = (ArchiveStreamProvider) var3o;
         ArchiveStreamFactory.handleSet(var3.getSet(), var3, var1);
      }

      return var1;
   }

   @Override
   public Object run() {
      return this.getSortedMap();
   }
}
