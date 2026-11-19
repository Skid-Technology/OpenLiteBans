package litebans.platform.bukkit;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketListener;
import com.comphenix.protocol.utility.MinecraftProtocolVersion;
import litebans.core.PluginModule;
import litebans.lib.kotlin.text.StringsKt;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ProtocolLibHook {
   public static final void handlePluginModule(@NotNull PluginModule var0, @Nullable Object var1) {
      if (var1 != null) {
         ProtocolLibrary.getProtocolManager().addPacketListener((PacketListener)var1);
      }
   }

   public static final void handlePluginModule2(@NotNull PluginModule var0, @Nullable Object var1) {
      if (var1 != null) {
         ProtocolLibrary.getProtocolManager().removePacketListener((PacketListener)var1);
      }
   }

   public static final boolean checkPlayer(@NotNull Player var0) {
      return var0.getClass().getSimpleName().length() > 8 && StringsKt.checkString(var0.getClass().getSimpleName(), "TemporaryPlayer", false, 2, null);
   }

   public static final boolean checkPlayer2(@Nullable Player var0) {
      return var0 == null || checkPlayer(var0);
   }

   public static final int getInt(@NotNull Player var0) {
      int var1 = MinecraftProtocolVersion.getCurrentVersion();
      int var2 = ProtocolLibrary.getProtocolManager().getProtocolVersion(var0);
      return var2 <= 0 ? var1 : var2;
   }
}
