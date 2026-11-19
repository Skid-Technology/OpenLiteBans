package litebans.punishment;

import litebans.config.Template;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PunishmentEntry implements Comparable {
   public static final WildcardPattern wildcardPattern = new WildcardPattern(null);
   private final PunishmentType type;
   private final String uuid;
   private final String ip;
   private final CharSequence reason;
   private final String executorUUID;
   private final String executorName;
   private final String serverScope;
   private final String serverOrigin;
   private final long dateStart;
   private final long dateEnd;
   private final byte template;
   private final boolean silent;
   private final boolean ipban;
   private final boolean active;
   private long id;
   private long removedByDate;
   private String removedByUuid;
   private String removedByName;
   private CharSequence removedByReason;
   private boolean warned;

   private PunishmentEntry(
      PunishmentType var1,
      String var2,
      String var3,
      CharSequence var4,
      String var5,
      String var6,
      String var7,
      String var8,
      long var9,
      long var11,
      byte var13,
      boolean var14,
      boolean var15,
      boolean var16,
      long var17
   ) {
      this.type = var1;
      this.uuid = var2;
      this.ip = var3;
      this.reason = var4;
      this.executorUUID = var5;
      this.executorName = var6;
      this.serverScope = var7;
      this.serverOrigin = var8;
      this.dateStart = var9;
      this.dateEnd = var11;
      this.template = var13;
      this.silent = var14;
      this.ipban = var15;
      this.active = var16;
      this.id = var17;
   }

   public PunishmentEntry(
      PunishmentType var1,
      String var2,
      String var3,
      CharSequence var4,
      String var5,
      String var6,
      String var7,
      String var8,
      long var9,
      long var11,
      byte var13,
      boolean var14,
      boolean var15,
      boolean var16,
      long var17,
      int var19,
      DefaultConstructorMarker var20
   ) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, ((var19 & 1024) != 0 ? -1 : var13), ((var19 & 2048) != 0 ? false : var14), ((var19 & 4096) != 0 ? false : var15), ((var19 & 8192) != 0 ? true : var16), ((var19 & 16384) != 0 ? -1L : var17), null);
   }

   public final PunishmentType getType() {
      return this.type;
   }

   public final String getUuid() {
      return this.uuid;
   }

   public final String getIp() {
      return this.ip;
   }

   public final CharSequence getReason() {
      return this.reason;
   }

   public final String getExecutorUUID() {
      return this.executorUUID;
   }

   public final String getExecutorName() {
      return this.executorName;
   }

   public final String getServerScope() {
      return this.serverScope;
   }

   public final String getServerOrigin() {
      return this.serverOrigin;
   }

   public final long getDateStart() {
      return this.dateStart;
   }

   public final long getDateEnd() {
      return this.dateEnd;
   }

   public final byte getTemplate() {
      return this.template;
   }

   public final boolean isSilent() {
      return this.silent;
   }

   public final boolean isIpban() {
      return this.ipban;
   }

   public final boolean isActive() {
      return this.active;
   }

   public final long getId() {
      return this.id;
   }

   public final void setId(long var1) {
      this.id = var1;
   }

   public PunishmentEntry(@NotNull PunishmentRequest var1, @NotNull ExecutorInfo var2, @NotNull CharSequence var3) {
      this(
         var1.getPunishmentType(),
         var2.getString4(),
         var2.getString5(),
         var3,
         var1.getSenderUuid(),
         var1.getSenderName(),
         var1.getServerScope(),
         var1.getServerOrigin(),
         var1.getDateStart(),
         var1.getDateEnd(),
         var1.getTemplate() != null ? var1.getTemplate().getId() : -1,
         var1.isSilent(),
         var1.isIpBan(),
         !var1.isSkip(),
         0L,
         16384,
         null
      );
   }

   public final long getRemovedByDate() {
      return this.removedByDate;
   }

   public final void setRemovedByDate(long var1) {
      this.removedByDate = var1;
   }

   public final String getRemovedByUuid() {
      return this.removedByUuid;
   }

   public final void setRemovedByUuid(@Nullable String var1) {
      this.removedByUuid = var1;
   }

   public final String getRemovedByName() {
      return this.removedByName;
   }

   public final void setRemovedByName(@Nullable String var1) {
      this.removedByName = var1;
   }

   public final CharSequence getRemovedByReason() {
      return this.removedByReason;
   }

   public final void setRemovedByReason(@Nullable CharSequence var1) {
      this.removedByReason = var1;
   }

   public final boolean isWarned() {
      return this.warned;
   }

   public final void setWarned(boolean var1) {
      this.warned = var1;
   }

   public int compareByDate(@NotNull PunishmentEntry var1) {
      return Intrinsics.getInt(var1.dateStart, this.dateStart);
   }

   public final String getString() {
      return this.type == PunishmentType.WARN ? ",warned" : "";
   }

   public final long getDuration() {
      return this.isPermanent() ? -1L : this.dateEnd - this.dateStart;
   }

   public final boolean isWildcardIpBan() {
      return this.ipban && wildcardPattern.checkString(this.ip);
   }

   public final boolean isExpired(long var1) {
      return !this.isPermanent() && var1 >= this.dateEnd;
   }

   public final boolean isPermanent() {
      return this.dateEnd <= 0L;
   }

   public final String getMessagePrefix(@NotNull PunishmentRequest var1) {
      StringBuilder var10000 = new StringBuilder().append(var1.isSilent() ? "silent_" : "");
      Object var10001;
      switch (PunishmentEntryWhenMappings.switchMap[var1.getPunishmentType().ordinal()]) {
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
            var10001 = var1.getPunishmentType();
            break;
         default:
            var10001 = (!this.isPermanent() ? "temp_" : "") + (var1.isIpBan() ? "ip" : "") + var1.getPunishmentType();
      }

      return var10000.append(var10001).toString();
   }

   public final boolean matches(@Nullable String var1, @Nullable String var2, long var3) {
      return (wildcardPattern.checkString(this.getUuid(), var1) || wildcardPattern.checkBoolean(this.isIpban(), this.getIp(), var2, this.isWildcardIpBan()))
         && this.active
         && !this.isExpired(var3);
   }

   public final void setRemovedBy(@Nullable String var1, @Nullable String var2, @NotNull CharSequence var3) {
      this.removedByName = var1;
      this.removedByUuid = var2;
      this.removedByReason = var3.toString();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("Entry(type=")
         .append(this.type)
         .append(", uuid=")
         .append(this.uuid)
         .append(", ip=")
         .append(this.ip)
         .append(", reason=")
         .append(this.reason)
         .append(", executorUUID=")
         .append(this.executorUUID)
         .append(", executorName=")
         .append(this.executorName)
         .append(", serverScope=")
         .append(this.serverScope)
         .append(", serverOrigin=")
         .append(this.serverOrigin)
         .append(", dateStart=")
         .append(this.dateStart)
         .append(", dateEnd=")
         .append(this.dateEnd)
         .append(", template=")
         .append(UByte.getString(this.template))
         .append(", silent=");
      var1.append(this.silent).append(", ipban=").append(this.ipban).append(", active=").append(this.active).append(", id=").append(this.id).append(')');
      return var1.toString();
   }

   @Override
   public int hashCode() {
      int var1 = this.type.hashCode();
      var1 = var1 * 31 + (this.uuid == null ? 0 : this.uuid.hashCode());
      var1 = var1 * 31 + (this.ip == null ? 0 : this.ip.hashCode());
      var1 = var1 * 31 + this.reason.hashCode();
      var1 = var1 * 31 + this.executorUUID.hashCode();
      var1 = var1 * 31 + (this.executorName == null ? 0 : this.executorName.hashCode());
      var1 = var1 * 31 + (this.serverScope == null ? 0 : this.serverScope.hashCode());
      var1 = var1 * 31 + (this.serverOrigin == null ? 0 : this.serverOrigin.hashCode());
      var1 = var1 * 31 + Long.hashCode(this.dateStart);
      var1 = var1 * 31 + Long.hashCode(this.dateEnd);
      var1 = var1 * 31 + UByte.getInt(this.template);
      var1 = var1 * 31 + Boolean.hashCode(this.silent);
      var1 = var1 * 31 + Boolean.hashCode(this.ipban);
      var1 = var1 * 31 + Boolean.hashCode(this.active);
      return var1 * 31 + Long.hashCode(this.id);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof PunishmentEntry)) {
         return false;
      } else {
         PunishmentEntry var2 = (PunishmentEntry)var1;
         if (this.type != var2.type) {
            return false;
         } else if (!Intrinsics.checkObject(this.uuid, var2.uuid)) {
            return false;
         } else if (!Intrinsics.checkObject(this.ip, var2.ip)) {
            return false;
         } else if (!Intrinsics.checkObject(this.reason, var2.reason)) {
            return false;
         } else if (!Intrinsics.checkObject(this.executorUUID, var2.executorUUID)) {
            return false;
         } else if (!Intrinsics.checkObject(this.executorName, var2.executorName)) {
            return false;
         } else if (!Intrinsics.checkObject(this.serverScope, var2.serverScope)) {
            return false;
         } else if (!Intrinsics.checkObject(this.serverOrigin, var2.serverOrigin)) {
            return false;
         } else if (this.dateStart != var2.dateStart) {
            return false;
         } else if (this.dateEnd != var2.dateEnd) {
            return false;
         } else if (this.template != var2.template) {
            return false;
         } else if (this.silent != var2.silent) {
            return false;
         } else if (this.ipban != var2.ipban) {
            return false;
         } else {
            return this.active != var2.active ? false : this.id == var2.id;
         }
      }
   }

   @Override
   public int compareTo(Object var1) {
      return this.compareByDate((PunishmentEntry)var1);
   }

   public PunishmentEntry(
      PunishmentType var1,
      String var2,
      String var3,
      CharSequence var4,
      String var5,
      String var6,
      String var7,
      String var8,
      long var9,
      long var11,
      byte var13,
      boolean var14,
      boolean var15,
      boolean var16,
      long var17,
      DefaultConstructorMarker var19
   ) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, var13, var14, var15, var16, var17);
   }
}
