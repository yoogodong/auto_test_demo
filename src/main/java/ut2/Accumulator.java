package ut2;

/**
 * 1，分析应该有几个测试用例
 * 2，首先要保证逻辑分支的覆盖， 然后要考虑边界值
 * 3，是否该测试所有的方法？
 * 4，是否该测试私有方法？
 * 5，在IDE中获得测试覆盖率
 * 6，在命令行通过 mvn jacoco:report 获得测试覆盖率（已经在 pom 中配好 jacoco-plugin)
 * 7，测试覆盖率的几种指标：行、分支、方法、类、指令
 */
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