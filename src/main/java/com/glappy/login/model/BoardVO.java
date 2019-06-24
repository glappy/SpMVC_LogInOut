package com.glappy.login.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardVO {
	private long id;
	private String b_userid;
	private String b_date;
	private String b_time;
	private String b_title;
	private String b_content;
	private long b_hit;
	private String b_image;
}
