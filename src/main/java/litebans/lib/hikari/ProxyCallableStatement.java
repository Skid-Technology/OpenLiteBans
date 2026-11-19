package litebans.lib.hikari;

import java.sql.CallableStatement;

public abstract class ProxyCallableStatement extends ProxyPreparedStatement implements CallableStatement {
   public ProxyCallableStatement(ProxyConnection var1, CallableStatement var2) {
      super(var1, var2);
   }
}
