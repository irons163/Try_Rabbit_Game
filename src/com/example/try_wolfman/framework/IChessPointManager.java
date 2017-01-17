package com.example.try_wolfman.framework;

import java.util.List;

import com.example.try_wolfman.framework.IChessPoint;

public interface IChessPointManager {
//	IChessPoint createChessPointRed();
//	IChessPoint createChessPointYellow();
//	IChessPoint createChessPointWhite();
	
	List<String> getUseableChessPointList();
	
	IChessPoint createChessPonitRamdon();
}
