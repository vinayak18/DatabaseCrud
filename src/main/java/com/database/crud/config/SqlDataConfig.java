package com.database.crud.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlDataConfig {
	private String sqlName;
	private String queryString;
	private String tableName;
	
}
