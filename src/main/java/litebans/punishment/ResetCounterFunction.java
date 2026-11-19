package litebans.punishment;

import java.util.concurrent.atomic.AtomicLong;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.FunctionReferenceImpl;
import litebans.util.Unused1;

// $VF: synthetic class
public final class ResetCounterFunction extends FunctionReferenceImpl implements Function1 {
   public static final ResetCounterFunction INSTANCE = new ResetCounterFunction();

   public ResetCounterFunction() {
      super(1, Unused1.class, "reset", "reset(Ljava/util/concurrent/atomic/AtomicLong;)V", 1);
   }

   public final void handleAtomicLong(AtomicLong var1) {
      var1.set(0L);
   }

   @Override
   public Object invoke(Object var1) {
      this.handleAtomicLong((AtomicLong)var1);
      return Unit.INSTANCE;
   }
}
