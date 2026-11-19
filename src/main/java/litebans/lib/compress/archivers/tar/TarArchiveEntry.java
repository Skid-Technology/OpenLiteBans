package litebans.lib.compress.archivers.tar;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import litebans.lib.compress.archivers.ArchiveEntry;
import litebans.lib.compress.archivers.ArchiveUtils;
import litebans.lib.compress.archivers.zip.ZipEncoding;

public class TarArchiveEntry implements ArchiveEntry, TarConstants {
   private static final TarArchiveEntry[] tarArchiveEntryArray = new TarArchiveEntry[0];
   public static final long longValue3 = -1L;
   private String string10 = "";
   private final boolean flag;
   private int intValue41;
   private long longValue4 = 0L;
   private long longValue5 = 0L;
   private long longValue6 = 0L;
   private long longValue7;
   private boolean flag2;
   private byte byteValue16;
   private String string11 = "";
   private String string12 = "ustar\u0000";
   private String string13 = "00";
   private String string14;
   private String string15 = "";
   private int intValue42 = 0;
   private int intValue43 = 0;
   private List list;
   private boolean flag3;
   private long longValue8;
   private boolean flag4;
   private boolean flag5 = false;
   private boolean flag6;
   private final File file;
   private final Map map = new HashMap();
   public static final int intValue44 = 31;
   public static final int intValue45 = 16877;
   public static final int intValue46 = 33188;
   public static final int intValue47 = 1000;

   private TarArchiveEntry(boolean var1) {
      String var2 = System.getProperty("user.name", "");
      if (var2.length() > 31) {
         var2 = var2.substring(0, 31);
      }

      this.string14 = var2;
      this.file = null;
      this.flag = var1;
   }

   public TarArchiveEntry(byte[] var1, ZipEncoding var2, boolean var3) {
      this(false);
      this.handleByteArray(var1, var2, false, var3);
   }

   public boolean checkTarArchiveEntry(TarArchiveEntry var1) {
      return var1 != null && this.getName().equals(var1.getName());
   }

   @Override
   public boolean equals(Object var1) {
      return var1 != null && this.getClass() == var1.getClass() ? this.checkTarArchiveEntry((TarArchiveEntry)var1) : false;
   }

   @Override
   public int hashCode() {
      return this.getName().hashCode();
   }

   @Override
   public String getName() {
      return this.string10;
   }

   public void handleString(String var1) {
      this.string10 = getString(var1, this.flag);
   }

   public void setString11(String var1) {
      this.string11 = var1;
   }

   public void setLongValue4(long var1) {
      this.longValue4 = var1;
   }

   public void setLongValue5(long var1) {
      this.longValue5 = var1;
   }

   public void setString14(String var1) {
      this.string14 = var1;
   }

   public void setString15(String var1) {
      this.string15 = var1;
   }

   public void handleLong(long var1) {
      this.longValue7 = var1 / 1000L;
   }

   @Override
   public long getSize() {
      return this.longValue6;
   }

   public void setList(List var1) {
      this.list = var1;
   }

   public List getList() {
      return this.list;
   }

   public boolean isFlag5() {
      return this.flag5;
   }

   public void handleLong2(long var1) {
      if (var1 < 0L) {
         throw new IllegalArgumentException("Size is out of range: " + var1);
      } else {
         this.longValue6 = var1;
      }
   }

   public void handleInt(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Major device number is out of range: " + var1);
      } else {
         this.intValue42 = var1;
      }
   }

   public void handleInt2(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Minor device number is out of range: " + var1);
      } else {
         this.intValue43 = var1;
      }
   }

   public boolean isFlag3() {
      return this.flag3;
   }

   public long getLong() {
      return !this.check7() ? this.longValue6 : this.longValue8;
   }

   public boolean check() {
      return this.check2() || this.isFlag4();
   }

   public boolean check2() {
      return this.byteValue16 == 83;
   }

   public boolean isFlag4() {
      return this.flag4;
   }

   public boolean isFlag6() {
      return this.flag6;
   }

   public boolean check3() {
      return this.byteValue16 == 75;
   }

   public boolean check4() {
      return this.byteValue16 == 76;
   }

   public boolean check5() {
      return this.byteValue16 == 120 || this.byteValue16 == 88;
   }

   public boolean check6() {
      return this.byteValue16 == 103;
   }

   @Override
   public boolean isDirectory() {
      if (this.file != null) {
         return this.file.isDirectory();
      } else {
         return this.byteValue16 == 53 ? true : !this.check5() && !this.check6() && this.getName().endsWith("/");
      }
   }

   public boolean check7() {
      return this.check() || this.isFlag6();
   }

   public void handleMap(Map var1) {
      for (Object var3o : var1.entrySet()) {
         Entry var3 = (Entry) var3o;
         String var4 = (String)var3.getKey();
         String var5 = (String)var3.getValue();
         this.handleString(var4, var5, var1);
      }
   }

   private final void handleString(String var1, String var2, Map var3) {
      switch (var1) {
         case "path":
            this.handleString(var2);
            break;
         case "linkpath":
            this.setString11(var2);
            break;
         case "gid":
            this.setLongValue5(Long.parseLong(var2));
            break;
         case "gname":
            this.setString15(var2);
            break;
         case "uid":
            this.setLongValue4(Long.parseLong(var2));
            break;
         case "uname":
            this.setString14(var2);
            break;
         case "size":
            this.handleLong2(Long.parseLong(var2));
            break;
         case "mtime":
            this.handleLong((long)(Double.parseDouble(var2) * 1000.0));
            break;
         case "SCHILY.devminor":
            this.handleInt2(Integer.parseInt(var2));
            break;
         case "SCHILY.devmajor":
            this.handleInt(Integer.parseInt(var2));
            break;
         case "GNU.sparse.size":
            this.handleMap2(var3);
            break;
         case "GNU.sparse.realsize":
            this.handleMap3(var3);
            break;
         case "SCHILY.filetype":
            if ("sparse".equals(var2)) {
               this.handleMap4(var3);
            }
            break;
         default:
            this.map.put(var1, var2);
      }
   }

   private final void handleByteArray(byte[] var1, ZipEncoding var2, boolean var3, boolean var4) {
      int var5 = 0;
      this.string10 = var3 ? TarUtils.getString(var1, var5, 100) : TarUtils.getString(var1, var5, 100, var2);
      var5 += 100;
      this.intValue41 = (int)this.getLong(var1, var5, 8, var4);
      var5 += 8;
      this.longValue4 = (long)((int)this.getLong(var1, var5, 8, var4));
      var5 += 8;
      this.longValue5 = (long)((int)this.getLong(var1, var5, 8, var4));
      var5 += 8;
      this.longValue6 = TarUtils.getLong2(var1, var5, 12);
      var5 += 12;
      this.longValue7 = this.getLong(var1, var5, 12, var4);
      var5 += 12;
      this.flag2 = TarUtils.checkByteArray(var1);
      var5 += 8;
      this.byteValue16 = var1[var5++];
      this.string11 = var3 ? TarUtils.getString(var1, var5, 100) : TarUtils.getString(var1, var5, 100, var2);
      var5 += 100;
      this.string12 = TarUtils.getString(var1, var5, 6);
      var5 += 6;
      this.string13 = TarUtils.getString(var1, var5, 2);
      var5 += 2;
      this.string14 = var3 ? TarUtils.getString(var1, var5, 32) : TarUtils.getString(var1, var5, 32, var2);
      var5 += 32;
      this.string15 = var3 ? TarUtils.getString(var1, var5, 32) : TarUtils.getString(var1, var5, 32, var2);
      var5 += 32;
      if (this.byteValue16 != 51 && this.byteValue16 != 52) {
         var5 += 16;
      } else {
         this.intValue42 = (int)this.getLong(var1, var5, 8, var4);
         var5 += 8;
         this.intValue43 = (int)this.getLong(var1, var5, 8, var4);
         var5 += 8;
      }

      int var6 = this.getInt(var1);
      switch (var6) {
         case 2:
            var5 += 12;
            var5 += 12;
            var5 += 12;
            var5 += 4;
            var5++;
            this.list = new ArrayList();

            for (int var33 = 0; var33 < 4; var33++) {
               TarArchiveStructSparse var8 = TarUtils.getTarArchiveStructSparse(var1, var5 + var33 * 24);
               if (var8.getOffset() > 0L || var8.getNumbytes() > 0L) {
                  this.list.add(var8);
               }
            }

            var5 += 96;
            this.flag3 = TarUtils.checkByteArray(var1, var5);
            this.longValue8 = TarUtils.getLong(var1, ++var5, 12);
            var5 += 12;
            break;
         case 3:
         default:
            String var32 = var3 ? TarUtils.getString(var1, var5, 155) : TarUtils.getString(var1, var5, 155, var2);
            if (this.isDirectory() && !this.string10.endsWith("/")) {
               this.string10 = this.string10 + "/";
            }

            if (var32.length() > 0) {
               this.string10 = var32 + "/" + this.string10;
            }
            break;
         case 4:
            String var7 = var3 ? TarUtils.getString(var1, var5, 131) : TarUtils.getString(var1, var5, 131, var2);
            if (var7.length() > 0) {
               this.string10 = var7 + "/" + this.string10;
            }
      }
   }

   private final long getLong(byte[] var1, int var2, int var3, boolean var4) {
      if (var4) {
         try {
            return TarUtils.getLong2(var1, var2, var3);
         } catch (IllegalArgumentException var5) {
            return -1L;
         }
      } else {
         return TarUtils.getLong2(var1, var2, var3);
      }
   }

   private static final String getString(String var0, boolean var1) {
      if (!var1) {
         String var2 = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
         if (var2 != null) {
            if (var2.startsWith("windows")) {
               if (var0.length() > 2) {
                  char var3 = var0.charAt(0);
                  char var4 = var0.charAt(1);
                  if (var4 == ':' && (var3 >= 'a' && var3 <= 'z' || var3 >= 'A' && var3 <= 'Z')) {
                     var0 = var0.substring(2);
                  }
               }
            } else if (var2.contains("netware")) {
               int var6 = var0.indexOf(58);
               if (var6 != -1) {
                  var0 = var0.substring(var6 + 1);
               }
            }
         }
      }

      var0 = var0.replace(File.separatorChar, '/');

      while (!var1 && var0.startsWith("/")) {
         var0 = var0.substring(1);
      }

      return var0;
   }

   private final int getInt(byte[] var1) {
      if (ArchiveUtils.checkString("ustar ", var1, 257, 6)) {
         return 2;
      } else if (ArchiveUtils.checkString("ustar\u0000", var1, 257, 6)) {
         return ArchiveUtils.checkString("tar\u0000", var1, 508, 4) ? 4 : 3;
      } else {
         return 0;
      }
   }

   public void handleMap2(Map var1) {
      this.flag4 = true;
      this.longValue8 = (long)Integer.parseInt((String)var1.get("GNU.sparse.size"));
      if (var1.containsKey("GNU.sparse.name")) {
         this.string10 = (String)var1.get("GNU.sparse.name");
      }
   }

   public void handleMap3(Map var1) {
      this.flag4 = true;
      this.flag5 = true;
      this.longValue8 = (long)Integer.parseInt((String)var1.get("GNU.sparse.realsize"));
      this.string10 = (String)var1.get("GNU.sparse.name");
   }

   public void handleMap4(Map var1) {
      this.flag6 = true;
      if (var1.containsKey("SCHILY.realsize")) {
         this.longValue8 = Long.parseLong((String)var1.get("SCHILY.realsize"));
      }
   }
}
