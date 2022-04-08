public class ParseError extends Exception
{
	int lookahead;
	public ParseError(int l){
		this.lookahead = l;
	}
	public String getMessage(){
		return "parse error\nlookahead:"+ (char) lookahead;
	}
}

