package litebans.platform;

import litebans.lib.kotlin.ResultFailure;

public final class SenderRunnable1 implements Runnable {
   public final Sender sender;
   public final ResultFailure resultFailure;

   public SenderRunnable1(Sender var1, ResultFailure var2) {
      this.sender = var1;
      this.resultFailure = var2;
   }

   @Override
   public final void run() {
      this.sender.dispatchCommand(this.resultFailure.object.toString());
   }
}
