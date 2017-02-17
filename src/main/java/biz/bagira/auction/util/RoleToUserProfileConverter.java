package biz.bagira.auction.util;

import biz.bagira.auction.entities.UserProfile;
import biz.bagira.auction.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
/**
 * Created by Dmitriy on 16.02.2017.
 */

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {
    private static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);

    @Autowired
    ProfileService profileService;

    /**
     * Gets UserProfile by Id
     *
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public UserProfile convert(Object element) {
        Integer id = Integer.parseInt((String) element);
        UserProfile profile = profileService.getById(id);
        logger.info("Profile : " + profile);
        return profile;
    }



}