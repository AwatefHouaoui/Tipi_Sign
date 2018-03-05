package org.Test.Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long authorityId;
	private String authorityName;
	private long ranking;

	public Authority() {
		super();
	}

	/**
	 * Constructor with parameters
	 * 
	 * @author awatef
	 * @since version 0.2.0
	 * @param authorityId
	 *            id of authority
	 * @param authorityName
	 *            name of authority
	 * @param ranking
	 *            ranking of authority
	 */
	public Authority(long authorityId, String authorityName, long ranking) {
		super();
		this.authorityId = authorityId;
		this.authorityName = authorityName;
		this.ranking = ranking;
	}

	public long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(long authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public long getRanking() {
		return ranking;
	}

	public void setRanking(long ranking) {
		this.ranking = ranking;
	}

}