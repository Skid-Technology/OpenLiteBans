package litebans.chat;

// $VF: synthetic class
public class VersionedComponentSerializerWhenMappings {
   public static final int[] switchMap = new int[ChatComponentVersion.values().length];

   static {
      try {
         switchMap[ChatComponentVersion.V1_16.ordinal()] = 1;
      } catch (NoSuchFieldError var1) {
      }

      try {
         switchMap[ChatComponentVersion.V1_21_5.ordinal()] = 2;
      } catch (NoSuchFieldError var0) {
      }
   }
}
