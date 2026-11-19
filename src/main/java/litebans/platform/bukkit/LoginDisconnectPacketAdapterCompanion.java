package litebans.platform.bukkit;

import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;

public final class LoginDisconnectPacketAdapterCompanion {
   private LoginDisconnectPacketAdapterCompanion() {
   }

   private final Object getObject() {
      Lazy var1 = LoginDisconnectPacketAdapter.getLazy();
      return var1.getValue();
   }

   public static final Object getObject(LoginDisconnectPacketAdapterCompanion var0) {
      return var0.getObject();
   }

   public LoginDisconnectPacketAdapterCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
