package exceptions;

public class IllegalCOmmandException extends Exception{
	public IllegalCOmmandException(String command){
		super(command + ":  illegal command");
	}
}
