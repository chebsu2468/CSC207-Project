package Classes.Settings;

import javax.swing.*;
import java.util.*;

public class StrategyMap {
    private final Map<Class<?>, UIStyleStrategy> strategies = new HashMap<>();

    //maps the strategy for different UI component with strategy interface
    public StrategyMap() {
        strategies.put(JScrollPane.class, new ScrollPaneStrategy());
    }

    public UIStyleStrategy getStrategy(Class<?> compClass) {
        UIStyleStrategy s = strategies.get(compClass);
        if (s != null) {
            return s;
        } else {
            return new DefaultUIStrategy();
        }

    }
}
