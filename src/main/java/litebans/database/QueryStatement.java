package litebans.database;

import java.io.Closeable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.randomid.UuidBytes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class QueryStatement implements Closeable {
   private final PunishmentStorage punishmentStorage;
   private final PreparedStatement preparedStatement;
   private final String string;
   private byte byteValue;

   private QueryStatement(PunishmentStorage var1, PreparedStatement var2, String var3) {
      this.punishmentStorage = var1;
      this.preparedStatement = var2;
      this.string = var3;
   }

   public final PunishmentStorage getPunishmentStorage() {
      return this.punishmentStorage;
   }

   public final PreparedStatement getPreparedStatement() {
      return this.preparedStatement;
   }

   public final String getString() {
      return this.string;
   }

   public QueryStatement getQueryStatement(boolean var1) {
      PreparedStatement var10000 = this.preparedStatement;
      this.byteValue = UByte.getByte((byte)(this.byteValue + 1));
      try {
         var10000.setBoolean(this.byteValue & 255, var1);
      } catch (SQLException var2) {
         throw new RuntimeException(var2);
      }
      return this;
   }

   public final QueryStatement getQueryStatement(@NotNull byte[] var1) {
      PreparedStatement var10000 = this.preparedStatement;
      this.byteValue = UByte.getByte((byte)(this.byteValue + 1));
      try {
         var10000.setBytes(this.byteValue & 255, var1);
      } catch (SQLException var2) {
         throw new RuntimeException(var2);
      }
      return this;
   }

   public final QueryStatement getQueryStatement(byte var1) {
      PreparedStatement var10000 = this.preparedStatement;
      this.byteValue = UByte.getByte((byte)(this.byteValue + 1));
      try {
         var10000.setInt(this.byteValue & 255, var1 & 255);
      } catch (SQLException var2) {
         throw new RuntimeException(var2);
      }
      return this;
   }

   public final QueryStatement getQueryStatement(int var1) {
      PreparedStatement var10000 = this.preparedStatement;
      this.byteValue = UByte.getByte((byte)(this.byteValue + 1));
      try {
         var10000.setInt(this.byteValue & 255, var1);
      } catch (SQLException var2) {
         throw new RuntimeException(var2);
      }
      return this;
   }

   public final QueryStatement getQueryStatement(long var1) {
      PreparedStatement var10000 = this.preparedStatement;
      this.byteValue = UByte.getByte((byte)(this.byteValue + 1));
      try {
         var10000.setLong(this.byteValue & 255, var1);
      } catch (SQLException var2) {
         throw new RuntimeException(var2);
      }
      return this;
   }

   public final QueryStatement getQueryStatement(@NotNull Number var1) {
      PreparedStatement var10000 = this.preparedStatement;
      this.byteValue = UByte.getByte((byte)(this.byteValue + 1));
      try {
         var10000.setLong(this.byteValue & 255, var1.longValue());
      } catch (SQLException var2) {
         throw new RuntimeException(var2);
      }
      return this;
   }

   public final QueryStatement getQueryStatement(@Nullable String var1) {
      PreparedStatement var10000 = this.preparedStatement;
      this.byteValue = UByte.getByte((byte)(this.byteValue + 1));
      try {
         var10000.setString(this.byteValue & 255, var1);
      } catch (SQLException var2) {
         throw new RuntimeException(var2);
      }
      return this;
   }

   public final QueryStatement getQueryStatement(@NotNull Timestamp var1) {
      PreparedStatement var10000 = this.preparedStatement;
      this.byteValue = UByte.getByte((byte)(this.byteValue + 1));
      try {
         var10000.setTimestamp(this.byteValue & 255, var1);
      } catch (SQLException var2) {
         throw new RuntimeException(var2);
      }
      return this;
   }

   public final QueryStatement getQueryStatement(@NotNull UUID var1) {
      return this.getQueryStatement(UuidBytes.getByteArray(var1));
   }

   @Override
   public void close() {
      try {
         this.preparedStatement.close();
      } catch (SQLException var2) {
         throw new RuntimeException(var2);
      }
   }

   public QueryStatement(PunishmentStorage var1, PreparedStatement var2, String var3, DefaultConstructorMarker var4) {
      this(var1, var2, var3);
   }
}
