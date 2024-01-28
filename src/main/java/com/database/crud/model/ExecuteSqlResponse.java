package com.database.crud.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteSqlResponse {
	private String message;
	private int rowCount;
	private List<Object> rowDataList;
}
