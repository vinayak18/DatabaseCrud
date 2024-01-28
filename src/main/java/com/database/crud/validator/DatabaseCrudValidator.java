package com.database.crud.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.database.crud.config.SqlDataStatements;
import com.database.crud.exception.InvalidRequestException;
import com.database.crud.model.ExecuteSqlRequest;

import io.micrometer.common.util.StringUtils;

@Component
public class DatabaseCrudValidator {

	@Autowired
	private SqlDataStatements sqlDataStmts;
	
	public boolean validateRequestData(ExecuteSqlRequest databaseCrudRequest) throws InvalidRequestException {
		if(null != databaseCrudRequest) {
			String sqlName = databaseCrudRequest.getSqlName();
			if(StringUtils.isBlank(sqlName)) {
				throw new InvalidRequestException("could not able to fetch the records as sqlName is null");
			}
			else if(null == sqlDataStmts.getConfigBySqlName(sqlName)) {
				throw new InvalidRequestException("SQL data-config does not exists for sqlName - "+sqlName);
			}
			else{
				return true;
			}
		}
		return false;
	}
}
