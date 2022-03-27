import java.util.ListResourceBundle;

/**
 * @author jiazh
 */
public class InternationalResourcePackage extends ListResourceBundle {
    private static final Object[][] resource_zh = {{ "hello", "你好" }, { "world", "世界" }};
    @Override protected Object[][] getContents() { return resource_zh; }
}