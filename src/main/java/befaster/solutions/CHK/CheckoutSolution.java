package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

public class CheckoutSolution {
	
	public CheckoutSolution() {
		
	}
	
    public Integer checkout(String skus) {
        if (skus instanceof String) {
        	return Integer.valueOf(0);
        } else {
        	return Integer.valueOf(-1);        	
        }
    }
}
