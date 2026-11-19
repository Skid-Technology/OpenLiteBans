package litebans.lib.compress.archivers.arj;

import java.util.Arrays;
import java.util.Objects;

public class ArjLocalFileHeader {
   public int intValue;
   public int minVersionToExtract;
   public int hostOS;
   public int arjFlags;
   public int method;
   public int fileType;
   public int reserved;
   public int dateTimeModified;
   public long compressedSize;
   public long originalSize;
   public long originalCrc32;
   public int fileSpecPosition;
   public int fileAccessMode;
   public int firstChapter;
   public int lastChapter;
   public int extendedFilePosition;
   public int dateTimeAccessed;
   public int dateTimeCreated;
   public int originalSizeEvenForVolumes;
   public String name;
   public String comment;
   public byte[][] extendedHeaders = (byte[][])null;

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("LocalFileHeader [archiverVersionNumber=");
      var1.append(this.intValue);
      var1.append(", minVersionToExtract=");
      var1.append(this.minVersionToExtract);
      var1.append(", hostOS=");
      var1.append(this.hostOS);
      var1.append(", arjFlags=");
      var1.append(this.arjFlags);
      var1.append(", method=");
      var1.append(this.method);
      var1.append(", fileType=");
      var1.append(this.fileType);
      var1.append(", reserved=");
      var1.append(this.reserved);
      var1.append(", dateTimeModified=");
      var1.append(this.dateTimeModified);
      var1.append(", compressedSize=");
      var1.append(this.compressedSize);
      var1.append(", originalSize=");
      var1.append(this.originalSize);
      var1.append(", originalCrc32=");
      var1.append(this.originalCrc32);
      var1.append(", fileSpecPosition=");
      var1.append(this.fileSpecPosition);
      var1.append(", fileAccessMode=");
      var1.append(this.fileAccessMode);
      var1.append(", firstChapter=");
      var1.append(this.firstChapter);
      var1.append(", lastChapter=");
      var1.append(this.lastChapter);
      var1.append(", extendedFilePosition=");
      var1.append(this.extendedFilePosition);
      var1.append(", dateTimeAccessed=");
      var1.append(this.dateTimeAccessed);
      var1.append(", dateTimeCreated=");
      var1.append(this.dateTimeCreated);
      var1.append(", originalSizeEvenForVolumes=");
      var1.append(this.originalSizeEvenForVolumes);
      var1.append(", name=");
      var1.append(this.name);
      var1.append(", comment=");
      var1.append(this.comment);
      var1.append(", extendedHeaders=");
      var1.append(Arrays.toString((Object[])this.extendedHeaders));
      var1.append("]");
      return var1.toString();
   }

   @Override
   public int hashCode() {
      return this.name == null ? 0 : this.name.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         ArjLocalFileHeader var2 = (ArjLocalFileHeader)var1;
         return this.intValue == var2.intValue
            && this.minVersionToExtract == var2.minVersionToExtract
            && this.hostOS == var2.hostOS
            && this.arjFlags == var2.arjFlags
            && this.method == var2.method
            && this.fileType == var2.fileType
            && this.reserved == var2.reserved
            && this.dateTimeModified == var2.dateTimeModified
            && this.compressedSize == var2.compressedSize
            && this.originalSize == var2.originalSize
            && this.originalCrc32 == var2.originalCrc32
            && this.fileSpecPosition == var2.fileSpecPosition
            && this.fileAccessMode == var2.fileAccessMode
            && this.firstChapter == var2.firstChapter
            && this.lastChapter == var2.lastChapter
            && this.extendedFilePosition == var2.extendedFilePosition
            && this.dateTimeAccessed == var2.dateTimeAccessed
            && this.dateTimeCreated == var2.dateTimeCreated
            && this.originalSizeEvenForVolumes == var2.originalSizeEvenForVolumes
            && Objects.equals(this.name, var2.name)
            && Objects.equals(this.comment, var2.comment)
            && Arrays.deepEquals(this.extendedHeaders, var2.extendedHeaders);
      } else {
         return false;
      }
   }
}
