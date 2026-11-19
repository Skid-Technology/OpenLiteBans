package litebans.platform.velocity;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.messages.LegacyChannelIdentifier;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Optional;
import java.util.UUID;
import litebans.VelocityPlugin;
import litebans.chat.AdventureComponentParser;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.Sender;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

@MetadataAnnotation(
   a = 2
)
public class VelocitySender implements Sender {
   private final VelocityPlugin velocityPlugin;
   private final WeakReference weakReference;
   private final String string;
   private final boolean flag;
   private UUID uuid2 = ZERO_UUID;
   private String string2 = null;
   private String string3;

   public VelocitySender(VelocityPlugin var1, CommandSource var2) {
      this.velocityPlugin = var1;
      this.weakReference = new WeakReference<>(var2);
      this.flag = var2 instanceof Player;
      if (this.flag) {
         this.string = ((Player)var2).getUsername();
      } else {
         this.string = "[Console]";
      }
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1 || var1 instanceof VelocitySender && ((VelocitySender)var1).getUuid().equals(this.getUuid());
   }

   @Override
   public void dispatchCommand(String var1) {
      CommandSource var2 = (CommandSource)this.weakReference.get();
      if (var2 != null && !var1.isEmpty()) {
         this.velocityPlugin.proxyServer.getCommandManager().executeAsync(var2, var1);
      }
   }

   @Override
   public String getDisplayName() {
      return this.getName();
   }

   @Override
   public Object getHandle() {
      return this.weakReference.get();
   }

   @Nullable
   @Override
   public String getIp() {
      String var1 = this.string3;
      if (var1 == null) {
         CommandSource var2 = (CommandSource)this.weakReference.get();
         if (var2 != null) {
            this.string3 = var1 = this.getString(var2);
         }
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
      CommandSource var1 = (CommandSource)this.weakReference.get();
      if (var1 == null) {
         return null;
      } else if (this.isPlayer()) {
         Player var2 = (Player)var1;
         Optional var3 = var2.getCurrentServer();
         return !var3.isPresent() ? null : ((ServerConnection)var3.get()).getServerInfo().getName();
      } else {
         return null;
      }
   }

   @NonNull
   @Override
   public UUID getUuid() {
      UUID var1 = this.uuid2;
      if (var1.equals(ZERO_UUID) && this.flag) {
         CommandSource var2 = (CommandSource)this.weakReference.get();
         if (var2 != null) {
            UUID var3 = ((Player)var2).getUniqueId();
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

   public int getInt() {
      Object var1 = this.getHandle();
      if (var1 instanceof Player) {
         Player var2 = (Player)var1;
         return var2.getProtocolVersion().getProtocol();
      } else {
         return 0;
      }
   }

   @Override
   public boolean hasPermission(@Nullable String var1) {
      if (var1 == null) {
         return true;
      } else {
         CommandSource var2 = (CommandSource)this.weakReference.get();
         return var2 != null && var2.hasPermission(var1);
      }
   }

   @Override
   public boolean isConsole() {
      return !this.flag;
   }

   @Override
   public boolean isOnline() {
      if (this.flag) {
         CommandSource var1 = (CommandSource)this.weakReference.get();
         if (var1 != null) {
            return ((Player)var1).isActive();
         }
      }

      return this.weakReference.get() != null;
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
         CommandSource var2 = (CommandSource)this.weakReference.get();
         if (var2 != null) {
            if (this.isPlayer()) {
               ((Player)var2).disconnect(AdventureComponentParser.getTextComponent(reason));
            }
         }
      }
   }

   @Override
   public void sendJsonMessage(String var1) {
      CommandSource var2 = (CommandSource)this.weakReference.get();
      if (var2 != null) {
         var2.sendMessage(AdventureComponentParser.getComponent(var1));
      }
   }

   @Override
   public void sendMessage(String var1) {
      CommandSource var2 = (CommandSource)this.weakReference.get();
      if (var2 != null) {
         var2.sendMessage(AdventureComponentParser.getTextComponent(var1));
      }
   }

   @Override
   public void sendPluginMessage(String var1, byte[] var2) {
      CommandSource var3 = (CommandSource)this.weakReference.get();
      if (var3 != null) {
         if (this.isPlayer()) {
            ((Player)var3).sendPluginMessage(new LegacyChannelIdentifier(var1), var2);
         }
      }
   }

   private final String getString(CommandSource var1) {
      if (var1 instanceof Player) {
         InetSocketAddress var2 = ((Player)var1).getRemoteAddress();
         if (var2 != null) {
            InetAddress var3 = var2.getAddress();
            if (var3 != null) {
               return var3.getHostAddress();
            }
         }
      }

      return null;
   }
}
