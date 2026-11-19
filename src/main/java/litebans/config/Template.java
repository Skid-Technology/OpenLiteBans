package litebans.config;

import java.util.List;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.Nullable;

public final class Template {
   public static final TemplateCompanion Companion = new TemplateCompanion(null);
   private final byte id;
   private final String name;
   private final PunishmentType type;
   private final TemplateData defaults;
   private final boolean ipTemplate;
   private final List ladder;
   private final long ladderExpiry;
   public static final byte byteValue = -1;
   public static final int intValue = -1;
   private static final TemplateData templateData = new TemplateData(null, null, null, null, CollectionsKt.getList(), CollectionsKt.getList());
   private static final Template INSTANCE = new Template((byte)-1, "", PunishmentType.BAN, templateData, false, CollectionsKt.getList(), 0L, null);

   private Template(byte var1, String var2, PunishmentType var3, TemplateData var4, boolean var5, List var6, long var7) {
      this.id = var1;
      this.name = var2;
      this.type = var3;
      this.defaults = var4;
      this.ipTemplate = var5;
      this.ladder = var6;
      this.ladderExpiry = var7;
   }

   public final byte getId() {
      return this.id;
   }

   public final String getName() {
      return this.name;
   }

   public final PunishmentType getType() {
      return this.type;
   }

   public final TemplateData getDefaults() {
      return this.defaults;
   }

   public final boolean isIpTemplate() {
      return this.ipTemplate;
   }

   public final List getLadder() {
      return this.ladder;
   }

   public final long getLadderExpiry() {
      return this.ladderExpiry;
   }

   public final TemplateData getTemplateData(int var1) {
      return this.ladder.isEmpty()
         ? this.defaults
         : (
            var1 >= this.ladder.size()
               ? (TemplateData)CollectionsKt.getObject3(this.ladder)
               : (var1 < 0 ? (TemplateData)CollectionsKt.getObject(this.ladder) : (TemplateData)this.ladder.get(var1))
         );
   }

   @Override
   public String toString() {
      return "Template(id="
         + UByte.getString(this.id)
         + ", name="
         + this.name
         + ", type="
         + this.type
         + ", defaults="
         + this.defaults
         + ", ipTemplate="
         + this.ipTemplate
         + ", ladder="
         + this.ladder
         + ", ladderExpiry="
         + this.ladderExpiry
         + ')';
   }

   @Override
   public int hashCode() {
      int var1 = UByte.getInt(this.id);
      var1 = var1 * 31 + this.name.hashCode();
      var1 = var1 * 31 + this.type.hashCode();
      var1 = var1 * 31 + this.defaults.hashCode();
      var1 = var1 * 31 + Boolean.hashCode(this.ipTemplate);
      var1 = var1 * 31 + this.ladder.hashCode();
      return var1 * 31 + Long.hashCode(this.ladderExpiry);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Template)) {
         return false;
      } else {
         Template var2 = (Template)var1;
         if (this.id != var2.id) {
            return false;
         } else if (!Intrinsics.checkObject(this.name, var2.name)) {
            return false;
         } else if (this.type != var2.type) {
            return false;
         } else if (!Intrinsics.checkObject(this.defaults, var2.defaults)) {
            return false;
         } else if (this.ipTemplate != var2.ipTemplate) {
            return false;
         } else {
            return !Intrinsics.checkObject(this.ladder, var2.ladder) ? false : this.ladderExpiry == var2.ladderExpiry;
         }
      }
   }

   public Template(byte var1, String var2, PunishmentType var3, TemplateData var4, boolean var5, List var6, long var7, DefaultConstructorMarker var9) {
      this(var1, var2, var3, var4, var5, var6, var7);
   }

   public static final TemplateData getTemplateData() {
      return templateData;
   }

   public static final Template getInstance() {
      return INSTANCE;
   }
}
