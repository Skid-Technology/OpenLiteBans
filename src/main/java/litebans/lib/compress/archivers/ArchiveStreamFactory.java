package litebans.lib.compress.archivers;

import java.io.InputStream;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import litebans.lib.compress.archivers.ar.ArArchiveInputStream;
import litebans.lib.compress.archivers.arj.ArjArchiveInputStream;
import litebans.lib.compress.archivers.cpio.CpioArchiveInputStream;
import litebans.lib.compress.archivers.dump.DumpArchiveInputStream;
import litebans.lib.compress.archivers.tar.TarArchiveInputStream;
import litebans.lib.compress.archivers.zip.JarArchiveInputStream;
import litebans.lib.compress.archivers.zip.ZipArchiveInputStream;
import litebans.lib.compress.utils.CommonsLists;
import litebans.lib.compress.utils.CommonsSets;
import litebans.lib.compress.utils.ServiceLoaderIterator;

public class ArchiveStreamFactory implements ArchiveStreamProvider {
   private static final int intValue = 512;
   private static final int intValue2 = 32;
   private static final int intValue3 = 12;
   private static final ArchiveStreamFactory INSTANCE = new ArchiveStreamFactory();
   public static final String string = "ar";
   public static final String string2 = "arj";
   public static final String string3 = "cpio";
   public static final String string4 = "dump";
   public static final String string5 = "jar";
   public static final String string6 = "tar";
   public static final String string7 = "zip";
   public static final String string8 = "7z";
   private final String string9;
   private volatile String string10;
   private SortedMap sortedMap;

   private static final ArrayList getArrayList() {
      return CommonsLists.getArrayList(getIterator());
   }

   public static final void handleSet(Set var0, ArchiveStreamProvider var1, TreeMap var2) {
      for (Object var4o : var0) {
         String var4 = (String) var4o;
         var2.put(getString(var4), var1);
      }
   }

   private static final Iterator getIterator() {
      return new ServiceLoaderIterator(ArchiveStreamProvider.class);
   }

   private static final String getString(String var0) {
      return var0.toUpperCase(Locale.ROOT);
   }

   public static final SortedMap getSortedMap() {
      return /* cast-fix */(SortedMap)(AccessController.doPrivileged(new ArchiveProviderLoader()));
   }

   public ArchiveStreamFactory() {
      this(null);
   }

   public ArchiveStreamFactory(String var1) {
      this.string9 = var1;
      this.string10 = var1;
   }

   public ArchiveInputStream getArchiveInputStream(String var1, InputStream var2) {
      return this.getArchiveInputStream(var1, var2, this.string10);
   }

   @Override
   public ArchiveInputStream getArchiveInputStream(String var1, InputStream var2, String var3) {
      try {
      if (var1 == null) {
         throw new IllegalArgumentException("Archivername must not be null.");
      } else if (var2 == null) {
         throw new IllegalArgumentException("InputStream must not be null.");
      } else if ("ar".equalsIgnoreCase(var1)) {
         return new ArArchiveInputStream(var2);
      } else if ("arj".equalsIgnoreCase(var1)) {
         return var3 != null ? new ArjArchiveInputStream(var2, var3) : new ArjArchiveInputStream(var2);
      } else if ("zip".equalsIgnoreCase(var1)) {
         return var3 != null ? new ZipArchiveInputStream(var2, var3) : new ZipArchiveInputStream(var2);
      } else if ("tar".equalsIgnoreCase(var1)) {
         return var3 != null ? new TarArchiveInputStream(var2, var3) : new TarArchiveInputStream(var2);
      } else if ("jar".equalsIgnoreCase(var1)) {
         return var3 != null ? new JarArchiveInputStream(var2, var3) : new JarArchiveInputStream(var2);
      } else if ("cpio".equalsIgnoreCase(var1)) {
         return var3 != null ? new CpioArchiveInputStream(var2, var3) : new CpioArchiveInputStream(var2);
      } else if ("dump".equalsIgnoreCase(var1)) {
         return var3 != null ? new DumpArchiveInputStream(var2, var3) : new DumpArchiveInputStream(var2);
      } else if ("7z".equalsIgnoreCase(var1)) {
         throw new StreamingNotSupportedException("7z");
      } else {
         ArchiveStreamProvider var4 = (ArchiveStreamProvider)this.getSortedMap2().get(getString(var1));
         if (var4 != null) {
            return var4.getArchiveInputStream(var1, var2, var3);
         } else {
            throw new ArchiveException("Archiver: " + var1 + " not found.");
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public SortedMap getSortedMap2() {
      if (this.sortedMap == null) {
         this.sortedMap = Collections.unmodifiableSortedMap(getSortedMap());
      }

      return this.sortedMap;
   }

   @Override
   public Set getSet() {
      return CommonsSets.getHashSet(new String[]{"ar", "arj", "zip", "tar", "jar", "cpio", "dump", "7z"});
   }

   public static final ArchiveStreamFactory getInstance() {
      return INSTANCE;
   }

   public static final ArrayList getArrayList2() {
      return getArrayList();
   }
}
