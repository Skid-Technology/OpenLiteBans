package litebans.lib.compress.archivers.dump;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public enum DumpPermission {
   SETUID(2048),
   SETGUI(1024),
   STICKY(512),
   USER_READ(256),
   USER_WRITE(128),
   USER_EXEC(64),
   GROUP_READ(32),
   GROUP_WRITE(16),
   GROUP_EXEC(8),
   WORLD_READ(4),
   WORLD_WRITE(2),
   WORLD_EXEC(1);
   private int intValue;
   private static final DumpPermission[] dumpPermissionArray = new DumpPermission[]{
      SETUID, SETGUI, STICKY, USER_READ, USER_WRITE, USER_EXEC, GROUP_READ, GROUP_WRITE, GROUP_EXEC, WORLD_READ, WORLD_WRITE, WORLD_EXEC
   };

   public static final DumpPermission getDumpPermission(String var0) {
      return Enum.valueOf(DumpPermission.class, var0);
   }

   private DumpPermission(int var3) {
      this.intValue = var3;
   }

   public static final Set getSet(int var0) {
      HashSet var1 = new HashSet();

      for (DumpPermission var5 : values()) {
         if ((var0 & var5.intValue) == var5.intValue) {
            var1.add(var5);
         }
      }

      return (Set)(var1.isEmpty() ? Collections.emptySet() : EnumSet.copyOf(var1));
   }
}
