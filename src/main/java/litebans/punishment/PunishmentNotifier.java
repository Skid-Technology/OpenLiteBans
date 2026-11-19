package litebans.punishment;

import litebans.command.CommandRunnable;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.util.Unreachable;
import litebans.webhook.DiscordWebhookSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PunishmentNotifier extends CommandRunnable {
   private final PunishmentRequest punishmentRequest;
   private final Sender sender;
   private final PlayerHistory playerHistory;
   private final CharSequence charSequence;
   private final Sender sender2;
   private final boolean flag;
   private final boolean flag2;

   public PunishmentNotifier(
      @NotNull PunishmentRequest var1,
      @Nullable Sender var2,
      @Nullable PlayerHistory var3,
      @NotNull CharSequence var4,
      @NotNull LiteBansPlatform var5,
      @Nullable Sender var6,
      boolean var7,
      boolean var8
   ) {
      super(var5);
      this.punishmentRequest = var1;
      this.sender = var2;
      this.playerHistory = var3;
      this.charSequence = var4;
      this.sender2 = var6;
      this.flag = var7;
      this.flag2 = var8;
   }

   public PunishmentNotifier(
      PunishmentRequest var1,
      Sender var2,
      PlayerHistory var3,
      CharSequence var4,
      LiteBansPlatform var5,
      Sender var6,
      boolean var7,
      boolean var8,
      int var9,
      DefaultConstructorMarker var10
   ) {
      this(
         var1,
         var2,
         var3,
         var4,
         var5,
         ((var9 & 32) != 0 ? (Sender)CommandRunnable.messageSender.getThreadLocal().get() : var6),
         ((var9 & 64) != 0 ? true : var7),
         ((var9 & 128) != 0 ? var1.getPunishmentEntry().isSilent() : var8)
      );
   }

   public final PunishmentRequest getPunishmentRequest() {
      return this.punishmentRequest;
   }

   public final Sender getSender2() {
      return this.sender;
   }

   public final PlayerHistory getPlayerHistory() {
      return this.playerHistory;
   }

   public final Sender getSender22() {
      return this.sender2;
   }

   public final boolean isFlag() {
      return this.flag;
   }

   public final boolean isFlag2() {
      return this.flag2;
   }

   @Override
   public void run() {
      CommandRunnable var1 = this;
      ((DatabaseManager)var1.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, (PunishmentStorage) st));
   }

   private static final Unit getUnit(PunishmentNotifier var0, PunishmentStorage var1) {
      CharSequence var6 = var0.charSequence;
      CharSequence var8 = var6;
      if (var0.isFlag2()) {
         CharSequence var9 = Message.BAN_SILENT_PREFIX;
         var8 = "" + var9 + var6;
      }

      if (var0.getPlayerHistory() != null) {
         var8 = EntryReader.getCharSequence(var1, var8, var0.getPlayerHistory(), false, 2, null);
      }

      var8 = EntryPlaceholders.getString(var1, var8, var0.getPunishmentRequest());
      CharSequence var4 = EntryReader.getCharSequence(
         var1, var1.getCharSequence2(var8, var0.getSender2()), var0.getPunishmentRequest().getPunishmentEntry(), false, 2, null
      );
      PunishmentRequest var19 = var0.getPunishmentRequest();
      PunishmentEntry var10000 = var19.getPunishmentEntry();
      Intrinsics.handleObject(var10000);
      PunishmentEntry var21 = var10000;
      Flag var12 = var0.getPunishmentRequest().isExtraSilent() ? Flag.BROADCAST_EXTRA_SILENT : (var0.isFlag2() ? Flag.BROADCAST_SILENT : Flag.BROADCAST);
      CommandRunnable var22 = var0;
      NotificationManager var10 = (NotificationManager)var22.getPlatform().getModule(NotificationManager.class);
      NotificationManager.broadcastDefault(
         var10, var1, var4, var12.getString(), var21.getType(), var21.getServerOrigin(), false, false, var0.getSender22(), 48, null
      );
      PunishmentService.broadcastDefault(var1, var12, var4, var21.getType(), null, 8, null);
      PunishmentRequest var13 = var0.getPunishmentRequest();
      var10000 = var13.getPunishmentEntry();
      Intrinsics.handleObject(var10000);
      PunishmentEntry var15 = var10000;
      PunishmentType var16 = var15.getType();
      if ((var16.getPunishmentType() == PunishmentType.MUTE || var16.getPunishmentType() == PunishmentType.WARN) && var0.isFlag() && var0.getSender2() != null) {
         String var27;
         switch (PunishmentNotifierWhenMappings.switchMap[var15.getType().getPunishmentType().ordinal()]) {
            case 1:
               var27 = "banned";
               break;
            case 2:
               var27 = "muted";
               break;
            case 3:
               var27 = "warned";
               break;
            case 4:
               var27 = "kicked";
               break;
            default:
               Unreachable.getVoid();
               throw new NoWhenBranchMatchedException();
         }

         String var25 = var27;
         CommandRunnable var17 = var0;
         NotificationManager var18 = (NotificationManager)var17.getPlatform().getModule(NotificationManager.class);
         var18.handlePunishmentStorage(var1, var0.getSender2(), var15, var13.getIntValue(), var25);
      }

      CommandRunnable var24 = var0;
      String var23 = ((LiteBansCore)var24.getPlatform().getModule(LiteBansCore.class))
         .getWebhookManager()
         .getString(var21.getMessagePrefix(var0.getPunishmentRequest()));
      if (var23.length() > 0 && (var12 == Flag.BROADCAST || var12 == Flag.BROADCAST_SILENT)) {
         DiscordWebhookSender.webhookEmbedBuilder.handlePunishmentStorage(var1, var0.getPunishmentRequest(), var23);
      }

      return Unit.INSTANCE;
   }
}
