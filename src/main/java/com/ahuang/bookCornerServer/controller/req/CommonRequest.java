package com.ahuang.bookCornerServer.controller.req;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommonRequest<T> {
	private String method;
	private Map<String, Object> header;
	@Valid
	@NotNull(message = "params不能为空")
	private T params;
}
