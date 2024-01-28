package com.database.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.database.crud.exception.InvalidRequestException;
import com.database.crud.exception.RequestProcessingException;
import com.database.crud.model.ExecuteSqlRequest;
import com.database.crud.model.ExecuteSqlResponse;
import com.database.crud.service.DatabaseCrudService;

import jakarta.validation.Valid;

@RestController
public class DatabaseCrudController {
	
	@Autowired
	private DatabaseCrudService databaseCrudService;
	
	@PostMapping(value = "/crud/v1/query/retrieve", produces = "application/json")
	public ResponseEntity<Object> executeSqlSelectStatement(@RequestBody @Valid ExecuteSqlRequest databaseCrudRequest){
		ExecuteSqlResponse response = null;
		try {
			response = databaseCrudService.retrieveSqlSelectStmtData(databaseCrudRequest);
		} catch (RequestProcessingException e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/crud/v1/query/insert", produces = "application/json")
	public ResponseEntity<Object> executeSqlInsertStatement(@RequestBody @Valid ExecuteSqlRequest databaseCrudRequest){
		ExecuteSqlResponse response = null;
		try {
			response = databaseCrudService.executeDatabaseStatement(databaseCrudRequest);
		} catch (RequestProcessingException e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/crud/v1/query/delete", produces = "application/json")
	public ResponseEntity<Object> executeSqlDeleteStatement(@RequestBody @Valid ExecuteSqlRequest databaseCrudRequest){
		ExecuteSqlResponse response = null;
		try {
			response = databaseCrudService.executeDatabaseStatement(databaseCrudRequest);
		} catch (RequestProcessingException e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/crud/v1/query/update", produces = "application/json")
	public ResponseEntity<Object> executeSqlUpdateStatement(@RequestBody @Valid ExecuteSqlRequest databaseCrudRequest){
		ExecuteSqlResponse response = null;
		try {
			response = databaseCrudService.executeDatabaseStatement(databaseCrudRequest);
		} catch (RequestProcessingException e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	

}
