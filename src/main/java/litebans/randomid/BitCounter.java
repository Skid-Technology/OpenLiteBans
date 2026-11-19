package litebans.randomid;

public final class BitCounter extends LongBitSet {
   public BitCounter() {
      super(0L, 1, null);
   }

   @Override
   public int getInt(int var1) {
      return this.getInt();
   }

   private final int getInt() {
      return 4;
   }
}
