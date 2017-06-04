package com.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StatusRowMapper implements RowMapper<Status>{
	@Override
	public Status mapRow(ResultSet rs, int rowNum) throws SQLException {

		Status status = new Status();

		status.setId(rs.getInt("id"));
		status.setResponseCode(rs.getInt("responseCode"));
		status.setLastUpdate(rs.getDate("lastUpdate"));

		return status;
	}
}
