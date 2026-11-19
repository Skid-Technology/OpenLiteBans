package litebans.api.impl;

import litebans.api.Entry;
import litebans.api.RandomID;
import litebans.config.Template;
import litebans.config.TemplateManager;
import litebans.core.LiteBansCore;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.DurationParser;
import litebans.punishment.DurationUnit;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class EntryImpl extends Entry {
   private final LiteBansPlatform liteBansPlatform;

   public EntryImpl(
      @NotNull LiteBansPlatform var1,
      long var2,
      @Nullable String var4,
      @Nullable String var5,
      @Nullable String var6,
      @Nullable String var7,
      @Nullable String var8,
      @Nullable String var9,
      @Nullable String var10,
      @Nullable String var11,
      @Nullable String var12,
      long var13,
      long var15,
      @Nullable String var17,
      @Nullable String var18,
      byte var19,
      boolean var20,
      boolean var21,
      boolean var22
   ) {
      super(var2, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var15, var17, var18, var19, var20, var21, var22);
      this.liteBansPlatform = var1;
   }

   @Override
   public String getDurationString() {
      long var1 = this.getDuration();
      return DurationParser.getStringDefault(DurationUnit.durationParser, var1, false, 2, null);
   }

   @Override
   public long getRemainingDuration(long var1) {
      return !this.isPermanent() && !this.isExpired(var1) ? this.getDateEnd() - var1 : -1L;
   }

   @Override
   public String getRemainingDurationString(long var1) {
      long var3 = this.getRemainingDuration(var1);
      return DurationParser.getStringDefault(DurationUnit.durationParser, var3, false, 2, null);
   }

   @Override
   public String getRandomID() {
      return RandomID.get().convert(this.getId());
   }

   @Override
   public long getDuration() {
      return this.isPermanent() ? -1L : this.getDateEnd() - this.getDateStart();
   }

   @Override
   public boolean isExpired(long var1) {
      return !this.isPermanent() && var1 >= this.getDateEnd();
   }

   @Override
   public boolean isPermanent() {
      return this.getDateEnd() <= 0L;
   }

   @Override
   public int getTemplateID() {
      return UByte.getByte(this.getTemplate()) & 0xFF;
   }

   @Override
   public String getTemplateName() {
      LiteBansCore var1 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
      TemplateManager var10000 = var1.getTemplateManager();
      PunishmentType var10001 = PunishmentType.Companion.getPunishmentType(this.getType());
      Intrinsics.handleObject(var10001);
      Template var2 = var10000.getTemplate(var10001, this.getTemplateID());
      if (var2 != null) {
         String var3 = var2.getName();
         if (var3 != null) {
            return var3;
         }
      }

      return "";
   }

   @Override
   public boolean hasTemplate() {
      return this.getTemplateID() != (-1 & 255);
   }
}
