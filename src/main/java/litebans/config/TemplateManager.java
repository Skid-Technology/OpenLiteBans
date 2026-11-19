package litebans.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import litebans.core.LiteBansCore;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TemplateManager {
   private final LiteBansPlatform liteBansPlatform;
   private final File file;
   private final boolean flag;
   private final ArrayList arrayList;
   private List list;
   private ConfigFile configFile;

   public TemplateManager(@NotNull LiteBansPlatform var1, @NotNull File var2, boolean var3) {
      this.liteBansPlatform = var1;
      this.file = var2;
      this.flag = var3;
      this.arrayList = new ArrayList();
      this.list = new ArrayList();
   }

   public TemplateManager(LiteBansPlatform var1, File var2, boolean var3, int var4, DefaultConstructorMarker var5) {
      this(var1, ((var4 & 2) != 0 ? new File((var1.getDataFolder()), ("templates.yml")) : var2), ((var4 & 4) != 0 ? false : var3));
   }

   public final LiteBansPlatform getLiteBansPlatform() {
      return this.liteBansPlatform;
   }

   public final File getFile() {
      return this.file;
   }

   public final ArrayList getArrayList() {
      return this.arrayList;
   }

   public final List getList() {
      return this.list;
   }

   public final void setList(@NotNull List var1) {
      this.list = var1;
   }

   public final ConfigFile getConfigFile() {
      return this.configFile;
   }

   public final void setConfigFile(@Nullable ConfigFile var1) {
      this.configFile = var1;
   }

   public final Template getTemplate(@NotNull PunishmentType var1, @NotNull String var2) {
      Iterable var3 = this.arrayList;
      Collection var8 = new ArrayList();

      for (Object var11 : var3) {
         Template var12 = (Template)var11;
         if (var12.getType() == var1.getPunishmentType()) {
            var8.add(var11);
         }
      }

      var3 = (List)var8;
      Iterator var5 = var3.iterator();

      Object var10000;
      while (true) {
         if (var5.hasNext()) {
            Object var6 = var5.next();
            Template var7 = (Template)var6;
            if (!StringsKt.checkString(var7.getName(), var2, true)) {
               continue;
            }

            var10000 = var6;
            break;
         }

         var10000 = null;
         break;
      }

      return (Template)var10000;
   }

   public final Template getTemplate(@NotNull PunishmentEntry var1) {
      return this.getTemplate(var1.getType(), var1.getTemplate() & 255);
   }

   public final Template getTemplate(@NotNull PunishmentType var1, byte var2) {
      Iterable var3 = this.arrayList;
      Collection var8 = new ArrayList();

      for (Object var11 : var3) {
         Template var12 = (Template)var11;
         if (var12.getType() == var1.getPunishmentType()) {
            var8.add(var11);
         }
      }

      var3 = (List)var8;
      Iterator var5 = var3.iterator();

      Object var10000;
      while (true) {
         if (var5.hasNext()) {
            Object var6 = var5.next();
            Template var7 = (Template)var6;
            if (var7.getId() != var2) {
               continue;
            }

            var10000 = var6;
            break;
         }

         var10000 = null;
         break;
      }

      return (Template)var10000;
   }

   public final Template getTemplate(@NotNull PunishmentType var1, int var2) {
      Iterable var3 = this.arrayList;
      Collection var8 = new ArrayList();

      for (Object var11 : var3) {
         Template var12 = (Template)var11;
         if (var12.getType() == var1.getPunishmentType()) {
            var8.add(var11);
         }
      }

      var3 = (List)var8;
      Iterator var5 = var3.iterator();

      Object var10000;
      while (true) {
         if (var5.hasNext()) {
            Object var6 = var5.next();
            Template var7 = (Template)var6;
            if ((var7.getId() & 255) != var2) {
               continue;
            }

            var10000 = var6;
            break;
         }

         var10000 = null;
         break;
      }

      return (Template)var10000;
   }

   public final TemplateGroup getTemplateGroup(@NotNull PunishmentType var1, @NotNull Template var2) {
      Iterable var3 = this.list;
      Collection var6 = new ArrayList();

      for (Object var9 : var3) {
         TemplateGroup var10 = (TemplateGroup)var9;
         if (var10.getType() == var1.getPunishmentType()) {
            var6.add(var9);
         }
      }

      var3 = (List)var6;
      Iterator var5 = var3.iterator();

      Object var10000;
      while (true) {
         if (var5.hasNext()) {
            Object var12 = var5.next();
            TemplateGroup var7 = (TemplateGroup)var12;
            if (!var7.getWeights().containsKey(var2)) {
               continue;
            }

            var10000 = var12;
            break;
         }

         var10000 = null;
         break;
      }

      return (TemplateGroup)var10000;
   }

   public final List getList(@NotNull PunishmentType var1) {
      Iterable var2 = this.arrayList;
      Collection var7 = new ArrayList();

      for (Object var10 : var2) {
         Template var11 = (Template)var10;
         if (var11.getType() == var1.getPunishmentType()) {
            var7.add(var10);
         }
      }

      var2 = (List)var7;
      Collection var5 = new ArrayList();

      for (Object var8 : var2) {
         Template var24 = (Template)var8;
         Iterable var26 = var24.getDefaults().getList2();
         boolean var10000;
         if (var26 instanceof Collection && ((Collection)var26).isEmpty()) {
            var10000 = false;
         } else {
            Iterator var13 = var26.iterator();

            while (true) {
               if (!var13.hasNext()) {
                  var10000 = false;
                  break;
               }

               Object var14 = var13.next();
               String var15 = (String)var14;
               if (StringsKt.checkCharSequence3(var15, "--hide", false, 2, null)) {
                  var10000 = true;
                  break;
               }
            }
         }

         if (var10000) {
            var5.add(var8);
         }
      }

      var2 = (List)var5;
      var5 = new ArrayList(CollectionsKt.getInt(var2, 10));

      for (Object var23 : var2) {
         Template var25 = (Template)var23;
         var5.add(UByte.getUByte(var25.getId()));
      }

      return (List)var5;
   }

   public final List getList(@NotNull ConfigSection var1, @Nullable String var2) {
      List var10000 = var1.getList(var2);
      if (var10000 == null) {
         String var4 = var1.getString(var2);
         if (var4 != null) {
            CharSequence var5 = var4;
            char[] var3 = new char[]{' '};
            var10000 = StringsKt.getList(var5, var3, false, 0, 6, null);
         } else {
            var10000 = null;
         }

         if (var10000 == null) {
            var10000 = CollectionsKt.getList();
         }
      }

      return var10000;
   }

   public final void handleException(@NotNull Exception var1) {
      if (this.flag) {
         throw new RuntimeException(var1);
      } else {
         LiteBansCore var2 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
         var2.reportConfigError("templates", var1);
         if (var2.isDebugLevel(1)) {
            var2.logException(var1);
         }
      }
   }
}
