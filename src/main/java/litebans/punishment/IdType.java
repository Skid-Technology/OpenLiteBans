package litebans.punishment;

import litebans.lib.kotlin.collections.EnumEntries;
import litebans.lib.kotlin.collections.EnumEntriesKt;

public enum IdType {
   UUID,
   EXECUTOR_UUID,
   ID,
   CUSTOM;
   private static final IdType[] idTypeArray = getIdTypeArray();
   private static final EnumEntries enumEntries = EnumEntriesKt.getEnumEntries(idTypeArray);

   public static IdType getIdType(String var0) {
      return Enum.valueOf(IdType.class, var0);
   }

   private static final IdType[] getIdTypeArray() {
      return new IdType[]{UUID, EXECUTOR_UUID, ID, CUSTOM};
   }
}
