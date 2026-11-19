package litebans.lib.hikari;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public final class ProxyFactory {
   public static ProxyConnection getProxyConnection(PoolEntry var0, Connection var1, FastList var2, ProxyLeakTask var3, long var4, boolean var6, boolean var7) {
      return new HikariProxyConnection(var0, var1, var2, var3, var4, var6, var7);
   }

   public static Statement getStatement(ProxyConnection var0, Statement var1) {
      return new HikariProxyStatement(var0, var1);
   }

   public static CallableStatement getCallableStatement(ProxyConnection var0, CallableStatement var1) {
      return new HikariProxyCallableStatement(var0, var1);
   }

   public static PreparedStatement getPreparedStatement(ProxyConnection var0, PreparedStatement var1) {
      return new HikariProxyPreparedStatement(var0, var1);
   }

   public static ResultSet getResultSet(ProxyConnection var0, ProxyStatement var1, ResultSet var2) {
      return new HikariProxyResultSet(var0, var1, var2);
   }

   public static DatabaseMetaData getDatabaseMetaData(ProxyConnection var0, DatabaseMetaData var1) {
      return new HikariProxyDatabaseMetaData(var0, var1);
   }
}
