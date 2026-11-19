package litebans.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import litebans.chat.ChatColorCode;
import litebans.config.Message;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.database.Table;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.TuplesKt;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.TypeIntrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentService;
import litebans.punishment.RemovalMode;
import litebans.punishment.ServerScope;
import org.jetbrains.annotations.NotNull;

public final class ResetDatabaseSubcommand extends CommandRunnable {
   private final String[] stringArray;
   private boolean flag;
   private String string;
   private RemovalMode removalMode;
   private PunishmentStorage punishmentStorage;

   public ResetDatabaseSubcommand(@NotNull String[] var1, @NotNull LiteBansPlatform var2) {
      super(var2);
      this.stringArray = var1;
      this.string = "global";
      this.removalMode = RemovalMode.DELETE;
   }

   @Override
   public void run() {
      this.string = PunishmentService.parseServerScope(this, this.getCurrentSender(), this.stringArray, this.string);
      CommandRunnable var1 = this;
      MessageSender var3 = CommandRunnable.messageSender;
      boolean var4 = var1.getCurrentSender().isConsole();
      CharSequence var5 = Message.ERROR_CONSOLE;
      boolean var8 = !var4;
      if (var8) {
         var3.getVoid(var5);
         throw new NoWhenBranchMatchedException();
      } else {
         MessageSender var13 = CommandRunnable.messageSender;
         boolean var2 = !ArraysKt.checkObjectArray(this.stringArray, "confirm");
         if (var2) {
            var13.getVoid(Message.RESET_DATABASE_CONFIRM.getString("serverScope", this.string));
            throw new NoWhenBranchMatchedException();
         } else {
            var1 = this;
            ((DatabaseManager)var1.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, (PunishmentStorage) st));
         }
      }
   }

   private static final Unit getUnit(ResetDatabaseSubcommand var0, PunishmentStorage var1) {
      MessageSender var2 = CommandRunnable.messageSender;
      boolean var3 = var1.isClosed();
      CharSequence var4 = Message.ERROR_NO_DATABASE;
      if (var3) {
         var2.getVoid(var4);
         throw new NoWhenBranchMatchedException();
      } else {
         List var25 = ArgUtils.getList(ArraysKt.getList2(var0.stringArray));
         Iterable var27 = var25;
         Iterator var6 = var27.iterator();

         Object var10000;
         while (true) {
            if (!var6.hasNext()) {
               var10000 = null;
               break;
            }

            Object var7 = var6.next();
            String var8 = (String)var7;
            if (!Intrinsics.checkObject(var8, "reset-database")
               && !StringsKt.checkString(var8, "exclude:", false, 2, null)
               && !StringsKt.checkString(var8, "mode:", false, 2, null)
               && !StringsKt.checkString(var8, "server:", false, 2, null)
               && !Intrinsics.checkObject(var8, "-g")
               && !Intrinsics.checkObject(var8, "confirm")) {
               var10000 = (PunishmentStorage)var7;
               break;
            }
         }

         String var26 = (String)var10000;
         MessageSender var28 = CommandRunnable.messageSender;
         boolean var5 = var26 != null;
         CharSequence var30 = ChatColorCode.getString3("Unrecognized parameter: " + ChatColorCode.getString5(var26));
         if (var5) {
            var28.getVoid(var30);
            throw new NoWhenBranchMatchedException();
         } else {
            var0.punishmentStorage = var1;
            var0.flag = ServerScope.serverScopeParser.checkString(var0.string);
            String var29 = var0.flag ? "__ALL__" : var0.string;
            Iterable var31 = var25;
            Collection var9 = new ArrayList();

            for (Object var12 : var31) {
               String var13 = (String)var12;
               if (StringsKt.checkString(var13, "exclude:reason:", false, 2, null)) {
                  var9.add(var12);
               }
            }

            Iterable var32 = (List)var9;
            var9 = new ArrayList(CollectionsKt.getInt(var32, 10));

            for (Object var55 : var32) {
               String var59 = (String)var55;
               var9.add(StringsKt.getString(var59, "exclude:reason:", null, 2, null));
            }

            Iterable var35 = var25;
            Collection var47 = new ArrayList();

            for (Object var14 : var35) {
               String var15 = (String)var14;
               if (StringsKt.checkString(var15, "exclude:", false, 2, null)) {
                  var47.add(var14);
               }
            }

            var35 = (List)var47;
            var47 = new ArrayList(CollectionsKt.getInt(var35, 10));

            for (Object var71 : var35) {
               String var78 = (String)var71;
               var47.add(StringsKt.getString(var78, "exclude:", null, 2, null));
            }

            var35 = (List)var47;
            var47 = new ArrayList();

            for (Object var72 : var35) {
               String var79 = (String)var72;
               if (!StringsKt.checkCharSequence3(var79, ':', false, 2, null)) {
                  var47.add(var72);
               }
            }

            List var33 = TypeIntrinsics.getList((List)var47);
            if (!var33.isEmpty()) {
               var33.add("history");
               var33.add("config");
            }

            Iterable var10 = var25;
            Iterator var50 = var10.iterator();

            while (true) {
               if (var50.hasNext()) {
                  Object var56 = var50.next();
                  String var63 = (String)var56;
                  if (!StringsKt.checkString(var63, "mode:", false, 2, null)) {
                     continue;
                  }

                  var10000 = var56;
                  break;
               }

               var10000 = null;
               break;
            }

            String var34 = (String)var10000 != null ? StringsKt.getString2((String)var10000, ':', null, 2, null) : null;
            if (var34 != null) {
               RemovalMode[] var43 = RemovalMode.values();
               RemovalMode[] var44 = var43;
               int var51 = 0;
               int var57 = var43.length;

               while (true) {
                  if (var51 >= var57) {
                     var10000 = null;
                     break;
                  }

                  RemovalMode var64 = var44[var51];
                  if (StringsKt.checkString(var64.name(), var34, true)) {
                     var10000 = var64;
                     break;
                  }

                  var51++;
               }

               RemovalMode var10001 = (RemovalMode)var10000;
               if (var10000 == null) {
                  var10001 = RemovalMode.DELETE;
               }

               var0.removalMode = var10001;
            }

            if (var0.flag) {
               Table[] var45 = Table.values();
               Table[] var58 = var45;
               Collection var65 = new ArrayList();
               int var80 = 0;

               for (int var16 = var45.length; var80 < var16; var80++) {
                  Table var17 = var58[var80];
                  if (var17.check() || var17 == Table.HISTORY) {
                     var65.add(var17);
                  }
               }

               var10000 = (List)var65;
            } else {
               var10000 = Table.Companion.getList();
            }

            var35 = (Iterable)var10000;
            var47 = new ArrayList();

            for (Object var73 : var35) {
               Table var81 = (Table)var73;
               if (!var33.contains(var81.toString().toLowerCase(Locale.ROOT))) {
                  var47.add(var73);
               }
            }

            var35 = (List)var47;
            var47 = new ArrayList();

            for (Object var74 : var35) {
               Table var82 = (Table)var74;
               if (!var33.contains(var82.name().toLowerCase(Locale.ROOT))) {
                  var47.add(var74);
               }
            }

            var35 = (List)var47;
            var47 = new ArrayList(CollectionsKt.getInt(var35, 10));

            for (Object var75 : var35) {
               Table var83 = (Table)var75;
               var47.add(TuplesKt.getPair(var83, EntryReader.getLong(var1, var83, var29, false, 4, null)));
            }

            var35 = (List)var47;

            for (Object var76 : var35) {
               Pair var84 = (Pair)var76;
               var10000 = var0.punishmentStorage;
               if (var0.punishmentStorage == null) {
                  Intrinsics.handleString3("");
                  var10000 = null;
               }

               ((PunishmentStorage) var10000).resetTable((Table)var84.getObject(), var0.string, var0.removalMode.ordinal());
            }

            for (Object var77 : var35) {
               Pair var85 = (Pair)var77;
               CommandRunnable var91 = var0;
               CharSequence var92 = Message.RESET_DATABASE_RESULT.getString("serverScope", var0.string);
               CharSequence[] var23 = new CharSequence[]{"amount", String.valueOf(((Number)var85.getObject2()).longValue())};
               var92 = ArgUtils.getString(var92, var23);
               var23 = new CharSequence[]{"type", ((Table)var85.getObject()).name().toLowerCase(Locale.ROOT)};
               PunishmentService.reply(var91, ArgUtils.getString(var92, var23));
            }

            DatabaseManager.createTablesDefault(var1.getDatabaseManager(), 0L, true, true, false, 1, null);
            return Unit.INSTANCE;
         }
      }
   }
}
