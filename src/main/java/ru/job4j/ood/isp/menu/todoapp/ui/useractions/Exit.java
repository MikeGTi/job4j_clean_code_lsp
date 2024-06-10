package ru.job4j.ood.isp.menu.todoapp.ui.useractions;

import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.todoapp.ui.Input;
import ru.job4j.ood.isp.menu.todoapp.ui.Output;

public class Exit implements UserAction {
    private final Output out;

    public Exit(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Завершить программу";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.println("=== Завершение программы ===");
        return false;
    }
}
