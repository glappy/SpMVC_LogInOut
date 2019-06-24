package com.glappy.login.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.ScriptAssert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ScriptAssert(lang = "javascript", message = "비밀번호와 비밀번호 확인이 일치하지 않습니다", 
			reportOn = "m_re_password", script = ("_this.m_parrword=_this.m_re_password"))
public class MemberVO {
	
	@Size(min = 3, max = 30, message = "ID는 3부터 30자리까지")
	@NotBlank(message="ID는 공백이 올 수 없습니다")
	@Email(message="ID는 이메일 형식이어야 합니다")
	private String m_userid;
	
	@NotBlank(message="비밀번호를 입력하세요")
	private String m_password;
	private String m_re_password;
	
	@NotBlank(message="사용자 이름을 입력하세요")
	private String m_name;
	
	@Pattern(regexp="\\d(1,15)", message="전화번호는 숫자 1부터 15자리까지만 가능")
	private String m_tel;
}