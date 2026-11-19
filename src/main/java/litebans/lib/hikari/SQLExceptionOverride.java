package litebans.lib.hikari;

import java.sql.SQLException;

public interface SQLExceptionOverride {
   default SQLExceptionOverrideResult getSQLExceptionOverrideResult(SQLException a) {
      return SQLExceptionOverrideResult.CONTINUE_EVICT;
   }
}
