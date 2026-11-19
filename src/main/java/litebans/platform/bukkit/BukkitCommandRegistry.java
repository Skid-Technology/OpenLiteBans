package litebans.platform.bukkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import litebans.chat.JsonMessageParser;
import litebans.command.BaseCommand;
import litebans.command.CommandInvokeTask;
import litebans.command.CommandRunnable;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import litebans.platform.PlatformBridge;
import litebans.platform.Sender;
import litebans.punishment.PunishmentService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@MetadataAnnotation(
   a = 0
)
public final class BukkitCommandRegistry extends BukkitCommandRegistryBase {
   public BukkitCommandRegistry(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void onLoad() {
      LiteBansCore var1 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      var1.onEnable();
      this.platform.setCommandRegistry(this);
      this.run6();
      this.run7();
      super.onLoad();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onReload() {
      this.run7();
   }

   private final void run6() {
      LiteBansCore var1 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      PlatformBridge var10000 = this.platform.createBridge();
      Intrinsics.handleObject(var10000, "");
      Plugin var2 = BukkitBridge.getPluginDefault((BukkitBridge)var10000, null, 1, null);
      Map var3 = var2.getDescription().getCommands();

      for (Object var6o : var3.entrySet()) {
         Entry var6 = (Entry) var6o;
         String var9 = (String)var6.getKey();
         PluginCommand var10 = var2.getServer().getPluginCommand(var9);
         if (var10 != null) {
            if (var10.getTabCompleter() != null && !Intrinsics.checkObject(var10.getPlugin(), this.platform)) {
               var1.debug(1, "Command /" + var9 + " is tab-completed by " + var10.getTabCompleter() + '!');
            }

            var10.setTabCompleter((TabCompleter)var2);
            if (!Intrinsics.checkObject(var10.getPlugin(), this.platform)) {
               var1.debug(1, "Command /" + var9 + " is owned by " + var10.getPlugin().getDescription().getName() + '!');
            }
         }
      }
   }

   private final void run7() {
      PlatformBridge var10000 = this.platform.createBridge();
      Intrinsics.handleObject(var10000, "");
      Plugin var1 = BukkitBridge.getPluginDefault((BukkitBridge)var10000, null, 1, null);
      Map var2 = var1.getDescription().getCommands();

      for (Object var5o : var2.entrySet()) {
         Entry var5 = (Entry) var5o;
         String var8 = (String)var5.getKey();
         PluginCommand var9 = var1.getServer().getPluginCommand(var8);
         if (var9 != null && Intrinsics.checkObject(var9.getPlugin(), this.platform)) {
            var9.setPermissionMessage(JsonMessageParser.formatSplitter.getString(Message.ERROR_NO_PERMISSION.toString()));
         }
      }
   }

   @Override
   public boolean onCommand(@NotNull CommandSender var1, @NotNull Command var2, @NotNull String var3, @NotNull String[] var4) {
      return this.checkCommandSender(var1, var2, var3, var4);
   }

   public final boolean checkCommandSender(@NotNull CommandSender var1, @Nullable Command var2, @NotNull String var3, @NotNull String[] var4) {
      Object var27;
      String[] var8 = var4;
      int var10 = 0;
      int var11 = var4.length;

      boolean var10000;
      while (true) {
         if (var10 >= var11) {
            var10000 = true;
            break;
         }

         String var12 = var8[var10];
         if (StringsKt.checkCharSequence(var12)) {
            var10000 = false;
            break;
         }

         var10++;
      }

      String[] var26;
      if (var10000) {
         var26 = var4;
      } else {
         String[] var19 = var4;
         Collection var22 = new ArrayList();
         int var13 = 0;

         for (int var14 = var4.length; var13 < var14; var13++) {
            String var15 = var19[var13];
            if (!StringsKt.checkCharSequence(var15)) {
               var22.add(var15);
            }
         }

         Collection var17 = (List)var22;
         var26 = /* cast-fix */(String[])(var17.toArray(new String[0]));
      }

      String[] var5;
      Sender var6;
      label55: {
         var5 = var26;
         var6 = this.platform.getSender(var1);
         if (var2 != null) {
            var27 = var2.getName();
            if (var27 != null) {
               break label55;
            }
         }

         var27 = var3;
      }

      String var7 = /* cast-fix */(String)(var27);
      String var18 = var7;
      if (StringsKt.checkString(var7, "litebans:", false, 2, null)) {
         var18 = var7.substring(9);
      }

      BaseCommand var9 = (BaseCommand)this.getCommand(var7);
      if (var9 != null) {
         String var21 = var9.getPermission();
         if (var21 != null) {
            Intrinsics.handleObject(var6);
            if (!var6.hasPermission(var21)) {
               CharSequence var24 = Message.ERROR_NO_PERMISSION;
               MessageSender.handleSenderDefault(CommandRunnable.messageSender, var6, var24, null, 4, null);
               return true;
            }
         }

         Intrinsics.handleObject(var6);
         this.handleSender(var6, var5, var9, var18);
         return true;
      } else {
         String[] var20 = PunishmentService.expandAlias(BaseCommand.commandAliases, var7, var5);
         BaseCommand var23 = (BaseCommand)this.getCommand("ban");
         if (var23 == null) {
            Intrinsics.handleObject(var6);
            CharSequence var25 = Message.ERROR_INTERNAL;
            MessageSender.handleSenderDefault(CommandRunnable.messageSender, var6, var25, null, 4, null);
            return true;
         } else {
            Intrinsics.handleObject(var6);
            this.handleSender(var6, var20, var23, var18);
            return true;
         }
      }
   }

   @Override
   public List suggest(@Nullable LiteCommand var1, @NotNull String var2, @NotNull Sender var3, @NotNull String[] var4) {
      if (var4.length != 0) {
         String var6 = "litebans.tabcomplete";
         if (var3.hasPermission(var6)) {
            return BaseCommand.commandAliases.getList(var1, var2, var3, this.platform, var4);
         }
      }

      return CollectionsKt.getList();
   }

   private final void handleSender(Sender var1, String[] var2, BaseCommand var3, String var4) {
      if (var3.isFlag()) {
         this.platform.execute(new CommandInvokeTask(var1, var2, var3, var4));
      } else {
         var3.dispatch(var1, var2, var4);
      }
   }
}
