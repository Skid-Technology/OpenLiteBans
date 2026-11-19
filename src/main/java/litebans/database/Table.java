package litebans.database;

import java.util.Locale;
import java.util.stream.IntStream;
import litebans.config.Config;
import litebans.core.LiteBansCore;
import litebans.core.PluginInstance;
import litebans.lib.kotlin.collections.EnumEntries;
import litebans.lib.kotlin.collections.EnumEntriesKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;

public enum Table implements CharSequence {
   BANS,
   MUTES,
   WARNINGS,
   KICKS,
   HISTORY,
   SYNC,
   SERVERS,
   CACHE8K,
   CONFIG,
   ALLOW;
   public static final TableCompanion Companion = new TableCompanion(null);
   private static final Table[] tableArray = getTableArray();
   private static final EnumEntries enumEntries = EnumEntriesKt.getEnumEntries(tableArray);

   public final boolean check() {
      boolean var10000;
      switch (TableWhenMappings.switchMap[this.ordinal()]) {
         case 1:
         case 2:
         case 3:
         case 4:
            var10000 = true;
            break;
         default:
            var10000 = false;
      }

      return var10000;
   }

   public final PunishmentType getPunishmentType() {
      PunishmentType var10000;
      switch (TableWhenMappings.switchMap[this.ordinal()]) {
         case 1:
            var10000 = PunishmentType.BAN;
            break;
         case 2:
            var10000 = PunishmentType.MUTE;
            break;
         case 3:
            var10000 = PunishmentType.WARN;
            break;
         case 4:
            var10000 = PunishmentType.KICK;
            break;
         default:
            throw new UnsupportedOperationException();
      }

      return var10000;
   }

   @Override
   public String toString() {
      return getTableNameDefault(this, null, false, 3, null);
   }

   public int getInt() {
      return this.toString().length();
   }

   @Override
   public IntStream chars() {
      return this.toString().chars();
   }

   public char getChar(int var1) {
      return this.toString().charAt(var1);
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      return this.toString().subSequence(var1, var2);
   }

   public final String getTableName(@NotNull Locale var1, boolean var2) {
      if (PluginInstance.Companion.check()) {
         LiteBansCore var3 = (LiteBansCore)PluginInstance.Companion.getLiteBansPlatform().getModule(LiteBansCore.class);
         String var4 = this.name().toLowerCase(var1);
         if (var3 != null) {
            Config var5 = var3.getConfig();
            var4 = var5.getString6() + var4;
            if (Intrinsics.checkObject(var3.getConfig().getSqlDriver(), "h2") && var2) {
               var4 = var4.toUpperCase(var1);
            }

            return var4;
         }
      }

      return this.name();
   }

   public static String getTableNameDefault(Table var0, Locale var1, boolean var2, int var3, Object var4) {
      if (var4 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var3 & 1) != 0) {
            var1 = Locale.ENGLISH;
         }

         if ((var3 & 2) != 0) {
            var2 = true;
         }

         return var0.getTableName(var1, var2);
      }
   }

   public static Table getTable(String var0) {
      return Enum.valueOf(Table.class, var0);
   }

   private static final Table[] getTableArray() {
      return new Table[]{BANS, MUTES, WARNINGS, KICKS, HISTORY, SYNC, SERVERS, CACHE8K, CONFIG, ALLOW};
   }

   @Override
   public final int length() {
      return this.getInt();
   }

   @Override
   public final char charAt(int var1) {
      return this.getChar(var1);
   }
}
