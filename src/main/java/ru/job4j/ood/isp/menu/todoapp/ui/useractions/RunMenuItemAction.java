package ru.job4j.ood.isp.menu.todoapp.ui.useractions;

import ru.job4j.ood.isp.menu.ActionDelegate;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.todoapp.ui.Input;
import ru.job4j.ood.isp.menu.todoapp.ui.Output;

import java.util.Optional;

public class RunMenuItemAction implements UserAction {

    private final Output out;

    public RunMenuItemAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Запустить действие пункта";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        System.out.println("=== Запуск действия пункта меню ===");
        String nameItem = input.askStr("Введите имя пункта: ");
        Optional<Menu.MenuItemInfo> item = menu.select(nameItem);
        boolean rsl = item.isPresent();
        if (rsl) {
            item.get().getActionDelegate().delegate();
        } else {
            System.out.println("Пункт не найден.");
        }
        return true;
    }
}
