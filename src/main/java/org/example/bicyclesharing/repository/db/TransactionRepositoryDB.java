package org.example.bicyclesharing.repository.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.example.bicyclesharing.domain.Impl.Transaction;
import org.example.bicyclesharing.domain.enums.TransactionType;
import org.example.bicyclesharing.repository.TransactionRepository;

public class TransactionRepositoryDB extends BaseRepositoryDB<Transaction, UUID> implements TransactionRepository {

  @Override
  public List<Transaction> findByUserId(UUID userId) {
    String sql = """
                SELECT * FROM TRANSACTIONS
                WHERE USER_ID = ?
                ORDER BY TIMESTAMP DESC
                """;

    return queryList(sql, userId.toString());
  }

  @Override
  protected Transaction map(ResultSet rs) throws SQLException {
    Transaction transaction = new Transaction(
        UUID.fromString(rs.getString("USER_ID")),
        rs.getDouble("AMOUNT"),
        TransactionType.valueOf(rs.getString("TYPE")),
        rs.getTimestamp("TIMESTAMP").toLocalDateTime(),
        rs.getString("DESCRIPTION")
    );

    transaction.setId(UUID.fromString(rs.getString("ID")));
    return transaction;
  }

  @Override
  protected String getTableName() {
    return "TRANSACTIONS";
  }

  @Override
  protected UUID getId(Transaction entity) {
    return entity.getId();
  }

  @Override
  protected String getIdColumn() {
    return "ID";
  }

  @Override
  protected String[] getUpdateColumns() {
    return new String[]{
        "USER_ID",
        "AMOUNT",
        "TYPE",
        "TIMESTAMP",
        "DESCRIPTION"
    };
  }

  @Override
  protected Object[] getInsertValues(Transaction entity) {
    return new Object[]{
        entity.getId().toString(),
        entity.getUserId().toString(),
        entity.getAmount(),
        entity.getType().name(),
        Timestamp.valueOf(entity.getTimestamp()),
        entity.getDescription()
    };
  }

  @Override
  protected Object[] getUpdateValues(Transaction entity) {
    return new Object[]{
        entity.getUserId().toString(),
        entity.getAmount(),
        entity.getType().name(),
        Timestamp.valueOf(entity.getTimestamp()),
        entity.getDescription(),
        entity.getId().toString()
    };
  }

  @Override
  protected String getCreateTableSQL() {
    return "CREATE TABLE IF NOT EXISTS TRANSACTIONS (" +
        "ID VARCHAR(36) PRIMARY KEY," +
        "USER_ID VARCHAR(36) NOT NULL," +
        "AMOUNT DOUBLE NOT NULL," +
        "TYPE VARCHAR(50) NOT NULL," +
        "TIMESTAMP TIMESTAMP NOT NULL," +
        "DESCRIPTION VARCHAR(255)" +
        ")";
  }
}