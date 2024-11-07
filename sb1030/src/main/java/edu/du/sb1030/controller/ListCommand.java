package edu.du.sb1030.controller;

import java.time.LocalDateTime;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ListCommand {

	@DateTimeFormat(pattern = "yyyyMMddHH")
	private LocalDateTime from;
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private LocalDateTime to;
}
