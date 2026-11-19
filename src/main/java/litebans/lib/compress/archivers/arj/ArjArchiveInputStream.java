package litebans.lib.compress.archivers.arj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import litebans.lib.compress.archivers.ArchiveException;
import litebans.lib.compress.archivers.ArchiveInputStream;

public class ArjArchiveInputStream extends ArchiveInputStream {
   @Override
   public litebans.lib.compress.archivers.ArchiveEntry getArchiveEntry() {
      return null;
   }

   private static final int intValue2 = 96;
   private static final int intValue3 = 234;
   private final DataInputStream dataInputStream;
   private final String string;
   private final ArjMainHeader arjMainHeader;
   private ArjLocalFileHeader arjLocalFileHeader = null;
   private InputStream inputStream = null;

   public ArjArchiveInputStream(InputStream var1, String var2) {
      try {
      this.dataInputStream = new DataInputStream(var1);
      this.string = var2;

      try {
         this.arjMainHeader = this.getArjMainHeader();
         if ((this.arjMainHeader.arjFlags & 1) != 0) {
            throw new ArchiveException("Encrypted ARJ files are unsupported");
         } else if ((this.arjMainHeader.arjFlags & 4) != 0) {
            throw new ArchiveException("Multi-volume ARJ files are unsupported");
         }
      } catch (Exception var4) {
         if (!(var4 instanceof IOException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw new ArchiveException(var4.getMessage(), var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public ArjArchiveInputStream(InputStream var1) {
      this(var1, "CP437");
   }

   @Override
   public void close() {
      try {
      this.dataInputStream.close();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final int getInt(DataInputStream var1) {
      try {
      int var2 = var1.readUnsignedByte();
      this.handleInt(1);
      return var2;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final int getInt2(DataInputStream var1) {
      try {
      int var2 = var1.readUnsignedShort();
      this.handleInt(2);
      return Integer.reverseBytes(var2) >>> 16;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final int getInt3(DataInputStream var1) {
      try {
      int var2 = var1.readInt();
      this.handleInt(4);
      return Integer.reverseBytes(var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final String getString(DataInputStream var1) {
      try {
      String var5;
      try (ByteArrayOutputStream var2 = new ByteArrayOutputStream()) {
         int var4;
         while ((var4 = var1.readUnsignedByte()) != 0) {
            var2.write(var4);
         }

         if (this.string == null) {
            return new String(var2.toByteArray());
         }

         var5 = new String(var2.toByteArray(), this.string);
      }

      return var5;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleDataInputStream(DataInputStream var1, byte[] var2) {
      try {
      var1.readFully(var2);
      this.handleInt(var2.length);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final byte[] getByteArray() {
      boolean var1 = false;
      byte[] var2 = null;

      do {
         int var4 = this.getInt(this.dataInputStream);

         int var3;
         do {
            var3 = var4;
            var4 = this.getInt(this.dataInputStream);
         } while (var3 != 96 && var4 != 234);

         int var5 = this.getInt2(this.dataInputStream);
         if (var5 == 0) {
            return null;
         }

         if (var5 <= 2600) {
            var2 = new byte[var5];
            this.handleDataInputStream(this.dataInputStream, var2);
            long var6 = (long)this.getInt3(this.dataInputStream) & 4294967295L;
            CRC32 var8 = new CRC32();
            var8.update(var2);
            if (var6 == var8.getValue()) {
               var1 = true;
            }
         }
      } while (!var1);

      return var2;
   }

   private final ArjMainHeader getArjMainHeader() {
      try {
      byte[] var1 = this.getByteArray();
      if (var1 == null) {
         throw new IOException("Archive ends without any headers");
      } else {
         DataInputStream var2 = new DataInputStream(new ByteArrayInputStream(var1));
         int var3 = var2.readUnsignedByte();
         byte[] var4 = new byte[var3 - 1];
         var2.readFully(var4);
         DataInputStream var5 = new DataInputStream(new ByteArrayInputStream(var4));
         ArjMainHeader var6 = new ArjMainHeader();
         var6.intValue = var5.readUnsignedByte();
         var6.minVersionToExtract = var5.readUnsignedByte();
         var6.hostOS = var5.readUnsignedByte();
         var6.arjFlags = var5.readUnsignedByte();
         var6.securityVersion = var5.readUnsignedByte();
         var6.fileType = var5.readUnsignedByte();
         var6.reserved = var5.readUnsignedByte();
         var6.dateTimeCreated = this.getInt3(var5);
         var6.dateTimeModified = this.getInt3(var5);
         var6.archiveSize = 4294967295L & (long)this.getInt3(var5);
         var6.securityEnvelopeFilePosition = this.getInt3(var5);
         var6.fileSpecPosition = this.getInt2(var5);
         var6.securityEnvelopeLength = this.getInt2(var5);
         this.handleLong2(20L);
         var6.encryptionVersion = var5.readUnsignedByte();
         var6.lastChapter = var5.readUnsignedByte();
         if (var3 >= 33) {
            var6.arjProtectionFactor = var5.readUnsignedByte();
            var6.arjFlags2 = var5.readUnsignedByte();
            var5.readUnsignedByte();
            var5.readUnsignedByte();
         }

         var6.name = this.getString(var2);
         var6.comment = this.getString(var2);
         int var7 = this.getInt2(this.dataInputStream);
         if (var7 > 0) {
            var6.extendedHeaderBytes = new byte[var7];
            this.handleDataInputStream(this.dataInputStream, var6.extendedHeaderBytes);
            long var8 = 4294967295L & (long)this.getInt3(this.dataInputStream);
            CRC32 var10 = new CRC32();
            var10.update(var6.extendedHeaderBytes);
            if (var8 != var10.getValue()) {
               throw new IOException("Extended header CRC32 verification failure");
            }
         }

         return var6;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      try {
      if (var3 == 0) {
         return 0;
      } else if (this.arjLocalFileHeader == null) {
         throw new IllegalStateException("No current arj entry");
      } else if (this.arjLocalFileHeader.method != 0) {
         throw new IOException("Unsupported compression method " + this.arjLocalFileHeader.method);
      } else {
         return this.inputStream.read(var1, var2, var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
