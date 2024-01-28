package com.database.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.crud.config.SqlDataConfig;
import com.database.crud.config.SqlDataStatements;
import com.database.crud.dao.DatabaseCrudDAO;
import com.database.crud.exception.InvalidRequestException;
import com.database.crud.exception.RequestProcessingException;
import com.database.crud.model.ExecuteSqlRequest;
import com.database.crud.model.ExecuteSqlResponse;
import com.database.crud.validator.DatabaseCrudValidator;

@Service
public class DatabaseCrudServiceImpl implements DatabaseCrudService{

	@Autowired
	private DatabaseCrudDAO databaseCrudDAO;
	
	@Autowired
	private SqlDataStatements sqlDataStmts;
	
	@Autowired
	private DatabaseCrudValidator databaseCrudValidator;
  
	@Override
	public ExecuteSqlResponse retrieveSqlSelectStmtData(ExecuteSqlRequest databaseCrudRequest) throws RequestProcessingException, InvalidRequestException {
		// TODO Auto-generated method stub
		SqlDataConfig sqlConfig = null;
		if(databaseCrudValidator.validateRequestData(databaseCrudRequest)) {
			sqlConfig = sqlDataStmts.getConfigBySqlName(databaseCrudRequest.getSqlName());
		}
		return 	databaseCrudDAO.retrieveSqlSelectStmtData(sqlConfig, databaseCrudRequest);
	}

	@Override
	public ExecuteSqlResponse executeDatabaseStatement(ExecuteSqlRequest databaseCrudRequest) throws InvalidRequestException, RequestProcessingException {
		SqlDataConfig sqlConfig = null;
		if(databaseCrudValidator.validateRequestData(databaseCrudRequest)) {
			sqlConfig = sqlDataStmts.getConfigBySqlName(databaseCrudRequest.getSqlName());
		}
		return 	databaseCrudDAO.executeDatabaseStatement(sqlConfig, databaseCrudRequest);
	}

}
