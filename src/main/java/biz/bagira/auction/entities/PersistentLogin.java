package biz.bagira.auction.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Dmitriy on 18.02.2017.
 */


@Entity
@Table(name="PERSISTENT_LOGINS")
public class PersistentLogin {

    @Id
    	private String series;

    	@Column(name="USERNAME", unique=true, nullable=false)
    	private String username;

    	@Column(name="TOKEN", unique=true, nullable=false)
    	private String token;

    	@Temporal(TemporalType.TIMESTAMP)
    	private Date last_used;

    	public String getSeries() {
    		return series;
    	}

    	public void setSeries(String series) {
    		this.series = series;
    	}

    	public String getUsername() {
    		return username;
    	}

    	public void setUsername(String username) {
    		this.username = username;
    	}

    	public String getToken() {
    		return token;
    	}

    	public void setToken(String token) {
    		this.token = token;
    	}

    	public Date getLast_used() {
    		return last_used;
    	}

    	public void setLast_used(Date last_used) {
    		this.last_used = last_used;
    	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PersistentLogin that = (PersistentLogin) o;

		if (series != null ? !series.equals(that.series) : that.series != null) return false;
		if (username != null ? !username.equals(that.username) : that.username != null) return false;
		return token != null ? token.equals(that.token) : that.token == null;

	}

	@Override
	public int hashCode() {
		int result = series != null ? series.hashCode() : 0;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (token != null ? token.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "PersistentLogin{" +
				"series='" + series + '\'' +
				", username='" + username + '\'' +
				", token='" + token + '\'' +
				", last_used=" + last_used +
				'}';
	}
}
