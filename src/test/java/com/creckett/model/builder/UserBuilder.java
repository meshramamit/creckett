package com.creckett.model.builder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.springframework.test.util.ReflectionTestUtils;

import com.creckett.model.User;

public class UserBuilder {

	private Long id;

	private String emailId;

	private String name;

	private Boolean playing;

	public User build() {
		try {
			Class<User> userClass = User.class;
			Constructor<?> constructor = userClass.getDeclaredConstructor(new Class[0]);
			constructor.setAccessible(true);
			User user = (User) constructor.newInstance();
			Field[] fields = User.class.getDeclaredFields();
			for (Field field : fields) {
				ReflectionTestUtils.setField(user, field.getName(),
						ReflectionTestUtils.getField(this, field.getName()));
			}
			constructor.setAccessible(false);
			user.setId(id);
			return user;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public UserBuilder withId(Long userId) {
		this.id = userId;
		return this;
	}

	public UserBuilder withEmailId(String emailId) {
		this.emailId = emailId;
		return this;
	}

	public UserBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public UserBuilder withPlaying(Boolean playing) {
		this.playing = playing;
		return this;
	}
}
