package biz.bagira.auction.entities;

import javax.persistence.*;

@Entity
@Table(name="PROFILE")
public class UserProfile {


	private Integer id;


	private String type = UserProfileType.USER.getUserProfileType();


	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="TYPE", length=15, unique=true, nullable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserProfile that = (UserProfile) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		return type != null ? type.equals(that.type) : that.type == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "UserProfile{" +
				"id=" + id +
				", type='" + type + '\'' +
				'}';
	}
}
