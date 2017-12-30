package responses.member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UserResponse {

    private String userName;
    
    private String userFirst;
    
    private LocalTime lt;
    
    private LocalDate ld;
    
    private LocalDateTime ldt;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirst() {
        return userFirst;
    }

    public void setUserFirst(String userFirst) {
        this.userFirst = userFirst;
    }

    public LocalTime getLt() {
        return lt;
    }

    public void setLt(LocalTime lt) {
        this.lt = lt;
    }

    public LocalDate getLd() {
        return ld;
    }

    public void setLd(LocalDate ld) {
        this.ld = ld;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }
}
