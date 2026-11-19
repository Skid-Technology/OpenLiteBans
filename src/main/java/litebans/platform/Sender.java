package litebans.platform;

import java.util.UUID;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

public interface Sender {
   Sender[] EMPTY_ARRAY = new Sender[0];
   UUID ZERO_UUID = new UUID(0L, 0L);

   void dispatchCommand(@NonNull String var1);

   @NonNull
   String getDisplayName();

   @Nullable
   Object getHandle();

   @Nullable
   String getIp();

   @NonNull
   String getName();

   @Nullable
   String getServerName();

   @NonNull
   UUID getUuid();

   @NonNull
   String getUuidString();

   boolean hasPermission(@Nullable String var1);

   boolean isConsole();

   boolean isOnline();

   boolean isPlayer();

   void kick(@NonNull String var1);

   void sendMessage(String var1);

   void sendJsonMessage(String var1);

   void sendPluginMessage(String var1, byte[] var2);
}
