package ru.job4j.ood.isp.menu.todoapp.ui.useractions;

import ru.job4j.ood.isp.menu.ActionDelegate;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.todoapp.ui.Input;
import ru.job4j.ood.isp.menu.todoapp.ui.Output;

public class AddMenuItemRoot implements UserAction {
    private final Output out;
    private final ActionDelegate action = () -> System.out.println("> Действие корневого пункта <");

    public AddMenuItemRoot(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Добавить корневой пункт меню";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.println("=== Создание корневого пункта меню ===");
        String nameRoot = input.askStr("Введите имя корневого пункта: ");
        menu.add(null, nameRoot, action);
        out.println("=== Добавлен корневой пункт меню ===");
        return true;
    }
}
