package litebans.config;

import java.util.List;
import java.util.Map;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TemplateGroup {
   private final String name;
   private final PunishmentType type;
   private final Map weights;
   private final Map templates;
   private final List ladder;
   private final long ladderExpiry;

   public TemplateGroup(@NotNull String var1, @NotNull PunishmentType var2, @NotNull Map var3, @NotNull Map var4, @NotNull List var5, long var6) {
      this.name = var1;
      this.type = var2;
      this.weights = var3;
      this.templates = var4;
      this.ladder = var5;
      this.ladderExpiry = var6;
   }

   public final String getName() {
      return this.name;
   }

   public final PunishmentType getType() {
      return this.type;
   }

   public final Map getWeights() {
      return this.weights;
   }

   public final Map getTemplates() {
      return this.templates;
   }

   public final List getLadder() {
      return this.ladder;
   }

   public final long getLadderExpiry() {
      return this.ladderExpiry;
   }

   public final TemplateData getTemplateData(int var1) {
      return var1 >= this.ladder.size()
         ? (TemplateData)CollectionsKt.getObject3(this.ladder)
         : (var1 < 0 ? (TemplateData)CollectionsKt.getObject(this.ladder) : (TemplateData)this.ladder.get(var1));
   }

   @Override
   public String toString() {
      return "TemplateGroup(name="
         + this.name
         + ", type="
         + this.type
         + ", weights="
         + this.weights
         + ", templates="
         + this.templates
         + ", ladder="
         + this.ladder
         + ", ladderExpiry="
         + this.ladderExpiry
         + ')';
   }

   @Override
   public int hashCode() {
      int var1 = this.name.hashCode();
      var1 = var1 * 31 + this.type.hashCode();
      var1 = var1 * 31 + this.weights.hashCode();
      var1 = var1 * 31 + this.templates.hashCode();
      var1 = var1 * 31 + this.ladder.hashCode();
      return var1 * 31 + Long.hashCode(this.ladderExpiry);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof TemplateGroup)) {
         return false;
      } else {
         TemplateGroup var2 = (TemplateGroup)var1;
         if (!Intrinsics.checkObject(this.name, var2.name)) {
            return false;
         } else if (this.type != var2.type) {
            return false;
         } else if (!Intrinsics.checkObject(this.weights, var2.weights)) {
            return false;
         } else if (!Intrinsics.checkObject(this.templates, var2.templates)) {
            return false;
         } else {
            return !Intrinsics.checkObject(this.ladder, var2.ladder) ? false : this.ladderExpiry == var2.ladderExpiry;
         }
      }
   }
}
