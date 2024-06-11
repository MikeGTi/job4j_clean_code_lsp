package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SimpleMenuPrinterTest {

    @AfterAll
    static void setOutToSystem() {
        System.setOut(System.out);
    }

    @Test
    void whenPrintRootElementThanNoneTab() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SimpleMenu menu = new SimpleMenu();
        ActionDelegate action = () -> System.out.println("Action");
        String name = "Root";
        menu.add(Menu.ROOT, name, action);

        SimpleMenuPrinter printer = new SimpleMenuPrinter();
        printer.print(menu);

        final String result = out.toString();
        assertFalse(result.startsWith("    "));
    }

    @Test
    void whenPrintSecondRootElementThanNoneTab() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SimpleMenu menu = new SimpleMenu();
        String name1 = "Root1";
        ActionDelegate action1 = () -> System.out.println("Action1");
        menu.add(Menu.ROOT, name1, action1);

        String name2 = "Root2";
        ActionDelegate action2 = () -> System.out.println("Action2");
        menu.add(Menu.ROOT, name2, action2);

        SimpleMenuPrinter printer = new SimpleMenuPrinter();
        printer.print(menu);

        final String result = out.toString();
        assertFalse(result.startsWith("    "));
        assertFalse(result.split("\n")[1].startsWith("    "));
    }

    @Test
    void whenPrintChildElementThanTab() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SimpleMenu menu = new SimpleMenu();
        String name1 = "Root";
        ActionDelegate action1 = () -> System.out.println("Action1");
        menu.add(Menu.ROOT, name1, action1);

        String name2 = "Child";
        ActionDelegate action2 = () -> System.out.println("Action2");
        menu.add(name1, name2, action2);

        SimpleMenuPrinter printer = new SimpleMenuPrinter();
        printer.print(menu);

        final String result = out.toString();
        assertFalse(result.startsWith("    "));
        assertTrue(result.split("\n")[1].startsWith("    "));
    }

    @Test
    void whenPrintMenuThanOk() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SimpleMenu menu = new SimpleMenu();
        String name1 = "Root";
        ActionDelegate action1 = () -> System.out.println("Action1");
        menu.add(Menu.ROOT, name1, action1);

        String name2 = "Child";
        ActionDelegate action2 = () -> System.out.println("Action2");
        menu.add(name1, name2, action2);

        SimpleMenuPrinter printer = new SimpleMenuPrinter();
        printer.print(menu);

        final String result = out.toString();
        assertFalse(result.startsWith("    "));
        assertTrue(result.split("\n")[1].startsWith("    "));
    }

    @Test
    public void whenAddThenPrintReturnSame() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String separator = System.lineSeparator();
        String expected = "   1. Сходить в магазин" + separator
                        + "      1.1. Купить продукты" + separator
                        + "         1.1.1. Хлеб" + separator
                        + "         1.1.2. Молоко" + separator
                        + "         1.1.3. Кефир" + separator
                        + "         1.1.4. Бананы" + separator
                        + "   2. Покормить собаку" + separator;

        final ActionDelegate STUB_ACTION = System.out::println;
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Молоко", STUB_ACTION);
        menu.add("Купить продукты", "Кефир", STUB_ACTION);
        menu.add("Купить продукты", "Бананы", STUB_ACTION);

        SimpleMenuPrinter simpleMenuPrinter = new SimpleMenuPrinter();
        simpleMenuPrinter.print(menu);
        assertThat(out.toString()).isEqualTo(expected);
    }
}