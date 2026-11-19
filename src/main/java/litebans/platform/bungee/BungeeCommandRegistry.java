package litebans.platform.bungee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import litebans.command.BaseCommand;
import litebans.command.CommandInvokeTask;
import litebans.command.CommandRunnable;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import litebans.platform.Sender;
import litebans.punishment.PunishmentService;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@MetadataAnnotation(
   a = 1
)
public final class BungeeCommandRegistry extends BungeeCommandRegistryBase {
   public BungeeCommandRegistry(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public Collection discoverCommands() {
      Collection var1 = super.discoverCommands();
      var1.removeIf(o -> checkLiteCommand((LiteCommand) o));
      return var1;
   }

   @Override
   public void executeCommand(@NotNull LiteCommand var1, @NotNull Sender var2, @NotNull String[] var3) {
      this.dispatch(var2, var1.getName(), var3);
   }

   @Override
   public void dispatch(@NotNull Sender var1, @NotNull String var2, @NotNull String[] var3) {
      String[] var7 = var3;
      int var9 = 0;
      int var10 = var3.length;

      boolean var10000;
      while (true) {
         if (var9 >= var10) {
            var10000 = true;
            break;
         }

         String var11 = var7[var9];
         if (StringsKt.checkCharSequence(var11)) {
            var10000 = false;
            break;
         }

         var9++;
      }

      String[] var23;
      if (var10000) {
         var23 = var3;
      } else {
         String[] var19 = var3;
         Collection var22 = new ArrayList();
         int var12 = 0;

         for (int var13 = var3.length; var12 < var13; var12++) {
            String var14 = var19[var12];
            if (!StringsKt.checkCharSequence(var14)) {
               var22.add(var14);
            }
         }

         Collection var16 = (List)var22;
         var23 = /* cast-fix */(String[])(var16.toArray(new String[0]));
      }

      String[] var4 = var23;
      String var5 = StringsKt.getString(var2, "litebans:", null, 2, null);
      BaseCommand var6 = (BaseCommand)this.getCommand(var5);
      if (var6 != null) {
         String var18 = var6.getPermission();
         if (var18 != null && !var1.hasPermission(var18)) {
            CharSequence var21 = Message.ERROR_NO_PERMISSION;
            MessageSender.handleSenderDefault(CommandRunnable.messageSender, var1, var21, null, 4, null);
         } else {
            this.platform.execute(new CommandInvokeTask(var1, var4, var6, var5));
         }
      } else {
         var7 = PunishmentService.expandAlias(BaseCommand.commandAliases, var5, var4);
         BaseCommand var24 = (BaseCommand)this.getCommand("ban");
         if (var24 == null) {
            Unreachable.getVoid();
            throw new NoWhenBranchMatchedException();
         } else {
            BaseCommand var20 = var24;
            this.platform.execute(new CommandInvokeTask(var1, var7, var20, var5));
         }
      }
   }

   @Override
   public void onLoad() {
      BungeeTabCommand var40;
      LiteBansCore var1 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      var1.onEnable();
      super.onLoad();
      this.platform.setCommandRegistry(this);
      Iterable var2 = this.getCommands();
      Collection var5 = new ArrayList(CollectionsKt.getInt(var2, 10));

      for (Object var8 : var2) {
         LiteCommand var9;
         String[] var10005;
         label109: {
            var9 = (LiteCommand)var8;
            String var14 = var9.getName();
            switch (var14.hashCode()) {
               case -1320563219:
                  if (var14.equals("dupeip")) {
                     String[] var34 = new String[]{"alts", "litebans:alts", "checkalts", "litebans:checkalts", "litebans:dupeip"};
                     var10005 = var34;
                     break label109;
                  }
                  break;
               case 712910260:
                  if (var14.equals("staffhistory")) {
                     String[] var32 = new String[]{"staffhist", "litebans:staffhistory"};
                     var10005 = var32;
                     break label109;
                  }
                  break;
               case 926934164:
                  if (var14.equals("history")) {
                     String[] var17 = new String[]{"hist", "litebans:history"};
                     var10005 = var17;
                     break label109;
                  }
            }

            String[] var33 = new String[]{"litebans:" + var14};
            var10005 = var33;
         }

         var5.add(new BungeeTabCommand(var9, this.platform, var9.getPermission(), var10005));
      }

      for (Object var27 : (List)var5) {
         LiteCommand var6 = (LiteCommand)var27;
         this.handleLiteCommand2(var6);
      }

      BungeeCommandRegistry var26 = this;
      Intrinsics.handleObject(var1);
      String[] var28 = BaseCommand.commandAliases.getStringArray(var1);
      int var29 = 0;

      for (int var30 = var28.length; var29 < var30; var29++) {
         String var31;
         StringBuilder var39;
         String var10001;
         label90: {
            label89: {
               label88: {
                  var31 = var28[var29];
                  var39 = new StringBuilder().append("litebans.");
                  switch (var31.hashCode()) {
                     case -1396405339:
                        if (var31.equals("ban-ip")) {
                           break label88;
                        }
                        break;
                     case -1062781696:
                        if (var31.equals("muteip")) {
                           break label89;
                        }
                        break;
                     case -840405966:
                        if (var31.equals("unmute")) {
                           var10001 = "unmute.own";
                           break label90;
                        }
                        break;
                     case -840127329:
                        if (var31.equals("unwarn")) {
                           var10001 = "unwarn.own";
                           break label90;
                        }
                        break;
                     case 93503862:
                        if (var31.equals("banip")) {
                           break label88;
                        }
                        break;
                     case 111426262:
                        if (var31.equals("unban")) {
                           var10001 = "unban.own";
                           break label90;
                        }
                        break;
                     case 1231784084:
                        if (var31.equals("tempipmute")) {
                           break label89;
                        }
                        break;
                     case 1351111124:
                        if (var31.equals("tempmuteip")) {
                           break label89;
                        }
                        break;
                     case 1972486690:
                        if (var31.equals("tempbanip")) {
                           break label88;
                        }
                        break;
                     case 1979386420:
                        if (var31.equals("tempipban")) {
                           break label88;
                        }
                  }

                  var10001 = var31;
                  break label90;
               }

               var10001 = "ipban";
               break label90;
            }

            var10001 = "ipmute";
         }

         LiteCommand var15;
         String[] var10006;
         label71: {
            String var35 = var39.append(var10001).toString();
            var15 = new BungeeMappedCommand(var31, var35, var26);
            String var19 = var15.getName();
            switch (var19.hashCode()) {
               case -1320563219:
                  if (var19.equals("dupeip")) {
                     String[] var38 = new String[]{"alts", "litebans:alts", "checkalts", "litebans:checkalts", "litebans:dupeip"};
                     var10006 = var38;
                     break label71;
                  }
                  break;
               case 712910260:
                  if (var19.equals("staffhistory")) {
                     String[] var36 = new String[]{"staffhist", "litebans:staffhistory"};
                     var10006 = var36;
                     break label71;
                  }
                  break;
               case 926934164:
                  if (var19.equals("history")) {
                     String[] var22 = new String[]{"hist", "litebans:history"};
                     var10006 = var22;
                     break label71;
                  }
            }

            String[] var37 = new String[]{"litebans:" + var19};
            var10006 = var37;
         }

         var40 = new BungeeTabCommand(var15, var26.platform, var15.getPermission(), var10006);
         var26.handleLiteCommand2(var40);
      }
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onReload() {
      Iterable var1 = this.getCommands();
      Collection var4 = new ArrayList(CollectionsKt.getInt(var1, 10));

      for (Object var7 : var1) {
         LiteCommand var8;
         String[] var10005;
         label36: {
            var8 = (LiteCommand)var7;
            String var13 = var8.getName();
            switch (var13.hashCode()) {
               case -1320563219:
                  if (var13.equals("dupeip")) {
                     String[] var23 = new String[]{"alts", "litebans:alts", "checkalts", "litebans:checkalts", "litebans:dupeip"};
                     var10005 = var23;
                     break label36;
                  }
                  break;
               case 712910260:
                  if (var13.equals("staffhistory")) {
                     String[] var21 = new String[]{"staffhist", "litebans:staffhistory"};
                     var10005 = var21;
                     break label36;
                  }
                  break;
               case 926934164:
                  if (var13.equals("history")) {
                     String[] var16 = new String[]{"hist", "litebans:history"};
                     var10005 = var16;
                     break label36;
                  }
            }

            String[] var22 = new String[]{"litebans:" + var13};
            var10005 = var22;
         }

         var4.add(new BungeeTabCommand(var8, this.platform, var8.getPermission(), var10005));
      }

      for (Object var20 : (List)var4) {
         LiteCommand var5 = (LiteCommand)var20;
         this.handleLiteCommand2(var5);
      }
   }

   @Override
   public void handleLiteCommand2(@Nullable LiteCommand var1) {
      LiteBansCore var2 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      if (var1 instanceof BungeeTabCommand && var2.isCommandEnabled(((BungeeTabCommand)var1).getName())) {
         super.handleLiteCommand2(var1);
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

   private static final boolean checkLiteCommand(LiteCommand var0) {
      return Intrinsics.checkObject(var0.getName(), "togglechat");
   }

   private static final boolean checkFunction1(Function1 var0, Object var1) {
      return (Boolean)var0.invoke(var1);
   }
}
