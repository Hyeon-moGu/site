package com.site.site;

import com.site.site.model.entity.Question;
import com.site.site.repository.QuestionRepository;
import com.site.site.service.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SiteApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuestionService questionService;

	@Test
	@DisplayName("JPA 테스트 데이터 생성")
	void Test_JPA() {
		for (int i = 1; i <= 100; i++) {
			String title = String.format("[%03d]번째 테스트 제목", i);
			String content = "내용없음";
			this.questionService.create(title, content, null);
		}
	}

	@Test
	@DisplayName("id로 제목 조회")
	void Test_FindByTitle() {
		Question q = this.questionRepository.findByTitle("Test Title One");
		assertEquals(2, q.getId());
	}

	@Test
	@DisplayName("id로 제목 및 내용 조회")
	void Test_FindByTitleAndContent() {
		Question q = this.questionRepository.findByTitleAndContent(
				"Test Title One", "Test Content One");
		assertEquals(2, q.getId());
	}



}
