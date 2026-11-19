package litebans.lib.hikari;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ProxyDatabaseMetaData implements DatabaseMetaData {
   public final ProxyConnection proxyConnection;
   public final DatabaseMetaData databaseMetaData;

   public ProxyDatabaseMetaData(ProxyConnection var1, DatabaseMetaData var2) {
      this.proxyConnection = var1;
      this.databaseMetaData = var2;
   }

   public final SQLException getSQLException(SQLException var1) {
      return this.proxyConnection.getSQLException(var1);
   }

   @Override
   public final String toString() {
      String var1 = this.databaseMetaData.toString();
      return this.getClass().getSimpleName() + '@' + System.identityHashCode(this) + " wrapping " + var1;
   }

   @Override
   public final Connection getConnection() {
      return this.proxyConnection;
   }

   @Override
   public ResultSet getProcedures(String var1, String var2, String var3) {
      try {
      ResultSet var4 = this.databaseMetaData.getProcedures(var1, var2, var3);
      Statement var5 = var4.getStatement();
      if (var5 != null) {
         var5 = ProxyFactory.getStatement(this.proxyConnection, var5);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var5, var4);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getProcedureColumns(String var1, String var2, String var3, String var4) {
      try {
      ResultSet var5 = this.databaseMetaData.getProcedureColumns(var1, var2, var3, var4);
      Statement var6 = var5.getStatement();
      if (var6 != null) {
         var6 = ProxyFactory.getStatement(this.proxyConnection, var6);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var6, var5);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getTables(String var1, String var2, String var3, String[] var4) {
      try {
      ResultSet var5 = this.databaseMetaData.getTables(var1, var2, var3, var4);
      Statement var6 = var5.getStatement();
      if (var6 != null) {
         var6 = ProxyFactory.getStatement(this.proxyConnection, var6);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var6, var5);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getSchemas() {
      try {
      ResultSet var1 = this.databaseMetaData.getSchemas();
      Statement var2 = var1.getStatement();
      if (var2 != null) {
         var2 = ProxyFactory.getStatement(this.proxyConnection, var2);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var2, var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getCatalogs() {
      try {
      ResultSet var1 = this.databaseMetaData.getCatalogs();
      Statement var2 = var1.getStatement();
      if (var2 != null) {
         var2 = ProxyFactory.getStatement(this.proxyConnection, var2);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var2, var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getTableTypes() {
      try {
      ResultSet var1 = this.databaseMetaData.getTableTypes();
      Statement var2 = var1.getStatement();
      if (var2 != null) {
         var2 = ProxyFactory.getStatement(this.proxyConnection, var2);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var2, var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getColumns(String var1, String var2, String var3, String var4) {
      try {
      ResultSet var5 = this.databaseMetaData.getColumns(var1, var2, var3, var4);
      Statement var6 = var5.getStatement();
      if (var6 != null) {
         var6 = ProxyFactory.getStatement(this.proxyConnection, var6);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var6, var5);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getColumnPrivileges(String var1, String var2, String var3, String var4) {
      try {
      ResultSet var5 = this.databaseMetaData.getColumnPrivileges(var1, var2, var3, var4);
      Statement var6 = var5.getStatement();
      if (var6 != null) {
         var6 = ProxyFactory.getStatement(this.proxyConnection, var6);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var6, var5);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getTablePrivileges(String var1, String var2, String var3) {
      try {
      ResultSet var4 = this.databaseMetaData.getTablePrivileges(var1, var2, var3);
      Statement var5 = var4.getStatement();
      if (var5 != null) {
         var5 = ProxyFactory.getStatement(this.proxyConnection, var5);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var5, var4);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getBestRowIdentifier(String var1, String var2, String var3, int var4, boolean var5) {
      try {
      ResultSet var6 = this.databaseMetaData.getBestRowIdentifier(var1, var2, var3, var4, var5);
      Statement var7 = var6.getStatement();
      if (var7 != null) {
         var7 = ProxyFactory.getStatement(this.proxyConnection, var7);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var7, var6);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getVersionColumns(String var1, String var2, String var3) {
      try {
      ResultSet var4 = this.databaseMetaData.getVersionColumns(var1, var2, var3);
      Statement var5 = var4.getStatement();
      if (var5 != null) {
         var5 = ProxyFactory.getStatement(this.proxyConnection, var5);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var5, var4);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getPrimaryKeys(String var1, String var2, String var3) {
      try {
      ResultSet var4 = this.databaseMetaData.getPrimaryKeys(var1, var2, var3);
      Statement var5 = var4.getStatement();
      if (var5 != null) {
         var5 = ProxyFactory.getStatement(this.proxyConnection, var5);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var5, var4);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getImportedKeys(String var1, String var2, String var3) {
      try {
      ResultSet var4 = this.databaseMetaData.getImportedKeys(var1, var2, var3);
      Statement var5 = var4.getStatement();
      if (var5 != null) {
         var5 = ProxyFactory.getStatement(this.proxyConnection, var5);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var5, var4);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getExportedKeys(String var1, String var2, String var3) {
      try {
      ResultSet var4 = this.databaseMetaData.getExportedKeys(var1, var2, var3);
      Statement var5 = var4.getStatement();
      if (var5 != null) {
         var5 = ProxyFactory.getStatement(this.proxyConnection, var5);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var5, var4);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getCrossReference(String var1, String var2, String var3, String var4, String var5, String var6) {
      try {
      ResultSet var7 = this.databaseMetaData.getCrossReference(var1, var2, var3, var4, var5, var6);
      Statement var8 = var7.getStatement();
      if (var8 != null) {
         var8 = ProxyFactory.getStatement(this.proxyConnection, var8);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var8, var7);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getTypeInfo() {
      try {
      ResultSet var1 = this.databaseMetaData.getTypeInfo();
      Statement var2 = var1.getStatement();
      if (var2 != null) {
         var2 = ProxyFactory.getStatement(this.proxyConnection, var2);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var2, var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getIndexInfo(String var1, String var2, String var3, boolean var4, boolean var5) {
      try {
      ResultSet var6 = this.databaseMetaData.getIndexInfo(var1, var2, var3, var4, var5);
      Statement var7 = var6.getStatement();
      if (var7 != null) {
         var7 = ProxyFactory.getStatement(this.proxyConnection, var7);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var7, var6);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getUDTs(String var1, String var2, String var3, int[] var4) {
      try {
      ResultSet var5 = this.databaseMetaData.getUDTs(var1, var2, var3, var4);
      Statement var6 = var5.getStatement();
      if (var6 != null) {
         var6 = ProxyFactory.getStatement(this.proxyConnection, var6);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var6, var5);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getSuperTypes(String var1, String var2, String var3) {
      try {
      ResultSet var4 = this.databaseMetaData.getSuperTypes(var1, var2, var3);
      Statement var5 = var4.getStatement();
      if (var5 != null) {
         var5 = ProxyFactory.getStatement(this.proxyConnection, var5);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var5, var4);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getSuperTables(String var1, String var2, String var3) {
      try {
      ResultSet var4 = this.databaseMetaData.getSuperTables(var1, var2, var3);
      Statement var5 = var4.getStatement();
      if (var5 != null) {
         var5 = ProxyFactory.getStatement(this.proxyConnection, var5);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var5, var4);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getAttributes(String var1, String var2, String var3, String var4) {
      try {
      ResultSet var5 = this.databaseMetaData.getAttributes(var1, var2, var3, var4);
      Statement var6 = var5.getStatement();
      if (var6 != null) {
         var6 = ProxyFactory.getStatement(this.proxyConnection, var6);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var6, var5);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getSchemas(String var1, String var2) {
      try {
      ResultSet var3 = this.databaseMetaData.getSchemas(var1, var2);
      Statement var4 = var3.getStatement();
      if (var4 != null) {
         var4 = ProxyFactory.getStatement(this.proxyConnection, var4);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var4, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getClientInfoProperties() {
      try {
      ResultSet var1 = this.databaseMetaData.getClientInfoProperties();
      Statement var2 = var1.getStatement();
      if (var2 != null) {
         var2 = ProxyFactory.getStatement(this.proxyConnection, var2);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var2, var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getFunctions(String var1, String var2, String var3) {
      try {
      ResultSet var4 = this.databaseMetaData.getFunctions(var1, var2, var3);
      Statement var5 = var4.getStatement();
      if (var5 != null) {
         var5 = ProxyFactory.getStatement(this.proxyConnection, var5);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var5, var4);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getFunctionColumns(String var1, String var2, String var3, String var4) {
      try {
      ResultSet var5 = this.databaseMetaData.getFunctionColumns(var1, var2, var3, var4);
      Statement var6 = var5.getStatement();
      if (var6 != null) {
         var6 = ProxyFactory.getStatement(this.proxyConnection, var6);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var6, var5);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getPseudoColumns(String var1, String var2, String var3, String var4) {
      try {
      ResultSet var5 = this.databaseMetaData.getPseudoColumns(var1, var2, var3, var4);
      Statement var6 = var5.getStatement();
      if (var6 != null) {
         var6 = ProxyFactory.getStatement(this.proxyConnection, var6);
      }

      return ProxyFactory.getResultSet(this.proxyConnection, (ProxyStatement)var6, var5);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public final Object unwrap(Class var1) {
      try {
      if (var1.isInstance(this.databaseMetaData)) {
         return this.databaseMetaData;
      } else if (this.databaseMetaData != null) {
         return this.databaseMetaData.unwrap(var1);
      } else {
         throw new SQLException("Wrapped DatabaseMetaData is not an instance of " + var1);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
