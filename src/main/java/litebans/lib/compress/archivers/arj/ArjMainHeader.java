package litebans.lib.compress.archivers.arj;

import java.util.Arrays;

public class ArjMainHeader {
   public int intValue;
   public int minVersionToExtract;
   public int hostOS;
   public int arjFlags;
   public int securityVersion;
   public int fileType;
   public int reserved;
   public int dateTimeCreated;
   public int dateTimeModified;
   public long archiveSize;
   public int securityEnvelopeFilePosition;
   public int fileSpecPosition;
   public int securityEnvelopeLength;
   public int encryptionVersion;
   public int lastChapter;
   public int arjProtectionFactor;
   public int arjFlags2;
   public String name;
   public String comment;
   public byte[] extendedHeaderBytes = null;

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("MainHeader [archiverVersionNumber=");
      var1.append(this.intValue);
      var1.append(", minVersionToExtract=");
      var1.append(this.minVersionToExtract);
      var1.append(", hostOS=");
      var1.append(this.hostOS);
      var1.append(", arjFlags=");
      var1.append(this.arjFlags);
      var1.append(", securityVersion=");
      var1.append(this.securityVersion);
      var1.append(", fileType=");
      var1.append(this.fileType);
      var1.append(", reserved=");
      var1.append(this.reserved);
      var1.append(", dateTimeCreated=");
      var1.append(this.dateTimeCreated);
      var1.append(", dateTimeModified=");
      var1.append(this.dateTimeModified);
      var1.append(", archiveSize=");
      var1.append(this.archiveSize);
      var1.append(", securityEnvelopeFilePosition=");
      var1.append(this.securityEnvelopeFilePosition);
      var1.append(", fileSpecPosition=");
      var1.append(this.fileSpecPosition);
      var1.append(", securityEnvelopeLength=");
      var1.append(this.securityEnvelopeLength);
      var1.append(", encryptionVersion=");
      var1.append(this.encryptionVersion);
      var1.append(", lastChapter=");
      var1.append(this.lastChapter);
      var1.append(", arjProtectionFactor=");
      var1.append(this.arjProtectionFactor);
      var1.append(", arjFlags2=");
      var1.append(this.arjFlags2);
      var1.append(", name=");
      var1.append(this.name);
      var1.append(", comment=");
      var1.append(this.comment);
      var1.append(", extendedHeaderBytes=");
      var1.append(Arrays.toString(this.extendedHeaderBytes));
      var1.append("]");
      return var1.toString();
   }
}
