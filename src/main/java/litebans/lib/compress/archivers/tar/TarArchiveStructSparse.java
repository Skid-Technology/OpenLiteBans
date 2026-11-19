package litebans.lib.compress.archivers.tar;

import java.util.Objects;

public final class TarArchiveStructSparse {
   private final long offset;
   private final long numbytes;

   public TarArchiveStructSparse(long var1, long var3) {
      this.offset = var1;
      this.numbytes = var3;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         TarArchiveStructSparse var2 = (TarArchiveStructSparse)var1;
         return this.offset == var2.offset && this.numbytes == var2.numbytes;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.offset, this.numbytes);
   }

   @Override
   public String toString() {
      return "TarArchiveStructSparse{offset=" + this.offset + ", numbytes=" + this.numbytes + '}';
   }

   public long getOffset() {
      return this.offset;
   }

   public long getNumbytes() {
      return this.numbytes;
   }
}
