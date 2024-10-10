package com.example.sb_1010_1.config;

import com.example.sb_1010_1.spring.MemberPrinter;
import com.example.sb_1010_1.spring.MemberSummaryPrinter;
import com.example.sb_1010_1.spring.VersionPrinter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"com.example.sb_1010_1.spring", "com.example.sb_1010_1.spring2"},
		excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ManualBean.class)})
public class AppCtx {

	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
