import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// ================= PRODUCT CLASS =================

class Product {

    private String productName;
    private double price;

    public Product(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }
}


// ================= ORDER BASE CLASS =================

abstract class OrderBase {

    protected static int counter = 1;

    protected String orderId;
    protected Product product;
    protected double amount;

    public OrderBase(Product product) {

        this.product = product;
        this.amount = product.getPrice();

        // Auto Generated Order ID
        this.orderId = String.format("%04d", counter++);

        // Reset after 100
        if (counter > 100) {
            counter = 1;
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public double getAmount() {
        return amount;
    }

    public abstract void createOrder();
}


// ================= ORDER TYPES =================

class RegularOrder extends OrderBase {

    public RegularOrder(Product product) {
        super(product);
    }

    @Override
    public void createOrder() {

        System.out.println("Regular Order Created");
    }
}

class DiscountedOrder extends OrderBase {

    public DiscountedOrder(Product product) {
        super(product);
    }

    @Override
    public void createOrder() {

        amount = amount - (amount * 0.10);

        System.out.println("10% Discount Applied");
        System.out.println("Discounted Order Created");
    }
}

class PriorityOrder extends OrderBase {

    public PriorityOrder(Product product) {
        super(product);
    }

    @Override
    public void createOrder() {

        amount = amount + 100;

        System.out.println("Priority Charge Added");
        System.out.println("Priority Order Created");
    }
}


// ================= ORDER FACTORY =================

class OrderFactory {

    public static OrderBase createOrder(
            int choice,
            Product product) {

        if (choice == 1) {
            return new RegularOrder(product);
        }
        else if (choice == 2) {
            return new DiscountedOrder(product);
        }
        else {
            return new PriorityOrder(product);
        }
    }
}


// ================= PAYMENT INTERFACE =================

interface PaymentMethod {
    void pay(double amount);
}


// ================= PAYMENT CLASSES =================

class CreditCardPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {

        System.out.println(
                "Payment Done Using Credit Card");
    }
}

class UPIPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {

        System.out.println(
                "Payment Done Using UPI");
    }
}

class WalletPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {

        System.out.println(
                "Payment Done Using Wallet");
    }
}


// ================= NOTIFICATION INTERFACE =================

interface NotificationService {

    void sendNotification(String message);
}


// ================= NOTIFICATION CLASSES =================

class EmailNotification
        implements NotificationService {

    @Override
    public void sendNotification(String message) {

        System.out.println(
                "Email Notification: "
                        + message);
    }
}

class SMSNotification
        implements NotificationService {

    @Override
    public void sendNotification(String message) {

        System.out.println(
                "SMS Notification: "
                        + message);
    }
}

class PushNotification
        implements NotificationService {

    @Override
    public void sendNotification(String message) {

        System.out.println(
                "Push Notification: "
                        + message);
    }
}


// ================= STORAGE INTERFACE =================

interface OrderRepository {

    void saveOrder(OrderBase order);
}


// ================= FILE STORAGE =================

class FileRepository
        implements OrderRepository {

    @Override
    public void saveOrder(OrderBase order) {

        try {

            FileWriter writer =
                    new FileWriter(
                            "orders.txt",
                            true
                    );

            writer.write(
                    "Order ID: "
                            + order.getOrderId()

                            + "\nProduct: "
                            + order.getProduct()
                            .getProductName()

                            + "\nFinal Price: Rs."
                            + order.getAmount()

                            + "\n----------------------\n"
            );

            writer.close();

            System.out.println(
                    "Order Saved In orders.txt");

        } catch (IOException e) {

            System.out.println(
                    "Error Saving Order");
        }
    }
}


// ================= ORDER SERVICE =================

class OrderService {

    private PaymentMethod paymentMethod;

    private NotificationService
            notificationService;

    private OrderRepository repository;

    public OrderService(
            PaymentMethod paymentMethod,

            NotificationService
                    notificationService,

            OrderRepository repository) {

        this.paymentMethod =
                paymentMethod;

        this.notificationService =
                notificationService;

        this.repository =
                repository;
    }

    public void processOrder(
            OrderBase order) {

        order.createOrder();

        System.out.println(
                "Final Amount: Rs."
                        + order.getAmount());

        paymentMethod.pay(
                order.getAmount());

        notificationService
                .sendNotification(
                        "Order "
                                + order.getOrderId()
                                + " placed successfully"
                );

        repository.saveOrder(order);

        System.out.println(
                "\n===== ORDER DETAILS =====");

        System.out.println(
                "Order ID: "
                        + order.getOrderId());

        System.out.println(
                "Product: "
                        + order.getProduct()
                        .getProductName());

        System.out.println(
                "Total Price: Rs."
                        + order.getAmount());

        System.out.println(
                "\nOrder Completed Successfully");
    }
}


// ================= MAIN CLASS =================

public class Order {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Predefined Products
        Product p1 =
                new Product(
                        "Laptop",
                        55000
                );

        Product p2 =
                new Product(
                        "Phone",
                        25000
                );

        Product p3 =
                new Product(
                        "Headphones",
                        3000
                );

        // Product Selection
        System.out.println(
                "===== PRODUCT LIST =====");

        System.out.println(
                "1. Laptop - Rs.55000");

        System.out.println(
                "2. Phone - Rs.25000");

        System.out.println(
                "3. Headphones - Rs.3000");

        System.out.print(
                "\nSelect Product: ");

        int productChoice =
                sc.nextInt();

        Product selectedProduct;

        if (productChoice == 1) {
            selectedProduct = p1;
        }
        else if (productChoice == 2) {
            selectedProduct = p2;
        }
        else {
            selectedProduct = p3;
        }

        // Order Type
        System.out.println(
                "\nSelect Order Type:");

        System.out.println(
                "1. Regular Order");

        System.out.println(
                "2. Discounted Order");

        System.out.println(
                "3. Priority Order");

        int orderChoice =
                sc.nextInt();

        OrderBase order =
                OrderFactory.createOrder(
                        orderChoice,
                        selectedProduct
                );

        // Payment Method
        System.out.println(
                "\nSelect Payment Method:");

        System.out.println(
                "1. Credit Card");

        System.out.println(
                "2. UPI");

        System.out.println(
                "3. Wallet");

        int paymentChoice =
                sc.nextInt();

        PaymentMethod paymentMethod;

        if (paymentChoice == 1) {
            paymentMethod =
                    new CreditCardPayment();
        }
        else if (paymentChoice == 2) {
            paymentMethod =
                    new UPIPayment();
        }
        else {
            paymentMethod =
                    new WalletPayment();
        }

        // Notification Type
        System.out.println(
                "\nSelect Notification Type:");

        System.out.println(
                "1. Email");

        System.out.println(
                "2. SMS");

        System.out.println(
                "3. Push Notification");

        int notifyChoice =
                sc.nextInt();

        NotificationService
                notificationService;

        if (notifyChoice == 1) {

            notificationService =
                    new EmailNotification();
        }
        else if (notifyChoice == 2) {

            notificationService =
                    new SMSNotification();
        }
        else {

            notificationService =
                    new PushNotification();
        }

        // Storage Mechanism
        OrderRepository repository =
                new FileRepository();

        // Dependency Injection
        OrderService service =
                new OrderService(
                        paymentMethod,
                        notificationService,
                        repository
                );

        System.out.println(
                "\n===== PROCESSING ORDER =====\n");

        service.processOrder(order);

        sc.close();
    }
}
