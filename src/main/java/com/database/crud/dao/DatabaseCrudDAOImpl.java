package com.database.crud.dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.QueryException;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.database.crud.config.SqlDataConfig;
import com.database.crud.exception.InvalidRequestException;
import com.database.crud.exception.RequestProcessingException;
import com.database.crud.model.ExecuteSqlRequest;
import com.database.crud.model.ExecuteSqlResponse;
import com.database.crud.model.ParameterDataDetails;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Component
public class DatabaseCrudDAOImpl implements DatabaseCrudDAO {
	
	@Autowired
	private EntityManager em;

	@Override
	public ExecuteSqlResponse retrieveSqlSelectStmtData(SqlDataConfig sqlConfig, ExecuteSqlRequest databaseCrudRequest) throws RequestProcessingException, InvalidRequestException {
		// TODO Auto-generated method stub
		String stmt = "SELECT ";
		Query query = null;
		try {
			if(null != sqlConfig 
					&& null != sqlConfig.getQueryString() 
					&& (!StringUtils.hasText(sqlConfig.getQueryString()) 
							|| "none".equalsIgnoreCase(sqlConfig.getQueryString()))) {
				String tableName = sqlConfig.getTableName();
				List<String> columnsList = databaseCrudRequest.getColumns();
				String columns = "";
				if(null == columnsList || columnsList.size() == 0) {
					columns = "*";
				}
				else {
					columns = columnsList.stream()
								.map(data -> new String(tableName+"."+data))
								.collect(Collectors.joining(","));
				}
				stmt = stmt + columns + " FROM " + tableName + " ";
				query = em.createNativeQuery(stmt);
				Map<String, ParameterDataDetails> attributesList = databaseCrudRequest.getAttributes();
				String clause = "";
				if(null != attributesList && attributesList.size() != 0) {
					clause = attributesList.entrySet().stream()
								.map((data) -> {
									if(!data.getValue().getOperator().toUpperCase().startsWith("NOT") || data.getValue().getOperator().toUpperCase().contains("LIKE")) {
										return new String(" "+tableName+"."+data.getKey()+" "+data.getValue().getOperator()+" :"+data.getKey());
									}
									else {
										return new String(" NOT "+tableName+"."+data.getKey()+" "+data.getValue().getOperator().substring(data.getValue().getOperator().indexOf(" ")+1)+" :"+data.getKey());
									}
								})
								.collect(Collectors.joining(" AND","WHERE",""));
					stmt = stmt + clause;
					query = em.createNativeQuery(stmt);
					for(Map.Entry<String,ParameterDataDetails> data : attributesList.entrySet()) {
						query.setParameter(data.getKey(), data.getValue().getParamValue());
					}
				}
				
			}else {
				return executeStaticSqlStatement(sqlConfig, databaseCrudRequest);
			}
			NativeQuery<Object> nativeQuery = (NativeQuery<Object>)query;
			nativeQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Object> rows = query.getResultList();
			ExecuteSqlResponse response = new ExecuteSqlResponse();
			response.setMessage("SUCCESS");
			response.setRowCount(rows.size());
			response.setRowDataList(rows);
			return response;
		}catch(QueryException e) {
			throw new InvalidRequestException("Number of params passed is not matching with the required criteria - "+e.getMessage());
		}
		catch(Exception e) {
			throw new RequestProcessingException("Error occured while processing the SQL statement: "+e.getMessage());
		}
	}

	public ExecuteSqlResponse executeStaticSqlStatement(SqlDataConfig sqlConfig, ExecuteSqlRequest databaseCrudRequest) throws InvalidRequestException, RequestProcessingException {
		try {
			if(null != sqlConfig && StringUtils.hasText(sqlConfig.getQueryString()) && !"none".equalsIgnoreCase(sqlConfig.getQueryString())) {
				Query query = em.createNativeQuery(sqlConfig.getQueryString());
				if(null != databaseCrudRequest.getQueryParams()) {
					for(Map.Entry<String, Object> obj : databaseCrudRequest.getQueryParams().entrySet()) {
						query.setParameter(obj.getKey(), obj.getValue());
					}
				}
				NativeQuery<Object> nativeQuery = (NativeQuery<Object>)query;
				nativeQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				List<Object> rows = query.getResultList();
				ExecuteSqlResponse response = new ExecuteSqlResponse();
				response.setMessage("SUCCESS");
				response.setRowCount(rows.size());
				return response;
			}
			else {
				throw new RequestProcessingException("SQL queryString is not correctly written.");
			}
		}catch(QueryException e) {
			throw new InvalidRequestException("Number of params passed is not matching with the required criteria - "+e.getMessage());
		}
		catch(Exception e) {
			throw new RequestProcessingException("Error occured while processing the SQL statement: "+e.getMessage());
		}
	}
	
	@Override
	public ExecuteSqlResponse executeDatabaseStatement(SqlDataConfig sqlConfig, ExecuteSqlRequest databaseCrudRequest) throws InvalidRequestException, RequestProcessingException {
		// TODO Auto-generated method stub
		return executeStaticSqlStatement(sqlConfig, databaseCrudRequest);
	}
}
