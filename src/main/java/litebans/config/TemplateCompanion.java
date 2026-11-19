package litebans.config;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;

public final class TemplateCompanion {
   private TemplateCompanion() {
   }

   public final TemplateData getTemplateData() {
      return Template.getTemplateData();
   }

   public final Template getTemplate() {
      return Template.getInstance();
   }

   public TemplateCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
