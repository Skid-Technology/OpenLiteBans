package litebans.config;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.function.Supplier;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;

public final class LazyConfigFormat extends ConfigFormat {
   public final File file;
   public final Class classValue;

   public LazyConfigFormat(File var1, Class var2) {
      this.file = var1;
      this.classValue = var2;
   }

   @Override
   public void handleConfigNode(ConfigNode var1, Writer var2) {
      if (StringsKt.checkString2(this.file.getName(), ".json", false, 2, null)) {
         ConfigFormat.getConfigFormat(JsonConfigFormat.class).handleConfigNode(var1, var2);
      }
   }

   @Override
   public ConfigNode getConfigNode(Reader var1, ConfigNode var2) {
      return this.getConfigNode(var1);
   }

   @Override
   public ConfigNode getConfigNode(Reader var1) {
      try {
      Constructor var10000 = this.classValue.getConstructors()[0];
      Object[] var4 = new Object[]{var1};
      Object var5 = var10000.newInstance(var4);
      Intrinsics.handleObject(var5, "");
      Supplier var2 = (Supplier)var5;
      Map var3 = (Map)var2.get();
      return new ConfigNode(var3, null);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
