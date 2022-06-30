package org.iproute.structure.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author winterfell
 */
public class ChessFlyWeigthFactory {

    public static Map<String, ChessFlyWeight> map = new HashMap<>();

    public static ChessFlyWeight getChess(String color) {
        if (map.get(color) != null) {
            return map.get(color);
        } else {
            ChessFlyWeight cfw = new ConcreteChess(color);
            map.put(color, cfw);
            return cfw;
        }
    }

}
