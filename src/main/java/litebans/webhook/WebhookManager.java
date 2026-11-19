package litebans.webhook;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import litebans.config.ConfigFile;
import litebans.core.LiteBansCore;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentType;
import litebans.punishment.PunishmentTypeCompanion;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;

public final class WebhookManager {
   private final LiteBansPlatform liteBansPlatform;
   private final File file;
   private final HashMap hashMap;
   private String string;
   private final LiteBansCore liteBansCore;

   public WebhookManager(@NotNull LiteBansPlatform var1, @NotNull File var2) {
      this.liteBansPlatform = var1;
      this.file = var2;
      this.hashMap = new HashMap();
      this.string = "";
      this.liteBansCore = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
   }

   public WebhookManager(LiteBansPlatform var1, File var2, int var3, DefaultConstructorMarker var4) {
      this(var1, ((var3 & 2) != 0 ? new File((var1.getDataFolder()), ("webhooks.yml")) : var2));
   }

   public final String getString(@NotNull String var1) {
      String var2 = this.hashMap.getOrDefault(var1, "").toString();
      LiteBansCore var5 = this.liteBansCore;
      byte var6 = 1;
      if (var5.isDebugLevel(var6)) {
         var5.debug("Fetch webhook: " + var1 + " => '" + var2 + '\'');
      }

      return !Intrinsics.checkObject(var2, "on") && !Intrinsics.checkObject(var2, "true") && var2.length() != 0
         ? (!Intrinsics.checkObject(var2, "off") && !Intrinsics.checkObject(var2, "false") ? var2 : "")
         : this.string;
   }

   public final WebhookManager getWebhookManager() {
      try {
         this.liteBansPlatform.saveDefaultResource("webhooks.yml");
         ConfigFile var4 = this.liteBansPlatform.loadConfig(this.file).getConfigFile4();
         var4.getConfigFile(ConfigFile.getString2("webhooks.yml", null));
         String var5 = var4.getString("default_webhook", "");
         CharSequence var6 = var5;
         this.string = var6 == null || var6.length() == 0 ? this.liteBansCore.getConfig().getNotifyNotifyDiscord() : var5;
         this.hashMap.clear();
         PunishmentTypeCompanion var7 = PunishmentType.Companion;
         Iterable var9 = var7.getList();
         Collection var12 = new ArrayList();

         for (Object var15 : var9) {
            PunishmentType var16 = (PunishmentType)var15;
            List var10000;
            switch (WebhookManagerWhenMappings.switchMap[var16.ordinal()]) {
               case 1:
                  var10000 = CollectionsKt.getList(var16.getPunishmentType());
                  break;
               case 2:
                  Object[] var44 = new Object[]{var16.getPunishmentType(), "un" + var16.getPunishmentType()};
                  var10000 = CollectionsKt.getList(var44);
                  break;
               default:
                  Object[] var20 = new Object[]{
                     var16.getPunishmentType(),
                     "temp_" + var16.getPunishmentType(),
                     "temp_ip" + var16.getPunishmentType(),
                     "ip" + var16.getPunishmentType(),
                     "un" + var16.getPunishmentType()
                  };
                  var10000 = CollectionsKt.getList(var20);
            }

            Iterable var21 = var10000;
            Collection var23 = new ArrayList(CollectionsKt.getInt(var21, 10));

            for (Object var26 : var21) {
               var23.add(var26.toString());
            }

            Iterable var41 = (List)var23;
            CollectionsKt.checkCollection(var12, var41);
         }

         Collection var53 = (List)var12;
         var9 = var7.getList();
         Collection var30 = var53;
         var12 = new ArrayList();

         for (Object var40 : var9) {
            PunishmentType var42 = (PunishmentType)var40;
            List var54;
            switch (WebhookManagerWhenMappings.switchMap[var42.ordinal()]) {
               case 1:
                  var54 = CollectionsKt.getList(var42.getPunishmentType());
                  break;
               case 2:
                  Object[] var46 = new Object[]{var42.getPunishmentType(), "un" + var42.getPunishmentType()};
                  var54 = CollectionsKt.getList(var46);
                  break;
               default:
                  Object[] var22 = new Object[]{
                     var42.getPunishmentType(),
                     "temp_" + var42.getPunishmentType(),
                     "temp_ip" + var42.getPunishmentType(),
                     "ip" + var42.getPunishmentType(),
                     "un" + var42.getPunishmentType()
                  };
                  var54 = CollectionsKt.getList(var22);
            }

            Iterable var47 = var54;
            Collection var49 = new ArrayList(CollectionsKt.getInt(var47, 10));

            for (Object var28 : var47) {
               var49.add(var28.toString());
            }

            Iterable var45 = (List)var49;
            Collection var48 = new ArrayList(CollectionsKt.getInt(var45, 10));

            for (Object var51 : var45) {
               String var52 = (String)var51;
               var48.add("silent_" + var52);
            }

            Iterable var43 = (List)var48;
            CollectionsKt.checkCollection(var12, var43);
         }

         for (Object var36o : CollectionsKt.getList(var30, (List)var12)) {
            String var36 = (String) var36o;
            this.hashMap.put(var36, this.getString(var4, "webhooks." + var36, ""));
         }

         this.hashMap.put("alts", this.getString(var4, "webhooks.alts", "off"));
      } catch (Exception var34) {
         this.liteBansCore.reportConfigError("webhooks", var34);
      }

      return this;
   }

   private final String getString(ConfigFile var1, String var2, String var3) {
      Object var4 = var1.getObject(var2, var3);
      if (var4 instanceof Boolean) {
         return (Boolean)var4 ? "on" : "off";
      } else if (var4 instanceof String) {
         return (String)var4;
      } else {
         Unreachable.getVoid();
         throw new NoWhenBranchMatchedException();
      }
   }
}
