package litebans.lib.compress.archivers.tar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import litebans.lib.compress.archivers.ArchiveEntry;
import litebans.lib.compress.archivers.ArchiveInputStream;
import litebans.lib.compress.archivers.ArchiveUtils;
import litebans.lib.compress.archivers.zip.ZipEncoding;
import litebans.lib.compress.archivers.zip.ZipEncodingHelper;
import litebans.lib.compress.utils.BoundedInputStream;
import litebans.lib.compress.utils.EmptyInputStream;
import litebans.lib.compress.utils.IOUtils;
import litebans.util.SortComparator1;

public class TarArchiveInputStream extends ArchiveInputStream {
   private static final int intValue2 = 256;
   private final byte[] byteArray2 = new byte[256];
   private final int intValue3;
   private final int intValue4;
   private boolean flag;
   private long longValue2;
   private long longValue3;
   private final InputStream inputStream;
   private List list;
   private int intValue5;
   private TarArchiveEntry tarArchiveEntry;
   private final ZipEncoding zipEncoding;
   public final String string;
   private Map map = new HashMap();
   private final List list2 = new ArrayList();
   private final boolean flag2;

   public TarArchiveInputStream(InputStream var1) {
      this(var1, 10240, 512);
   }

   public TarArchiveInputStream(InputStream var1, String var2) {
      this(var1, 10240, 512, var2);
   }

   public TarArchiveInputStream(InputStream var1, int var2, int var3) {
      this(var1, var2, var3, null);
   }

   public TarArchiveInputStream(InputStream var1, int var2, int var3, String var4) {
      this(var1, var2, var3, var4, false);
   }

   public TarArchiveInputStream(InputStream var1, int var2, int var3, String var4, boolean var5) {
      this.inputStream = var1;
      this.flag = false;
      this.string = var4;
      this.zipEncoding = ZipEncodingHelper.getZipEncoding(var4);
      this.intValue3 = var3;
      this.intValue4 = var2;
      this.flag2 = var5;
   }

   @Override
   public void close() throws IOException {
      if (this.list != null) {
         for (Object var2o : this.list) {
            InputStream var2 = (InputStream) var2o;
            var2.close();
         }
      }

      this.inputStream.close();
   }

   @Override
   public int available() {
      if (this.check()) {
         return 0;
      } else {
         return this.tarArchiveEntry.getLong() - this.longValue3 > 2147483647L ? Integer.MAX_VALUE : (int)(this.tarArchiveEntry.getLong() - this.longValue3);
      }
   }

   @Override
   public long skip(long var1) throws IOException {
      if (var1 > 0L && !this.check()) {
         long var3 = this.tarArchiveEntry.getLong() - this.longValue3;
         long var5;
         if (!this.tarArchiveEntry.check7()) {
            var5 = IOUtils.getLong(this.inputStream, Math.min(var1, var3));
         } else {
            var5 = this.getLong(Math.min(var1, var3));
         }

         this.handleLong(var5);
         this.longValue3 += var5;
         return var5;
      } else {
         return 0L;
      }
   }

   private final long getLong(long var1) throws IOException {
      if (this.list != null && this.list.size() != 0) {
         long var3 = 0L;

         while (var3 < var1 && this.intValue5 < this.list.size()) {
            InputStream var5 = (InputStream)this.list.get(this.intValue5);
            var3 += var5.skip(var1 - var3);
            if (var3 < var1) {
               this.intValue5++;
            }
         }

         return var3;
      } else {
         return this.inputStream.skip(var1);
      }
   }

   @Override
   public boolean markSupported() {
      return false;
   }

   @Override
   public synchronized void mark(int var1) {
   }

   @Override
   public synchronized void reset() {
   }

   public TarArchiveEntry getTarArchiveEntry() throws IOException {
      if (this.isFlag()) {
         return null;
      } else {
         if (this.tarArchiveEntry != null) {
            IOUtils.getLong(this, Long.MAX_VALUE);
            this.run();
         }

         byte[] var1 = this.getByteArray2();
         if (var1 == null) {
            this.tarArchiveEntry = null;
            return null;
         } else {
            try {
               this.tarArchiveEntry = new TarArchiveEntry(var1, this.zipEncoding, this.flag2);
            } catch (IllegalArgumentException var3) {
               throw new IOException("Error detected parsing the header", var3);
            }

            this.longValue3 = 0L;
            this.longValue2 = this.tarArchiveEntry.getSize();
            if (this.tarArchiveEntry.check3()) {
               byte[] var2 = this.getByteArray();
               if (var2 == null) {
                  return null;
               }

               this.tarArchiveEntry.setString11(this.zipEncoding.getString(var2));
            }

            if (this.tarArchiveEntry.check4()) {
               byte[] var4 = this.getByteArray();
               if (var4 == null) {
                  return null;
               }

               this.tarArchiveEntry.handleString(this.zipEncoding.getString(var4));
            }

            if (this.tarArchiveEntry.check6()) {
               this.run2();
            }

            if (this.tarArchiveEntry.check5()) {
               this.run3();
            } else if (!this.map.isEmpty()) {
               this.handleMap(this.map, this.list2);
            }

            if (this.tarArchiveEntry.check2()) {
               this.run4();
            }

            this.longValue2 = this.tarArchiveEntry.getSize();
            return this.tarArchiveEntry;
         }
      }
   }

   private final void run() {
      if (!this.check() && this.longValue2 > 0L && this.longValue2 % (long)this.intValue3 != 0L) {
         long var1 = this.longValue2 / (long)this.intValue3 + 1L;
         long var3 = var1 * (long)this.intValue3 - this.longValue2;
         long var5 = IOUtils.getLong(this.inputStream, var3);
         this.handleLong(var5);
      }
   }

   public byte[] getByteArray() throws IOException {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();

      int var2;
      while ((var2 = this.read(this.byteArray2)) >= 0) {
         var1.write(this.byteArray2, 0, var2);
      }

      this.getArchiveEntry();
      if (this.tarArchiveEntry == null) {
         return null;
      } else {
         byte[] var3 = var1.toByteArray();
         var2 = var3.length;

         while (var2 > 0 && var3[var2 - 1] == 0) {
            var2--;
         }

         if (var2 != var3.length) {
            byte[] var4 = new byte[var2];
            System.arraycopy(var3, 0, var4, 0, var2);
            var3 = var4;
         }

         return var3;
      }
   }

   private final byte[] getByteArray2() throws IOException {
      byte[] var1 = this.getByteArray3();
      this.setFlag(this.checkByteArray(var1));
      if (this.isFlag() && var1 != null) {
         this.run5();
         this.run6();
         var1 = null;
      }

      return var1;
   }

   public boolean checkByteArray(byte[] var1) {
      return var1 == null || ArchiveUtils.checkByteArray(var1, this.intValue3);
   }

   public byte[] getByteArray3() {
      byte[] var1 = new byte[this.intValue3];
      int var2 = IOUtils.getInt(this.inputStream, var1);
      this.handleInt(var2);
      return var2 != this.intValue3 ? null : var1;
   }

   private final void run2() throws IOException {
      this.map = this.getMap(this, this.list2);
      this.getArchiveEntry();
   }

   private final void run3() throws IOException {
      Object var1 = new ArrayList();
      Map var2 = this.getMap(this, (List)var1);
      if (var2.containsKey("GNU.sparse.map")) {
         var1 = this.getList((String)var2.get("GNU.sparse.map"));
      }

      this.getArchiveEntry();
      this.handleMap(var2, (List)var1);
      if (this.tarArchiveEntry.isFlag5()) {
         var1 = this.getList();
         this.tarArchiveEntry.setList((List)var1);
      }

      this.run7();
   }

   private final List getList(String var1) {
      ArrayList var2 = new ArrayList();
      String[] var3 = var1.split(",");

      for (byte var4 = 0; var4 < var3.length; var4 += 2) {
         long var5 = Long.parseLong(var3[var4]);
         long var7 = Long.parseLong(var3[var4 + 1]);
         var2.add(new TarArchiveStructSparse(var5, var7));
      }

      return var2;
   }

   private final List getList() throws IOException {
      ArrayList var1 = new ArrayList();
      long var2 = 0L;
      long[] var4 = this.getLongArray(this.inputStream);
      long var5 = var4[0];
      var2 += var4[1];

      while (var5-- > 0L) {
         var4 = this.getLongArray(this.inputStream);
         long var7 = var4[0];
         var2 += var4[1];
         var4 = this.getLongArray(this.inputStream);
         long var9 = var4[0];
         var2 += var4[1];
         var1.add(new TarArchiveStructSparse(var7, var9));
      }

      long var15 = (long)this.intValue3 - var2 % (long)this.intValue3;
      IOUtils.getLong(this.inputStream, var15);
      return var1;
   }

   private final long[] getLongArray(InputStream var1) throws IOException {
      long var3 = 0L;
      long var5 = 0L;

      int var2;
      while ((var2 = var1.read()) != 10) {
         var5++;
         if (var2 == -1) {
            throw new IOException("Unexpected EOF when reading parse information of 1.X PAX format");
         }

         var3 = var3 * 10L + (long)(var2 - 48);
      }

      return new long[]{var3, ++var5};
   }

   public Map getMap(InputStream var1, List var2) throws IOException {
      HashMap var3 = new HashMap(this.map);
      Long var4 = null;

      int var5;
      do {
         int var6 = 0;
         int var7 = 0;

         while ((var5 = var1.read()) != -1) {
            var7++;
            if (var5 == 10) {
               break;
            }

            if (var5 == 32) {
               ByteArrayOutputStream var8 = new ByteArrayOutputStream();

               while ((var5 = var1.read()) != -1) {
                  var7++;
                  if (var5 == 61) {
                     String var9 = var8.toString("UTF-8");
                     int var10 = var6 - var7;
                     if (var10 == 1) {
                        var3.remove(var9);
                     } else {
                        byte[] var11 = new byte[var10];
                        int var12 = IOUtils.getInt(var1, var11);
                        if (var12 != var10) {
                           throw new IOException("Failed to read Paxheader. Expected " + var10 + " bytes, read " + var12);
                        }

                        String var13 = new String(var11, 0, var10 - 1, "UTF-8");
                        var3.put(var9, var13);
                        if (var9.equals("GNU.sparse.offset")) {
                           if (var4 != null) {
                              var2.add(new TarArchiveStructSparse(var4, 0L));
                           }

                           var4 = Long.valueOf(var13);
                        }

                        if (var9.equals("GNU.sparse.numbytes")) {
                           if (var4 == null) {
                              throw new IOException("Failed to read Paxheader.GNU.sparse.offset is expected before GNU.sparse.numbytes shows up.");
                           }

                           var2.add(new TarArchiveStructSparse(var4, Long.parseLong(var13)));
                           var4 = null;
                        }
                     }
                     break;
                  }

                  var8.write((byte)var5);
               }
               break;
            }

            var6 *= 10;
            var6 += var5 - 48;
         }
      } while (var5 != -1);

      if (var4 != null) {
         var2.add(new TarArchiveStructSparse(var4, 0L));
      }

      return var3;
   }

   private final void handleMap(Map var1, List var2) {
      this.tarArchiveEntry.handleMap(var1);
      this.tarArchiveEntry.setList(var2);
   }

   private final void run4() throws IOException {
      TarArchiveSparseEntry var1;
      if (this.tarArchiveEntry.isFlag3()) {
         do {
            byte[] var2 = this.getByteArray2();
            if (var2 == null) {
               this.tarArchiveEntry = null;
               break;
            }

            var1 = new TarArchiveSparseEntry(var2);
            this.tarArchiveEntry.getList().addAll(var1.getList());
         } while (var1.isFlag());
      }

      this.run7();
   }

   private final boolean check() {
      return this.tarArchiveEntry != null && this.tarArchiveEntry.isDirectory();
   }

   @Override
   public ArchiveEntry getArchiveEntry() throws IOException {
      return this.getTarArchiveEntry();
   }

   private final void run5() throws IOException {
      boolean var1 = true;
      boolean var2 = this.inputStream.markSupported();
      if (var2) {
         this.inputStream.mark(this.intValue3);
      }

      try {
         var1 = !this.checkByteArray(this.getByteArray3());
      } finally {
         if (var1 && var2) {
            this.handleLong2((long)this.intValue3);
            this.inputStream.reset();
         }
      }
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws IOException {
      if (var3 == 0) {
         return 0;
      } else if (this.isFlag() || this.check()) {
         return -1;
      } else if (this.tarArchiveEntry == null) {
         throw new IllegalStateException("No current tar entry");
      } else {
         if (!this.tarArchiveEntry.check7()) {
            if (this.longValue3 >= this.longValue2) {
               return -1;
            }
         } else if (this.longValue3 >= this.tarArchiveEntry.getLong()) {
            return -1;
         }

         var3 = Math.min(var3, this.available());
         int var4;
         if (this.tarArchiveEntry.check7()) {
            var4 = this.getInt(var1, var2, var3);
         } else {
            var4 = this.inputStream.read(var1, var2, var3);
         }

         if (var4 == -1) {
            if (var3 > 0) {
               throw new IOException("Truncated TAR archive");
            }

            this.setFlag(true);
         } else {
            this.handleInt(var4);
            this.longValue3 += (long)var4;
         }

         return var4;
      }
   }

   private final int getInt(byte[] var1, int var2, int var3) throws IOException {
      if (this.list != null && this.list.size() != 0) {
         if (this.intValue5 >= this.list.size()) {
            return -1;
         } else {
            InputStream var4 = (InputStream)this.list.get(this.intValue5);
            int var5 = var4.read(var1, var2, var3);
            if (this.intValue5 == this.list.size() - 1) {
               return var5;
            } else if (var5 == -1) {
               this.intValue5++;
               return this.getInt(var1, var2, var3);
            } else if (var5 < var3) {
               this.intValue5++;
               int var6 = this.getInt(var1, var2 + var5, var3 - var5);
               return var6 == -1 ? var5 : var5 + var6;
            } else {
               return var5;
            }
         }
      } else {
         return this.inputStream.read(var1, var2, var3);
      }
   }

   public final boolean isFlag() {
      return this.flag;
   }

   public final void setFlag(boolean var1) {
      this.flag = var1;
   }

   private final void run6() {
      long var1 = this.getLongValue() % (long)this.intValue4;
      if (var1 > 0L) {
         long var3 = IOUtils.getLong(this.inputStream, (long)this.intValue4 - var1);
         this.handleLong(var3);
      }
   }

   private final void run7() throws IOException {
      this.intValue5 = -1;
      this.list = new ArrayList();
      List var1 = this.tarArchiveEntry.getList();
      if (var1 != null && var1.size() > 1) {
         SortComparator1 var2 = new SortComparator1(this);
         Collections.sort(var1, var2);
      }

      if (var1 != null) {
         EmptyInputStream var7 = new EmptyInputStream(null);
         long var3 = 0L;

         for (Object var6o : var1) {
            TarArchiveStructSparse var6 = (TarArchiveStructSparse) var6o;
            if (var6.getOffset() == 0L && var6.getNumbytes() == 0L) {
               break;
            }

            if (var6.getOffset() - var3 < 0L) {
               throw new IOException("Corrupted struct sparse detected");
            }

            if (var6.getOffset() - var3 > 0L) {
               this.list.add(new BoundedInputStream(var7, var6.getOffset() - var3));
            }

            if (var6.getNumbytes() > 0L) {
               this.list.add(new BoundedInputStream(this.inputStream, var6.getNumbytes()));
            }

            var3 = var6.getOffset() + var6.getNumbytes();
         }
      }

      if (this.list.size() > 0) {
         this.intValue5 = 0;
      }
   }
}
