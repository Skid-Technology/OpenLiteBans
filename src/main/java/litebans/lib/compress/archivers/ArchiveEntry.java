package litebans.lib.compress.archivers;

public interface ArchiveEntry {
   long longValue = -1L;

   String getName();

   long getSize();

   boolean isDirectory();
}
