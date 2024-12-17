public class HealthRecord {
    private int height; // ความสูงของนักเรียน (หน่วย: เซนติเมตร)

    // ค่าคงที่
    private static final int MIN_PERMITTED_HEIGHT = 50;
    private static final int MAX_PERMITTED_HEIGHT = 175;
    private static final int DEFAULT_HEIGHT = 100;

    // ตัวแปร static สำหรับเก็บสถานะคลาส
    private static int tallestHeight = DEFAULT_HEIGHT;
    private static int shortestHeight = DEFAULT_HEIGHT;
    private static int counter = 0; // นับจำนวนออบเจกต์ที่ถูกสร้าง
    private static double averageHeight = 0.0; // ค่าเฉลี่ยความสูง

    // Constructor
    public HealthRecord(int height) {
        setHeight(height);
        counter++; // เพิ่มจำนวนออบเจกต์เมื่อสร้าง
    }

    // Getter สำหรับดึงค่าความสูง
    public int getHeight() {
        return height;
    }

    // Setter สำหรับกำหนดค่าความสูง
    public void setHeight(int height) {
        int oldHeight = this.height; // เก็บค่าความสูงก่อนหน้า

        // ตรวจสอบว่าค่าความสูงอยู่ในช่วงที่อนุญาต
        if (height >= MIN_PERMITTED_HEIGHT && height <= MAX_PERMITTED_HEIGHT) {
            this.height = height;
        } else {
            this.height = DEFAULT_HEIGHT;
        }

        // อัปเดตความสูงสูงสุดและต่ำสุด
        if (this.height > tallestHeight) {
            tallestHeight = this.height;
        }
        if (this.height < shortestHeight) {
            shortestHeight = this.height;
        }

        // อัปเดตค่าเฉลี่ยความสูง
        averageHeight = (averageHeight * (counter - 1) - oldHeight + this.height) / counter;
    }

    // Static Getter สำหรับดึงค่าความสูงสูงสุด
    public static int getTallestHeight() {
        return tallestHeight;
    }

    // Static Getter สำหรับดึงค่าความสูงต่ำสุด
    public static int getShortestHeight() {
        return shortestHeight;
    }

    // Static Getter สำหรับดึงค่าเฉลี่ยความสูง
    public static double getAverageHeight() {
        return averageHeight;
    }

    // Method สำหรับแสดงข้อมูลนักเรียน
    public void displayDetails() {
        System.out.println("Height (cm): " + getHeight());
    }

    // Static Method สำหรับแสดงข้อมูลคลาส
    public static void displayClassDetails() {
        System.out.println("The tallest height (cm): " + getTallestHeight());
        System.out.println("The shortest height (cm): " + getShortestHeight());
        System.out.println("The average height (cm): " + String.format("%.2f", getAverageHeight()));
    }
    public static void main(String[] args) {
        // สร้าง HealthRecord objects
        HealthRecord student1 = new HealthRecord(120);
        HealthRecord student2 = new HealthRecord(55);
        HealthRecord student3 = new HealthRecord(180); // ค่าจะถูกแทนที่ด้วย DEFAULT_HEIGHT (100)

        // แสดงรายละเอียดของแต่ละออบเจกต์
        student1.displayDetails(); // 120
        student2.displayDetails(); // 55
        student3.displayDetails(); // 100

        // แสดงรายละเอียดคลาสรวม
        HealthRecord.displayClassDetails();
        // Output:
        // The tallest height (cm): 120
        // The shortest height (cm): 55
        // The average height (cm): 91.67
    }
}
