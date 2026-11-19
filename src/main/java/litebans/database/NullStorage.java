package litebans.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import litebans.config.Template;
import litebans.config.TemplateGroup;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.TuplesKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.ExecutorInfo;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class NullStorage implements PunishmentStorage {
   private final LiteBansPlatform liteBansPlatform;

   public NullStorage(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   @Override
   public LiteBansPlatform getPlatform() {
      return this.liteBansPlatform;
   }

   @Override
   public boolean isClosed() {
      return true;
   }

   @Override
   public void deactivate(@Nullable String var1, @Nullable String var2, @NotNull CharSequence var3, @NotNull PunishmentEntry var4, boolean var5) {
   }

   @Override
   public void removeEntry(@NotNull PunishmentEntry var1, @Nullable String var2, @Nullable String var3, @NotNull CharSequence var4, boolean var5) {
   }

   @Override
   public long countEntries() {
      return 0L;
   }

   @Override
   public int countTemplateUses(@NotNull Template var1, @Nullable String var2, @Nullable String var3) {
      return 0;
   }

   @Override
   public Pair computeTemplateStep(@Nullable String var1, @Nullable String var2, @NotNull TemplateGroup var3, @NotNull Template var4) {
      return TuplesKt.getPair(0.0, 0.0);
   }

   @Override
   public void insertEntry(@NotNull PunishmentEntry var1) {
   }

   @Override
   public void recordLogin(@Nullable String var1, @Nullable String var2, @Nullable String var3, boolean var4, boolean var5) {
   }

   @Override
   public PlayerHistory getPlayerHistory(@NotNull PlayerHistory var1, boolean var2) {
      return new PlayerHistory(null, null, null, new Date());
   }

   @Override
   public int updateHistoryIp(@NotNull PlayerHistory var1) {
      return 0;
   }

   @Override
   public void close() {
   }

   @Override
   public List findEntries(@Nullable String var1, @Nullable String var2, @NotNull PunishmentType var3, @Nullable String var4, boolean var5, int var6) {
      return CollectionsKt.getList();
   }

   @Override
   public List getList(@NotNull PunishmentEntry var1, int var2) {
      return CollectionsKt.getList();
   }

   @Override
   public List getList(@NotNull Sender var1, @NotNull PunishmentType var2, boolean var3, int var4) {
      return CollectionsKt.getList();
   }

   @Override
   public int count(@NotNull SelectQuery var1, @NotNull PunishmentType var2) {
      return 0;
   }

   @Override
   public List getList(@NotNull String var1, @NotNull PunishmentType var2, @NotNull String var3, int var4) {
      return CollectionsKt.getList();
   }

   @Override
   public List findHistoryByIp(@Nullable String var1, int var2) {
      return CollectionsKt.getList();
   }

   @Override
   public Collection findEntriesInRange(@NotNull PunishmentType var1, long var2, long var4, boolean var6) {
      return CollectionsKt.getList();
   }

   @Override
   public PunishmentEntry findActive(
      @Nullable String var1, @Nullable String var2, @NotNull PunishmentType var3, @Nullable String var4, boolean var5, boolean var6
   ) {
      return null;
   }

   @Override
   public PunishmentEntry findNameBan(@NotNull String var1, @Nullable String var2, @Nullable String var3, @NotNull PunishmentType var4) {
      return null;
   }

   @Override
   public List getList(@NotNull String var1, @Nullable String var2, @Nullable String var3, @NotNull PunishmentType var4, boolean var5) {
      return CollectionsKt.getList();
   }

   @Override
   public PunishmentEntry findById(long var1, @NotNull PunishmentType var3, @NotNull String var4, boolean var5) {
      return null;
   }

   @Override
   public PunishmentEntry findById(@NotNull String var1, @NotNull PunishmentType var2, @NotNull String var3, boolean var4) {
      return null;
   }

   @Override
   public PunishmentEntry findActiveFor(@NotNull PunishmentEntry var1, boolean var2) {
      return null;
   }

   @Override
   public PunishmentEntry findActive(@Nullable String var1, @Nullable String var2, @NotNull PunishmentType var3, boolean var4, boolean var5) {
      return null;
   }

   @Override
   public long countActive(@NotNull Table var1, @NotNull String var2, boolean var3) {
      return 0L;
   }

   @Override
   public boolean existsByUuid(@NotNull UUID var1, @NotNull PunishmentType var2) {
      return false;
   }

   public EmptyResultSet getEmptyResultSet(@NotNull UUID var1, int var2) {
      return EmptyResultSetHolder.getEmptyResultSet();
   }

   public EmptyResultSet getEmptyResultSet(@NotNull UUID var1) {
      return EmptyResultSetHolder.getEmptyResultSet();
   }

   @Override
   public List findHistory(@NotNull String var1) {
      return CollectionsKt.getList();
   }

   @Override
   public PlayerHistory findLatestHistory(@Nullable String var1) {
      return null;
   }

   @Override
   public List findHistoryByTarget(@Nullable String var1, int var2) {
      return CollectionsKt.getList();
   }

   @Override
   public long lookupId(@NotNull PunishmentEntry var1) {
      return 0L;
   }

   @Override
   public void copyTable(@Nullable Connection var1, @NotNull Table var2, boolean var3) {
   }

   @Override
   public PunishmentEntry readEntry(@NotNull ResultSet var1, @NotNull PunishmentType var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public QueryStatement prepare(@NotNull CharSequence var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void pruneHistory(@Nullable String var1, long var2) {
   }

   @Override
   public ResultSet resetTable(@NotNull Table var1, @NotNull String var2, int var3) {
      return EmptyResultSetHolder.getEmptyResultSet();
   }

   @Override
   public ResultSet resetTemplates(@NotNull Table var1) {
      return EmptyResultSetHolder.getEmptyResultSet();
   }

   @Override
   public void updateDurationAndReason(@NotNull CharSequence var1, long var2, @NotNull PunishmentEntry var4) {
   }

   @Override
   public long rollbackStaff(@NotNull String var1, long var2, @NotNull String var4) {
      return -1L;
   }

   @Override
   public ResultSet markWarned(@NotNull String var1) {
      return new EmptyResultSet();
   }

   @Override
   public void expireEntries(boolean var1) {
   }

   @Override
   public boolean columnExists(@NotNull Table var1, @NotNull String var2, boolean var3) {
      return false;
   }

   @Override
   public boolean isOpen() {
      return EntryReader.checkPunishmentStorage(this);
   }

   @Override
   public DatabaseManager getDatabaseManager() {
      return EntryReader.getDatabaseManager(this);
   }

   @Override
   public List getList(@NotNull PlayerHistory var1, @NotNull PunishmentType var2, @NotNull String var3, boolean var4, int var5) {
      return EntryReader.getList(this, var1, var2, var3, var4, var5);
   }

   @Override
   public List readEntries(@NotNull ResultSet var1, @NotNull PunishmentType var2) {
      return EntryReader.getList(this, var1, var2);
   }

   @Override
   public String getString(@NotNull Sender var1) {
      return EntryReader.getString(this, var1);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1, @NotNull Sender var2) {
      return EntryReader.getCharSequence(this, var1, var2);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1, @Nullable String var2, @Nullable String var3, boolean var4) {
      return EntryReader.getCharSequence(this, var1, var2, var3, var4);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1) {
      return EntryReader.getCharSequence(this, var1);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1, @Nullable PlayerHistory var2, boolean var3) {
      return EntryReader.getCharSequence(this, var1, var2, var3);
   }

   @Override
   public CharSequence getCharSequence2(@NotNull CharSequence var1, @Nullable Sender var2) {
      return EntryReader.getCharSequence2(this, var1, var2);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1, @NotNull ExecutorInfo var2) {
      return EntryReader.getCharSequence(this, var1, var2);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1, @Nullable PunishmentEntry var2, boolean var3) {
      return EntryReader.getCharSequence(this, var1, var2, var3);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1, @Nullable PunishmentEntry var2, @Nullable PlayerHistory var3, boolean var4) {
      return EntryReader.getCharSequence(this, var1, var2, var3, var4);
   }

   @Override
   public CharSequence getCharSequence(
      @NotNull CharSequence var1, @Nullable String var2, @Nullable String var3, @Nullable String var4, @Nullable String var5, @Nullable String var6
   ) {
      return EntryReader.getCharSequence(this, var1, var2, var3, var4, var5, var6);
   }

   @Override
   public boolean appliesTo(@NotNull PunishmentEntry var1, @NotNull Sender var2) {
      return EntryReader.checkPunishmentStorage(this, var1, var2);
   }

   @Override
   public boolean appliesToTarget(@Nullable PunishmentEntry var1) {
      return EntryReader.checkPunishmentStorage(this, var1);
   }

   @Override
   public ResultSet insertSyncRecord(UUID var1, int var2) {
      return this.getEmptyResultSet(var1, var2);
   }

   @Override
   public ResultSet deleteSyncRecords(UUID var1) {
      return this.getEmptyResultSet(var1);
   }

   @Override
   public boolean tableExists(@NotNull CharSequence var1) {
      return false;
   }
   @Override
   public boolean historyExists(@Nullable String var1, @Nullable String var2, @NotNull String var3) {
      return false;
   }
   @Override
   public PunishmentEntry readFirstEntry(@NotNull ResultSet var1, @NotNull PunishmentType var2) {
      return null;
   }

   @Override
   public PlayerHistory readHistory(@NotNull ResultSet var1, boolean var2, @Nullable String var3) {
      return null;
   }
}
