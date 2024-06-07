package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        /* добавил реализацию */
        SimpleMenuItem item = new SimpleMenuItem(childName, actionDelegate);
        Optional<ItemInfo> parentElement = findItem(parentName);
        parentElement.map(itemInfo -> itemInfo.menuItem.getChildren().add(item))
                     .orElseGet(() -> this.rootElements.add(item));
        return true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        /* добавил реализацию */
        Optional<MenuItemInfo> rsl = Optional.empty();
        Optional<ItemInfo> itemElement = findItem(itemName);
        if (itemElement.isPresent()) {
            rsl = Optional.of(new MenuItemInfo(itemElement.get().menuItem, itemElement.get().number));
        }
        return rsl;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        /* добавить реализацию */
        return new Iterator<>() {
            private DFSIterator dfsIterator = new DFSIterator();
            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                SimpleMenu.ItemInfo itemElement = dfsIterator.next();
                return new MenuItemInfo(itemElement.menuItem, itemElement.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        /* добавил реализацию */
        Optional<ItemInfo> rsl = Optional.empty();
        if (name != null) {
            DFSIterator it = new DFSIterator();
            while (it.hasNext()) {
                ItemInfo itm = it.next();
                if (name.equals(itm.menuItem.getName())) {
                    rsl = Optional.of(itm);
                    break;
                }
            }
        }
        return rsl;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            var current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}