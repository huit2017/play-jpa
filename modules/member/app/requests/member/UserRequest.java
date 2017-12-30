package requests.member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;

import play.data.format.Formats;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;

public class UserRequest {

	@Required
	@Email
	protected String email;
	
	@Required
	protected String password;
	
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	@Required
	@Formats.DateTime(pattern = "yyyy/MM/dd")
	protected Date birthday;
	
	@Required
	@Email
	protected String secondEmail;
	
	@JsonFormat(pattern = "uuuu/MM/dd HH:mm:ss", timezone = "Asia/Tokyo")
	@Required
	@Formats.DateTime(pattern = "uuuu/MM/dd HH:mm:ss")
	protected LocalDateTime entryday;
	
	@JsonFormat(pattern = "uuuu/MM/dd", timezone = "Asia/Tokyo")
    @Required
    @Formats.DateTime(pattern = "uuuu/MM/dd")
	protected LocalDate anniversary;
	
	@JsonFormat(pattern = "HH:mm:ss", timezone = "Asia/Tokyo")
    @Required
    @Formats.DateTime(pattern = "HH:mm:ss")
    protected LocalTime formTime;
	
	@JsonFormat(pattern = "HH:mm", timezone = "Asia/Tokyo")
	@Required
    @Formats.DateTime(pattern = "HH:mm")
    protected LocalTime toTime;
	
	@Valid
    protected List<UserChildRequest> childs;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public void setSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
    }

    public LocalDateTime getEntryday() {
        return entryday;
    }

    public void setEntryday(LocalDateTime entryday) {
        this.entryday = entryday;
    }

    public LocalDate getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(LocalDate anniversary) {
        this.anniversary = anniversary;
    }

    public LocalTime getFormTime() {
        return formTime;
    }

    public void setFormTime(LocalTime formTime) {
        this.formTime = formTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }
    
    public List<UserChildRequest> getChilds() {
        return childs;
    }

    public void setChilds(List<UserChildRequest> childs) {
        this.childs = childs;
    }
    
    @Override
    public String toString() {
        return "UserRequest [email=" + email + ", password=" + password + ", birthday=" + birthday + ", secondEmail="
                + secondEmail + ", entryday=" + entryday + ", anniversary=" + anniversary + ", formTime=" + formTime
                + ", toTime=" + toTime + ", childs=" + childs + "]";
    }
    
    
}
