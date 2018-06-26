package com.ahuang.bookCornerServer.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustUsersEntity implements Serializable{

	private static final long serialVersionUID = 8684899405648144184L;
	private String userNo;
	private String userName;
}
