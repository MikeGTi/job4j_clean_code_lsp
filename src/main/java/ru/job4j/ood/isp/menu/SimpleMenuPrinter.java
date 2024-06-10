package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class SimpleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> it = menu.iterator();
        while (it.hasNext()) {
            print(it.next());
        }
    }

    @Override
    public void print(Menu.MenuItemInfo menuItemInfo) {
        System.out.printf(String.format("%s %s%n",
                                        menuItemInfo.getNumber(),
                                        menuItemInfo.getName()).indent(getIndentCount(menuItemInfo.getNumber())));
    }

    private int getIndentCount(String str) {
        int indent;
        final int multiplier = 2;
        char smb = '.';
        int count = countSymbol(str, smb);
        if (count < 2) {
            indent = 0;
        } else {
            if (count % 2 != 0) {
                count += 1;
            }
            indent = count * multiplier;
        }
        return indent;
    }

    private int countSymbol(String str, char symbol) {
        return (int) str.chars().filter(c -> c == (int) symbol).count();
    }
}
