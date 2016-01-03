package chenanze.com.buanews;

/**
 * Created by duian on 15/12/22.
 */
public class Constant {
    // Fragment 枚举
    public enum FragmentType{
        CONTENT_FRAGMENT,
        ABOUT_FRAGMENT
    }

    // Intent 常量
    public static final String INTENT_CONTENT_DETAIL_WEB = "intent_content_detail_web";
    public static final String INTENT_CONTENT_DETAIL_NATIVE = "intent_content_detail_native";


    // 内容列表 item 类型
    public static final int CONTENT_LIST_ITEM_TYPE_HEADER = 0x00000;
    public static final int CONTENT_LIST_ITEM_TYPE_CELL = 0x00001;
}
