package litebans.punishment;

public enum PunishmentStage {
   INIT,
   PRE_TARGET,
   POST_TARGET,
   ENTRY_CREATED,
   ENTRY_EMITTED,
   ENTRY_APPLIED,
   FINALIZED,
   CANCELLED;
   private static final PunishmentStage[] punishmentStageArray = getPunishmentStageArray();

   public static PunishmentStage getPunishmentStage(String var0) {
      return Enum.valueOf(PunishmentStage.class, var0);
   }

   private static final PunishmentStage[] getPunishmentStageArray() {
      return new PunishmentStage[]{INIT, PRE_TARGET, POST_TARGET, ENTRY_CREATED, ENTRY_EMITTED, ENTRY_APPLIED, FINALIZED, CANCELLED};
   }
}
