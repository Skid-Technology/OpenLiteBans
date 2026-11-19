package litebans.platform.fabric;

import com.mojang.brigadier.CommandDispatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import litebans.FabricPlugin;
import litebans.command.BaseCommand;
import litebans.command.CommandInvokeTask;
import litebans.command.CommandPermissionMapper;
import litebans.command.CommandRunnable;
import litebans.command.MappedCommand;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
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
   a = 3
)
public final class FabricCommandRegistry extends FabricCommandRegistryBase {
   public FabricCommandRegistry(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public boolean isSupported() {
      return this.platform.getPlatformId() == 3;
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
         var23 = var16.toArray(new String[0]);
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
      LiteBansCore var1 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      var1.onLoad();
      super.onLoad();
      this.platform.setCommandRegistry(this);
      Intrinsics.handleObject(var1);
      LiteBansPlatform var10000 = this.platform;
      Intrinsics.handleObject(this.platform, "");
      ((FabricPlugin)var10000).getFabricServerAdapter().handleFunction(new CommandPermissionMapper(var1, this));
   }

   @Override
   public void handleFabricCommand(@NotNull FabricCommand var1, @NotNull CommandDispatcher var2) {
      LiteBansCore var3 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      if (var3.isCommandEnabled(var1.getName())) {
         super.handleFabricCommand(var1, var2);
         if (!(var1.getLiteCommand() instanceof MappedCommand)) {
            String[][] var4;
            String[] var10002;
            label118: {
               var4 = new String[][]{var1.getAliases(), null};
               String var6 = var1.getName();
               switch (var6.hashCode()) {
                  case -1320563219:
                     if (var6.equals("dupeip")) {
                        String[] var28 = new String[]{"alts", "litebans:alts", "checkalts", "litebans:checkalts", "litebans:dupeip"};
                        var10002 = var28;
                        break label118;
                     }
                     break;
                  case 712910260:
                     if (var6.equals("staffhistory")) {
                        String[] var26 = new String[]{"staffhist", "litebans:staffhistory"};
                        var10002 = var26;
                        break label118;
                     }
                     break;
                  case 926934164:
                     if (var6.equals("history")) {
                        String[] var9 = new String[]{"hist", "litebans:history"};
                        var10002 = var9;
                        break label118;
                     }
               }

               String[] var27 = new String[]{"litebans:" + var6};
               var10002 = var27;
            }

            var4[1] = var10002;
            Iterable var22 = ArraysKt.getList(var4);
            Collection var7 = new ArrayList();

            for (Object var10 : var22) {
               String var11 = (String)var10;
               Intrinsics.handleObject(var11);
               if (!StringsKt.checkCharSequence3(var11, ':', false, 2, null)) {
                  var7.add(var10);
               }
            }

            for (Object var25 : (List)var7) {
               String var8;
               String var12;
               StringBuilder var10000;
               String var10001;
               label99: {
                  label98: {
                     label97: {
                        var8 = (String)var25;
                        Intrinsics.handleObject(var8);
                        var12 = var1.getName();
                        var10000 = new StringBuilder().append("litebans.");
                        switch (var8.hashCode()) {
                           case -1396405339:
                              if (var8.equals("ban-ip")) {
                                 break label97;
                              }
                              break;
                           case -1062781696:
                              if (var8.equals("muteip")) {
                                 break label98;
                              }
                              break;
                           case -840405966:
                              if (var8.equals("unmute")) {
                                 var10001 = "unmute.own";
                                 break label99;
                              }
                              break;
                           case -840127329:
                              if (var8.equals("unwarn")) {
                                 var10001 = "unwarn.own";
                                 break label99;
                              }
                              break;
                           case 93503862:
                              if (var8.equals("banip")) {
                                 break label97;
                              }
                              break;
                           case 111426262:
                              if (var8.equals("unban")) {
                                 var10001 = "unban.own";
                                 break label99;
                              }
                              break;
                           case 1231784084:
                              if (var8.equals("tempipmute")) {
                                 break label98;
                              }
                              break;
                           case 1351111124:
                              if (var8.equals("tempmuteip")) {
                                 break label98;
                              }
                              break;
                           case 1972486690:
                              if (var8.equals("tempbanip")) {
                                 break label97;
                              }
                              break;
                           case 1979386420:
                              if (var8.equals("tempipban")) {
                                 break label97;
                              }
                        }

                        var10001 = var8;
                        break label99;
                     }

                     var10001 = "ipban";
                     break label99;
                  }

                  var10001 = "ipmute";
               }

               MappedCommand var14;
               label79: {
                  String var17 = var10000.append(var10001).toString();
                  var14 = new MappedCommand(var8, var12, var1, var17, this);
                  String var18 = var14.getName();
                  switch (var18.hashCode()) {
                     case -1320563219:
                        if (var18.equals("dupeip")) {
                           String[] var32 = new String[]{"alts", "litebans:alts", "checkalts", "litebans:checkalts", "litebans:dupeip"};
                           var10001 = var32;
                           break label79;
                        }
                        break;
                     case 712910260:
                        if (var18.equals("staffhistory")) {
                           String[] var30 = new String[]{"staffhist", "litebans:staffhistory"};
                           var10001 = var30;
                           break label79;
                        }
                        break;
                     case 926934164:
                        if (var18.equals("history")) {
                           String[] var21 = new String[]{"hist", "litebans:history"};
                           var10001 = var21;
                           break label79;
                        }
                  }

                  String[] var31 = new String[]{"litebans:" + var18};
                  var10001 = var31;
               }

               String[] var16 = var10001;
               this.handleFabricCommand(new FabricCommand(var14, var14.getPermission(), this.platform, var16), var2);
               if (var1 != null) {
                  this.register(var8, var14);
               }
            }
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
}
