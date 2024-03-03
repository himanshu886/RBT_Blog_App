package com.rbt.payload;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogDto {
	
    private Integer blogId;
	
	private String title; 
	private String content;

}
