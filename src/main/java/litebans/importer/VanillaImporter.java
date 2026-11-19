package litebans.importer;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import litebans.chat.ChatColorCode;
import litebans.command.CommandRunnable;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.json.JSONArray;
import litebans.lib.json.JSONObject;
import litebans.lib.json.JSONValue;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.io.TextStreamsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.Charsets;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import litebans.sync.QueueFeederTask;
import org.jetbrains.annotations.NotNull;

public final class VanillaImporter extends Importer {
   private final File file = new File("banned-players.json");
   private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

   public VanillaImporter(@NotNull LiteBansPlatform var1) {
      super(var1, "vanilla", null, 0, 0L, 28, null);
   }

   @Override
   public boolean check() {
      return false;
   }

   @Override
   public boolean checkString(@NotNull String var1) {
      return super.checkString(var1)
         || StringsKt.checkCharSequence3(var1, "banned-players", false, 2, null)
         || StringsKt.checkCharSequence3(var1, "essentials", false, 2, null);
   }

   @Override
   public void handleString(@NotNull String var1, @NotNull Connection var2, @NotNull PunishmentStorage var3, boolean var4) {
      try {
      File var6 = this.file;
      Charset var7 = Charsets.charset;
      short var8 = 8192;
      Reader var9 = new InputStreamReader(new FileInputStream(var6), var7);
      BufferedReader var5 = var9 instanceof BufferedReader ? (BufferedReader)var9 : new BufferedReader(var9, var8);
      Closeable var17 = var5;
      Throwable var18 = null;

      try {
         String var10 = CollectionsKt.getString(TextStreamsKt.getList(var5), null, null, null, 0, null, null, 63, null);
         Object var11 = JSONValue.getObject(var10);
         Intrinsics.handleObject(var11, "");
         JSONArray var12 = (JSONArray)var11;
         this.handleJSONArray(var12, var3);
      } catch (Throwable var15) {
         var18 = var15;
         throw var15;
      } finally {
         CloseableKt.handleCloseable(var17, var18);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleJSONArray(JSONArray var1, PunishmentStorage var2) {
      JSONObject var3 = null;
      String var4 = "";

      for (Object var6 : var1) {
         try {
            Intrinsics.handleObject(var6, "");
            var3 = (JSONObject)var6;
            String var7 = (String)var3.get("name");
            String var8 = (String)var3.get("uuid");
            Date var9 = this.dateFormat.parse((String)var3.get("created"));
            long var10 = var9.getTime();
            String var10000 = (String)var3.get("source");
            if (var10000 != null) {
               String var12 = var10000;
               var4 = ChatColorCode.getString(var12);
               String var15 = (String)var3.get("expires");
               long var13 = Intrinsics.checkObject(var15, "forever") ? 0L : this.dateFormat.parse(var15).getTime();
               var10000 = (String)var3.get("reason");
               if (var10000 == null) {
                  var10000 = "";
               }

               String var16 = var10000;
               PunishmentEntry var17 = new PunishmentEntry(
                  PunishmentType.BAN, var8, null, var16, this.getString(var4), var4, "*", null, var10, var13, (byte)0, false, false, false, 0L, 31744, null
               );
               if (EntryReader.getPunishmentEntry(var2, var8, null, null, false, false, 30, null) == null) {
                  var2.insertEntry(var17);
                  this.getAtomicLong().incrementAndGet();
               } else {
                  this.getLogger().warning("Ignoring duplicate ban for " + var17.getUuid());
               }

               if (var7 != null && var8 != null) {
                  new QueueFeederTask(this.getPlatform(), var7, var8, null).run();
               }
            }
         } catch (Exception var18) {
            this.handleString(this.getString(), var4, var3, var18);
         }
      }
   }

   private final void handleString(String var1, String var2, JSONObject var3, Exception var4) {
      this.getPlatform().getLogger().warning("Failed to import ban for " + var1 + " (created by " + var2 + "). JSON data:");
      this.getPlatform().getLogger().warning(var3 != null ? var3.getString() : null);
      CommandRunnable var5 = this;
      ((DatabaseManager)var5.getPlatform().getModule(DatabaseManager.class)).reportError(var4);
   }
}
