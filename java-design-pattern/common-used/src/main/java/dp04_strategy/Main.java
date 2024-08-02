package dp04_strategy;

import dp04_strategy.stratege.AddStringAfterStrategy;
import dp04_strategy.stratege.AddStringBeforeStrategy;
import dp04_strategy.stratege.RemoveSpaceStrategy;

/**
 * @author tech@intellij.io
 */
public class Main {

    public static void main(String[] args) {

        StringContext stringContext = new StringContext(
                // 构造器传入原始策略
                new AddStringBeforeStrategy()
        );

        String oldString = "hello";

        String newString1 = stringContext.getNewString(oldString);

        // 设置新的策略
        stringContext.setStringStrategy(new AddStringAfterStrategy());
        String newString2 = stringContext.getNewString(newString1);

        stringContext.setStringStrategy(new RemoveSpaceStrategy());
        String newString3 = stringContext.getNewString(newString2);


        System.out.println("old String : " + oldString);
        System.out.println("new String 1 : " + newString1);
        System.out.println("new String 2 : " + newString2);
        System.out.println("new String 3 : " + newString3);

    }

}
