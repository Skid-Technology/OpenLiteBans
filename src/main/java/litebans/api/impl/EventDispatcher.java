package litebans.api.impl;

import java.util.Collection;
import litebans.api.Entry;
import litebans.api.Events;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class EventDispatcher {
   public static final EventDispatcher INSTANCE = new EventDispatcher();

   private EventDispatcher() {
   }

   public final void handlePunishmentStorage(@NotNull PunishmentStorage var1, @NotNull LiteBansPlatform var2, @NotNull CharSequence var3, @Nullable String var4) {
      Collection var8 = this.getCollection();
      if (!var8.isEmpty()) {
         var2.execute(new BroadcastSentNotifyTask(var8, var1, var3, var4));
      }
   }

   public final Entry getEntry(@NotNull LiteBansPlatform var1, @NotNull PunishmentEntry var2) {
      return new EntryImpl(
         var1,
         var2.getId(),
         var2.getType().getPunishmentType().toString(),
         var2.getUuid(),
         var2.getIp(),
         var2.getReason().toString(),
         var2.getExecutorUUID(),
         var2.getExecutorName(),
         var2.getRemovedByUuid(),
         var2.getRemovedByName(),
         String.valueOf(var2.getRemovedByReason()),
         var2.getDateStart(),
         var2.getDateEnd(),
         var2.getServerScope(),
         var2.getServerOrigin(),
         var2.getTemplate(),
         var2.isSilent(),
         var2.isIpban(),
         var2.isActive()
      );
   }

   public final void handlePunishmentStorage(@NotNull PunishmentStorage var1, @NotNull LiteBansPlatform var2, @NotNull PunishmentEntry var3) {
      Entry var4 = this.getEntry(var2, var3);
      Collection var8 = this.getCollection();
      if (!var8.isEmpty()) {
         var2.execute(new EntryAddedNotifyTask(var8, var1, var4));
      }
   }

   public final void handlePunishmentStorage2(@NotNull PunishmentStorage var1, @NotNull LiteBansPlatform var2, @NotNull PunishmentEntry var3) {
      Entry var4 = this.getEntry(var2, var3);
      Collection var8 = this.getCollection();
      if (!var8.isEmpty()) {
         var2.execute(new EntryRemovedNotifyTask(var8, var1, var4));
      }
   }

   public final void handlePunishmentStorage(@NotNull PunishmentStorage var1, @NotNull Throwable var2) {
      var1.getPlatform().getLogger().severe("An uncaught exception was thrown by a LiteBans API event listener.");
      if (var2 instanceof Exception) {
         var1.getPlatform().getLogger().severe("This is likely a bug in a plugin which is using the LiteBans API.");
      }

      var2.printStackTrace();
   }

   public final Collection getCollection() {
      Events var10000 = Events.get();
      Intrinsics.handleObject(var10000, "");
      return ((EventsImpl)var10000).getCopyOnWriteArrayList();
   }
}
