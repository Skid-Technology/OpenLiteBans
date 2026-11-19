package litebans.config;

import java.io.File;
import java.util.Locale;
import java.util.Locale.Category;
import java.util.logging.Logger;
import litebans.core.LiteBansCore;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MessagesLoader {
   private final LiteBansPlatform liteBansPlatform;
   private final File file;
   private final LiteBansCore liteBansCore;
   private Locale locale;
   private ConfigFile configFile;

   public MessagesLoader(@NotNull LiteBansPlatform var1, @NotNull File var2) {
      this.liteBansPlatform = var1;
      this.file = var2;
      this.liteBansCore = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
      this.locale = this.getLocale2();
   }

   public MessagesLoader(LiteBansPlatform var1, File var2, int var3, DefaultConstructorMarker var4) {
      this(var1, ((var3 & 2) != 0 ? new File((var1.getDataFolder()), ("messages.yml")) : var2));
   }

   public final LiteBansPlatform getLiteBansPlatform() {
      return this.liteBansPlatform;
   }

   public final File getFile() {
      return this.file;
   }

   public final LiteBansCore getLiteBansCore() {
      return this.liteBansCore;
   }

   public final Locale getLocale() {
      return this.locale;
   }

   public final void setLocale(@NotNull Locale var1) {
      this.locale = var1;
   }

   public final ConfigFile getConfigFile() {
      return this.configFile;
   }

   public final void setConfigFile(@Nullable ConfigFile var1) {
      this.configFile = var1;
   }

   public final Locale getLocale2() {
      return Locale.getDefault(Category.FORMAT);
   }

   public final void handleException(@NotNull Exception var1) {
      Logger var2 = this.liteBansPlatform.getLogger();
      String var4 = "messages";
      this.liteBansCore.reportConfigError(var4, var1);
      String var5 = "[!!] " + var4 + ".yml";
      if (this.file.exists()) {
         var2.severe(var5 + " may be invalid. Check with https://yaml-online-parser.appspot.com");
      } else {
         var2.severe(var5 + " could not be created in " + this.liteBansPlatform.getDataFolder().getAbsolutePath());
      }

      if (this.liteBansCore.isDebugLevel(1)) {
         this.liteBansCore.logException(var1);
      }
   }
}
