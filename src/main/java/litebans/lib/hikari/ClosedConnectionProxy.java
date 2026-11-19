package litebans.lib.hikari;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public final class ClosedConnectionProxy {
   public static final Connection connection = getConnection();

   private ClosedConnectionProxy() {
   }

   private static final Connection getConnection() {
      InvocationHandler var0 = ClosedConnectionProxy::getObject;
      return (Connection)Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[]{Connection.class}, var0);
   }

   private static final Object getObject(Object var0, Method var1, Object[] var2) {
      try {
      String var3 = var1.getName();
      if ("isClosed".equals(var3)) {
         return Boolean.TRUE;
      } else if ("isValid".equals(var3)) {
         return Boolean.FALSE;
      } else if ("abort".equals(var3)) {
         return void.class;
      } else if ("close".equals(var3)) {
         return void.class;
      } else if ("toString".equals(var3)) {
         return ClosedConnectionProxy.class.getCanonicalName();
      } else {
         throw new SQLException("Connection is closed");
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
