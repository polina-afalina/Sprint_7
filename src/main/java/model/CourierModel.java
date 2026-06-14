package model;

public class CourierModel {

    private String login;
    private String password;
    private String firstName;

    public CourierModel(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static CourierModel withLoginAndPassword(String login, String password) {
        return new CourierModel(login, password, null);
    }

    public static CourierModel withLogin(String login) {
        return new CourierModel(login, null, null);
    }

    public static CourierModel withPassword(String password) {
        return new CourierModel(null, password, null);
    }

    public static CourierModel withLoginAndFirstName(String login, String firstName) {
        return new CourierModel(login, null, firstName);
    }

    public static CourierModel withPasswordAndFirstName(String password, String firstName) {
        return new CourierModel(null, password, firstName);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
