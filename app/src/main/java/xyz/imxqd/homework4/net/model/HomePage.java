package xyz.imxqd.homework4.net.model;

import java.util.List;

public class HomePage {
    public boolean hasNext;
    public boolean hotSaleBegin;
    public List<Template> list;
    public HeadData headData;

    public static class HeadData {
        public String hotSearchKey;
    }
}
