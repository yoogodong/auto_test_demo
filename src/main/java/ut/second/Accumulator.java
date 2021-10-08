package ut.second;

public class Accumulator {

    public int accumulate(int input) throws NumberOutOfBoundException {
        if (input > 1000 || input < 0)
            throw new NumberOutOfBoundException("输入参数的范围必须在[0,1000]范围之内");
        int sum = 0;
        for (int i = 1; i <= input; i++) {
            sum += i;
        }
        return sum;
    }

    public String accumulate(String input) throws NumberOutOfBoundException {
        final Integer value = Integer.valueOf(input);

        final int accumulate = accumulate(value);

        return  String.valueOf(accumulate);
    }

}