package ru.job4j.ood.isp.menu.todoapp;

import ru.job4j.ood.isp.menu.*;
import ru.job4j.ood.isp.menu.todoapp.ui.*;
import ru.job4j.ood.isp.menu.todoapp.ui.useractions.*;

import java.util.List;

/**
 * 6. Создайте класс TodoApp - консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {
    private final Output out;

    public TodoApp(Output out) {
        this.out = out;
    }

    public void init(Input input, SimpleMenu menu, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            showUserMenu(actions);
            int select = input.askInt("Выбрать: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Неверный ввод, вы можете выбрать: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, menu);
            new SimpleMenuPrinter().print(menu);
        }
    }

    private void showUserMenu(List<UserAction> actions) {
        System.out.println("Выберите:");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ". " + actions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        List<UserAction> actions = List.of(
                                            new AddMenuItemRoot(output),
                                            new AddMenuItemChild(output),
                                            new RunMenuItemAction(output),
                                            new Exit(output)
        );
        SimpleMenu menu = new SimpleMenu();
        new TodoApp(output).init(input, menu, actions);
    }
}
