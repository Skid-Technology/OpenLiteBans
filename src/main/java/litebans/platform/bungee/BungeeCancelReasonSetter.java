package litebans.platform.bungee;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import net.md_5.bungee.api.event.LoginEvent;
import org.jetbrains.annotations.NotNull;

public final class BungeeCancelReasonSetter {
   private BungeeCancelReasonSetter() {
   }

   public final void handleObject(@NotNull Object var1, @NotNull CharSequence var2) {
      if (var1 instanceof LoginEvent) {
         ((LoginEvent)var1).setCancelReason(var2.toString());
      }
   }

   public BungeeCancelReasonSetter(DefaultConstructorMarker var1) {
      this();
   }
}
