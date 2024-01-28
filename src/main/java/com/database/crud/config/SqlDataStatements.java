package com.database.crud.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(prefix = "sql-data-statements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlDataStatements {
	private List<SqlDataConfig> sqlDataConfigList = new ArrayList<>();
	
	public SqlDataConfig getConfigBySqlName(String sqlName) {
		if(null != sqlDataConfigList && !sqlDataConfigList.isEmpty()) {
			for(SqlDataConfig data: sqlDataConfigList) {
				if(data.getSqlName().equalsIgnoreCase(sqlName)) {
					return data;
				}
			}
		}
		return null;
	}
}
