package litebans.platform;

public final class SenderRunnable2 implements Runnable {
   public final Sender sender;
   public final String string;

   public SenderRunnable2(Sender var1, String var2) {
      this.sender = var1;
      this.string = var2;
   }

   @Override
   public final void run() {
      this.sender.dispatchCommand(this.string);
   }
}
