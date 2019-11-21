package common;

import java.util.List;

public class NestedInteger implements NestedObject {

    private Integer integer;

    public NestedInteger(Integer integer) {
        this.integer = integer;
    }

    @Override
    public boolean isInteger() {
        return true;
    }

    @Override
    public Integer getInteger() {
        return integer;
    }

    @Override
    public List<NestedObject> getList() {
        throw new UnsupportedOperationException();
    }
}
