package litebans.command;

import com.mojang.brigadier.CommandDispatcher;
import java.util.function.Function;
import litebans.core.LiteBansCore;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.fabric.FabricCommand;
import litebans.platform.fabric.FabricCommandRegistry;

public final class CommandPermissionMapper implements Function {
   public final LiteBansCore liteBansCore;
   public final FabricCommandRegistry fabricCommandRegistry;

   public CommandPermissionMapper(LiteBansCore var1, FabricCommandRegistry var2) {
      this.liteBansCore = var1;
      this.fabricCommandRegistry = var2;
   }

   public final void handleObject(Object var1) {
      String[] var2 = BaseCommand.commandAliases.getStringArray(this.liteBansCore);
      FabricCommandRegistry var3 = this.fabricCommandRegistry;
      int var5 = 0;

      for (int var6 = var2.length; var5 < var6; var5++) {
         String var7;
         Object var11;
         CommandDispatcher var12;
         StringBuilder var10000;
         String var10001;
         label73: {
            label72: {
               label71: {
                  var7 = var2[var5];
                  var11 = null;
                  Intrinsics.handleObject(var1, "");
                  var12 = (CommandDispatcher)var1;
                  var10000 = new StringBuilder().append("litebans.");
                  switch (var7.hashCode()) {
                     case -1396405339:
                        if (var7.equals("ban-ip")) {
                           break label71;
                        }
                        break;
                     case -1062781696:
                        if (var7.equals("muteip")) {
                           break label72;
                        }
                        break;
                     case -840405966:
                        if (var7.equals("unmute")) {
                           var10001 = "unmute.own";
                           break label73;
                        }
                        break;
                     case -840127329:
                        if (var7.equals("unwarn")) {
                           var10001 = "unwarn.own";
                           break label73;
                        }
                        break;
                     case 93503862:
                        if (var7.equals("banip")) {
                           break label71;
                        }
                        break;
                     case 111426262:
                        if (var7.equals("unban")) {
                           var10001 = "unban.own";
                           break label73;
                        }
                        break;
                     case 1231784084:
                        if (var7.equals("tempipmute")) {
                           break label72;
                        }
                        break;
                     case 1351111124:
                        if (var7.equals("tempmuteip")) {
                           break label72;
                        }
                        break;
                     case 1972486690:
                        if (var7.equals("tempbanip")) {
                           break label71;
                        }
                        break;
                     case 1979386420:
                        if (var7.equals("tempipban")) {
                           break label71;
                        }
                  }

                  var10001 = var7;
                  break label73;
               }

               var10001 = "ipban";
               break label73;
            }

            var10001 = "ipmute";
         }

         MappedCommand var14;
         label54: {
            String var17 = var10000.append(var10001).toString();
            var14 = new MappedCommand(var7, var7, (FabricCommand)var11, var17, var3);
            String var18 = var14.getName();
            switch (var18.hashCode()) {
               case -1320563219:
                  if (var18.equals("dupeip")) {
                     String[] var24 = new String[]{"alts", "litebans:alts", "checkalts", "litebans:checkalts", "litebans:dupeip"};
                     var10001 = /* cast-fix */(String)(var24);
                     break label54;
                  }
                  break;
               case 712910260:
                  if (var18.equals("staffhistory")) {
                     String[] var22 = new String[]{"staffhist", "litebans:staffhistory"};
                     var10001 = /* cast-fix */(String)(var22);
                     break label54;
                  }
                  break;
               case 926934164:
                  if (var18.equals("history")) {
                     String[] var21 = new String[]{"hist", "litebans:history"};
                     var10001 = /* cast-fix */(String)(var21);
                     break label54;
                  }
            }

            String[] var23 = new String[]{"litebans:" + var18};
            var10001 = /* cast-fix */(String)(var23);
         }

         String[] var16 = /* cast-fix */(String[])(var10001);
         var3.handleFabricCommand(new FabricCommand(var14, var14.getPermission(), var3.platform, var16), var12);
      }
   }

   @Override
   public Object apply(Object var1) {
      this.handleObject(var1);
      return Unit.INSTANCE;
   }
}
