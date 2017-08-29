package com.wyl.dp.command;

public class RemoteControlTest {

	public static void main(String[] args) {
		SimpleOnRemoteControl onRemoteControl = new SimpleOnRemoteControl();
		Light light = new Light();
		LightOnCommand lightOn = new LightOnCommand(light);
		onRemoteControl.setCommand(lightOn);
		onRemoteControl.buttonWasPressed();
		
		SimpleOffRemoteControl offRemoteControl = new SimpleOffRemoteControl();
		LightOffCommand lightOff = new LightOffCommand(light);
		offRemoteControl.setCommand(lightOff);
		offRemoteControl.buttonWasUp();
		
	}

}
