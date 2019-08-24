/**
 * 
 */
package com.wizer.test.config.security.util;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 *
 * @author uniqueM
 *
 * This source file was written by Adindu Stevens as uniqueM.
 * On 2019-02-09 20:21:35
 * It may be altered or replicated by anyone capable,
 * but the risks that might be accrued by such actions shall be borne 
 * by the same.
 * uniqueM may however, be contacted through chukwudereadindu@gmail.com if
 * there arises a need for a additional functionaly that may not have been
 * included in the original project.
 * If the module package does not include a user manual,
 * uniqueM may also be contacted to provide one or other technical clarification.
 * 
 */
public class Authority implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String authority;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
