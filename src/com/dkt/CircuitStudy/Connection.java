package com.dkt.CircuitStudy;

public class Connection {
 protected EndPoint first,second;
 protected String name;
 public Connection(String n,EndPoint f,EndPoint s){
	 name=n;
	 first=f;
	 second=s;
 }
}
