package exceptions;

public class IllegalCommandException extends Exception {
	public IllegalCommandException(String command){
		super(command + ":  illegal command");
	}
}  
