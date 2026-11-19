package litebans.database;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import litebans.platform.LiteBansPlatform;
import litebans.util.FileUtils;
import org.jetbrains.annotations.NotNull;

public final class LockingStatementExecutor extends StatementExecutor {
   private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

   public LockingStatementExecutor(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public ResultSet executeQuery(@NotNull QueryStatement var1) {
      try {
      if (var1.getPunishmentStorage().isClosed()) {
         return EmptyResultSetHolder.getEmptyResultSet();
      } else {
         Lock var2 = this.reentrantReadWriteLock.writeLock();
         var2.lock();

         ResultSet var3;
         try {
            var3 = var1.getPreparedStatement().execute() ? var1.getPreparedStatement().getResultSet() : EmptyResultSetHolder.getEmptyResultSet();
         } finally {
            var2.unlock();
         }

         return var3;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int[] executeBatch(@NotNull Statement var1) {
      try {
      Lock var2 = this.reentrantReadWriteLock.writeLock();
      var2.lock();

      int[] var3;
      try {
         var3 = var1.executeBatch();
      } finally {
         var2.unlock();
      }

      return var3;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet execute(@NotNull QueryStatement var1) {
      try {
      if (var1.getPunishmentStorage().isClosed()) {
         return EmptyResultSetHolder.getEmptyResultSet();
      } else {
         Lock var2 = this.reentrantReadWriteLock.readLock();
         var2.lock();

         ResultSet var3;
         try {
            var3 = var1.getPreparedStatement().executeQuery();
         } finally {
            var2.unlock();
         }

         return var3;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(@NotNull QueryStatement var1) {
      try {
      if (var1.getPunishmentStorage().isClosed()) {
         return -1;
      } else {
         Lock var2 = this.reentrantReadWriteLock.writeLock();
         var2.lock();

         int var3;
         try {
            var3 = var1.getPreparedStatement().executeUpdate();
         } finally {
            var2.unlock();
         }

         return var3;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getString(@NotNull String var1) {
      return FileUtils.getFile(new File(var1)).getAbsolutePath();
   }
}
