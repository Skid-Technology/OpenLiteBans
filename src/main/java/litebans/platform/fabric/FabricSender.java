package litebans.platform.fabric;

import java.lang.ref.WeakReference;
import java.util.UUID;
import java.util.function.Supplier;
import litebans.FabricPlugin;
import litebans.chat.ChatColorCode;
import litebans.chat.ChatComponentVersion;
import litebans.chat.LegacyTextParser;
import litebans.chat.VersionedComponentSerializer;
import litebans.platform.Sender;
import lombok.NonNull;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FabricSender implements Sender {
   private final FabricPlugin fabricPlugin;
   private final WeakReference weakReference;
   private final Supplier supplier;
   private final String string;
   private final boolean flag;
   private UUID uuid2 = ZERO_UUID;
   private String string2 = null;
   private String string3;

   public FabricSender(FabricPlugin var1, Object var2, @Nullable Supplier var3) {
      this.fabricPlugin = var1;
      this.weakReference = new WeakReference<>(var2);
      this.supplier = var3;
      this.flag = var2 instanceof ServerPlayer || var2 instanceof CommandSourceStack && ((CommandSourceStack)var2).isPlayer();
      if (this.flag && var2 instanceof ServerPlayer) {
         this.string = ((ServerPlayer)var2).getPlainTextName();
      } else if (var2 instanceof CommandSourceStack) {
         this.string = ((CommandSourceStack)var2).getTextName();
      } else {
         this.string = "?";
      }
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1 || var1 instanceof FabricSender && ((FabricSender)var1).getUuid().equals(this.getUuid());
   }

   @Override
   public void dispatchCommand(@NotNull String var1) {
      Object var2 = this.getHandle();
      if (var2 instanceof CommandSourceStack && !var1.isEmpty()) {
         this.fabricPlugin.getFabricServerAdapter().handleObject2(var2, var1);
      }
   }

   @Override
   public String getDisplayName() {
      return this.getName();
   }

   @Override
   public Object getHandle() {
      Object var1 = this.weakReference.get();
      if (this.supplier == null) {
         return var1;
      } else {
         return var1 != null ? var1 : this.supplier.get();
      }
   }

   @Nullable
   @Override
   public String getIp() {
      String var1 = this.string3;
      if (var1 == null) {
         Object var2 = this.getHandle();
         this.string3 = var1 = this.fabricPlugin.getFabricServerAdapter().getString(var2);
      }

      return var1;
   }

   @Override
   public String getName() {
      return this.string;
   }

   @Nullable
   @Override
   public String getServerName() {
      return null;
   }

   @NonNull
   @Override
   public UUID getUuid() {
      UUID var1 = this.uuid2;
      if (var1.equals(ZERO_UUID) && this.flag) {
         Object var2 = this.getHandle();
         if (var2 != null) {
            UUID var3 = this.getServerPlayer().getUUID();
            if (var3 != null) {
               this.uuid2 = var3;
               return var3;
            }
         }
      }

      return var1;
   }

   @Override
   public String getUuidString() {
      String var1 = this.string2;
      return var1 != null && !var1.equals("00000000-0000-0000-0000-000000000000") ? var1 : (this.string2 = this.getUuid().toString());
   }

   @Override
   public boolean hasPermission(@Nullable String var1) {
      try {
         if (var1 == null) {
            return true;
         } else {
            Object var2 = this.getHandle();
            if (var2 == null) {
               return false;
            } else if (this.fabricPlugin.isFlag() && (this.flag || !(var2 instanceof CommandSourceStack))) {
               return var2 instanceof CommandSourceStack && this.isOnline() ? (Boolean)Permissions.check(this.getUuid(), var1).get() : false;
            } else {
               return this.fabricPlugin.getFabricServerAdapter().checkObject(var2, var1);
            }
         }
      } catch (Throwable var3) {
         throw new RuntimeException(var3);
      }
   }

   @Override
   public boolean isConsole() {
      return !this.flag;
   }

   @Override
   public boolean isOnline() {
      Object var1 = this.getHandle();
      return this.flag && var1 != null ? !this.getServerPlayer().hasDisconnected() : var1 != null;
   }

   private final ServerPlayer getServerPlayer() {
      CommandSourceStack var1 = (CommandSourceStack)this.getHandle();
      return var1 == null ? null : var1.getPlayer();
   }

   @Override
   public boolean isPlayer() {
      return this.flag;
   }

   @Override
   public void kick(@NonNull String reason) {
      if (reason == null) {
         throw new NullPointerException("reason is marked non-null but is null");
      } else {
         Object var2 = this.getHandle();
         if (var2 != null) {
            if (this.isPlayer()) {
               if (this.fabricPlugin.isPrimaryThread()) {
                  this.fabricPlugin.getFabricServerAdapter().handleObject(this.getServerPlayer(), reason);
               } else {
                  this.fabricPlugin.runSync(this::handleString5);
               }
            }
         }
      }
   }

   @Override
   public void sendJsonMessage(String var1) {
      Object var2 = this.getHandle();
      this.fabricPlugin.getFabricServerAdapter().handleObject3(var2, var1);
   }

   @Override
   public void sendMessage(String var1) {
      if (!this.flag) {
         Object var2 = this.getHandle();
         if (var2 != null) {
            ((CommandSourceStack)var2)
               .sendSystemMessage(
                  (Component)this.fabricPlugin.getFabricServerAdapter().getComponentParser().getObject(ChatColorCode.getString((CharSequence)var1))
               );
         }
      } else {
         ChatComponentVersion var3 = ChatComponentVersion.V1_16;
         if (this.fabricPlugin.getFabricServerAdapter().getInt() >= 770) {
            var3 = ChatComponentVersion.V1_21_5;
         }

         this.sendJsonMessage(VersionedComponentSerializer.getVersionedComponentSerializer(var3).getString(LegacyTextParser.getBaseComponentArray(var1)));
      }
   }

   @Override
   public void sendPluginMessage(String var1, byte[] var2) {
      try {
         Object var3 = this.getHandle();
         if (var3 != null) {
            if (this.isPlayer()) {
               if (var1.equals("BungeeCord")) {
                  var1 = "bungeecord:main";
               }

               this.fabricPlugin.getFabricServerAdapter().handleObject(this.getServerPlayer(), var1, var2);
            }
         }
      } catch (Throwable var4) {
         throw new RuntimeException(var4);
      }
   }

   private final void handleString5(String var1) {
      this.kick(var1);
   }
}
