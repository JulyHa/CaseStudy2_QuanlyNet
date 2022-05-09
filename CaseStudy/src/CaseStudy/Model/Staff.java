package CaseStudy.Model;

public class Staff {
    private String id;
    private String fullName;
    private String homeAddress;
    private String phoneNumber;
    private String yearOfBirth;
    private boolean isWork;

    public Staff(String id,String fullName, String homeAddress, String phoneNumber, String yearOfBirth, boolean isWork) {
        this.id = id;
        this.fullName = fullName;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.yearOfBirth = yearOfBirth;
        this.isWork = isWork;
    }

    public Staff() {
        this.id = null;
        this.fullName = null;
        this.homeAddress = null;
        this.phoneNumber = null;
        this.yearOfBirth = null;
        this.isWork = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", isWork=" + isWork +
                '}';
    }
}
