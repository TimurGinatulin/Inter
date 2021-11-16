package lesson1;
/*
* Или использовать аннотацию @Builder lombok
* */
public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public static class PersonBuilder {
        private final Person person;

        private PersonBuilder() {
            person = new Person();
        }

        public PersonBuilder firstName(String firstName) {
            person.firstName = firstName;
            return this;
        }

        public PersonBuilder lastName(String lastName) {
            person.lastName = lastName;
            return this;
        }

        public PersonBuilder middleName(String middleName) {
            person.middleName = middleName;
            return this;
        }

        public PersonBuilder country(String country) {
            person.country = country;
            return this;
        }

        public PersonBuilder address(String address) {
            person.address = address;
            return this;
        }

        public PersonBuilder phone(String phone) {
            person.phone = phone;
            return this;
        }

        public PersonBuilder age(int age) {
            person.age = age;
            return this;
        }

        public PersonBuilder gender(String gender) {
            person.phone = gender;
            return this;
        }

        public Person build() {
            return person;
        }
    }
}
