package services.member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.typesafe.config.Config;

import annotations.member.ServiceInterceptorTarget;
import clients.member.CacheClinet;
import clients.member.WebserviceClient;
import daos.member.UserDao;
import play.api.i18n.Lang;
import play.i18n.Langs;
import play.i18n.MessagesApi;
import requests.member.UserRequest;
import responses.member.UserResponse;

@Singleton
public class HomeService {
	
	private Logger logger = LoggerFactory.getLogger(HomeService.class);
	
	@Inject
	private play.Application applidation;
	
	@Inject
	private Config config;
	
	@Inject
	private MessagesApi messagesApi;
	
	@Inject
	private Langs langs;
	
	@Inject
	private UserDao userDao;
	
	@Inject
	private CacheClinet cacheClinet;
	
	@Inject
	private WebserviceClient webserviceClient;
	
	public void index() {
	    
	    String key = "bar";
        cacheClinet.set(key, "aaa");
        String value = (String) cacheClinet.get(key);
        logger.info(String.format("cache value: %s", value));
	}
	
	public List<?> list() {
        return userDao.find();
    }

    public void detail() {
        webserviceClient.get().handle((response, error) -> {
            if (error != null) {
                logger.warn("NG");
                logger.error("error", error);
                return null;
            } else {
                logger.info("OK");
                logger.info(response.getAllHeaders().toString());
                logger.info(String.valueOf(response.getStatus()));
                logger.info(response.getStatusText());
                logger.info(response.getBody());
                return null;
            }
        });
    }
	
	@ServiceInterceptorTarget
	public UserResponse input(UserRequest userRequest) {
	  //userDao.updateNameById(1, "yamak22");
        userDao.create(userRequest.getSecondEmail(), userRequest.getPassword(), userRequest.getEmail());
        logger.info(String.format("HomeService#%s()", "input"));
        logger.info(String.format("userRequest.toString - %s", userRequest.toString()));
        String title = messagesApi.get(new Lang(Locale.JAPAN), "error.minLength", new Integer(3));
        logger.info(String.format("title:%s", title));
        logger.info(String.format("application path: %s", applidation.path().getAbsolutePath()));
        UserResponse response = new UserResponse();
        response.setUserFirst("taro");
        response.setUserName("suzu");
        response.setLd(LocalDate.now());
        response.setLdt(LocalDateTime.now());
        response.setLt(LocalTime.now());
        return response;
    }
}
