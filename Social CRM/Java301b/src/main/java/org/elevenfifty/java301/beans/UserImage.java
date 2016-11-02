package org.elevenfifty.java301.beans;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.Base64Utils;
@Entity
@Table(name="user_images")

public class UserImage {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	private long userId;
	private String type;
	private int key;
	//Can I change this to a byte instead of a long
	private byte[] image;
	private String contentType;
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return id == other.getId();
	}

	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	

	public byte[] getImage() {
		return image;
	}
	
	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}
	
    public String getHtmlSrc() {
        return "data:" + this.contentType + ":base64," + Base64Utils.encodeToString(image);
	}

}
	
	
	


