package com.example.try_rabbit_engine;

public interface IAddAndRemoveEvent {
	void addRabbitToData();
	void addBellToData();
	void addBirdToData();
	
	void removeRabbit();
	void removeBell();
	void removeBird();
}
