package litebans.importer;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.UUID;
import litebans.command.CommandRunnable;
import litebans.core.LiteBansCore;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.io.AutoCloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.ConsoleProfile;
import litebans.punishment.PunishmentType;
import litebans.randomid.UuidBytes;
import org.jetbrains.annotations.NotNull;

public final class LibertyBansImporter extends Importer {
   private final byte[] byteArray = UuidBytes.getByteArray(Sender.ZERO_UUID);

   public LibertyBansImporter(@NotNull LiteBansPlatform var1) {
      super(var1, "LibertyBans", "libertybans_", 0, 0L, 24, null);
   }

   public final byte[] getByteArray() {
      return this.byteArray;
   }

   @Override
   public void handleString(@NotNull String var1, @NotNull Connection var2, @NotNull PunishmentStorage var3, boolean var4) {
      handleConnectionDefault(this, var2, var4, var3, PunishmentType.BAN, null, 16, null);
      handleConnectionDefault(this, var2, var4, var3, PunishmentType.MUTE, null, 16, null);
      this.handleConnection(var2, var4, var3, PunishmentType.WARN, "warns");
   }

   private final void handleConnection(Connection var1, boolean var2, PunishmentStorage var3, PunishmentType var4, String var5) {
      try {
      String var6 = "SELECT * FROM " + this.getString2() + "applicable_" + var5;
      CommandRunnable var7 = this;
      LiteBansCore var65 = (LiteBansCore)var7.getPlatform().getModule(LiteBansCore.class);
      if (var65.isDebugEnabled()) {
         var65.debug(var6);
      }

      PreparedStatement var66 = var1.prepareStatement(var6);
      AutoCloseable var8 = var66;
      Throwable var9 = null;

      try {
         if (var66.execute()) {
            ResultSet var12 = var66.getResultSet();
            AutoCloseable var13 = var12;
            Throwable var14 = null;

            try {
               while (var12.next()) {
                  int var17 = var12.getInt("victim_type");
                  Intrinsics.handleObject(var12);
                  byte[] var21 = var12.getBytes("victim_uuid");
                  UUID var22 = null;
                  if (var21 != null && var21.length == 16) {
                     Intrinsics.handleObject(var21);
                     var22 = UuidBytes.getUUID(var21);
                  }

                  if (var22 == null || Intrinsics.checkObject(var22, ConsoleProfile.getUuid())) {
                     var21 = var12.getBytes("uuid");
                     if (var21 != null && var21.length == 16) {
                        Intrinsics.handleObject(var21);
                        var22 = UuidBytes.getUUID(var21);
                     }
                  }

                  CommandRunnable var23 = this;
                  LiteBansCore var69 = (LiteBansCore)var23.getPlatform().getModule(LiteBansCore.class);
                  if (var69.isDebugEnabled()) {
                     var69.debug("victim: " + var22);
                  }

                  UUID var27 = var22;
                  byte[] var68 = var12.getBytes("operator");
                  if (var68 == null || var68.length != 16) {
                     var68 = this.getByteArray();
                  }

                  Intrinsics.handleObject(var68);
                  UUID var18 = UuidBytes.getUUID(var68);
                  String var19 = null;
                  if (var2) {
                     String var70 = null;
                     if (var17 != 0) {
                        byte[] var24 = var12.getBytes("address");
                        if (var24.length >= 4) {
                           Intrinsics.handleObject(var24);
                           var70 = InetAddress.getByAddress(var24).getHostAddress().toString();
                        }
                     }

                     var19 = var70;
                  }

                  String var20 = var12.getString("reason");
                  long var29 = var12.getLong("start") * (long)1000;
                  long var31 = var12.getLong("end") * (long)1000;
                  String var10000;
                  if (var22 == null) {
                     var10000 = null;
                  } else {
                     label396: {
                        PreparedStatement var33 = var1.prepareStatement("SELECT name FROM " + this.getString2() + "latest_names WHERE uuid=?");
                        AutoCloseable var34 = var33;
                        Throwable var35 = null;

                        label338: {
                           String var39;
                           try {
                              var33.setBytes(1, UuidBytes.getByteArray(var27));
                              var33.execute();
                              ResultSet var38 = var33.getResultSet();
                              if (var38 == null || !var38.next()) {
                                 break label338;
                              }

                              var39 = var38.getString(1);
                           } catch (Throwable var59) {
                              var35 = var59;
                              throw var59;
                           } finally {
                              AutoCloseableKt.handleAutoCloseable(var34, var35);
                           }

                           var10000 = var39;
                           break label396;
                        }

                        var10000 = null;
                     }
                  }

                  String var25 = var10000;
                  Intrinsics.handleObject(var20);
                  String var28 = this.getString(var18);
                  Importer.handlePunishmentStorageDefault(this, var3, var25, var20, var29, var31, var28, var22, var18, var19, null, var4, 512, null);
               }
            } catch (Throwable var61) {
               var14 = var61;
               throw var61;
            } finally {
               AutoCloseableKt.handleAutoCloseable(var13, var14);
            }
         }
      } catch (Throwable var63) {
         var9 = var63;
         throw var63;
      } finally {
         AutoCloseableKt.handleAutoCloseable(var8, var9);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static void handleConnectionDefault(
      LibertyBansImporter var0, Connection var1, boolean var2, PunishmentStorage var3, PunishmentType var4, String var5, int var6, Object var7
   ) {
      if ((var6 & 16) != 0) {
         String var8 = var4.getTable().name();
         var5 = var8.toLowerCase(Locale.ENGLISH);
      }

      var0.handleConnection(var1, var2, var3, var4, var5);
   }
}
