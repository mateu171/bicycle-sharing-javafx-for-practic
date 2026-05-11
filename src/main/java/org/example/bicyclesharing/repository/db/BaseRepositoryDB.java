package org.example.bicyclesharing.repository.db;

import org.example.bicyclesharing.repository.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryDB<T, ID> implements Repository<T, ID> {

  private static final String DB_URL = "jdbc:h2:file:./data/bicyclesharing";
  private static final String DB_USER = "sa";
  private static final String DB_PASSWORD = "";

  public BaseRepositoryDB() {
    createDataFolder();
    initTable();
  }

  private void createDataFolder() {
    try {
      Files.createDirectories(Path.of("data"));
    } catch (IOException e) {
      throw new RuntimeException("Не вдалося створити папку data", e);
    }
  }

  protected Connection getConnection() throws SQLException {
    return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
  }

  private void initTable() {
    try (
        Connection connection = getConnection();
        Statement statement = connection.createStatement()
    ) {
      statement.execute(getCreateTableSQL());
    } catch (SQLException e) {
      throw new RuntimeException("Помилка створення таблиці " + getTableName(), e);
    }
  }

  protected abstract String getCreateTableSQL();

  protected abstract String getTableName();

  protected abstract ID getId(T entity);

  protected abstract T map(ResultSet rs) throws SQLException;

  protected abstract Object[] getInsertValues(T entity);

  protected abstract Object[] getUpdateValues(T entity);

  protected abstract String[] getUpdateColumns();

  protected abstract String getIdColumn();

  @Override
  public T save(T entity) {
    String sql = getInsertSQL(entity);

    try (
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ) {
      setParameters(statement, getInsertValues(entity));
      statement.executeUpdate();
      return entity;
    } catch (SQLException e) {
      throw new RuntimeException("Помилка збереження в таблицю " + getTableName(), e);
    }
  }

  private String getInsertSQL(T entity) {
    int valuesCount = getInsertValues(entity).length;

    String placeholders = String.join(
        ", ",
        java.util.Collections.nCopies(valuesCount, "?")
    );

    return "INSERT INTO " + getTableName() + " VALUES (" + placeholders + ")";
  }

  @Override
  public T update(T entity) {
    String[] columns = getUpdateColumns();

    StringBuilder setClause = new StringBuilder();

    for (int i = 0; i < columns.length; i++) {
      setClause.append(columns[i]).append(" = ?");
      if (i < columns.length - 1) {
        setClause.append(", ");
      }
    }

    String sql = "UPDATE " + getTableName()
        + " SET " + setClause
        + " WHERE " + getIdColumn() + " = ?";

    try (
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ) {
      setParameters(statement, getUpdateValues(entity));
      statement.executeUpdate();
      return entity;
    } catch (SQLException e) {
      throw new RuntimeException("Помилка оновлення таблиці " + getTableName(), e);
    }
  }

  @Override
  public List<T> findAll() {
    String sql = "SELECT * FROM " + getTableName();
    return queryList(sql);
  }

  @Override
  public Optional<T> findById(ID id) {
    String sql = "SELECT * FROM " + getTableName()
        + " WHERE " + getIdColumn() + " = ?";

    List<T> result = queryList(sql, id);
    return result.stream().findFirst();
  }

  @Override
  public boolean deleteById(ID id) {
    String sql = "DELETE FROM " + getTableName()
        + " WHERE " + getIdColumn() + " = ?";

    return executeUpdate(sql, id) > 0;
  }

  @Override
  public boolean delete(T entity) {
    return deleteById(getId(entity));
  }

  @Override
  public boolean existsById(ID id) {
    String sql = "SELECT COUNT(*) FROM " + getTableName()
        + " WHERE " + getIdColumn() + " = ?";

    return queryCount(sql, id) > 0;
  }

  @Override
  public long count() {
    String sql = "SELECT COUNT(*) FROM " + getTableName();
    return queryCount(sql);
  }

  protected List<T> queryList(String sql, Object... params) {
    List<T> result = new ArrayList<>();

    try (
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ) {
      setParameters(statement, params);

      try (ResultSet rs = statement.executeQuery()) {
        while (rs.next()) {
          result.add(map(rs));
        }
      }

      return result;
    } catch (SQLException e) {
      throw new RuntimeException("Помилка SELECT у таблиці " + getTableName(), e);
    }
  }

  protected int executeUpdate(String sql, Object... params) {
    try (
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ) {
      setParameters(statement, params);
      return statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Помилка UPDATE/DELETE у таблиці " + getTableName(), e);
    }
  }

  protected long queryCount(String sql, Object... params) {
    try (
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ) {
      setParameters(statement, params);

      try (ResultSet rs = statement.executeQuery()) {
        return rs.next() ? rs.getLong(1) : 0;
      }
    } catch (SQLException e) {
      throw new RuntimeException("Помилка COUNT у таблиці " + getTableName(), e);
    }
  }

  protected void setParameters(PreparedStatement statement, Object... params) throws SQLException {
    for (int i = 0; i < params.length; i++) {
      statement.setObject(i + 1, params[i]);
    }
  }
}