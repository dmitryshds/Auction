package biz.bagira.auction.entities;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * Created by Dmitriy on 17.03.2017.
 */
public class UserBuilder {

    private User user;

    public UserBuilder() {
        this.user = new User();
    }

    public UserBuilder id(Integer idUsers){
        ReflectionTestUtils.setField(user,"idUsers",idUsers);
        return this;
    }
    public UserBuilder login(String login){
        ReflectionTestUtils.setField(user,"login",login);
        return this;
    }

    public User build(){
        return user;
    }
}
