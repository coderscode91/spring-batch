package org.springframework.batch.item.database.support;

/**
 * Oracle implementation of a  {@link PagingQueryProvider} using
 * database specific features.
 *
 * @author Thomas Risberg
 * @since 2.0
 */
public class Db2PagingQueryProvider extends SqlWindowingPagingQueryProvider {

	@Override
	public String generateFirstPageQuery(int pageSize) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(getSelectClause());
		sql.append(" FROM ").append(getFromClause());
		sql.append(getWhereClause() == null ? "" : " WHERE " + getWhereClause());
		sql.append(" FETCH FIRST ").append(pageSize).append(" ROWS ONLY");

		return sql.toString();
	}

	@Override
	public String generateRemainingPagesQuery(int pageSize) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(getSelectClause());
		sql.append(" FROM ").append(getFromClause());
		sql.append(" WHERE ").append(getSortKey()).append(" > ?");
		sql.append(getWhereClause() == null ? "" : " AND " + getWhereClause());
		sql.append(" FETCH FIRST ").append(pageSize).append(" ROWS ONLY");

		return sql.toString();
	}

}
