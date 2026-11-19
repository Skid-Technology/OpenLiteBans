package litebans.punishment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;
import litebans.database.Table;
import litebans.lib.kotlin.collections.EnumEntries;
import litebans.lib.kotlin.collections.EnumEntriesKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.util.Unreachable;

public enum PunishmentType implements CharSequence {
   BAN(null, Table.BANS, 1, null),
   MUTE(null, Table.MUTES, 1, null),
   WARN(null, Table.WARNINGS, 1, null),
   KICK(null, Table.KICKS, 1, null),
   UNBAN(BAN, null, 2, null),
   UNMUTE(MUTE, null, 2, null),
   UNWARN(WARN, null, 2, null);
   public static final PunishmentTypeCompanion Companion = new PunishmentTypeCompanion(null);
   private final PunishmentType punishmentType;
   private final Table table;
   private static final List list;
   private static final PunishmentType[] punishmentTypeArray = getPunishmentTypeArray();
   private static final EnumEntries enumEntries = EnumEntriesKt.getEnumEntries(punishmentTypeArray);

   private PunishmentType(PunishmentType var3, Table var4) {
      this.punishmentType = var3;
      this.table = var4;
   }

   PunishmentType(PunishmentType var3, Table var4, int var5, DefaultConstructorMarker var6) {
      this(
         ((var5 & 1) != 0 ? null : var3),
         ((var5 & 2) != 0 ? getTableDefault(var5, var3) : var4)
      );
   }

   private static Table getTableDefault(int var5, PunishmentType var3) {
      if ((var5 & 1) != 0) {
         var3 = null;
      }

      if (var3 != null && var3.table != null) {
         return var3.table;
      }

      return (Table)(Object) Unreachable.getVoid();
   }

   public final Table getTable() {
      return this.table;
   }

   public final PunishmentType getPunishmentType() {
      PunishmentType var10000 = this.punishmentType;
      if (this.punishmentType == null) {
         var10000 = this;
      }

      return var10000;
   }

   public int getInt() {
      return this.toString().length();
   }

   @Override
   public String toString() {
      String var1 = super.toString();
      return var1.toLowerCase(Locale.ENGLISH);
   }

   @Override
   public IntStream chars() {
      return this.toString().chars();
   }

   @Override
   public IntStream codePoints() {
      return this.toString().codePoints();
   }

   public char getChar(int var1) {
      return this.toString().charAt(var1);
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      return this.toString().subSequence(var1, var2);
   }

   public static PunishmentType getPunishmentType(String var0) {
      return Enum.valueOf(PunishmentType.class, var0);
   }

   private static final PunishmentType[] getPunishmentTypeArray() {
      return new PunishmentType[]{BAN, MUTE, WARN, KICK, UNBAN, UNMUTE, UNWARN};
   }

   @Override
   public final int length() {
      return this.getInt();
   }

   @Override
   public final char charAt(int var1) {
      return this.getChar(var1);
   }

   public static final List getList() {
      return list;
   }

   static {
      PunishmentType[] var0 = values();
      PunishmentType[] var2 = var0;
      Collection var3 = new ArrayList();
      int var5 = 0;

      for (int var6 = var0.length; var5 < var6; var5++) {
         PunishmentType var7 = var2[var5];
         if (var7 == var7.getPunishmentType()) {
            var3.add(var7);
         }
      }

      list = (List)var3;
   }
}
