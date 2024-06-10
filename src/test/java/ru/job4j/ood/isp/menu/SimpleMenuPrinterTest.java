package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMenuPrinterTest {

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
}