public class main_code {

	public static final int MAX_CLUSTERS = 2;
	public static final int VECTORS = 4;
	public static final int VEC_LEN = 4;
	public static final double DECAY_RATE = 0.96;
	public static final double MIN_ALPHA = 0.01;

	//dane wzorcowe
	public static final int Pattern[][] = new int[][] {{1, 1, 0, 0},
	                                                   {0, 0, 0, 1},
	                                                   {1, 0, 0, 0},
	                                                   {0, 0, 1, 1}};

	//dane testowe                                              
	public static final int Tests[][] = new int[][] {{1, 0, 0, 1},
	                                              	 {0, 1, 1, 0},
	                                              	 {1, 0, 1, 0},
	                                              	 {0, 1, 0, 1}};
	
	public static void main(String[] args) {
		double Alpha = 0.6;
		
		SOM_Class1 som = new SOM_Class1(VECTORS, MAX_CLUSTERS, Alpha, MIN_ALPHA, DECAY_RATE, VEC_LEN);
		som.Train(Pattern, Tests);
		som.Test(Pattern, Tests);
		
		System.out.println("Iterations: " + som.Iterations() + "\n");
		
		return;
	}

}