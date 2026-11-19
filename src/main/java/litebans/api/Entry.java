package litebans.api;

import lombok.Generated;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

public abstract class Entry {
   private final long id;
   private final String type;
   @Nullable
   private final String uuid;
   @Nullable
   private final String ip;
   private final String reason;
   @Nullable
   private final String executorUUID;
   @Nullable
   private final String executorName;
   @Nullable
   private final String removedByUUID;
   @Nullable
   private final String removedByName;
   @Nullable
   private final String removalReason;
   private final long dateStart;
   private final long dateEnd;
   private final String serverScope;
   private final String serverOrigin;
   private final byte template;
   private final boolean silent;
   private final boolean ipban;
   private final boolean active;

   public abstract long getDuration();

   public abstract String getDurationString();

   public abstract long getRemainingDuration(long var1);

   public abstract String getRemainingDurationString(long var1);

   public abstract String getRandomID();

   public abstract boolean isExpired(long var1);

   public abstract boolean isPermanent();

   public abstract int getTemplateID();

   @NonNull
   public abstract String getTemplateName();

   public abstract boolean hasTemplate();

   @Generated
   public Entry(
      long var1,
      String var3,
      @Nullable String var4,
      @Nullable String var5,
      String var6,
      @Nullable String var7,
      @Nullable String var8,
      @Nullable String var9,
      @Nullable String var10,
      @Nullable String var11,
      long var12,
      long var14,
      String var16,
      String var17,
      byte var18,
      boolean var19,
      boolean var20,
      boolean var21
   ) {
      this.id = var1;
      this.type = var3;
      this.uuid = var4;
      this.ip = var5;
      this.reason = var6;
      this.executorUUID = var7;
      this.executorName = var8;
      this.removedByUUID = var9;
      this.removedByName = var10;
      this.removalReason = var11;
      this.dateStart = var12;
      this.dateEnd = var14;
      this.serverScope = var16;
      this.serverOrigin = var17;
      this.template = var18;
      this.silent = var19;
      this.ipban = var20;
      this.active = var21;
   }

   @Generated
   public long getId() {
      return this.id;
   }

   @Generated
   public String getType() {
      return this.type;
   }

   @Nullable
   @Generated
   public String getUuid() {
      return this.uuid;
   }

   @Nullable
   @Generated
   public String getIp() {
      return this.ip;
   }

   @Generated
   public String getReason() {
      return this.reason;
   }

   @Nullable
   @Generated
   public String getExecutorUUID() {
      return this.executorUUID;
   }

   @Nullable
   @Generated
   public String getExecutorName() {
      return this.executorName;
   }

   @Nullable
   @Generated
   public String getRemovedByUUID() {
      return this.removedByUUID;
   }

   @Nullable
   @Generated
   public String getRemovedByName() {
      return this.removedByName;
   }

   @Nullable
   @Generated
   public String getRemovalReason() {
      return this.removalReason;
   }

   @Generated
   public long getDateStart() {
      return this.dateStart;
   }

   @Generated
   public long getDateEnd() {
      return this.dateEnd;
   }

   @Generated
   public String getServerScope() {
      return this.serverScope;
   }

   @Generated
   public String getServerOrigin() {
      return this.serverOrigin;
   }

   @Generated
   public boolean isSilent() {
      return this.silent;
   }

   @Generated
   public boolean isIpban() {
      return this.ipban;
   }

   @Generated
   public boolean isActive() {
      return this.active;
   }

   @Generated
   @Override
   public String toString() {
      return "Entry(id="
         + this.getId()
         + ", type="
         + this.getType()
         + ", uuid="
         + this.getUuid()
         + ", ip="
         + this.getIp()
         + ", reason="
         + this.getReason()
         + ", executorUUID="
         + this.getExecutorUUID()
         + ", executorName="
         + this.getExecutorName()
         + ", removedByUUID="
         + this.getRemovedByUUID()
         + ", removedByName="
         + this.getRemovedByName()
         + ", removalReason="
         + this.getRemovalReason()
         + ", dateStart="
         + this.getDateStart()
         + ", dateEnd="
         + this.getDateEnd()
         + ", serverScope="
         + this.getServerScope()
         + ", serverOrigin="
         + this.getServerOrigin()
         + ", template="
         + this.getTemplate()
         + ", silent="
         + this.isSilent()
         + ", ipban="
         + this.isIpban()
         + ", active="
         + this.isActive()
         + ")";
   }

   @Generated
   public byte getTemplate() {
      return this.template;
   }
}
