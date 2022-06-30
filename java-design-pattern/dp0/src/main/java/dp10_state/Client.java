package dp10_state;


/**
 * 状态模式
 *
 * @author winterfell
 */
public class Client {

    public static void main(String[] args) {

        Context context = new Context();

        // 不同的设置得到不同的内容
        context.setState(new StartState());

        System.out.println(context.getState());


        context.setState(new StopState());

        System.out.println(context.getState());

    }

}
