public class Test_Hopfield {

    static float[] data [] = {
            {1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, 1, -1, 1, -1, -1, -1, -1, -1, 1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -11, 1, 1, -1, 1, -1},
            {1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, 1}
    };

    static Hopfield test;

    public static void main(String[] args) {
        test = new Hopfield(13);
        test.addTrainingData(data[0]);
        test.addTrainingData(data[1]);
        test.addTrainingData(data[2]);

        test.train();
        helper(test, "wzor 0", data[0]);
        helper(test, "wzor 1", data[1]);
        helper(test, "wzor 2", data[2]);

    }

    private static void helper(Hopfield test, String s, float[] test_data) {
        float[] dd = new float[13];
        for (int i = 0; i < 13; i++) {
            dd[i] = test_data[i];
        }

        int NUM_BITS_TO_FLIP = 1;
        for (int i_scramble=0; i_scramble<NUM_BITS_TO_FLIP; i_scramble++) {
            int index = (int) (12.0f * (float) Math.random());
            dd[index] = -dd[index];
        }

        float[] rr = test.recall(dd, 15);
        System.out.print(s+"\nOryginalne dane:\t");
        for (int i = 0; i < 13; i++) System.out.print(pp(test_data[i]) + " ");
        System.out.print("\nLosowe dane:\t");
        for (int i = 0; i < 13; i++) System.out.print(pp(dd[i]) + " ");
        System.out.print("\nRozpoznany wzor:\t");
        for (int i = 0; i < 13; i++) System.out.print(pp(rr[i]) + " ");
        System.out.println();
    }

    private static int pp(float x) {
        if (x > 0.1f) return 1;
        return 0;
    }
}