import java.io.IOException;
public class Main {
	public static void main(String[] args) {
		// write your code here
		try{
			new Calculator(System.in).printCalculations();
		}
		catch (IOException | ParseError e){
			System.err.println(e.getMessage() + "\n"+e.getStackTrace()[0].getMethodName());
		}
	}
}
