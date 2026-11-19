package litebans.core;

import litebans.config.Message;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.TuplesKt;

public final class ThreadLocalHolder extends ThreadLocal {
   public final LiteBansCore liteBansCore;

   public ThreadLocalHolder(LiteBansCore var1) {
      this.liteBansCore = var1;
   }

   public Pair getPair() {
      return TuplesKt.getPair(Message.DATE_FORMAT.toString(), LiteBansCore.createDateFormatDefault(this.liteBansCore, Message.DATE_FORMAT, null, 2, null));
   }

   @Override
   public Object initialValue() {
      return this.getPair();
   }
}
