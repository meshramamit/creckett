package com.creckett.config;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * An improved naming strategy that prefers embedded underscores to mixed case
 * names and converts names to <CODE>UPPERCASE</CODE>.
 * 
 */
public class DatabaseNamingStrategy extends ImprovedNamingStrategy {

	private static final long serialVersionUID = 3904953557637026493L;

	@Override
	public String classToTableName(String className) {
		return super.classToTableName(className).toUpperCase();
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return super.propertyToColumnName(propertyName).toUpperCase();
	}

	@Override
	public String collectionTableName(String ownerEntity, String ownerEntityTable, String associatedEntity,
			String associatedEntityTable, String propertyName) {
		return super.collectionTableName(ownerEntity, ownerEntityTable, associatedEntity,
				associatedEntityTable, propertyName).toUpperCase();
	}

	@Override
	public String columnName(String columnName) {
		return super.columnName(columnName).toUpperCase();
	}

	@Override
	public String foreignKeyColumnName(String propertyName, String propertyEntityName,
			String propertyTableName, String referencedColumnName) {
		return super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName,
				referencedColumnName).toUpperCase();
	}

	@Override
	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
		return super.joinKeyColumnName(joinedColumn, joinedTable).toUpperCase();
	}

	@Override
	public String logicalCollectionColumnName(String columnName, String propertyName, String referencedColumn) {
		return super.logicalCollectionColumnName(columnName, propertyName, referencedColumn).toUpperCase();
	}

	@Override
	public String logicalCollectionTableName(String tableName, String ownerEntityTable,
			String associatedEntityTable, String propertyName) {
		return super.logicalCollectionTableName(tableName, ownerEntityTable, associatedEntityTable,
				propertyName).toUpperCase();
	}

	@Override
	public String logicalColumnName(String columnName, String propertyName) {
		return super.logicalColumnName(columnName, propertyName).toUpperCase();
	}

	@Override
	public String tableName(String tableName) {
		return super.tableName(tableName).toUpperCase();
	}

}
