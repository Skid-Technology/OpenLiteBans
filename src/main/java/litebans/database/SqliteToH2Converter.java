package litebans.database;

import java.io.Closeable;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import litebans.command.CommandRunnable;
import litebans.config.Config;
import litebans.config.Message;
import litebans.core.MessageSender;
import litebans.core.PluginModule;
import litebans.lib.kotlin.io.AutoCloseableKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;

public final class SqliteToH2Converter {
   private SqliteToH2Converter() {
   }

   public final ThreadLocal getThreadLocal() {
      return SqlDateFormat.getThreadLocal();
   }

   public final boolean checkDatabaseManager(@NotNull DatabaseManager var1) {
      try {
      Config var2 = var1.getCore().getConfig();
      String var3 = var2.getSqlAddress();
      boolean var4 = StringsKt.checkCharSequence3(var3, ':', false, 2, null)
         || Intrinsics.checkObject(var3, "localhost")
         || Intrinsics.checkObject(var3, "127.0.0.1");
      if (var4) {
         File var6 = var1.platform.getDataFolder();
         String var7 = "litebans.sqlite";
         var3 = new File(var6, var7).getPath();
      }

      File var5 = new File(var3);
      if (var5.exists()) {
         PluginModule var44 = var1;
         String var46 = "Converting SQLite database to H2 database...";
         var44.platform.getLogger().info(var46);
         DatabaseManager var45 = new DatabaseManager(var1.platform);
         var45.setFlag(false);
         var45.onEnable();
         var2.setSqlDriver("h2");
         var1.getNotifications().handleString("h2");
         var1.createDataSource(var1.getCore());
         DatabaseManager.createTablesDefault(var1, 0L, false, false, false, 15, null);
         DatabaseManager var47 = var1;

         try {
            PunishmentStorage var9 = var47.openStorage();
            Closeable var10 = var9;
            Throwable var11 = null;

            try {
               PunishmentStorage var14 = var9;
               AutoCloseable var16 = var45.getConnection();
               Throwable var17 = null;

               try {
                  Connection var18 = (Connection)var16;
                  if (var18 != null) {
                     Table[] var20 = Table.values();
                     int var22 = 0;

                     for (int var23 = var20.length; var22 < var23; var22++) {
                        Table var24 = var20[var22];
                        var14.copyTable(var18, var24, true);
                     }

                     long var27 = var14.countEntries();
                     Sender var48 = var14.getPlatform().getConsole();
                     CharSequence var51 = Message.IMPORT_FINISH_LITEBANS.getString("amount", var27);
                     MessageSender.handleSenderDefault(CommandRunnable.messageSender, var48, var51, null, 4, null);
                     var18.close();
                     var45.onDisable();
                     if (var5.exists()) {
                        File var52 = var5.getParentFile();
                        String var55 = var5.getName() + ".backup";
                        File var49 = new File(var52, var55);
                        if (var5.renameTo(var49)) {
                           PluginModule var53 = var1;
                           var55 = "Renamed SQLite database: " + var5.getName() + " => " + var49.getName();
                           var53.platform.getLogger().info(var55);
                        }
                     }

                     PluginModule var50 = var1;
                     String var54 = "SQLite -> H2 conversion completed successfully.";
                     var50.platform.getLogger().info(var54);
                  }
               } catch (Throwable var39) {
                  var17 = var39;
                  throw var39;
               } finally {
                  AutoCloseableKt.handleAutoCloseable(var16, var17);
               }
            } catch (Throwable var41) {
               var11 = var41;
               throw var41;
            } finally {
               CloseableKt.handleCloseable(var10, var11);
            }
         } catch (SQLException var43) {
            if (!var1.isConnectionError(var43)) {
               throw var43;
            }
         }

         return true;
      } else {
         var2.setSqlDriver("h2");
         var1.getNotifications().handleString("h2");
         return false;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public SqliteToH2Converter(DefaultConstructorMarker var1) {
      this();
   }
}
