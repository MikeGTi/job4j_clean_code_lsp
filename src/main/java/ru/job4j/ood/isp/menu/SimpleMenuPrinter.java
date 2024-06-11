package ru.job4j.ood.isp.menu;


public class SimpleMenuPrinter implements MenuPrinter {

    public SimpleMenuPrinter() {
    }

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            print(menuItemInfo);
        }
    }

    @Override
    public void print(Menu.MenuItemInfo menuItemInfo) {
        String number = menuItemInfo.getNumber();
        int indentCount = getIndentCount(number);
        String rsl = number.indent(indentCount).replace('\n', ' ') + menuItemInfo.getName() + System.lineSeparator();
        System.out.printf(rsl);
    }

    private int getIndentCount(String str) {
        int symbolsCount = (int) str.chars().filter(c -> c == (int) '.').count();
        return symbolsCount * 3;
    }

}
