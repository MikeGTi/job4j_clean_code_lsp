package ru.job4j.ood.isp.menu.todoapp.ui.useractions;

import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.todoapp.ui.Input;

public interface UserAction {
    String name();

    boolean execute(Input input, Menu menu);
}
