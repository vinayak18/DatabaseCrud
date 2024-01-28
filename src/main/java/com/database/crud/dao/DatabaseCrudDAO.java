package com.database.crud.dao;

import org.springframework.stereotype.Component;

import com.database.crud.config.SqlDataConfig;
import com.database.crud.exception.InvalidRequestException;
import com.database.crud.exception.RequestProcessingException;
import com.database.crud.model.ExecuteSqlRequest;
import com.database.crud.model.ExecuteSqlResponse;

@Component
public interface DatabaseCrudDAO {

	public ExecuteSqlResponse retrieveSqlSelectStmtData(SqlDataConfig sqlConfig, ExecuteSqlRequest databaseCrudRequest) throws RequestProcessingException, InvalidRequestException;

	public ExecuteSqlResponse executeDatabaseStatement(SqlDataConfig sqlConfig, ExecuteSqlRequest databaseCrudRequest) throws InvalidRequestException, RequestProcessingException; 

}
