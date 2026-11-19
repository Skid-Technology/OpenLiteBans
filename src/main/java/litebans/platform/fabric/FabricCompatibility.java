package litebans.platform.fabric;

import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FabricCompatibility {
   public static FabricServerAdapter getFabricServerAdapter(LiteBansPlatform var0, String var1) {
      FabricServerAdapterMojang var2 = getFabricServerAdapterMojang(var0, var1);
      if (var2 != null) {
         return var2;
      } else {
         String var3 = "method_64396";

         try {
            Class.forName("net.minecraft.class_3222").getMethod(var3);
            return new FabricServerAdapterIntermediary(var0);
         } catch (NoSuchMethodException var5) {
            return getFabricServerAdapter2(var0, var3);
         } catch (LinkageError | Exception var6) {
            throw new RuntimeException("Compatibility check failed (1.21) for " + var1, var6);
         }
      }
   }

   @Nullable
   private static final FabricServerAdapterMojang getFabricServerAdapterMojang(LiteBansPlatform var0, String var1) {
      try {
         Class.forName("net.minecraft.WorldVersion");
         return new FabricServerAdapterMojang(var0);
      } catch (ClassNotFoundException var3) {
         return null;
      } catch (LinkageError | Exception var4) {
         throw new RuntimeException("Compatibility check failed (26) for " + var1, var4);
      }
   }

   @NotNull
   public static FabricServerAdapter getFabricServerAdapter2(LiteBansPlatform var0, String var1) {
      try {
         Class.forName("net.minecraft.class_8710");
         return new FabricServerAdapterIntermediary120(var0);
      } catch (ClassNotFoundException var2) {
         return new FabricServerAdapterIntermediary121(var0);
      }
   }
}
