package litebans.platform.fabric;

import java.util.function.Supplier;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;

public final class FabricLoginListenerSupplier implements Supplier {
   public final FabricLoginListenerRegistryIntermediary fabricLoginListenerRegistryIntermediary;
   public final FabricServerAdapter fabricServerAdapter;

   public FabricLoginListenerSupplier(FabricLoginListenerRegistryIntermediary var1, FabricServerAdapter var2) {
      this.fabricLoginListenerRegistryIntermediary = var1;
      this.fabricServerAdapter = var2;
   }

   public final ListenerHandle[] getListenerHandleArray() {
      FabricLoginListenerIntermediary1[] var1 = new FabricLoginListenerIntermediary1[1];
      LiteBansPlatform var10004 = this.fabricLoginListenerRegistryIntermediary.platform;
      FabricServerAdapter var2 = this.fabricServerAdapter;
      Intrinsics.handleObject(var2);
      var1[0] = new FabricLoginListenerIntermediary1(var10004, var2);
      return var1;
   }

   @Override
   public Object get() {
      return this.getListenerHandleArray();
   }
}
