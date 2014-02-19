package com.dkt.CircuitStudy;

 public class EndPoint {
 protected Component component;
 public enum Polarity{
	 POSITIVE,NEGATIVE,NEUTRAL
	 
 }
  protected Polarity polarity= Polarity.NEUTRAL;
  protected String name;
  public EndPoint(String n,Polarity p ,Component c){
	  polarity=p;
	  name=n;
	  component=c;
	  
  }
  public EndPoint(String n ,Component c){
	  name=n;
	  polarity=Polarity.NEUTRAL;
	  component=c;
  }
}
