package com.database.crud.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteSqlRequest {

	private String sqlName;
	private List<String> columns;
	private Map<String, ParameterDataDetails> attributes;
	private Map<String, Object> queryParams;
}
