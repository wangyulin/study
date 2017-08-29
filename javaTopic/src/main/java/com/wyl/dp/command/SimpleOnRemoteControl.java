package com.wyl.dp.command;

public class SimpleOnRemoteControl {
	Command slot;
	
	public SimpleOnRemoteControl() {}
	
	public void setCommand(Command command) {
		this.slot = command;
	}
	
	public void buttonWasPressed() {
		slot.execute();
	}
	
	
}
