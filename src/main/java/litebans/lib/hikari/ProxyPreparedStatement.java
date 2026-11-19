package litebans.lib.hikari;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class ProxyPreparedStatement extends ProxyStatement implements PreparedStatement {
   public ProxyPreparedStatement(ProxyConnection var1, PreparedStatement var2) {
      super(var1, var2);
   }

   @Override
   public boolean execute() {
      try {
      this.proxyConnection.run();
      return ((PreparedStatement)this.statement).execute();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet executeQuery() {
      try {
      this.proxyConnection.run();
      ResultSet var1 = ((PreparedStatement)this.statement).executeQuery();
      return ProxyFactory.getResultSet(this.proxyConnection, this, var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate() {
      try {
      this.proxyConnection.run();
      return ((PreparedStatement)this.statement).executeUpdate();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate() {
      try {
      this.proxyConnection.run();
      return ((PreparedStatement)this.statement).executeLargeUpdate();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
