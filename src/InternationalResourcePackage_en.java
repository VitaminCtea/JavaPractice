import java.util.ListResourceBundle;

/**
 * @author jiazh
 */
public class InternationalResourcePackage_en extends ListResourceBundle {
    private static final Object[][] resource_en = {{ "hello", "hello"}, { "world", "world" }};
    @Override protected Object[][] getContents() { return resource_en; }
}