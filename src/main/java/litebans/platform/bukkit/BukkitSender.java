package litebans.platform.bukkit;

import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.UUID;
import litebans.BukkitPlugin;
import litebans.lib.json.JSONObject;
import litebans.lib.json.JSONParser;
import litebans.platform.PlatformDetector;
import litebans.platform.Sender;
import litebans.punishment.KickTask;
import lombok.NonNull;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class BukkitSender implements Sender {
   private final BukkitPlugin bukkitPlugin;
   private final WeakReference weakReference;
   private UUID uuid2 = ZERO_UUID;
   private String string = null;
   private final String string2;
   private String string3;
   private final boolean flag;
   private final boolean flag2;

   public BukkitSender(BukkitPlugin var1, CommandSender var2) {
      this.bukkitPlugin = var1;
      this.weakReference = new WeakReference<>(var2);
      boolean var3 = var2 instanceof Player;
      this.flag = var3;
      this.flag2 = !var3 && var2 instanceof ConsoleCommandSender;
      this.string2 = var2.getName();
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1 || var1 instanceof BukkitSender && ((BukkitSender)var1).getUuid().equals(this.getUuid());
   }

   @Override
   public void dispatchCommand(String var1) {
      CommandSender var2 = (CommandSender)this.weakReference.get();
      if (var2 != null && !var1.isEmpty()) {
         if (PlatformDetector.isFlag3()) {
            if (var2 instanceof BlockCommandSender) {
               FoliaScheduler.handleBukkitPlugin(this.bukkitPlugin, ((BlockCommandSender)var2).getBlock().getLocation(), () -> handleCommandSender4(var2, var1));
            } else if (var2 instanceof Entity) {
               FoliaScheduler.handleBukkitPlugin(this.bukkitPlugin, this, () -> handleCommandSender3(var2, var1));
            } else {
               FoliaScheduler.handleBukkitPlugin(this.bukkitPlugin, () -> handleCommandSender2(var2, var1));
            }
         } else {
            this.bukkitPlugin.getServer().dispatchCommand(var2, var1);
         }
      }
   }

   @Override
   public String getDisplayName() {
      CommandSender var1 = (CommandSender)this.weakReference.get();
      if (var1 == null) {
         return this.string2;
      } else {
         return this.flag ? ((Player)var1).getDisplayName() : this.getName();
      }
   }

   @Override
   public Object getHandle() {
      return this.weakReference.get();
   }

   @Override
   public String getIp() {
      String var1 = this.string3;
      if (var1 == null) {
         CommandSender var2 = (CommandSender)this.weakReference.get();
         if (var2 != null) {
            this.string3 = var1 = this.getString(var2);
         }
      }

      return var1;
   }

   @Override
   public String getName() {
      return this.string2;
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
         CommandSender var2 = (CommandSender)this.weakReference.get();
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
      String var1 = this.string;
      return var1 != null && !var1.equals("00000000-0000-0000-0000-000000000000") ? var1 : (this.string = this.getUuid().toString());
   }

   @Override
   public boolean hasPermission(@Nullable String var1) {
      if (var1 == null) {
         return true;
      } else {
         CommandSender var2 = (CommandSender)this.weakReference.get();
         return var2 != null && var2.hasPermission(var1);
      }
   }

   @Override
   public boolean isConsole() {
      return this.flag2;
   }

   @Override
   public boolean isOnline() {
      CommandSender var1 = (CommandSender)this.weakReference.get();
      return this.flag && var1 != null && ((Player)var1).isOnline();
   }

   @Override
   public boolean isPlayer() {
      return this.flag;
   }

   @Override
   public void kick(String var1) {
      CommandSender var2 = (CommandSender)this.weakReference.get();
      if (var2 != null) {
         if (this.flag) {
            Player var3 = (Player)var2;
            if (!PlatformDetector.isFlag3() && !this.bukkitPlugin.getServer().isPrimaryThread()) {
               this.bukkitPlugin.runSync(new KickTask(this, var3, var1));
            } else {
               var3.kickPlayer(var1);
            }
         }
      }
   }

   @Override
   public void sendJsonMessage(String var1) {
      CommandSender var2 = (CommandSender)this.weakReference.get();
      if (var2 != null && var1 != null) {
         if (this.flag) {
            Player var3 = (Player)var2;
            if (PlatformDetector.isFlag2()) {
               PlatformDetector.handlePlayer(var3, var1);
            } else {
               this.sendMessage(this.getString(var1));
            }
         } else {
            this.sendMessage(this.getString(var1));
         }
      }
   }

   @Override
   public void sendMessage(String var1) {
      CommandSender var2 = (CommandSender)this.weakReference.get();
      if (var2 != null) {
         if (PlatformDetector.isFlag3() && var2 instanceof BlockCommandSender) {
            FoliaScheduler.handleBukkitPlugin(this.bukkitPlugin, ((BlockCommandSender)var2).getBlock().getLocation(), () -> handleCommandSender(var2, var1));
         } else {
            var2.sendMessage(var1);
         }
      }
   }

   @Override
   public void sendPluginMessage(String var1, byte[] var2) {
      CommandSender var3 = (CommandSender)this.weakReference.get();
      if (var3 != null) {
         if (this.flag) {
            ((Player)var3).sendPluginMessage(this.bukkitPlugin, var1, var2);
         }
      }
   }

   private final String getString(String var1) {
      try {
         JSONObject var2 = (JSONObject)new JSONParser().getObject(var1);
         return (String)var2.get("text");
      } catch (Throwable var3) {
         throw new RuntimeException(var3);
      }
   }

   private final String getString(CommandSender var1) {
      if (var1 instanceof Player) {
         InetSocketAddress var2 = ((Player)var1).getAddress();
         if (var2 != null) {
            InetAddress var3 = var2.getAddress();
            if (var3 != null) {
               return var3.getHostAddress();
            }
         }
      }

      return null;
   }

   private static final void handleCommandSender(CommandSender var0, String var1) {
      var0.sendMessage(var1);
   }

   private final void handleCommandSender2(CommandSender var1, String var2) {
      this.bukkitPlugin.getServer().dispatchCommand(var1, var2);
   }

   private final void handleCommandSender3(CommandSender var1, String var2) {
      this.bukkitPlugin.getServer().dispatchCommand(var1, var2);
   }

   private final void handleCommandSender4(CommandSender var1, String var2) {
      this.bukkitPlugin.getServer().dispatchCommand(var1, var2);
   }
}
