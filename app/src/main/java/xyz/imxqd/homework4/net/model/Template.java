package xyz.imxqd.homework4.net.model;

import java.util.List;

public class Template {

    public static final String TYPE_RECOMMEND = "P_N_396";
    public static final String TYPE_ON_SALE = "P_FLASH_SALE";
    public static final String TYPE_LARGE_IMAGE = "P_BN";
    public static final String TYPE_ENTRY = "P_KINGKONG";
    public static final String TYPE_BANNER = "P_BANNER";

    public String templateType;
    public String titleType;
    public String userStatus;
    public int columnNum;
    public String titleText;
    public long orderBy;
    public long productListId;
    public long id;
    public List<Item> items;

    public static class Item {
        public String userStatus;
        public String itemTitle;
        public int replacePosition;
        public String itemImage;
        public boolean onlyShowImg;
        public String itemUrl;
        public String itemPrice;
    }
}
