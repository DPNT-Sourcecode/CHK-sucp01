package befaster.solutions.HLO;

import befaster.runner.SolutionNotImplementedException;

public class HelloSolution {
	
	public HelloSolution() {
		
	}
	
    public String hello(String friendName) {
    	//Using StringBuilder to make execution consistent across JVMs
    	StringBuilder sb = new StringBuilder();
    	sb.append("Hello, ");
    	sb.append(friendName);
    	sb.append("!");
    	return sb.toString();

    }
}
