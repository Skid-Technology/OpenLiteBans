package litebans.database;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import litebans.config.Template;
import litebans.config.TemplateGroup;
import litebans.lib.kotlin.Pair;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.ExecutorInfo;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PunishmentStorage extends Closeable {
   boolean isClosed();

   boolean isOpen();

   LiteBansPlatform getPlatform();

   DatabaseManager getDatabaseManager();

   void insertEntry(@NotNull PunishmentEntry var1);

   PlayerHistory getPlayerHistory(@NotNull PlayerHistory var1, boolean var2);

   void recordLogin(@Nullable String var1, @Nullable String var2, @Nullable String var3, boolean var4, boolean var5);

   ResultSet insertSyncRecord(@NotNull UUID var1, int var2);

   boolean existsByUuid(@NotNull UUID var1, @NotNull PunishmentType var2);

   boolean columnExists(@NotNull Table var1, @NotNull String var2, boolean var3);

   PunishmentEntry readEntry(@NotNull ResultSet var1, @NotNull PunishmentType var2);

   PunishmentEntry readFirstEntry(@NotNull ResultSet var1, @NotNull PunishmentType var2);

   PlayerHistory readHistory(@NotNull ResultSet var1, boolean var2, @Nullable String var3);

   int count(@NotNull SelectQuery var1, @NotNull PunishmentType var2);

   int countTemplateUses(@NotNull Template var1, @Nullable String var2, @Nullable String var3);

   List findEntries(@Nullable String var1, @Nullable String var2, @NotNull PunishmentType var3, @Nullable String var4, boolean var5, int var6);

   List getList(@NotNull String var1, @NotNull PunishmentType var2, @NotNull String var3, int var4);

   List findHistoryByIp(@Nullable String var1, int var2);

   Collection findEntriesInRange(@NotNull PunishmentType var1, long var2, long var4, boolean var6);

   PunishmentEntry findActiveFor(@NotNull PunishmentEntry var1, boolean var2);

   PunishmentEntry findActive(@Nullable String var1, @Nullable String var2, @NotNull PunishmentType var3, boolean var4, boolean var5);

   PunishmentEntry findActive(@Nullable String var1, @Nullable String var2, @NotNull PunishmentType var3, @Nullable String var4, boolean var5, boolean var6);

   PunishmentEntry findById(@NotNull String var1, @NotNull PunishmentType var2, @NotNull String var3, boolean var4);

   PunishmentEntry findById(long var1, @NotNull PunishmentType var3, @NotNull String var4, boolean var5);

   long countActive(@NotNull Table var1, @NotNull String var2, boolean var3);

   long lookupId(@NotNull PunishmentEntry var1);

   PunishmentEntry findNameBan(@NotNull String var1, @Nullable String var2, @Nullable String var3, @NotNull PunishmentType var4);

   List getList(@NotNull String var1, @Nullable String var2, @Nullable String var3, @NotNull PunishmentType var4, boolean var5);

   List findHistory(@NotNull String var1);

   PlayerHistory findLatestHistory(@Nullable String var1);

   List findHistoryByTarget(@Nullable String var1, int var2);

   boolean historyExists(@Nullable String var1, @Nullable String var2, @NotNull String var3);

   void copyTable(@Nullable Connection var1, @NotNull Table var2, boolean var3);

   QueryStatement prepare(@NotNull CharSequence var1);

   void pruneHistory(@Nullable String var1, long var2);

   Pair computeTemplateStep(@Nullable String var1, @Nullable String var2, @NotNull TemplateGroup var3, @NotNull Template var4);

   ResultSet markWarned(@NotNull String var1);

   void updateDurationAndReason(@NotNull CharSequence var1, long var2, @NotNull PunishmentEntry var4);

   ResultSet deleteSyncRecords(@NotNull UUID var1);

   ResultSet resetTable(@NotNull Table var1, @NotNull String var2, int var3);

   ResultSet resetTemplates(@NotNull Table var1);

   void deactivate(@Nullable String var1, @Nullable String var2, @NotNull CharSequence var3, @NotNull PunishmentEntry var4, boolean var5);

   void removeEntry(@NotNull PunishmentEntry var1, @Nullable String var2, @Nullable String var3, @NotNull CharSequence var4, boolean var5);

   long rollbackStaff(@NotNull String var1, long var2, @NotNull String var4);

   boolean tableExists(@NotNull CharSequence var1);

   long countEntries();

   int updateHistoryIp(@NotNull PlayerHistory var1);

   void expireEntries(boolean var1);

   List readEntries(@NotNull ResultSet var1, @NotNull PunishmentType var2);

   List getList(@NotNull PlayerHistory var1, @NotNull PunishmentType var2, @NotNull String var3, boolean var4, int var5);

   List getList(@NotNull PunishmentEntry var1, int var2);

   List getList(@NotNull Sender var1, @NotNull PunishmentType var2, boolean var3, int var4);

   String getString(@NotNull Sender var1);

   CharSequence getCharSequence(@NotNull CharSequence var1, @NotNull Sender var2);

   CharSequence getCharSequence(@NotNull CharSequence var1, @Nullable String var2, @Nullable String var3, boolean var4);

   CharSequence getCharSequence(@NotNull CharSequence var1);

   CharSequence getCharSequence(@NotNull CharSequence var1, @Nullable PlayerHistory var2, boolean var3);

   CharSequence getCharSequence2(@NotNull CharSequence var1, @Nullable Sender var2);

   CharSequence getCharSequence(@NotNull CharSequence var1, @NotNull ExecutorInfo var2);

   CharSequence getCharSequence(
      @NotNull CharSequence var1, @Nullable String var2, @Nullable String var3, @Nullable String var4, @Nullable String var5, @Nullable String var6
   );

   CharSequence getCharSequence(@NotNull CharSequence var1, @Nullable PunishmentEntry var2, boolean var3);

   CharSequence getCharSequence(@NotNull CharSequence var1, @Nullable PunishmentEntry var2, @Nullable PlayerHistory var3, boolean var4);

   boolean appliesTo(@NotNull PunishmentEntry var1, @NotNull Sender var2);

   boolean appliesToTarget(@Nullable PunishmentEntry var1);
}
