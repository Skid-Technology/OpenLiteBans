package litebans.sync;

import java.util.Collection;
import java.util.Iterator;
import litebans.command.CommandRunnable;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PlayerInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class QueueFeederTask extends CommandRunnable {
   private final String string;
   private final String string2;
   private final String string3;

   public QueueFeederTask(@NotNull LiteBansPlatform var1, @NotNull String var2, @NotNull String var3, @Nullable String var4) {
      super(var1);
      this.string = var2;
      this.string2 = var3;
      this.string3 = var4;
   }

   @Override
   public void run() {
      try {
      AsyncQueue var1 = (AsyncQueue)this.getPlatform().getModule(AsyncQueue.class);
      String var3 = this.string;
      String var4 = this.string2;
      String var5 = this.string3;
      Iterable var7 = var1.getQueue();
      boolean var10000;
      if (var7 instanceof Collection && ((Collection)var7).isEmpty()) {
         var10000 = false;
      } else {
         Iterator var9 = var7.iterator();

         while (true) {
            if (!var9.hasNext()) {
               var10000 = false;
               break;
            }

            Object var10 = var9.next();
            PlayerInfo var11 = (PlayerInfo)var10;
            if (Intrinsics.checkObject(var11.getString(), var3) && Intrinsics.checkObject(var11.getUuid(), var4) && Intrinsics.checkObject(var11.getIp(), var5)
               )
             {
               var10000 = true;
               break;
            }
         }
      }

      if (!var10000) {
         var1.getQueue().put(new PlayerInfo(var3, var4, var5));
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
