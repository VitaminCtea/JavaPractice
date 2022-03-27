import gbc.GBC;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Node;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import randomAccess.DataIO;
import randomAccess.Employee;
import sun.misc.Unsafe;

import javax.imageio.ImageIO;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.awt.print.Paper;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.annotation.*;
import java.lang.reflect.Array;
import java.lang.reflect.Proxy;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.function.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

enum CharCode {
    MIN_CHAR_CODE(65), MAX_CHAR_CODE(122), MIN_SPECIAL_CHAR_CODE(90), MAX_SPECIAL_CHAR_CODE(97);
    private final int code;

    CharCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
@interface BankTransferMoney {
    double maximumTransferAmount() default 10000;
}

interface ReflexMethodsStreamInterface<T, U> {
    U getReflexMethodStream(T cls);
}

interface MethodNameEqualInterface<T, U, R> {
    R isMethodNameEqual(T method, U methodName);
}

interface ParametersTypeInterface<T, U> {
    U getMethodParametersType(T methodName);
}

interface ThrowNoSuchMethodErrorInterface<T> {
    void createNoSuchMethodError(T message) throws NoSuchMethodException;
}

class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    }

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> acc.get(isPrime(acc.get(true), candidate)).add(candidate);
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public boolean isPrime(List<Integer> primes, Integer candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return primes.stream().takeWhile(i -> i <= candidateRoot).noneMatch(i -> candidate % i == 0);
    }
}

enum MaskList {
    CHAR_SEQUENCE(1), STRING(2), ARRAY_STRING(4), LIST(8), INT_ARRAY(16);
    public final int MASK;

    MaskList(int mask) {
        this.MASK = mask;
    }
}

record WordCounter(int counter, boolean isWhitespace) {

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return isWhitespace ? this : new WordCounter(counter, true);
        }
        return isWhitespace ? new WordCounter(counter + 1, false) : this;
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.isWhitespace);
    }

    public int getCounter() {
        return counter;
    }
}

class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentSplitPos = 0;

    public WordCounterSpliterator(String string) { this.string = string; }

    @Override public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentSplitPos++));
        return currentSplitPos < string.length();
    }

    @Override public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentSplitPos;
        if (currentSize < 10) {
            return null;
        }
        for (int splitPos = currentSize / 2 + currentSplitPos; splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) {
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentSplitPos, splitPos));
                currentSplitPos = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override public long estimateSize() {
        return string.length() - currentSplitPos;
    }

    @Override public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}

class Stock {   // 股票
    private String symbol;
    private String market;

    public void setSymbol(String symbol) { this.symbol = symbol; }
    public void setMarket(String market) {
        this.market = market;
    }
}

class Trade {   // 交易
    public enum Type {BUY, SELL}

    private Type type;
    private Stock stock;
    private int quantity;
    private double price;

    public Type getType() { return type; }

    public void setType(Type type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public double getValue() {
        return quantity * price;
    }
}

class Order {   // 订单
    private String customer;
    private final List<Trade> trades = new ArrayList<>();

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getValue() {
        return String.format("%.2f", trades.stream().mapToDouble(Trade::getValue).sum());
    }

    public List<Trade> getTrades() {
        return trades;
    }
}

class TradeBuilder {
    private final Trade trade = new Trade();

    public TradeBuilder quantity(int quantity) {
        trade.setQuantity(quantity);
        return this;
    }

    public TradeBuilder price(double price) {
        trade.setPrice(price);
        return this;
    }

    public StockBuilder stock(String symbol) {
        return new StockBuilder(this, trade, symbol);
    }

    public Trade getTrade() {
        return trade;
    }
}

class StockBuilder {
    private final TradeBuilder builder;
    private final Trade trade;
    private final Stock stock = new Stock();

    public StockBuilder(TradeBuilder builder, Trade trade, String symbol) {
        this.builder = builder;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilder on(String market) {
        stock.setMarket(market);
        trade.setStock(stock);
        return builder;
    }
}

class MixedBuilder {
    public static Order forCustomer(String customer, TradeBuilder... builders) {
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(builders).forEach(b -> order.addTrade(b.getTrade()));
        return order;
    }

    public static TradeBuilder buy(Consumer<TradeBuilder> consumer) {
        return buildTrade(consumer, Trade.Type.BUY);
    }

    public static TradeBuilder sell(Consumer<TradeBuilder> consumer) {
        return buildTrade(consumer, Trade.Type.SELL);
    }

    private static TradeBuilder buildTrade(Consumer<TradeBuilder> consumer, Trade.Type buy) {
        TradeBuilder builder = new TradeBuilder();
        builder.getTrade().setType(buy);
        consumer.accept(builder);
        return builder;
    }
}

class NewPermission {
    private int flag;
    public static final int ALLOW_SELECT = 1; // 0001
    public static final int ALLOW_INSERT = 1 << 1; // 0010
    public static final int ALLOW_UPDATE = 1 << 2; // 0100
    public static final int ALLOW_DELETE = 1 << 3; // 1000

    public void setPermission(int permission) {
        flag = permission;
    }

    public void enable(int permission) {
        flag |= permission;
    }

    public void disable(int permission) {
        flag &= ~permission;
    }

    public boolean isAllow(int permission) {
        return (flag & permission) == permission;
    }

    public boolean isNotAllow(int permission) {
        return (flag & permission) == 0;
    }

    public boolean isOnlyAllow(int permission) {
        return flag == permission;
    }

    public void resetPermission() {
        flag &= ~flag;
    }

    public int getFlag() {
        return this.flag;
    }
}

class ViewDBFrame extends JFrame {
    private DataPanel dataPanel;
    private Component scrollPane;
    private final JComboBox<String> tableNames;
    private Properties props;
    private CachedRowSet cachedRowSet;
    private Connection connection;

    public ViewDBFrame() {
        tableNames = new JComboBox<>();
        try {
            readDatabaseProperties();
            connection = getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            // 返回一个包含所有数据库表信息的结果集，该结果集中的每一行都包含了数据库中一张表的详细信息，其中，第三列是表的名称。
            /*
             * getTables(String catalog, String schemaPattern, String tableNamePattern, String types[])
             * 返回某个目录(catalog)中所有表的描述，该目录必须匹配给定的模式(schema)、表名字模式以及类型标准。
             * (模式用于描述一组相关的表和访问权限，而目录描述的是一组相关的模式，这些概念对组织大型数据库非常重要。)
             *
             * catalog和schema参数可以为""，用于检索那些没有目录或模式的表。如果不想考虑目录和模式，也可以将上述参数设为null。
             * types数组包含了所需的表类型的名称，通常表类型有TABLE、VIEW、SYSTEM TABLE、GLOBAL TEMPORARY、LOCAL TEMPORARY、ALIAS和SYNONYM。
             * 如果types为null，则返回所有类型的表。
             *
             * 返回的结果集共有5列，均为String类型。
             *
             * 行                    名称                    解释
             * 1                   TABLE_CAT          表目录(可以为null)
             * 2                   TABLE_SCHEM        表模式(可以为null)
             * 3                   TABLE_NAME               表名称
             * 4                   TABLE_TYPE               表类型
             * 5                     REMARKS              关于表的注释*/
            try (ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"})) {
                while (resultSet.next()) {
                    tableNames.addItem(resultSet.getString(3)/* 获取表名 */);
                }
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }

        tableNames.addActionListener(event -> showTable((String) tableNames.getSelectedItem(), connection));
        add(tableNames, BorderLayout.NORTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    for (Throwable t : ex) {
                        t.printStackTrace();
                    }
                }
            }
        });

        Function<JPanel, BiFunction<String, Runnable, ?>> createBaseButton = panel -> (text, func) -> {
            JButton jButton = new JButton(text);
            jButton.addActionListener(event -> func.run());
            panel.add(jButton);
            return null;
        };

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        BiFunction<String, Runnable, ?> createButton = createBaseButton.apply(buttonPanel);

        createButton.apply("Previous", this::showPreviousRow);
        createButton.apply("Next", this::showNextRow);
        createButton.apply("Delete", this::deleteRow);
        createButton.apply("Save", this::saveChanges);

        if (tableNames.getItemCount() > 0) {
            showTable(tableNames.getItemAt(0), connection);
        }
    }

    public static void init() {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ViewDBFrame();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            frame.setSize(600, 400);
            frame.setLocation(
                    (int) (toolkit.getScreenSize().getWidth() - frame.getWidth()) / 2,
                    (int) (toolkit.getScreenSize().getHeight() - frame.getHeight()) / 2
            );

            frame.setTitle("ViewDB");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public void showTable(String tableName, Connection connection) {
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();  // 创建一个行集工厂
            cachedRowSet = rowSetFactory.createCachedRowSet();  // 创建带有缓存的行集，此行集不必一直连接数据库，等到需要重新写回数据库的时候会自动连接。
            // 如果是使用结果集来填充行集，那么行集就无从获知需要更新数据的数据库表名。此时，必须调用SetTable方法来设置表名称。
            // 也就是说，一个数据库中如果存在多张表，并且利用行集进行修改某一个表时，必须加以标识，用来表示修改的是哪张表的数据。
            cachedRowSet.setTableName(tableName);
            cachedRowSet.populate(resultSet);   // 将指定的结果集中的数据填充到被缓存的行集中。
            if (scrollPane != null) {
                remove(scrollPane);
            }
            dataPanel = new DataPanel(cachedRowSet);
            scrollPane = new JScrollPane(dataPanel);
            add(scrollPane, BorderLayout.CENTER);
            pack();
            showNextRow();
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
    }

    public void showPreviousRow() {
        baseShowRow(false);
    }

    public void showNextRow() {
        baseShowRow(true);
    }

    private void baseShowRow(boolean isNextRow) {
        try {
            if (cachedRowSet == null || (isNextRow ? Objects.requireNonNull(cachedRowSet).isLast() : cachedRowSet.isFirst())) {
                return;
            }
            if (isNextRow) {
                cachedRowSet.next();
            } else {
                cachedRowSet.previous();
            }
            dataPanel.showRow(cachedRowSet);
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
    }

    public void deleteRow() {
        if (cachedRowSet == null) {
            return;
        }
        new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() throws SQLException {
                connection.setAutoCommit(false);
                cachedRowSet.deleteRow();
                // 重新连接数据库，并写回行集中修改过的数据。如果因为数据库中的数据已经被修改而导致无法写回行集中的数据，该方法可能会抛出SyncProviderException异常。
                cachedRowSet.acceptChanges(connection);
                if (cachedRowSet.isAfterLast() && !cachedRowSet.last()) {
                    cachedRowSet = null;
                }
                return null;
            }

            @Override
            public void done() {
                dataPanel.showRow(cachedRowSet);
            }
        }.execute();
    }

    public void saveChanges() {
        if (cachedRowSet == null) {
            return;
        }
        new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() throws SQLException {
                dataPanel.setRow(cachedRowSet);
                cachedRowSet.acceptChanges(connection);
                return null;
            }
        }.execute();
    }

    private void readDatabaseProperties() throws IOException {
        props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            props.load(in);
        }
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
    }

    private Connection getConnection() throws SQLException {
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        /*
         * 在Web或企业环境中部署JDBC应用时，数据库连接管理与Java名字和目录接口(JNDI)是集成在一起的。
         * 遍布企业的数据源的属性可以储存在一个目录中，采用这种方式使得我们可以集中管理用户名、密码、数据库名和JDBC URL。
         * 在这样的环境中，可以使用下列代码创建数据库连接:

         * var jndiContext = new InitialContext();
         * var source = (DataSource) jndiContext.lookup("java:comp/env/jdbc/coreJava");
         * Connection conn = source.getConnection();
         * 注意的是，不再=使用DriverManager，而是使用JNDI服务来定位数据源，而是使用JNDI服务来定位数据源。
         */
        return DriverManager.getConnection(url, username, password);
    }
}

class DataPanel extends JPanel {
    private final List<JTextField> fields;

    public DataPanel(CachedRowSet cachedRowSet) throws SQLException {
        fields = new ArrayList<>();
        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;

        Insets insets = new Insets(10, 10, 10, 10);
        Insets defaultInsets = new Insets(0, 0, 0, 0);

        // ResultSetMetaData接口用于提供结果集的相关信息。每当通过查询得到一个结果集时，都可以获取该结果集的列数以及每一列的名称、类型和字段宽度。
        // 注意区分上面的DatabaseMetaData接口，ResultSetMetaData接口用来提供结果集的信息；DatabaseMetaData用来提供数据库的信息！！
        ResultSetMetaData resultSetMetaData = cachedRowSet.getMetaData();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = i - 1;
            gridBagConstraints.anchor = GridBagConstraints.EAST;
            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.insets = defaultInsets;

            JLabel jLabel = new JLabel(resultSetMetaData.getColumnLabel(i));
            jLabel.setFont(new Font(null, Font.PLAIN, 16));
            add(jLabel, gridBagConstraints);

            JTextField tb = new JTextField(resultSetMetaData.getColumnDisplaySize(i));
            tb.setPreferredSize(new Dimension(0, 30));
            if (!Objects.equals(resultSetMetaData.getColumnClassName(i), "java.lang.String")) {
                tb.setEditable(false);
            }
            fields.add(tb);

            gridBagConstraints.gridx = 1;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.anchor = GridBagConstraints.WEST;
            gridBagConstraints.insets = insets;
            add(tb, gridBagConstraints);
        }
    }

    public void showRow(RowSet rs) {
        if (rs == null) {
            return;
        }
        traverse(i -> {
            try {
                fields.get(i - 1).setText(rs.getString(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void setRow(RowSet rs) throws SQLException {
        traverse(i -> {
            JTextField tb = fields.get(i - 1);
            String text = tb.getText();
            try {
                if (!rs.getString(i).equals(text)) {
                    rs.updateString(i, text);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        rs.updateRow();
    }

    private void traverse(Consumer<Integer> callback) {
        for (int i = 1; i <= fields.size(); i++) {
            callback.accept(i);
        }
    }
}

class NumberFormatFrame extends JFrame {
    interface RadioButtonInterface<A, B, C, D> {
        D run(A a, B b, C c);
    }

    private double currentNumber = 123456.78;
    ;
    private final JComboBox<String> localeComboBox = new JComboBox<>();
    private final JTextField numberText = new JTextField(30);
    private final JRadioButton numberRadioButton = createJRadioButton("Number");
    private final JRadioButton currencyRadioButton = createJRadioButton("Currency");
    private final JRadioButton percentRadioButton = createJRadioButton("Percent");
    private final Locale[] locales;
    private NumberFormat currentNumberFormat;

    public NumberFormatFrame() {
        setLayout(new GridBagLayout());

        JPanel jPanel = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        ActionListener listener = event -> updateDisplay();

        RadioButtonInterface<JPanel, ButtonGroup, ActionListener, Consumer<JRadioButton>> _addRadioButton =
                (container, bGroup, actionListener) ->
                        jRadioButton -> {
                            jRadioButton.setSelected(bGroup.getButtonCount() == 0);
                            jRadioButton.addActionListener(actionListener);
                            bGroup.add(jRadioButton);
                            container.add(jRadioButton);
                        };

        Consumer<JRadioButton> addRadioButton = _addRadioButton.run(jPanel, buttonGroup, listener);

        addRadioButton.accept(numberRadioButton);
        addRadioButton.accept(currencyRadioButton);
        addRadioButton.accept(percentRadioButton);

        add(new JLabel("Locale: "), new GBC(0, 0).setAnchor(GBC.EAST));
        add(jPanel, new GBC(1, 1));

        JButton parseButton = new JButton("Parse");
        parseButton.addActionListener(event -> {
            try {
                currentNumber = currentNumberFormat.parse(numberText.getText().trim()).doubleValue();
                updateDisplay();
            } catch (ParseException e) {
                numberText.setText(e.getMessage());
            }
        });
        add(parseButton, new GBC(0, 2).setInsets(2)/* setInsets设置外边距 */);

        add(localeComboBox, new GBC(1, 0).setAnchor(GBC.WEST));
        add(numberText, new GBC(1, 2).setFill(GBC.HORIZONTAL));

        locales = NumberFormat.getAvailableLocales().clone();

        Arrays.sort(locales, Comparator.comparing(Locale::getLanguage));
        for (Locale locale : locales) {
            localeComboBox.addItem(locale.getDisplayName());
        }

        localeComboBox.setSelectedItem(Locale.getDefault().getDisplayName());

        updateDisplay();

        localeComboBox.addActionListener(listener);

        pack();
    }

    public static void init() {
        EventQueue.invokeLater(() -> {
            NumberFormatFrame numberFormatFrame = new NumberFormatFrame();
            numberFormatFrame.setTitle("NumberFormatTest");
            numberFormatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            numberFormatFrame.setLocation(
                    (int) (toolkit.getScreenSize().getWidth() - numberFormatFrame.getWidth()) / 2,
                    (int) (toolkit.getScreenSize().getHeight() - numberFormatFrame.getHeight()) / 2
            );
            numberFormatFrame.setVisible(true);
        });
    }

    private void updateDisplay() {
        Locale currentLocale = locales[localeComboBox.getSelectedIndex()];
        currentNumberFormat = null;

        if (numberRadioButton.isSelected()) {
            currentNumberFormat = NumberFormat.getNumberInstance(currentLocale);
        } else if (currencyRadioButton.isSelected()) {
            currentNumberFormat = NumberFormat.getCurrencyInstance(currentLocale);
        } else if (percentRadioButton.isSelected()) {
            currentNumberFormat = NumberFormat.getPercentInstance(currentLocale);
        }

        numberText.setText(currentNumberFormat.format(currentNumber));
    }

    private JRadioButton createJRadioButton(String text) {
        return new JRadioButton(text);
    }
}

interface MySubscriber<T> { void publish(T item); }
interface MyPublisher<T> { void subscribe(MySubscriber<? super T> subscriber); }
class SimpleCell implements MyPublisher<Integer>, MySubscriber<Integer> {
    private int value = 0;
    private final String name;
    protected final List<MySubscriber> subscribers = new ArrayList<>();

    public SimpleCell(String name) { this.name = name; }

    @Override
    public void subscribe(MySubscriber<? super Integer> subscriber) { subscribers.add(subscriber); }

    @Override
    public void publish(Integer newValue) {
        this.value = newValue;
        System.out.println(this.name + " : " + this.value);
        notifyAllSubscribers();
    }

    public void notifyAllSubscribers() { subscribers.forEach(subscriber -> subscriber.publish(this.value)); }
}

class ArithmeticCell extends SimpleCell {
    private static int left = 0;
    private static int right = 0;

    public ArithmeticCell(String name) { super(name); }
    public void setLeft(int val) { setBase(val, "left"); }
    public void setRight(int val) { setBase(val, "right"); }
    public boolean isEqual(String a, String b) { return a.equals(b); }

    public void setBase(int val, String name) {
        try {
            Class<?> cls = Class.forName(this.getClass().getName());
            Field[] fields = cls.getDeclaredFields();
            Field field = Arrays.stream(fields).filter(f -> isEqual(f.getName(), name)).findFirst().orElseThrow();
            Field otherField = fields[!isEqual(name, "left") ? 0 : 1];

            if (!Modifier.isStatic(field.getModifiers())) {
                throw new NoSuchFieldException(name + "成员属性不是静态的，请检查！");
            }
            if (Arrays.stream(fields).noneMatch(f -> f.canAccess(null))) {
                AccessibleObject.setAccessible(fields, true);
            }

            field.setInt(cls, val);
            publish(val + otherField.getInt(cls));
        } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) { e.printStackTrace(); }
    }
}

interface ThreadPool {
    void execute(Runnable runnable);
    void shutdown();
    int getInitSize();
    int getMaxSize();
    int getCoreSize();
    int getQueueSize();
    int getActiveCount();
    boolean isShutdown();
}

interface RunnableQueue {
    void offer(Runnable runnable);
    Runnable take() throws InterruptedException;
    int size();
}

@FunctionalInterface
interface ThreadFactory { Thread createThread(Runnable runnable); }

@FunctionalInterface
interface DenyPolicy { void reject(Runnable runnable, ThreadPool threadPool); }

class StrategyDenyPolicy {
    public static class DiscardDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {}
    }

    public static class AbortDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) { throw new RunnableDenyException("The runnable " + runnable + " will be abort."); }
    }

    public static class RunnerDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }
}

class RunnableDenyException extends RuntimeException { public RunnableDenyException(String message) { super(message); } }

class InternalTask implements Runnable {
    private final RunnableQueue runnableQueue;
    private volatile boolean running = true;
    public InternalTask(RunnableQueue runnableQueue) { this.runnableQueue = runnableQueue; }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                runnableQueue.take().run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() { this.running = false; }
}

class LinkedRunnableQueue implements RunnableQueue {
    private final int maxCapacity;
    private final DenyPolicy denyPolicy;
    private final LinkedList<Runnable> runnableLinkedList = new LinkedList<>();
    private final ThreadPool threadPool;
    public LinkedRunnableQueue(int maxCapacity, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.maxCapacity = maxCapacity;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableLinkedList) {
            if (runnableLinkedList.size() >= maxCapacity) {
                denyPolicy.reject(runnable, threadPool);
            } else {
                runnableLinkedList.addLast(runnable);
                runnableLinkedList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableLinkedList) {
            while (runnableLinkedList.isEmpty()) {
                runnableLinkedList.wait();
            }
            return runnableLinkedList.removeFirst();
        }
    }

    @Override
    public int size() { synchronized (runnableLinkedList) { return runnableLinkedList.size(); } }
}

class BasicThreadPool extends Thread implements ThreadPool {
    private final int initSize;
    private final int maxSize;
    private final int coreSize;
    private int activeCount;
    private final ThreadFactory threadFactory;
    private final RunnableQueue runnableQueue;
    private volatile boolean isShutdown = false;
    private final Queue<ThreadTask> threadTaskQueue = new ArrayDeque<>();
    private final static DenyPolicy DEFAULT_DENY_POLICY = new StrategyDenyPolicy.DiscardDenyPolicy();
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();
    private final long keepAliveTime;
    private final TimeUnit timeUnit;
    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY, queueSize, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(
            int initSize,
            int maxSize,
            int coreSize,
            ThreadFactory threadFactory,
            int queueSize,
            DenyPolicy denyPolicy,
            long keepAliveTime,
            TimeUnit timeUnit
    ) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    private void init() {
        start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    public static void runExample() {
        ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) { e.printStackTrace(); }
            });
        }
//        for (; ;) {
//            target.println("getActiveCount: " + threadPool.getActiveCount());
//            target.println("getQueueSize: " + threadPool.getQueueSize());
//            target.println("getCoreSize: " + threadPool.getCoreSize());
//            target.println("getMaxSize: " + threadPool.getMaxSize());
//            target.println("========================================");
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        try {
            TimeUnit.SECONDS.sleep(12);
            threadPool.shutdown();
//            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        this.runnableQueue.offer(runnable);
    }

    private static class ThreadTask {
        Thread thread;
        InternalTask internalTask;
        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    private void newThread() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadTaskQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread() {
        ThreadTask threadTask = threadTaskQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void run() {
        while (!isShutdown && !isInterrupted()) {
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }
            synchronized (this) {
                if (isShutdown) {
                    break;
                }
                if (runnableQueue.size() > 0 && activeCount < coreSize) {
                    this.traverseThread(initSize, coreSize, this::newThread);
                    continue;
                }
                if (runnableQueue.size() > 0 && activeCount < maxSize) {
                    this.traverseThread(coreSize, maxSize, this::newThread);
                }
                if (runnableQueue.size() == 0 && activeCount > coreSize) {
                    this.traverseThread(coreSize, activeCount, this::removeThread);
                }
            }
        }
    }

    private void traverseThread(int startSize, int endSize, Runnable run) { for (int i = startSize; i < endSize; i++) {
        run.run();
    }
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown) {
                return;
            }
            isShutdown = true;
            threadTaskQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() { return getSize(initSize); }

    @Override
    public int getMaxSize() { return getSize(maxSize); }

    @Override
    public int getCoreSize() { return getSize(coreSize); }

    @Override
    public int getQueueSize() { return getSize(runnableQueue.size()); }

    @Override
    public int getActiveCount() {
        synchronized (this) { return activeCount; }
    }

    @Override
    public boolean isShutdown() { return isShutdown; }

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
        private static final ThreadGroup threadGroup = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndIncrement());
        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) { return new Thread(threadGroup, runnable, "thread-pool-" + COUNTER.getAndIncrement()); }
    }

    private int getSize(int size) throws IllegalStateException {
        if (isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return size;
    }
}

// 继承初始化顺序演示
class Meal {
    public static int bb = 22;
    static {
        System.out.println("Meal静态块初始化");
    }
    Meal() { System.out.println("Meal()"); }
}
class Bread { Bread() { System.out.println("Bread()"); }}
class Cheese { Cheese() { System.out.println("Cheese()"); }}
class Lettuce { Lettuce() { System.out.println("Lettuce()"); }}
class Lunch extends Meal {
    static {
        System.out.println("Lunch静态块初始化");
    }
    Lunch() { System.out.println("Lunch()"); }
}
class PortableLunch extends Lunch {
    static {
        System.out.println("PortableLunch静态块初始化");
    }
    PortableLunch() { System.out.println("PortableLunch()"); }
}

// 在基类中调用多态方法！由于是继承，所以先初始化基类，当再基类构造器中执行draw()方法时，由于子类也有相同的draw()方法，此时基类构造器调用的draw()方法会直接找到派生RoundGlyph类
// 然而，根据继承初始化顺序来看此时的派生RoundGlyph类的构造器此时还没有运行，但成员变量和成员方法此时已经进行了初始化，值得注意的是此时的成员变量还没有进行赋值，仅仅是初始化阶段
// 那么此时的radius的值根据成员变量初始化的默认值来看radius为0，此时基类中构造器运行的draw()方法实际运行的是派生RoundGlyph类中的draw()方法，那么draw()方法运行的结果是0
// 当基类构造器初始化完毕后，轮到派生RoundGlyph类进行初始化，此时radius被赋值为1，当执行new RoundGlyph(5)，radius又被赋值为5，所以radius的最后结果是5。
// 总结来说，当在继承中，子类覆盖了基类的方法时(即: 两个类都存在同名方法)，如果在基类中进行调用同名方法时，会默认找到派生类的同名方法，然后进行调用，而不在基类中进行调用这个同名方法。
// 接着由于派生类非静态成员此时还没有进行实际赋值(已进行初始化)，那么这个同名方法所取到的这个非静态成员变量此时为默认值，所以取到的时候为默认值。
// 直到派生类的构造器初始化之前，非静态成员变量才正确的被进行赋值
// 需要注意是，静态成员变量在进行初始化的时候会被同时正确赋值，而非静态成员变量不会！同名方法从基类一直到派生类进行扩散，也就是在基类调用同名方法时，调用的实际是派生类的同名方法！
class Glyph {
    void draw() { System.out.println("Glyph.draw()"); }
    Glyph() {
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;
    private static final int staticRadius = 2;
    RoundGlyph(int r) { radius = r;System.out.println("RoundGlyph.RoundGlyph(), radius = " + radius); }
    @Override
    void draw() { System.out.println("RoundGlyph.draw(), radius = " + radius + ", staticRadius = " + staticRadius); }
}

class Grain {
    @Override
    public String toString() { return "Grain"; }
}

class Wheat extends Grain {
    @Override
    public String toString() { return "Wheat"; }
}

class Mill { Grain process() { return new Grain(); }}
class WheatMill extends Mill { @Override
Wheat process() { return new Wheat(); }}

class WithInner {
    class Inner {
        public void run() {
            System.out.println("我是WithInner的内部类Inner！");
        }
    }
}

class InheritInner extends WithInner.Inner {
    InheritInner(WithInner withInner) {
        // 当继承一个类中的内部类时，在子类的构造器必须显示给出这个继承内部类的外部类引用，然后外部类引用.super()，正确指向外部类，之后就可以实现继承这个内部类了
        withInner.super();
    }
    @Override
    public void run() {
        System.out.println("我是InheritInner类，我继承WithInner类中的内部类Inner！");
    }
    public void parentMethodRun() {
        super.run();
    }
}

// 异常示例(在运行时动态的向DynamicFields对象添加字段)
class DynamicFieldsException extends Exception {}
class DynamicFields {
    private Object[][] fields;
    public DynamicFields(int initialSize) {
        fields = new Object[initialSize][2];
        for (int i = 0; i < initialSize; i++) {
            fields[i] = new Object[]{ null, null };
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Object[] obj: fields) {
            result.append((obj[0])).append(": ").append(obj[1]).append("\n");
        }
        return result.toString();
    }

    private int findField(String id) {
        for (int i = 0; i < fields.length; i++) {
            if (id.equals(fields[i][0])) {
                return i;
            }
        }
        return -1;
    }

    private int getFieldNumber(String id) throws NoSuchFieldException {
        int fieldNum = findField(id);
        if (fieldNum == -1) {
            throw new NoSuchFieldException();
        }
        return fieldNum;
    }

    private int makeField(String id) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i][0] == null) {
                fields[i][0] = id;
                return i;
            }
        }

        Object[][] tmp = new Object[fields.length + 1][2];
        System.arraycopy(fields, 0, tmp, 0, fields.length);
        tmp[fields.length] = new Object[]{ null, null };
        fields = tmp;
        return makeField(id);
    }

    public Object getField(String id) throws NoSuchFieldException { return fields[getFieldNumber(id)][1]; }
    public Object setField(String id, Object value) throws DynamicFieldsException {
        if (value == null) {
            DynamicFieldsException dynamicFieldsException = new DynamicFieldsException();
            // initCause()方法作用是包装原始的异常类，然后再统一向上抛出一个统一的异常信息，这样，如果在上层处理这些异常，则上层就不必写很多catch进行捕获了，起到代理的作用。
            dynamicFieldsException.initCause(new NullPointerException());
            throw dynamicFieldsException;
        }

        int fieldNumber = findField(id);
        if (fieldNumber == -1) {
            fieldNumber = makeField(id);
        }
        Object result;
        try { result = getField(id); } catch (NoSuchFieldException e) { throw new RuntimeException(e); }
        fields[fieldNumber][1] = value;
        return result;
    }
}

// 当在主程序中使用try语句时，如果后面不跟catch语句，直接跟着finally的话，则finally语句的异常会覆盖掉try语句内抛出的异常信息，导致try语句内的异常信息丢失。
class VeryImportantException extends Exception {
    @Override
    public String toString() { return "A very important exception"; }
}

class HoHumException extends Exception {
    @Override
    public String toString() { return "A trivial exception"; }
}

class LostMessage {
    void f() throws VeryImportantException { throw new VeryImportantException(); }
    void dispose() throws HoHumException { throw new HoHumException(); }
}

class LinkedStack<T> {
    private static class Node<U> {
        public U item;
        public Node<U> next;
        public Node() { this.item = null; this.next = null; }
        public Node(U item, Node<U> next) { this.item = item; this.next = next; }
        public boolean end() { return item == null && next == null; }
    }

    private Node<T> top = new Node<>();

    public void push(T item) { top = new Node<>(item, top); }
    public T pop() {
        T item = top.item;
        if (!top.end()) {
            top = top.next;
        }
        return item;
    }
}

interface Generator<T> { T next(); }
class Generators {
    public static <T> Collection<T> makeGeneratorFillToCollection(Collection<T> collection, Generator<T> generator, int n) {
        for (int i = 0; i < n; i++) {
            collection.add(generator.next());
        }
        return collection;
    }
}

class Counter {
    private static long counter = 0;
    private long id = ++counter;
    public final String counterName;
    private static Class<?> previousClass = null;

    public Counter() { this("Counter: "); }
    public Counter(String counterName) { this.counterName = counterName; }

    public void reset() {
        counter = 0;
        id = 1;
        counter++;
    }

    public static Counter create(Class<?> cls) {
        Counter instance = new Counter();
        if (previousClass != null && previousClass != cls) {
            instance.reset();
        }
        previousClass = cls;
        return instance;
    }

    @Override
    public String toString() { return counterName + id; }
}

record Product(int id, String description, double price) {

    @Override
    public String toString() { return id + ": " + description + ", price: $" + price + ", " + Counter.create(this.getClass()); }

    public static Generator<Product> generator = new Generator<>() {
        private final Random random = new Random(47);

        @Override
        public Product next() {
            return new Product(random.nextInt(100), "Test", Math.round(random.nextDouble() * 1000.0) + 0.99);
        }
    };
}

class Shelf extends ArrayList<Product> {
    public Shelf(int nProducts) { Generators.makeGeneratorFillToCollection(this, Product.generator, nProducts); }
}

class Aisle extends ArrayList<Shelf> {
    public Aisle(int nShelves, int nProducts) {
        for(int i = 0; i < nShelves; i++) {
            add(new Shelf(nProducts));
        }
    }
}

class Store extends ArrayList<Aisle> {
    public Store(int nAisles, int nShelves, int nProducts) { for (int i = 0; i < nAisles; i++) {
        add(new Aisle(nShelves, nProducts));
    }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Aisle aisle: this) {
            for(Shelf shelf: aisle) {
                for (Product product : shelf) {
                    result.append(product).append("\n");
                }
            }
        }
        return result.toString();
    }
}

class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}

class GenericWriting {
    static <T> void writeExact(List<T> list, T item) { list.add(item); }
    static List<Apple> apples = new ArrayList<>();
    static List<Fruit> fruit = new ArrayList<>();
    static void f1() {
        writeExact(apples, new Apple());
        writeExact(fruit, new Apple()); // 照理说，这里应该报错的，因为List<Fruit>和List<Apple>类型不兼容！可是这里并没有报错...，难道是继承关系？
        try {
            Method method = fruit.getClass().getDeclaredMethod("add", Object.class);
            Parameter[] ps = method.getParameters();
            Arrays.stream(ps).forEach(p -> System.out.println(p.getType().getName() + ": " + p.getName()));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    static <T> void writeWithWildcard(List<? super T> list, T item) { list.add(item); }
    static void f2() {
        writeWithWildcard(apples, new Apple());
        writeWithWildcard(fruit, new Apple());
    }
}

abstract class LibraryItem {
    private final String name;
    public LibraryItem(String name) { this.name = name; }
    public String getItemName() { return name; }
    public abstract String getType();
}

// 书籍
class Book extends LibraryItem {
    public Book(String name) { super(name); }

    @Override
    public String getType() { return "Book"; }
}

// 杂志
class Journal extends LibraryItem {
    public Journal(String name) { super(name); }

    @Override
    public String getType() { return "Journal"; }
}

// 人(借书或归还书的人)
record Borrower(String name) {}

abstract class Library {
    public abstract void borrowItem(LibraryItem item, Borrower borrower);
    public abstract void returnItem(LibraryItem item);
    public void recordInfo(LibraryItem item, String supplementaryInfo) {
        System.out.println(item.getType() + " " + item.getItemName() + " have been borrowed by " + supplementaryInfo);
    }
}

class ConcreteLibrary extends Library {
    @Override
    public void borrowItem(LibraryItem item, Borrower borrower) { recordInfo(item, borrower.name()); }

    @Override
    public void returnItem(LibraryItem item) { recordInfo(item, "have been returned."); }
}

abstract class LibraryDecorator extends Library {
    protected Library library;
    public LibraryDecorator(Library library) { this.library = library; }

    @Override
    public void borrowItem(LibraryItem item, Borrower borrower) { this.library.borrowItem(item, borrower); }

    @Override
    public void returnItem(LibraryItem item) { this.library.returnItem(item); }
}

class CountingLibrary extends LibraryDecorator {
    private static int counter = 0;
    private enum BookOperate { BORROW, RETURN }

    public CountingLibrary(Library library) { super(library); }

    @Override
    public void borrowItem(LibraryItem item, Borrower borrower) {
        this.library.borrowItem(item, borrower);
        operate(BookOperate.BORROW);
    }

    @Override
    public void returnItem(LibraryItem item) {
        this.library.returnItem(item);
        operate(BookOperate.RETURN);
    }

    private void operate(BookOperate bookOperate) {
        counter = BookOperate.BORROW == bookOperate ? counter + 1 : counter - 1;
        this.printItemsCounter();
    }

    private void printItemsCounter() {
        System.out.println("The library has been borrowed " + counter + " items.");
        int ALL_BOOKS_RETURNED = 0;
        if (counter == ALL_BOOKS_RETURNED) {
            System.out.println("All the books have been returned!");
        }
    }
}

class SellingLibrary extends LibraryDecorator {
    public SellingLibrary(Library library) { super(library); }
    public void sellBook(LibraryItem item) { recordInfo(item, "has been sold."); }
}

class Decorator {
    public static void run() {
        Borrower borrower = new Borrower("刑天");
        LibraryItem book = new Book("《《代码整洁之道》》");
        LibraryItem journal = new Journal("《《Day night》》");
        Library library = new ConcreteLibrary();
        System.out.println("-----------------------------------------");
        CountingLibrary countingLibrary = new CountingLibrary(library);
        SellingLibrary sellingLibrary = new SellingLibrary(countingLibrary);
        countingLibrary.borrowItem(book, borrower);
        countingLibrary.borrowItem(journal, borrower);
        countingLibrary.returnItem(book);
        countingLibrary.returnItem(journal);

        sellingLibrary.sellBook(book);
    }
}

abstract class Seasoning {
    private final String name;
    private double price;
    public Seasoning(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getSeasoningName() { return name; }
    public double getPrice() { return price; }
    public String getName() { return name; }
    public void setPrice(double newPrice) { price = newPrice; }
    public abstract String getType();
}

class DrinkSeasoning extends Seasoning {
    private final String type;
    public DrinkSeasoning(String name, double price, String type) {
        super(name, price);
        this.type = type;
    }

    @Override
    public String getType() { return type; }
}

abstract class Drink {
    private String name;
    private static double price;
    private String type = "Drink";
    private static final double NO_PRICE = 0.00;
    private static double totalPrice = NO_PRICE;
    private static final List<Seasoning> seasoningBox = new ArrayList<>();
    private static final List<String> drinkCompositionTable = new ArrayList<>();
    public Drink(String name) { this.name = name; }

    protected double getPrice() { return price; }
    protected String getName() { return name; }
    protected String getType() { return type; }
    protected double getTotalPrice() { return totalPrice; }
    protected List<Seasoning> getSeasoningBox() { return seasoningBox; }
    protected List<String> getDrinkCompositionTable() { return drinkCompositionTable; }

    protected void setName(String newName) { name = newName; }
    protected void setOriginalDrinkTotalPrice(double price) { totalPrice += price; }
    protected void setType(String newType) { type = newType; }
    protected void setPrice(double newPrice) {
        if (newPrice < NO_PRICE) {
            return;
        }
        price = newPrice;
        setOriginalDrinkTotalPrice(price);
    }

    protected void printDrinkInfo(String info) { System.out.println(info); }
    protected void addSeasoning(Seasoning seasoning) { seasoningBox.add(seasoning); }
    protected void addDrinkComposition(String drinkComposition) { drinkCompositionTable.add(drinkComposition); }
    protected void calcTotalPrice(Seasoning seasoning) { totalPrice += seasoning.getPrice(); }

    protected String getNewProductCoffeeCompositionTable() {
        List<String> list = new ArrayList<>(getDrinkCompositionTable());
        list.addAll(getListStream(getSeasoningBox(), Seasoning::getSeasoningName).collect(Collectors.toList()));
        return String.join("、", list);
    }

    protected String getSeasoningNames() { return getBaseNames(getSeasoningBox(), Seasoning::getSeasoningName); }
    private <T> String getBaseNames(List<T> list, Function<T, String> func) { return getListStream(list, func).collect(Collectors.joining(", ")); }
    private <T, R> Stream<R> getListStream(List<T> list, Function<T, R> func) { return list.stream().map(func); }

    public static void remakingDrink() {
        seasoningBox.clear();
        drinkCompositionTable.clear();
        price = NO_PRICE;
        totalPrice = NO_PRICE;
    }

    public abstract void printCoffeeInfo();
}

class Coffee extends Drink {
    public Coffee(String name, double price) {
        super(name);
        setPrice(price);
        makeCoffee();
    }

    public void makeCoffee() {
        setType("Coffee");
        addDrinkComposition("水");
        addDrinkComposition("咖啡豆");
        printCoffeeInfo();
    }

    @Override
    public void printCoffeeInfo() { this.printDrinkInfo("原味咖啡, 成分表: " + getNewProductCoffeeCompositionTable() + ", 价格: ¥" + getPrice() + "元"); }
}

abstract class DrinkDecorator extends Drink {
    protected final Drink drink;
    public DrinkDecorator(String name, Drink drink) {
        super(name);
        this.drink = drink;
    }
}

class NewProductCoffee extends DrinkDecorator {
    public NewProductCoffee(String name, Drink drink) { super(name, drink); }

    @Override
    public void printCoffeeInfo() {
        String newProductCoffeeCompositionTable = getNewProductCoffeeCompositionTable();
        String seasoningNames = getSeasoningNames();
        double totalPrice = getTotalPrice();
        String info = "在" + getType() + "倒入了" + "( " + seasoningNames + " ), 商品名称: " + getName() + ", 成分表: " + newProductCoffeeCompositionTable +
                ", 此时的总价格: ¥" + totalPrice + "元";
        System.out.println(info);
    }

    @Override
    public String getType() { return this.drink.getType(); }
}

class DecoratorDrinkExample {
    public static void run() {
        String[] seasoningItemNames = new String[] { "牛奶", "泡沫", "巧克力", "焦糖", "生奶油" };
        double[] seasoningItemPrice = new double[] { 1.00, 4.00, 8.00, 10.00, 22.00 };
        Seasoning[] seasoningMenuItems = new Seasoning[seasoningItemPrice.length];
        for(int i = 0; i < 5; i++) {
            seasoningMenuItems[i] = new DrinkSeasoning(
                    seasoningItemNames[i],
                    seasoningItemPrice[i],
                    i > 1 && i < seasoningItemPrice.length - 1 ? "Solid Seasoning" : "Liquid Seasoning"
            );
        }

        Drink drink = new Coffee("咖啡", 18.00);
        StringBuilder newProductName = new StringBuilder();
        for (Seasoning seasoningMenuItem: seasoningMenuItems) {
            newProductName.append(seasoningMenuItem.getName()).append("咖啡");
            drink = new NewProductCoffee(newProductName.toString(), drink);
            drink.addSeasoning(seasoningMenuItem);
            drink.calcTotalPrice(seasoningMenuItem);
            drink.printCoffeeInfo();
            newProductName.delete(newProductName.length() - 2, newProductName.length());
        }

        enum TestEnum {
            ONE("ONE"), TWO("TWO");

            private final String str;
            TestEnum(String s) { str = s; }
            public String getStr() { return str; }
        }

        System.out.println(Enum.valueOf(TestEnum.class, "ONE"));
        System.out.println(Arrays.toString(TestEnum.values()));
    }
}

interface TimeStamped { long getStamp(); }
class TimeStampedImp implements TimeStamped {
    private final long timeStamp;
    public TimeStampedImp() { this.timeStamp = new Date().getTime(); }
    @Override
    public long getStamp() { return timeStamp; }
}

interface SerialNumbered { String getSerialNumberDescription(); }
class SerialNumberedImp implements SerialNumbered {
    private final Counter serialNumber = Counter.create(this.getClass());

    @Override
    public String getSerialNumberDescription() { return serialNumber.toString(); }
    public SerialNumberedImp resetCounter() {
        serialNumber.reset();
        return this;
    }
}

interface Basic { void set(String val); String get(); }
class BasicImp implements Basic {
    private String title;
    @Override
    public void set(String newTitle) { title = newTitle; }
    @Override
    public String get() { return title; }
}

// 此模式不属于Mixin模式，顶多算一个组合模式
class Mixin extends BasicImp implements TimeStamped {
    private final TimeStamped timeStamped = new TimeStampedImp();
    private final SerialNumbered serialNumbered = new SerialNumberedImp();

    @Override
    public long getStamp() { return timeStamped.getStamp(); }
    public String getSerialNumberDescription() { return serialNumbered.getSerialNumberDescription(); }

    @Override
    public String toString() { return "title" + "( " + get() + " ) -> " + "timeStamp: " + getStamp() + ", " + getSerialNumberDescription(); }
}

class Mixins {
    public static void runMixin() {
        Mixin mixin1 = new Mixin(), mixin2 = new Mixin();
        mixin1.set("test string 1");
        mixin2.set("test string 2");
        System.out.println(mixin1);
        System.out.println(mixin2);
    }
}

class Tuple {
    static class TwoTuple<A, B> {
        public final A first;
        public final B second;
        public TwoTuple(A a, B b) {
            this.first = a;
            this.second = b;
        }
    }

    static class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
        public final C three;
        public ThreeTuple(A a, B b, C c) {
            super(a, b);
            this.three = c;
        }
    }

    static class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
        public final D four;
        public FourTuple(A a, B b, C c, D d) {
            super(a, b, c);
            this.four = d;
        }
    }

    static class FiveTuple<A, B, C, D, E> extends FourTuple<A, B, C, D> {
        public final E five;
        public FiveTuple(A a, B b, C c, D d, E e) {
            super(a, b, c, d);
            this.five = e;
        }
    }

    public static <A, B> TwoTuple<A, B> tuple(A a, B b) { return new TwoTuple<>(a, b); }
    public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) { return new ThreeTuple<>(a, b, c); }
    public static <A, B, C, D> FourTuple<A, B, C, D> tuple(A a, B b, C c, D d) { return new FourTuple<>(a, b, c, d); }
    public static <A, B, C, D, E> FiveTuple<A, B, C, D, E> tuple(A a, B b, C c, D d, E e) { return new FiveTuple<>(a, b, c, d, e); }
}

class MixinProxy implements InvocationHandler {
    Map<String, Object> delegatesByMethod;
    @SafeVarargs
    public MixinProxy(Tuple.TwoTuple<Object, Class<?>>... pairs) {
        delegatesByMethod = new HashMap<>();
        for (Tuple.TwoTuple<Object, Class<?>> pair: pairs) {
            for (Method method: pair.second.getMethods()) {
                String methodName = method.getName();
                if (!delegatesByMethod.containsKey(methodName)) {
                    delegatesByMethod.put(methodName, pair.first);
                }
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(delegatesByMethod.get(method.getName()), args);
    }

    public static Object newInstance(Tuple.TwoTuple... pairs) {
        final int PAIRS_LENGTH = pairs.length;
        Class<?>[] interfaces = new Class[PAIRS_LENGTH];
        for(int i = 0; i < PAIRS_LENGTH; i++) {
            interfaces[i] = (Class<?>) pairs[i].second;
        }
        ClassLoader classLoader = pairs[0].first.getClass().getClassLoader();
        return Proxy.newProxyInstance(classLoader, interfaces, new MixinProxy(pairs));
    }
}

class DynamicProxyMixin {
    public static void run() {
        Object mixin = MixinProxy.newInstance(
                tuple(new BasicImp(), Basic.class),
                tuple(new TimeStampedImp(), TimeStamped.class),
                tuple(new SerialNumberedImp().resetCounter(), SerialNumbered.class)
        );

        Basic basic = (Basic) mixin;
        TimeStamped timeStamped = (TimeStamped) mixin;
        SerialNumbered serialNumbered = (SerialNumbered) mixin;

        basic.set("Hello");

        System.out.println(basic.get());
        System.out.println(timeStamped.getStamp());
        System.out.println(serialNumbered.getSerialNumberDescription());
    }

    public static <A, B> Tuple.TwoTuple<A, B> tuple(A entityClass, B cls) { return Tuple.tuple(entityClass, cls); }
}

interface CustomCombiner<T> { T combine(T x, T y); }
interface CustomFunction<T, R> { R transform(T x); }
interface CustomCollector<T> extends CustomFunction<T, T> { T result(); }
interface CustomUnaryPredicate<T> { boolean test(T x); }

class CustomFunctional {
    public static <T> T reduce(Iterable<T> seq, CustomCombiner<T> combiner) {
        Iterator<T> iterator = seq.iterator();
        if(iterator.hasNext()) {
            T result = iterator.next();
            while(iterator.hasNext()) {
                result = combiner.combine(result, iterator.next());
            }
            return result;
        }
        return null;
    }

    public static <T> CustomCollector<T> forEach(Iterable<T> seq, CustomCollector<T> func) {
        for(T t: seq) {
            func.transform(t);
        }
        return func;
    }

    public static <T, R> List<R> transform(Iterable<T> seq, CustomFunction<T, R> func) {
        return traverse(seq, (List<R> result, T item) -> result.add(func.transform(item)));
    }

    public static <T> List<T> filter(Iterable<T> seq, CustomUnaryPredicate<T> predicate) {
        return traverse(seq, (List<T> result, T item) -> {
            if (predicate.test(item)) {
                result.add(item);
            }
            return null;
        });
    }

    private static <T, R> List<R> traverse(Iterable<T> seq, BiFunction<List<R>, T, ?> callback) {
        List<R> result = new ArrayList<>();
        for(T t: seq) {
            callback.apply(result, t);
        }
        return result;
    }

    public static class IntegerAdder implements CustomCombiner<Integer> { @Override
    public Integer combine(Integer x, Integer y) { return x + y; } }

    public static class IntegerSubtraction implements CustomCombiner<Integer> { @Override
    public Integer combine(Integer x, Integer y) { return x - y; } }

    public static class BigDecimalAdder implements CustomCombiner<BigDecimal> { @Override
    public BigDecimal combine(BigDecimal x, BigDecimal y) { return x.add(y); } }

    public static class BigIntegerAdder implements CustomCombiner<BigInteger> { @Override
    public BigInteger combine(BigInteger x, BigInteger y) { return x.add(y); } }

    public static class AtomicLongAdder implements CustomCombiner<AtomicLong> {
        @Override
        public AtomicLong combine(AtomicLong x, AtomicLong y) { return new AtomicLong(x.addAndGet(y.get())); }
    }

    public static class BigDecimalUlp implements CustomFunction<BigDecimal, BigDecimal> { @Override
    public BigDecimal transform(BigDecimal x) { return x.ulp(); } }

    public record GreaterThan<T extends Comparable<T>>(T bound) implements CustomUnaryPredicate<T> {
        @Override
        public boolean test(T x) { return x.compareTo(bound) > 0; }
    }

    public static class MultiplyingIntegerCollector implements CustomCollector<Integer> {
        private Integer val = 1;
        @Override
        public Integer result() { return val; }
        @Override
        public Integer transform(Integer x) { return val *= x; }
    }

    public static void run() {
        Consumer<? super Object> println = System.out::println;

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Integer result = reduce(list, new IntegerAdder());
        println.accept(result);

        result = reduce(list, new IntegerSubtraction());
        System.out.println(result);

        println.accept(filter(list, new GreaterThan<>(4)));
        println.accept(forEach(list, new MultiplyingIntegerCollector()).result());
        println.accept(forEach(filter(list, new GreaterThan<>(4)), new MultiplyingIntegerCollector()).result());

        // 用于浮点数向上、向下取整等操作，具体参见枚举RoundingMode
        MathContext mathContext = new MathContext(7);
        List<BigDecimal> lbd = Arrays.asList(
                new BigDecimal(1.1, mathContext),
                new BigDecimal(2.2, mathContext),
                new BigDecimal(3.3, mathContext),
                new BigDecimal(4.4, mathContext)
        );

        println.accept(reduce(lbd, new BigDecimalAdder()));
        println.accept(filter(lbd, new GreaterThan<>(new BigDecimal(3))));
        println.accept(transform(lbd, new BigDecimalUlp()));

        List<BigInteger> lbi = new ArrayList<>(11);
        BigInteger bi = BigInteger.valueOf(11);
        for (int i = 1; i < 11; i++) {
            lbi.add(bi = bi.nextProbablePrime());
        }

        println.accept(lbi);

        BigInteger rbi = reduce(lbi, new BigIntegerAdder());
        println.accept(rbi);
        assert rbi != null;
        println.accept(rbi.isProbablePrime(5));

        List<AtomicLong> lal = Arrays.asList(
                new AtomicLong(11),
                new AtomicLong(47),
                new AtomicLong(74),
                new AtomicLong(133)
        );

        println.accept(reduce(lal, new AtomicLongAdder()));
    }
}

class CollectionData<T> extends ArrayList<T> {
    public CollectionData(Generator<T> generator, int quantity) { for (int i = 0; i < quantity; i++) {
        add(generator.next());
    }
    }
    public static <T> CollectionData<T> list(Generator<T> generator, int quantity) { return new CollectionData<>(generator, quantity); }
}

class Government implements Generator<String> {
    String[] foundation = "strange women lying in ponds distributing swords is no basis for a system of government".split(" ");
    private int index;
    @Override
    public String next() { return foundation[index++]; }
}

class CollectionDataTest {
    public static void run() {
        Set<String> set = new LinkedHashSet<>(new CollectionData<>(new Government(), 15));
        set.addAll(CollectionData.list(new Government(), 15));
        System.out.println(set);
    }
}

// 书写equals方法的几点建议示例
class EqualsMethodSuggestion {
    public String compareField_1 = "比较测试变量1";
    public String compareField_2 = "比较测试变量2";

    @Override public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        EqualsMethodSuggestion otherEqualsMethodSuggestion = (EqualsMethodSuggestion) other;
        // 防备实例变量字段可能为null的情况，需要使用Objects.equals方法。如果两个参数都为null，Objects.equals(a, b)调用将返回true；
        // 如果其中一个参数为null，则返回false；否则，如果两个参数都不为null，则调用a.equals(b)。
        return Objects.equals(compareField_1, otherEqualsMethodSuggestion.compareField_1) &&
                Objects.equals(compareField_2, otherEqualsMethodSuggestion.compareField_2);
    }
}

class Copy {
    public static Object goodCopyOf(Object obj, int newLength) {
        Class<?> cls = obj.getClass();
        if (!cls.isArray()) {
            return null;
        }
        Class<?> componentType = cls.getComponentType();
        int length = Array.getLength(obj);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(obj, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }
}

class MobilePhone {
    private final int[] originalSequence = IntStream.range(0, 10).toArray();
    private final int[] mobilePhoneSecondIndexSequence = new int[] { 3, 8, 5, 7, 9 };
    private static final Random randomNumber = new Random();

    enum Operator {
        MOBILE("四川移动", 5, 7, 8, 9), UNICOM("四川联通", 0, 2, 5, 6, 9), TELECOM("四川电信", 3, 7, 9), OTHER("other");
        private final int[] rules;
        Operator(String val, int... rules) { this.rules = rules; }
        public int[] getRules() { return rules; }
    }

    enum ChooseType {
        BEAUTIFUL_NUMBER("选取靓号"), GENERAL_PICK_NUMBER("普通选号"), OTHER("other");
        ChooseType(String val) {}
    }

    public void phoneNo() {
        String info = "该号码不是我想要的号码, 我不办理";
        Pattern pattern = Pattern.compile("^1(9)\\1{9}$");
        while (true) {
            String phoneNumber = generatorMobilePhoneNumber(Operator.UNICOM, ChooseType.BEAUTIFUL_NUMBER);
            if (phoneNumber == null) {
                System.out.println("对不起，您暂时无法获取号码！");
                return;
            }

            if (pattern.matcher(phoneNumber).matches()) {
                info = "我要办理该手机号, 号码为: " + phoneNumber;
                break;
            }
        }

        System.out.println(info);
    }

    private String generatorMobilePhoneNumber(Operator operator, ChooseType chooseType) {
        StringBuilder mobilePhoneNumber = new StringBuilder("1");
        int secondDigit = generatorMobilePhoneNumberRandomDigit(mobilePhoneSecondIndexSequence);
        int thirdDigit = generatorMobilePhoneNumberThirdDigit(secondDigit);
        int fourDigit = generatorMobilePhoneNumberFourDigit(operator);
        int remainingNumber = generatorRemainingMobilePhoneNumber(chooseType);

        boolean isOtherOperator = fourDigit == Operator.OTHER.ordinal();
        boolean isOtherType = remainingNumber == ChooseType.OTHER.ordinal();
        if (isOtherOperator || isOtherType) {
            boolean[] otherTypes = new boolean[] { isOtherOperator, isOtherType };
            for (int i = 0; i < otherTypes.length; i++) {
                if (otherTypes[i]) {
                    System.err.println("没有该种" + (i == 0 ? "运营商" : "选号") + "类型");
                }
            }
            return null;
        }

        return mobilePhoneNumber
                .append(secondDigit)
                .append(thirdDigit)
                .append(fourDigit)
                .append(remainingNumber)
                .toString();
    }

    private int generatorMobilePhoneNumberThirdDigit(int mobilePhoneNumberSecondDigit) {
        final List<Integer> mobilePhoneSecondIndexList = Arrays.stream(mobilePhoneSecondIndexSequence).boxed().collect(Collectors.toList());
        final int LIST_SIZE = mobilePhoneSecondIndexList.size();
        final int LIST_MIDDLE_INDEX = LIST_SIZE >>> 1;
        Predicate<Integer> isMatch = isMatch(mobilePhoneNumberSecondDigit);

        if (mobilePhoneSecondIndexList.subList(0, LIST_MIDDLE_INDEX).stream().anyMatch(isMatch)) {
            return generatorMobilePhoneNumberRandomDigit(originalSequence);
        }

        return mobilePhoneSecondIndexList.subList(LIST_MIDDLE_INDEX, LIST_SIZE - 1)
                .stream()
                .filter(isMatch)
                .map(x ->
                        generatorMobilePhoneNumberRandomDigit(
                                generatorComplement(
                                        x > mobilePhoneSecondIndexList.get(LIST_MIDDLE_INDEX) ? createArray(4, 9) : createArray(4)
                                )
                        )
                )
                .findFirst()
                .orElse(generatorMobilePhoneNumberRandomDigit(8, 9));
    }

    private int generatorMobilePhoneNumberFourDigit(Operator operator) {
        Operator o = Arrays.stream(Operator.values()).filter(isMatch(operator)).findFirst().orElse(Operator.OTHER);
        if (o != Operator.OTHER) {
            return generatorMobilePhoneNumberRandomDigit(o.getRules());
        }
        return o.ordinal();
    }

    private int generatorRemainingMobilePhoneNumber(ChooseType chooseType) {
        return switch (chooseType) {
            case BEAUTIFUL_NUMBER -> generatorMobilePhoneNumberRandomDigit(6666666, 8888888, 9999999);
            case GENERAL_PICK_NUMBER -> generatorGeneralPick();
            case OTHER -> ChooseType.OTHER.ordinal();
        };
    }

    private <T, R> Predicate<T> isMatch(R val) { return (T x) -> x == val; }
    private int[] createArray(int... values) { return values; }
    private int generatorMobilePhoneNumberRandomDigit(int... subset) { return generatorMobilePhoneNumberRandomDigit(subset, subset.length); }
    private int generatorMobilePhoneNumberRandomDigit(int[] subset, int defineLength) { return subset[randomNumber.nextInt(defineLength)]; }

    private int generatorGeneralPick() {
        StringBuilder generalPickNumberGroup = new StringBuilder();
        final int LENGTH = 7;
        for (int i = 0; i < LENGTH; i++) {
            generalPickNumberGroup.append(generatorMobilePhoneNumberRandomDigit(originalSequence, LENGTH));
        }
        return Integer.parseInt(generalPickNumberGroup.toString());
    }

    public int[] generatorComplement(int... excludeSequence) {
        final int EXCLUDE_SEQUENCE_LENGTH = excludeSequence.length;
        final int ORIGINAL_SEQUENCE_LENGTH = originalSequence.length;
        final int RESULT_LENGTH = ORIGINAL_SEQUENCE_LENGTH - EXCLUDE_SEQUENCE_LENGTH;
        int[] resultSequence = new int[RESULT_LENGTH];
        int i = 0, index = 0;
        boolean isExclude = false;

        Arrays.sort(excludeSequence);   // 当参数不是有序的时候，会产生问题

        for (int j = 0; i < ORIGINAL_SEQUENCE_LENGTH; i++) {
            if (originalSequence[i] == excludeSequence[j]) {
                j++;
                if (j == EXCLUDE_SEQUENCE_LENGTH) {
                    break;
                }
                continue;
            }
            if (!isExclude) {
                isExclude = true;
            }
            resultSequence[(index = i - j)] = originalSequence[i];
        }

        if (index < RESULT_LENGTH) {
            if (isExclude) {
                index++;
            }
            System.arraycopy(originalSequence, i + 1, resultSequence, index, ORIGINAL_SEQUENCE_LENGTH - i - 1);
        }

        return resultSequence;
    }
}

class TimePrinter implements ActionListener {
    @Override public void actionPerformed(ActionEvent event) {
        System.out.println("All the tone, the time is " + Instant.ofEpochMilli(event.getWhen()));
        Toolkit.getDefaultToolkit().beep();
    }
}

// 模拟函数式接口Predicate以及实现函数式接口的链式调用
/*
* 泛型类在编译期间，无论是否使用限定符(extends、super)，都会把泛型变量进行擦除，只留下基本类型类。
* 例如: Pair<T>和Pair<T extends Comparable>在编译期间都会变成class Pair {}，为了提高效率，应该将没有方法的接口放在限定列表的末尾。
*
* 泛型方法则不然，如果泛型变量没有使用限定符的话，则在编译期间使用泛型变量会变为Object，如果泛型变量使用了限定符，则在编译期间会变为第一个限定的类型。
* 例如: public static <T extends Comparable & Serializable> T test(T[] arr) {}
* 由于限定的接口第一个是Comparable，则在编译期间会变为public static Comparable test(Comparable[] arr) {}，属于就近原则。
* 泛型方法的返回值也不同，如果对泛型变量没有使用限定符，则默认返回Object。
* 在表达式赋值给一个带有具体泛型的返回值时，由于在编译期间是Object，而在表达式当中又确定了这个泛型变量的具体类型，所以当方法运行结束之后，会返回Object，
* 接着在隐式进行强制转换为表达式的泛型的具体类型。
*
* 例如:
* Pair<Employee> buddies = "...";
* Employee buddy = buddies.getFirst()
* 首先在编译期间，擦除之后为：class Pair<T> {} -> class Pair {}, public T getFirst() { ... } -> public Object getFirst() { ... }
* 接着实例化Pair，进行隐式强制转换为Employee类型，确定了泛型类的类型是Employee。调用getFirst方法，返回一个Object类型，然后隐式强制转换为Employee，调用结束。
* new Pair<T> -> new Pair<Employee>, Object buddy = buddies.getFirst() -> Employee buddy = (Employee) buddies.getFirst()
*/
class Chain {
    private interface UserPredicate<T> { boolean test(T t); }
    private interface MyPredicateImp<T> extends UserPredicate<T> {
        MyPredicateImp<T> and(UserPredicate<T> userPredicate);
        MyPredicateImp<T> or(UserPredicate<T> userPredicate);
        MyPredicateImp<T> negate();
    }

    private static class MyPredicate<T> implements MyPredicateImp<T> {
        // 选择链表的原因是添加删除是O(1)操作，而ArrayList则是额外的动态分配内存，避免不必要的开销！又因为是顺序读取每个元素，所以认为链表更适合一些！
        private static final LinkedList<ControllerImp> predicates = new LinkedList<>();
        private static UserPredicate initialPredicate;

        public MyPredicate(UserPredicate<T> predicate) { initialPredicate = predicate; }

        private enum Type { AND, OR, NEGATE, NOT, EQUAL }

        private static class Data {
            private final boolean shouldSetObj;
            private final Object objectToBeCompared;
            private final MyPredicateImp predicate;
            private boolean shouldResetSpecialStaticFields;

            public Data(boolean shouldSetObj, boolean shouldResetSpecialStaticFields, Object objectToBeCompared, MyPredicateImp predicate) {
                this.shouldSetObj = shouldSetObj;
                this.shouldResetSpecialStaticFields = shouldResetSpecialStaticFields;
                this.objectToBeCompared = objectToBeCompared;
                this.predicate = predicate;
            }

            public boolean isShouldSetObj() { return shouldSetObj; }
            public boolean isShouldResetSpecialStaticFields() { return shouldResetSpecialStaticFields; }
            public Object getObjectToBeCompared() { return objectToBeCompared; }
            public MyPredicateImp getPredicate() { return predicate; }
        }

        interface ControllerImp<T> { boolean test(T t, boolean b); }
        private record Controller<R>(UserPredicate<R> userPredicate, Type type) implements ControllerImp<R> {
            private static Object obj;

            public void setObj(Object newObj) { obj = newObj; }

            private boolean not(R val) { return !runUserDefinePredicate(val); }
            private boolean or(R val, boolean b) { return runUserDefinePredicate(val) || b; }
            private boolean and(R val, boolean b) { return runUserDefinePredicate(val) && b; }
            private boolean negate(boolean b) { return !b; }
            private boolean isEqual(R val) { return Objects.equals(obj, val); }
            private boolean runUserDefinePredicate(R val) { return userPredicate.test(val); }

            @Override
            public boolean test(R val, boolean b) {
                return switch (type) {
                    case AND -> and(val, b);
                    case OR -> or(val, b);
                    case NEGATE -> negate(b);
                    case NOT -> not(val);
                    case EQUAL -> isEqual(val);
                };
            }
        }

        @Override public MyPredicateImp and(UserPredicate<T> predicate) { return add(predicate, Type.AND); }

        @Override public MyPredicateImp or(UserPredicate<T> predicate) { return add(predicate, Type.OR); }

        // Predicate.isEqual("a").negate().test("a");
        @Override public MyPredicateImp negate() { return add(null, Type.NEGATE); }

        public static <R> MyPredicateImp<R> isEqual(R objectToBeCompared) {
            return add(
                    initialPredicate,
                    Type.EQUAL,
                    createData(
                            true,
                            true,
                            objectToBeCompared,
                            ofNullablePredicate()
                    )
            );
        }

        public static <R> MyPredicateImp<R> not(UserPredicate<R> predicate) { return add(predicate, Type.NOT, createData(ofNullablePredicate())); }

        private <R> MyPredicateImp<R> add(UserPredicate<R> predicate, Type type) { return add(predicate, type, createData(this)); }
        private static <R> MyPredicateImp<R> add(UserPredicate<R> predicate, Type type, Data data) {
            if (data.isShouldResetSpecialStaticFields()) { resetListAndInitialPredicateStaticFields(); }
            Controller<R> controller = new Controller<>(predicate, type);
            if (data.isShouldSetObj()) { controller.setObj(data.getObjectToBeCompared()); }
            predicates.add(controller);
            return data.getPredicate();
        }

        private static <R> Data createData(MyPredicate<R> predicate) {
            return createData(false, false, null, predicate);
        }

        private static <R> Data createData(
                boolean shouldSetObj,
                boolean shouldResetSpecialStaticFields,
                Object objectToBeCompared,
                MyPredicateImp<R> predicate
        ) {
            return new Data(shouldSetObj, shouldResetSpecialStaticFields, objectToBeCompared, predicate);
        }

        private static <R> MyPredicate<R> ofNullablePredicate() { return new MyPredicate<>(null); }

        private static void resetListAndInitialPredicateStaticFields() {
            if (predicates.size() > 0) { predicates.clear(); }
            initialPredicate = null;
        }

        private boolean test(boolean result, T val, int index) {
            if (index == predicates.size()) {
                resetListAndInitialPredicateStaticFields();
                return result;
            }

            return test(predicates.get(index).test(val, result), val, index + 1);
        }

        @Override public boolean test(T val) { return test(!Objects.isNull(initialPredicate) && initialPredicate.test(val), val, 0); }
    }

    public static void main(String[] args) {
        class TestPredicateOne implements UserPredicate<Integer> { @Override public boolean test(Integer x) { return x % 4 == 0; }}
        class TestPredicateTwo implements UserPredicate<Integer> { @Override public boolean test(Integer x) { return x % 100 != 0; }}
        class TestPredicateThree implements UserPredicate<Integer> { @Override public boolean test(Integer x) { return x % 400 == 0; }}

        boolean test_native_predicate_one = Predicate.isEqual("a").and(x -> Objects.equals(x, "b")).negate().negate().test("b");
        boolean test_native_predicate_two = Predicate.not(x -> x == "a").and(x -> x == "b").or(x -> ((String) x).length() == 1).test("b");

        boolean test_custom_predicate_one = MyPredicate.isEqual("a").and(x -> Objects.equals(x, "b")).negate().negate().test("b");
        boolean test_custom_predicate_two = MyPredicate.not(x -> x == "a").and(x -> x == "b").or(x -> ((String) x).length() == 1).test("b");

        System.out.println("test_native_predicate_one is ?" + test_native_predicate_one);
        System.out.println("test_native_predicate_two is ?" + test_native_predicate_two);
        System.out.println("test_custom_predicate_one is ?" + test_custom_predicate_one);
        System.out.println("test_custom_predicate_two is ?" + test_custom_predicate_two);

        MyPredicateImp<Integer> myPredicate = new MyPredicate<>(new TestPredicateOne());
        myPredicate = myPredicate.and(new TestPredicateTwo()).or(new TestPredicateThree());
        System.out.println("是否是闰年？" + myPredicate.test(2000));

        boolean isLeapYear = ((Predicate<Integer>) x -> x % 4 == 0)
                .and(x -> x % 100 != 0)
                .or(x -> x % 400 == 0)
                .test(2000);
        System.out.println("是否是闰年？" + isLeapYear);
    }
}

class GenericCopy {
    private static <T extends Comparable<? super T>> T[] buildArray(IntFunction<T[]> constructor, T... a) {
        T[] result = constructor.apply(a.length);
        System.arraycopy(a, 0, result, 0, a.length);
        return result;
    }

    public static void main(String[] args) {
        String[] strings = buildArray(String[]::new, "a", "b", "c");
        System.out.println(Arrays.toString(strings));
    }
}

// 使得Runnable接口的run方法可以抛出异常！要让run方法可以抛出异常，则只需要包装一下run方法即可。
// 泛型可以被当作异常抛出，例如throwAs方法！这样的话，在asRunnable方法中的catch块中就不必在catch块中重写try..catch语句了，不然throw t后还需再写try...catch块。
/*
* 非检查异常：编译器不要求强制处置的异常，虽然有可能出现错误，但是我不会在编译的时候检查。
* 除了RuntimeException（运行时异常）与其子类，以及错误（Error），其他的都是检查异常。*/
class ThreadThrowExceptionTest {
    private interface TaskImp {
        void run() throws Exception;

        static <T extends Throwable> void throwAs(Throwable t) throws T { throw (T) t; }
        static Runnable MakeRunnableThrowException(TaskImp task) { return asRunnable(task, Function.identity()); }
        static Runnable MakeRunnableThrowException(Supplier<TaskImp> task) { return asRunnable(task, t -> task.get()); }

        static <T> Runnable asRunnable(T task, Function<T, TaskImp> function) {
            return () -> {
                try {
                    function.apply(task).run();
                } catch (Throwable t) {
                    // throw t; 需要重新写try...catch块
                    // 这种机制是骗过了编译器，因为泛型变量T，在编译器眼中不清楚是不是检查异常(Throwable是异常和Error的超类)，估计是当作非检查异常来处理
                    TaskImp.throwAs(t/* 编译器会认为是非检查型异常，即：RunTimeException及子类的Exception */);
                }
            };
        }
    }

    private static class ChildrenTask implements TaskImp {
        @Override public void run() throws Exception {
            Thread.sleep(1000);
            System.out.println("Hello World!");
            throw new Exception("Check this out!");
        }
    }

    public static void main(String[] args) {
        new Thread(TaskImp.MakeRunnableThrowException(ChildrenTask::new)).start();
    }
}

// 以下示例相当于一个Map，其中key为任意类型，value为一个函数，表示当key为某些值的时候，会为这个key运行这个key对应的函数
class TypeLiteral<T> {
    private final Type type;
    public TypeLiteral() {
        Type parentType = getClass().getGenericSuperclass();
        if (parentType instanceof ParameterizedType) {
            // 如果new TypeLiteral<ArrayList<Integer>>(){}，则type为ArrayList<Integer>类型
            type = ((ParameterizedType) parentType).getActualTypeArguments()[0];
        } else { throw new UnsupportedOperationException("Construct as new TypeLiteral<...>(){}"); }
    }

    private TypeLiteral(Type type) { this.type = type; }
    public static TypeLiteral<?> of(Type type) { return new TypeLiteral<>(type); }

    @Override public String toString() {
        if (type instanceof Class) { return ((Class<?>) type).getName(); }
        else { return type.toString(); }
    }

    @Override public boolean equals(Object otherObject) {
        return otherObject instanceof TypeLiteral && type.equals(((TypeLiteral<?>) otherObject).type);
    }

    @Override public int hashCode() { return type.hashCode(); }
}

class Formatter {
    private final Map<TypeLiteral<?>, Function<?, String>> rules = new HashMap<>();
    public <T> void forType(TypeLiteral<T> type, Function<T, String> formatterForType) { rules.put(type, formatterForType); }
    public String formatFields(Object obj) throws IllegalArgumentException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field f: obj.getClass().getDeclaredFields()) {
            stringBuilder.append(f.getName()).append("=");
            f.setAccessible(true);
            Function<?, String> formatterForType = rules.get(TypeLiteral.of(f.getGenericType()));
            if (!Objects.isNull(formatterForType)) {
                Function<Object, String> objectFormatter = (Function<Object, String>) formatterForType;
                stringBuilder.append(objectFormatter.apply(f.get(obj)));
            } else {
                stringBuilder.append(f.get(obj).toString());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

class TypeLiterals {
    public static class Sample {
        ArrayList<Integer> nums = new ArrayList<>() {{ add(42); add(1729); }};
        ArrayList<Character> chars = new ArrayList<>() {{ add('H'); add('i'); }};
        ArrayList<String> strings = new ArrayList<>() {{ add("Hello"); add("World"); }};
    }
    private static <T> String join(String separator, ArrayList<T> elements) {
        StringBuilder stringBuilder = new StringBuilder();
        for (T e: elements) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(separator);
            }
            stringBuilder.append(e.toString());
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) throws Exception {
        Formatter formatter = new Formatter();
        formatter.forType(new TypeLiteral<ArrayList<Integer>>(){}, lst -> join(" ", lst));
        formatter.forType(new TypeLiteral<ArrayList<Character>>(){}, lst -> "\"" + join(" ", lst) + "\"");
        System.out.println(formatter.formatFields(new Sample()));
    }
}

// 演示LinkedList有坑点
// List接口用于描述有序集合，并且集合中每个元素的位置很重要！
class LinkedListTest {
    public static void main(String[] args) {
        class Test {
            private final LinkedList<String> linkedList = new LinkedList<>(){{ add("A"); add("B"); add("C"); add("|"); }};

            // listIterator实现了add方法，但与Iterator所声明的方法不同，这个方法没有返回值，它是假设每次都会改变LinkedList！
            // listIterator(int index)方法接收一个位置索引参数，表示在这个位置上的元素开始，默认listIterator()方法从头部开始！
            private final ListIterator<String> listIterator = linkedList.listIterator(2);

            public Test previous() { listIterator.previous();return this; }

            public Test next() { listIterator.next(); return this; }

            public Test remove() { listIterator.remove(); return this; }

            public void print() { System.out.println(linkedList); }
        }
        /*
         * 如果没有在remove操作之前调用previous或next方法的话，会报错！因为内部实现remove方法在最后执行前会清空previous或next返回的元素(lastReturned = null)
         * lastReturned这个实例变量主要作用是防止并发操作同一个元素时侯不一致问题！
         * 同时又可以记录所找到的元素，同时在remove操作后，如果没有调用previous或next方法的话，以便抛出异常！
         *
         * 例如：一个线程删除了linkedList其中的某一个元素，而另一个线程对这个删除的元素又进行了set或删除操作时，这个元素肯定是不存在的，会抛出异常！
         * 在调用previous或next方法时候，lastReturned会被赋值为这两个方法所找到的元素！当第二次调用remove方法时候，会检查lastReturned是否为null，为null则报错！
         *
         * 错误示例：                                     正确示例：
         * listIterator.previous();                      listIterator.previous();
         * listIterator.remove();                        listIterator.remove();
         * listIterator.remove();                        listIterator.previous();
         *                                               listIterator.remove();
         * Note: 下面演示删除操作，其中listIterator(2) = C, linkedList = [ * A, * B, & C, | * ]
         * *代表指向linkedList的位置, &代表当前指向linkedList的位置(内部源码nextIndex变量)。可以看到，当前的位置索引是介于两个元素之间的！
         *
         * 1. previous() && remove() -> linkedList = [ * A, & C, | * ]
         * 2. previous() && remove() -> linkedList = [ & C, | * ]
         * 3. next() && remove() -> linkedList = [ & | * ]
         * 4. next() && remove() -> linkedList = [ & ] , 此时&位置 == linkedList.size(), hasNext()或hasPrevious()返回值等于0，结果为false
         * 此时，无论什么操作都会抛出异常！
         * 另外，如果调用previous方法，再调用remove方法的话，则会删除掉当前(&)指向的位置右边的元素！反之，next() && remove()会删除左边的元素(仔细观察上述的演示过程！)
         */
        new Test().previous().remove().previous().remove().next().remove().next().remove().print();
    }
}

/*
 * File对象代表一个特定的文件，又代表一个目录下的所有文件的集合(数组)!
 * 通过调用file.list方法可以得到一个目录下的所有文件，但有时想从这些文件集合中筛选出我们感兴趣的文件
 * 这时可以向file.list方法传入一个由实现FilenameFilter接口的函数或者是类，来达到过滤效果!
 * FilenameFilter接口只有一个抽象方法, 签名为: boolean accept(File dir, String name), 注意: 接口的抽象方法默认是public修饰符
 * 例如下面的例子中，在找到某个目录下找到所有以.java为后缀的文件，通过正则表达式来过滤出我们不想要的文件!
 * 正则表达式^\\p{Alpha}+\\.java$代表字母字符一个或多个，然后后缀为.java!
 * \\p{Lower}代表小写字符, \\p{Upper}代表大写字符, \\p{ASCII}代表所有ASCII字符, 剩下的以\p开头的正则表达式都代表不同的意思，具体查看java API Pattern类的文档!
 * */
class DirList {
    private final String path;
    private final String regex;

    public DirList(String path, String regex) {
        this.path = path;
        this.regex = regex;
    }

    private FilenameFilter filenameFilter() { return (dir, name) -> Pattern.compile(regex).matcher(name).matches(); }

    public void run() {
        File file = new File(path);
        String[] list = file.list(filenameFilter());
        assert list != null;
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem: list) { System.out.println(dirItem); }
    }
}

final class Directory {
    public static FilenameFilter filenameFilter(String regexPath) {
        Pattern pattern = Pattern.compile(regexPath);
        return (file, fileName) -> pattern.matcher(new File(fileName).getName()).matches();
    }

    public static File[] local(File dir, final String regexPath) { return dir.listFiles(filenameFilter(regexPath)); }

    public static File[] local(String path, final String regexPath) { return local(new File(path), regexPath); }

    public static class TreeInfo implements Iterable<File> {
        public final List<File> files = new ArrayList<>();
        public final List<File> dirs = new ArrayList<>();

        @Override
        public Iterator<File> iterator() { return files.iterator(); }

        public void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        @Override public String toString() { return "dirs: " + PPrint.format(dirs) + "\n\nfiles: " + PPrint.format(files); }
    }

    private static class PPrint {
        public static <T> String format(Collection<T> collection) {
            if (collection.size() == 0) {
                return "[]";
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (T element: collection) {
                if (collection.size() != 1) { stringBuilder.append("\n   "); }
                stringBuilder.append(element);
            }
            if (collection.size() != 1) { stringBuilder.append("\n"); }
            stringBuilder.append("]");
            return stringBuilder.toString();
        }

        public static <T> void print(Collection<T> collection) { System.out.print(format(collection)); }
        public static void print(Object[] objs) { System.out.print(format(Arrays.asList(objs))); }
    }

    public static TreeInfo walk(String pathname, String regexPath) {
        return recurseDirs(createFile(pathname), regexPath);
    }

    public static TreeInfo walk(File file, String regexPath, String... excludeSpecifiedDirs) {
        return recurseDirs(file, regexPath);
    }

    public static TreeInfo walk(File file) { return recurseDirs(file, ".*"); }

    public static TreeInfo walk(String pathname) { return recurseDirs(createFile(pathname), ".*"); }

    public static TreeInfo recurseDirs(File file, String regexPath) {
        File[] listFiles = file.listFiles();
        if (isNull(listFiles)) {
            return new TreeInfo();
        }
        return recurseDirs(Pattern.compile(regexPath), listFiles);
    }

    public static TreeInfo recurseDirs(Pattern pattern, File[] listFiles) {
        TreeInfo result = new TreeInfo();
        for (File f: listFiles) {
            if (isFind(pattern, f)) { continue; }

            if (f.isDirectory()) {
                File[] files = isNull(f.listFiles()) ? new File[0] : f.listFiles();

                assert files != null;
                // 当正则表达式模式排除指定文件时，如果某个文件夹的文件全部都排除时，那么文件夹也不应该添加进去！
                if (Arrays.stream(files).noneMatch(x -> isFind(pattern, x))) { result.dirs.add(f); }
                result.addAll(recurseDirs(pattern, files));
            } else { result.files.add(f); }
        }

        return result;
    }

    public static File createFile(String pathname) { return new File(pathname); }
    public static <T> boolean isNull(T val) { return !shouldNotNull(val); }
    public static <T> boolean shouldNotNull(T val) { return !Objects.isNull(val); }
    public static boolean isFind(Pattern pattern, File file) { return pattern.matcher(file.getName()).find(); }
    public static <T> boolean removeIf(Collection<T> collection, Predicate<T> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        if (collection.isEmpty()) {
            return removed;
        }
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }

    public static void main(String[] args) {
        System.out.println(
                walk(
                        ".",
                        "(?:\\.idea|out|logger)|(?:\\.iml|\\.txt|\\.(?:jpg|png|gif)|\\.html|\\.sql|\\.json|\\.properties|\\.dat|\\.policy|\\.mp3)$"
                )
        );

        Map<String, Integer> map = Map.ofEntries(Map.entry("a", 1), Map.entry("b", 2), Map.entry("c", 3));
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }

        List<Integer> removeTestList = new ArrayList<>(){{ add(1); add(2); add(3); }};
        System.out.println(removeIf(removeTestList, x -> x == 3));
        System.out.println(removeTestList);
    }
}

final class MyLinkedList<T> {
    private final String START_CHARACTER = "[ ";
    private final int START_POSITION_INDEX = 0;
    private final StringBuilder result = new StringBuilder(START_CHARACTER);

    private enum Position { FIRST, MIDDLE, LAST }

    final class Node<R extends T> implements Cloneable {
        public R item;
        public Node<R> next;
        public Node(R item, Node<R> next) {
            this.item = item;
            this.next = next;
        }

        @Override public Object clone() throws CloneNotSupportedException { return super.clone(); }
    }

    private Node<T> head;
    private Node<T> last;
    private int size;

    private class IterableClass implements Iterable<Node<T>> {
        private final Node<T> head;

        public IterableClass(Node<T> head) { this.head = head; }

        @Override public Iterator<Node<T>> iterator() {
            final Node<T> node = head;
            return new Iterator<>() {
                Node<T> pointer = node;

                @Override public boolean hasNext() { return !isEmptyLinkedList() && !isNull(pointer); }

                @Override public Node<T> next() {
                    if (pointer != head) { result.append(", "); }
                    Node<T> node = pointer;
                    pointer = pointer.next;
                    return node;
                }
            };
        }
    }

    public boolean add(int index, T item) {
        checkIndexIsValid(index);
        if (index == START_POSITION_INDEX) {
            return addFirst(item);
        }
        if (index == size) {
            return addLast(item);
        }
        return add(item, Position.MIDDLE, index);
    }

    public boolean addFirst(T item) { return add(item, Position.FIRST, 0); }
    public boolean addLast(T item) { return add(item, Position.LAST, size); }

    public boolean addAllFirst(Collection<? extends T> collection) { return addAll(0, collection); }
    public boolean addAllLast(Collection<? extends T> collection) { return addAll(size, collection); }
    public boolean addAll(int index, Collection<? extends T> collection) {  // O(n)
        checkIndexIsValid(index);

        if (collection.isEmpty()) {
            return false;
        }
        Object[] array = collection.toArray();

        if (index == size) {
            for (Object item: array) {
                if (isNull(item)) {
                    continue;
                }
                addLast((T) item);
            }
        } else {
            Consumer<T> consumer = index == 0 || size == 0 ? this::addFirst : val -> add(index, val);
            for (int i = array.length - 1; i >= 0; i--) {
                T item = (T) array[i];
                if (Objects.isNull(item)) {
                    continue;
                }
                consumer.accept(item);
            }
        }

        return true;
    }

    private boolean add(T item, Position position, int index) {
        checkIndexIsValid(index);

        switch (position) {
            case FIRST -> { // O(1)
                head = new Node<>(item, head);
                if (isNull(last)) { last = head; }
            }

            case MIDDLE -> { // O(index)
                int count = 0;
                for (Node<T> node = head; !isNull(node); node = node.next, count++) {
                    if (index - 1 == count) {
                        node.next = new Node<>(item, node.next);
                        break;
                    }
                }
            }

            case LAST -> { // O(1)
                Node<T> newNode = new Node<>(item, null);
                if (isNull(head)) {
                    head = last = newNode;
                } else {
                    last.next = newNode;
                    last = last.next;
                }
            }
        }

        size++;
        return true;
    }

    public Node<T> reverse() {
        try {
            return isNull(head) ? null : reverse(cloneLinkedList());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Node<T> reverse(Node<T> current) { // O(n)
        if (isNull(current.next)) { return current; }
        Node<T> next = current.next;
        Node<T> node = reverse(next);
        current.next = null;
        next.next = current;
        return node;
    }

    private Node<T> cloneLinkedList() throws CloneNotSupportedException {
        Node<T> newNode = (Node<T>) head.clone();
        Node<T> current = newNode;
        for (Node<T> node = current.next; !isNull(node); node = node.next) {
            current.next = (Node<T>) node.clone();
            current = current.next;
        }
        return newNode;
    }

    public T removeFirst() {    // O(1)
        checkRemoveLinkedListValid();

        T element = head.item;
        Node<T> next = head.next;
        head.item = null;
        head.next = null;
        head = next;
        if (isNull(next)) { last = null; }
        size--;
        return element;
    }

    public T removeLast() { // O(n)
        checkRemoveLinkedListValid();

        T element = last.item;

        Node<T> node = head;
        int index = 1;
        while (++index < size) {
            node = node.next;
        }

        last.item = null;
        last.next = null;

        if (isNull(node.next)) { head = last = null; }
        else {
            node.next = null;
            last = node;
        }

        size--;
        return element;
    }

    public boolean removeIf(Predicate<T> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        if (isEmpty()) { return removed; }

        int modCount = 0;
        Node<T> n = head;
        for (Node<T> node = head; !isNull(node);) {
            if (filter.test(node.item)) {
                modCount++;
                if (node == head) {
                    head = head.next;
                } else {
                    n.next = node.next;
                    node = node.next;
                    continue;
                }
            }
            n = node;
            node = node.next;
        }

        size -= modCount;
        return removed;
    }

    public boolean removeRange(int start, int end) {
        checkIndexIsValid(start);
        checkIndexIsValid(end);
        if (start > end) {
            throw new IndexOutOfBoundsException("The start position is greater than the end position.");
        }

        Node<T> startNode = findRemoveRangePosition(head, 0, start);
        Node<T> endNode = findRemoveRangePosition(start == 0 ? startNode : startNode.next, start, end);

        if (start == START_POSITION_INDEX) {
            Node<T> next = endNode.next;
            head.item = null;
            head.next = null;
            head = next;
        } else {
            startNode.next = endNode.next;
        }

        size -= end - start;

        return true;
    }

    private Node<T> findRemoveRangePosition(Node<T> startNode, int start, int end) {
        for (int i = start; i < end - 1; i++) { startNode = startNode.next; }
        return startNode;
    }

    public boolean remove(T val) {
        checkRemoveLinkedListValid();
        if (equals(head, val)) {
            Node<T> newHead = head.next;
            head.item = null;
            head.next = null;
            head = newHead;
            size--;
            return true;
        }

        Node<T> n = head, node = head.next;

        int index = 0;
        while (index < size) {
            if (equals(node, val)) {
                Node<T> next = node.next;
                node.item = null;
                node.next = null;
                n.next = next;
                break;
            }
            index++;
            n = node;
            node = node.next;
        }

        size--;

        return index != size;
    }

    private void checkRemoveLinkedListValid() {
        if (isEmpty()) {
            throw new NullPointerException("The linked list is empty and cannot be deleted.");
        }
    }

    public T getFirst() { return getElement(head); }
    public T getReverseLinkedListFirst() { return getElement(last); }

    public T getLast() { return getElement(last); }
    public T getReverseLinkedListLast() { return getElement(head); }

    private T getElement(Node<T> node) { return isNull(node) ? null : node.item; }

    public boolean contains(T val) {
        for (Node<T> node = head; !isNull(node); node = node.next) {
            if (equals(node, val)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (Node<T> node = head; !isNull(node);) {
            Node<T> next = node.next;
            node.item = null;
            node.next = null;
            node = next;
        }
        head = last = null;
        size = 0;
    }

    public T set(int index, T val) {
        checkIndexIsValid(index);
        int count = 0;
        for (Node<T> node = head; !isNull(node); node = node.next, count++) {
            if (count == index) {
                T oldVal = node.item;
                node.item = val;
                return oldVal;
            }
        }
        return null;
    }

    public boolean isEmpty() { return size == 0; }

    public int getSize() { return size; }

    private void checkIndexIsValid(int index) {
        if (index < START_POSITION_INDEX || index > size) {
            throw new IndexOutOfBoundsException("The index value is invalid!");
        }
    }

    private boolean equals(Node<T> node, T val) { return Objects.equals(node.item, val); }
    private boolean isNull(Object val) { return val == null; }
    private boolean isEmptyLinkedList() { return size == 0; }

    private String toString(Node<T> reverseLinkedListHead) { return linkedListToString(reverseLinkedListHead); }
    @Override public String toString() { return linkedListToString(head); }

    private String linkedListToString(Node<T> linkedListHead) {
        if (result.length() > START_CHARACTER.length()) { result.delete(START_CHARACTER.length(), result.length()); }
        for (Node<T> node: new IterableClass(linkedListHead)) { result.append(node.item); }
        result.append(" ]");
        return result.toString();
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.addLast(1);
        myLinkedList.addLast(2);
        myLinkedList.addLast(3);
        myLinkedList.addLast(4);
        myLinkedList.add(0, 5);
        myLinkedList.add(2, 10);
        myLinkedList.add(6, 20);

        myLinkedList.removeFirst();
        System.out.println("remove First: " + myLinkedList);

        myLinkedList.removeLast();
        System.out.println("Remove last: " + myLinkedList);

        myLinkedList.remove(2);
        System.out.println("Remove 2: " + myLinkedList);

        myLinkedList.remove(1);
        System.out.println("Remove 1: " + myLinkedList);

        myLinkedList.addAllLast(List.of(4, 5, 6, 7, 8));
        myLinkedList.addAllFirst(List.of(-4, -3, -2, -1, 0));
        myLinkedList.addAll(2, List.of(10, 20, 30, 40));

        System.out.println("Before Remove range: " + myLinkedList);
        myLinkedList.removeRange(6, 6);
        System.out.println("After Remove range: " + myLinkedList);

        System.out.println("current linkedList: " + myLinkedList);
        System.out.println("current reverse linkedList: " + myLinkedList.toString(myLinkedList.reverse()));

        System.out.println("LinedList first: " + myLinkedList.getFirst());
        System.out.println("LinedList last: " + myLinkedList.getLast());
        System.out.println("Reverse LinedList first: " + myLinkedList.getReverseLinkedListFirst());
        System.out.println("Reverse LinedList last: " + myLinkedList.getReverseLinkedListLast());
        System.out.println("size: " + myLinkedList.getSize());
        System.out.println("Is it empty? " + myLinkedList.isEmpty());

        myLinkedList.set(2, 100);
        System.out.println("After set linkedList: " + myLinkedList);
        System.out.println("After set reverse linkedList: " + myLinkedList.toString(myLinkedList.reverse()));

        System.out.println("Whether the linked list contains: " + myLinkedList.contains(0));
        System.out.println("Whether the linked list contains: " + myLinkedList.contains(-100));

        myLinkedList.removeIf(x -> x <= 0);
        System.out.println("After removeIf: " + myLinkedList);

        myLinkedList.clear();
        System.out.println("After clear linkedList: " + myLinkedList);
        System.out.println("After clear reverse linkedList: " + myLinkedList.toString(myLinkedList.reverse()));
        System.out.println("Is it empty? " + myLinkedList.isEmpty());
    }
}

class TestPropertyMap {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("width", "600");
        properties.setProperty("height", "200");
        try (var fileOutputStream = new FileOutputStream("program.properties")) {
            properties.store(fileOutputStream, "Program Properties");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (var fileInputStream = new FileInputStream("program.properties")) {
            properties.load(fileInputStream);
            String width = properties.getProperty("width");
            System.out.println(width);
        }
    }
}

class TestSystemFont {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 获取计算机上的可用字体的列表，getAvailableFontFamilyNames方法返回一个字符串数组
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        System.out.println("Is there MS UI Gothic font on your computer? " + Arrays.asList(fontNames).contains("MS UI Gothic"));

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int screenWidth = toolkit.getScreenSize().width;

        StringBuilder result = new StringBuilder("[ \n");
        Map<Character, List<String>> fontMap =
                Arrays.stream(fontNames)
                        .sorted()
                        .collect(Collectors.groupingBy(name -> name.charAt(0), LinkedHashMap::new, Collectors.toList()));

        // 获取字符宽度
        // 如果不在Graphics2D环境下，需要使用JComponent中的getFontMetrics方法，然后在调用getFontRenderContext来获取字体上下文(字体属性)
        Font font = new Font("JetBrains Mono", Font.PLAIN, 12);
        FontRenderContext fontRenderContext = new JComponent() {}.getFontMetrics(font).getFontRenderContext();
        final int DELIMITER_WIDTH = (int) font.getStringBounds(", ", fontRenderContext).getWidth();
        final int ALL_LEADING_WHITE_SPACE_CHARACTER_WIDTH = (int) font.getStringBounds(" ", fontRenderContext).getWidth() * 8;
        final int BOUNDARY_WIDTH = (int) (screenWidth * 0.7);
        final String FORMAT_CHARACTERS = "\n\t\t";

        int addTotalCount = 0;
        for (Map.Entry<Character, List<String>> entry: fontMap.entrySet()) {

            Character key = entry.getKey();
            List<String> list = entry.getValue();

            int totalWidth = 0;
            int addCount = 0;

            boolean isBoundary = false;
            String endCharacter = " ]";
            StringBuilder stringBuilder = new StringBuilder("[ ");

            final int OFFSET = stringBuilder.length();

            for (String name: list) {

                int itemWidth = (int) font.getStringBounds(name, fontRenderContext).getWidth() + ALL_LEADING_WHITE_SPACE_CHARACTER_WIDTH;

                if (addCount < list.size() - 1) { itemWidth += DELIMITER_WIDTH; }

                if (totalWidth + itemWidth > BOUNDARY_WIDTH) {
                    if (!isBoundary) {
                        stringBuilder.insert(OFFSET, FORMAT_CHARACTERS);
                        isBoundary = true;
                    }
                    stringBuilder.append(FORMAT_CHARACTERS);
                    endCharacter = "\n\t]" ;
                    totalWidth = 0;
                }

                stringBuilder.append(name);

                if (addCount < list.size() - 1) { stringBuilder.append(", "); }

                addCount++;
                totalWidth += itemWidth;
            }

            result.append("\t")
                    .append(key)
                    .append(": ")
                    .append(stringBuilder.append(endCharacter));

            if (addTotalCount < fontMap.size() - 1) { result.append("\n"); }

            addTotalCount++;
        }

        result.append(" \n]");
        System.out.println(result);
    }
}

class Binary {
    public static String toBinary(int num) {
        final int VALID_BIT = 1;

        Map<String, Object> info = recordInfo(num, VALID_BIT);
        int highestBitIndex = (int) info.get("highestBitIndex");
        int[] binaryArray = (int[]) info.get("binaryArray");

        return Arrays.stream(binaryArray, highestBitIndex, binaryArray.length)
                .collect(StringBuilder::new, StringBuilder::append, (r1, r2) -> {})
                .toString();
    }

    public static Map<String, Object> recordInfo(int num, int validBit) {
        int[] binaryArray = new int[32];

        if (num < 0) {
            num = -num;
            binaryArray[0] = validBit;
        }

        int highestCarryCount = calcCarryBitCount(num);
        int remainingNum = calcRemainingNum(num, highestCarryCount);

        setValidBit(binaryArray, highestCarryCount, validBit);

        int currentRemainingNum = remainingNum;
        while (currentRemainingNum > 0) {
            int carryCount = calcCarryBitCount(currentRemainingNum);
            setValidBit(binaryArray, carryCount, validBit);
            currentRemainingNum = calcRemainingNum(currentRemainingNum, carryCount);
        }

        return new HashMap<>() {{ put("highestBitIndex", binaryArray.length - highestCarryCount - 1); put("binaryArray", binaryArray); }};
    }

    public static int calcRemainingNum(int val, int carryCount) { return val - (int) Math.pow(2, carryCount); }
    public static int calcCarryBitCount(int val) { return (int) Math.floor(Math.log(val) / Math.log(2)); }
    public static void setValidBit(int[] binaryArray, int carryCount, int validBit) { binaryArray[binaryArray.length - carryCount - 1] = validBit; }

    public static void main(String[] args) { System.out.println(toBinary(1234)); }
}

class ButtonFrame extends JFrame {
    private final JPanel panel = new JPanel();

    class ColorAction implements ActionListener {
        private final Color backGroundColor;
        public ColorAction(Color backGroundColor) { this.backGroundColor = backGroundColor; }
        @Override public void actionPerformed(ActionEvent actionEvent) { panel.setBackground(backGroundColor); }
    }

    public ButtonFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = dimension.width >>> 1;
        int height = dimension.height >>> 1;

        setIconImage(toolkit.getImage("icon.jpeg"));
        setSize(new Dimension(width, height));
        setLocation(new Point((dimension.width - width) >>> 1, (dimension.height - height) >>> 1));

        String[] texts = new String[] { "Blue", "Yellow", "Red" };
        Color[] colors = new Color[] { Color.BLUE, Color.YELLOW, Color.RED };

        for (int i = 0; i < texts.length; i++) {
            JButton button = new JButton(texts[i]);
            button.addActionListener(new ColorAction(colors[i]));
            panel.add(button);
        }

        add(panel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ButtonFrame buttonFrame = new ButtonFrame();
            buttonFrame.setTitle("Button setting background color example");
            buttonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            buttonFrame.setVisible(true);
        });
    }
}

// 寻找每个文件中包含的指定关键字
class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (var in = new Scanner(System.in)) {
            String directory = readLine(in, "Enter Base directory: ");
            String keyword = readLine(in, "Enter keyword: ");

            Runnable searcher = () -> {
                try { while (!queue.isEmpty()) { search(queue.take(), keyword); } }
                catch (IOException | InterruptedException e) { e.printStackTrace(); }
            };

            File[] listFiles = new File(directory).listFiles();
            if (!Objects.isNull(listFiles) && listFiles.length > 0) {
                startThread(() -> {
                    try { enumerator(listFiles); }
                    catch (IOException | InterruptedException e) { e.printStackTrace(); }
                });
                IntStream.range(0, SEARCH_THREADS).forEach(x -> startThread(searcher));
            } else { System.out.println("The file containing the keyword cannot be found. There are no directories or files to search in this directory."); }
        }
    }

    public static void enumerator(File[] files) throws IOException, InterruptedException {
        for (File file: files) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (!Objects.isNull(listFiles) && listFiles.length > 0) { enumerator(listFiles); }
            }
            else { queue.put(file.toPath()); }
        }
    }

    public static void search(Path file, String keyword) throws IOException, InterruptedException {
        try (var in = new Scanner(file, StandardCharsets.UTF_8)) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword)) { System.out.printf("%s: %d: %s%n", file, lineNumber, line); }
            }
        }
    }

    public static String readLine(Scanner in, String promptInfo) {
        System.out.println(promptInfo);
        return in.nextLine();
    }

    public static void startThread(Runnable runnable) { new Thread(runnable).start(); }
}

// Fork-join框架
// 工作原理是递归的为每一个线程分配指定数量的任务，然后返回结果的时候会依次进行合并结果，最后返回，和归并排序一个道理
// Fork-join线程的负载均衡是对异步(非阻塞)任务有效，对下面的例子来说，效率会差一些！
// RecursiveTask<T>接口返回T，RecursiveAction无返回值，这两个接口都必须实现compute方法
// compute方法的修饰符是protected，在实现的时候最好也是protected，因为这个方法不想被外界调用(在单独包的时候)
class ForkJoinTest {
    private static class Counter extends RecursiveTask<Integer> {
        public static final int THRESHOLD = 1000;
        private final double[] values;
        private final int from;
        private final int to;
        private final DoublePredicate filter;

        public Counter(double[] values, int from, int to, DoublePredicate filter) {
            this.values = values;
            this.from = from;
            this.to = to;
            this.filter = filter;
        }

        @Override protected Integer compute() {
            if (to - from < THRESHOLD) {
                int count = 0;
                for (int i = from; i < to; i++) { if (filter.test(values[i])) { count++; } }
                return count;
            } else {
                int mid = (from + to) / 2;
                Counter first = new Counter(values, from, mid, filter);
                Counter second = new Counter(values, mid, to, filter);
                invokeAll(first, second);
                return first.join() + second.join();
            }
        }
    }

    public static void main(String[] args) {
        final int SIZE = 10_000_000;
        double[] numbers = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = Math.random();
        }
        Counter counter = new Counter(numbers, 0, numbers.length, x -> x > 0.5);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(counter);
        System.out.println(counter.join());
    }
}

class CompletableFutureDemo {
    private static final Pattern IMG_PATTERN = Pattern.compile("<\\s*[iI][mM][gG]\\s*[^>]*[sS][rR][cC]\\s*=\\s*['\"]([^'\"]*)['\"][^>]*>");
    private static final String OUT_DIRECTORY = System.getProperty("user.dir") + "/completedFuture_images";
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private URL urlToProcess;

    public CompletableFuture<String> readPage(URL url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String contents = new String(url.openStream().readAllBytes(), StandardCharsets.UTF_8);
                System.out.println("Read page from " + url);
                return contents;
            } catch (IOException e) { throw new UncheckedIOException(e); }
        }, executorService);
    }

    public List<URL> getImageUrls(String webpage) {
        try {
            List<URL> result = new ArrayList<>();
            Matcher matcher = IMG_PATTERN.matcher(webpage);
            while (matcher.find()) { result.add(new URL(urlToProcess, matcher.group(1))); }
            System.out.println("Found URLs: " + result);
            return result;
        } catch (IOException e) { throw new UncheckedIOException(e); }
    }

    public CompletableFuture<List<BufferedImage>> getImages(List<URL> urls) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                List<BufferedImage> result = new ArrayList<>();
                for (URL url: urls) {
                    // ImageIO.read方法必须要使用https协议，http协议不能读取，会返回null！
                    result.add(ImageIO.read(url));
                    System.out.println("Loaded " + url);
                }
                return result;
            } catch (IOException e) { throw new UncheckedIOException(e); }
        }, executorService);
    }

    public void saveImages(List<BufferedImage> images) {
        System.out.println("Saving " + images.size() + " images");
        try {
            for (int i = 0; i < images.size(); i++) {
                ImageIO.write(
                        images.get(i),
                        "PNG",
                        new File(OUT_DIRECTORY, "/image" + (i + 1) + ".png")
                );
            }
        } catch (IOException e) { throw new UncheckedIOException(e); }
        executorService.shutdown();
    }

    public void run(URL url) throws IOException {
        urlToProcess = url;

        if (tryDeleteFiles()) {
            CompletableFuture.completedFuture(url)
                    .thenComposeAsync(this::readPage, executorService)
                    .thenApply(this::getImageUrls)
                    .thenCompose(this::getImages)
                    .thenAccept(this::saveImages);
        }
    }

    private boolean tryDeleteFiles() throws IOException { return tryDeleteFiles(createDirectoryIfNonExistent(OUT_DIRECTORY), true); }

    private boolean tryDeleteFiles(File file, boolean isFirst) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f: files) {
                    boolean isSuccess = tryDeleteFiles(f, false);
                    if (!isSuccess) { return false; }
                }
            }
        }
        return isFirst || file.delete();
    }

    private File createDirectoryIfNonExistent(String path) throws IOException {
        File file = new File(path);
        if (!hasDirectory(file)) {
            Files.createDirectory(Path.of(path));
        }
        return file;
    }

    private boolean hasDirectory(File file) { return file.exists(); }

    public static void main(String[] args) throws IOException {
        new CompletableFutureDemo().run(new URL("https://horstmann.com/index.html"));
    }
}

class IOTest {
    public static void main(String[] args) throws IOException {
//        创建读取缓冲流的两种方式，第一种InputStreamReader是一个适配器，接受一个InputStream流！第二种是直接使用字符读取流！
//        new BufferedReader(new InputStreamReader(new FileInputStream("")));
//        new BufferedReader(new FileReader(""));

        System.out.println("请输入一段单词: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String result = bufferedReader.readLine()/* readLine方法会抛出IOException异常 */.toUpperCase();

        // 将System.out转换成PrintWriter！System.out和System.err都是printStream(OutputStream)
        PrintWriter printWriter = new PrintWriter(System.out, true/* 必须设置true，不然不会输出 */);
        printWriter.println(result);
    }
}

// 使用ByteBuffer对字符串相邻字符颠倒
// ByteBuffer.get()会移动position指针，mark()方法会记住上次的position指针的位置，put()也会移动position指针，reset()会使得position指针从新指向ByteBuffer开头(index = 0)
// 对ByteBuffer操作只能是在ByteBuffer视图上进行操作(asxxx等方法，例如下面的asCharBuffer())
// allocate()是对ByteBuffer分配一个空间
class UsingBuffers {
    private static void symmetricScramble(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer buffer = ByteBuffer.allocate(data.length << 1);
        CharBuffer charBuffer = buffer.asCharBuffer();
        charBuffer.put(data);

        System.out.print(charBuffer.rewind());
        symmetricScramble(charBuffer);
        System.out.print("\t" + charBuffer.rewind());
        symmetricScramble(charBuffer);
        System.out.print("\t" + charBuffer.rewind());
    }
}

class LockingMappedFiles {
    private static final int LENGTH = 0x8FFFFFF;
    private static FileChannel fileChannel;
    private static final int ALLOC_THREADS = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService pool = Executors.newFixedThreadPool(ALLOC_THREADS);
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("test.txt", "rw");
        randomAccessFile.setLength(0);

        fileChannel = randomAccessFile.getChannel();
        pool.execute(LockingMappedFiles::runBatchTask);
    }

    private static void runBatchTask() {
        try {
            MappedByteBuffer mappedByteBuffer = pool.submit(LockingMappedFiles::initMappedByteBuffer).get();
            int start = 0;
            for (int end = LENGTH / ALLOC_THREADS; end <= LENGTH; start += end, end += start) { runTask(mappedByteBuffer, start, end); }
            runTask(mappedByteBuffer, start, LENGTH);
        }
        catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
        finally { pool.shutdown(); }
    }

    private static MappedByteBuffer initMappedByteBuffer() throws IOException {
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) { mappedByteBuffer.put((byte) (97 + random.nextInt(25))); }
        return mappedByteBuffer;
    }

    private static void runTask(MappedByteBuffer mappedByteBuffer, int start, int end) { pool.execute(new LockAndModify(mappedByteBuffer, start, end)); }

    private static class LockAndModify implements Runnable {
        private final ByteBuffer buffer;
        private final int start;
        private final int end;
        public LockAndModify(ByteBuffer byteBuffer, int start, int end) {
            this.start = start;
            this.end = end;
            buffer = byteBuffer.limit(end).position(start).slice();
        }

        @Override public void run() {
            try {
                FileLock fileLock = fileChannel.lock(start, end, false);
                System.out.println("Locked: " + start + " to " + end);
                // 对加锁的部分字节进行内容修改，如果不需要修改的话，则不需要对每一部分的buffer进行加锁
                while (buffer.position() < buffer.limit() - 1) {
                    byte origin = (byte) (buffer.get() - 49);
                    // 如果ascii码是48 ~ 57(字符是0 - 9)的话，则对这部分区域的buffer进行修改
                    if (origin >= 48 && origin <= 57) { buffer.put(origin); }
                }
                fileLock.release();
                System.out.println("Released: " + start + " to " + end);
            } catch (IOException e) { throw new RuntimeException(e); }
        }
    }
}

// 将txt文件压缩成GZIP文件，如果这个被压缩的文件中含有中文的话，则需要设置字符编码
class GZIPCompress {
    public static void main(String[] args) throws IOException {
        // 解决中文乱码使用StandardCharsets.ISO_8859_1字符集
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("original.txt"), StandardCharsets.ISO_8859_1));
        BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("testGIP.gz")));
        System.out.println("Writing file...");

        int c;
        while ((c = in.read()) != -1) { out.write(c); }
        in.close();
        out.close();
        System.out.println("Write complete!");

        System.out.println("Reading file...");
        BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("testGIP.gz"))));

        String s;
        while ((s = in2.readLine()) != null) { System.out.println(s); }
        System.out.println("Read complete!");
        in2.close();
    }
}

class Data implements Serializable {
    private int n;
    public Data(int n) { this.n = n; }
    @Override public String toString() { return Integer.toString(n); }
}

class Worm implements Serializable {
    private final static Random random = new Random(47);
    private static final Data[] d = IntStream.range(0, 3).mapToObj(x -> new Data(random.nextInt(10))).toList().toArray(new Data[0]);
    private Worm next;
    private final char c;

    public Worm(int i, char x) {
        c = x;
        if (--i > 0) { next = new Worm(i, (char) (x + 1)); }
    }

    @Override public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c).append("(");
        for (Data dat: d) { result.append(dat); }
        result.append(")");
        // append方法会调用String.valueOf方法，String.valueOf方法又会调用obj.toString()(这里是next.toString())
        // 所以，一旦使用System.out.println(new Worm(6, 'a'))方法时，首先执行Worm类的toString()方法，在遇到下面的语句时，由于next保存了递归后的Worm类
        // 所以，这里会触发new Worm(6, 'a').next的新的Worm类，再然后又toString()方法，直至到next = null停止
        // 所以，才会输出:a(853):b(119):c(802):d(788):e(199):f(881)这样的奇怪的字符串
        if (next != null) { result.append(next); }
        return result.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Worm worm = new Worm(6, 'a');
        System.out.println("worm = " + worm);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("worm.out"));
        out.writeObject("Worm storage\n");
        out.writeObject(worm);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm.out"));
        String s = (String) in.readObject();    // 读取第一行写入的内容("Worm storage\n")
        Worm w = (Worm) in.readObject();
        System.out.println(s + "w = " + w);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bout);
        out2.writeObject("Worm storage\n");
        out2.writeObject(worm);
        out2.flush();

        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        s = (String) in2.readObject();
        Worm w2 = (Worm) in2.readObject();
        System.out.println(s + "w2 = " + w2);
    }
}

// 使用Scanner写入文件，写入文件的方法是println，而不是write()方法！
class TextFileTest {
    public static void main(String[] args) throws IOException {
        Employee[] employees = new Employee[3];
        employees[0] = new Employee("Carl Cracker", 75000.00, 1978, 12, 15);
        employees[1] = new Employee("Harry Hacker", 50000.00, 1989, 10, 1);
        employees[2] = new Employee("Tony Tester", 40000.00, 1990, 3, 15);

        try (PrintWriter out = new PrintWriter("employee.dat", StandardCharsets.UTF_8)) { writeData(employees, out); }
        try (Scanner in = new Scanner(new FileInputStream("employee.dat"), "UTF-8")) {
            Arrays.stream(readData(in)).forEach(System.out::println);
        }
    }

    private static void writeData(Employee[] employees, PrintWriter out) {
        out.println(employees.length);
        Arrays.stream(employees).forEach(employee -> writeEmployee(employee, out));
    }

    private static void writeEmployee(Employee employee, PrintWriter out) {
        out.println(employee.getName() + "|" + employee.getSalary() + "|" + employee.getHireDay());
    }

    private static Employee[] readData(Scanner in) {
        int n = in.nextInt();
        in.nextLine();
        return IntStream.range(0, n).collect(() -> new Employee[n], fillEmployee(in), (r1, r2) -> {});
    }

    private static ObjIntConsumer<Employee[]> fillEmployee(Scanner in) {
        return (employees, i) -> employees[i] = readEmployee(in);
    }

    private static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        LocalDate hireDate = LocalDate.parse(tokens[2]);
        return new Employee(
                tokens[0],
                Double.parseDouble(tokens[1]),
                hireDate.getYear(),
                hireDate.getMonthValue(),
                hireDate.getDayOfMonth()
        );
    }
}

// 使用Files.walkFileTree()方法清空目录下的所有文件和子目录(最后可以删除这个根目录(:可选))
class TestWalkFileTreeMethod {
    public static void main(String[] args) throws IOException {
        deleteAllFile(copy(joinPath("D:", "testWalkFileTreeMethod"), joinPath("/testWalkFileTreeMethod")).toString());
    }

    private static Path copy(String fromPath, String toPath) throws IOException {
        return copy(
                fromPath,
                toPath,
                rootDir -> System.out.println("Root directory " + rootDir.getFileName() + " copied successfully")
        );
    }

    private static Path copy(String fromPath, String toPath, Consumer<Path> consumer) throws IOException {
        Path from = Paths.get(fromPath);
        if (!Files.isDirectory(from)) { return from; }

        Path to = Paths.get(toPath);
        if (!Files.exists(to)) { Files.createDirectory(to); }

        int childDir = 0;
        final byte ROOT_DIR = 1;

        List<Path> fileList = Files.list(from).toList();
        for (Path path: fileList) {
            String originPath = path.toString();
            String destPath = joinPath(toPath, path.getFileName().toString());
            if (Files.isDirectory(path)) {
                childDir++;
                copy(originPath, destPath, consumer);
            } else {
                copyFile(originPath, destPath);
            }
        }

        if (childDir == ROOT_DIR) { consumer.accept(from); }
        return to;
    }

    private static String joinPath(String... paths) {
        StringBuilder resultPath = new StringBuilder();
        String FILE_SEPARATOR = File.separator;

        int i = 0;
        if (paths[0].startsWith("/")) {
            // 对 \ 和 $ 字符，Java会表示出不同的含义，需要使用quoteReplacement方法转义，然后进行替换
            resultPath
                    .append(System.getProperty("user.dir"))
                    .append(paths[0].replaceAll("/", Matcher.quoteReplacement(FILE_SEPARATOR)));
            i++;
        }

        for (; i < paths.length; i++) {
            resultPath.append(paths[i]);
            if (i < paths.length - 1) { resultPath.append(FILE_SEPARATOR); }
        }

        return resultPath.toString();
    }

    private static void copyFile(String fromPath, String toPath) throws IOException {
        try (
                BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(Paths.get(fromPath)));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(toPath)))
        ) { bufferedOutputStream.write(bufferedInputStream.readAllBytes()); }
    }

    private static void deleteAllFile(String path) throws IOException {
        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<>() {
            @Override public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) {
                System.out.println("已找到目录：\"" + path.getFileName() + "\"！");
                return FileVisitResult.CONTINUE;
            }

            @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
                printf("找到文件", file.getFileName(), "，注意：这个文件即将删除！");
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override public FileVisitResult visitFileFailed(Path file, IOException exception) {
                printf("哎呀，", file.getFileName(), "文件不能删除！");
                return FileVisitResult.CONTINUE;
            }

            @Override public FileVisitResult postVisitDirectory(Path dir, IOException exception) throws IOException {
                if (exception != null) { throw exception; }
                printf("目录", dir.getFileName(), "的内容都已清空，", "注意：这个目录即将被删除！");
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void printf(Object... formatCharSequence) {
        String format =
                IntStream.range(0, formatCharSequence.length - 1)
                        .collect(StringBuilder::new, (result, current) -> result.append("%s"), (r1, r2) -> {})
                        .toString();

        System.out.printf(format + "\033[5;31;2m %s \033[0m", formatCharSequence);
        System.out.println();
    }
}

// 匹配某个url地址网页中的所有a标签的链接
class HrefMatch {
    public static void main(String[] args) {
        try {
            Function<Matcher, List<String>> resultList = matcher -> {
                List<String> result = new ArrayList<>();
                while (matcher.find()) { result.add(matcher.group(1)); }
                return result;
            };
            Matcher matcher = Pattern
                    .compile("(?i:<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>)", Pattern.CASE_INSENSITIVE)
                    .matcher(new String(new URL("http://openjdk.java.net").openStream().readAllBytes(), StandardCharsets.UTF_8));
            resultList.apply(matcher).forEach(System.out::println);
//            如果是java9的话，可以使用Matcher的results方法，会返回所有匹配结果流
//            matcher.results().map(x -> x.group(1)).forEach(System.out::println);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

class GeneratorHashCode {
    private final int a;
    private final int b = 2;
    private final List<Integer> list = new ArrayList<>() {{ add(1); add(2); add(3); add(null); }};
    private final String c = "Hello, World";

    public GeneratorHashCode() { this(1); }
    public GeneratorHashCode(int a) { this.a = a; }

    @Override public boolean equals(Object other) {
        if (other == this) { return true; }
        if (!(other instanceof GeneratorHashCode)) { return false; }
        GeneratorHashCode otherGeneratorHashCode = (GeneratorHashCode) other;

        return a == otherGeneratorHashCode.a &&
                b == otherGeneratorHashCode.b &&
                Objects.equals(list, otherGeneratorHashCode.list) &&
                Objects.equals(c, otherGeneratorHashCode.c);
    }

    @Override public int hashCode() {
        int result = c.hashCode();
        result = 31 * result + Integer.hashCode(a);
        result = 31 * result + Integer.hashCode(b);
        result = 31 * result + list.hashCode();
        for (Integer integer: list) { result = 31 * result + (integer == null ? 0 : Integer.hashCode(integer)); }
        return result;
    }

    public static void main(String[] args) {
        GeneratorHashCode generatorHashCode1 = new GeneratorHashCode();
        GeneratorHashCode generatorHashCode2 = new GeneratorHashCode(2);

        // 调用覆写equals方法来比较是否是同一个对象
        System.out.println("equals是否相等？" + generatorHashCode1.equals(generatorHashCode2));

        // 使用HashMap来测试当put一个GeneratorHashCode对象，然后使用get(new GeneratorHashCode())进行查询，是否能找到这个key
        Map<GeneratorHashCode, String> map = new HashMap<>();
        map.put(generatorHashCode1, "我是GeneratorHashCode1类");
        map.put(generatorHashCode2, "我是GeneratorHashCode2类");

        System.out.println("在HashMap中是否能找到GeneratorHashCode1类？" + map.containsKey(new GeneratorHashCode()));
        System.out.println("在HashMap中key为GeneratorHashCode1类对应的值是：" + map.get(new GeneratorHashCode()));

        System.out.println("在HashMap中是否能找到GeneratorHashCode1类？" + map.containsKey(new GeneratorHashCode(3)));
        System.out.println("在HashMap中key为GeneratorHashCode1类对应的值是：" + map.get(new GeneratorHashCode(3)));

        List<? super Number> list = new ArrayList<>();
        list.add(1);
        list.add(1.2f);
        list.forEach(System.out::println);
    }
}

/**
 * 解析XML文档，提取属性id部分，使得生成由每一个包含id属性的标签所生成的链表结构
 * 例：
 * <entry id="test">
 *  <font id="font">
 *    <name id="person">Tom</name>
 *    <size id="age">31</size>
 *  </font>
 * </entry>
 * 结果：[ test: [ font: [ person: [ name = Tom ] ][ age: [ size = 31 ] ] ] ]
 */
class TestXML {
    interface FormatterAssist<K, V> {
        int FIRST_RECURSION = 0;

        Map<K, ? extends V> getCurrentMap();
        String getResultAndToString();

        int getRecursionCount();
        int getCurrentMapSize();
        int getMapLastIndex();

        void incrementRecursionCount();
        void incrementCurrentMapSize();
        void appendContent(Object content);
        void appendContent(Object... contents);
        void setCurrentMap(Map<K, ? extends V> childMap);
        void decrementRecursionCount();
        void appendCloseSymbol();
    }

    enum Symbol {
        OPEN_SYMBOL("[ "), CLOSE_SYMBOL(" ]"), COLON_SEPARATOR(": "), SEMICOLON_SEPARATOR("; "), NEWLINE_CHARACTER("\n"), MAP_SEPARATOR(" = ");
        private final String symbol;
        Symbol(String symbol) { this.symbol = symbol; }
        public String get() { return symbol; }
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        parse("config.xml", element -> System.out.println(parseConfig(element)));
        parse("test.xml", element -> System.out.println(formatMapFromXML(collectIDAttrFromXML(element))));
        generatorDTDFile("./readXML/write.xml", generateXMLDocument("write.xml"));
    }

    private static Transformer generateXMLDocument(String filename) throws ParserConfigurationException, TransformerException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Map<String, List<String>> record = Map.ofEntries(
                Map.entry("test", new ArrayList<>(){{ add("Tom"); add("31"); }}),
                Map.entry("title", new ArrayList<>(){{ add("Helvetica"); add("36"); }})
        );

        Function<String, Element> createElement = document::createElement;
        BiConsumer<Element, Element> appendChild = Node::appendChild;

        Element rootElement = createElement.apply("config");
        DocumentFragment documentFragment = document.createDocumentFragment();
        for (Map.Entry<String, List<String>> entry: record.entrySet()) {
            List<String> values = entry.getValue();

            Element entryElement = createElement.apply("entry");
            Element fontElement = createElement.apply("font");
            Element nameElement = createElement.apply("name");
            Element sizeElement = createElement.apply("size");

            Element fontElement1 = createElement.apply("font");
            Element nameElement1 = createElement.apply("name");
            Element sizeElement1 = createElement.apply("size");

            entryElement.setAttribute("id", entry.getKey());
            entryElement.setAttribute("value", "entry");

            nameElement1.setTextContent("lala");
            sizeElement1.setTextContent("90");
            nameElement.setTextContent(values.get(0));
            sizeElement.setTextContent(values.get(1));
            sizeElement.setAttribute("id", "999");

            appendChild.accept(fontElement1, nameElement1);
            appendChild.accept(fontElement1, sizeElement1);
            appendChild.accept(entryElement, fontElement1);

            appendChild.accept(fontElement, nameElement);
            appendChild.accept(fontElement, sizeElement);
            appendChild.accept(entryElement, fontElement);
            documentFragment.appendChild(entryElement);
        }

        Element entryElement1 = createElement.apply("entry");
        Element fontElement1 = createElement.apply("font");
        Element nameElement1 = createElement.apply("name");
        Element sizeElement1 = createElement.apply("size");

        nameElement1.setTextContent("lala");
        sizeElement1.setTextContent("90");

        appendChild.accept(fontElement1, nameElement1);
        appendChild.accept(fontElement1, sizeElement1);
        appendChild.accept(entryElement1, fontElement1);

        documentFragment.appendChild(entryElement1);

        Element note = createElement.apply("note");
        Element to = createElement.apply("to");
        Element from = createElement.apply("from");
        Element heading = createElement.apply("heading");
        Element body = createElement.apply("body");
        Element koko = createElement.apply("koko");

        to.setTextContent("George");
        from.setTextContent("John");
        heading.setTextContent("Reminder");
        body.setTextContent("Don't forget the meeting!");

        appendChild.accept(note, to);
        appendChild.accept(note, from);
        appendChild.accept(note, heading);
        appendChild.accept(note, body);
        appendChild.accept(note, koko);

        documentFragment.appendChild(note);
        rootElement.appendChild(documentFragment);
        document.appendChild(rootElement);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(Files.newOutputStream(Paths.get("./readXML", filename))));

        return transformer;
    }

    private static void generatorDTDFile(String path, Transformer transformer)
            throws IOException, ParserConfigurationException, SAXException, TransformerException
    {
        String newFilename = path.split("/")[2].split("\\.")[0] + "DTD" + ".dtd";

        try (
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(newFilename));
        ) {
            Element document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in).getDocumentElement();
            Pattern pattern = Pattern.compile("\\p{Space}+");
            LinkedList<Node> queue = new LinkedList<>() {{ add(document); }};
            Set<String> resultSet = new LinkedHashSet<>();

            String prev = "";
            while (!queue.isEmpty()) {
                Node element = queue.removeFirst();
                StringBuilder tagNameBuilder = new StringBuilder();
                String elementName = element.getNodeName();
                NodeList nodeList = element.getChildNodes();

                if (element.hasChildNodes()) {
                    tagNameBuilder.append("<!ELEMENT ").append(elementName);

                    List<String> tagNames = new ArrayList<>();
                    Map<String, Integer> filterRepeatTagName = new HashMap<>();
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node child = nodeList.item(i);
                        String value = child.getNodeValue();
                        if (value != null && pattern.matcher(value).matches()) { continue; }
                        String childName = child.getNodeName();
                        queue.addLast(child);
                        tagNames.add(childName);
                        filterRepeatTagName.computeIfPresent(childName, (key, val) -> val + 1);
                        filterRepeatTagName.putIfAbsent(childName, 1);
                    }

                    if (prev.equals(elementName)) { continue; }

                    boolean isValidText;
                    if (!(isValidText = tagNames.contains("#text"))) {
                        tagNameBuilder.append(" (");

                        Collection<String> collectionTagNames =
                                nodeList.getLength() > filterRepeatTagName.size() ?
                                        filterRepeatTagName.keySet() :
                                        tagNames;

                        int index = 0;
                        for (String tagName: collectionTagNames) {
                            boolean isAppendPlus = false;
                            Integer count = filterRepeatTagName.get(tagName);
                            if (count != null && count > 1) { isAppendPlus = true; }
                            collectTagName(tagNameBuilder, tagName, isAppendPlus);
                            if (index++ < collectionTagNames.size() - 1) { tagNameBuilder.append(","); }
                        }

                        tagNameBuilder.append(")");
                    }

                    if (isValidText) { tagNameBuilder.append(" (#PCDATA)"); }
                    tagNameBuilder.append(">").append(Symbol.NEWLINE_CHARACTER.get());
                } else {
                    if (element instanceof Element) {
                        tagNameBuilder
                                .append("<!ELEMENT ")
                                .append(elementName)
                                .append(" EMPTY")
                                .append(">")
                                .append(Symbol.NEWLINE_CHARACTER.get());
                    }
                }

                NamedNodeMap attributes = element.getAttributes();
                StringBuilder attributeBuilder = new StringBuilder();
                if (element instanceof Element && attributes.getLength() > 0) {
                    for (int i = 0; i < attributes.getLength(); i++) {
                        attributeBuilder
                                .append("<!ATTLIST ")
                                .append(elementName)
                                .append(" ")
                                .append(attributes.item(i).getNodeName())
                                .append(" CDATA ")
                                .append("#IMPLIED")
                                .append(">")
                                .append(Symbol.NEWLINE_CHARACTER.get());
                    }
                }

                addElementDTDRules(resultSet, tagNameBuilder, attributeBuilder);
                prev = elementName;
            }

            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, newFilename);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            try (BufferedOutputStream reWriteDocument = new BufferedOutputStream(new FileOutputStream(path))) {
                transformer.transform(new DOMSource(document), new StreamResult(reWriteDocument));
            }

            bufferedOutputStream.write(
                    resultSet.stream().collect(StringBuilder::new, StringBuilder::append, (r1, r2) -> {}).toString().getBytes(StandardCharsets.UTF_8)
            );
        }
    }

    private static void addElementDTDRules(Set<String> set, StringBuilder... builders) {
        Arrays.stream(builders).filter(builder -> builder.length() > 0).map(StringBuilder::toString).forEach(set::add);
    }

    private static void collectTagName(StringBuilder builder, String tagName, boolean isAppendPlus) {
        builder.append(tagName);
        if (isAppendPlus) { builder.append("+"); }
    }

    private static void parse(String fileName, Consumer<Element> func) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true); // 该工厂生成的所有文档生成器都将根据DTD来验证它们的输入。

        // 生成器不报告文本节点中的空白字符(默认标签和标签之间会有空白字符，类似于\n     )
        if (!documentBuilderFactory.isIgnoringElementContentWhitespace()) { documentBuilderFactory.setIgnoringElementContentWhitespace(true); }

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        try (InputStream in = Files.newInputStream(Paths.get(System.getProperty("user.dir"), "readXML", fileName))) {
            func.accept(documentBuilder.parse(in).getDocumentElement());
        }
    }

    private static Map<String, Object> parseConfig(Element e) {
        Map<String, Object> result = new HashMap<>();
        NodeList children = e.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Element child = (Element) children.item(i);
            String name = child.getAttribute("id");
            try {
                Object value = parseObject((Element) child.getFirstChild());
                result.put(name, value);
            } catch (ReflectiveOperationException ex) { ex.printStackTrace(); }
        }
        return result;
    }

    private static Object parseObject(Element e) throws ReflectiveOperationException {
        return ParseFactory
                .getParser(e)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Parser"))
                .parse(e);
    }

    interface Parser {
        Object parse(Element e) throws ReflectiveOperationException;
        static Object[] parseArgs(NodeList elements) throws ReflectiveOperationException {
            Object[] result = new Object[elements.getLength()];
            for (int i = 0; i < elements.getLength(); i++) {
                result[i] = parseObject((Element) elements.item(i));
            }
            return result;
        }

        static Class<?>[] getParameterTypes(Object[] args) {
            Class<?>[] result = new Class<?>[args.length];
            Map<Class<?>, Class<?>> toPrimitive = Map.of(Integer.class, int.class, Boolean.class, boolean.class);
            for (int i = 0; i < result.length; i++) {
                Class<?> cls = args[i].getClass();
                result[i] = toPrimitive.get(cls);
                if (result[i] == null) { result[i] = cls; }
            }
            return result;
        }
    }
    private static class ParseMethod implements Parser {
        @Override public Object parse(Element e) throws ReflectiveOperationException {
            String className = e.getAttribute("class");
            String methodName = e.getAttribute("method");
            Object[] args = Parser.parseArgs(e.getChildNodes());
            Class<?>[] parameterTypes = Parser.getParameterTypes(args);
            Method method = Class.forName(className).getMethod(methodName, parameterTypes);
            return method.invoke(null, args);
        }
    }
    private static class ParseConstruct implements Parser {
        @Override public Object parse(Element e) throws ReflectiveOperationException {
            String className = e.getAttribute("class");
            Object[] args = Parser.parseArgs(e.getChildNodes());
            Class<?>[] parameterTypes = Parser.getParameterTypes(args);
            Constructor<?> constructor = Class.forName(className).getConstructor(parameterTypes);
            return constructor.newInstance(args);
        }
    }

    private static class ParseText implements Parser { @Override public Object parse(Element e) { return ((CharacterData) e.getFirstChild()).getData(); } }
    private static class ParseInt implements Parser {
        @Override public Object parse(Element e) { return Integer.valueOf(((CharacterData) e.getFirstChild()).getData()); }
    }
    private static class ParseBoolean implements Parser {
        @Override public Object parse(Element e) { return Boolean.valueOf(((CharacterData) e.getFirstChild()).getData()); }
    }

    private static class ParseFactory {
        private static final Map<String, Parser> factory = Map.ofEntries(
                Map.entry("factory", new ParseMethod()),
                Map.entry("construct", new ParseConstruct()),
                Map.entry("int", new ParseInt()),
                Map.entry("boolean", new ParseBoolean()),
                Map.entry("text", new ParseText())
        );
        public static Optional<Parser> getParser(Element e) {
            Parser parse = factory.get(e.getTagName());
            return Optional.ofNullable(parse == null ? factory.get("text") : parse);
        }
    }

    private static <K extends String, V> String formatMapFromXML(final Map<K, ? extends V> mapResult) {
        // 利用类来代替方法参数过多问题
        class Formatter implements FormatterAssist<K, V> {
            private final StringBuilder result = new StringBuilder();
            private Map<K, ? extends V> currentMap = mapResult;
            private int currentMapSize = 0;
            private int recursionCount = 0;

            @Override public Map<K, ? extends V> getCurrentMap() { return currentMap; }
            @Override public String getResultAndToString() { return result.toString(); }

            @Override public int getRecursionCount() { return recursionCount; }
            @Override public int getCurrentMapSize() { return currentMapSize; }
            @Override public int getMapLastIndex() { return mapResult.size() - 1; }

            @Override public void setCurrentMap(Map<K, ? extends V> childMap) { currentMap = childMap; }

            @Override public void incrementCurrentMapSize() { ++currentMapSize; }
            @Override public void incrementRecursionCount() { ++recursionCount; }
            @Override public void decrementRecursionCount() { --recursionCount; }

            @Override public void appendContent(Object content) { result.append(content); }
            @Override public void appendContent(Object... contents) { for (Object content: contents) { appendContent(content); } }

            @Override public void appendCloseSymbol() { appendContent(Symbol.CLOSE_SYMBOL.get()); }
        }

        return formatMapFromXML(new Formatter()).getResultAndToString();
    }

    private static <K extends String, V, T extends FormatterAssist<K, V>> T formatMapFromXML(T formatterAssist) {
        int index = 0;
        boolean isUseOpenSymbol = false;
        Set<? extends Map.Entry<K, ? extends V>> entries = formatterAssist.getCurrentMap().entrySet();

        for (Map.Entry<K, ? extends V> entry: entries) {
            if (entry.getValue() instanceof Map) {
                formatterAssist.appendContent(Symbol.OPEN_SYMBOL.get(), entry.getKey(), Symbol.COLON_SEPARATOR.get());
                formatterAssist.setCurrentMap((Map<K, ? extends V>) entry.getValue());
                formatterAssist.incrementRecursionCount();

                formatMapFromXML(formatterAssist);

                formatterAssist.appendCloseSymbol();
                formatterAssist.decrementRecursionCount();

                if (isUseOpenSymbol) { formatterAssist.appendCloseSymbol(); }
                if (
                        formatterAssist.getRecursionCount() == FormatterAssist.FIRST_RECURSION &&
                        formatterAssist.getCurrentMapSize() < formatterAssist.getMapLastIndex()
                ) {
                    formatterAssist.appendContent(Symbol.NEWLINE_CHARACTER.get());
                    formatterAssist.incrementCurrentMapSize();
                }
            } else {
                if (!isUseOpenSymbol) {
                    formatterAssist.appendContent(Symbol.OPEN_SYMBOL.get());
                    isUseOpenSymbol = true;
                }

                formatterAssist.appendContent(entry.getKey(), Symbol.MAP_SEPARATOR.get(), entry.getValue());
                int boundaryIndex = entries.size() - 1;
                if (index < boundaryIndex) { formatterAssist.appendContent(Symbol.SEMICOLON_SEPARATOR.get()); }
                if (index == boundaryIndex) { formatterAssist.appendCloseSymbol(); }

                index++;
            }
        }

        return formatterAssist;
    }

    private static Map<String, Object> collectIDAttrFromXML(Element rootElement) { return collectIDAttrFromXML(rootElement,"id"); }

    private static Map<String, Object> collectIDAttrFromXML(Element rootElement, String attrName) {
        return collectIDAttrFromXML(rootElement, new HashMap<>(), attrName,  null);
    }

    private static <T extends Map<String, Object>> T collectIDAttrFromXML(Element rootElement, T result, String attrName, String key) {
        NodeList nodeList = rootElement.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Node attr = node.getAttributes().getNamedItem(attrName);
                if (attr != null) {
                    Map<String, Object> map = new HashMap<>();
                    result.put((key = attr.getNodeValue()), map);
                    collectIDAttrFromXML((Element) node, map, attrName, key);
                } else { collectIDAttrFromXML((Element) node, result, attrName, key); }
            } else { result.put(node.getParentNode().getNodeName(), node.getTextContent().trim()); }
        }

        return result;
    }
}

class TestSocket {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("time-a.nist.gov", 13), 4000);
        if (socket.isConnected()) { System.out.println("连接主机" + socket.getInetAddress().getHostName() + "成功!"); }
        Scanner scanner = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
        socket.close();

        Scanner in = new Scanner(new URL("https://www.baidu.com").openStream(), StandardCharsets.UTF_8);
        while (in.hasNextLine()) { System.out.println(in.nextLine()); }
    }
}

final class Singleton {
    private final byte[] data = new byte[1024];
    private volatile static Singleton instance; // volatile关键字保证jvm指令不会重排序，保证可见性，但不保证原子性
    public String conn;
    public String socket;

    private Singleton() {
        this.conn = "Connection";
        this.socket = "Socket";
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    System.out.println("Singleton进行初始化一次！");
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

final class Singleton2 {
    private final byte[] data = new byte[1024];
    { System.out.println("Singleton2只初始化一次！"); }
    private Singleton2() {}
    private static final class Holder { private static final Singleton2 instance = new Singleton2(); }
    public static Singleton2 getInstance() { return Holder.instance; }
}

final class Singleton3 {
    private final byte[] data = new byte[1024];
    private static Singleton3 instance;
    private Singleton3() {}
    public static synchronized Singleton3 getInstance() {
        if (instance == null) {
            System.out.println("Singleton3只初始化一次！");
            instance = new Singleton3();
        }
        return instance;
    }
}

class TestSingleton {
    public static void main(String[] args) {
        List<Class<?>> list = List.of(Singleton.class, Singleton2.class, Singleton3.class);
        IntStream.rangeClosed(0, 5).forEach(x -> new Thread(() -> list.forEach(cls -> {
            try {
                Class.forName(cls.getName()).getDeclaredMethod("getInstance").invoke(null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        })).start());
    }
}

class ValueOffsetExample {
    private final int testValue = 10;
}

class TestUnsafe {
    private static Class<?> cls;
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        System.out.println(getValueOffset("ValueOffsetExample", "testValue"));
        System.out.println(getValueOffset("java.util.concurrent.atomic.AtomicLong", "value"));
    }

    private static long getValueOffset(String className, String fieldName) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        // objectFieldOffset(Field field)方法返回指定的变量在所属类中的内存偏移地址，该偏移地址仅仅在该Unsafe函数中访问指定字段时使用。
        // 正常使用Unsafe类需要自己使用反射进行获取，因为Unsafe是一个单例类，在方法获取实例时会进行安全检查，这样会报错！
        Unsafe unsafe = (Unsafe) getField("sun.misc.Unsafe", "theUnsafe").get(cls);
        return unsafe.objectFieldOffset(getField(className, fieldName));
    }

    private static Field getField(String className, String fieldName) throws NoSuchFieldException, ClassNotFoundException {
        cls = Class.forName(className);
        Field field = cls.getDeclaredField(fieldName);
        int modifiers = field.getModifiers();
        if (Modifier.isPrivate(modifiers) && !Modifier.isVolatile(modifiers)) { field.setAccessible(true); }
        return field;
    }
}

class FalseSharingTest {
    // 测试伪共享的类
    public static class Pointer {
        public volatile long x;
        // 不在Cache line使用填充数据的话时间花费>=35秒, 使用填充时间花费为>=5秒
        // 除了使用Contended注解(但在java 8 以上的版本貌似不起作用)，也还可以在L1 Cache line中声明8个long类型的变量进行充满行
        public long p1, p2, p3, p4, p5, p6, p7, p8;
        public volatile long y;
        @Override public String toString() { return "Pointer [" + "x = " + x + ", y = " + y + "]"; }
    }
    public static void testPointer(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000000; i++) { pointer.x++; }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000000; i++) { pointer.y++; }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("cost " + (System.currentTimeMillis() - start) + "ms");
        System.out.println(pointer);
    }
    public static void main(String[] args) throws InterruptedException { testPointer(new Pointer()); }
}

class TestLongAccumulator {
    public static void main(String[] args) {
        // LongAccumulator是LongAdder类的一个特例，使用LongAccumulator类可以更好的控制实际操作和原始的初始值，而LongAdder类内置了一套自身定义的算法和初始值，不可以自定义
        LongAccumulator longAccumulator = new LongAccumulator(/* 接收一个LongBinaryOperator接口和一个初始值 */(left, right) -> left * right, 10);
        longAccumulator.accumulate(20);
        longAccumulator.accumulate(20);
        System.out.println(longAccumulator.get());
    }
}

class CopyList {
    private static final CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        arrayList.add("Hello");
        arrayList.add("alibaba");
        arrayList.add("welcome");
        arrayList.add("to");
        arrayList.add("hangzhou");

        Thread thread = new Thread(() -> {
            arrayList.set(1, "baba");
            arrayList.remove(2);
            arrayList.remove(3);
        });

        thread.start();
        thread.join();
        for (String s : arrayList) { System.out.println(s); }
    }
}

class UnionFind {
    private final int[] id;
    private final int[] sz;
    private final int allocateSize;
    private int connectedPairsCount;

    public UnionFind(int size) {
        this.allocateSize = allocateMemory(size);
        this.id = new int[allocateSize];
        for (int i = 0; i < size; i++) { id[i] = i; }
        this.sz = IntStream.range(0, size).map(x -> 1).toArray();
    }

    private UnionFind(int[][] data) { this(data.length); }
    private int getConnectedPairsCount() { return connectedPairsCount; }
    private int getSize() { return sz.length; }

    private int getAllocateSize() { return allocateSize; }
    private int allocateMemory(int size) {
        final int MAX_MEMORY = Integer.MAX_VALUE;
        if (size >= MAX_MEMORY || size < 0) { return MAX_MEMORY; }
        if ((size & (size - 1)) != 0) {
            int num = 1;
            for (int i = 1; i < 31; i++) {
                if ((num <<= 1) > size) { return num; }
            }
        }
        return size;
    }

    private boolean connected(int p, int q) { return find(p) == find(q); }
    private boolean union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (connected(pRoot, qRoot)) {
            connectedPairsCount++;
            return true;
        }

        if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }

        return false;
    }
    private int find(int val) {
        while (val != id[val]) { val = id[val]; }
        return val;
    }

    public static void main(String[] args) throws IOException {
        int[][] data = new int[][]{ { 4, 3 }, { 3, 8 }, { 6, 5 }, { 9, 4 }, { 8, 9 }, { 2, 1 }, { 5, 0 }, { 7, 2 }, { 6, 1 }, { 1, 0 }, { 6, 7 } };
        final int LENGTH = data.length;
        UnionFind unionFind = new UnionFind(data);
        String path = System.getProperty("user.dir") + File.separator + "测试连通性.txt";
        File file = new File(path);
        boolean isAppend = true;

        if (!file.exists()) {
            isAppend = !file.createNewFile();
            System.out.println("创建文件成功!");
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, isAppend))) {
            if (!isAppend) {
                bufferedWriter.write(
                        """
                                说明: union-find算法是判断点与点是否连通(连通分量)
                                如果存在连通分量则不输出内容！这个算法在连通分量存在三大原则：自反性、对称性、传递性
                                
                                这个程序的运行是，如果p和q没有连通，则输出两个点
                                否则记录连通的个数，如果想知道有哪些连通对，则可以写成这样的if (!unionFind.union(p, q))语句即可
                                """);
            }

            final int allocateSize = unionFind.getAllocateSize();
            final int size = unionFind.getSize();
            StringBuilder writeContent = new StringBuilder();
            for (int i = 0; i < size; i++) {
                int index = i & (allocateSize - 1);
                int p = data[index][0];
                int q = data[index][1];
                if (!unionFind.union(p, q)) { continue; }
                writeContent.append("( ").append(p).append(", ").append(q).append(" )").append(", ");
            }

            if (size - unionFind.getConnectedPairsCount() != 0) {
                writeContent.delete(writeContent.length() - 2, writeContent.length());
                bufferedWriter.write(writeContent.toString());
            }

            String delimiter = "=======================================分割线============================================";
            for (String item: new String[]{ null, "有 " + unionFind.getConnectedPairsCount() + " 对连通对", null, delimiter, null }) {
                if (item == null) {
                    bufferedWriter.newLine();
                    continue;
                }
                bufferedWriter.write(item);
            }
        }
    }
}

// 底层锁，LockSupport.unpark方法解除阻塞状态，使得能够被CPU执行！park方法是使线程阻塞挂起
class TestLockSupport {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park!");
            LockSupport.park();
            System.out.println("child thread unpark!");
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("main thread begin unpark!");
        LockSupport.unpark(thread);
        System.out.println("main thread end!");
        AtomicBoolean atomicBoolean = new AtomicBoolean();
    }
}

// ConcurrentLinkedQueue
class FIFOMutex {
    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();
    public void lock() {
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);
        // 如果当前的线程不是头部的那个线程或者线程没有获取到锁，则对线程进行挂起和阻塞
        while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
            // 激活线程阻塞挂起的条件有：调用LockSupport.unpark()方法、其他线程调用Thread.interrupted()中断该线程、虚假唤醒
            /*
            * 虚假唤醒是指：在进行同步操作时，生产者和两个消费者的问题，假设两个消费者一开始就运行，此时product这个共享变量此时为0
            * 所以在两个消费者线程内的条件语句if (product <= 0) { this.wait(); }会被挂起
            * 消费者代码如下：
            * public synchronized void sale() {
            *   if (product <= 0) { this.wait(); }
            *   --product;
            *   this.notifyAll();
            * }
            * 当生产者线程进行运行时，代码如下：
            * public synchronized void add() {
            *   if (product > 0) { this.wait(); }
            *   ++product;
            *   this.notifyAll();
            * }
            * 当生产者线程代码执行完毕后，product为1，最后唤醒全部因wait()方法阻塞挂起的线程，所以两个消费者线程会被唤醒
            * 当1号消费者线程唤醒之后，发现条件语句不符合，则会--product，此时的product为0，而1号线程会执行最后的notifyAll()方法
            * 所以有2号消费者线程和生产者线程进行争抢锁，假设这时2号消费者线程抢到了锁，由于上一次执行到了this.wait()方法，所以这次会执行this.wait()方法后面的操作
            * 此时product是为0的，但由于不是while循环这样的继续检查，所以继续执行后面的操作，--product，此时product为-1了，这就是不正常的唤醒导致的情况
            * 解决办法只需把if条件语句换成while条件即可解决问题，这样当被唤醒的线程继续执行时候，只有满足条件继续挂起，否则继续执行后面的操作，符合预期行为
            * */
            LockSupport.park(this);
            if (Thread.interrupted()) { wasInterrupted = true; }
        }
        waiters.remove();
        // 如果其他线程对本线程进行了中断，则对本线程进行中断
        if (wasInterrupted) { current.interrupt(); }
    }

    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }
}

// ReentrantLock重入锁(关键点是条件变量)
class TestReentrantLockCondition {
    private interface TaskImp {
        void run() throws Exception;

        static <T extends Throwable> void throwAs(Throwable t) throws T { throw (T) t; }
        static Runnable MakeRunnableThrowException(TaskImp task) { return asRunnable(task); }

        static <T extends TaskImp> Runnable asRunnable(T task) {
            return () -> {
                try {
                    task.run();
                } catch (Throwable t) {
                    // throw t; 需要重新写try...catch块
                    // 这种机制是骗过了编译器，因为泛型变量T，在编译器眼中不清楚是不是检查异常(Throwable是异常和Error的超类)，估计是当作非检查异常来处理
                    TaskImp.throwAs(t/* 编译器会认为是非检查型异常，即：RunTimeException及子类的Exception */);
                }
            };
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        BiFunction<TaskImp, String, Runnable> run = (func, outString) -> () -> {
            lock.lock();
            try {
                System.out.println("begin " + outString);
                func.run();
                System.out.println("end " + outString);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };

        Thread t1 = new Thread(run.apply(condition::await, "await"));
        Thread t2 = new Thread(run.apply(condition::signal, "signal"));
        t1.start();
        TimeUnit.MILLISECONDS.sleep(1);
        t2.start();
    }
}

// 抽象队列同步器(简称：AQS)
class NonReentrantLock implements Lock, java.io.Serializable {
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean isHeldExclusively() { return getState() == 1; }

        @Override
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int releases) {
            if (getState() == 0) { throw new IllegalMonitorStateException(); }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public Condition newCondition() { return new ConditionObject(); }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() { sync.acquire(1); }

    @Override
    public void lockInterruptibly() throws InterruptedException { sync.acquireInterruptibly(1); }

    @Override
    public boolean tryLock() { return sync.tryAcquire(1); }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException { return sync.tryAcquireNanos(1, unit.toNanos(time)); }

    @Override
    public void unlock() { sync.release(1); }

    @Override
    public Condition newCondition() { return sync.newCondition(); }

    public boolean isLocked() { return sync.isHeldExclusively(); }

    private static final NonReentrantLock lock = new NonReentrantLock();
    private static final Condition conditionalVariable_notFull = lock.newCondition();;
    private static final Condition conditionalVariable_notEmpty = lock.newCondition();
    private static final int LETTERS = 26;
    // 这里也可以使用Character[] chars = new Character[LETTERS]来替代，因为这里在同一时间只有一个线程对数据进行操作，不存在同步问题
    // 如果这里是多线程同时运行的话，则需要使用同步包里的数据结果，保证数据的完整性
    private static final AtomicReferenceArray<Character> atomicReferenceArray = new AtomicReferenceArray<>(LETTERS);

    private interface ThrowErrorRunnable { void run() throws Exception; }

    public static void main(String[] args) {
        Function<ThrowErrorRunnable, Runnable> run = throwErrorRunnable -> () -> {
            lock.lock();
            try { throwErrorRunnable.run(); } catch (Exception e) { e.printStackTrace(); } finally { lock.unlock(); }
        };

        Thread producer = new Thread(run.apply(() -> {
            while (atomicReferenceArray.get(LETTERS - 1) != null) { conditionalVariable_notEmpty.await(); }
            Set<Character> set = new HashSet<>();
            int index = 0;
            while (index < LETTERS) {
                Character c = (char) ('A' + ThreadLocalRandom.current().nextInt(LETTERS));
                if (set.contains(c)) { continue; }
                atomicReferenceArray.set(index++, c);
                set.add(c);
            }
            conditionalVariable_notFull.signalAll();
        }));

        Thread consumer = new Thread(run.apply(() -> {
            while (atomicReferenceArray.get(LETTERS - 1) == null) { conditionalVariable_notFull.await(); }
            for (int i = 0; i < LETTERS; i++) {
                System.out.print(atomicReferenceArray.get(i));
                atomicReferenceArray.set(i, null);
                if (i < LETTERS - 1) { System.out.print(" "); }
            }
            conditionalVariable_notEmpty.signalAll();
        }));

        producer.start();
        consumer.start();
    }
}

/*
* PriorityBlockingQueue优先级无界阻塞队列，内部原理是使用堆的算法进行调整堆的
* offer方法是使用独占锁的方式来进行插入元素，其中会判断是否需要扩容，如果需要扩容则需要调用tryGrow方法，在进行tryGrow方法之后第一时间会释放这个锁
* 因为扩容方法是使用while循环进行检测的，所以会避免了虚假唤醒的可能
* 至于在进入tryGrow方法(CAS算法操作)第一行代码首先会释放锁是因为扩容方法需要另一个线程进行操作，不会阻塞该线程，让其他线程有机会有入队和出队的操作，提高了并发性
* 其中offer方法使用上浮操作
*
* poll方法内部使用了堆算法的下沉操作，与算法书不同的是，在第一次下沉操作时，首先获取的是根节点的左右孩子节点，左右孩子节点进行比较！
* 如果是最小堆的话，取左右孩子最小的节点，反之，最大堆则取最大！然后取到孩子的节点后直接设置父节点(这是个递归过程，如此反复，直到满足最小/最大堆的条件就会退出)
* 而算法书中是先把队列的最后的尾节点复制到根节点上，然后最后尾节点清除(设置为null)，然后在根据左右子节点进行比较
* 如果左孩子最小(最小堆情况)，则左孩子节点和这个新的根节点进行交换，如此反复(递归过程)，知道满足最小/最大堆的条件即可退出
* */
class TestPriorityBlockingQueue {
    public record Task(int priority, String taskName) implements Comparable<Task> {
        @Override public int compareTo(Task o) { return Integer.compare(priority, o.getPriority()); }
        public void print() { System.out.println(taskName + ": " + getPriority()); }
        public int getPriority() { return priority; }
    }

    public static void main(String[] args) {
        Random random = new Random();
        PriorityBlockingQueue<Task> priorityBlockingQueue = new PriorityBlockingQueue<>();
        IntStream.range(0, 10).forEach(index -> priorityBlockingQueue.offer(new Task(random.nextInt(10), "taskName " + index)));
        while (!priorityBlockingQueue.isEmpty()) {
            Task task = priorityBlockingQueue.poll();
            if (!Objects.isNull(task)) { task.print(); }
        }
    }
}

class TestDelayedQueue {
    public static class DelayedEle implements Delayed {
        private final long delayTime;
        private final long expire;
        private final String taskName;
        public DelayedEle(long delayTime, String taskName) {
            this.delayTime = delayTime;
            this.taskName = taskName;
            this.expire = System.currentTimeMillis() + delayTime;
        }

        private String format() {
            StringBuilder sb = new StringBuilder("DelayedEle{ ");
            try {
                Class<?> cls = Class.forName("TestDelayedQueue$DelayedEle");
                Delayed delayedEle = (Delayed) cls.getDeclaredConstructor(long.class, String.class).newInstance(delayTime, taskName);
                Field[] fields = cls.getDeclaredFields();
                final int LENGTH = fields.length;
                for (int i = 0; i < LENGTH; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    Object name = field.get(delayedEle);
                    sb.append(field.getName()).append(" = ").append(i < LENGTH - 1 ? name : "\"" + name + "\"");
                    if (i < LENGTH - 1) { sb.append(", "); }
                }
                sb.append(" }");
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override public long getDelay(TimeUnit unit) { return unit.convert(expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS); }
        @Override public int compareTo(Delayed o) { return Long.compare(getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS)); }
        @Override public String toString() { return format(); }
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-1 << 29));
        DelayQueue<DelayedEle> delayQueue = new DelayQueue<>();
        Random random = new Random();
        IntStream.range(0, 10).forEach(index -> delayQueue.offer(new DelayedEle(random.nextInt(500), "task" + index)));
        DelayedEle delayedEle;
        try {
            while (!Objects.isNull((delayedEle = delayQueue.take()))) { System.out.println(delayedEle); }
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}

// 使用线程有时需要等待所有线程都结束后再回头执行该线程，这时需要使用join()方法
// 但使用线程池时候就没办法使用join()方法了，这时可以使用CountDownLatch、CyclicBarrier、Semaphore类来达到和join()方法相同的效果，比使用join()方法更具有灵活性和方便性
// CountDownLatch是使用AQS实现的，构造函数的参数是代表AQS的state值(计数器)，当多个线程调用countdown方法时实际时原子性递减AQS的状态值
// 当线程调用await方法后当前线程会被放入AQS的阻塞队列等待计数器为0再返回。
// 其他线程调用countdown方法让计数器值递减1，当计数器变为0时，当前线程还要调用AQS的doReleaseShared方法来激活由于调用await()方法而被阻塞的线程
class TestCountDownLatch {
    private static final CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Random random = new Random();
        Supplier<Integer> generatorRandomTime = () -> 1 + random.nextInt(5);
        BiFunction<Integer, Integer, Runnable> runnable = (threadSerialNumber, time) -> () -> {
            try { TimeUnit.SECONDS.sleep(time); } catch (InterruptedException e) { e.printStackTrace(); } finally { countDownLatch.countDown(); }
            System.out.println("线程" + threadSerialNumber + "运行完毕");
        };

        executorService.submit(runnable.apply(1, generatorRandomTime.get()));
        executorService.submit(runnable.apply(2, generatorRandomTime.get()));

        System.out.println("等待所有子线程运行完毕...");
        countDownLatch.await();
        System.out.println("所有子线程均运行完毕！");
        executorService.shutdown();
    }
}

// CyclicBarrier是回环屏障的意思，它可以让一组线程全部达到一个状态后再同时执行，构造函数CyclicBarrier(int parties)的parties代表线程数
// 之所以叫作回环时因为当所有等待线程执行完毕，并重置CyclicBarrier的状态后它可以被重用
// 之所以叫做屏障是因为线程调用await方法后就会被阻塞，这个阻塞点就称为屏障点，等所有线程都调用了await方法后，线程们就会冲破屏障，继续向下运行
// CyclicBarrier第二个参数是一个Runnable函数，代表当计数器值为0时需要执行的任务
class TestCyclicBarrier {
    private static final int MAX_THREADS = 2;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
    private static final AtomicInteger atomicInteger = new AtomicInteger();
    private static class InnerCyclicBarrier1 {
        private static final CyclicBarrier cyclicBarrier =
                new CyclicBarrier(MAX_THREADS, () -> System.out.println(Thread.currentThread() + " task1 merge result"));
        public static void run() {
            Function<Integer, Runnable> runnableFunction = serialNumber -> () -> {
                try {
                    System.out.println(Thread.currentThread() + " task1-" + serialNumber);
                    System.out.println(Thread.currentThread() + " enter in barrier");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " enter out barrier");
                    atomicInteger.getAndIncrement();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            };

            executorService.submit(runnableFunction.apply(1));
            executorService.submit(runnableFunction.apply(2));
        }
    }

    private static class InnerCyclicBarrier2 {
        private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(MAX_THREADS);
        public static void run() {
            Supplier<Runnable> runnableFunction = () -> () -> {
                try {
                    System.out.println(Thread.currentThread() + " step1");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " step2");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " step3");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            };

            System.out.println("=====================================================================================");
            executorService.submit(runnableFunction.get());
            executorService.submit(runnableFunction.get());
            executorService.shutdown();
        }
    }
    public static void main(String[] args) {
        InnerCyclicBarrier1.run();
        for (;;) {
            if (atomicInteger.get() == MAX_THREADS) {
                InnerCyclicBarrier2.run();
                break;
            }
        }
    }
}

// Semaphore类和CyclicBarrier类类似，不同在于Semaphore类需要调用release()方法增加信号量！而在阻塞线程中需要使用acquire()方法来等待所有子线程运行完毕
// acquire(int permits)方法中的permits代表在所有线程调用的全部次数，如：A线程调用两次release()，B线程调用三次release()，则在需要阻塞线程调用acquire(5)
// 通过调整permits参数也可以达到CyclicBarrier类的回环效果(只不过感觉没有CyclicBarrier类好一些)
class TestSemaphore {
    private static final int MAX_THREADS = 2;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
    private static final Semaphore semaphore = new Semaphore(0);
    private static class InnerCyclicSemaphore1 {
        public static void run() {
            Function<Integer, Runnable> runnableFunction = serialNumber -> () -> {
                System.out.println(Thread.currentThread() + " task1-" + serialNumber);
                System.out.println(Thread.currentThread() + " enter in semaphore");
                System.out.println(Thread.currentThread() + " enter out semaphore");
                semaphore.release();
            };

            executorService.submit(runnableFunction.apply(1));
            executorService.submit(runnableFunction.apply(2));
        }
    }

    private static class InnerCyclicSemaphore2 {
        public static void run() {
            Supplier<Runnable> runnableFunction = () -> () -> {
                System.out.println(Thread.currentThread() + " step1");
                semaphore.release();
                System.out.println(Thread.currentThread() + " step2");
                semaphore.release();
                System.out.println(Thread.currentThread() + " step3");
            };

            System.out.println("=====================================================================================");
            executorService.submit(runnableFunction.get());
            executorService.submit(runnableFunction.get());
            executorService.shutdown();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        InnerCyclicSemaphore1.run();
        semaphore.acquire(2);
        InnerCyclicSemaphore2.run();
        semaphore.acquire(4);
        System.out.println("线程全部执行完毕！");
    }
}

class URLConnectionTest {
    public static void main(String[] args) {
        try {
            String urlName = "http://horstmann.com";
            URL url = new URL(urlName);
            URLConnection connection = url.openConnection();
            connection.connect();
            Map<String, List<String>> headers = connection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry: headers.entrySet()) {
                String key = entry.getKey();
                for (String value: entry.getValue()) { System.out.println(key + ": " + value); }
            }

            System.out.println("---------------------------------------------");
            System.out.println("getContentType: " + connection.getContentType());
            System.out.println("getContentLength: " + connection.getContentLength());
            System.out.println("getContentEncoding: " + connection.getContentEncoding());
            System.out.println("getDate: " + connection.getDate());
            System.out.println("getExpiration: " + connection.getExpiration());
            System.out.println("getLastModified: " + connection.getLastModified());
            System.out.println("---------------------------------------------");

            String encoding = connection.getContentEncoding();
            if (encoding == null) { encoding = "utf-8"; }
            try (Scanner in = new Scanner(connection.getInputStream(), encoding)) {
                for (int n = 1; in.hasNextLine() && n <= 10; n++) { System.out.println(in.nextLine()); }
                if (in.hasNextLine()) { System.out.println("..."); }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class PostTest {
    public static void main(String[] args) throws IOException {
        String propsFileName = "post.properties";
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(propsFileName))) { props.load(in); }

        String urlString = props.getProperty("url");
        Object userAgent = props.getProperty("User-Agent");
        Object redirects = props.getProperty("redirects");

        // 当请求一个URL时，如果在请求头中包含redirect(即：状态码3xx)时，为了使cookie正确的传送到最终重定向的地址，可以设置全局Cookie管理器
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        System.out.println(
                doPost(
                    new URL(urlString),
                    props,
                    userAgent == null ? null : userAgent.toString(),
                    redirects == null ? -1 : Integer.parseInt(redirects.toString())
                )
        );
    }

    private static String doPost(URL url, Map<Object, Object> nameValuePairs, String userAgent, int redirects) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (userAgent != null) { connection.setRequestProperty("User-Agent", userAgent); }
        if (redirects >= 0) { /* 禁止自动重定向，使用手动重定向来模拟 */connection.setInstanceFollowRedirects(false); }
        connection.setDoOutput(true);
        try (PrintStream out = new PrintStream(connection.getOutputStream())) {
            int index = 0;
            StringBuilder params = new StringBuilder();
            for (Map.Entry<Object, Object> pair: nameValuePairs.entrySet()) {
                /*
                URLEncoder类:
                HTML 表单编码的实用程序类。此类包含用于将 String 转换为application/x-www-form-urlencoded MIME 格式的静态方法
                对字符串进行编码时，适用以下规则：
                字母数字字符“ a ”到“ z ”、“ A ”到“ Z ”和“ 0 ”到“ 9 ”保持不变
                特殊字符“ . ”、“ - ”、“ * ”和“ _ ”保持不变
                空格字符“”转换为加号“ + ”
                所有其他字符都是不安全的，并且首先使用某种编码方案将其转换为一个或多个字节。然后每个字节由 3 个字符的字符串“ %xy ”表示，其中xy是字节的两位十六进制表示
                推荐使用的编码方案是 UTF-8。但是，出于兼容性原因，如果未指定编码，则使用平台的默认编码
                例如，使用 UTF-8 作为编码方案，字符串 "The string ü@foo-bar" 将被转换为 "The+string+%C3%BC%40foo-bar"，
                因为在 UTF-8 中，字符 ü 被编码为两个字节 C3（十六进制）和 BC（十六进制），字符 @ 被编码为一个字节 40（十六进制）*/
                params.append(pair.getKey().toString()).append("=").append(URLEncoder.encode(pair.getValue().toString(), StandardCharsets.UTF_8));
                if (index++ < nameValuePairs.size() - 1) { params.append("&"); }
            }
            out.print(params);
        }

        if (redirects == 0) { throw new IOException("Too many redirects"); }
        if (redirects > 0) {
            int responseCode = connection.getResponseCode();
            boolean needJump = Stream.of(
                    HttpURLConnection.HTTP_MOVED_PERM,
                    HttpURLConnection.HTTP_MOVED_TEMP,
                    HttpURLConnection.HTTP_SEE_OTHER).anyMatch(code -> responseCode == code);
            if (needJump) {
                // Location 首部指定的是需要将页面重新定向至的地址。一般在响应码为3xx的响应中才会有意义
                String location = connection.getHeaderField("Location");
                if (location != null) {
                    URL base = connection.getURL();
                    connection.disconnect();
                    return doPost(new URL(base, location), nameValuePairs, userAgent, redirects - 1);
                }
            }
        }

        StringBuilder response = new StringBuilder();
        String encoding = connection.getContentEncoding();
        try (Scanner in = new Scanner(connection.getInputStream(), encoding == null ? "UTF-8" : encoding)) {
            while (in.hasNextLine()) { response.append(in.nextLine()).append("\n"); }
        } catch (IOException e) {
            InputStream err = connection.getErrorStream();  // 捕获服务器的错误，如状态码404
            if (err == null) { throw e; }
            try (Scanner in = new Scanner(err)) { response.append(in.nextLine()).append("\n"); }
        }
        return response.toString();
    }
}

class MoreBodyPublishers {
    public static HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        return traverseData(
                data,
                (entry, builder) -> builder.append(urlEncode(entry.getKey())).append("=").append(urlEncode(entry.getValue())),
                "&",
                null,
                null
        );
    }

    private static String urlEncode(Object val) { return URLEncoder.encode(String.valueOf(val), StandardCharsets.UTF_8); }

    public static HttpRequest.BodyPublisher ofMimeMultipartData(Map<Object, Object> data, String boundary) throws IOException {
        List<byte[]> byteArrays = new ArrayList<>();
        byte[] separator = bytes("--" + boundary + "\nContent-Disposition: form-data; name=");
        for (Map.Entry<Object, Object> entry: data.entrySet()) {
            byteArrays.add(separator);
            if (entry.getValue() instanceof Path val) {
                String mimeType = Files.probeContentType(val);
                byteArrays.add(bytes("\"" + entry.getKey() + "\"; filename=\"" + val.getFileName() + "\"\nContent-Type: " + mimeType + "\n\n"));
                byteArrays.add(Files.readAllBytes(val));
            } else { byteArrays.add(bytes("\"" + entry.getKey() + "\"\n\n" + entry.getValue() + "\n")); }
        }
        byteArrays.add(bytes("--" + boundary + "--"));
        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

    private static byte[] bytes(String s) { return s.getBytes(StandardCharsets.UTF_8); }
    private static boolean checkValidString(String str) { return str != null && str.length() > 0; }

    public static HttpRequest.BodyPublisher ofSimpleJSON(Map<Object, Object> data) {
        return traverseData(
                data,
                (entry, builder) -> builder.append(jsonEscape(entry.getKey().toString())).append(": ").append(jsonEscape(entry.getValue().toString())),
                ",",
                "{",
                "}"
        );
    }

    private static HttpRequest.BodyPublisher traverseData(
            Map<Object, Object> data,
            BiConsumer<Map.Entry<Object, Object>, StringBuilder> consumer,
            String delimiter,
            String begin,
            String last
    ) {
        int index = 0;
        StringBuilder builder = new StringBuilder();
        if (checkValidString(begin)) { builder.append(begin); }
        for (Map.Entry<Object, Object> entry: data.entrySet()) {
            consumer.accept(entry, builder);
            if (index++ < data.size() - 1) { builder.append(delimiter); }
        }
        if (checkValidString(last)) { builder.append(last); }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    private static StringBuilder jsonEscape(String str) {
        StringBuilder result = new StringBuilder("\"");
        Map<Character, String> replacements = Map.of(
                '\b', "\\b",
                '\f', "\\f",
                '\n', "\\n",
                '\r', "\\r",
                '\t', "\\t",
                '"', "\\\"",
                '\\', "\\\\"
        );
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String replacement = replacements.get(ch);
            if (replacement == null) { result.append(ch); }
            else { result.append(replacement); }
        }
        result.append("\"");
        return result;
    }
}

class HttpClientTest {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        System.setProperty("jdk.httpclient.HttpClient.log", "headers,errors");
        Path propsPath = getPath("client/json.properties");
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(propsPath)) { props.load(in); }
        String contentType = "" + props.remove("Content-Type");
        if (Objects.equals(contentType, "multipart/form-data")) {
            contentType += ";boundary=" + new BigInteger(256, new Random());
            props.replaceAll((k, v) -> v.toString().startsWith("file://") ? propsPath.getParent().resolve(getPath(v.toString().substring(7))) : v);
        }
        System.out.println(doPost((String) props.remove("url"), contentType, props));
    }
    private static Path getPath(String path) { return Paths.get(path); }
    public static String doPost(String url, String contentType, Map<Object, Object> data) throws IOException, URISyntaxException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
        HttpRequest.BodyPublisher publisher;
        switch (contentType) {
            case "multipart/form-data" -> publisher =
                    MoreBodyPublishers.ofMimeMultipartData(data, contentType.substring(contentType.lastIndexOf("=") + 1));
            case "application/x-www-form-urlencoded" -> publisher = MoreBodyPublishers.ofFormData(data);
            default -> {
                contentType = "application/json";
                publisher = MoreBodyPublishers.ofSimpleJSON(data);
            }
        }
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).header("Content-Type", contentType).POST(publisher).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}

class TestDateFormatter {
    public static void main(String[] args) {
        // yyyy(年)-MM(月)-dd(日) (KK/HH)(小时):mm(分钟):ss(秒) a(AM/PM，即上午/下午) VV(当前的时区)
        // HH代表传入的小时是24小时制，例如传入的小时是14，那么时间也会显示14点
        // 如果使用KK的话，会被转成上午的时间，这样的话如果后面不跟显示下午或上午的标志的话，那么统一会变为上午的时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd KK:mm:ss a VV");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(
                1993, 8, 10, 14, 23, 43, 0,
                ZoneId.systemDefault());
        String dateTime = dateTimeFormatter.format(zonedDateTime);
        System.out.println(dateTime);

        nationalRegionsCompare("absent", "absenu", null);
        nationalRegionsCompare("absent", "absent", null);
        nationalRegionsCompare("absenv", "absent", null);
        nationalRegionsCompare("Absent", "absent", null, Collator.PRIMARY);
        String pattern = "On {2,date,long}, a {0} destroyed {1,choice,0#no houses|1#one house|2#{1} houses} and caused {3,number,currency} of damage.";
        String result = MessageFormat.format(
                pattern,
                "hurricane", 30, new GregorianCalendar(1999, Calendar.JANUARY, 1).getTime(), 10.0E8);
        System.out.println(result);
    }

    // 按照不同国家对字符串进行按字符串的每一位比较
    private static void nationalRegionsCompare(String str1, String str2, Locale locale) { nationalRegionsCompare(str1, str2, locale, Integer.MAX_VALUE); }
    private static void nationalRegionsCompare(String str1, String str2, Locale locale, int newStrength) {
        String[] position = new String[]{ "之前", "相等", "之后"  };
        Collator collator = locale == null ? Collator.getInstance() : Collator.getInstance(locale); // Collator是根据国家地区比较字符串的工具类
        // 默认强度是Collator.TERTIARY
        if (newStrength >= Collator.PRIMARY && newStrength <= Collator.IDENTICAL && newStrength != collator.getStrength()) { collator.setStrength(newStrength); }
        String description = position[collator.getCollationKey(str1).compareTo(collator.getCollationKey(str2)) + 1];
        System.out.println("字符串" + str1 + "应该在" + str2 + description);
    }
}

/*
 * 国际化资源包(类)和国际化资源属性文件
 * 每个国际化的类必须是public的，否则会抛出MissingResourceException异常
 *
 * getBundle方法试图加载匹配当前locale定义的语言和国家的包。如果失败，通过依次放弃国家和语言来继续进行查找，然后同样的查找被应用于默认的locale
 * 最后，如果还不行的话就去查看默认的包文，如果这也失败了，则抛出MissingResourceException异常
 * 这就是说，getBundle方法会试图加载以下包：
 * baseName_currentLocaleLanguage_currentLocaleCountry.java
 * baseName_currentLocaleLanguage.java
 * baseName_currentLocaleLanguage_defaultLocaleCountry.java
 * baseName_currentLocaleLanguage.java
 * baseName.java
 *
 * baseName_currentLocaleLanguage_currentLocaleCountry.properties
 * baseName_currentLocaleLanguage.properties
 * baseName_currentLocaleLanguage_defaultLocaleCountry.properties
 * baseName_currentLocaleLanguage.properties
 * baseName.properties
 *
 * 命名规则：例如，为德国定义的资源放在一个名为baseName_de_DE的文件中，而所有说德语的国家所共享的资源则放在名为baseName_de的文件中
 * 一般来说，使用baseName_language_country来命名所有和国家相关的资源，baseName_language来命名所有和语言相关的资源
 *
 * 另外属性文件的命名和资源包(类)的命名规则一样，只不过文件类型是.properties，而资源包是.java
 *
 * 实现国家化资源包类的最简单方法就是继承ListResourceBundle类，实现(public/protected) Object[][] getContents()方法即可
 * 也可与继承ResourceBundle类，只不过要实现Enumeration<String> getKeys()和Object handleGetObject(String key)这两个方法
 **/
class InternationalResourcePackageTest {
    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("InternationalResourcePackage", Locale.getDefault());
        String key_hello = resourceBundle.getString("hello");
        String key_world = resourceBundle.getString("world");
        System.out.println(key_hello + key_world);
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation { String[] value(); }

class TestInterfaceAnnotation {
    @TestAnnotation({ "hello", " world" }) public static void printHelloWorld(String[] args) { Arrays.stream(args).forEach(System.out::print); }
    @TestAnnotation({ "我叫", "Janny" }) public static void printName(String[] args) { Arrays.stream(args).forEach(System.out::print); }
    private static void process() {
        try {
            Method[] methods = TestInterfaceAnnotation.class.getDeclaredMethods();
            Collator collator = Collator.getInstance(Locale.getDefault());
            Arrays.sort(methods, Comparator.comparing(a -> collator.getCollationKey(a.getName())));
            int index = 0;
            for (Method method: methods) {
                TestAnnotation testAnnotation = method.getAnnotation(TestAnnotation.class);
                if (testAnnotation != null) {
                    method.invoke(null, (Object) testAnnotation.value());
                    if (index++ < methods.length - 1) { System.out.println(); } }
            }
        } catch (ReflectiveOperationException e) { e.printStackTrace(); }
    }
    public static void main(String[] args) {
        TestInterfaceAnnotation.process();
    }
}

interface FavoriteDrink {
    // 咖啡类
    enum Coffee implements FavoriteDrink {
        LATTE_COFFEE, CAPPUCCINO_COFFEE, PLAIN_COFFEE, MOCHA_COFFEE,
        CAFE_AMERICANO, BLACK_COFFEE, WHITE_COFFEE, BLUE_MOUNTAIN_COFFEE
    }
    // 酒类
    enum Alcohol implements FavoriteDrink {
        CHINESE_SPIRITS, BEER, WINE, COCKTAIL, WHISKY,
        BRANDY, VODKA, RUM, FRUIT_WINE, YELLOW_WINE
    }
    // 茶类
    enum TeaWater implements FavoriteDrink {
        BILUOCHUN_TEA, XINYANG_MAOJIAN_TEA, DAHONGPAO_TEA, TIE_GUANYIN,
        BLACK_TEA, OOLONG_TEA, YELLOW_TEA, MILKY_TEA
    }
    // 饮料类
    enum Beverage implements FavoriteDrink {
        ICE_BLACK_TEA, SWEET_STEWED_SNOW_PEAR, COCA_COLA, SPRITE,
        KOWAS, BIG_WHITE_PEAR, FARMER_ORCHARD, WATER_SOLUBLE_C
    }
}

class Enums {
    private static final Random random = new Random();
    public static <T extends Enum<T>> T random(Class<T> cls) { return random(cls.getEnumConstants()); }
    public static <T> T random(T[] values) { return values[random.nextInt(values.length)]; }
}

class TestRandomEnum {
    private enum Drink {
        COFFEE(FavoriteDrink.Coffee.class), ALCOHOL(FavoriteDrink.Alcohol.class),
        TEA_WATER(FavoriteDrink.TeaWater.class), BEVERAGE(FavoriteDrink.Beverage.class);
        private final FavoriteDrink[] values;
        Drink(Class<? extends FavoriteDrink> cls) { values = cls.getEnumConstants(); }
        public FavoriteDrink randomSelection() { return Enums.random(values); }
    }
    public static void main(String[] args) {
        IntStream.range(0, 5).forEach(x -> {
            Stream.of(Drink.values()).forEach(e -> System.out.println(e.randomSelection()));
            System.out.println("------------------------------------------------------------");
        });
    }
}

/*
* 访问者模式
* 访问者（Visitor）模式实现的关键是如何将作用于元素的操作分离出来封装成独立的类，其基本结构与实现方法如下。
* 模式的结构:
* 访问者模式包含以下主要角色。
* 抽象访问者（Visitor）角色：定义一个访问具体元素的接口，为每个具体元素类对应一个访问操作 visit() ，该操作中的参数类型标识了被访问的具体元素。
* 具体访问者（ConcreteVisitor）角色：实现抽象访问者角色中声明的各个访问操作，确定访问者访问一个元素时该做什么。
* 抽象元素（Element）角色：声明一个包含接受操作 accept() 的接口，被接受的访问者对象作为 accept() 方法的参数。
* 具体元素（ConcreteElement）角色：实现抽象元素角色提供的 accept() 操作，其方法体通常都是 visitor.visit(this) ，另外具体元素中可能还包含本身业务逻辑的相关操作。
* 对象结构（Object Structure）角色：是一个包含元素角色的容器，提供让访问者对象遍历容器中的所有元素的方法，通常由 List、Set、Map 等聚合类实现。
* */
interface Visitor {
    void visit(ConcreteElementA element);
    void visit(ConcreteElementB element);
}

class ConcreteVisitorA implements Visitor {
    @Override public void visit(ConcreteElementA element) { System.out.println("具体访问者A访问 -> " + element.operation()); }
    @Override public void visit(ConcreteElementB element) { System.out.println("具体访问者A访问 -> " + element.operation()); }
}

class ConcreteVisitorB implements Visitor {
    @Override public void visit(ConcreteElementA element) { System.out.println("具体访问者B访问 -> " + element.operation()); }
    @Override public void visit(ConcreteElementB element) { System.out.println("具体访问者B访问 -> " + element.operation()); }
}

interface CustomElement { void accept(Visitor visitor); }
class ConcreteElementA implements CustomElement {
    @Override public void accept(Visitor visitor) { visitor.visit(this); }
    public String operation() { return "具体元素A的操作"; }
}

class ConcreteElementB implements CustomElement {
    @Override public void accept(Visitor visitor) { visitor.visit(this); }
    public String operation() { return "具体元素B的操作"; }
}

class ObjectStructure {
    private final List<CustomElement> list = new ArrayList<>();
    public void accept(Visitor visitor) { list.forEach(element -> element.accept(visitor)); }
    public void add(CustomElement element) { list.add(element); }
}

class VisitorPattern {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.add(new ConcreteElementA());
        objectStructure.add(new ConcreteElementB());
        objectStructure.accept(new ConcreteVisitorA());
        System.out.println("-----------------------------------------------");
        objectStructure.accept(new ConcreteVisitorB());
    }
}

// 建造者模式模拟装修房子
class DecorationBuildingMaterials {
    private String buildingMaterialProduct;
    private String buildingMaterialsBrand;
    private String buildingMaterialGrade;
    private BigDecimal buildingMaterialsPrice;
    private String buildingMaterialsBrandDescription;
    protected BuildBuildingMaterials buildBuildingMaterials = new BuildBuildingMaterials();

    public class BuildBuildingMaterials {
        private String buildingMaterialsBrand;
        private String buildingMaterialGrade;
        private BigDecimal buildingMaterialsPrice;
        private String buildingMaterialsBrandDescription;

        public BuildBuildingMaterials addBuildingMaterialsBrand(String buildingMaterialsBrand) {
            this.buildingMaterialsBrand = buildingMaterialsBrand;
            return this;
        }

        public BuildBuildingMaterials addBuildingMaterialGrade(String buildingMaterialGrade) {
            this.buildingMaterialGrade = buildingMaterialGrade;
            return this;
        }

        public BuildBuildingMaterials addBuildingMaterialsPrice(BigDecimal buildingMaterialsPrice) {
            this.buildingMaterialsPrice = buildingMaterialsPrice;
            return this;
        }

        public BuildBuildingMaterials addBuildingMaterialsBrandDescription(String buildingMaterialsBrandDescription) {
            this.buildingMaterialsBrandDescription = buildingMaterialsBrandDescription;
            return this;
        }

        public void build() {
            DecorationBuildingMaterials.this.buildingMaterialsBrand = buildingMaterialsBrand;
            DecorationBuildingMaterials.this.buildingMaterialGrade = buildingMaterialGrade;
            DecorationBuildingMaterials.this.buildingMaterialsPrice = buildingMaterialsPrice;
            DecorationBuildingMaterials.this.buildingMaterialsBrandDescription = buildingMaterialsBrandDescription;
        }
    }

    public DecorationBuildingMaterials(String product) { buildingMaterialProduct = product; }

    public String getBuildingMaterialProduct() { return buildingMaterialProduct; }
    public String getBuildingMaterialsBrand() { return buildingMaterialsBrand; }
    public String getBuildingMaterialGrade() { return buildingMaterialGrade; }
    public BigDecimal getBuildingMaterialsPrice() { return buildingMaterialsPrice; }
    public String getBuildingMaterialsBrandDescription() { return buildingMaterialsBrandDescription; }
}

abstract class SuspendedCeiling extends DecorationBuildingMaterials {
    public SuspendedCeiling() { super("吊顶"); }
}

class AIACeiling extends SuspendedCeiling {
    public AIACeiling() {
        buildBuildingMaterials.addBuildingMaterialsBrand("友邦")
                .addBuildingMaterialGrade("一级顶")
                .addBuildingMaterialsPrice(new BigDecimal(356))
                .addBuildingMaterialsBrandDescription("友邦，中国集成吊顶行业缔造者和领导者，专注以创新设计和卓越性能缔造一流的室内吊顶整体解决方案。")
                .build();
    }
}

class AUPUCeiling extends SuspendedCeiling {
    public AUPUCeiling() {
        buildBuildingMaterials.addBuildingMaterialsBrand("AUPU奥普")
                .addBuildingMaterialGrade("二级顶")
                .addBuildingMaterialsPrice(new BigDecimal(300))
                .addBuildingMaterialsBrandDescription(
                        "奥普正以一贯的原创力量带动着行业的发展，并严谨把控产品出厂、认真规划上市经营、及时反馈市场信息，记取合作伙伴与消费者的建议和期望为原则，" +
                                "不断创造市场典范的产品，让经典品质代代相传。")
                .build();
    }
}

abstract class Coating extends DecorationBuildingMaterials {
    public Coating() { super("涂料"); }
}

class Nippon extends Coating {
    public Nippon() {
        buildBuildingMaterials.addBuildingMaterialsBrand("Nippon立邦")
                .addBuildingMaterialGrade("第一代")
                .addBuildingMaterialsPrice(new BigDecimal(650))
                .addBuildingMaterialsBrandDescription("立邦一直以美化和保护人们的生活为己任，不断创造出品质优越的产品，让美丽的色彩足迹遍及中国每一角落。")
                .build();
    }
}

class Dulux extends Coating {
    public Dulux() {
        buildBuildingMaterials.addBuildingMaterialsBrand("多乐士(Dulux)")
                .addBuildingMaterialGrade("第二代")
                .addBuildingMaterialsPrice(new BigDecimal(546))
                .addBuildingMaterialsBrandDescription("⽴立邦始终以开发绿⾊色产品、注重⾼高科技、⾼高品质为⽬目标，以技术⼒力力量量不不断推进科 研和开发，满⾜足消费者需求。")
                .build();
    }
}

abstract class Floor extends DecorationBuildingMaterials {
    public Floor() { super("地板"); }
}

class ShengxiangFloor extends Floor {
    public ShengxiangFloor() {
        buildBuildingMaterials.addBuildingMaterialsBrand("圣象地板")
                .addBuildingMaterialGrade("A+")
                .addBuildingMaterialsPrice(new BigDecimal(319))
                .addBuildingMaterialsBrandDescription(
                        "圣象集团成立于1995年，总部位于上海，是国内强化地板的知名企业。20年来圣象以前瞻的视野、创新的思维和拼搏的精神，不断提升自身优势，创造了前无古人的辉煌成绩，" +
                                "推动行业的整体发展。")
                .build();
    }
}

class ElegantLivingFloor extends Floor {
    public ElegantLivingFloor() {
        buildBuildingMaterials.addBuildingMaterialsBrand("生活家(ELEGANT LIVING)")
                .addBuildingMaterialGrade("一级")
                .addBuildingMaterialsPrice(new BigDecimal(220))
                .addBuildingMaterialsBrandDescription(
                        "生活家正行驶在发展的快速路上，地板产业和整体定制家居齐头并进，并拟定了远大的战略目标。以创新为企业核心能力，在更广泛的木制品领域，" +
                                "生活家将为全球消费者提供超高品位与品质的产品、完善与尊贵的服务。")
                .build();
    }
}

abstract class CeramicTile extends DecorationBuildingMaterials {
    public CeramicTile() { super("瓷砖"); }
}

class MarcopoloCeramicTile extends CeramicTile {
    public MarcopoloCeramicTile() {
        buildBuildingMaterials.addBuildingMaterialsBrand("Marcopolo马可波罗")
                .addBuildingMaterialGrade("特级")
                .addBuildingMaterialsPrice(new BigDecimal(239))
                .addBuildingMaterialsBrandDescription(
                        "企业拥有380多项专利，其自主研发的原创设计──“中国印象”系列产品，将中国传统文化之精髓展现在瓷砖上，引导设计之风回归东方禅韵，受到国内外消费者和专家的广泛关注。")
                .build();
    }
}

class DongPengCeramicTile extends CeramicTile {
    public DongPengCeramicTile() {
        buildBuildingMaterials.addBuildingMaterialsBrand("DONGPENG东鹏瓷砖")
                .addBuildingMaterialGrade("一级")
                .addBuildingMaterialsPrice(new BigDecimal(187))
                .addBuildingMaterialsBrandDescription(
                        "为广大消费者提供环保、艺术、高科技、个性化的家居解决方案，是东鹏的追求与使命。未来，东鹏将不断创新超越，为更多用户提供品质和品味的享受，朝着“百年企业，" +
                                "世界东鹏”的愿景，打造享誉中外的整体家居国际品牌。")
                .build();
    }
}

// 装修风格：豪华欧式、轻奢田园、现代简约
enum DecorationStyle {
    DELUXE_EUROPEAN_STYLE("豪华欧式"),
    LIGHT_LUXURY_COUNTRYSIDE_STYLE("轻奢田园"),
    MODERN_SIMPLICITY_STYLE("现代简约");
    private final String style;
    private DecorationStyle(String style) { this.style = style; }
    public String getStyle() { return style; }
}

interface IMenu {
    IMenu addCeiling(SuspendedCeiling suspendedCeiling);
    IMenu addCoating(Coating coating);
    IMenu addFloor(Floor floor);
    IMenu addCeramicTile(CeramicTile ceramicTile);
    String getDetail();
}

class ColorTextTool {
    private static final Random random = new Random();
    private ColorTextTool() {}
    public static String createRandomColorText(String text) {
        return String.format("%s%s%s", "\033[" + (31 + random.nextInt(6)) + ";2m", text, "\033[0m");
    }
}

class DecorationCompany implements IMenu {
    private List<DecorationBuildingMaterials> decorationList = new ArrayList<>();
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal area;
    private DecorationStyle style;

    private DecorationCompany() {}
    public DecorationCompany(double area, DecorationStyle style) {
        this.area = new BigDecimal(area);
        this.style = style;
    }

    @Override public IMenu addCeiling(SuspendedCeiling suspendedCeiling) {
        return calcPriceAndAddBuildingMaterial(suspendedCeiling, "0.2", true);
    }

    @Override public IMenu addCoating(Coating coating) { return calcPriceAndAddBuildingMaterial(coating, "1.4", true); }
    @Override public IMenu addFloor(Floor floor) { return calcPriceAndAddBuildingMaterial(floor, "", false); }
    @Override public IMenu addCeramicTile(CeramicTile ceramicTile) { return calcPriceAndAddBuildingMaterial(ceramicTile, "", false); }
    @Override public String getDetail() {
        StringBuilder detail = new StringBuilder("装修清单：");
        detail.append(createRandomColorText("\n\t套餐等级：" + style.getStyle()))
                .append(createRandomColorText("\n\t套餐价格：" + price.setScale(2, RoundingMode.HALF_UP) + "元"))
                .append(createRandomColorText("\n\t房屋面积：" + area.doubleValue() + "平米"))
                .append("\n材料清单：");

        StringBuilder l = decorationList.stream().collect(StringBuilder::new, (r, c) -> {
            StringBuilder temp = new StringBuilder();
            temp.append("\n\t").append(c.getBuildingMaterialProduct()).append(": ")
                    .append("\n\t\t").append("品牌：").append(c.getBuildingMaterialsBrand())
                    .append("\n\t\t").append("等级：").append(c.getBuildingMaterialGrade())
                    .append("\n\t\t").append("平米价格：").append(c.getBuildingMaterialsPrice()).append("元");

            detail.append(createRandomColorText(temp.toString()));
        }, (r1, r2) -> {});

        detail.append(l).append("\n---------------------------------------------------------------------------------\n");
        return detail.toString();
    }

    private IMenu calcPriceAndAddBuildingMaterial(DecorationBuildingMaterials decorationBuildingMaterials, String ratio, boolean hasRatio) {
        decorationList.add(decorationBuildingMaterials);
        BigDecimal temp = price;

        if (hasRatio) temp = temp.add(area.multiply(new BigDecimal(ratio)).multiply(decorationBuildingMaterials.getBuildingMaterialsPrice()));
        else temp = temp.add(area.multiply(decorationBuildingMaterials.getBuildingMaterialsPrice()));
        BigDecimal buildingMaterialsPrice = decorationBuildingMaterials.getBuildingMaterialsPrice();
        temp.add(hasRatio && (temp = area.multiply(new BigDecimal(ratio))) != null ? temp.multiply(buildingMaterialsPrice) : area.multiply(buildingMaterialsPrice));
        price = temp;
        return this;
    }

    private String createRandomColorText(String text) { return ColorTextTool.createRandomColorText(text); }
}

class Builder {
    public IMenu createDeluxeEuropeanStyle(double area) {
        return new DecorationCompany(area, DecorationStyle.DELUXE_EUROPEAN_STYLE)
                .addCeiling(new AIACeiling())
                .addCoating(new Dulux())
                .addFloor(new ShengxiangFloor())
                .addCeramicTile(new MarcopoloCeramicTile());
    }

    public IMenu createLightLuxuryCountrysideStyle(double area) {
        return new DecorationCompany(area, DecorationStyle.LIGHT_LUXURY_COUNTRYSIDE_STYLE)
                .addCeiling(new AUPUCeiling())
                .addCoating(new Nippon())
                .addFloor(new ElegantLivingFloor())
                .addCeramicTile(new MarcopoloCeramicTile());
    }

    public IMenu createModernSimplicityStyle(double area) {
        return new DecorationCompany(area, DecorationStyle.MODERN_SIMPLICITY_STYLE)
                .addCeiling(new AUPUCeiling())
                .addCoating(new Dulux())
                .addFloor(new ShengxiangFloor())
                .addCeramicTile(new DongPengCeramicTile());
    }
}

class DecorationClient {
    public static void main(String[] args) {
        Builder builder = new Builder();
        System.out.println(builder.createDeluxeEuropeanStyle(132.52).getDetail());
        System.out.println(builder.createLightLuxuryCountrysideStyle(98.52).getDetail());
        System.out.println(builder.createModernSimplicityStyle(85.43).getDetail());
    }
}

// 原型模式试卷系统
interface Question {
    String getQuestion();
    String getAnswer();
}

class ChoiceQuestion implements Question {
    private String type = "选择题";
    private String question;
    private Map<String, String> options;
    private String answer;

    public ChoiceQuestion(String question, Map<String, String> options, String answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public String getType() { return type; }
    @Override public String getQuestion() { return question; }
    @Override public String getAnswer() { return answer; }
    public Map<String, String> getOptions() { return options; }

    public void setType(String type) { this.type = type; }
    public void setSubject(String question) { this.question = question; }
    public void setOptions(Map<String, String> options) { this.options = options; }
    public void setAnswer(String answer) { this.answer = answer; }
}

class EssayQuestion implements Question {
    private String type = "问答题";
    private String question;
    private String answer;


    public EssayQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getType() { return type; }
    @Override public String getQuestion() { return question; }
    @Override public String getAnswer() { return answer; }

    public void setType(String type) { this.type = type; }
    public void setQuestion(String question) { this.question = question; }
    public void setAnswer(String answer) { this.answer = answer; }
}

class TopicDisorderUtil {
    private TopicDisorderUtil() {}
    public static ChoiceQuestion randomOptions(ChoiceQuestion choiceQuestion) {
        Set<String> originKey = choiceQuestion.getOptions().keySet();
        Map<String, String> originOptions = choiceQuestion.getOptions();
        Map<String, String> newOptions = new HashMap<>();
        List<String> randomOptions = new ArrayList<>(originKey);

        Collections.shuffle(randomOptions);

        int index = 0;
        String randomAnswer = "";
        String answer = choiceQuestion.getAnswer();
        for (String key: originKey) {
            String randomKey = randomOptions.get(index++);
            if (Objects.equals(key, answer)) randomAnswer = randomKey;
            newOptions.put(randomKey, originOptions.get(key));
        }

        return new ChoiceQuestion(choiceQuestion.getQuestion(), newOptions, randomAnswer);
    }
}

class QuestionBank implements Cloneable {
    private ArrayList<ChoiceQuestion> choiceQuestionList = new ArrayList<>();
    private ArrayList<EssayQuestion> essayQuestionList = new ArrayList<>();
    private String candidateName;
    private String examineeNumber;
    private String title;

    public QuestionBank appendQuestion(ChoiceQuestion choiceQuestion) {
        choiceQuestionList.add(choiceQuestion);
        return this;
    }

    public QuestionBank appendQuestion(EssayQuestion essayQuestion) {
        essayQuestionList.add(essayQuestion);
        return this;
    }

    @Override protected Object clone() throws CloneNotSupportedException {
        QuestionBank QuestionBank = (QuestionBank) super.clone();
        QuestionBank.choiceQuestionList = (ArrayList<ChoiceQuestion>) choiceQuestionList.clone();
        QuestionBank.essayQuestionList = (ArrayList<EssayQuestion>) essayQuestionList.clone();

        Collections.shuffle(QuestionBank.choiceQuestionList);
        Collections.shuffle(QuestionBank.essayQuestionList);

        ArrayList<ChoiceQuestion> choiceQuestions = QuestionBank.choiceQuestionList;
        for (ChoiceQuestion choiceQuestion: choiceQuestions) {
            ChoiceQuestion question = TopicDisorderUtil.randomOptions(choiceQuestion);
            choiceQuestion.setOptions(question.getOptions());
            choiceQuestion.setAnswer(question.getAnswer());
        }

        return QuestionBank;
    }

    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("考生：").append(candidateName).append("\n考号：").append(examineeNumber)
                .append("\n---------------------------------------------------------------------------------\n");
        result.append(create("一、选择题", choiceQuestionList, (options, r) -> {
            Iterator<Map.Entry<String, String>> iterator = options.entrySet().iterator();
            while (iterator.hasNext()) {
                StringBuilder ops = new StringBuilder();
                Map.Entry<String, String> next = iterator.next();
                ops.append(next.getKey()).append(": ").append(next.getValue()).append("\n");
                r.append(ColorTextTool.createRandomColorText(ops.toString()));
            }
        }));
        result.append(create("二、问答题", essayQuestionList, null));
        return result.toString();
    }

    private String create(String title, List<? extends Question> questionList, BiConsumer<Map<String, String>, StringBuilder> func) {
        StringBuilder result = new StringBuilder(title);
        result.append("\n");

        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            StringBuilder r = new StringBuilder();
            if (!isChoiceQuestion(question)) {
                r.append("第 ").append(i + 1).append(" 题：").append(question.getQuestion());
                r.append("答案：").append(question.getAnswer()).append("\n");
                result.append(ColorTextTool.createRandomColorText(r.toString()));
            } else {
                result.append("第 ").append(i + 1).append(" 题：").append(question.getQuestion()).append("\n");
                func.accept(((ChoiceQuestion) question).getOptions(), result);
                r.append("答案：").append(question.getAnswer()).append("\n\n");
                result.append(ColorTextTool.createRandomColorText(r.toString()));
            }
        }

        return result.toString();
    }

    private boolean isChoiceQuestion(Question question) { return question instanceof ChoiceQuestion; }

    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }
    public String getExamineeNumber() { return examineeNumber; }
    public void setExamineeNumber(String examineeNumber) { this.examineeNumber = examineeNumber; }
}

class QuestionBankController {
    private QuestionBank questionBank = new QuestionBank();
    public void initQuestion() {
        createChoiceQuestion("JAVA所定义的版本中不不包括", "D", "JAVA2 EE", "JAVA2 Card", "JAVA2 ME", "JAVA2 HE", "JAVA2 SE");
        createChoiceQuestion(
                "下列列说法正确的是",
                "A",
                "JAVA程序的main方法必须写在类里面",
                "JAVA程序中可以有多个main方法",
                "JAVA程序中类名必须与文件名一样",
                "JAVA程序的main⽅法中如果只有⼀条语句，可以不用{}(大括号)括起来"
        );
        createChoiceQuestion(
                "变量命名规范说法正确的是",
                "B",
                "变量由字母、下划线、数字、$符号随意组成；",
                "变量不能以数字作为开头；",
                "A和a在java中是同一个变量；",
                "不同类型的变量，可以起相同的名字；"
        );
        createChoiceQuestion("以下()不是合法的标识符", "C", "STRING", "x3x", "void", "de$f");
        createChoiceQuestion("表达式(11+3*8)/4%3的值是", "D", "31", "0", "1", "2");

        createEssayQuestion("小红马和小黑马生的小马几条腿？", "4条腿");
        createEssayQuestion("铁棒打头疼还是木棒打头疼？", "头最疼");
        createEssayQuestion("什么床不能睡觉？", "牙床");
        createEssayQuestion("为什么好马不吃回头草？", "后面的草没了");
    }

    public String createPaper(String candidateName, String examineeNumber) throws CloneNotSupportedException {
        QuestionBank questionBankClone = (QuestionBank) questionBank.clone();
        questionBankClone.setCandidateName(candidateName);
        questionBankClone.setExamineeNumber(examineeNumber);
        return questionBankClone.toString();
    }

    private void createChoiceQuestion(String question, String answer, String... ops) {
        Map<String, String> options = new HashMap<>();
        for (int i = 0; i < ops.length; i++) options.put(String.valueOf((char) (65 + i)), ops[i]);
        questionBank.appendQuestion(new ChoiceQuestion(question, options, answer));
    }

    private void createEssayQuestion(String question, String answer) { questionBank.appendQuestion(new EssayQuestion(question, answer)); }
}

class QuestionBankControllerTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        QuestionBankController questionBankController = new QuestionBankController();
        questionBankController.initQuestion();
        System.out.println(questionBankController.createPaper("花花", "1000001921032"));
        System.out.println(questionBankController.createPaper("豆豆", "1000001921051"));
        System.out.println(questionBankController.createPaper("大宝", "1000001921987"));
    }
}

// 规格模式
class SpecificationUser {
    private String name;
    private int age;
    public SpecificationUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() {return age;}
    public void setAge(int age) { this.age = age; }

    @Override public String toString() { return "用户名：" + name + ", 年龄：" + age; }
}

interface IUserSpecification {
    boolean isSatisfiedBy(SpecificationUser user);
    IUserSpecification and(IUserSpecification specification);
    IUserSpecification or(IUserSpecification specification);
    IUserSpecification not();
}

abstract class CompositeSpecification implements IUserSpecification {
    public IUserSpecification and(IUserSpecification specification) { return new AndSpecification(this, specification); }
    public IUserSpecification or(IUserSpecification specification) { return new OrSpecification(this, specification); }
    public IUserSpecification not() { return new NotSpecification(this); }
    public abstract boolean isSatisfiedBy(SpecificationUser user);
}

class AndSpecification extends CompositeSpecification {
    private IUserSpecification left;
    private IUserSpecification right;

    public AndSpecification(IUserSpecification left, IUserSpecification right) {
        this.left = left;
        this.right = right;
    }

    @Override public boolean isSatisfiedBy(SpecificationUser user) { return left.isSatisfiedBy(user) && right.isSatisfiedBy(user); }
}

class OrSpecification extends CompositeSpecification {
    private IUserSpecification left;
    private IUserSpecification right;

    public OrSpecification(IUserSpecification left, IUserSpecification right) {
        this.left = left;
        this.right = right;
    }

    @Override public boolean isSatisfiedBy(SpecificationUser user) { return left.isSatisfiedBy(user) || right.isSatisfiedBy(user); }
}

class NotSpecification extends CompositeSpecification {
    private IUserSpecification specification;
    public NotSpecification(IUserSpecification specification) { this.specification = specification; }
    @Override public boolean isSatisfiedBy(SpecificationUser user) { return !specification.isSatisfiedBy(user); }
}

// 业务
final class UserDataBase {
    private static final List<SpecificationUser> userList = new ArrayList<>();
    static {
        Random random = new Random();
        String[] userNames = new String[]{ "苏大", "牛二", "张三", "李四", "王五", "赵六", "马七", "杨八", "侯九", "布十" };
        for (int i = 0; i < userNames.length; i++) userList.add(new SpecificationUser(userNames[i], 30 + random.nextInt(20)));
    }

    public static List<SpecificationUser> getUserList() { return Collections.unmodifiableList(userList); }
}

interface IUserProvider { List<SpecificationUser> findUser(List<SpecificationUser>userList, IUserSpecification specification); }
final class UserProvider implements IUserProvider {
    @Override public List<SpecificationUser> findUser(List<SpecificationUser>userList, IUserSpecification specification) {
        return userList.stream().filter(specification::isSatisfiedBy).collect(Collectors.toList());
    }
}

class UserByAgeThan extends CompositeSpecification {
    private int age;
    public UserByAgeThan(int age) { this.age = age; }
    @Override public boolean isSatisfiedBy(SpecificationUser user) { return user.getAge() > age; }
}

class UserByNameThan extends CompositeSpecification {
    private String name;
    public UserByNameThan(String name) { this.name = name; }
    @Override public boolean isSatisfiedBy(SpecificationUser user) { return Objects.equals(user.getName(), name); }
}

class SpecificationTest {
    public static void main(String[] args) {
        IUserProvider userProvider = new UserProvider();

        /*
        * !(user.name == "赵六" && user.age > 35 || user.name == 苏大 && user.age > 30)
        *                                   ↕ ↕
        * ((user.name != "赵六" || user.age <= 35) && (user.name != 苏大 || user.age <= 30))
        */

        List<SpecificationUser> filterUserList =
                userProvider.findUser(UserDataBase.getUserList(),
                        userByAgeThan(35).and(userByNameThan("赵六")).or(userByNameThan("苏大").and(userByAgeThan(30))).not());

        filterUserList.forEach(System.out::println);
    }

    private static CompositeSpecification userByAgeThan(int age) { return new UserByAgeThan(age); }
    private static CompositeSpecification userByNameThan(String name) { return new UserByNameThan(name); }
}

/*
* 罗马数字是阿拉伯数字传入之前使用的一种数码。其采用七个罗马字母作数字、即Ⅰ（1）、X（10）、C（100）、M（1000）、V（5）、L（50）、D（500）。
* 记数的方法：相同的数字连写，所表示的数等于这些数字相加得到的数，如 Ⅲ=3；
* 小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数，如 Ⅷ=8、Ⅻ=12；
* 小的数字（限于 Ⅰ、X 和 C）在大的数字的左边，所表示的数等于大数减小数得到的数，如 Ⅳ=4、Ⅸ=9、IC=99；
* 在一个数的上面画一条横线，表示这个数增值 1,000 倍，如=5000。
*
* 有两条须注意掌握：
* 基本数字 Ⅰ、X 、C 中的任何一个、自身连用构成数目、或者放在大数的右边连用构成数目、都不能超过三个；放在大数的左边只能用一个；
* 不能把基本数字 V 、L 、D 中的任何一个作为小数放在大数的左边采用相减的方法构成数目；放在大数的右边采用相加的方式构成数目、只能使用一个；
* */
class RomanNumerals {
    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    static boolean isRomanNumeral(String s) { return ROMAN.matcher(s).matches(); }
}

class RomanNumeralsTest {
    public static void main(String[] args) {
        System.out.println(RomanNumerals.isRomanNumeral("MCMXCIV"));
    }
}

public class JavaPractice implements Cloneable {
    // 演示实例化一个对象时的初始化顺序
    // Note: 初始化顺序为先静态成员和方法，然后是非静态成员和方法，最后是构造方法(所有的初始化顺序是按照书写顺序进行初始化的)
    // 在继承情况中，实例化一个导出类(派生类)时，先按照前面所述的初始化顺序初始化基类，然后层层初始化基类，当所有基类都已完成了初始化工作时，这时的导出类(派生类)才进行初始化
    /*
    * 每当初始化一个类时，如果存在继承情况时，则先运行顶层的基类静态块、静态成员、静态方法，然后层层往下运行每一层继承而来的类中的静态块、静态成员、静态方法
    * 最后运行派生类的静态块、静态成员、静态方法，接着在回过头初始化基类的非静态成员变量和方法，然后往下每一层都要初始化每一层继承而来的类中的非静态成员变量和方法
    * 直到派生类的非静态成员变量和方法初始完毕，最后再一次回过头来从基类开始，一直到派生类，每一层都要运行构造器，以完成最后的初始化，此时所有的初始化工作都已完成！
    */
    private static final PortableLunch portableLunch = new PortableLunch();
    private static final Bread bread = new Bread();
    private static final Cheese cheese = new Cheese();
    private static final Lettuce lettuce = new Lettuce();
    public JavaPractice() { System.out.println("JavaPractice()"); }
    private static final int MIN_INITIAL_CAPACITY = 5;

    public interface WhiteSpaceChars<T, U, R> {
        void run(T t, U u, R r);
    }

    private static final Logger logger = Logger.getLogger("com.horstmann.core.java");

    static {
        try {
            logger.setLevel(Level.ALL);
            logger.addHandler(
                    new FileHandler(
                            System.getProperty("user.dir") + File.separator + "logger" + File.separator + "myapp.log",
                            0/* 在打开另一个文件之前允许写入一个文件的近似最大字节数(0表示无限制) */,
                            10/* 循环序列的文件数量 */
                    )
            );
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't create log file handler", e);
        }
    }

    static class Parcel { void sounds() {
            System.out.println("静态内部类实例化");
        } }

    public static void main(String[] args) {
        GenericWriting.f1();
        GenericWriting.f2();

        new RoundGlyph(5);

        Mill mill = new Mill();
        Grain grain = mill.process();
        System.out.println(grain);
        mill = new WheatMill();
        grain = mill.process();
        System.out.println(grain);

        new Parcel().sounds();

        JavaPractice target = new JavaPractice();

        WithInner withInner = new WithInner();
        InheritInner inheritInner = new InheritInner(withInner);
        inheritInner.run();
        inheritInner.parentMethodRun();

        target.println("14975删除2位的最小数为: " + target.getDeleteDigitsAfterMinimumNumber(14975, 2));
        target.println("541270936删除3位的最小数为: " + target.getDeleteDigitsAfterMinimumNumber(541270936, 3));
        target.println("6540000123删除3位的最小数为: " + target.getDeleteDigitsAfterMinimumNumber(6540000123L, 3));

        int[] lengths = {6, 10, 20, 32};
        double[] moneys = {10000, 12100.462, 16000};

        Arrays.stream(lengths).forEach(length -> target.println("长度" + length + "扩容之后为: " + target.alloc(length)));
        Arrays.stream(moneys).forEach(money -> target.println(target.transferMoney(money)));

        StringBuilder pythagoreanTriplesStringBuilder = new StringBuilder();
        target.createPythagoreanTriples(1, 100, 5).forEach(items ->
                pythagoreanTriplesStringBuilder.append("( ").append(items[0]).append(", ").append(items[1]).append(", ").append(items[2]).append(" )")
        );

        target.println(target.join(", ", "", "", pythagoreanTriplesStringBuilder.toString()));

        Map<Boolean, List<Integer>> primeList = PrimeNumbersCollector.partitionPrimesWithCustomCollector(30);
        Function<Boolean, String> covertStringJoining = isPrime -> target.join(", ", "[ ", " ]", primeList.get(isPrime));
        target.println("30 以内的质数为: " + covertStringJoining.apply(true) + ", 30 以内的非质数为: " + covertStringJoining.apply(false));

        String paragraph = " Nel    mezzo del cammin  di nostra  vita mi   ritroval in una  selva oscura hello   world  dritta via era   smarrita ";
        int counter = StreamSupport.stream(new WordCounterSpliterator(paragraph), true)
                .reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine)
                .getCounter();
        target.println("Found " + counter + " words.");

        Order order = MixedBuilder.forCustomer(
                "BigBank",
                MixedBuilder.buy(t -> t.quantity(80).stock("IBM").on("NYSE").price(125.00)),
                MixedBuilder.sell(t -> t.quantity(50).stock("GOOGLE").on("NASDAQ").price(125.00))
        );

        target.println(order.getValue());

        NewPermission newPermission = new NewPermission();
        newPermission.enable(NewPermission.ALLOW_UPDATE);
        newPermission.enable(NewPermission.ALLOW_DELETE);
        newPermission.enable(NewPermission.ALLOW_INSERT);
        target.println(newPermission.isAllow(NewPermission.ALLOW_SELECT));
        newPermission.resetPermission();
        target.println(newPermission.getFlag());

        String hello = "hello";
        target.println(hello.codePointAt(hello.offsetByCodePoints(0, 3)));

        class Number {
            private final int num;

            public Number(int num) {
                this.num = num;
            }

            public int getNum() {
                return num;
            }
        }

        Random random = new Random();
        List<Number> numberList = new ArrayList<>();
        IntStream.rangeClosed(0, 7).forEach(item -> numberList.add(new Number(random.nextInt(100))));
        IntSummaryStatistics summaryStatistics = numberList.stream().collect(Collectors.summarizingInt(Number::getNum));
        target.println(summaryStatistics.getSum());

        /*
         * 用来计算抽奖中奖的概率。例如，如果必须重1 ~ 50之间的数字中取6个数字来抽奖，那么会有(50 * 49 * 48 * 47 * 46 * 45) / (1 * 2 * 3 * 4 * 5 * 6)种可能的结果
         * 所以中奖的几率是 1 / 15890700。公式为: n * (n - 1) * (n - 2) * ... * (n - k + 1) / 1 * 2 * 3 * 4 * ... * k
         */
        BiFunction<Integer, Integer, BigInteger> createLotteryOdds = (n, k) -> {
            if (n < k) {
                return BigInteger.ZERO;
            }
            BigInteger lotteryOdds = BigInteger.valueOf(1);
            for (int i = 1; i <= k; i++) {
                lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
            }
            return lotteryOdds;
        };

        BigInteger lotteryOdds = createLotteryOdds.apply(50, 6);
        target.println(lotteryOdds.equals(BigInteger.ZERO) ? "您没有机会中奖..." : "您中奖的概率为: 1 / " + lotteryOdds);

        List<Integer> list = new ArrayList<>(List.of(6, 7, 4, 6, 7, 8));
        target.rotate(list, 2);
        target.println(list.toString());
        target.createRandomAccessFile();

//        try (var socket = new Socket("time-a.nist.gov", 13)) {
//            var in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8);
//            while (in.hasNextLine()) target.println(in.nextLine());
//        } catch (IOException e) { logger.fine("Socket exception."); e.printStackTrace(); }

        target.createCalendar(60, 4);

        /*
         * CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
         * 如果cookie需要在重定向中从一个站点发送给另一个站点，那么可以像下面这样配置一个全局的cookie处理器，然后，cookie就可以被正确的包含在重定向请求中了。*/

        // HttpPost.post("fileupload.properties", true);
        // target.createDatabaseData();
        // ViewDBFrame.init();
        BiFunction<LocalDate, LocalDate, Function<Period, List<LocalDate>>> createStepDateFactory = (startLocalDate, endLocalDate) ->
                period -> startLocalDate.datesUntil(endLocalDate, period).collect(Collectors.toList());
        Function<Period, List<LocalDate>> generatorStepDate = createStepDateFactory.apply(LocalDate.now(), LocalDate.now().plusMonths(3));

        target.println(generatorStepDate.apply(Period.ofMonths(1)));
        target.println(generatorStepDate.apply(Period.ofWeeks(3)));
        target.println(generatorStepDate.apply(Period.ofDays(15)));

        target.println(NumberFormat.getCurrencyInstance(Locale.GERMAN).format(123456.78));

        // NumberFormatFrame.init();
        // new GridBag().init();
        // Retire.init();

        // new ButtonFrame();

        class TrackHandler implements InvocationHandler {
            private Object target;

            public TrackHandler(Object t) {
                this.target = t;
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName());
                return method.invoke(target, args);
            }
        }

        TrackHandler trackHandler = new TrackHandler("hello world");
        Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Comparable.class}, trackHandler);
        target.println(proxy.toString());

        // TemporalAdjusters类可以很方便的计算出基于当前的指定日期到下一个日期为多少！TemporalAdjusters是转换到另一个Temporal接口日期，因为所有日期类都实现了Temporal接口
        // ofDateAdjuster是TemporalAdjuster类的静态工厂方法，它接受一个UnaryOperator<LocalDate>类型的参数，即：原子接口，LocalDate -> LocalDate
        // 当前的LocalDate类通过ofDateAdjuster静态工厂方法转换为另一个全新的LocalDate类！
        // 通过TemporalAdjusters类可以将日期调整到下个周日、下个工作日、或者是本月的最后一天(通过lastDayOfMonth方法获取本月的最后一天)
        // TemporalAdjusters类中的所有工厂方法调用结果是with方法的参数，即：LocalDate.now().with(lastDayOfMonth())
        // 计算下一个工作日是哪天(周一 ~ 周五为工作日，如果当前星期为周六，则下一个工作日应该为下周一，此时需要增加两天！如果当前为周五，则需要增加三天才能到下一个工作日，即：下周一)
        LocalDate localDate = LocalDate.now().with(TemporalAdjusters.ofDateAdjuster(temporal -> {
            // 传递一个TemporalField参数给get方法访问同样的信息。TemporalField是一个接口，它定义了如何访问temporal对象某个字段的值
            // ChronoField枚举实现了这一接口，所以可以很方便的使用get方法得到枚举元素的值。即：日期的get的方法参数类型为TemporalField
            // ChronoField枚举为：public enum ChronoField implements TemporalField
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK)); // 计算当前为星期几
            int dayToAdd = 1;
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                dayToAdd += 2;
            } else if (dayOfWeek == DayOfWeek.SATURDAY) {
                dayToAdd++;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }));
        target.println("The next work day is: " + localDate.toString() + " " + localDate.getDayOfWeek());
        target.println(TimeZone.getDefault().toZoneId());
        target.println(LocalDate.of(2014, Month.MARCH, 18).atStartOfDay(TimeZone.getDefault().toZoneId()));

        // 最长公共前缀
        Function<String[], String> longestCommonPrefix = stringArray -> {
            int stringArrayLength = stringArray.length;

            if (stringArrayLength < 2) {
                return "";
            }

            for (String str : stringArray) {
                if (str.isEmpty() || !Pattern.matches("[a-z]+", str)) {
                    throw new IllegalArgumentException("Each string in the string array must consist of a-z characters");
                }
            }

            StringBuilder resultBuilder = new StringBuilder();
            String shortestString = stringArray[0];
            int shortestStringLength = shortestString.length();

            for (int i = 0; i < shortestStringLength; i++) {
                boolean isFindLongestCommonPrefix = true;
                for (int j = 1; j < stringArrayLength; j++) {
                    String stringItem = stringArray[j];
                    int stringItemLength = stringItem.length();
                    if (shortestString.charAt(i) != stringItem.charAt(i)) {
                        isFindLongestCommonPrefix = false;
                        break;
                    }
                    if (shortestStringLength > stringItemLength) {
                        shortestStringLength = stringItemLength;
                    }
                }
                if (!isFindLongestCommonPrefix) {
                    break;
                }
                resultBuilder.append(shortestString.charAt(i));
            }

            return resultBuilder.isEmpty() ? "没有找到最长公共前缀..." : "最长公共前缀为: " + resultBuilder;
        };

        target.println(longestCommonPrefix.apply(new String[]{"flower", "flow", "flight"}));
        target.println(longestCommonPrefix.apply(new String[]{"dog", "racecar", "car"}));

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<Integer> completableFuture1 = new CompletableFuture<>();
        CompletableFuture<Integer> completableFuture2 = new CompletableFuture<>();
        CompletableFuture<Integer> completableFutureResult = completableFuture1.thenCombine(completableFuture2, Integer::sum);
        executorService.submit(() -> completableFuture1.complete(work1(1337000)));
        executorService.submit(() -> completableFuture2.complete(work2(10000)));
        try {
            // 前两个线程运行的任务确实没有进行阻塞，completableFutureResult.get()会等待前两个线程全部运行完成之后，才会进行相加操作，最后返回结果。
            // 这里的不阻塞是相对于最后一个线程而言的。
            target.println("两个函数相加的最后结果为: " + completableFutureResult.get());
            executorService.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ArithmeticCell arithmeticCell = new ArithmeticCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(arithmeticCell::setLeft);
        c2.subscribe(arithmeticCell::setRight);

        c1.publish(10);
        c2.publish(20);
        c1.publish(15);

//        long start = System.nanoTime();
//        target.println(target.findPrices("myPhone27S"));
//        long duration = (System.nanoTime() - start) / 1_000_000;
//        target.println("Done in " + duration + " msecs");
        long start1 = System.nanoTime();
        CompletableFuture.allOf(
                target.findPricesStream("myPhone27S")
                        .map(f -> f.thenAccept(result -> target.println(result + "(done in " + ((System.nanoTime() - start1) / 1_000_000) + "msecs)")))
                        .toArray(CompletableFuture[]::new)).join();
        target.println("All shops have now responded in " + ((System.nanoTime() - start1) / 1_000_000) + " msecs");

        BiConsumer<String, Boolean> createReleaseSubscription =
                (name, isProcessor) -> target.getTemperatures(name, isProcessor).subscribe(new TempSubscriber());

        createReleaseSubscription.accept("New York", true);
        createReleaseSubscription.accept("San Francisco", false);
        target.println("结合器演示：" + target.repeat(3, x -> x + "Hello").apply("Hello"));

        // 模拟线程池
        // BasicThreadPool.runExample();
        int a = 0xa1;
        int b = -1 << 1;    // 先左移，然后在循环内每次都无符号右移，直至所有的二进制位都被移出
        while (a != 0) {
            target.println("一个最高有效位为1的二进制数字的所有二进制位每次移出的结果为: " + Integer.toBinaryString(a >>= 1));
        }
        // 这里必须要使用无符号右移，不然b的值为一个负数，当使用有符号右移时还是负数，会造成死循环
        while (b != 0) {
            target.println("一个所有位都为1的二进制数字的所有二进制位每次移出的结果为: " + Integer.toBinaryString(b >>>= 1));
        }
        target.f(1, 'a');
        target.f('a', 'b');

        String testString = "1,2,3,4,5";
        target.println("===================================分割线======================================");
        target.println(Arrays.toString(target.split(testString, ",", 1)));
        target.println("===================================分割线======================================");
        target.println(Arrays.toString(target.split(testString, ",", 2)));
        target.println("===================================分割线======================================");
        target.println(Arrays.toString(target.split(testString, ",", 3)));
        target.println("===================================分割线======================================");
        target.println(Arrays.toString(target.split(testString, ",", 4)));
        target.println("===================================分割线======================================");

        target.println(target.substring("123456", 0, 3));
        target.println(target.substring("123456", 8, -2));

        class IteratorClass implements Iterable<String> {
            protected final String[] words = ("And that is how we know the Earth to be banana-shaped.").split(" ");
            @Override
            public Iterator<String> iterator() {
                return new Iterator<>() {
                    private int index = 0;

                    @Override
                    public boolean hasNext() {
                        return index < words.length;
                    }

                    @Override
                    public String next() {
                        return words[index++];
                    }
                };
            }
        }

        for (String s: new IteratorClass()) {
            target.print(s + " ");
        }
        target.println();
        target.println("===================================分割线======================================");
        // 获取所有的操作系统环境变量
        for (Map.Entry<String, String> entry: System.getenv().entrySet()) {
            target.println(entry.getKey() + ": " + entry.getValue());
        }
        target.println("===================================分割线======================================");

        DynamicFields dynamicFields = new DynamicFields(3);
        target.println(dynamicFields);
        try {
            dynamicFields.setField("d", "A value for d");
            dynamicFields.setField("number", 47);
            dynamicFields.setField("number2", 48);
            target.println(dynamicFields);
            dynamicFields.setField("d", "A new value for d");
            dynamicFields.setField("number3", 11);
            target.println("dynamicField: " + dynamicFields);
            target.println("dynamicFields.getField(\"d\"): " + dynamicFields.getField("d"));
            Object field = dynamicFields.setField("d", null);
        } catch (NoSuchFieldException | DynamicFieldsException e) {
            e.printStackTrace(System.out);
        }

        try {
            LostMessage lostMessage = new LostMessage();
            try {
                lostMessage.f();
            } finally {
                lostMessage.dispose();
            }
        } catch (Exception e) { target.println(e); }

        class A {}
        class B extends A {}
        class C extends B {}
        class D extends C {}

        target.println(
                target.join(
                        " -> ",
                        "[ ",
                        " ]",
                        target.findInheritanceStructures(D.class, new LinkedList<>()).toArray(new String[0])
                )
        );

        LinkedStack<String> linkedStack = new LinkedStack<>();
        for (String str: "Phasers or stun!".split(" ")) {
            linkedStack.push(str);
        }
        String popString;
        while((popString = linkedStack.pop()) != null) {
            target.println(popString);
        }

        target.println(new Store(1, 2, 2));

        Mixins.runMixin();
        Decorator.run();
        DecoratorDrinkExample.run();
        DynamicProxyMixin.run();
        CustomFunctional.run();
        CollectionDataTest.run();

        // 测试差集
        // MobilePhone mobilePhone = new MobilePhone();
        // int[][] lala11 = { { 0 }, { 0, 1 }, { 4 }, { 4, 9 }, { 0, 1, 2, 3, 4, 6 }, { 1, 3, 4, 7, 8 }, { 0, 1, 2, 4, 5, 6, 8 }};
        // for (int i = 0; i < lala11.length; i++) System.out.println(Arrays.toString(mobilePhone.generatorComplement(lala11[i])));
        new MobilePhone().phoneNo();

        // 调用javax.swing.Timer类来实现每隔delay时间后，系统响铃一次。如果不点击showMessageDialog按钮的话，则程序会一直运行，直到点击了弹出框中的OK按钮才停止
        // javax.swing.Timer t = new javax.swing.Timer(1000, new TimePrinter());
        // t.start();
        // JOptionPane.showMessageDialog(null, "Quit program?");

        // Hello
        // Hello World
        Function<? super Object, ?> function = x -> { System.out.println(x); return x; };
        ((Consumer<String>) System.out::println).andThen(x -> System.out.println(x + " World")).accept("Hello");
        ((Function<String, String>) str -> str).compose(x -> x + " World").andThen(function).apply("Hello");

        // 两个字符串的首字母排序，返回最小的那一个
        BinaryOperator.minBy(Comparator.comparingInt((String a1) -> a1.charAt(0))).andThen(function).apply("Hello", "World");   // 返回Hello
        BinaryOperator.maxBy(Comparator.comparingInt((String a1) -> a1.charAt(0))).andThen(function).apply("Hello", "World");   // 返回World

        new DirList("D:/code/JavaPractice/com.horstmann.greetsvc/src/com/horstmann/greetsvc/internal", "^\\p{Alpha}+\\.java$").run();
    }

    public LinkedList<String> findInheritanceStructures(Class<?> cls, LinkedList<String> linkedList) {
        if (cls == null) {
            return linkedList;
        }
        linkedList.add(cls.getSimpleName());
        return findInheritanceStructures(cls.getSuperclass(), linkedList);
    }

    public String[] split(String str, String regex) { return split(str, regex, 0); }
    public String[] split(String str, String regex, int limit) {
        if (limit < 0) {
            limit = 0;
        }
        char ch;
        final int REGEX_LENGTH = regex.length();
        if (
                REGEX_LENGTH == 1 && indexOf(".$|()[{^?*+\\", ch = charAt(regex, 0)) == -1 ||
                REGEX_LENGTH == 2 && charAt(regex, 0) == '\\' &&
                        ((ch = charAt(regex, 1)) - '0' | '9' - ch) < 0 &&
                        ((ch - 'a') | 'z' - ch) < 0 &&
                        ((ch - 'A') | 'Z' - ch) < 0
        ) {
            int findIndex = 0;
            int nextIndex;
            boolean hasLimit = limit > 0;
            ArrayList<String> list = new ArrayList<>();
            while ((nextIndex = indexOf(str, ch, findIndex)) != -1) {
                if (!hasLimit || list.size() < limit - 1) {
                    list.add(substring(str, findIndex, nextIndex));
                    findIndex = nextIndex + 1;
                } else {
                    list.add(substring(str, findIndex, str.length()));
                    findIndex = REGEX_LENGTH;
                    break;
                }
            }

            if (findIndex == 0) {
                return new String[] { str };
            }

            if (!hasLimit || list.size() < limit) {
                list.add(substring(str, findIndex, str.length()));
            }

            int resultSize = list.size();
            if (limit == 0) {
                while (resultSize > 0 && list.get(resultSize - 1).isEmpty()) {
                    resultSize--;
                }
            }

            return list.subList(0, resultSize).toArray(new String[resultSize]);
        }

        return Pattern.compile(regex).split(str, limit);
    }

    public String substring(String str, int fromIndex, int endIndex) {
        if (fromIndex == 0 && endIndex == str.length()) {
            return str;
        }

        if (endIndex < 0 && fromIndex > 0) {
            int delta = fromIndex + endIndex;
            if (delta < 0 && fromIndex < str.length()) {
                endIndex = fromIndex;
            } else {
                endIndex = Math.min(delta, str.length());
            }
            fromIndex = 0;
        }

        if (fromIndex < 0 && endIndex < 0) {
            fromIndex = 0;
            endIndex = str.length();
        }

        if (fromIndex > endIndex && fromIndex < str.length()) {
            int temp = endIndex;
            endIndex = fromIndex;
            fromIndex = temp;
        }

        if (endIndex > str.length()) {
            endIndex = str.length();
        }
        int subLen = endIndex - fromIndex;
        if (subLen == 0 || str.length() == 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (int i = fromIndex; i < endIndex; i++) {
            builder.append(str.charAt(i));
        }
        return builder.toString();
    }

    public char charAt(String str, int index) {
        byte[] bytes = stringToBytes(str);
        if (index < 0 || index >= bytes.length) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return (char)(bytes[index] & 0xff);
    }

    public int indexOf(String str, int ch) { return indexOf(str, ch, 0); }
    public int indexOf(String str, int ch, int fromIndex) {
        if (!canEncode(ch)) {
            return -1;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        } else if (fromIndex >= str.length()) {
            return -1;
        }
        return indexOfChar(stringToBytes(str), ch, fromIndex);
    }

    public int indexOfChar(byte[] bytes, int ch, int fromIndex) {
        byte c = (byte) ch;
        for (int i = fromIndex; i < bytes.length; i++) {
            if (bytes[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public boolean canEncode(int ch) { return ch >>> 8 == 0; }

    public byte[] stringToBytes(String str) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            list.add((int) str.charAt(i));
        }
        byte[] resultBytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resultBytes[i] = list.get(i).byteValue();
        }
        return resultBytes;
    }

    public void f(float a, Character... chars) { println("first"); }
    public void f(char a, Character... chars) { println("second"); }

    public <A> Function<A, A> repeat(int n, Function<A, A> f) { return n <= 1 ? UnaryOperator.identity() : f.compose(repeat(n - 1, f)); }

    // Publisher、Subscriber、Subscription使用示例，即：发布-订阅演示
    public record TempInfo(String town, int temp) {
        public static Random random = new Random();
        public static TempInfo fetch(String town, ExecutorService executorService) {
            if (random.nextInt(10) == 0) {
                executorService.shutdown();
            }
            return new TempInfo(town, random.nextInt(100));
        }

        @Override
        public String toString() { return town + " : " + temp; }
    }

    public record TempSubscription(Subscriber<? super TempInfo> subscriber, String town) implements Subscription {
        public static final ExecutorService executorService = Executors.newSingleThreadExecutor();
        @Override
        public void request(long n) {
            try {
                if (executorService.isShutdown() && executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                    return;
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
            executorService.submit(() -> {
                for (long i = 0L; i < n; i++) {
                    try { subscriber.onNext(TempInfo.fetch(town, executorService)); } catch (Exception e) { subscriber.onError(e); break; }
                }
            });
        }

        @Override
        public void cancel() { subscriber.onComplete(); }
    }

    public static class TempSubscriber implements Subscriber<TempInfo> {
        private Subscription subscription;

        @Override
        public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(TempInfo tempInfo) {
            System.out.println(tempInfo);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable t) { System.err.println( t.getMessage()); }

        @Override
        public void onComplete() { System.out.println("Done!"); }
    }

    // Processor接口适合对数据进行转换时使用是非常合适的，比如这里的华氏摄氏度转换为摄氏温度，如果是常规的发布-订阅的话，不需要使用这个接口！
    // 这个实现Processor接口的类相当于一个代理类，对原来的Subscriber类进行了一次拦截，通过拦截可以实现对数据的修改，然后再重新订阅！
    // 注意，重新订阅的Subscriber类是这个代理的Subscriber类
    public static class TempProcessor implements Processor<TempInfo, TempInfo> {
        private Subscriber<? super TempInfo> subscriber;

        @Override
        public void subscribe(Subscriber<? super TempInfo> subscriber) { this.subscriber = subscriber; }

        @Override
        public void onNext(TempInfo tempInfo) { subscriber.onNext(new TempInfo(tempInfo.town(), (tempInfo.temp() - 32) * 5 / 9)); }

        @Override
        public void onSubscribe(Subscription subscription) { subscriber.onSubscribe(subscription); }

        @Override
        public void onError(Throwable throwable) { subscriber.onError(throwable); }

        @Override
        public void onComplete() { subscriber.onComplete(); }
    }

    public Publisher<TempInfo> getTemperatures(String town, boolean isProcessor) {
        return subscriber -> {
            var processor = isProcessor ? new TempProcessor() : subscriber;
            if (isProcessor) {
                ((TempProcessor) processor).subscribe(subscriber);
            }
            processor.onSubscribe(new TempSubscription((Subscriber<? super TempInfo>) processor, town));
        };
    }

    // CompletableFuture使用示例
    private final List<Shop> shops =
            Stream.of("BestPrice", "LetsSaveBig", "MyFavoriteShop", "BuyItAll", "ShopEasy").map(Shop::new).collect(Collectors.toList());

    // Java里面线程池的顶级接口是Executor，但是严格意义上讲Executor并不是一个线程池，而只是一个执行线程的工具。真正的线程池接口是ExecutorService
    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    private static final Random random = new Random();

    /*
     * Java中Record类型是Java 14中的预览函数引入的，并且应作为普通的 不可变 数据类，用于在类和应用程序之间进行数据传输。
     * 像Enum 一样，Record也是一个特殊的类输入Java。它旨在用于仅创建类以充当普通数据载体的地方
     * 类(Class)与记录(Record)之间的重要区别是，Record旨在消除设置和从实例获取数据所需的所有代码，Record将这种责任转移给生成编译器
     */
    record Quote(String shopName, double price, Discount.Code discountCode) {
        static Quote parse(String s) {
            String[] split = s.split(":");
            return new Quote(split[0], Double.parseDouble(split[1]), Discount.Code.valueOf(split[2]));
        }
    }

    private static class Discount {
        public enum Code {
            NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
            private final int percentage;
            Code(int percentage) { this.percentage = percentage; }
        }

        public static String getDiscountInfo(Quote quote) {
            return quote.shopName() + " price is " + Discount.calculateDiscount(quote.price(), quote.discountCode());
        }

        public static double calculateDiscount(double price, Code code) {
            randomDelay();
            return format(price * (100 - code.percentage) / 100);
        }
    }

    public static void delay(long speed) { try { Thread.sleep(speed); } catch (InterruptedException e) { throw new RuntimeException(e); } }
    public static void delay() { delay(1000L); }
    public static void randomDelay() { delay(500 + random.nextInt(2000)); }

    public static double format(double number) {
        DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.CHINA));
        return Double.parseDouble(formatter.format(number));
    }

    private static class Shop {
        private final String name;
        private final Random random;

        public Shop(String name) {
            this.name = name;
            random = new Random(IntStream.range(0, 3).reduce(1, (x, y) -> x * getChar(name, y)));
        }

        public String getPrice(String product) {
            double price = calculatePrice(product);
            Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
            return name + ":" + price + ":" + code;
        }

        public double calculatePrice(String product) {
            randomDelay();
            return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
        }

        private char getChar(String str, int index) { return str.charAt(index); }
    }

    private Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.getDiscountInfo(quote), executor)));
    }

    private List<String> findPrices(String product) {
        // CompletableFuture.supplyAsync工厂方法执行线程并返回一个CompletableFuture
        List<CompletableFuture<String>> priceFutures = findPricesStream(product).collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public static int work1(int x) {
        return IntStream.rangeClosed(0, x).sum();
    }

    public static int work2(int z) {
        return IntStream.rangeClosed(0, z).reduce(1, (x, y) -> x * y);
    }

    private void createDatabaseData() {
        try {
            final String JDBC_Directory_NAME = "jdbc";

            Properties properties = new Properties();
            properties.load(Files.newInputStream(Paths.get("database.properties")));
            Connection connection = DriverManager.getConnection(
                    properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.username"),
                    properties.getProperty("jdbc.password")
            );

            // 从某个数据库得到这个数据库下的所有表
            ResultSet resultSet = connection.getMetaData().getTables("books_db", null, null, new String[]{"TABLE"});
            Statement statement = connection.createStatement();

            List<Path> fileList = Files.walk(Paths.get(JDBC_Directory_NAME)).collect(Collectors.toList());

            int index = 1;  // 去掉目录名，剩下的全是文件名
            while (resultSet.next()) {
                Path path = fileList.get(index++);
                String fileName = path.getFileName().toString().replaceAll("\\.sql", "");
                String tableName = resultSet.getString("TABLE_NAME");
                /*
                 * Note: “->” 代表依次对应关系，即产生同等结果。
                 * resultSet.getString("TABLE_CAT"); -> resultSet.getString(1);
                 * resultSet.getString("TABLE_CAT"); -> resultSet.getString(2);
                 * resultSet.getString("TABLE_NAME"); -> resultSet.getString(3);
                 * resultSet.getString("TABLE_TYPE"); -> resultSet.getString(4);
                 * resultSet.getString("REMARKS"); -> resultSet.getString(5);
                 */
                if (!fileName.equalsIgnoreCase(tableName)) {
                    break;
                }
            }

            if (index == fileList.size()) {
                return;
            }
            fileList = fileList.subList(index, fileList.size());

            for (Path file : fileList) {
                String fileName = file.getFileName().toString();
                try (Scanner in = new Scanner(Files.newInputStream(Paths.get(JDBC_Directory_NAME, fileName)))) {
                    statement.executeUpdate(in.nextLine());
                    while (in.hasNextLine()) {
                        statement.addBatch(in.nextLine());
                    }
                    statement.executeBatch();
                }
            }
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private <T> Object getPairValue(T val, Function<T, ?> run, T defaultVal) {
        if (val != null) {
            return run.apply(val);
        }
        return defaultVal;
    }

    private void createCalendar() {
        createCalendar(60, 3);
    }

    private void createCalendar(int separatorLength, int defaultSpaceCount) {
        LocalDate localDate = LocalDate.now();
        LocalDate firstDay = localDate.with(TemporalAdjusters.firstDayOfMonth());
        StringBuilder stringBuilder = new StringBuilder();

        final var dayOfLocalDate = new Object() {
            LocalDate data = firstDay;
        };

        final int TODAY = localDate.getDayOfMonth();
        final int LAST_DAY = localDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        final int WEEK_DAY = firstDay.getDayOfWeek().getValue();

        final String[] WEEKS = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        final String DATE_STRING = localDate.getYear() + "年" + localDate.getMonthValue() + "月";

        final int ONE_WEEK_LENGTH = WEEKS[0].length();
        final int ONE_WEEK_TOTAL_LENGTH = defaultSpaceCount + ONE_WEEK_LENGTH;
        final int WEEKS_TOTAL_LENGTH = (WEEKS.length - 1) * ONE_WEEK_TOTAL_LENGTH + ONE_WEEK_LENGTH;
        final int WEEK_START_POSITION = (separatorLength - WEEKS_TOTAL_LENGTH) / 2;

        if (separatorLength < WEEKS_TOTAL_LENGTH || defaultSpaceCount <= 0) {
            logger.fine("The parameter separatorLength or DefaultSpaceCount is out of bounds! " +
                    "The parameter separatorLength must be greater than or equal to " + WEEKS_TOTAL_LENGTH + "." +
                    "Likewise, the parameter defaultSpaceCount must be greater than or equal to 1."
            );
            throw new IllegalArgumentException(
                    "The parameter separatorLength or DefaultSpaceCount is out of bounds! " +
                            "The parameter separatorLength must be greater than or equal to " + WEEKS_TOTAL_LENGTH + "." +
                            "Likewise, the parameter defaultSpaceCount must be greater than or equal to 1."
            );
        }


        BiFunction<String, Integer, String> createWhiteSpaceCharacter = (character, length) -> character.repeat(Math.max(0, length));
        WhiteSpaceChars<String, Integer, Consumer<String>> createWhiteSpaceCharacters = (character, length, whiteSpaceChars) -> {
            stringBuilder.append(createWhiteSpaceCharacter.apply(character, length));
            whiteSpaceChars.accept(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        };

        Runnable createSeparator = () -> createWhiteSpaceCharacters.run("=", separatorLength, this::println);
        createSeparator.run();

        createWhiteSpaceCharacters.run(" ", (separatorLength - DATE_STRING.length()) / 2, whiteSpace -> println(whiteSpace + DATE_STRING));
        createWhiteSpaceCharacters.run(
                " ",
                WEEK_START_POSITION,
                whiteSpace -> {
                    print(whiteSpace);
                    String weekWhiteSpaceCharacter = createWhiteSpaceCharacter.apply(" ", defaultSpaceCount);
                    List<String> set = new ArrayList<>();
                    for (int i = 0; i < WEEKS.length; ) {
                        int colorVal = ((int) (31 + (Math.random() * (39 - 31))));
                        String color = "\033[" + colorVal + "m%1$s\033[0m" + weekWhiteSpaceCharacter;
                        String discardWhiteSpace = color.substring(0, color.length() - weekWhiteSpaceCharacter.length());
                        if (set.contains(discardWhiteSpace)) {
                            continue;
                        }
                        if (i == WEEKS.length - 1) {
                            printf(discardWhiteSpace, WEEKS[i++]);
                        } else {
                            printf(color, WEEKS[i++]);
                        }
                        set.add(discardWhiteSpace);
                    }
                    println();
                }
        );

        final String PREFIX_DAY_WHITE_CHARACTER = createWhiteSpaceCharacter.apply(" ", WEEK_START_POSITION + 1);
        final String DAY_SPACE_WHITE_CHARACTER = createWhiteSpaceCharacter.apply(" ", ONE_WEEK_TOTAL_LENGTH - 1);

        print(PREFIX_DAY_WHITE_CHARACTER);
        for (int i = 0; i < ONE_WEEK_TOTAL_LENGTH * (WEEK_DAY - 1); i += ONE_WEEK_TOTAL_LENGTH) {
            print(createWhiteSpaceCharacter.apply(" ", ONE_WEEK_TOTAL_LENGTH));
        }

        // 删除多余的空白字符
        BiFunction<String, Integer, String> removeExcessWhitespaceCharacters = (origin, length) -> origin.substring(0, origin.length() - length);

        // 提取公共逻辑
        interface OptimizationLogicInterface<A, B, C, D, E> {
            E run(A currentValue, B initValue, C changeValue, D finalFunc);
        }
        OptimizationLogicInterface<Integer, Object, Object, Function<Object, Object>, String> optimizationLogic =
                (currentValue, initValue, changeValue, finalFunc) ->
                        (String) finalFunc.apply(currentValue > 9 ? changeValue : initValue);

        IntStream.rangeClosed(1, LAST_DAY).forEach(day -> {
            String format = "%1d" + DAY_SPACE_WHITE_CHARACTER;

            // 修复今天日期显示位置错位问题！10以内的数要使用\033[34m%1d\033[0m，否则要使用\033[34m%2d\033[0m
            if (day == TODAY) {
                format = optimizationLogic.run(
                        day,
                        1,
                        2,
                        formatNumber -> "\033[34m%" + formatNumber + "d\033[0m" + DAY_SPACE_WHITE_CHARACTER
                );
            }

            if (day == LAST_DAY) {
                format = removeExcessWhitespaceCharacters.apply(format, DAY_SPACE_WHITE_CHARACTER.length());
            }

            if (day < LAST_DAY) {
                dayOfLocalDate.data = dayOfLocalDate.data.plusDays(1);
            }

            if (dayOfLocalDate.data.getDayOfWeek().getValue() == 1) {
                printf(format, day);
                println();
                print(
                        optimizationLogic.run(
                                day,
                                PREFIX_DAY_WHITE_CHARACTER,
                                removeExcessWhitespaceCharacters.apply(PREFIX_DAY_WHITE_CHARACTER, 1),
                                Function.identity()
                        )
                );
            } else if (day >= 9 && day < LAST_DAY) {
                printf(removeExcessWhitespaceCharacters.apply(format, 1), day);
            } else {
                printf(format, day);
            }
        });
        println();
        createSeparator.run();

        // 飞机图形
        interface TraverseInterface {
            void run(int startIndex, int endIndex, Consumer<Integer> func);
        }
        TraverseInterface traverse = (startIndex, endIndex, func) -> IntStream.range(startIndex, endIndex).forEach(func::accept);

        interface PrintCharactersInterface {
            Runnable run(int whiteSpaceLength, String delimiter, String data);
        }
        interface StringBuilderInterface {
            PrintCharactersInterface run();
        }
        StringBuilderInterface createStringBuilder = () -> {
            AtomicReference<StringBuilder> ref = new AtomicReference<>(new StringBuilder());
            return (int whiteSpaceLength, String delimiter, String data) -> {
                StringBuilder builder = ref.get();
                println(
                        builder.append(createWhiteSpaceCharacter.apply(" ", whiteSpaceLength))
                                .append(join(delimiter, "", "", data))
                                .toString()
                );
                builder.setLength(0);
                return () -> ref.set(null);
            };
        };

        interface AirplanePartInterface {
            int run(int row, int column, int whiteSpaceLength, boolean hasPlaneHead);
        }
        AirplanePartInterface buildAirplanePart = (row, column, whiteSpaceLength, hasPlaneHead) -> {
            PrintCharactersInterface printCharacters = createStringBuilder.run();
            if (!hasPlaneHead) {
                StringBuilder characterBuilder = new StringBuilder();
                traverse.run(0, Math.max(row, column) - 1, index -> {
                    if (index == 0) {
                        characterBuilder.append(createWhiteSpaceCharacter.apply("@", 1));
                    } else {
                        characterBuilder.append("/");
                        characterBuilder.append(createWhiteSpaceCharacter.apply("^", column - (column - index) + 1 - 2));
                        characterBuilder.append("\\");
                    }
                    printCharacters.run(whiteSpaceLength + ((column << 1) - 1 >> 1) - index, "^", characterBuilder.toString());
                    characterBuilder.setLength(0);
                });
            }

            traverse.run(0, row, index -> printCharacters.run(whiteSpaceLength, " ", createWhiteSpaceCharacter.apply("*", column)));
            traverse.run(0, row, index -> {
                int len = row * column + (index << 1);
                Runnable clearClosure =
                        printCharacters.run(
                                whiteSpaceLength - ((len << 1) - ((column << 1) - 1) >> 1),
                                " ",
                                createWhiteSpaceCharacter.apply("*", len)
                        );
                if (index == row - 1) {
                    clearClosure.run();
                }
            });

            return whiteSpaceLength;
        };

        BiFunction<Integer, Integer, Integer> setBoundaryValue = (minValue, currentValue) -> Math.max(minValue, Math.min(10, currentValue));

        interface AirplaneInterface {
            void run(int row, int column, int whiteSpaceLength);
        }
        AirplaneInterface composeAirplane = (row, column, whiteSpaceLength) -> {
            row = setBoundaryValue.apply(3, row);
            column = setBoundaryValue.apply(2, column);

            int maximumNumberCharacters = ((((row * column) + (row - 1 << 1)) << 1) - (column << 1) - 1) >> 1;
            if (whiteSpaceLength < maximumNumberCharacters || whiteSpaceLength > (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()) {
                whiteSpaceLength = maximumNumberCharacters;
            }

            buildAirplanePart.run(row - 1, column, buildAirplanePart.run(row, column, whiteSpaceLength, false), true);
        };

        composeAirplane.run(3, 2, 10);
    }

    private void createRandomAccessFile() {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        File file = new File(System.getProperty("user.dir") + File.separator + "employee.dat");
        String filePath = file.getAbsolutePath();

        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            logger.fine("Unable to create employee.dat file.");
            e.printStackTrace();
        }

        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(filePath))) {
            for (Employee e : staff) {
                writeData(dataOutputStream, e);
            }
        } catch (IOException e) {
            logger.fine("DataOutputStream cannot write to the file.");
            e.printStackTrace();
        }

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r")) {
            int employeeCount = (int) (randomAccessFile.length() / Employee.RECORD_SIZE);
            Employee[] newStaff = new Employee[employeeCount];
            for (int i = 0; i < employeeCount; i++) {
                randomAccessFile.seek((long) i * Employee.RECORD_SIZE);
                newStaff[i] = readData(randomAccessFile);
            }
            Arrays.stream(newStaff).forEach(this::println);
        } catch (IOException e) {
            logger.fine("RandomAccessFile cannot read the file.");
            e.printStackTrace();
        }
    }

    private void writeData(DataOutput dataOutput, Employee employee) throws IOException {
        DataIO.writeFixedString(employee.getName(), Employee.NAME_SIZE, dataOutput);
        dataOutput.writeDouble(employee.getSalary());
        LocalDate localDate = employee.getHireDay();

        dataOutput.writeInt(localDate.getYear());
        dataOutput.writeInt(localDate.getMonthValue() + 1);
        dataOutput.writeInt(localDate.getDayOfMonth());
    }

    private Employee readData(DataInput dataInput) throws IOException {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, dataInput);
        double salary = dataInput.readDouble();
        return new Employee(name, salary, dataInput.readInt(), dataInput.readInt() - 1, dataInput.readInt());
    }

    /**
     * 旋转列表中的元素，将索引 i 的条目移动到位置（i + d) % l.size() 例如，将列表 [t，a, r] 旋转移 2 个位置后得到 [a, r，t]。
     * 这个方法的时间复杂度为O(n) , n 为列表的长度。
     */
    private <T> void rotate(List<T> list, int distance) {
        int size = list.size();
        if (size == 0 || distance % size == 0) {
            return;
        }
        distance = distance % size;
        if (distance < 0) {
            distance += size;
        }
        if (distance == 0) {
            return;
        }

        for (int cycleStart = 0, nMoved = 0; nMoved != size; cycleStart++) {
            T displaced = list.get(cycleStart);
            int i = cycleStart;
            do {
                i += distance;
                if (i >= size) {
                    i -= size;
                }
                displaced = list.set(i, displaced);
                nMoved++;
            } while (i != cycleStart);
        }
    }

    private Stream<int[]> createPythagoreanTriples(int low, int high, int limit) {
        return IntStream.rangeClosed(low, high).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, high)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}))
                .limit(limit);
    }

    private <T extends String> String join(T delimiter, T prefix, T suffix, CharSequence... data) {
        return baseJoin(delimiter, data, prefix, suffix, 0, (origin, i) -> ((CharSequence[]) origin)[i]);
    }

    private <T extends String> String join(T delimiter, T prefix, T suffix, T data) {
        return baseJoin(delimiter, data, prefix, suffix, 1, String::charAt);
    }

    private <T extends String> String join(T delimiter, T prefix, T suffix, T[] data) {
        return baseJoin(delimiter, data, prefix, suffix, 2, (origin, i) -> ((String[]) origin)[i]);
    }

    private <T extends String> String join(T delimiter, T prefix, T suffix, List<Integer> data) {
        return baseJoin(delimiter, data, prefix, suffix, 3, List::get);
    }

    private <T extends String> String join(T delimiter, T prefix, T suffix, int[] data) {
        return baseJoin(delimiter, data, prefix, suffix, 4, (origin, i) -> ((int[]) origin)[i]);
    }

    private <T, S extends String> String baseJoin(S delimiter, T data, S prefix, S suffix, int mask, BiFunction<T, Integer, Object> getElement) {
        return coreJoin(delimiter, data, prefix, suffix, mask, getElement, false).apply(0);
    }

    private <T> Function<Integer, String> coreJoin(
            String delimiter,
            T data,
            String prefix,
            String suffix,
            int digit,
            BiFunction<T, Integer, Object> getElement,
            boolean isInitialized
    ) {
        return length -> {
            if (!isInitialized) {
                byte mask = (byte) (1 << digit);
                Function<Integer, String> func = coreJoin(delimiter, data, prefix, suffix, digit, getElement, true);
                if ((mask & MaskList.CHAR_SEQUENCE.MASK) != 0) {
                    return func.apply(((CharSequence[]) data).length);
                }
                if ((mask & MaskList.STRING.MASK) != 0) {
                    return func.apply(((String) data).length());
                }
                if ((mask & MaskList.ARRAY_STRING.MASK) != 0) {
                    return func.apply(((String[]) data).length);
                }
                if ((mask & MaskList.LIST.MASK) != 0) {
                    return func.apply(((List) data).size());
                }
                if ((mask & MaskList.INT_ARRAY.MASK) != 0) {
                    return func.apply(((int[]) data).length);
                }
                return null;
            }

            StringBuilder stringBuilder = new StringBuilder();
            if (prefix.length() != 0) {
                stringBuilder.append(prefix);
            }

            for (int i = 0; i < length; i++) {
                stringBuilder.append(getElement.apply(data, i));
                if (i + 1 < length) {
                    stringBuilder.append(delimiter);
                }
            }

            if (suffix.length() != 0) {
                stringBuilder.append(suffix);
            }
            return stringBuilder.toString();
        };
    }

    private String fillZero(int val, int digits) {
        String binaryString = Long.toBinaryString((long) Math.pow(2, 32));
        String valToString = String.valueOf(val);
        return binaryString.substring(binaryString.length() - digits + valToString.length()) + valToString;
    }

    private int alloc(int space) {
        int initialCapacity = MIN_INITIAL_CAPACITY;
        if (space >= initialCapacity) {
            initialCapacity = space;
            int moveDigits = 1;
            while (moveDigits < 32) {
                initialCapacity |= (initialCapacity >>> moveDigits);
                moveDigits *= 2;
            }
            initialCapacity++;
        }
        return initialCapacity;
    }

    @BankTransferMoney(maximumTransferAmount = 15000)
    private String transferMoney(double money) {
        double maxMoney = (double) createBankTransferMoneySystem("BankTransferMoney");
        if (money > maxMoney) {
            return "对不起，转账失败！当前转账金额大于最大限额";
        }
        return "转账成功！转账金额为: " + String.format("%.2f", money) + "元";
    }

    private <T extends String> Object createBankTransferMoneySystem(T annotationName) {
        try {
            if (!isBankSystemAnnotation(annotationName)) {
                throw new ClassNotFoundException("银行系统注解名字必须以Bank开头!");
            }

            Class<?> baseClass = this.getClass();
            Class<? extends Annotation> bankTransferAccountsClass = (Class<? extends Annotation>) Class.forName(annotationName);
            String callThisMethodFunctionName = Thread.currentThread().getStackTrace()[2].getMethodName();

            ReflexMethodsStreamInterface<Class<?>, Stream<Method>> reflexMethodsStream = cls -> Arrays.stream(cls.getDeclaredMethods());
            MethodNameEqualInterface<Method, String, Boolean> methodNameEqual = (method, methodName) -> method.getName().equals(methodName);
            ParametersTypeInterface<String, Class<?>[]> parametersTypeInterface = methodName ->
                    reflexMethodsStream.getReflexMethodStream(baseClass)
                            .filter(method -> methodNameEqual.isMethodNameEqual(method, methodName))
                            .findFirst()
                            .map(Method::getParameterTypes)
                            .orElseGet(() -> new Class[0]);
            ThrowNoSuchMethodErrorInterface<String> throwNoSuchMethodError = message -> {
                throw new NoSuchMethodException(message);
            };

            Method methodUnderAnnotation = baseClass.getDeclaredMethod(
                    callThisMethodFunctionName,
                    parametersTypeInterface.getMethodParametersType(callThisMethodFunctionName)
            );
            boolean isAnnotation = methodUnderAnnotation.isAnnotationPresent(bankTransferAccountsClass);
            if (isAnnotation) {
                Annotation bankTransferMoney = methodUnderAnnotation.getDeclaredAnnotation(bankTransferAccountsClass);
                String annotationAttribute = bankTransferAccountsClass.getDeclaredMethods()[0].getName();
                Method annotationVariableName = reflexMethodsStream.getReflexMethodStream(bankTransferAccountsClass)
                        .filter(method -> methodNameEqual.isMethodNameEqual(method, annotationAttribute))
                        .findFirst().orElse(null);
                if (annotationVariableName == null) {
                    throwNoSuchMethodError.createNoSuchMethodError(bankTransferAccountsClass.getName() + "注解上不存在" + annotationAttribute + "属性!");
                }
                return annotationVariableName.invoke(bankTransferMoney);
            }
            throw new ClassNotFoundException("属性" + callThisMethodFunctionName + "不存在!");
        } catch (NoSuchMethodException | InvocationTargetException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return "银行系统内部异常，请检查！";
    }

    private boolean isBankSystemAnnotation(String annotationName) {
        return check("^Bank(?:[A-Z](?:[a-z]+))+$", annotationName);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private char randomChar() {
        Random rand = new Random();
        int min_char_code = CharCode.MIN_CHAR_CODE.getCode();
        int randomVal = rand.nextInt(CharCode.MAX_CHAR_CODE.getCode() - min_char_code) + min_char_code;
        if (randomVal > CharCode.MIN_SPECIAL_CHAR_CODE.getCode() && randomVal < CharCode.MAX_SPECIAL_CHAR_CODE.getCode()) {
            return this.randomChar();
        }
        return (char) randomVal;
    }

    private int getDeleteDigitsAfterMinimumNumber(long num, int deleteCount) {
        String stringNum = String.valueOf(num);
        final int STRING_LENGTH = stringNum.length();
        char[] stack = new char[STRING_LENGTH - deleteCount + 1];
        final int STACK_LENGTH = stack.length;

        for (int i = 0, top = 0; i < STRING_LENGTH; i++) {
            char c = stringNum.charAt(i);
            for (; deleteCount > 0 && top > 0 && stack[top - 1] > c; top--, deleteCount--) {
                ;
            }
            stack[top++] = c;
        }

        int offset = 0;
        for (; offset < STACK_LENGTH && stack[offset] == '0'; offset++) {
            ;
        }

        return offset == STACK_LENGTH - 1 ? 0 : Integer.parseInt(new String(stack, offset, STACK_LENGTH - offset - 1));
    }

    private int lcm(int a, int b) {
        return a * b / stein(a, b);
    }

    private int stein(int a, int b) {
        if (a / b > 0 && a % b == 0 || b / a > 0 && b % a == 0) {
            return Math.min(a, b);
        }
        if (a == b || b == 0) {
            return b;
        }

        if ((a & 1) == 0 && (b & 1) == 0) {
            return stein(a >> 1, b >> 1) << 1;
        }
        if ((a & 1) == 0 && (b & 1) != 0) {
            return stein(a >> 1, b);
        }
        if ((a & 1) != 0 && (b & 1) == 0) {
            return stein(a, b >> 1);
        }
        return stein(a < b ? -(a - b) : a - b, a);
    }

    private int sum(int first, int last) {
        return (first + last) * last / 2;
    }

    private <T> void print(T arg) {
        System.out.print(arg);
    }

    private <T> void println(T arg) {
        System.out.println(arg);
    }

    private void println() {
        System.out.println();
    }

    private void printf(String format, Object... val) {
        System.out.printf(format, val);
    }

    private boolean check(String reg, String val) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(val);
        return matcher.find();
    }
}
