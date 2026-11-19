package litebans.command;

import java.util.Arrays;
import litebans.chat.ChatColorCode;
import litebans.core.LiteBansCore;
import litebans.database.DatabaseManager;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;

public final class CommandRegistrationTask implements Runnable {
   private final LiteBansPlatform liteBansPlatform;

   public CommandRegistrationTask(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   @Override
   public void run() {
      LiteBansCore var1;
      String[] var10005;
      label26: {
         var1 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
         Intrinsics.handleObject(var1);
         String var5 = "update";
         switch (var5.hashCode()) {
            case -1320563219:
               if (var5.equals("dupeip")) {
                  String[] var13 = new String[]{"alts", "litebans:alts", "checkalts", "litebans:checkalts", "litebans:dupeip"};
                  var10005 = var13;
                  break label26;
               }
               break;
            case 712910260:
               if (var5.equals("staffhistory")) {
                  String[] var11 = new String[]{"staffhist", "litebans:staffhistory"};
                  var10005 = var11;
                  break label26;
               }
               break;
            case 926934164:
               if (var5.equals("history")) {
                  String[] var8 = new String[]{"hist", "litebans:history"};
                  var10005 = var8;
                  break label26;
               }
         }

         String[] var12 = new String[]{"litebans:" + var5};
         var10005 = var12;
      }

      String[] var3 = var10005;
      TextArgs var10002 = new TextArgs(var1, Arrays.copyOf(var3, var3.length));
      CommandAlias var10000 = new CommandAlias(var10002.getInt(), "disabled", "false", null);
      CommandAlias var2 = var10000;
      var1.setCommandAlias(var2);
      CommandAlias var9 = ((DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class)).getCommandAlias();
      if (var9 != null) {
         var9.setString(var2.getString());
         var9.setString2(var2.getString2());
      }

      Sender var10 = this.liteBansPlatform.getConsole();
      String var7 = "[LiteBans] " + ChatColorCode.YELLOW;
      var10.sendMessage(var7 + "======================================");
      var10.sendMessage(var7 + "|| " + ChatColorCode.RED + "Plugin access has been disabled! " + ChatColorCode.YELLOW + "||");
      var10.sendMessage(var7 + "|| " + ChatColorCode.LIGHT_PURPLE + "If you have bought LiteBans,     " + ChatColorCode.YELLOW + "||");
      var10.sendMessage(var7 + "|| " + ChatColorCode.LIGHT_PURPLE + "contact Ruan on SpigotMC.        " + ChatColorCode.YELLOW + "||");
      var10.sendMessage(var7 + "======================================");
   }
}
