package litebans.platform.bukkit;

import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;

public final class ChatPacketAdapterCompanion {
   private ChatPacketAdapterCompanion() {
   }

   private final Object getObject() {
      Lazy var1 = ChatPacketAdapter.getLazy();
      return var1.getValue();
   }

   private final Object getObject2() {
      Lazy var1 = ChatPacketAdapter.getLazy2();
      return var1.getValue();
   }

   public static final Object getObject(ChatPacketAdapterCompanion var0) {
      return var0.getObject();
   }

   public static final Object getObject2(ChatPacketAdapterCompanion var0) {
      return var0.getObject2();
   }

   public ChatPacketAdapterCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
