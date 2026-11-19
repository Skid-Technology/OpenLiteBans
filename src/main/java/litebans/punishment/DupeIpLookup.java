package litebans.punishment;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import litebans.command.AbortException;
import litebans.command.CommandRunnable;
import litebans.command.LiteBansCommand;
import litebans.command.OperationCancelledException;
import litebans.command.SilentAbortException;
import litebans.config.Config;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DupeIpLookup extends CommandRunnable {
   private final String string;
   private final PunishmentStorage punishmentStorage;
   private final Sender sender;
   private final boolean flag;
   private int intValue;
   private boolean flag2;
   private boolean flag3;
   private boolean flag4;
   private boolean flag5;
   private int intValue2;
   private int intValue3;
   private final ArrayList arrayList;
   private PlayerHistory playerHistory;
   private String string2;
   private String string3;

   public DupeIpLookup(@NotNull String var1, @NotNull PunishmentStorage var2, @Nullable Sender var3, boolean var4, int var5) {
      super(var2.getPlatform());
      this.string = var1;
      this.punishmentStorage = var2;
      this.sender = var3;
      this.flag = var4;
      this.intValue = var5;
      this.arrayList = new ArrayList();
      this.string3 = "";
   }

   public DupeIpLookup(String var1, PunishmentStorage var2, Sender var3, boolean var4, int var5, int var6, DefaultConstructorMarker var7) {
      this(var1, var2, ((var6 & 4) != 0 ? null : var3), ((var6 & 8) != 0 ? true : var4), ((var6 & 16) != 0 ? 0 : var5));
   }

   public final boolean isFlag2() {
      return this.flag2;
   }

   public final boolean isFlag3() {
      return this.flag3;
   }

   public final boolean isFlag4() {
      return this.flag4;
   }

   public final boolean isFlag5() {
      return this.flag5;
   }

   public final int getIntValue2() {
      return this.intValue2;
   }

   public final int getIntValue3() {
      return this.intValue3;
   }

   public final ArrayList getArrayList() {
      return this.arrayList;
   }

   public final PlayerHistory getPlayerHistory() {
      return this.playerHistory;
   }

   public final String getString2() {
      return this.string2;
   }

   public final String getString3() {
      return this.string3;
   }

   public final DupeIpLookup getDupeIpLookup() {
      try {
         try {
            if (LiteBansCommand.object == null) {
               throw new OperationCancelledException();
            }
         } catch (Exception var10) {
            CommandRunnable var2 = this;
            LiteBansCore.handleObjectDefault((LiteBansCore)var2.getPlatform().getModule(LiteBansCore.class), var10, 0, 2, null);
            this.getVoid();
            throw new NoWhenBranchMatchedException();
         }

         if (this.intValue == 0) {
            CommandRunnable var1 = this;
            this.intValue = ((LiteBansCore)var1.getPlatform().getModule(LiteBansCore.class)).getConfig().getNotifyDupeipScanLimit();
         }

         String var12 = this.string;
         String var13 = var12.toLowerCase(Locale.ENGLISH);
         List var3 = this.punishmentStorage.findHistoryByIp(var12, this.intValue);
         PlayerHistory var10000 = (PlayerHistory)CollectionsKt.getObject2(var3);
         if (var10000 == null) {
            this.getVoid();
            throw new NoWhenBranchMatchedException();
         }

         PlayerHistory var4 = var10000;
         this.playerHistory = var4;
         this.handleString(var13);
         this.handleString(var4.getUuid());
         HashSet var5 = new HashSet(var3.size());
         Iterator var6 = var3.iterator();

         while (var6.hasNext()) {
            String var8 = ((PlayerHistory)var6.next()).getIp2();
            if (var8 != null && !StringsKt.checkCharSequence(var8, '#', false, 2, null)) {
               var5.add(var8);
            }
         }

         boolean var10001;
         label60: {
            this.intValue2 = var5.size();
            this.string2 = var4.getIp();
            CommandRunnable var14 = this;
            if (((LiteBansCore)var14.getPlatform().getModule(LiteBansCore.class)).getConfig().isDupeipScanAllIps()) {
               CommandRunnable var15 = this;
               if (!((LiteBansCore)var15.getPlatform().getModule(LiteBansCore.class)).getConfig().isDeletePreviousHistory() && this.intValue2 > 1) {
                  var10001 = true;
                  break label60;
               }
            }

            var10001 = false;
         }

         this.flag5 = var10001;
         List var16;
         if (TargetParser.INSTANCE.checkString2(var12)) {
            var16 = var3;
            this.string2 = var12;
         } else {
            var16 = this.punishmentStorage.findHistoryByIp(this.string2, this.intValue);
         }

         if (var16.isEmpty()) {
            this.getVoid();
            throw new NoWhenBranchMatchedException();
         }

         Sender[] var7 = this.getPlatform().getOnlineSenders();
         HashSet var17 = new HashSet(8);
         HashMap var9 = new HashMap(8);
         List var10004 = this.arrayList;
         Intrinsics.handleObject(var7);
         this.handleHashSet(var17, var9, var16, var10004, var7);
         if (this.flag5) {
            this.handleList(var3, var17, var9, var7);
         }

         this.intValue3 = this.arrayList.size();
         this.string3 = CollectionsKt.getString(this.arrayList, Message.DUPEIP_SEPARATOR, null, null, 0, null, a -> getCharSequence((DupeIPResult) a), 30, null);
      } catch (AbortException var11) {
         if (!this.flag && this.sender != null) {
            MessageSender.handleSenderDefault(CommandRunnable.messageSender, this.sender, String.valueOf(var11.getMessage()), null, 4, null);
         }

         this.flag2 = true;
      }

      return this;
   }

   private final void handleList(List var1, HashSet var2, HashMap var3, Sender[] var4) {
      int var5 = 1;

      for (int var6 = this.intValue2; var5 < var6; var5++) {
         String var7 = ((PlayerHistory)var1.get(var5)).getIp2();
         List var8 = this.punishmentStorage.findHistoryByIp(var7, this.intValue);
         if (!var8.isEmpty()) {
            this.handleHashSet(var2, var3, var8, this.arrayList, var4);
         }
      }
   }

   private final void handleString(String var1) {
      CommandRunnable var3 = this;
      Set var2 = ((LiteBansCore)var3.getPlatform().getModule(LiteBansCore.class)).getConfig().getHiddenDupeipPlayers();
      if (!var2.isEmpty()) {
         if (CollectionsKt.checkIterable(var2, var1)) {
            this.getVoid2();
            throw new NoWhenBranchMatchedException();
         }
      }
   }

   private final Void getVoid() {
      throw new SilentAbortException();
   }

   private final Void getVoid2() {
      boolean var14;
      PlayerHistory var1 = this.playerHistory;
      if (var1 != null) {
         String var10000 = var1.getUuid();
         if (var10000 != null) {
            String var2 = var10000;
            Sender[] var3 = this.getPlatform().getOnlineSenders();
            Intrinsics.handleObject(var3);
            Sender[] var5 = var3;
            int var7 = 0;
            int var8 = var3.length;

            while (true) {
               if (var7 >= var8) {
                  var14 = false;
                  break;
               }

               Sender var9 = var5[var7];
               if (Intrinsics.checkObject(var9.getUuidString(), var2)) {
                  var14 = true;
                  break;
               }

               var7++;
            }

            boolean var4 = var14;
            Message var11 = var4 ? Message.DUPEIP_ONLINE : Message.DUPEIP_OFFLINE;
            if (this.sender != null) {
               PunishmentStorage var12 = this.punishmentStorage;
               MessageSender.handleSenderDefault(
                  CommandRunnable.messageSender,
                  this.sender,
                  EntryReader.getCharSequence(var12, Message.DUPEIP_START_NO_IP, var1, false, 2, null),
                  null,
                  4,
                  null
               );
               MessageSender var15 = CommandRunnable.messageSender;
               Sender var10001 = this.sender;
               CharSequence var13 = var11;
               CharSequence var10 = var1.getString();
               MessageSender.handleSenderDefault(var15, var10001, "" + var13 + var10, null, 4, null);
            }

            throw new AbortException(Message.DUPEIP_END);
         }
      }

      throw new SilentAbortException();
   }

   public final void handleHashSet(@NotNull HashSet var1, @NotNull HashMap var2, @NotNull List var3, @NotNull List var4, @NotNull Sender[] var5) {
      Object var39;
      Object var40;
      CommandRunnable var7 = this;
      Config var6 = ((LiteBansCore)var7.getPlatform().getModule(LiteBansCore.class)).getConfig();
      Set var25 = var6.getHiddenDupeipPlayers();
      Iterator var8 = var3.iterator();

      while (true) {
         String var10;
         String var11;
         String var12;
         Date var13;
         boolean var10000;
         label146:
         do {
            while (true) {
               if (!var8.hasNext()) {
                  return;
               }

               PlayerHistory var9 = (PlayerHistory)var8.next();
               var10 = var9.getString2();
               var11 = var9.getUuid2();
               var12 = var9.getIp2();
               var13 = var9.getDate2();
               if (var11 != null
                  && var10 != null
                  && (
                     Intrinsics.checkObject(var11, this.playerHistory != null ? this.playerHistory.getUuid() : null)
                        || System.currentTimeMillis() - var13.getTime() <= var6.getNotifyDupeipScanFilterDuration()
                  )) {
                  String var14 = (String)var2.get(var11);
                  if (var14 == null) {
                     var2.put(var11, var10);
                     break;
                  }

                  if (Intrinsics.checkObject(var10, var14)) {
                     break;
                  }
               }
            }

            if (var25.isEmpty()) {
               break;
            }

            String[] var15 = new String[]{var10.toLowerCase(Locale.ROOT), var11, var12};
            var15 = var15;
            int var17 = 0;

            for (int var18 = var15.length; var17 < var18; var17++) {
               String var19 = var15[var17];
               if (CollectionsKt.checkIterable(var25, var19)) {
                  var10000 = true;
                  continue label146;
               }
            }

            var10000 = false;
         } while (var10000);

         boolean var27 = false;
         boolean var16 = false;
         boolean var28 = false;
         Sender[] var20 = var5;
         int var21 = 0;
         int var22 = var5.length;

         while (true) {
            if (var21 >= var22) {
               var39 = null;
               break;
            }

            Sender var23 = var20[var21];
            if (Intrinsics.checkObject(var23.getUuidString(), var11)) {
               var39 = var23;
               break;
            }

            var21++;
         }

         Sender var29;
         label172: {
            var29 = /* cast-fix */(Sender)(var39);
            if (this.sender != null) {
               var40 = this.sender.getServerName();
               if (var40 != null) {
                  break label172;
               }
            }

            CommandRunnable var35 = this;
            var40 = ((DatabaseManager)var35.getPlatform().getModule(DatabaseManager.class)).getString();
         }

         String var30 = /* cast-fix */(String)(var40);
         if (var29 != null) {
            String var31 = var29.getName();
            if (Intrinsics.checkObject(var10, var31)) {
               if (!var1.contains(var31)) {
                  PunishmentEntry var33 = null;
                  if (var6.isNotifyDupeipShowMutedAccounts()) {
                     var33 = EntryReader.getPunishmentEntry(this.punishmentStorage, var11, var12, PunishmentType.MUTE, var30, false, false, 48, null);
                     var28 = var33 != null;
                  }

                  Message var36 = var28 ? Message.DUPEIP_MUTED : Message.DUPEIP_ONLINE;
                  var4.add(new DupeIPResult(var36, var31, var11, var13, null, var33));
                  var1.add(var31);
               } else {
                  var16 = true;
               }
            } else if (!var1.contains(var10)) {
               var4.add(new DupeIPResult(Message.DUPEIP_OFFLINE, var10, var11, var13, null, null));
               var1.add(var10);
            }

            var27 = true;
         }

         if (var1.contains(var10)) {
            var16 = true;
         }

         if (!var16 && !var27) {
            PunishmentEntry var32 = EntryReader.getPunishmentEntry(this.punishmentStorage, var11, var12, null, var30, false, false, 52, null);
            PunishmentEntry var34 = null;
            boolean var37 = var32 != null;
            boolean var38 = var32 != null && !Intrinsics.checkObject(var32.getUuid(), var11);
            if (var6.isNotifyDupeipShowMutedAccounts()) {
               var34 = EntryReader.getPunishmentEntry(this.punishmentStorage, var11, var12, PunishmentType.MUTE, var30, false, false, 48, null);
               var28 = var34 != null;
            }

            if (var37) {
               this.flag3 = true;
            }

            if (var28) {
               this.flag4 = true;
            }

            Message var24 = var38 ? Message.DUPEIP_IP_BANNED : (var37 ? Message.DUPEIP_BANNED : (var28 ? Message.DUPEIP_MUTED : Message.DUPEIP_OFFLINE));
            var4.add(new DupeIPResult(var24, var10, var11, var13, var32, var34));
            var1.add(var10);
         }
      }
   }

   private static final CharSequence getCharSequence(DupeIPResult var0) {
      CharSequence var1 = var0.getPrefix();
      CharSequence var2 = var0.getName();
      return "" + var1 + var2;
   }
}