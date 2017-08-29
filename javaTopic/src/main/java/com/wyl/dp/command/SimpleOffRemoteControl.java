package com.wyl.dp.command;

public class SimpleOffRemoteControl {
	Command slot;
	
	public SimpleOffRemoteControl(){}
	
	public void setCommand(Command command) {
		this.slot = command;
	}
	
	public void buttonWasUp() {
		slot.execute();
	}
}
