package org.iproute.other.spi;


/**
 * Created by      IDEA
 *
 * @author tech@intellij.io
 * Date    :       2018-10-11
 * Time    :       17:41
 * Version :       1.0
 * Company :       Beijing Tepia (Wuhan R&D Center)
 **/
/**
 * @author tech@intellij.io
 */
public class Client {

    public static void main(String[] args) {

        String path = Client.class.getResource("/").toString();

        System.out.println("path:" + path);

    }

}
