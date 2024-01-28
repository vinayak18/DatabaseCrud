package com.database.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MicroserviceMappings {
	@Id
	private String servicename;
	private String url;
	private String type;
}
