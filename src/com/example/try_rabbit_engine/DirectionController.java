package com.example.try_rabbit_engine;

public class DirectionController{
	enum DirectionType{
		NONE, LEFT, RIGHT
	}
	
	private static DirectionType directionType = DirectionType.NONE;
	
	public static DirectionType getDirectionType(){
		return directionType;
	}
	
	public static void setDirectionType(DirectionType directionType){
		DirectionController.directionType = directionType;
	}
}
