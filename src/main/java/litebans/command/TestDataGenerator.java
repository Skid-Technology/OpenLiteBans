package litebans.command;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import litebans.chat.ChatColorCode;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentService;
import litebans.punishment.PunishmentType;
import litebans.randomid.LongBitSet;
import org.jetbrains.annotations.NotNull;

public final class TestDataGenerator extends CommandRunnable {
   private final String[] stringArray;

   public TestDataGenerator(@NotNull String[] var1, @NotNull LiteBansPlatform var2) {
      super(var2);
      this.stringArray = var1;
   }

   @Override
   public void run() {
      LongBitSet var1 = new LongBitSet(0L, 1, null);
      PunishmentType[] var3 = PunishmentType.values();
      int var5 = 0;

      for (int var6 = var3.length; var5 < var6; var5++) {
         PunishmentType var7 = var3[var5];
         if (Intrinsics.checkObject(var7.name(), this.stringArray[1])) {
            int var11 = Integer.parseInt(this.stringArray[2]);
            int var15 = Integer.parseInt(this.stringArray[3]);
            int var16 = Integer.parseInt(this.stringArray[4]);
            byte var13 = 15;
            byte var14 = 40;
            List var8 = new ArrayList(var11);
            List var9 = new ArrayList(var11);
            CommandRunnable var10 = this;
            ((DatabaseManager)var10.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, var11, var8, var1, var15, var16, var13, var14, var7, var9, (PunishmentStorage) st));
            return;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   private static final Unit getUnit(
      TestDataGenerator var0,
      int var1,
      List var2,
      LongBitSet var3,
      int var4,
      int var5,
      int var6,
      int var7,
      PunishmentType var8,
      List var9,
      PunishmentStorage var10
   ) {
      if (var10.isClosed()) {
         PunishmentService.reply(var0, Message.ERROR_NO_DATABASE);
         return Unit.INSTANCE;
      } else {
         for (int var11 = 0; var11 < var1; var11++) {
            var2.add(new UUID(var3.getLong(), var3.getLong()).toString());
         }

         PunishmentService.reply(var0, ArgUtils.getString(ChatColorCode.GREEN, "Generating entries..."));
         long var28 = DatabaseManager.currentTimeMillisDefault(var10.getDatabaseManager(), false, 1, null);

         for (int var13 = 0; var13 < var1; var13++) {
            String var14 = (String)var2.get(var13);
            boolean var15 = var3.getInt(100) < var4;
            boolean var16 = var3.getInt(100) < var5;
            boolean var17 = var16 && var6 > 0 && var3.getInt(100) < var6;
            boolean var18 = var3.getInt(100) < var7;
            boolean var19 = !var18;
            long var20;
            if (var15) {
               var20 = 0L;
            } else {
               var20 = var28 + (long)300000000 + (long)var3.getInt(1000000000);
               if (var18) {
                  var20 = var28 - (long)var3.getInt(10000000);
               }
            }

            int var22 = var3.getInt(252) + 2;
            int var23 = var3.getInt(252) + 2;
            int var24 = var3.getInt(252) + 2;
            String var25 = String.valueOf(var3.getInt(252) + 2);
            if (var16 && var17) {
               var25 = "%";
            }

            String var26 = "" + var22 + '.' + var23 + '.' + var24 + '.' + var25;
            PunishmentEntry var27 = new PunishmentEntry(var8, var14, var26, "", "", "", "*", "", var28, var20, (byte)0, false, var16, var19, 0L, 17408, null);
            var9.add(var27);
         }

         PunishmentService.reply(var0, ArgUtils.getString(ChatColorCode.GREEN, "Adding entries..."));
         CommandRunnable var30 = var0;
         byte var29 = ((LiteBansCore)var30.getPlatform().getModule(LiteBansCore.class)).getDebugLevel();
         var30 = var0;
         ((LiteBansCore)var30.getPlatform().getModule(LiteBansCore.class)).setDebugLevel((byte)0);

         for (Object var36 : var9) {
            PunishmentEntry var37 = (PunishmentEntry)var36;
            var10.insertEntry(var37);
         }

         var30 = var0;
         ((LiteBansCore)var30.getPlatform().getModule(LiteBansCore.class)).setDebugLevel(var29);
         long var34 = DatabaseManager.currentTimeMillisDefault(var10.getDatabaseManager(), false, 1, null);
         PunishmentService.reply(var0, ArgUtils.getString(ChatColorCode.GREEN, "Done in ") + (var34 - var28) + " ms.");
         return Unit.INSTANCE;
      }
   }
}
