package PhoneBook;

public class PhoneNumber {
    private String phoneNumber;
    private int count;
    private String name;

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.count = 0;
    }

    public PhoneNumber(String name, String phoneNumber) {
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int compareTo(PhoneNumber phoneNumber) {
        return Integer.compare(this.count, phoneNumber.getCount());
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getPhoneNumber();
    }
}

