import java.io.IOException;
import java.io.InputStream;

public class Calculator
{
	private InputStream in;
	
	private int lookahead;
	
	public Calculator(InputStream in) throws IOException{
		this.in = in;
		lookahead = in.read();
	}
	
	private void consume(int sym) throws IOException,ParseError{
		if (lookahead == sym){
			lookahead = in.read(); //moves carret
		}
		else {
			throw new ParseError(lookahead);
		}
	}
	
	private boolean Digit(int d){
		return (d>='0' && d<='9');
	}
	
	private int EvalDigit(int d){
		return d-'0';
	}
	
	public void printCalculations() throws IOException,ParseError{
		while (lookahead != -1){
			System.out.println(calc());
		}
	}
	
	private int calc() throws IOException,ParseError{
		int val = Exp();
		if (isEOL()){ //consume end of line for next calculation
			consume(lookahead);
			//System.out.println("XD");
		}
		return val;
	}
	
	private int Exp() throws IOException,ParseError{
		boolean isDigit = Digit(lookahead);
		int cond;
		if (isDigit | lookahead == '('){
			return Exp2(Term());
		}
		else {
			throw new ParseError(lookahead);
		}
	}
	
	private boolean isEOL() throws  ParseError,IOException{
		return lookahead == '\r' | lookahead == '\n';
	}
	
	private boolean isEOF() throws ParseError, IOException{
		return lookahead == -1;
	}
	
	private int Exp2(int val) throws IOException,ParseError{
		if (lookahead == '^'){
			consume(lookahead);
			return Exp2(val^Term());
		}
		else if (lookahead == ')' | isEOL() | isEOF()){
			//if (lookahead == ')'){
			//	consume(lookahead);
			//	//isEND();
			//}
			return val;
		}
		else{
			throw new ParseError(lookahead);
		}
	}
	
	private int Term() throws IOException,ParseError{
		return Term2(Factor());
	}
	
	private int Term2(int val) throws IOException,ParseError{
		if (lookahead == '&'){
			consume(lookahead);
			return Term2(val&Factor());
		}
		else if (lookahead == ')' | isEOL() | isEOF() | lookahead == '^'){
			//int la = lookahead;
			//consume(lookahead);
			//if (lookahead == ')'){
			//consume(lookahead);
			//	//isEND();
			//}
			return val;
		}
		else{
			throw new ParseError(lookahead);
		}
	}
	
	private int Factor() throws IOException,ParseError{
		int look = lookahead;
		consume(lookahead);
		//int val = Digit(look)?EvalDigit(look):Exp();
		if (Digit(look)){
			return EvalDigit(look);
		}
		else {
			int val = Exp();
			if (lookahead==')'){
				consume(lookahead);
			}
			else {
				throw new ParseError(lookahead);
			}
			return val;
		}
	}
}

