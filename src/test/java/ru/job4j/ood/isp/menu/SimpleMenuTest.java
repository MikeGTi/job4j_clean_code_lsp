package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Молоко", STUB_ACTION);
        menu.add("Купить продукты", "Кефир", STUB_ACTION);
        menu.add("Купить продукты", "Бананы", STUB_ACTION);

        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Хлеб", "Молоко", "Кефир", "Бананы"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        //menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));

        SimpleMenuPrinter simpleMenuPrinter = new SimpleMenuPrinter();
        simpleMenuPrinter.print(menu);
    }

    // select existing item returns correct MenuItemInfo
    @Test
    public void whenSelectExistingItemThanReturnMenuItemInfo() {
        SimpleMenu menu = new SimpleMenu();
        ActionDelegate action = () -> System.out.println("Action");
        menu.add(Menu.ROOT, "root", action);
        menu.add("root", "child", action);
        Optional<Menu.MenuItemInfo> result = menu.select("child");
        assertTrue(result.isPresent());
        assertEquals("child", result.get().getName());
    }

    // select non-existing item returns empty Optional
    @Test
    public void whenSelectNonExistingItemThanReturnOptionalEmpty() {
        SimpleMenu menu = new SimpleMenu();
        ActionDelegate action = () -> System.out.println("Action");
        menu.add(Menu.ROOT, "root", action);
        Optional<Menu.MenuItemInfo> result = menu.select("nonExisting");
        assertFalse(result.isPresent());
    }

    // select item with special characters in name
    @Test
    public void whenSelectItemWithSpecialCharactersInNameThanReturnMenuItemInfo() {
        SimpleMenu menu = new SimpleMenu();
        ActionDelegate action = () -> System.out.println("Action");
        String specialName = "item@#%&*";
        menu.add(Menu.ROOT, specialName, action);
        Optional<Menu.MenuItemInfo> result = menu.select(specialName);
        assertTrue(result.isPresent());
        assertEquals(specialName, result.get().getName());
    }
}