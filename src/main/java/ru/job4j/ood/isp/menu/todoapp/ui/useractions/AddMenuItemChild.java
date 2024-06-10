package ru.job4j.ood.isp.menu.todoapp.ui.useractions;

import ru.job4j.ood.isp.menu.ActionDelegate;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.todoapp.ui.Input;
import ru.job4j.ood.isp.menu.todoapp.ui.Output;

public class AddMenuItemChild implements UserAction {
    private final Output out;
    private final ActionDelegate action = () -> System.out.println("> Действие подпункта <");

    public AddMenuItemChild(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Добавить подпункт меню";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.println("=== Создание подпункта меню ===");
        String nameRoot = input.askStr("Введите имя корневого пункта: ");
        String nameChild = input.askStr("Введите имя нового подпункта: ");
        if (menu.select(nameRoot).isPresent() && nameChild != null) {
            menu.add(nameRoot, nameChild, action);
            out.println("=== Добавлен подпункт меню ===");
        } else {
            out.println("=== Подпункт меню не добавлен (не найден корневой пункт) ===");
        }
        return true;
    }
}
