package com.database.crud.service;

import com.database.crud.exception.InvalidRequestException;
import com.database.crud.exception.RequestProcessingException;
import com.database.crud.model.ExecuteSqlRequest;
import com.database.crud.model.ExecuteSqlResponse;

import jakarta.validation.Valid;

public interface DatabaseCrudService {

	public ExecuteSqlResponse retrieveSqlSelectStmtData(ExecuteSqlRequest databaseCrudRequest) throws RequestProcessingException, InvalidRequestException;

	public ExecuteSqlResponse executeDatabaseStatement(@Valid ExecuteSqlRequest databaseCrudRequest) throws InvalidRequestException, RequestProcessingException;

}
