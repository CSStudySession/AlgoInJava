package common;

import java.util.List;

public class NestedList implements NestedObject {

    List<NestedObject> list;

    public NestedList(List<NestedObject> list) {
        this.list = list;
    }

    @Override
    public boolean isInteger() {
        return false;
    }

    @Override
    public Integer getInteger() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<NestedObject> getList() {
        return list;
    }
}
