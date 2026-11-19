package litebans.lib.hikari;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.sql.Wrapper;

public final class HikariProxyDatabaseMetaData extends ProxyDatabaseMetaData implements Wrapper, DatabaseMetaData {
   @Override
   public boolean isWrapperFor(Class var1) {
      try {
      try {
         return super.databaseMetaData.isWrapperFor(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean allProceduresAreCallable() {
      try {
      try {
         return super.databaseMetaData.allProceduresAreCallable();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean allTablesAreSelectable() {
      try {
      try {
         return super.databaseMetaData.allTablesAreSelectable();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getURL() {
      try {
      try {
         return super.databaseMetaData.getURL();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getUserName() {
      try {
      try {
         return super.databaseMetaData.getUserName();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isReadOnly() {
      try {
      try {
         return super.databaseMetaData.isReadOnly();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean nullsAreSortedHigh() {
      try {
      try {
         return super.databaseMetaData.nullsAreSortedHigh();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean nullsAreSortedLow() {
      try {
      try {
         return super.databaseMetaData.nullsAreSortedLow();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean nullsAreSortedAtStart() {
      try {
      try {
         return super.databaseMetaData.nullsAreSortedAtStart();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean nullsAreSortedAtEnd() {
      try {
      try {
         return super.databaseMetaData.nullsAreSortedAtEnd();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getDatabaseProductName() {
      try {
      try {
         return super.databaseMetaData.getDatabaseProductName();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getDatabaseProductVersion() {
      try {
      try {
         return super.databaseMetaData.getDatabaseProductVersion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getDriverName() {
      try {
      try {
         return super.databaseMetaData.getDriverName();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getDriverVersion() {
      try {
      try {
         return super.databaseMetaData.getDriverVersion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getDriverMajorVersion() {
      return super.databaseMetaData.getDriverMajorVersion();
   }

   @Override
   public int getDriverMinorVersion() {
      return super.databaseMetaData.getDriverMinorVersion();
   }

   @Override
   public boolean usesLocalFiles() {
      try {
      try {
         return super.databaseMetaData.usesLocalFiles();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean usesLocalFilePerTable() {
      try {
      try {
         return super.databaseMetaData.usesLocalFilePerTable();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsMixedCaseIdentifiers() {
      try {
      try {
         return super.databaseMetaData.supportsMixedCaseIdentifiers();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean storesUpperCaseIdentifiers() {
      try {
      try {
         return super.databaseMetaData.storesUpperCaseIdentifiers();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean storesLowerCaseIdentifiers() {
      try {
      try {
         return super.databaseMetaData.storesLowerCaseIdentifiers();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean storesMixedCaseIdentifiers() {
      try {
      try {
         return super.databaseMetaData.storesMixedCaseIdentifiers();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsMixedCaseQuotedIdentifiers() {
      try {
      try {
         return super.databaseMetaData.supportsMixedCaseQuotedIdentifiers();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean storesUpperCaseQuotedIdentifiers() {
      try {
      try {
         return super.databaseMetaData.storesUpperCaseQuotedIdentifiers();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean storesLowerCaseQuotedIdentifiers() {
      try {
      try {
         return super.databaseMetaData.storesLowerCaseQuotedIdentifiers();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean storesMixedCaseQuotedIdentifiers() {
      try {
      try {
         return super.databaseMetaData.storesMixedCaseQuotedIdentifiers();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getIdentifierQuoteString() {
      try {
      try {
         return super.databaseMetaData.getIdentifierQuoteString();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getSQLKeywords() {
      try {
      try {
         return super.databaseMetaData.getSQLKeywords();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getNumericFunctions() {
      try {
      try {
         return super.databaseMetaData.getNumericFunctions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getStringFunctions() {
      try {
      try {
         return super.databaseMetaData.getStringFunctions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getSystemFunctions() {
      try {
      try {
         return super.databaseMetaData.getSystemFunctions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getTimeDateFunctions() {
      try {
      try {
         return super.databaseMetaData.getTimeDateFunctions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getSearchStringEscape() {
      try {
      try {
         return super.databaseMetaData.getSearchStringEscape();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getExtraNameCharacters() {
      try {
      try {
         return super.databaseMetaData.getExtraNameCharacters();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsAlterTableWithAddColumn() {
      try {
      try {
         return super.databaseMetaData.supportsAlterTableWithAddColumn();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsAlterTableWithDropColumn() {
      try {
      try {
         return super.databaseMetaData.supportsAlterTableWithDropColumn();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsColumnAliasing() {
      try {
      try {
         return super.databaseMetaData.supportsColumnAliasing();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean nullPlusNonNullIsNull() {
      try {
      try {
         return super.databaseMetaData.nullPlusNonNullIsNull();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsConvert() {
      try {
      try {
         return super.databaseMetaData.supportsConvert();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsConvert(int var1, int var2) {
      try {
      try {
         return super.databaseMetaData.supportsConvert(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsTableCorrelationNames() {
      try {
      try {
         return super.databaseMetaData.supportsTableCorrelationNames();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsDifferentTableCorrelationNames() {
      try {
      try {
         return super.databaseMetaData.supportsDifferentTableCorrelationNames();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsExpressionsInOrderBy() {
      try {
      try {
         return super.databaseMetaData.supportsExpressionsInOrderBy();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsOrderByUnrelated() {
      try {
      try {
         return super.databaseMetaData.supportsOrderByUnrelated();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsGroupBy() {
      try {
      try {
         return super.databaseMetaData.supportsGroupBy();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsGroupByUnrelated() {
      try {
      try {
         return super.databaseMetaData.supportsGroupByUnrelated();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsGroupByBeyondSelect() {
      try {
      try {
         return super.databaseMetaData.supportsGroupByBeyondSelect();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsLikeEscapeClause() {
      try {
      try {
         return super.databaseMetaData.supportsLikeEscapeClause();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsMultipleResultSets() {
      try {
      try {
         return super.databaseMetaData.supportsMultipleResultSets();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsMultipleTransactions() {
      try {
      try {
         return super.databaseMetaData.supportsMultipleTransactions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsNonNullableColumns() {
      try {
      try {
         return super.databaseMetaData.supportsNonNullableColumns();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsMinimumSQLGrammar() {
      try {
      try {
         return super.databaseMetaData.supportsMinimumSQLGrammar();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsCoreSQLGrammar() {
      try {
      try {
         return super.databaseMetaData.supportsCoreSQLGrammar();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsExtendedSQLGrammar() {
      try {
      try {
         return super.databaseMetaData.supportsExtendedSQLGrammar();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsANSI92EntryLevelSQL() {
      try {
      try {
         return super.databaseMetaData.supportsANSI92EntryLevelSQL();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsANSI92IntermediateSQL() {
      try {
      try {
         return super.databaseMetaData.supportsANSI92IntermediateSQL();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsANSI92FullSQL() {
      try {
      try {
         return super.databaseMetaData.supportsANSI92FullSQL();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsIntegrityEnhancementFacility() {
      try {
      try {
         return super.databaseMetaData.supportsIntegrityEnhancementFacility();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsOuterJoins() {
      try {
      try {
         return super.databaseMetaData.supportsOuterJoins();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsFullOuterJoins() {
      try {
      try {
         return super.databaseMetaData.supportsFullOuterJoins();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsLimitedOuterJoins() {
      try {
      try {
         return super.databaseMetaData.supportsLimitedOuterJoins();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getSchemaTerm() {
      try {
      try {
         return super.databaseMetaData.getSchemaTerm();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getProcedureTerm() {
      try {
      try {
         return super.databaseMetaData.getProcedureTerm();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getCatalogTerm() {
      try {
      try {
         return super.databaseMetaData.getCatalogTerm();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isCatalogAtStart() {
      try {
      try {
         return super.databaseMetaData.isCatalogAtStart();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getCatalogSeparator() {
      try {
      try {
         return super.databaseMetaData.getCatalogSeparator();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsSchemasInDataManipulation() {
      try {
      try {
         return super.databaseMetaData.supportsSchemasInDataManipulation();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsSchemasInProcedureCalls() {
      try {
      try {
         return super.databaseMetaData.supportsSchemasInProcedureCalls();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsSchemasInTableDefinitions() {
      try {
      try {
         return super.databaseMetaData.supportsSchemasInTableDefinitions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsSchemasInIndexDefinitions() {
      try {
      try {
         return super.databaseMetaData.supportsSchemasInIndexDefinitions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsSchemasInPrivilegeDefinitions() {
      try {
      try {
         return super.databaseMetaData.supportsSchemasInPrivilegeDefinitions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsCatalogsInDataManipulation() {
      try {
      try {
         return super.databaseMetaData.supportsCatalogsInDataManipulation();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsCatalogsInProcedureCalls() {
      try {
      try {
         return super.databaseMetaData.supportsCatalogsInProcedureCalls();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsCatalogsInTableDefinitions() {
      try {
      try {
         return super.databaseMetaData.supportsCatalogsInTableDefinitions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsCatalogsInIndexDefinitions() {
      try {
      try {
         return super.databaseMetaData.supportsCatalogsInIndexDefinitions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsCatalogsInPrivilegeDefinitions() {
      try {
      try {
         return super.databaseMetaData.supportsCatalogsInPrivilegeDefinitions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsPositionedDelete() {
      try {
      try {
         return super.databaseMetaData.supportsPositionedDelete();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsPositionedUpdate() {
      try {
      try {
         return super.databaseMetaData.supportsPositionedUpdate();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsSelectForUpdate() {
      try {
      try {
         return super.databaseMetaData.supportsSelectForUpdate();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsStoredProcedures() {
      try {
      try {
         return super.databaseMetaData.supportsStoredProcedures();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsSubqueriesInComparisons() {
      try {
      try {
         return super.databaseMetaData.supportsSubqueriesInComparisons();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsSubqueriesInExists() {
      try {
      try {
         return super.databaseMetaData.supportsSubqueriesInExists();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsSubqueriesInIns() {
      try {
      try {
         return super.databaseMetaData.supportsSubqueriesInIns();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsSubqueriesInQuantifieds() {
      try {
      try {
         return super.databaseMetaData.supportsSubqueriesInQuantifieds();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsCorrelatedSubqueries() {
      try {
      try {
         return super.databaseMetaData.supportsCorrelatedSubqueries();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsUnion() {
      try {
      try {
         return super.databaseMetaData.supportsUnion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsUnionAll() {
      try {
      try {
         return super.databaseMetaData.supportsUnionAll();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsOpenCursorsAcrossCommit() {
      try {
      try {
         return super.databaseMetaData.supportsOpenCursorsAcrossCommit();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsOpenCursorsAcrossRollback() {
      try {
      try {
         return super.databaseMetaData.supportsOpenCursorsAcrossRollback();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsOpenStatementsAcrossCommit() {
      try {
      try {
         return super.databaseMetaData.supportsOpenStatementsAcrossCommit();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsOpenStatementsAcrossRollback() {
      try {
      try {
         return super.databaseMetaData.supportsOpenStatementsAcrossRollback();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxBinaryLiteralLength() {
      try {
      try {
         return super.databaseMetaData.getMaxBinaryLiteralLength();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxCharLiteralLength() {
      try {
      try {
         return super.databaseMetaData.getMaxCharLiteralLength();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxColumnNameLength() {
      try {
      try {
         return super.databaseMetaData.getMaxColumnNameLength();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxColumnsInGroupBy() {
      try {
      try {
         return super.databaseMetaData.getMaxColumnsInGroupBy();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxColumnsInIndex() {
      try {
      try {
         return super.databaseMetaData.getMaxColumnsInIndex();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxColumnsInOrderBy() {
      try {
      try {
         return super.databaseMetaData.getMaxColumnsInOrderBy();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxColumnsInSelect() {
      try {
      try {
         return super.databaseMetaData.getMaxColumnsInSelect();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxColumnsInTable() {
      try {
      try {
         return super.databaseMetaData.getMaxColumnsInTable();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxConnections() {
      try {
      try {
         return super.databaseMetaData.getMaxConnections();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxCursorNameLength() {
      try {
      try {
         return super.databaseMetaData.getMaxCursorNameLength();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxIndexLength() {
      try {
      try {
         return super.databaseMetaData.getMaxIndexLength();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxSchemaNameLength() {
      try {
      try {
         return super.databaseMetaData.getMaxSchemaNameLength();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxProcedureNameLength() {
      try {
      try {
         return super.databaseMetaData.getMaxProcedureNameLength();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxCatalogNameLength() {
      try {
      try {
         return super.databaseMetaData.getMaxCatalogNameLength();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxRowSize() {
      try {
      try {
         return super.databaseMetaData.getMaxRowSize();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean doesMaxRowSizeIncludeBlobs() {
      try {
      try {
         return super.databaseMetaData.doesMaxRowSizeIncludeBlobs();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxStatementLength() {
      try {
      try {
         return super.databaseMetaData.getMaxStatementLength();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxStatements() {
      try {
      try {
         return super.databaseMetaData.getMaxStatements();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxTableNameLength() {
      try {
      try {
         return super.databaseMetaData.getMaxTableNameLength();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxTablesInSelect() {
      try {
      try {
         return super.databaseMetaData.getMaxTablesInSelect();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxUserNameLength() {
      try {
      try {
         return super.databaseMetaData.getMaxUserNameLength();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getDefaultTransactionIsolation() {
      try {
      try {
         return super.databaseMetaData.getDefaultTransactionIsolation();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsTransactions() {
      try {
      try {
         return super.databaseMetaData.supportsTransactions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsTransactionIsolationLevel(int var1) {
      try {
      try {
         return super.databaseMetaData.supportsTransactionIsolationLevel(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsDataDefinitionAndDataManipulationTransactions() {
      try {
      try {
         return super.databaseMetaData.supportsDataDefinitionAndDataManipulationTransactions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsDataManipulationTransactionsOnly() {
      try {
      try {
         return super.databaseMetaData.supportsDataManipulationTransactionsOnly();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean dataDefinitionCausesTransactionCommit() {
      try {
      try {
         return super.databaseMetaData.dataDefinitionCausesTransactionCommit();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean dataDefinitionIgnoredInTransactions() {
      try {
      try {
         return super.databaseMetaData.dataDefinitionIgnoredInTransactions();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getProcedures(String var1, String var2, String var3) {
      try {
      try {
         return super.getProcedures(var1, var2, var3);
      } catch (Exception var5) {
         if (!(var5 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getProcedureColumns(String var1, String var2, String var3, String var4) {
      try {
      try {
         return super.getProcedureColumns(var1, var2, var3, var4);
      } catch (Exception var6) {
         if (!(var6 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var6); }
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getTables(String var1, String var2, String var3, String[] var4) {
      try {
      try {
         return super.getTables(var1, var2, var3, var4);
      } catch (Exception var6) {
         if (!(var6 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var6); }
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getSchemas() {
      try {
      try {
         return super.getSchemas();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getCatalogs() {
      try {
      try {
         return super.getCatalogs();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getTableTypes() {
      try {
      try {
         return super.getTableTypes();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getColumns(String var1, String var2, String var3, String var4) {
      try {
      try {
         return super.getColumns(var1, var2, var3, var4);
      } catch (Exception var6) {
         if (!(var6 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var6); }
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getColumnPrivileges(String var1, String var2, String var3, String var4) {
      try {
      try {
         return super.getColumnPrivileges(var1, var2, var3, var4);
      } catch (Exception var6) {
         if (!(var6 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var6); }
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getTablePrivileges(String var1, String var2, String var3) {
      try {
      try {
         return super.getTablePrivileges(var1, var2, var3);
      } catch (Exception var5) {
         if (!(var5 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getBestRowIdentifier(String var1, String var2, String var3, int var4, boolean var5) {
      try {
      try {
         return super.getBestRowIdentifier(var1, var2, var3, var4, var5);
      } catch (Exception var7) {
         if (!(var7 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var7); }
         throw this.getSQLException((SQLException)var7);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getVersionColumns(String var1, String var2, String var3) {
      try {
      try {
         return super.getVersionColumns(var1, var2, var3);
      } catch (Exception var5) {
         if (!(var5 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getPrimaryKeys(String var1, String var2, String var3) {
      try {
      try {
         return super.getPrimaryKeys(var1, var2, var3);
      } catch (Exception var5) {
         if (!(var5 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getImportedKeys(String var1, String var2, String var3) {
      try {
      try {
         return super.getImportedKeys(var1, var2, var3);
      } catch (Exception var5) {
         if (!(var5 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getExportedKeys(String var1, String var2, String var3) {
      try {
      try {
         return super.getExportedKeys(var1, var2, var3);
      } catch (Exception var5) {
         if (!(var5 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getCrossReference(String var1, String var2, String var3, String var4, String var5, String var6) {
      try {
      try {
         return super.getCrossReference(var1, var2, var3, var4, var5, var6);
      } catch (Exception var8) {
         if (!(var8 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var8); }
         throw this.getSQLException((SQLException)var8);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getTypeInfo() {
      try {
      try {
         return super.getTypeInfo();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getIndexInfo(String var1, String var2, String var3, boolean var4, boolean var5) {
      try {
      try {
         return super.getIndexInfo(var1, var2, var3, var4, var5);
      } catch (Exception var7) {
         if (!(var7 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var7); }
         throw this.getSQLException((SQLException)var7);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsResultSetType(int var1) {
      try {
      try {
         return super.databaseMetaData.supportsResultSetType(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsResultSetConcurrency(int var1, int var2) {
      try {
      try {
         return super.databaseMetaData.supportsResultSetConcurrency(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean ownUpdatesAreVisible(int var1) {
      try {
      try {
         return super.databaseMetaData.ownUpdatesAreVisible(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean ownDeletesAreVisible(int var1) {
      try {
      try {
         return super.databaseMetaData.ownDeletesAreVisible(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean ownInsertsAreVisible(int var1) {
      try {
      try {
         return super.databaseMetaData.ownInsertsAreVisible(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean othersUpdatesAreVisible(int var1) {
      try {
      try {
         return super.databaseMetaData.othersUpdatesAreVisible(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean othersDeletesAreVisible(int var1) {
      try {
      try {
         return super.databaseMetaData.othersDeletesAreVisible(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean othersInsertsAreVisible(int var1) {
      try {
      try {
         return super.databaseMetaData.othersInsertsAreVisible(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean updatesAreDetected(int var1) {
      try {
      try {
         return super.databaseMetaData.updatesAreDetected(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean deletesAreDetected(int var1) {
      try {
      try {
         return super.databaseMetaData.deletesAreDetected(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean insertsAreDetected(int var1) {
      try {
      try {
         return super.databaseMetaData.insertsAreDetected(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsBatchUpdates() {
      try {
      try {
         return super.databaseMetaData.supportsBatchUpdates();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getUDTs(String var1, String var2, String var3, int[] var4) {
      try {
      try {
         return super.getUDTs(var1, var2, var3, var4);
      } catch (Exception var6) {
         if (!(var6 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var6); }
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsSavepoints() {
      try {
      try {
         return super.databaseMetaData.supportsSavepoints();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsNamedParameters() {
      try {
      try {
         return super.databaseMetaData.supportsNamedParameters();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsMultipleOpenResults() {
      try {
      try {
         return super.databaseMetaData.supportsMultipleOpenResults();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsGetGeneratedKeys() {
      try {
      try {
         return super.databaseMetaData.supportsGetGeneratedKeys();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getSuperTypes(String var1, String var2, String var3) {
      try {
      try {
         return super.getSuperTypes(var1, var2, var3);
      } catch (Exception var5) {
         if (!(var5 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getSuperTables(String var1, String var2, String var3) {
      try {
      try {
         return super.getSuperTables(var1, var2, var3);
      } catch (Exception var5) {
         if (!(var5 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getAttributes(String var1, String var2, String var3, String var4) {
      try {
      try {
         return super.getAttributes(var1, var2, var3, var4);
      } catch (Exception var6) {
         if (!(var6 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var6); }
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsResultSetHoldability(int var1) {
      try {
      try {
         return super.databaseMetaData.supportsResultSetHoldability(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getResultSetHoldability() {
      try {
      try {
         return super.databaseMetaData.getResultSetHoldability();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getDatabaseMajorVersion() {
      try {
      try {
         return super.databaseMetaData.getDatabaseMajorVersion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getDatabaseMinorVersion() {
      try {
      try {
         return super.databaseMetaData.getDatabaseMinorVersion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getJDBCMajorVersion() {
      try {
      try {
         return super.databaseMetaData.getJDBCMajorVersion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getJDBCMinorVersion() {
      try {
      try {
         return super.databaseMetaData.getJDBCMinorVersion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getSQLStateType() {
      try {
      try {
         return super.databaseMetaData.getSQLStateType();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean locatorsUpdateCopy() {
      try {
      try {
         return super.databaseMetaData.locatorsUpdateCopy();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsStatementPooling() {
      try {
      try {
         return super.databaseMetaData.supportsStatementPooling();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public RowIdLifetime getRowIdLifetime() {
      try {
      try {
         return super.databaseMetaData.getRowIdLifetime();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getSchemas(String var1, String var2) {
      try {
      try {
         return super.getSchemas(var1, var2);
      } catch (Exception var4) {
         if (!(var4 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsStoredFunctionsUsingCallSyntax() {
      try {
      try {
         return super.databaseMetaData.supportsStoredFunctionsUsingCallSyntax();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean autoCommitFailureClosesAllResultSets() {
      try {
      try {
         return super.databaseMetaData.autoCommitFailureClosesAllResultSets();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getClientInfoProperties() {
      try {
      try {
         return super.getClientInfoProperties();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getFunctions(String var1, String var2, String var3) {
      try {
      try {
         return super.getFunctions(var1, var2, var3);
      } catch (Exception var5) {
         if (!(var5 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getFunctionColumns(String var1, String var2, String var3, String var4) {
      try {
      try {
         return super.getFunctionColumns(var1, var2, var3, var4);
      } catch (Exception var6) {
         if (!(var6 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var6); }
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getPseudoColumns(String var1, String var2, String var3, String var4) {
      try {
      try {
         return super.getPseudoColumns(var1, var2, var3, var4);
      } catch (Exception var6) {
         if (!(var6 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var6); }
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean generatedKeyAlwaysReturned() {
      try {
      try {
         return super.databaseMetaData.generatedKeyAlwaysReturned();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getMaxLogicalLobSize() {
      try {
      try {
         return super.databaseMetaData.getMaxLogicalLobSize();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean supportsRefCursors() {
      try {
      try {
         return super.databaseMetaData.supportsRefCursors();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public HikariProxyDatabaseMetaData(ProxyConnection var1, DatabaseMetaData var2) {
      super(var1, var2);
   }
}
