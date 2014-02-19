package com.dkt.CircuitStudy;

public interface DBInterface {
boolean writeToDb(java.sql.Connection conn);
boolean readFromDb(java.sql.Connection conn);
}
